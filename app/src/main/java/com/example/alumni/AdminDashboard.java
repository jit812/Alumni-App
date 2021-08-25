package com.example.alumni;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;

public class AdminDashboard extends AppCompatActivity {

    LinearLayout muserRequests,logout;
    TextView jobview,jobadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        logout=findViewById(R.id.logoutll);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth mAuth=FirebaseAuth.getInstance();
                mAuth.signOut();
                Intent i=new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });


        muserRequests = findViewById(R.id.userRequests);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        muserRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(com.example.alumni.AdminDashboard.this, com.example.alumni.UserSignupRequest.class);
                startActivity(intent);
            }
        });
    }
    public void ClickSupportAdmin(View view){
        //Redirect activity to dashboard
        redirectActivity(this,AdminSupportList.class);
    }
    public void ClickAdmissionReferenceAdmin(View view){
        //Redirect activity to dashboard
        redirectActivity(this,AdmissionRetrieveActivity.class);
    }
    public void ClickAdminNews(View view){
        //Redirect activity to dashboard
        redirectActivity(this,admin_news.class);
    }
    public void ClickJobr(View view){




        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(AdminDashboard.this);
        bottomSheetDialog.setContentView(R.layout.bs_job);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        jobview = bottomSheetDialog.findViewById(R.id.jobview);
        jobadd = bottomSheetDialog.findViewById(R.id.jobadd);

        jobadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),JobPortal.class);
                startActivity(i);
                bottomSheetDialog.dismiss();

            }
        });

        jobview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),JobRetrieveActivity.class);
                startActivity(i);
                bottomSheetDialog.dismiss();

            }
        });




    }
    public void ClickFeed(View view){
        redirectActivity(this,RadioRetrieve.class);
    }
    public void ClickScore(View view){ redirectActivity(this,my_score.class);}
    public void ClickScre(View view){ redirectActivity(this,my_score_retrieve.class);}
    public static void redirectActivity(Activity activity, Class aClass) {
        //Initialize intent
        Intent intent = new Intent(activity,aClass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //start activity
        activity.startActivity(intent);
    }

    public void backbb(View view) {
        onBackPressed();
    }
}