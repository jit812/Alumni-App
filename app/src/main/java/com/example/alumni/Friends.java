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

public class Friends extends AppCompatActivity {
    RecyclerView frnsRecyclerview;
    List<UserData> userdata;
    FriendAdapter friendAdapter;
    FirebaseAuth mAuth;
    String currentID, list_keys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        frnsRecyclerview = findViewById(R.id.frnsRecyclerview);

        friendAdapter = new FriendAdapter(userdata, getApplicationContext(), list_keys);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        frnsRecyclerview.setLayoutManager(new LinearLayoutManager(Friends.this));
        userdata = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();

        currentID= mAuth.getCurrentUser().getUid();


        DatabaseReference rootNode = FirebaseDatabase.getInstance().getReference("Friends").child(currentID);
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

                                //String keys = ds.getRef().getKey();
                                UserData data = ds.getValue(UserData.class);
                                userdata.add(data);
                            }
                            friendAdapter = new FriendAdapter(userdata, getApplicationContext(), list_keys);
                            frnsRecyclerview.setAdapter(friendAdapter);
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

