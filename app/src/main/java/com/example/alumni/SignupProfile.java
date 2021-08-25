package com.example.alumni;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SignupProfile extends AppCompatActivity {

    private static final String TAG = "TAG";
    TextView muserProfileTitle;
    EditText mfullnameProfile, msrnProfile, memailProfile, mphoneProfile, mpassedYearProfile, mschoolEdit;
    Button macceptBtnProfile, mdeclineBtnProfile, mhomeBtn, muserRequest;
    ProgressBar mprogressBarProfile;

    private DatabaseReference regRef, usersRef, requestsRef;
    private FirebaseAuth mAuth;
    private String senderUserId;
    private String receiverUserId;
    private String saveCurrentDate, CURRENT_STATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_profile);

        muserProfileTitle = findViewById(R.id.userProfileTitle);
        mfullnameProfile = findViewById(R.id.fullnameProfile);
        msrnProfile = findViewById(R.id.srnProfile);
        memailProfile = findViewById(R.id.emailProfile);
        mphoneProfile = findViewById(R.id.phoneProfile);
        mpassedYearProfile = findViewById(R.id.passedYearProfile);
        macceptBtnProfile = findViewById(R.id.acceptBtnProfile);
        mdeclineBtnProfile = findViewById(R.id.declineBtnProfile);
        mprogressBarProfile = findViewById(R.id.progressBarProfile);
        mhomeBtn = findViewById(R.id.homeBtn);
        muserRequest = findViewById(R.id.userRequest);
        mschoolEdit = findViewById(R.id.schoolEdit);

        mAuth = FirebaseAuth.getInstance();
        receiverUserId = mAuth.getCurrentUser().getUid();


        usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        regRef = FirebaseDatabase.getInstance().getReference().child("Register");
        requestsRef = FirebaseDatabase.getInstance().getReference().child("Requests");

        mfullnameProfile.setText(getIntent().getStringExtra("fullnamekey"));
        msrnProfile.setText(getIntent().getStringExtra("srnkey"));
        memailProfile.setText(getIntent().getStringExtra("emailkey"));
        mphoneProfile.setText(getIntent().getStringExtra("phonekey"));
        mpassedYearProfile.setText(getIntent().getStringExtra("passedyearkey"));
        mschoolEdit.setText(getIntent().getStringExtra("schoolkey"));

        senderUserId = getIntent().getStringExtra("uid");
        final String mainadmin = "S87fylxtp3cEf7ZRXvLhC6E9xQu1";
        final String csaadmin = "U1rKePSC6jM7UU4f76XZOeLXzDb2";
        final String eceadmin = "jdZY0dfI5ua9Zpq4Dmtm9iPtOn83";

        macceptBtnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (receiverUserId.equals(mainadmin)) {
                    AcceptRequest();
                } else{
                    AcceptRequestcsa();
                }

            }


            private void AcceptRequestcsa() {
                Calendar calFordDate = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
                saveCurrentDate = currentDate.format(calFordDate.getTime());
                usersRef.child(receiverUserId).child(senderUserId).child("date").setValue(saveCurrentDate)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    usersRef.child(senderUserId).child(receiverUserId).child("date").setValue(saveCurrentDate)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        requestsRef.child(senderUserId).child(receiverUserId)
                                                                .removeValue()
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if (task.isSuccessful()) {
                                                                            requestsRef.child(receiverUserId).child(senderUserId)
                                                                                    .removeValue()
                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                        @Override
                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                            if (task.isSuccessful()) {
                                                                                                requestsRef.child(senderUserId).child(mainadmin)
                                                                                                        .removeValue()
                                                                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                            @Override
                                                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                                                if (task.isSuccessful()) {
                                                                                                                    requestsRef.child(mainadmin).child(senderUserId)
                                                                                                                            .removeValue()
                                                                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                @Override
                                                                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                    if (task.isSuccessful()) {
                                                                                                                                        CURRENT_STATE = "Authorised User";
                                                                                                                                        macceptBtnProfile.setVisibility(View.INVISIBLE);
                                                                                                                                        macceptBtnProfile.setEnabled(false);
                                                                                                                                        mdeclineBtnProfile.setVisibility(View.INVISIBLE);
                                                                                                                                        mdeclineBtnProfile.setEnabled(false);
                                                                                                                                        mhomeBtn.setVisibility(View.VISIBLE);
                                                                                                                                        mhomeBtn.setEnabled(true);
                                                                                                                                        muserRequest.setVisibility(View.VISIBLE);
                                                                                                                                        muserRequest.setEnabled(true);
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            });
                                                                                                                }
                                                                                                            }
                                                                                                        });
                                                                                            }
                                                                                        }
                                                                                    });
                                                                        }
                                                                    }
                                                                });
                                                    }
                                                }
                                            });
                                }
                            }
                        });
            }

            private void AcceptRequest() {
                Calendar calFordDate = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
                saveCurrentDate = currentDate.format(calFordDate.getTime());
                usersRef.child(receiverUserId).child(senderUserId).child("date").setValue(saveCurrentDate)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    usersRef.child(senderUserId).child(receiverUserId).child("date").setValue(saveCurrentDate)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        requestsRef.child(senderUserId).child(receiverUserId)
                                                                .removeValue()
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if (task.isSuccessful()) {
                                                                            requestsRef.child(receiverUserId).child(senderUserId).removeValue()
                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                        @Override
                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                            if (task.isSuccessful()) {
                                                                                                requestsRef.child(csaadmin).addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                                    @Override
                                                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                                                        for (DataSnapshot ds: snapshot.getChildren()){
                                                                                                            String key = ds.getRef().getKey();
                                                                                                            if (key.equals(senderUserId)){
                                                                                                                requestsRef.child(senderUserId).child(csaadmin)
                                                                                                                        .removeValue()
                                                                                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                            @Override
                                                                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                if (task.isSuccessful()) {
                                                                                                                                    requestsRef.child(csaadmin).child(senderUserId)
                                                                                                                                            .removeValue()
                                                                                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                @Override
                                                                                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                                    if (task.isSuccessful()) {
                                                                                                                                                        CURRENT_STATE = "Authorised User";
                                                                                                                                                        macceptBtnProfile.setVisibility(View.INVISIBLE);
                                                                                                                                                        macceptBtnProfile.setEnabled(false);
                                                                                                                                                        mdeclineBtnProfile.setVisibility(View.INVISIBLE);
                                                                                                                                                        mdeclineBtnProfile.setEnabled(false);
                                                                                                                                                        mhomeBtn.setVisibility(View.VISIBLE);
                                                                                                                                                        mhomeBtn.setEnabled(true);
                                                                                                                                                        muserRequest.setVisibility(View.VISIBLE);
                                                                                                                                                        muserRequest.setEnabled(true);

                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            });
                                                                                                                                }
                                                                                                                            }
                                                                                                                        });
                                                                                                            }
                                                                                                        }
                                                                                                    }

                                                                                                    @Override
                                                                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                                                                    }
                                                                                                });

                                                                                                requestsRef.child(eceadmin).addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                                    @Override
                                                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                                                        for (DataSnapshot ds: snapshot.getChildren()){
                                                                                                            String key = ds.getRef().getKey();
                                                                                                            if (key.equals(senderUserId)){
                                                                                                                requestsRef.child(senderUserId).child(eceadmin)
                                                                                                                        .removeValue()
                                                                                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                            @Override
                                                                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                if (task.isSuccessful()) {
                                                                                                                                    requestsRef.child(eceadmin).child(senderUserId)
                                                                                                                                            .removeValue()
                                                                                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                @Override
                                                                                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                                    if (task.isSuccessful()) {
                                                                                                                                                        CURRENT_STATE = "Authorised User";
                                                                                                                                                        macceptBtnProfile.setVisibility(View.INVISIBLE);
                                                                                                                                                        macceptBtnProfile.setEnabled(false);
                                                                                                                                                        mdeclineBtnProfile.setVisibility(View.INVISIBLE);
                                                                                                                                                        mdeclineBtnProfile.setEnabled(false);
                                                                                                                                                        mhomeBtn.setVisibility(View.VISIBLE);
                                                                                                                                                        mhomeBtn.setEnabled(true);
                                                                                                                                                        muserRequest.setVisibility(View.VISIBLE);
                                                                                                                                                        muserRequest.setEnabled(true);

                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            });
                                                                                                                                }
                                                                                                                            }
                                                                                                                        });
                                                                                                            }
                                                                                                        }
                                                                                                    }

                                                                                                    @Override
                                                                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                                                                    }
                                                                                                });

                                                                                            }
                                                                                        }
                                                                                    });
                                                                        }
                                                                    }
                                                                });
                                                    }
                                                }
                                            });
                                }
                            }
                        });


            }

        });



        mdeclineBtnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (receiverUserId.equals(mainadmin)) {
                    DeclineRequest();
                } else{
                    DeclineRequestcsa();
                }
            }

            private void DeclineRequestcsa() {
                requestsRef.child(receiverUserId).child(senderUserId)
                        .removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    requestsRef.child(senderUserId).child(receiverUserId).removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        requestsRef.child(mainadmin).child(senderUserId)
                                                                .removeValue()
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if (task.isSuccessful()){
                                                                            requestsRef.child(senderUserId).child(mainadmin)
                                                                                    .removeValue()
                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                        @Override
                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                            if (task.isSuccessful()) {
                                                                                                mdeclineBtnProfile.setVisibility(View.INVISIBLE);
                                                                                                mdeclineBtnProfile.setEnabled(false);
                                                                                                macceptBtnProfile.setVisibility(View.INVISIBLE);
                                                                                                macceptBtnProfile.setEnabled(false);
                                                                                                mhomeBtn.setVisibility(View.VISIBLE);
                                                                                                mhomeBtn.setEnabled(true);
                                                                                                muserRequest.setVisibility(View.VISIBLE);
                                                                                                muserRequest.setEnabled(true);
                                                                                            }
                                                                                        }
                                                                                    });
                                                                        }

                                                                    }
                                                                });
                                                    }
                                                }
                                            });
                                }
                            }
                        });
            }

            private void DeclineRequest() {
                requestsRef.child(receiverUserId).child(senderUserId)
                        .removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    requestsRef.child(senderUserId).child(receiverUserId).removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        requestsRef.child(csaadmin).addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                for (DataSnapshot ds: snapshot.getChildren()) {
                                                                    String key = ds.getRef().getKey();
                                                                    if (key.equals(senderUserId)) {
                                                                        requestsRef.child(csaadmin).child(senderUserId)
                                                                                .removeValue()
                                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                        if (task.isSuccessful()) {
                                                                                            requestsRef.child(senderUserId).child(csaadmin)
                                                                                                    .removeValue()
                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                        @Override
                                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                                            if (task.isSuccessful()) {
                                                                                                                mdeclineBtnProfile.setVisibility(View.INVISIBLE);
                                                                                                                mdeclineBtnProfile.setEnabled(false);
                                                                                                                macceptBtnProfile.setVisibility(View.INVISIBLE);
                                                                                                                macceptBtnProfile.setEnabled(false);
                                                                                                                mhomeBtn.setVisibility(View.VISIBLE);
                                                                                                                mhomeBtn.setEnabled(true);
                                                                                                                muserRequest.setVisibility(View.VISIBLE);
                                                                                                                muserRequest.setEnabled(true);
                                                                                                            }
                                                                                                        }
                                                                                                    });
                                                                                        }
                                                                                    }
                                                                                });
                                                                    }
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });

                                                        requestsRef.child(eceadmin).addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                for (DataSnapshot ds: snapshot.getChildren()) {
                                                                    String key = ds.getRef().getKey();
                                                                    if (key.equals(senderUserId)) {
                                                                        requestsRef.child(eceadmin).child(senderUserId)
                                                                                .removeValue()
                                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                        if (task.isSuccessful()) {
                                                                                            requestsRef.child(senderUserId).child(eceadmin)
                                                                                                    .removeValue()
                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                        @Override
                                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                                            if (task.isSuccessful()) {
                                                                                                                mdeclineBtnProfile.setVisibility(View.INVISIBLE);
                                                                                                                mdeclineBtnProfile.setEnabled(false);
                                                                                                                macceptBtnProfile.setVisibility(View.INVISIBLE);
                                                                                                                macceptBtnProfile.setEnabled(false);
                                                                                                                mhomeBtn.setVisibility(View.VISIBLE);
                                                                                                                mhomeBtn.setEnabled(true);
                                                                                                                muserRequest.setVisibility(View.VISIBLE);
                                                                                                                muserRequest.setEnabled(true);
                                                                                                            }
                                                                                                        }
                                                                                                    });
                                                                                        }
                                                                                    }
                                                                                });
                                                                    }
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });



                                                    }
                                                }
                                            });
                                }
                            }
                        });


            }
        });

        mhomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupProfile.this, AdminDashboard.class);
                startActivity(intent);
            }
        });


        muserRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupProfile.this, UserSignupRequest.class);
                startActivity(intent);
            }
        });

    }


}