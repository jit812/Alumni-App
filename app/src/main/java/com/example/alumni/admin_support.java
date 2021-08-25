package com.example.alumni;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class admin_support extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Supportadapter supportadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_support);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);

        Query query=FirebaseDatabase.getInstance().getReference().child("support").child("checked").equalTo("0");
        IntializeFields();
        FirebaseRecyclerOptions<Suppots>options=
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