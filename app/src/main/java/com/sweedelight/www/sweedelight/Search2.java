package com.sweedelight.www.sweedelight;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Search2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Search2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Search2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View myFragmentView;
    SearchView search;
    ImageButton buttonBarcode;
    ImageButton buttonAudio;
    // Typeface type;
    ListView searchResults;
    String found = "N";
    URL url_api= null;

    //This arraylist will have data as pulled from server. This will keep cumulating.
    ArrayList<Product> productResults = new ArrayList<Product>();
    //Based on the search string, only filtered products will be moved here from productResults
    ArrayList<Product> filteredProductResults = new ArrayList<Product>();

    private OnFragmentInteractionListener mListener;

    public Search2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Search2.
     */
    // TODO: Rename and change types and number of parameters
    public static Search2 newInstance(String param1, String param2) {
        Search2 fragment = new Search2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //get the context of the HomeScreen Activity
        final MainActivity activity = (MainActivity) getActivity();

        //define a typeface for formatting text fields and listview.

        //   type= Typeface.createFromAsset(activity.getAssets(),"fonts/book.TTF");
        myFragmentView = inflater.inflate(R.layout.fragment_search, container, false);

        search=(SearchView) myFragmentView.findViewById(R.id.searchView1);
        search.setQueryHint("Start typing to search...");

        searchResults = (ListView) myFragmentView.findViewById(R.id.listview_search);




        //this part of the code is to handle the situation when user enters any search criteria, how should the
        //application behave?

        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

                //Toast.makeText(activity, String.valueOf(hasFocus),Toast.LENGTH_SHORT).show();
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText.length() > 3)
                {

                    searchResults.setVisibility(myFragmentView.VISIBLE);
                    myAsyncTask m= (myAsyncTask) new myAsyncTask().execute(newText);
                    //GetExample g= new GetExample(newText);

                }
                else
                {

                    searchResults.setVisibility(myFragmentView.INVISIBLE);
                }



                return false;
            }

        });
        return myFragmentView;
    }

    //this filters products from productResults and copies to filteredProductResults based on search text

    public void filterProductArray(String newText)
    {

        String pName;

        filteredProductResults.clear();
        for (int i = 0; i < productResults.size(); i++)
        {
            pName = productResults.get(i).getProductName().toLowerCase();
            if ( pName.contains(newText.toLowerCase()))
//                          ||
//                    productResults.get(i).getProductBarcode().contains(newText))
            {
                filteredProductResults.add(productResults.get(i));

            }
        }

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    //in this myAsyncTask, we are fetching data from server for the search string entered by user.
    class myAsyncTask extends AsyncTask<String, Void, String>
    {
        // JSONParser jParser;
        JSONArray productList;
        String url=new String();
        String textSearch;
        ProgressDialog pd;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            productList=new JSONArray();
            //   jParser = new JSONParser();
            pd= new ProgressDialog(getActivity());
            pd.setCancelable(false);
            pd.setMessage("Searching...");
            pd.getWindow().setGravity(Gravity.CENTER);
            pd.show();
        }

        @Override
        protected String doInBackground(String... sText) {

            url="http://www.sweedelight.com/index.php?rt=a/product/filter&api_key=sweedelight800&keyword="+sText[0];
            // url_api= new URL(url);
            String returnResult = getProductList(url);
            this.textSearch = sText[0];
            return returnResult;

        }

        public String getProductList(String url1)
        {
            int responseCode;
            try {
                url_api = new URL(url1);
                HttpURLConnection urlConnection;
                BufferedReader bufferedReader = null;

                urlConnection = (HttpURLConnection) url_api.openConnection();

                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                responseCode = urlConnection.getResponseCode();
                System.out.println("Response Code: " + responseCode + "\n");


                //BufferedReader bufferedReader;
                if(responseCode==200)
                    bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                else
                    bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                System.out.println("Response: " + "\n");
                while ((line = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(line).append("\n");
                    System.out.println(line);
                }
                bufferedReader.close();

                JSONObject result= new JSONObject(stringBuilder.toString());
                JSONArray productrows= result.getJSONArray("rows");

                Product tempProduct;

                for (int i= 0; i<productrows.length();i++)
                {
                    tempProduct= new Product();
                    JSONObject curr_product= productrows.getJSONObject(i);
                    JSONObject phone;
                    //check if this is already there in product list
                    for(int j=0; j<productResults.size(); j++)
                    {
                        if(productResults.get(j).getProduct_id().equals(tempProduct.getProduct_id()))
                        {
                            break;
                        }
                        else
                        {
                            tempProduct.setProduct_id(curr_product.getString("id"));

                            phone= curr_product.getJSONObject("cell");
                            tempProduct.setProductName(phone.getString("name"));
                            tempProduct.setProductMRP(phone.getDouble("price"));
                            productResults.add(tempProduct);
                        }
                    }

                }

//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//            finally
//            {
//                bufferedReader.close();
//                urlConnection.disconnect();
//            }
            }
            catch (Exception e)

            {
                Toast.makeText(getActivity(), "Wrong URL", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            return "OK";
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);

            if(result.equalsIgnoreCase("Exception Caught"))
            {
                Toast.makeText(getActivity(), "Unable to connect to server,please try later", Toast.LENGTH_LONG).show();

                pd.dismiss();
            }
            else
            {


                //calling this method to filter the search results from productResults and move them to
                //filteredProductResults
                filterProductArray(textSearch);
                searchResults.setAdapter(new SearchResultsAdapter(getActivity(),filteredProductResults));
                pd.dismiss();
            }
        }

    }


}

/*
class SearchResultsAdapter extends BaseAdapter
{
    private LayoutInflater layoutInflater;

    private ArrayList<Product> productDetails=new ArrayList<Product>();
    int count;
    Typeface type;
    Context context;

    //constructor method
    public SearchResultsAdapter(Context context, ArrayList<Product> product_details) {

        layoutInflater = LayoutInflater.from(context);

        this.productDetails=product_details;
        this.count= product_details.size();
        this.context = context;
        // type= Typeface.createFromAsset(context.getAssets(),"fonts/book.TTF");

    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int arg0) {
        return productDetails.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        ViewHolder holder;
        Product tempProduct = productDetails.get(position);

        if (convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.listtwo_searchresults, null);
            holder = new ViewHolder();
            holder.product_name = (TextView) convertView.findViewById(R.id.product_name);
            holder.product_mrp = (TextView) convertView.findViewById(R.id.product_mrp);
            holder.product_mrpvalue = (TextView) convertView.findViewById(R.id.product_mrpvalue);
//            holder.product_bb = (TextView) convertView.findViewById(R.id.product_bb);
//            holder.product_bbvalue = (TextView) convertView.findViewById(R.id.product_bbvalue);
            holder.addToCart = (Button) convertView.findViewById(R.id.add_cart);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.product_name.setText(tempProduct.getProductName());
        //  holder.product_name.setTypeface(type);

        //holder.product_mrp.setTypeface(type);

        holder.product_mrpvalue.setText(tempProduct.getProductMRP());
        // holder.product_mrpvalue.setTypeface(type);

        //holder.product_bb.setTypeface(type);

        holder.product_bbvalue.setText(tempProduct.getProductBBPrice());
//        holder.product_bbvalue.setTypeface(type);

        return convertView;
    }

    static class ViewHolder
    {
        TextView product_name;
        TextView product_mrp;
        TextView product_mrpvalue;
        TextView product_bb;
        TextView product_bbvalue;
        TextView product_savings;
        TextView product_savingsvalue;
        TextView qty;
        TextView product_value;
        Button addToCart;

    }

}
*/