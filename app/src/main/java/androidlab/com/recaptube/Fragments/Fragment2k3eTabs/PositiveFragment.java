package androidlab.com.recaptube.Fragments.Fragment2k3eTabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidlab.com.recaptube.Fragments.Fragment2k3f;
import androidlab.com.recaptube.R;


public class PositiveFragment extends Fragment implements View.OnClickListener {

    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13
    ,btn14,btn15,btn16,btn17;
    String Text,InterventionSubject,thirdString;
    Bundle bundle;
    String finalText;
    String oldText="The";
    String newText="the";
    Fragment fragment;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_positive, container, false);

        sharedPreferences = getActivity().getSharedPreferences("recap", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        bundle=new Bundle();
        InterventionSubject=sharedPreferences.getString("subject","");
        Text=getArguments().getString("text");
        finalText=Text.replace(oldText,newText);
        btn1=(Button)view.findViewById(R.id.agreed);
        btn2=(Button)view.findViewById(R.id.appearedinterested);
        btn3=(Button)view.findViewById(R.id.engaged2K3e);
        btn4=(Button)view.findViewById(R.id.complied);
        btn5=(Button)view.findViewById(R.id.contributedtotheconversation);
        btn6=(Button)view.findViewById(R.id.displayedproblemsolvingskills);
        btn7=(Button)view.findViewById(R.id.madeconsciencedecisions);
        btn8=(Button)view.findViewById(R.id.madepositivestatements);
        btn9=(Button)view.findViewById(R.id.modifiedtheirbehavior);
        btn10=(Button)view.findViewById(R.id.respondedappropriately);
        btn11=(Button)view.findViewById(R.id.smiled);
        btn12=(Button)view.findViewById(R.id.usedactivelisteningskills);
        btn13=(Button)view.findViewById(R.id.wasassertive);
        btn14=(Button)view.findViewById(R.id.wasempathetic);
        btn15=(Button)view.findViewById(R.id.washopeful);
        btn16=(Button)view.findViewById(R.id.wasinsightful);
        btn17=(Button)view.findViewById(R.id.wasreceptive);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);
        btn13.setOnClickListener(this);
        btn14.setOnClickListener(this);
        btn15.setOnClickListener(this);
        btn16.setOnClickListener(this);
        btn17.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.agreed:
                if (Text.contains(btn1.getText()) || Text.contains(btn2.getText())|| Text.contains(btn3.getText())
                        || Text.contains(btn4.getText()) || Text.contains(btn5.getText()) || Text.contains(btn6.getText())
                        || Text.contains(btn7.getText()) || Text.contains(btn8.getText()) || Text.contains(btn9.getText())
                        || Text.contains(btn10.getText()) || Text.contains(btn11.getText()) || Text.contains(btn12.getText())
                        || Text.contains(btn13.getText()) || Text.contains(btn14.getText()) || Text.contains(btn15.getText())
                        || Text.contains(btn16.getText()) || Text.contains(btn17.getText()))
                {
                    bundle.putString("text",Text);
                    fragment=new Fragment2k3f();
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }
                else
                {
                    thirdString="The "+InterventionSubject+ " " +btn1.getText()+ " when " +finalText;
                    fragment=new Fragment2k3f();
                    bundle.putString("text",thirdString);
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }

            break;
            case R.id.appearedinterested:
                if (Text.contains(btn1.getText()) || Text.contains(btn2.getText())|| Text.contains(btn3.getText())
                        || Text.contains(btn4.getText()) || Text.contains(btn5.getText()) || Text.contains(btn6.getText())
                        || Text.contains(btn7.getText()) || Text.contains(btn8.getText()) || Text.contains(btn9.getText())
                        || Text.contains(btn10.getText()) || Text.contains(btn11.getText()) || Text.contains(btn12.getText())
                        || Text.contains(btn13.getText()) || Text.contains(btn14.getText()) || Text.contains(btn15.getText())
                        || Text.contains(btn16.getText()) || Text.contains(btn17.getText()))
                {
                    bundle.putString("text",Text);
                    fragment=new Fragment2k3f();
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }
                else
                {
                    Text="The "+InterventionSubject+ " " +btn2.getText()+ " when " +finalText;
                    fragment=new Fragment2k3f();
                    bundle.putString("text",Text);
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }

                break;
            case R.id.engaged2K3e:
                if (Text.contains(btn1.getText()) || Text.contains(btn2.getText())|| Text.contains(btn3.getText())
                        || Text.contains(btn4.getText()) || Text.contains(btn5.getText()) || Text.contains(btn6.getText())
                        || Text.contains(btn7.getText()) || Text.contains(btn8.getText()) || Text.contains(btn9.getText())
                        || Text.contains(btn10.getText()) || Text.contains(btn11.getText()) || Text.contains(btn12.getText())
                        || Text.contains(btn13.getText()) || Text.contains(btn14.getText()) || Text.contains(btn15.getText())
                        || Text.contains(btn16.getText()) || Text.contains(btn17.getText()))
                {
                    bundle.putString("text",Text);
                    fragment=new Fragment2k3f();
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }
                else
                {
                    Text="The "+InterventionSubject+ " " +btn3.getText()+ " when " +finalText;
                    fragment=new Fragment2k3f();
                    bundle.putString("text",Text);
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }

                break;
            case R.id.complied:
                if (Text.contains(btn1.getText()) || Text.contains(btn2.getText())|| Text.contains(btn3.getText())
                        || Text.contains(btn4.getText()) || Text.contains(btn5.getText()) || Text.contains(btn6.getText())
                        || Text.contains(btn7.getText()) || Text.contains(btn8.getText()) || Text.contains(btn9.getText())
                        || Text.contains(btn10.getText()) || Text.contains(btn11.getText()) || Text.contains(btn12.getText())
                        || Text.contains(btn13.getText()) || Text.contains(btn14.getText()) || Text.contains(btn15.getText())
                        || Text.contains(btn16.getText()) || Text.contains(btn17.getText()))
                {
                    bundle.putString("text",Text);
                    fragment=new Fragment2k3f();
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }
                else
                {
                    Text="The "+InterventionSubject+ " " +btn4.getText()+ " when " +finalText;
                    fragment=new Fragment2k3f();
                    bundle.putString("text",Text);
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }

                break;
            case R.id.contributedtotheconversation:
                if (Text.contains(btn1.getText()) || Text.contains(btn2.getText())|| Text.contains(btn3.getText())
                        || Text.contains(btn4.getText()) || Text.contains(btn5.getText()) || Text.contains(btn6.getText())
                        || Text.contains(btn7.getText()) || Text.contains(btn8.getText()) || Text.contains(btn9.getText())
                        || Text.contains(btn10.getText()) || Text.contains(btn11.getText()) || Text.contains(btn12.getText())
                        || Text.contains(btn13.getText()) || Text.contains(btn14.getText()) || Text.contains(btn15.getText())
                        || Text.contains(btn16.getText()) || Text.contains(btn17.getText()))
                {
                    bundle.putString("text",Text);
                    fragment=new Fragment2k3f();
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }
                else {

                    Text="The "+InterventionSubject+ " " +btn5.getText()+ " when " +finalText;
                    fragment=new Fragment2k3f();
                    bundle.putString("text",Text);
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }

                break;
            case R.id.displayedproblemsolvingskills:
                if (Text.contains(btn1.getText()) || Text.contains(btn2.getText())|| Text.contains(btn3.getText())
                        || Text.contains(btn4.getText()) || Text.contains(btn5.getText()) || Text.contains(btn6.getText())
                        || Text.contains(btn7.getText()) || Text.contains(btn8.getText()) || Text.contains(btn9.getText())
                        || Text.contains(btn10.getText()) || Text.contains(btn11.getText()) || Text.contains(btn12.getText())
                        || Text.contains(btn13.getText()) || Text.contains(btn14.getText()) || Text.contains(btn15.getText())
                        || Text.contains(btn16.getText()) || Text.contains(btn17.getText()))
                {
                    bundle.putString("text",Text);
                    fragment=new Fragment2k3f();
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }
                else
                {
                    Text="The "+InterventionSubject+ " " +btn6.getText()+ " when " +finalText;
                    fragment=new Fragment2k3f();
                    bundle.putString("text",Text);
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }

                break;
            case R.id.madeconsciencedecisions:
                if (Text.contains(btn1.getText()) || Text.contains(btn2.getText())|| Text.contains(btn3.getText())
                        || Text.contains(btn4.getText()) || Text.contains(btn5.getText()) || Text.contains(btn6.getText())
                        || Text.contains(btn7.getText()) || Text.contains(btn8.getText()) || Text.contains(btn9.getText())
                        || Text.contains(btn10.getText()) || Text.contains(btn11.getText()) || Text.contains(btn12.getText())
                        || Text.contains(btn13.getText()) || Text.contains(btn14.getText()) || Text.contains(btn15.getText())
                        || Text.contains(btn16.getText()) || Text.contains(btn17.getText()))
                {
                    bundle.putString("text",Text);
                    fragment=new Fragment2k3f();
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }
                else
                {
                    Text="The "+InterventionSubject+ " " +btn7.getText()+ " when " +finalText;
                    fragment=new Fragment2k3f();
                    bundle.putString("text",Text);
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }

                break;
            case R.id.madepositivestatements:
                if (Text.contains(btn1.getText()) || Text.contains(btn2.getText())|| Text.contains(btn3.getText())
                        || Text.contains(btn4.getText()) || Text.contains(btn5.getText()) || Text.contains(btn6.getText())
                        || Text.contains(btn7.getText()) || Text.contains(btn8.getText()) || Text.contains(btn9.getText())
                        || Text.contains(btn10.getText()) || Text.contains(btn11.getText()) || Text.contains(btn12.getText())
                        || Text.contains(btn13.getText()) || Text.contains(btn14.getText()) || Text.contains(btn15.getText())
                        || Text.contains(btn16.getText()) || Text.contains(btn17.getText()))
                {
                    bundle.putString("text",Text);
                    fragment=new Fragment2k3f();
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }
                else
                {
                    Text="The "+InterventionSubject+ " " +btn8.getText()+ " when " +finalText;
                    fragment=new Fragment2k3f();
                    bundle.putString("text",Text);
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }

                break;
            case R.id.modifiedtheirbehavior:
                if (Text.contains(btn1.getText()) || Text.contains(btn2.getText())|| Text.contains(btn3.getText())
                        || Text.contains(btn4.getText()) || Text.contains(btn5.getText()) || Text.contains(btn6.getText())
                        || Text.contains(btn7.getText()) || Text.contains(btn8.getText()) || Text.contains(btn9.getText())
                        || Text.contains(btn10.getText()) || Text.contains(btn11.getText()) || Text.contains(btn12.getText())
                        || Text.contains(btn13.getText()) || Text.contains(btn14.getText()) || Text.contains(btn15.getText())
                        || Text.contains(btn16.getText()) || Text.contains(btn17.getText()))
                {
                    bundle.putString("text",Text);
                    fragment=new Fragment2k3f();
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }
                else
                {
                    Text="The "+InterventionSubject+ " " +btn9.getText()+ " when " +finalText;
                    fragment=new Fragment2k3f();
                    bundle.putString("text",Text);
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }

                break;
            case R.id.respondedappropriately:
                if (Text.contains(btn1.getText()) || Text.contains(btn2.getText())|| Text.contains(btn3.getText())
                        || Text.contains(btn4.getText()) || Text.contains(btn5.getText()) || Text.contains(btn6.getText())
                        || Text.contains(btn7.getText()) || Text.contains(btn8.getText()) || Text.contains(btn9.getText())
                        || Text.contains(btn10.getText()) || Text.contains(btn11.getText()) || Text.contains(btn12.getText())
                        || Text.contains(btn13.getText()) || Text.contains(btn14.getText()) || Text.contains(btn15.getText())
                        || Text.contains(btn16.getText()) || Text.contains(btn17.getText()))
                {
                    bundle.putString("text",Text);
                    fragment=new Fragment2k3f();
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }
                else
                {
                    Text="The "+InterventionSubject+ " " +btn10.getText()+ " when " +finalText;
                    fragment=new Fragment2k3f();
                    bundle.putString("text",Text);
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }

                break;
            case R.id.smiled:
                if (Text.contains(btn1.getText()) || Text.contains(btn2.getText())|| Text.contains(btn3.getText())
                        || Text.contains(btn4.getText()) || Text.contains(btn5.getText()) || Text.contains(btn6.getText())
                        || Text.contains(btn7.getText()) || Text.contains(btn8.getText()) || Text.contains(btn9.getText())
                        || Text.contains(btn10.getText()) || Text.contains(btn11.getText()) || Text.contains(btn12.getText())
                        || Text.contains(btn13.getText()) || Text.contains(btn14.getText()) || Text.contains(btn15.getText())
                        || Text.contains(btn16.getText()) || Text.contains(btn17.getText()))
                {
                    bundle.putString("text",Text);
                    fragment=new Fragment2k3f();
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }
                else
                {
                    Text="The "+InterventionSubject+ " " +btn11.getText()+ " when " +finalText;
                    fragment=new Fragment2k3f();
                    bundle.putString("text",Text);
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }

                break;
            case R.id.usedactivelisteningskills:
                if (Text.contains(btn1.getText()) || Text.contains(btn2.getText())|| Text.contains(btn3.getText())
                        || Text.contains(btn4.getText()) || Text.contains(btn5.getText()) || Text.contains(btn6.getText())
                        || Text.contains(btn7.getText()) || Text.contains(btn8.getText()) || Text.contains(btn9.getText())
                        || Text.contains(btn10.getText()) || Text.contains(btn11.getText()) || Text.contains(btn12.getText())
                        || Text.contains(btn13.getText()) || Text.contains(btn14.getText()) || Text.contains(btn15.getText())
                        || Text.contains(btn16.getText()) || Text.contains(btn17.getText()))
                {
                    bundle.putString("text",Text);
                    fragment=new Fragment2k3f();
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }
                else
                {
                    Text="The "+InterventionSubject+ " " +btn12.getText()+ " when " +finalText;
                    fragment=new Fragment2k3f();
                    bundle.putString("text",Text);
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }

                break;
            case R.id.wasassertive:
                if (Text.contains(btn1.getText()) || Text.contains(btn2.getText())|| Text.contains(btn3.getText())
                        || Text.contains(btn4.getText()) || Text.contains(btn5.getText()) || Text.contains(btn6.getText())
                        || Text.contains(btn7.getText()) || Text.contains(btn8.getText()) || Text.contains(btn9.getText())
                        || Text.contains(btn10.getText()) || Text.contains(btn11.getText()) || Text.contains(btn12.getText())
                        || Text.contains(btn13.getText()) || Text.contains(btn14.getText()) || Text.contains(btn15.getText())
                        || Text.contains(btn16.getText()) || Text.contains(btn17.getText()))
                {
                    bundle.putString("text",Text);
                    fragment=new Fragment2k3f();
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }
                else
                {
                    Text="The "+InterventionSubject+ " " +btn13.getText()+ " when " +finalText;
                    fragment=new Fragment2k3f();
                    bundle.putString("text",Text);
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }

                break;
            case R.id.wasempathetic:
                if (Text.contains(btn1.getText()) || Text.contains(btn2.getText())|| Text.contains(btn3.getText())
                        || Text.contains(btn4.getText()) || Text.contains(btn5.getText()) || Text.contains(btn6.getText())
                        || Text.contains(btn7.getText()) || Text.contains(btn8.getText()) || Text.contains(btn9.getText())
                        || Text.contains(btn10.getText()) || Text.contains(btn11.getText()) || Text.contains(btn12.getText())
                        || Text.contains(btn13.getText()) || Text.contains(btn14.getText()) || Text.contains(btn15.getText())
                        || Text.contains(btn16.getText()) || Text.contains(btn17.getText()))
                {
                    bundle.putString("text",Text);
                    fragment=new Fragment2k3f();
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }
                else
                {
                    Text="The "+InterventionSubject+ " " +btn14.getText()+ " when " +finalText;
                    fragment=new Fragment2k3f();
                    bundle.putString("text",Text);
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }

                break;
            case R.id.washopeful:
                if (Text.contains(btn1.getText()) || Text.contains(btn2.getText())|| Text.contains(btn3.getText())
                        || Text.contains(btn4.getText()) || Text.contains(btn5.getText()) || Text.contains(btn6.getText())
                        || Text.contains(btn7.getText()) || Text.contains(btn8.getText()) || Text.contains(btn9.getText())
                        || Text.contains(btn10.getText()) || Text.contains(btn11.getText()) || Text.contains(btn12.getText())
                        || Text.contains(btn13.getText()) || Text.contains(btn14.getText()) || Text.contains(btn15.getText())
                        || Text.contains(btn16.getText()) || Text.contains(btn17.getText()))
                {
                    bundle.putString("text",Text);
                    fragment=new Fragment2k3f();
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }
                else
                {
                    Text="The "+InterventionSubject+ " " +btn15.getText()+ " when " +finalText;
                    fragment=new Fragment2k3f();
                    bundle.putString("text",Text);
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }

                break;
            case R.id.wasinsightful:
                if (Text.contains(btn1.getText()) || Text.contains(btn2.getText())|| Text.contains(btn3.getText())
                        || Text.contains(btn4.getText()) || Text.contains(btn5.getText()) || Text.contains(btn6.getText())
                        || Text.contains(btn7.getText()) || Text.contains(btn8.getText()) || Text.contains(btn9.getText())
                        || Text.contains(btn10.getText()) || Text.contains(btn11.getText()) || Text.contains(btn12.getText())
                        || Text.contains(btn13.getText()) || Text.contains(btn14.getText()) || Text.contains(btn15.getText())
                        || Text.contains(btn16.getText()) || Text.contains(btn17.getText()))
                {
                    bundle.putString("text",Text);
                    fragment=new Fragment2k3f();
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }
                else
                {
                    Text="The "+InterventionSubject+ " " +btn16.getText()+ " when " +finalText;
                    fragment=new Fragment2k3f();
                    bundle.putString("text",Text);
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }

                break;
            case R.id.wasreceptive:
                if (Text.contains(btn1.getText()) || Text.contains(btn2.getText())|| Text.contains(btn3.getText())
                        || Text.contains(btn4.getText()) || Text.contains(btn5.getText()) || Text.contains(btn6.getText())
                        || Text.contains(btn7.getText()) || Text.contains(btn8.getText()) || Text.contains(btn9.getText())
                        || Text.contains(btn10.getText()) || Text.contains(btn11.getText()) || Text.contains(btn12.getText())
                        || Text.contains(btn13.getText()) || Text.contains(btn14.getText()) || Text.contains(btn15.getText())
                        || Text.contains(btn16.getText()) || Text.contains(btn17.getText()))
                {
                    bundle.putString("text",Text);
                    fragment=new Fragment2k3f();
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }
                else

                {
                    Text="The "+InterventionSubject+ " " +btn17.getText()+ " when " +finalText;
                    fragment=new Fragment2k3f();
                    bundle.putString("text",Text);
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack("abc").commit();
                }

                break;

            default:
                break;
        }
    }
}
