package com.example.alumni;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RetrieveNews extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Newsadapter newsadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_news);


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);


        InitializeFields();

        FirebaseRecyclerOptions<News>options=
                new FirebaseRecyclerOptions.Builder<News>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Admin News"),News.class)
                .build();
        newsadapter = new Newsadapter(options);
        recyclerView.setAdapter(newsadapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        newsadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        newsadapter.stopListening();
    }

    private void InitializeFields() {
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
    }


    public void ClickBack(View view) {
        onBackPressed();
    }
}