package com.example.alumni;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class support_fee_details extends AppCompatActivity {

    Button next;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_fee_details);

        img=findViewById(R.id.backbb);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
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
    public void ClickSup(View v) {
        Intent intent;
        switch (v.getId()) {

            case R.id.d1:
                intent = new Intent(support_fee_details.this, SupportDetails.class);
                intent.putExtra("SDetails", 1);
                startActivity(intent);
                break;
            case R.id.d2:
                intent = new Intent(this, SupportDetails.class);
                intent.putExtra("SDetails", 2);
                startActivity(intent);
                break;
            case R.id.d3:
                intent = new Intent(this, SupportDetails.class);
                intent.putExtra("SDetails", 3);
                startActivity(intent);
                break;
            case R.id.d4:
                intent = new Intent(this, SupportDetails.class);
                intent.putExtra("SDetails", 4);
                startActivity(intent);
                break;
            case R.id.d5:
                intent = new Intent(this, SupportDetails.class);
                intent.putExtra("SDetails", 5);
                startActivity(intent);
                break;
            case R.id.d6:
                intent = new Intent(this, SupportDetails.class);
                intent.putExtra("SDetails", 6);
                startActivity(intent);
                break;
            case R.id.d7:
                intent = new Intent(this, SupportDetails.class);
                intent.putExtra("SDetails", 7);
                startActivity(intent);
                break;
            case R.id.d8:
                intent = new Intent(this, Accomodation.class);
                intent.putExtra("SDetails", 8);
                startActivity(intent);
                break;
            case R.id.d9:
                intent = new Intent(this, GuestLecture.class);
                intent.putExtra("SDetails", 9);
                startActivity(intent);
                break;
        }
    }
}
