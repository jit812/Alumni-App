package com.example.alumni;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class email extends AppCompatActivity {

    EditText mRecipientEt,mSubjectEt,mMessageEt;
    Button mSendEmailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);

        mRecipientEt=findViewById(R.id.RecipientEt);
        mSubjectEt=findViewById(R.id.SubjectEt);
        mMessageEt=findViewById(R.id.MessageEt);
        mSendEmailBtn=findViewById(R.id.SendEmailBtn);
        mSendEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipient=mRecipientEt.getText().toString().trim();
                String subject=mSubjectEt.getText().toString().trim();
                String message=mMessageEt.getText().toString().trim();
                sendEmail(recipient, subject, message);

            }
        });

    }

    private void sendEmail(String recipient, String subject, String message) {

        Intent mEmailIntent=new Intent(Intent.ACTION_SEND);
        mEmailIntent.setData(Uri.parse("mailto:"));
        mEmailIntent.setType("text/plain");
        mEmailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{recipient});
        mEmailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        mEmailIntent.putExtra(Intent.EXTRA_TEXT, message);
        try {
            startActivity(Intent.createChooser(mEmailIntent,"Choose an Email Client"));

        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT).show();

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
        redirectActivity(this,my_score_retrieve.class);
    }
}