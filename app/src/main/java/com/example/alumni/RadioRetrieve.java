package com.example.alumni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class RadioRetrieve extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Radioadapter radioadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_retrieve);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        InitializeFields();

        FirebaseRecyclerOptions<Radio>options=
                new FirebaseRecyclerOptions.Builder<Radio>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Feedback"),Radio.class)
                .build();

        radioadapter=new Radioadapter(options);
        recyclerView.setAdapter(radioadapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        radioadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        radioadapter.stopListening();
    }

    private void InitializeFields() {
        recyclerView = (RecyclerView)findViewById(R.id.radio_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void backbb(View view) {
        onBackPressed();
    }
}