package com.example.alumni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class UncheckedSupport extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Supportadapter supportadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_support);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);

        String data=getIntent().getStringExtra("node");
        String check=getIntent().getStringExtra("checkornot");
        Query query= FirebaseDatabase.getInstance().getReference().child("support").child(check).child(data);
        IntializeFields();
        FirebaseRecyclerOptions<Suppots> options=
                new FirebaseRecyclerOptions.Builder<Suppots>()
                        .setQuery(query,Suppots.class)
                        .build();
        supportadapter=new Supportadapter(options);
        recyclerView.setAdapter(supportadapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        supportadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        supportadapter.stopListening();
    }

    private void IntializeFields(){
        recyclerView=(RecyclerView)findViewById(R.id.recycler_support);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    public void ClickBack(View view) {
        onBackPressed();
    }
}