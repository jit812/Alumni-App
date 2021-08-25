package com.example.alumni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //Initailize variables
    DrawerLayout drawerLayout;
    ImageView mprofileBtn,insta1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mprofileBtn = findViewById(R.id.profileBtn);
        insta1=findViewById(R.id.insta1);

        insta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("http://instagram.com/_u/reva_university");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/reva_university")));
                }

            }
        });

        mprofileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.alumni.MainActivity.this, com.example.alumni.Profile.class);
                startActivity(intent);
            }
        });

    }

    public void ClickContact(View view){
        //Recreate activity
        recreate();
    }

    public void ClickSupport(View view){
        //Redirect activity to dashboard
        redirectActivity(this,support_fee_details.class);
    }

    public void ClickSupportAdmin(View view){
        //Redirect activity to dashboard
        redirectActivity(this,admin_support.class);
    }

    public void ClickContactDetails(View view){
        //Redirect activity to dashboard
        redirectActivity(this, Contacts.class);
    }

    public void ClickAdmissionReference(View view){
        //Redirect activity to dashboard
        redirectActivity(this,AdmissionReferenceActivity.class);
    }

    public void ClickFeedBack(View view){
        //Redirect activity to dashboard
        redirectActivity(this,RadioActivity.class);
    }

    public void ClickAboutAlumni(View view){
        //Redirect activity to dashboard
        redirectActivity(this,about_alumni.class);
    }

    public void ClickAdminNews(View view){
        //Redirect activity to dashboard
        redirectActivity(this,admin_news.class);
    }

    public void ClickAdmissionReferenceAdmin(View view){
        //Redirect activity to dashboard
        redirectActivity(this,AdmissionRetrieveActivity.class);
    }
    public void ClickJobr(View view){
        redirectActivity(this,JobRetrieveActivity.class);
    }


    public static void redirectActivity(Activity activity, Class aClass) {
        //Initialize intent
        Intent intent = new Intent(activity,aClass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //start activity
        activity.startActivity(intent);
    }

}