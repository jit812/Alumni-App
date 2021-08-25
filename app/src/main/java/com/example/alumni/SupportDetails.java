package com.example.alumni;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SupportDetails extends AppCompatActivity {


    public int num;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supportdetails);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        TextView sdetails = (TextView) findViewById(R.id.SDetails);

        next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupportDetails.this,support.class);
                startActivity(intent);
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            num = extras.getInt("SDetails");

        }

        switch (num) {
            case 1:
                sdetails.setText("Dear Student/Alumni,\n\n\t\tTo get Transfer Certificate, kindly fill the form and pay Rs.500 to given account.\n\nAccount number:6662000100000101\nIFSC Code:KARB0000666\nMICR-560052072\nKarnataka Bank\nREVA University");
                break;
            case 2:
                sdetails.setText("Dear Student/Alumni,\n\n\t\tTo get Migration Certificate, kindly fill the form and pay Rs.500 to given account.\n\nAccount number:6662000100000101\nIFSC Code:KARB0000666\nMICR-560052072\nKarnataka Bank\nREVA University");
                break;
            case 3:
                sdetails.setText("Dear Student/Alumni,\n\n\t\tTo get Transcript, kindly fill the form and pay Rs.500 to given account.\n\nAccount number:6662000100000101\nIFSC Code:KARB0000666\nMICR-560052072\nKarnataka Bank\nREVA University");
                break;
            case 4:
                sdetails.setText("Dear Student/Alumni,\n\n\t\tTo get Consolidated Marks, kindly fill the form and pay Rs.500 to given account.\n\nAccount number:6662000100000101\nIFSC Code:KARB0000666\nMICR-560052072\nKarnataka Bank\nREVA University");
                break;
            case 5:
               sdetails.setText("Dear Student/Alumni,\n\n\t\tTo get Provisional Certificate, kindly fill the form and pay Rs.500 to given account.\n\nAccount number:6662000100000101\nIFSC Code:KARB0000666\nMICR-560052072\nKarnataka Bank\nREVA University");
                break;
            case 6:
                sdetails.setText("Dear Student/Alumni,\n\n\t\tTo get Duplicate Marks Card, kindly fill the form and pay Rs.500 to given account.\n\nAccount number:6662000100000101\nIFSC Code:KARB0000666\nMICR-560052072\nKarnataka Bank\nREVA University");
                break;
            case 7:
                sdetails.setText("Dear Student/Alumni,\n\n\t\tTo get 10th,12th Marks Card, kindly fill the form and pay Rs.500 to given account.\n\nAccount number:6662000100000101\nIFSC Code:KARB0000666\nMICR-560052072\nKarnataka Bank\nREVA University");
                break;
            case 8:
                sdetails.setText("Dear Student/Alumni,\n\n\t\tKindly mail to email:revaalumnischools@gmail.com\n\nfor booking accomodation,mentioning:\nPurpose of visiting university\nNo. of days\nVisiting School name:\n\nContact us for further queries:\nPhone:\nEmail:");
                next.setVisibility(Button.GONE);
                break;
            case 9:
                sdetails.setText("Dear Student/Alumni,\nKindly mail to email:revaalumnischools@gmail.com \n by mentioning:\nPurpose of visiting university:Guest Lecture/Workshop/Conference/Hands on Session/Seminar\nNo. of days\nVisiting School name:\n\nDomain Expert area:\nContact us for further queries:\nPhone:\nEmail:");
                next.setVisibility(Button.GONE);
                break;

        }
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        //Initialize intent
        Intent intent = new Intent(activity,aClass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //start activity
        activity.startActivity(intent);
    }

    public void ClickBack(View view){
        //Redirect activity to dashboard
        redirectActivity(this,support_fee_details.class);
    }

    public void backbb(View view) {
        onBackPressed();
    }
}