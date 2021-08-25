package com.example.alumni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class GuestLecture extends AppCompatActivity {


    EditText name,phone;
    Spinner school,reason;
    Button buttongl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_lecture);

        name=findViewById(R.id.alumniname_gl);
        phone=findViewById(R.id.phone_gl);
        school=findViewById(R.id.school_gl);
        reason=findViewById(R.id.purpose_gl);
        buttongl=findViewById(R.id.submit_btn_gl);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);



        buttongl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String namet=name.getText().toString();
                String phonet=phone.getText().toString();
                String schoolt=school.getSelectedItem().toString();
                String reasont=reason.getSelectedItem().toString();


                String[] TO = {"revaalumnischools@gmail.com"};
               String email="Hi my name is "+namet+" My Course was "+schoolt+". My Purpose for coming is "+reasont+" My contact no is "+phonet;

//
//                String recepientEmail = ""; // either set to destination email or leave empty
//                Intent intent = new Intent(Intent.ACTION_SENDTO);
//                intent.setData(Uri.parse("mailto:" + recepientEmail));
//                startActivity(intent);


                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");

                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Regarding Lecture");
                emailIntent.putExtra(Intent.EXTRA_TEXT,email);


                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    Toast.makeText(getApplicationContext(),"Redirecting to Email Client",Toast.LENGTH_SHORT);
                    finish();
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(),
                            "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });







    }

    public void backbb(View view) {
        onBackPressed();
    }
}