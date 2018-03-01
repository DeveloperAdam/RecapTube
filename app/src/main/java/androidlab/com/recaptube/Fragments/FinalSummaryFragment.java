package androidlab.com.recaptube.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidlab.com.recaptube.R;

public class FinalSummaryFragment extends Fragment {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor ;
    EditText finalTextPreview;
    String getIntroduction2k1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_final_summary, container, false);

        finalTextPreview=(EditText)view.findViewById(R.id.finalTextPreview);
        sharedPreferences = getActivity().getSharedPreferences("recap", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        getIntroduction2k1=sharedPreferences.getString("back","");
        finalTextPreview.setText(getIntroduction2k1);


        return view;
    }

}
