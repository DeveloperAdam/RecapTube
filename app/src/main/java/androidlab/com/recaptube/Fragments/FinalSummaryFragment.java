package androidlab.com.recaptube.Fragments;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import androidlab.com.recaptube.R;

public class FinalSummaryFragment extends Fragment {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor ;
    EditText finalTextPreview;
    Button btnSubmit;
    int[] calendarId;
    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    String rec, subject, textMessage;
    String getIntroduction2k1,getBehviorText1,getBehviorText2,getIntervention,getResponse,getPtext1,getPtext2,getPtext3,getSelectedGoal;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_final_summary, container, false);

        finalTextPreview=(EditText)view.findViewById(R.id.finalTextPreview);
        sharedPreferences = getActivity().getSharedPreferences("recap", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        btnSubmit=(Button)view.findViewById(R.id.btnSubmit);
        getSelectedGoal=sharedPreferences.getString("selectedgoal","");
        getIntroduction2k1=sharedPreferences.getString("back","");
        getBehviorText1=sharedPreferences.getString("bText1","");
        getBehviorText2=sharedPreferences.getString("bText2","");
        getIntervention=sharedPreferences.getString("intervention","");
        getResponse=sharedPreferences.getString("response","");
        getPtext1=sharedPreferences.getString("pText1","");
        getPtext2=sharedPreferences.getString("pText2","");
        getPtext3=sharedPreferences.getString("pText3","");
        finalTextPreview.setText("********** INTRODUCTION **********\n"+getIntroduction2k1+
                        "\n\n*************** GOAL **************\n"+getSelectedGoal+
        "\n\n********** BEHAVIOR **********\n"+getBehviorText1+getBehviorText2+
                        "\n\n********** INTERVENTION **********\n"+
        getIntervention+"\n\n********** RESPONSE **********\n"+getResponse
                        +"\n\n************** PLAN **************\n"+getPtext1+" "+getPtext2+ " " +getPtext3);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");

                session = Session.getDefaultInstance(props, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("adamnooriit@gmail.com", "Adam_Noor321");
                    }
                });

                pdialog = ProgressDialog.show(getActivity(), "", "Sending Mail...", true);

                RetreiveFeedTask task = new RetreiveFeedTask();
                task.execute();

//
//                Cursor cursor=getActivity().getContentResolver().query(Uri.parse("content://com.android.calendar/calendars"), new String[]{"calendar_id", CalendarContract.Calendars.CALENDAR_DISPLAY_NAME}, null, null, null);
//                cursor.moveToFirst();
//// Get calendars name
//                String calendarNames[] = new String[cursor.getCount()];
//// Get calendars id
//                calendarId = new int[cursor.getCount()];
//                for (int i = 0; i < calendarNames.length; i++)
//                {
//                    calendarId[i] = cursor.getInt(0);
//                    calendarNames[i] = cursor.getString(1);
//                    cursor.moveToNext();
//                }
//
//                ContentValues contentEvent = new ContentValues();
//// Particular Calendar in which we need to add Event
//                contentEvent.put("calendar_id", calendarId[0]);
//// Title/Caption of the Event
//                contentEvent.put("title", "Wedding");
//// Description of the Event
//                contentEvent.put("description", "Wedding Party");
//// Venue/Location of the Event
//                contentEvent.put("eventLocation", "New York");
//// Start Date of the Event with Time
//                contentEvent.put("dtstart", "3/3/2018");
//// End Date of the Event with Time
//                contentEvent.put("dtend", "10/3/2018");
//// All Day Event
//                contentEvent.put("allDay", 1);
//// Set alarm for this Event
//                contentEvent.put("hasAlarm",1);
//                Uri eventsUri = Uri.parse("content://com.android.calendar/events");
//// event is added successfully
//                getActivity().getContentResolver().insert(eventsUri, contentEvent);
//                cursor.close();
            }
        });

        return view;
    }
    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("adamnooriit@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("adamnoort1@gmail.com"));
                message.setSubject("testing");
                message.setContent(finalTextPreview.getText().toString(), "text/html; charset=utf-8");
                Transport.send(message);
            } catch(MessagingException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pdialog.dismiss();
            Toast.makeText(getActivity(), "Message sent", Toast.LENGTH_LONG).show();
        }
    }
}
