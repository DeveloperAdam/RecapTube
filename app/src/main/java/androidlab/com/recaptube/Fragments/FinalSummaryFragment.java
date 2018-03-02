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
    String getIntroduction2k1,getBehviorText1,getBehviorText2,getIntervention,getResponse,getPtext1,getPtext2,getPtext3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_final_summary, container, false);

        finalTextPreview=(EditText)view.findViewById(R.id.finalTextPreview);
        sharedPreferences = getActivity().getSharedPreferences("recap", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        getIntroduction2k1=sharedPreferences.getString("back","");
        getBehviorText1=sharedPreferences.getString("bText1","");
        getBehviorText2=sharedPreferences.getString("bText2","");
        getIntervention=sharedPreferences.getString("intervention","");
        getResponse=sharedPreferences.getString("response","");
        getPtext1=sharedPreferences.getString("pText1","");
        getPtext2=sharedPreferences.getString("pText2","");
        getPtext3=sharedPreferences.getString("pText3","");
        finalTextPreview.setText("********** INTRODUCTION **********\n"+getIntroduction2k1+
                        "\n\n*************** GOAL **************\n"+
        "We will change this later\n\n********** BEHAVIOR **********\n"+getBehviorText1+getBehviorText2+
                        "\n\n********** INTERVENTION **********\n"+
        getIntervention+"\n\n********** RESPONSE **********\n"+getResponse
                        +"\n\n************** PLAN **************\n"+getPtext1+" "+getPtext2+ " " +getPtext3);


        return view;
    }

}
