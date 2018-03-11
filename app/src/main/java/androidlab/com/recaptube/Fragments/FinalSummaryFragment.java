package androidlab.com.recaptube.Fragments;

import android.Manifest;
import android.accounts.Account;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
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
import android.os.Environment;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
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
import java.io.FileWriter;
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
    TextView startDate,endDate,tvTotalTime
            ,tvFaceToFace,tvOtherTime,tvEncounterWith,tvClientInvolved,tvSessoinType,
    tvAddress2Title,tvAddress2Street,tvAdress2City,tvAddress2Zip,tvFamilyMember,tvOtherAndFriends,tvIntervention;
    ProgressDialog pdialog = null;
    Context context = null;
    String getIntroduction2k1, getBehviorText1, getBehviorText2, getIntervention, getResponse, getPtext1, getPtext2, getPtext3, getSelectedGoal;
    int day, year, month;
    String time,Fname,type,Lname,sessionType,address2Street,address2City,address2Zip,startAtime,endAtime,clientName,add2Title,sessionCode;
    int familyMembers,other,friends,interventionTime,otherInterventionTime;
    int commute=00;
    int travel=14;
    int documentation=10;
    String tarikh=null;
    int zero=00;
    String currentDateTime;
    int startimeMinutes,endTimeMinutes;
    char first;
    DatePickerDialog datePickerDialog;
    GoogleAccountCredential mCredential;
    String date2k1,dateAndTime2k1,abc,clientPresence;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_final_summary, container, false);

        finalTextPreview = (EditText) view.findViewById(R.id.finalTextPreview);
        sharedPreferences = getActivity().getSharedPreferences("recap", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        tvIntervention=(TextView)view.findViewById(R.id.tvInterventionTime);
        tvAdress2City=(TextView)view.findViewById(R.id.tvAddress2City);
        tvAddress2Zip=(TextView)view.findViewById(R.id.tvAddressZip);
        tvSessoinType=(TextView)view.findViewById(R.id.tvSessionType);
        tvTotalTime=(TextView)view.findViewById(R.id.tvTotalTimeValue);
        tvAddress2Street=(TextView)view.findViewById(R.id.tvAddress2Street);
        tvEncounterWith=(TextView)view.findViewById(R.id.tvEncounterWith);
        tvFaceToFace=(TextView)view.findViewById(R.id.tvFacetoFaceValue);
        tvOtherAndFriends=(TextView)view.findViewById(R.id.tvOtherAndFriends);
        tvFamilyMember=(TextView)view.findViewById(R.id.tvFamilyMembers);
        tvOtherTime=(TextView)view.findViewById(R.id.tvOtherTime);
        tvAddress2Title=(TextView)view.findViewById(R.id.tvAddress2Title);
        startDate=(TextView)view.findViewById(R.id.tvStartDate);
        endDate=(TextView)view.findViewById(R.id.tvEndDate);
        tvClientInvolved=(TextView)view.findViewById(R.id.tvClientInvolved);

        sessionCode=sharedPreferences.getString("code","");
        add2Title=sharedPreferences.getString("add2title","");
        clientName=sharedPreferences.getString("clientname","");
        day = sharedPreferences.getInt("Day", 0);
        other=sharedPreferences.getInt("other",0);
        friends=sharedPreferences.getInt("friends",0);
        month = sharedPreferences.getInt("Month", 0);
        address2Street=sharedPreferences.getString("a2street","");
        address2City=sharedPreferences.getString("a2city","");
        address2Zip=sharedPreferences.getString("a2zip","");
        year = sharedPreferences.getInt("Year", 0);
        Fname=sharedPreferences.getString("fname","");
        Lname=sharedPreferences.getString("lname","");
        sessionType=sharedPreferences.getString("type","");
        tarikh=sharedPreferences.getString("date","");
        date2k1=sharedPreferences.getString("2k1Date","");
        familyMembers=sharedPreferences.getInt("fm",0);
        dateAndTime2k1=sharedPreferences.getString("2k1DateAndTime","");
        type=sharedPreferences.getString("type","");
        clientPresence=sharedPreferences.getString("client","");
        first = Fname.charAt(0);

        mCredential = new GoogleAccountCredential(getActivity(), "Adamnooriit@gmail.com");

        java.util.Calendar c = java.util.Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String formattedDate = df.format(c.getTime());

        tvSessoinType.setText(sessionCode);
        tvAddress2Street.setText(address2Street);
        tvAdress2City.setText(address2City);
        tvAddress2Zip.setText(address2Zip);
        tvFamilyMember.setText(String.valueOf(familyMembers));
        tvOtherAndFriends.setText(String.valueOf(other+friends));
        tvAddress2Title.setText(clientName+": "+add2Title);


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

        timeDifference(date2k1,getCurrentTimeStamp());

        if(clientPresence.equals("yes"))
        {
            tvClientInvolved.setText("Yes");
            tvFaceToFace.setText(String.valueOf(interventionTime));
            otherInterventionTime=zero;
            tvEncounterWith.setText("05 - Client or Client With Others");
            tvOtherTime.setText(String.valueOf(otherInterventionTime+documentation+travel-commute));
            int face=Integer.parseInt(tvFaceToFace.getText().toString());
            int other=Integer.parseInt(tvOtherTime.getText().toString());
            tvTotalTime.setText(String.valueOf(face+other+1));
        }
        else
        {
            tvClientInvolved.setText("No");
            tvFaceToFace.setText(String.valueOf(zero));
            tvIntervention.setText("+"+String.valueOf(interventionTime+1)+" (Intervention)");
            otherInterventionTime=interventionTime;
            if (familyMembers>0)
            {
                tvEncounterWith.setText("01 - Client's Family or Significant Other");
            }
            else
            {
                tvEncounterWith.setText("02 - Other Professional");
            }
            tvOtherTime.setText(String.valueOf(otherInterventionTime+documentation+travel-commute));
            int face=Integer.parseInt(tvFaceToFace.getText().toString());
            int other=Integer.parseInt(tvOtherTime.getText().toString());
            tvTotalTime.setText(String.valueOf(face+other));
        }


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

                final java.util.Calendar c = java.util.Calendar.getInstance();
                int hour = c.get(java.util.Calendar.HOUR_OF_DAY);
                int minute = c.get(java.util.Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                int hour = hourOfDay;
                                int minutes = minute;
                                String timeSet = "";
                                if (hour > 12) {
                                   timeSet = "PM";
                                    hour -= 12;

                                } else if (hour == 0) {
                                    hour += 12;
                                    timeSet = "AM";
                                } else if (hour == 12) {
                                    timeSet = "PM";
                                } else {
                                    timeSet = "AM";
                                }

                                String min = "";
                                if (minutes < 10)
                                    min = "0" + minutes;
                                else
                                    min = String.valueOf(minutes);

                                // Append in a StringBuilder
                                startimeMinutes=hour*60+minutes;
                                difference(startimeMinutes,endTimeMinutes);
                                 startAtime = new StringBuilder().append(hour).append(':')
                                        .append(min).toString();
                                abc=startDate.getText().toString();
                                startDate.setText(abc+ " at " +startAtime);

                              //  timeDifference(startAtime,currentDateTime);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();


                final java.util.Calendar c1 = java.util.Calendar.getInstance();
                int mYear = c1.get(java.util.Calendar.YEAR); // current year
                int mMonth = c1.get(java.util.Calendar.MONTH); // current month
                final int mDay = c1.get(java.util.Calendar.DAY_OF_MONTH);
                // date picker dialog
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                abc =  year+"-" +(monthOfYear + 1) + "-" + dayOfMonth;
                                startDate.setText(abc);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final java.util.Calendar c = java.util.Calendar.getInstance();
                int hour = c.get(java.util.Calendar.HOUR_OF_DAY);
                int minute = c.get(java.util.Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                int hour = hourOfDay;
                                int minutes = minute;
                                String timeSet = "";
                                if (hour > 12) {
                                    hour -= 12;
                                  //  timeSet = "PM";
                                } else if (hour == 0) {
                                    hour += 12;
                                  //  timeSet = "AM";
                                } else if (hour == 12) {
                                   // timeSet = "PM";
                                } else {
                                   // timeSet = "AM";
                                }

                                String min = "";
                                if (minutes < 10)
                                    min = "0" + minutes;
                                else
                                    min = String.valueOf(minutes);
                                endTimeMinutes=hour*60+minutes;
                                difference(startimeMinutes,endTimeMinutes);
                                // Append in a StringBuilder
                                 endAtime = new StringBuilder().append(hour).append(':')
                                        .append(min).toString();
                                abc=endDate.getText().toString();
                                endDate.setText(abc+ " at " +endAtime);

                               // timeDifference(startAtime.toString(),endAtime);


                            }
                        }, hour, minute, false);
                timePickerDialog.show();


                final java.util.Calendar c1 = java.util.Calendar.getInstance();
                int mYear = c1.get(java.util.Calendar.YEAR); // current year
                int mMonth = c1.get(java.util.Calendar.MONTH); // current month
                final int mDay = c1.get(java.util.Calendar.DAY_OF_MONTH);
                // date picker dialog
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                abc = year+"-" +(monthOfYear + 1) + "-" + dayOfMonth;
                                endDate.setText(abc);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

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
//                        //return new PasswordAuthentication("Adamnooriit@gmail.com", "Adam_Noor321");
//                    }
//                });
//
//                pdialog = ProgressDialog.show(getActivity(), "", "Sending Mail...", true);

              //  RetreiveFeedTask task = new RetreiveFeedTask();
               // task.execute();
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
                        + getPtext1 + " " + getPtext2 + " " + getPtext3+"\n\n               Total Time    "
                        +tvTotalTime.getText().toString()+"\n        Face-to-Face Time        "+tvFaceToFace.getText().toString()
                        +"\n               Other Time        "+tvOtherTime.getText().toString()+"\n                                     "
                        +tvIntervention.getText().toString()+"\n                                     +10 (Documentation)\n"+
                        "                                     +14 (Travel)\n                                     -00 (Commute)\n            Mileage Route"+
                        "    https://www.google.com/maps\n             Service Site    12 - Home\n    Service Facility Name    "
                        +tvAddress2Title.getText().toString()+"\n Service Facility Address    "+tvAddress2Street.getText().toString()
                        +"\n    Service Facility City    "+tvAdress2City.getText().toString()+"\n   Service Facility State    CA\n"
                        +"Service Facility Zip Code    "+tvAddress2Zip.getText().toString()+"\n          Client Involved    "
                        +tvClientInvolved.getText().toString()+"\n       Family Collaterals    "+tvFamilyMember.getText().toString()
                        +"\n   Non-Family Collaterals    "+tvOtherAndFriends.getText().toString()+"\n             Session Type    "
                        +tvSessoinType.getText().toString()+"\n            Activity Type    02 - Face to Face with Client - IBHIS\n"
                        +"           Encounter With    05 - Client or Client With Others\n\n  Evidence Based Practice    00 - No Evidence-Based Practice\n"
                        +"             Completed By    Eric Ramos (Child and Family Specialist III Bilingual)\n                Submit To    Boss Lady (CFS Coordinator)"
                        ;
                generateNoteOnSD(mailContent);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.mainContainer, new ClientsFragment()).commit();
               // sendMail();

            }
        });

        return view;
    }
        public void difference(int start,int end)
        {
            int finalTime=end-start+1;

            interventionTime=finalTime;
            tvIntervention.setText("+"+String.valueOf(interventionTime)+" (Intervention)");
            if(clientPresence.equals("yes"))
            {
                tvClientInvolved.setText("Yes");
                tvFaceToFace.setText(String.valueOf(interventionTime));
                otherInterventionTime=zero;
                tvEncounterWith.setText("05 - Client or Client With Others");
                tvOtherTime.setText(String.valueOf(otherInterventionTime+documentation+travel-commute));
                int face=Integer.parseInt(tvFaceToFace.getText().toString());
                int other=Integer.parseInt(tvOtherTime.getText().toString());
                tvTotalTime.setText(String.valueOf(face+other+1));
            }
            else
            {
                tvClientInvolved.setText("No");
                tvFaceToFace.setText(String.valueOf(zero));
                otherInterventionTime=interventionTime;
                if (familyMembers>0)
                {
                    tvEncounterWith.setText("01 - Client's Family or Significant Other");
                }
                else
                {
                    tvEncounterWith.setText("02 - Other Professional");
                }
                tvOtherTime.setText(String.valueOf(otherInterventionTime+documentation+travel-commute));
                int face=Integer.parseInt(tvFaceToFace.getText().toString());
                int other=Integer.parseInt(tvOtherTime.getText().toString());
                tvTotalTime.setText(String.valueOf(face+other+1));
            }
        }
    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected String doInBackground(String... params) {

            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("SBHGApp@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("Adamnooriit@gmail.com"));
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
                        + getPtext1 + " " + getPtext2 + " " + getPtext3+"\n\n               Total Time    "
                        +tvTotalTime.getText().toString()+"\n        Face-to-Face Time        "+tvFaceToFace.getText().toString()
                        +"\n               Other Time        "+tvOtherTime.getText().toString()+"\n                             +"
                        +tvIntervention.getText().toString()+" (Intervention)\n                             +10 (Documentation)\n"+
                        "                             +14 (Travel)\n                             -00 (Commute)\n            Mileage Route"+
                        "    https://www.google.com/maps\n             Service Site    12 - Home\n    Service Facility Name    "
                        +tvAddress2Title.getText().toString()+"\n Service Facility Address    "+tvAddress2Street.getText().toString()
                        +"\n    Service Facility City    "+tvAdress2City.getText().toString()+"\n   Service Facility State    CA\n"
                        +"Service Facility Zip Code    "+tvAddress2Zip.getText().toString()+"\n          Client Involved    "
                        +tvClientInvolved.getText().toString()+"\n       Family Collaterals    "+tvFamilyMember.getText().toString()
                        +"\n   Non-Family Collaterals    "+tvOtherAndFriends.getText().toString()+"\n             Session Type    "
                        +tvSessoinType.getText().toString()+"\n             Activity Type    02 - Face to Face with Client -\nIBHIS\n"
                        +"           Encounter With    05 - Client or Client With Others\n\n  Evidence Based Practice    00 - No Evidence-Based Practice\n"
                        +"             Completed By    Eric Ramos (Child and Family\nSpecialist III Bilingual\n                Submit To    Boss Lady (CFS Coordinator)"
                        ;
                message.setContent(mailContent.replace("\n","<br/>"),"text/html; charset=utf-8");
              //  Transport.send(message);
                    generateNoteOnSD(mailContent);
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

    public void generateNoteOnSD(String sBody) {

        SimpleDateFormat formatter = new SimpleDateFormat("mm");
        Date now = new Date();
       // String fileName = formatter.format(now) + ".txt";
        String fileName = clientName+ "_StartTime.txt";
        //String fileName = "Summary.txt";
        try
        {
            File root = new File(Environment.getExternalStorageDirectory()+File.separator+"Notes");
            //File root = new File(Environment.getExternalStorageDirectory(), "Notes");
            if (!root.exists())
            {
               // root.mkdirs();
            }
            else
            {
                root.delete();
            }
            File gpxfile = new File(root, fileName);


            FileWriter writer = new FileWriter(gpxfile,true);
            writer.append(sBody+"\n\n");
            writer.flush();
            writer.close();

            //sendMail();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMail(){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"SBHGApp@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
        i.putExtra(Intent.EXTRA_TEXT   , "body of email");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {

        }
    }


    public String getCurrentTimeStamp(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            currentDateTime = dateFormat.format(new Date()); // Find todays date

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
            interventionTime=min;
            tvIntervention.setText("+"+String.valueOf(interventionTime)+" (Intervention)");
            if(clientPresence.equals("yes"))
            {
                tvClientInvolved.setText("Yes");
                tvFaceToFace.setText(String.valueOf(interventionTime));
                otherInterventionTime=zero;
                tvEncounterWith.setText("05 - Client or Client With Others");
                tvOtherTime.setText(String.valueOf(otherInterventionTime+documentation+travel-commute));
                int face=Integer.parseInt(tvFaceToFace.getText().toString());
                int other=Integer.parseInt(tvOtherTime.getText().toString());
                tvTotalTime.setText(String.valueOf(face+other+1));
            }
            else
            {
                tvClientInvolved.setText("No");
                tvFaceToFace.setText(String.valueOf(zero));
                otherInterventionTime=interventionTime;
                if (familyMembers>0)
                {
                    tvEncounterWith.setText("01 - Client's Family or Significant Other");
                }
                else
                {
                    tvEncounterWith.setText("02 - Other Professional");
                }
                tvOtherTime.setText(String.valueOf(otherInterventionTime+documentation+travel-commute));
                int face=Integer.parseInt(tvFaceToFace.getText().toString());
                int other=Integer.parseInt(tvOtherTime.getText().toString());
                tvTotalTime.setText(String.valueOf(face+other+1));
            }


            Log.i("======= Hours"," :: "+hours);

        }catch(ParseException ex){
            // handle parsing exception if date string was different from the pattern applying into the SimpleDateFormat contructor
        }

    }
}
