package androidlab.com.recaptube.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import androidlab.com.recaptube.Adapter.ClientAdapter;
import androidlab.com.recaptube.Controllers.ClientsDetailsModel;
import androidlab.com.recaptube.R;
import androidlab.com.recaptube.Utils.Alert_Utils;


public class ClientsFragment extends Fragment {

    GridView gridView;
    ArrayList<ClientsDetailsModel> clientsDetailsModels;
    ClientAdapter clientAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    android.support.v7.app.AlertDialog alertDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clients, container, false);

        //customActionBar();
        deleteCache(getActivity());


       // clearCache();
        //  getActivity().setTitle("My Clients");
        gridView = (GridView) view.findViewById(R.id.gridViewAllContestent);
        clientsDetailsModels = new ArrayList<>();

        if (alertDialog == null) {
            alertDialog = Alert_Utils.createProgressDialog(getActivity());
            alertDialog.show();
        }
        clientsDetailsModels.clear();

        clientAdapter = new ClientAdapter(getActivity(), clientsDetailsModels);
        gridView.setAdapter(clientAdapter);
        apicall();
        return view;
    }
    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
    public boolean clearCache() {
        try {
            File[] files = getActivity().getBaseContext().getCacheDir().listFiles();

            for (File file : files) {

                // delete returns boolean we can use
                if (!file.delete()) {
                    return false;
                }
            }

            // if for completes all
            return true;

        } catch (Exception e) {}

        // http://trendingfashionable.ipage.com/Recaptube/client_info.php
        //http://squaresdevelopers.com/RecapTube/client_info.php
        return false;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.profile:
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
// ...Irrelevant code for customizing the buttons and title
                LayoutInflater inflater = this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_profile, null);
                dialogBuilder.setView(dialogView);
                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
                break;

            case R.id.todolist:

                break;

            case R.id.scheduled:

                break;

            case R.id.mileage:

                break;

            case R.id.addClient:
                Fragment fragment = new AddClientFragment();
                getFragmentManager().beginTransaction().replace(R.id.mainContainer, fragment).addToBackStack("abc").commit();
                break;

            case R.id.searchClient:

                break;

        }
        return true;

    }
    private void apicall() {
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, "http://trendingfashionable.ipage.com/Recaptube/client_info.php"
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("zmaRes", String.valueOf(response));
                try {
                   JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        ClientsDetailsModel contes = new ClientsDetailsModel();
                        JSONObject temp = jsonArray.getJSONObject(i);
                        contes.setClientId(temp.getString("id"));
                        contes.setClinetFname(temp.getString("first_name"));
                        contes.setClientLname(temp.getString("last_name"));
                        contes.setClientGoals(temp.getString("goals"));
                        clientsDetailsModels.add(contes);
                        if (alertDialog != null)
                            alertDialog.dismiss();
                        Collections.sort(clientsDetailsModels, new Comparator<ClientsDetailsModel>() {
                            @Override
                            public int compare(ClientsDetailsModel item, ClientsDetailsModel t1) {
                                String s1 = item.getClientLname();
                                String s2 = t1.getClientLname();
                                return s1.compareToIgnoreCase(s2);
                            }

                        });
                    }

                    clientAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    Log.d("zmaE", e.getMessage());
                    if (alertDialog != null)
                        alertDialog.dismiss();
                    e.printStackTrace();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //DialogUtils.sweetAlertDialog.dismiss();
                // DialogUtils.showErrorTypeAlertDialog(getActivity(), "Server error");
                if (alertDialog != null)
                    alertDialog.dismiss();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(getActivity(), "No client found", Toast.LENGTH_SHORT).show();
                }
                Log.d("error", String.valueOf(error.getCause()));

            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded;charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }

        };
        RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(stringRequest);
    }


    public void customActionBar() {

        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        ImageView imageView = (ImageView) mCustomView.findViewById(R.id.iv);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                Toast.makeText(getActivity(), "clicked", Toast.LENGTH_SHORT).show();
//
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }
}
