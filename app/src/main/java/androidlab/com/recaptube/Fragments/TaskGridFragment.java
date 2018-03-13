package androidlab.com.recaptube.Fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import androidlab.com.recaptube.R;

public class TaskGridFragment extends Fragment implements View.OnClickListener {

    ImageView ivProgressNote;
    TextView tvProgressNote;
    String clientId,clientName;
    LinearLayout linearLayoutProgressNote;
    Bundle bundle;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_task_grid, container, false);


        ivProgressNote=(ImageView)view.findViewById(R.id.ivProgressNote);
        tvProgressNote=(TextView)view.findViewById(R.id.tvProgressNote);
        linearLayoutProgressNote=(LinearLayout)view.findViewById(R.id.linearLayoutProgressNote);
       sharedPreferences = getActivity().getSharedPreferences("recap", Context.MODE_PRIVATE);
       editor =sharedPreferences.edit();
       clientId=sharedPreferences.getString("clientId","");
        bundle=new Bundle();
       // clientId=getArguments().getString("clientId");
        clientName=getArguments().getString("clientName");
        bundle.putString("clientId",clientId);
        bundle.putString("clientName",clientName);
        customActionBar();
        ivProgressNote.setOnClickListener(this);
        linearLayoutProgressNote.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.ivProgressNote:
                Fragment fragment=new ClientProgressNote();
                bundle.putString("clientName",clientName);
                bundle.putString("clientId",clientId);
                bundle.putString("task","Progress Note");
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                break;
            case R.id.tvProgressNote:
                Fragment fragment2=new ClientProgressNote();
                bundle.putString("task","Progress Note");
                bundle.putString("clientName",clientName);
                bundle.putString("clientId",clientId);
                 fragment2.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment2).addToBackStack("abc").commit();
                break;
            case R.id.linearLayoutProgressNote:
                Fragment fragment3=new ClientProgressNote();
                bundle.putString("task","Progress Note");
                bundle.putString("clientName",clientName);
                bundle.putString("clientId",clientId);
                fragment3.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment3).addToBackStack("abc").commit();
                break;
                default:
                    break;
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.client_progressnote, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.action_settings:

                break;

            case R.id.todolist:

                break;

            case R.id.scheduled:

                break;

            case R.id.mileage:

                break;

            case R.id.addClient:

                break;

            case R.id.searchClient:

                break;

        }
        return true;

    }

    public void customActionBar() {
        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar_taskgrid, null);
        TextView textView=(TextView)mCustomView.findViewById(R.id.textView2);
        String barTitle=clientName+" - "+"Task List";
        textView.setText(barTitle);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

    }
}

