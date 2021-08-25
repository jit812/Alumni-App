package com.example.alumni;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserSignupRequest extends AppCompatActivity {


    public RecyclerView msignupsList;
    List<UserData> userdata;
    HelperAdapter helperAdapter;
    FirebaseAuth mAuth;
    String currentID, list_keys;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup_request);

        msignupsList = findViewById(R.id.signupsList);

        helperAdapter = new HelperAdapter(userdata, getApplicationContext(), list_keys);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        msignupsList.setLayoutManager(new LinearLayoutManager(UserSignupRequest.this));
        userdata = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        currentID = mAuth.getCurrentUser().getUid();


        DatabaseReference rootNode = FirebaseDatabase.getInstance().getReference("Requests").child(currentID);
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

                                String keys = ds.getRef().getKey();
                                UserData data = ds.getValue(UserData.class);
                                userdata.add(data);
                            }
                            helperAdapter = new HelperAdapter(userdata, getApplicationContext(), list_keys);
                            msignupsList.setAdapter(helperAdapter);
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





