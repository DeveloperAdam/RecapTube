package androidlab.com.recaptube.Fragments.DiscussionTabs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidlab.com.recaptube.Fragments.Fragment2k3eTabs.Fragment2k3e;
import androidlab.com.recaptube.R;


public class DiscussionUpdateFragment extends Fragment implements View.OnClickListener{

    Button btn1,btn2,btn3,btn4,btn5;
    String Text,thirdString;
    Fragment fragment;
    Bundle bundle;
    String oldText="what?";
    String newText;
    String temp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_discussion_update, container, false);
        bundle=new Bundle();
        Text=getArguments().getString("text");
        btn1=(Button)view.findViewById(R.id.newinformation);
        btn2=(Button)view.findViewById(R.id.updates);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.newinformation:
                final EditText taskEditText = new EditText(getActivity());
                taskEditText.setSingleLine();
                taskEditText.setText(btn1.getText()+" on ");
                taskEditText.setSelection(taskEditText.length());
                AlertDialog alertDialogdialog = new AlertDialog.Builder(getActivity())
                        .setTitle(Text)
                        .setView(taskEditText)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                newText=Text.replace(oldText,task+".");
                                bundle.putString("text",newText);
                                Fragment fragment=new Fragment2k3e();
                                fragment.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                alertDialogdialog.show();
//                newText=btn1.getText().toString()+".";
//                temp= Text.replace(oldText,newText);
//                bundle.putString("text",temp);
//                fragment=new Fragment2k3e();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                break;
            case R.id.updates:
                final EditText taskEditText2 = new EditText(getActivity());
                taskEditText2.setSingleLine();
                taskEditText2.setText(btn2.getText()+" on ");
                taskEditText2.setSelection(taskEditText2.length());
                AlertDialog alertDialogdialog2 = new AlertDialog.Builder(getActivity())
                        .setTitle(Text)
                        .setView(taskEditText2)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText2.getText());
                                newText=Text.replace(oldText,task+".");
                                bundle.putString("text",newText);
                                Fragment fragment=new Fragment2k3e();
                                fragment.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                alertDialogdialog2.show();
                break;
            default:
                break;
        }
    }

}
