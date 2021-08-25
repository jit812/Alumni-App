package com.example.alumni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Accomodation extends AppCompatActivity {


    private EditText name,srn,purpose,phoneet,noofdayset;
    private Spinner rooms;

    private  String nametxt;
    private String srntxt;
    private String purposetxt;
    private String email;
    private String phone;
    private int room;
    private String noofdays;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomodation);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        name=findViewById(R.id.usernameLogin);
        srn=findViewById(R.id.something);
        purpose=findViewById(R.id.another);
        btn=findViewById(R.id.submit_btn_acc);
        phoneet=findViewById(R.id.anotherone);
        noofdayset=findViewById(R.id.anotheroneitseemsso);
        rooms=findViewById(R.id.anotheroneitseems);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nametxt=name.getText().toString();
                srntxt=srn.getText().toString();
                purposetxt=purpose.getText().toString();
                phone=phoneet.getText().toString();
                noofdays=noofdayset.getText().toString();
                room = rooms.getSelectedItemPosition();


                String[] TO = {"revaalumnischools@gmail.com"};
                email="Hi my name is "+nametxt+" My SRN is "+srntxt+". My Purpose for accomodation is "+purposetxt+". I will be staying in for"+noofdays
            +". I need "+room+"rooms."+" My contact no is "+phone;

//
//                String recepientEmail = ""; // either set to destination email or leave empty
//                Intent intent = new Intent(Intent.ACTION_SENDTO);
//                intent.setData(Uri.parse("mailto:" + recepientEmail));
//                startActivity(intent);


                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");

                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Regarding Accomodation");
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