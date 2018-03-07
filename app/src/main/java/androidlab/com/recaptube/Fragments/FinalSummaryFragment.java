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

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import androidlab.com.recaptube.R;
import pub.devrel.easypermissions.EasyPermissions;

public class FinalSummaryFragment extends Fragment {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText finalTextPreview;
    Button btnSubmit;
    Session session = null;
    TextView startDate,endDate,time2k1,time2k6;
    ProgressDialog pdialog = null;
    Context context = null;
    String getIntroduction2k1, getBehviorText1, getBehviorText2, getIntervention, getResponse, getPtext1, getPtext2, getPtext3, getSelectedGoal;
    int day, year, month;
    String time,Fname,type,Lname;
    String tarikh=null;
    char first;
    GoogleAccountCredential mCredential;
    Cursor cursor;
    String date2k1,dateAndTime2k1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_final_summary, container, false);

        finalTextPreview = (EditText) view.findViewById(R.id.finalTextPreview);
        sharedPreferences = getActivity().getSharedPreferences("recap", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        startDate=(TextView)view.findViewById(R.id.tvStartDate);
        endDate=(TextView)view.findViewById(R.id.tvEndDate);
        time2k1=(TextView)view.findViewById(R.id.tv2k1Time);
        time2k6=(TextView)view.findViewById(R.id.tv2k6Time);
        day = sharedPreferences.getInt("Day", 0);
        month = sharedPreferences.getInt("Month", 0);
        year = sharedPreferences.getInt("Year", 0);
        Fname=sharedPreferences.getString("fname","");
        Lname=sharedPreferences.getString("lname","");
        tarikh=sharedPreferences.getString("date","");
        date2k1=sharedPreferences.getString("2k1Date","");
        first = Fname.charAt(0);
        dateAndTime2k1=sharedPreferences.getString("2k1DateAndTime","");
        type=sharedPreferences.getString("type","");
        mCredential = new GoogleAccountCredential(getActivity(), "Adamnooriit@gmail.com");

        java.util.Calendar c = java.util.Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        if(dateAndTime2k1.contains(" "))
        {
            dateAndTime2k1=dateAndTime2k1.replace(" "," at ");
            startDate.setText(dateAndTime2k1);
        }
        if (formattedDate.contains(" "))
        {
            formattedDate=formattedDate.replace(" "," at ");
            endDate.setText(formattedDate);
        }

        time2k1.setText(date2k1);
        time2k6.setText(getCurrentTimeStamp());
        timeDifference(time2k1.getText().toString(),time2k6.getText().toString());


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

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
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
                        return new PasswordAuthentication("SBHGApp@gmail.com", "Fiverr2018");
                    }
                });

                pdialog = ProgressDialog.show(getActivity(), "", "Sending Mail...", true);

                RetreiveFeedTask task = new RetreiveFeedTask();
                task.execute();


                Toast.makeText(getActivity(), "Event is added", Toast.LENGTH_SHORT).show();

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.mainContainer, new ClientsFragment()).commit();


            }
        });

        return view;
    }

    public static MimeMessage createEmailWithAttachment(String to,
                                                        String from,
                                                        String subject,
                                                        String bodyText,
                                                        File file)
            throws MessagingException, IOException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);

        email.setFrom(new InternetAddress(from));
        email.addRecipient(javax.mail.Message.RecipientType.TO,
                new InternetAddress(to));
        email.setSubject(subject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(bodyText, "text/plain");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        mimeBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(file);

        mimeBodyPart.setDataHandler(new DataHandler(source));
        mimeBodyPart.setFileName(file.getName());

        multipart.addBodyPart(mimeBodyPart);
        email.setContent(multipart);

        return email;
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

        }
    }
    public static String getCurrentTimeStamp(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            String currentDateTime = dateFormat.format(new Date()); // Find todays date

            return currentDateTime;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
    public  void timeDifference(String date2k1,String date2k6)
    {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date d1 = sdf.parse(date2k1);
            Date d2 = sdf.parse(date2k6);

            long difference = d2.getTime() - d1.getTime();
           int days = (int) (difference / (1000*60*60*24));
           int  hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
           int  min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
            hours = (hours < 0 ? -hours : hours);
            Toast.makeText(getActivity(), String.valueOf(min+" min"), Toast.LENGTH_SHORT).show();
            Log.i("======= Hours"," :: "+hours);

        }catch(ParseException ex){
            // handle parsing exception if date string was different from the pattern applying into the SimpleDateFormat contructor
        }

    }
}
