package com.example.alumni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class JobRetrieveActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private JobAdapter jobAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_retrieve);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);

        IntializeFields();

        FirebaseRecyclerOptions<Jobs> options=
                new FirebaseRecyclerOptions.Builder<Jobs>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Job Portal"),Jobs.class)
                        .build();
        jobAdapter= new JobAdapter(options);
        recyclerView.setAdapter(jobAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        jobAdapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        jobAdapter.stopListening();
    }


    private void IntializeFields() {
        recyclerView=(RecyclerView) findViewById(R.id.recycle_job);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        redirectActivity(this,AdminDashboard.class);
    }

    public void backbb(View view) {
        onBackPressed();
    }
}