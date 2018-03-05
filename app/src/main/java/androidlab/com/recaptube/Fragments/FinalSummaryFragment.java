package androidlab.com.recaptube.Fragments;

import android.Manifest;
import android.accounts.Account;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import androidlab.com.recaptube.R;
import pub.devrel.easypermissions.EasyPermissions;

public class FinalSummaryFragment extends Fragment {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText finalTextPreview;
    Button btnSubmit;
    int[] calendarId;
    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    String getIntroduction2k1, getBehviorText1, getBehviorText2, getIntervention, getResponse, getPtext1, getPtext2, getPtext3, getSelectedGoal;
    int day, year, month;
    String time;
    Calendar service;
    GoogleSignInAccount googleSignInAccount;
    GoogleAccountCredential mCredential;
    Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_final_summary, container, false);

        finalTextPreview = (EditText) view.findViewById(R.id.finalTextPreview);
        sharedPreferences = getActivity().getSharedPreferences("recap", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        day = sharedPreferences.getInt("Day", 0);
        month = sharedPreferences.getInt("Month", 0);
        year = sharedPreferences.getInt("Year", 0);

        mCredential = new GoogleAccountCredential(getActivity(), "Adamnooriit@gmail.com");

        time = sharedPreferences.getString("time", "");
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        getSelectedGoal = sharedPreferences.getString("selectedgoal", "");
        getIntroduction2k1 = sharedPreferences.getString("back", "");
        getBehviorText1 = sharedPreferences.getString("bText1", "");
        getBehviorText2 = sharedPreferences.getString("bText2", "");
        getIntervention = sharedPreferences.getString("intervention", "");
        getResponse = sharedPreferences.getString("response", "");
        getPtext1 = sharedPreferences.getString("pText1", "");
        getPtext2 = sharedPreferences.getString("pText2", "");
        getPtext3 = sharedPreferences.getString("pText3", "");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            finalTextPreview.setText("********** INTRODUCTION **********\n"
                    + getIntroduction2k1 +
                    "\n\n********** GOAL **********\n"
                    + getSelectedGoal +
                    "\n\n********** BEHAVIOR **********\n"
                    + getBehviorText1 + getBehviorText2 +
                    "\n\n********** INTERVENTION **********\n" +
                    getIntervention +
                    "\n\n********** RESPONSE **********\n"
                    + getResponse
                    + "\n\n********** PLAN **********\n"
                    + getPtext1 + " " + getPtext2 + " " + getPtext3);
        }


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {

//                Properties props = new Properties();
//                props.put("mail.smtp.host", "smtp.gmail.com");
//                props.put("mail.smtp.socketFactory.port", "465");
//                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//                props.put("mail.smtp.auth", "true");
//                props.put("mail.smtp.port", "465");
//
//                session = Session.getDefaultInstance(props, new Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication("SBHGApp@gmail.com", "Fiverr2018");
//                    }
//                });
//
//                pdialog = ProgressDialog.show(getActivity(), "", "Sending Mail...", true);
//
//                RetreiveFeedTask task = new RetreiveFeedTask();
//                task.execute();


//                java.util.Calendar beginTime = java.util.Calendar.getInstance();
//                beginTime.set(year, month, day, 7, 30);
//                java.util.Calendar endTime =  java.util.Calendar.getInstance();
//                endTime.set(year, month, day, 8, 30);
//                Intent intent = new Intent(Intent.ACTION_INSERT)
//                        .setData(CalendarContract.Events.CONTENT_URI)
//                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
//                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
//                        .putExtra(CalendarContract.Events.TITLE, "Test")
//                        .putExtra(CalendarContract.Events.DESCRIPTION, "I am creating this event for test.")
//                        .putExtra(CalendarContract.Events.EVENT_LOCATION, "Los Angeles")
//                        .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
//                        .putExtra(Intent.EXTRA_EMAIL, "ericramos1990@gmail.com,SBHGApp@gmail.com");

                //    startActivity(intent);
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    cursor = getActivity().getContentResolver().query(CalendarContract.Events.CONTENT_URI, null, null, null, null);
                    while (cursor.moveToNext())
                    {
                        if (cursor!=null)
                        {
                            int id_1=cursor.getColumnIndex(CalendarContract.Events._ID);
                            int id_2=cursor.getColumnIndex(CalendarContract.Events.TITLE);
                            int id_3=cursor.getColumnIndex(CalendarContract.Events.DESCRIPTION);
                            int id_4=cursor.getColumnIndex(CalendarContract.Events.EVENT_LOCATION);

                            String idValue=cursor.getString(id_1);
                            String titleValue=cursor.getString(id_2);
                            String descriptionValue=cursor.getString(id_3);
                            String eventValue =cursor.getString(id_4);

                            Toast.makeText(getActivity(), eventValue, Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(getActivity(), "There is no event", Toast.LENGTH_SHORT).show();
                        }
                    }
                    return;
                }

                ContentResolver cr=getActivity().getContentResolver();
                ContentValues cv=new ContentValues();
                cv.put(CalendarContract.Events.TITLE,"This is event title");
                cv.put(CalendarContract.Events.DESCRIPTION,"This is description");
                cv.put(CalendarContract.Events.EVENT_LOCATION,"This is LosAngeles loc");
                cv.put(CalendarContract.Events.DTSTART,"2018-03-05 10:00 AM");
                cv.put(CalendarContract.Events.DTEND, "2018-03-05 11:00 AM");
                cv.put(CalendarContract.Events.CALENDAR_ID, 1);
                cv.put(CalendarContract.Events.EVENT_TIMEZONE, java.util.Calendar.getInstance().getTimeZone().getID());

                Uri uri=cr.insert(CalendarContract.Events.CONTENT_URI,cv);


                Toast.makeText(getActivity(), "Event is added", Toast.LENGTH_SHORT).show();


            }
        });

        return view;
    }



    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected String doInBackground(String... params) {

            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("SBHGApp@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ericramos1990@gmail.com"));
                message.setSubject("This will be uploaded directly to EMR");
                String mailContent="********** INTRODUCTION **********\n"
                        + getIntroduction2k1 +
                        "\n\n********** GOAL **********\n"
                        + getSelectedGoal +
                        "\n\n********** BEHAVIOR **********\n"
                        + getBehviorText1 + getBehviorText2 +
                        "\n\n********** INTERVENTION **********\n" +
                        getIntervention +
                        "\n\n********** RESPONSE **********\n"
                        + getResponse
                        + "\n\n********** PLAN **********\n"
                        + getPtext1 + " " + getPtext2 + " " + getPtext3;
                message.setContent(mailContent.replace("\n","<br/>"),"text/html; charset=utf-8");
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
//            Toast.makeText(getActivity(), "Message sent", Toast.LENGTH_LONG).show();
        }
    }
}
