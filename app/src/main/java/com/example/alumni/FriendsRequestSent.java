package com.example.alumni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FriendsRequestSent extends AppCompatActivity {
    RecyclerView FrnsReqRecyclerView;
    List<UserData> userdata;
    FriendsReqSentAdapter friendsReqSentAdapter;
    FirebaseAuth mAuth;
    String currentID, list_keys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_request_sent);
        FrnsReqRecyclerView = findViewById(R.id.FrnsReqRecyclerView);
        friendsReqSentAdapter = new FriendsReqSentAdapter(userdata, getApplicationContext(), list_keys);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        FrnsReqRecyclerView.setLayoutManager(new LinearLayoutManager(FriendsRequestSent.this));
        userdata = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();

        currentID= mAuth.getCurrentUser().getUid();


        DatabaseReference rootNode = FirebaseDatabase.getInstance().getReference("FriendsRequests").child(currentID);
        final DatabaseReference regNode = FirebaseDatabase.getInstance().getReference("Register");
        rootNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds: snapshot.getChildren()){
                    final String list_keys = ds.getRef().getKey();
                    regNode.orderByKey().equalTo(list_keys).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot ds: snapshot.getChildren()){
                                UserData data = ds.getValue(UserData.class);
                                userdata.add(data);
                            }
                            friendsReqSentAdapter = new FriendsReqSentAdapter(userdata, getApplicationContext(), list_keys);
                            FrnsReqRecyclerView.setAdapter(friendsReqSentAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}

