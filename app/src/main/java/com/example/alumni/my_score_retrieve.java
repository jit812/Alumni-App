package com.example.alumni;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class my_score_retrieve extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Scoreadapter scoreadapter;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_score_retrieve);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        InitializeFields();

        FirebaseRecyclerOptions<Score> options=
                new FirebaseRecyclerOptions.Builder<Score>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("MyScore"),Score.class)
                        .build();

        scoreadapter=new Scoreadapter(options);
        recyclerView.setAdapter(scoreadapter);
        submit = findViewById(R.id.submit1);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(my_score_retrieve.this,email.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        scoreadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        scoreadapter.stopListening();
    }

    private void InitializeFields() {
        recyclerView = (RecyclerView)findViewById(R.id.recycler_score);
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
}