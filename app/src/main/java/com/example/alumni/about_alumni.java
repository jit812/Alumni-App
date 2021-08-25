package com.example.alumni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class about_alumni extends AppCompatActivity {
    ViewPager viewPager;
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_alumni);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);
        t5=findViewById(R.id.t5);
        t6=findViewById(R.id.t6);
        t7=findViewById(R.id.t7);
        t8=findViewById(R.id.t8);
        t9=findViewById(R.id.t9);
        t10=findViewById(R.id.t10);
        t11=findViewById(R.id.t11);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            t1.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            t2.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            t3.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            t4.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            t5.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            t6.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            t7.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            t8.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            t9.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            t10.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            t11.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
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
        redirectActivity(this,DashBoard.class);
    }

    public void backbb(View view) {

        onBackPressed();
    }
}
