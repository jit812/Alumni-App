package com.example.alumni;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class userProfile extends AppCompatActivity {
    EditText mfullnameProfileText, memailProfileText, mphoneProfileText, mschoolsEdit;
    private String savedate,savetime;
    Button mupdateBtn;
    FirebaseAuth mAuth;
    EditText mdobpersonal, maddresspersonal, mpincodepersonal, mfathername, mbloodgroup;
    EditText mdesignationText, mdomainText, mcompanyName, mjobprofileInterest, mdomainInterested, mskillset,mjobSeeking;
    EditText msrnProfileText, mcourseProflieText, myearPassedText, malumniOf;
    Button Edit;
    private Spinner courseSprinner, schoolsSpinner;
    ImageView mgearBtn;
    LinearLayout emll,uemll;
    private static final int GalleryPick=1;
    private Uri ImageUri;
    private StorageReference CategoryImagesRef;
    int flag=0;
    private DatabaseReference CategoryRef;
    private String categoryRandomKey,downloadImageUrl;
    ImageView imageView;
    private RadioGroup radioGroup;
    ProgressDialog progressdialog;
    Button sendRequestBtn, cancelRequestBtn, acceptRequestBtn, unfriendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mAuth = FirebaseAuth.getInstance();
        final String currentUserId = mAuth.getCurrentUser().getUid();
        final DatabaseReference reqRef = FirebaseDatabase.getInstance().getReference("FriendsRequests");
        DatabaseReference frnsRef = FirebaseDatabase.getInstance().getReference("Friends");

        final String visit_user_id = getIntent().getStringExtra("emailkey");

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
        emll = findViewById(R.id.emll);
        uemll = findViewById(R.id.unemll);

        courseSprinner = findViewById(R.id.branchList);
        schoolsSpinner = findViewById(R.id.schoolsList);

        sendRequestBtn = findViewById(R.id.sendRequestBtn);
        cancelRequestBtn = findViewById(R.id.cancelRequestBtn);
        acceptRequestBtn = findViewById(R.id.acceptRequestBtn);
        unfriendBtn = findViewById(R.id.unfriendBtn);

        String[] school = getResources().getStringArray(R.array.Programs);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_list, school);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        courseSprinner.setAdapter(arrayAdapter);


        CategoryImagesRef = FirebaseStorage.getInstance().getReference().child("User Images");
        progressdialog = new ProgressDialog(userProfile.this);
        progressdialog.setMessage("Please Wait");



        final DatabaseReference regRef = FirebaseDatabase.getInstance().getReference("Register");
        final DatabaseReference frnRef = FirebaseDatabase.getInstance().getReference("Friends");
        regRef.orderByChild("email").equalTo(visit_user_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    final String visit_key = ds.getRef().getKey();
                    //Toast.makeText(userProfile.this, "visit_key: "+ visit_key, Toast.LENGTH_SHORT).show();
                    frnRef.child(currentUserId).orderByKey().equalTo(visit_key).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                unfriendBtn.setVisibility(View.VISIBLE);
                                unfriendBtn.setEnabled(true);
                                cancelRequestBtn.setVisibility(View.INVISIBLE);
                                cancelRequestBtn.setEnabled(false);
                                acceptRequestBtn.setVisibility(View.INVISIBLE);
                                acceptRequestBtn.setEnabled(false);
                                sendRequestBtn.setVisibility(View.INVISIBLE);
                                sendRequestBtn.setEnabled(false);

                                //unfriend(visit_key, currentUserId);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                    reqRef.child(currentUserId).child(visit_key).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                cancelRequestBtn.setVisibility(View.VISIBLE);
                                cancelRequestBtn.setEnabled(true);
                                acceptRequestBtn.setVisibility(View.INVISIBLE);
                                acceptRequestBtn.setEnabled(false);
                                unfriendBtn.setVisibility(View.INVISIBLE);
                                unfriendBtn.setEnabled(false);
                                sendRequestBtn.setVisibility(View.INVISIBLE);
                                sendRequestBtn.setEnabled(false);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                    reqRef.child(visit_key).child(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                acceptRequestBtn.setVisibility(View.VISIBLE);
                                acceptRequestBtn.setEnabled(true);
                                cancelRequestBtn.setVisibility(View.INVISIBLE);
                                cancelRequestBtn.setEnabled(false);
                                unfriendBtn.setVisibility(View.INVISIBLE);
                                unfriendBtn.setEnabled(false);
                                sendRequestBtn.setVisibility(View.INVISIBLE);
                                sendRequestBtn.setEnabled(false);


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    sendRequestBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sendRequest(visit_key, currentUserId);
                        }
                    });

                    acceptRequestBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            acceptFriendRequest(visit_key, currentUserId);
                        }
                    });

                    cancelRequestBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cancelRequest(visit_key, currentUserId);
                        }
                    });

                    unfriendBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            unfriend(visit_key, currentUserId);
                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.radioem) {

                    emll.setVisibility(View.VISIBLE);
                    uemll.setVisibility(View.GONE);
                    final DatabaseReference regRef = FirebaseDatabase.getInstance().getReference("Register");
                    regRef.orderByChild("email").equalTo(visit_user_id).addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                            for (DataSnapshot ds : snapshot.getChildren()) {
                                String mycompanyname = ds.child("companyname").getValue().toString();
                                String mydomain = ds.child("domain").getValue().toString();
                                String mydesignation = ds.child("designation").getValue().toString();

                                mcompanyName.setText(mycompanyname);
                                mdomainText.setText(mydomain);
                                mdesignationText.setText(mydesignation);

                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                if (checkedId == R.id.radiouem) {

                    uemll.setVisibility(View.VISIBLE);
                    emll.setVisibility(View.GONE);
                    final DatabaseReference regRef = FirebaseDatabase.getInstance().getReference("Register");
                    regRef.orderByChild("email").equalTo(visit_user_id).addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (final DataSnapshot ds : snapshot.getChildren()) {


                                String myprofileinterest = ds.child("profileinterest").getValue().toString();
                                String mydomaininterest = ds.child("domaininterest").getValue().toString();
                                String myskillset = ds.child("skillset").getValue().toString();

                                mjobprofileInterest.setText(myprofileinterest);
                                mdomainInterested.setText(mydomaininterest);
                                mskillset.setText(myskillset);


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }
        });


        final RadioButton rem = findViewById(R.id.radioem);
        final RadioButton ruem = findViewById(R.id.radiouem);


        imageView = (ImageView) findViewById(R.id.cardView2);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);

        mfullnameProfileText = findViewById(R.id.fullnameProfileText);
        memailProfileText = findViewById(R.id.emailProfileText);
        mphoneProfileText = findViewById(R.id.phoneProfileText);
        mdobpersonal = findViewById(R.id.dobpersonal);
        maddresspersonal = findViewById(R.id.addresspersonal);
        mpincodepersonal = findViewById(R.id.pincodepersonal);
        mfathername = findViewById(R.id.fathername);
        mbloodgroup = findViewById(R.id.bloodgroup);
        msrnProfileText = findViewById(R.id.srnProfileText);
        mcourseProflieText = findViewById(R.id.courceProflieText);
        myearPassedText = findViewById(R.id.yearPassedText);
        malumniOf = findViewById(R.id.alumniOf);
        mjobSeeking = findViewById(R.id.jobSeeking);
        mdesignationText = findViewById(R.id.designationText);
        mdomainText = findViewById(R.id.domainText);
        mcompanyName = findViewById(R.id.companyName);
        mjobprofileInterest = findViewById(R.id.jobprofileInterest);
        mdomainInterested = findViewById(R.id.domainInterested);
        mskillset = findViewById(R.id.skillset);


        //final DatabaseReference regRef = FirebaseDatabase.getInstance().getReference("Register");
        regRef.orderByChild("email").equalTo(visit_user_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (final DataSnapshot ds : snapshot.getChildren()) {

                    if (ds.child("image").exists()) {
                        String url = ds.child("image").getValue().toString();
                        Glide.with(getApplicationContext())

                                .load(url).placeholder(R.drawable.user).circleCrop()
                                .into(imageView);
                        String myProfileName = ds.child("fullname").getValue().toString();
                        String myemail = ds.child("email").getValue().toString();
                        String myphone = ds.child("phone").getValue().toString();
                        String mysrn = ds.child("srn").getValue().toString();

                        //String mybranch = ds.child("branch").getValue().toString();
                        String myYearPassed = ds.child("passedyear").getValue().toString();
                        String coursename = ds.child("course").getValue().toString();
                        String schoolname = ds.child("school").getValue().toString();

                        String mydob = ds.child("dob").getValue().toString();
                        String myaddress = ds.child("address").getValue().toString();
                        String mypincode = ds.child("pincode").getValue().toString();
                        String myfathername = ds.child("fathername").getValue().toString();
                        String mybloodgroup = ds.child("bloodgroup").getValue().toString();

                        String mycompanyname = ds.child("companyname").getValue().toString();
                        String mydomain = ds.child("domain").getValue().toString();
                        String mydesignation = ds.child("designation").getValue().toString();

                        String myprofileinterest = ds.child("profileinterest").getValue().toString();
                        String mydomaininterest = ds.child("domaininterest").getValue().toString();
                        String myskillset = ds.child("skillset").getValue().toString();


                        mfullnameProfileText.setText(myProfileName);
                        memailProfileText.setText(myemail);
                        mphoneProfileText.setText(myphone);
                        msrnProfileText.setText(mysrn);

                        mcourseProflieText.setText(coursename);
                        myearPassedText.setText(myYearPassed);

                        mdobpersonal.setText(mydob);
                        maddresspersonal.setText(myaddress);
                        mpincodepersonal.setText(mypincode);
                        mfathername.setText(myfathername);
                        mbloodgroup.setText(mybloodgroup);

                        mcompanyName.setText(mycompanyname);
                        mdomainText.setText(mydomain);
                        mdesignationText.setText(mydesignation);

                        mjobprofileInterest.setText(myprofileinterest);
                        mdomainInterested.setText(mydomaininterest);
                        mskillset.setText(myskillset);

                    } else {
                        String myProfileName = ds.child("fullname").getValue().toString();
                        String myemail = ds.child("email").getValue().toString();
                        String myphone = ds.child("phone").getValue().toString();
                        String mysrn = ds.child("srn").getValue().toString();

                        String mybranch = ds.child("branch").getValue().toString();
                        String myYearPassed = ds.child("passedyear").getValue().toString();
                        String coursename = ds.child("course").getValue().toString();
                        String schoolname = ds.child("school").getValue().toString();

                        String mydob = ds.child("dob").getValue().toString();
                        String myaddress = ds.child("address").getValue().toString();
                        String mypincode = ds.child("pincode").getValue().toString();
                        String myfathername = ds.child("fathername").getValue().toString();
                        String mybloodgroup = ds.child("bloodgroup").getValue().toString();


                        mfullnameProfileText.setText(myProfileName);
                        memailProfileText.setText(myemail);
                        mphoneProfileText.setText(myphone);
                        msrnProfileText.setText(mysrn);

                        myearPassedText.setText(myYearPassed);
                        mcourseProflieText.setText(coursename);

                        mdobpersonal.setText(mydob);
                        maddresspersonal.setText(myaddress);
                        mpincodepersonal.setText(mypincode);
                        mfathername.setText(myfathername);
                        mbloodgroup.setText(mybloodgroup);

                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void unfriend(final String visit_key, final String currentUserId) {
        final DatabaseReference frnRef = FirebaseDatabase.getInstance().getReference("Friends");
        frnRef.child(currentUserId).child(visit_key)
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            frnRef.child(visit_key).child(currentUserId).removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                unfriendBtn.setVisibility(View.INVISIBLE);
                                                unfriendBtn.setEnabled(false);
                                                acceptRequestBtn.setVisibility(View.INVISIBLE);
                                                acceptRequestBtn.setEnabled(false);
                                                cancelRequestBtn.setVisibility(View.INVISIBLE);
                                                cancelRequestBtn.setEnabled(false);
                                                sendRequestBtn.setVisibility(View.INVISIBLE);
                                                sendRequestBtn.setEnabled(false);
                                                Toast.makeText(userProfile.this, "Removed this person from Friends list ", Toast.LENGTH_SHORT).show();


                                            }
                                        }
                                    });
                        }
                    }
                });

    }

    private void acceptFriendRequest(final String visit_key, final String currentUserId) {
        final DatabaseReference frnsRef = FirebaseDatabase.getInstance().getReference("Friends");
        final DatabaseReference reqRef = FirebaseDatabase.getInstance().getReference("FriendsRequests");
        Calendar calFordDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        final String saveCurrentDate = currentDate.format(calFordDate.getTime());
        frnsRef.child(currentUserId).child(visit_key).child("date").setValue(saveCurrentDate)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            frnsRef.child(visit_key).child(currentUserId).child("date").setValue(saveCurrentDate)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                reqRef.child(visit_key).child(currentUserId)
                                                        .removeValue()
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    reqRef.child(currentUserId).child(visit_key).removeValue()
                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                    if (task.isSuccessful()) {
                                                                                        //CURRENT_STATE = "Authorised User";
                                                                                        acceptRequestBtn.setVisibility(View.INVISIBLE);
                                                                                        acceptRequestBtn.setEnabled(false);
                                                                                        cancelRequestBtn.setVisibility(View.INVISIBLE);
                                                                                        cancelRequestBtn.setEnabled(false);
                                                                                        acceptRequestBtn.setVisibility(View.INVISIBLE);
                                                                                        acceptRequestBtn.setEnabled(false);
                                                                                        sendRequestBtn.setVisibility(View.VISIBLE);
                                                                                        sendRequestBtn.setEnabled(true);
                                                                                        unfriendBtn.setVisibility(View.VISIBLE);
                                                                                        unfriendBtn.setEnabled(true);
                                                                                        unfriendBtn.setOnClickListener(new View.OnClickListener() {
                                                                                            @Override
                                                                                            public void onClick(View v) {
                                                                                                unfriend(visit_key, currentUserId);
                                                                                            }
                                                                                        });

                                                                                        //mhomeBtn.setVisibility(View.VISIBLE);
                                                                                        //mhomeBtn.setEnabled(true);
                                                                                        //muserRequest.setVisibility(View.VISIBLE);
                                                                                        //muserRequest.setEnabled(true);
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

    private void cancelRequest(final String visit_key, final String currentUserId) {
        final DatabaseReference reqRef = FirebaseDatabase.getInstance().getReference("FriendsRequests");
        reqRef.child(currentUserId).child(visit_key)
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            reqRef.child(visit_key).child(currentUserId).removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                cancelRequestBtn.setVisibility(View.INVISIBLE);
                                                cancelRequestBtn.setEnabled(false);
                                                acceptRequestBtn.setVisibility(View.INVISIBLE);
                                                acceptRequestBtn.setEnabled(false);
                                                unfriendBtn.setVisibility(View.INVISIBLE);
                                                unfriendBtn.setEnabled(false);
                                                sendRequestBtn.setVisibility(View.VISIBLE);
                                                sendRequestBtn.setEnabled(true);
                                                Toast.makeText(userProfile.this, "friends Request canceled", Toast.LENGTH_SHORT).show();
                                                sendRequestBtn.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        sendRequest(visit_key, currentUserId);
                                                    }
                                                });

                                                // macceptBtnProfile.setVisibility(View.INVISIBLE);
                                                //macceptBtnProfile.setEnabled(false);
                                                //mhomeBtn.setVisibility(View.VISIBLE);
                                                //mhomeBtn.setEnabled(true);
                                                //muserRequest.setVisibility(View.VISIBLE);
                                                //muserRequest.setEnabled(true);

                                            }
                                        }
                                    });
                        }
                    }
                });
    }

    private void sendRequest(final String visit_key, final String currentUserId) {
        final DatabaseReference reqRef = FirebaseDatabase.getInstance().getReference("FriendsRequests");
        reqRef.child(visit_key).child(currentUserId)
                .setValue("sent")
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            reqRef.child(currentUserId).child(visit_key).setValue("received")
                                    .addOnCompleteListener(new OnCompleteListener<Void>()
                                    {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task)
                                        {
                                            if(task.isSuccessful())
                                            {
                                                //msignUpBtn.setEnabled(true);
                                                sendRequestBtn.setVisibility(View.GONE);
                                                sendRequestBtn.setEnabled(false);
                                                acceptRequestBtn.setVisibility(View.INVISIBLE);
                                                acceptRequestBtn.setEnabled(false);
                                                unfriendBtn.setVisibility(View.INVISIBLE);
                                                unfriendBtn.setEnabled(false);
                                                cancelRequestBtn.setVisibility(View.VISIBLE);
                                                cancelRequestBtn.setEnabled(true);

                                                Toast.makeText(userProfile.this, "Request sent", Toast.LENGTH_SHORT).show();
                                                cancelRequestBtn.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        cancelRequest(visit_key, currentUserId);
                                                    }
                                                });
                                                //CURRENT_STATE = "request_sent";
                                                //SendFriendReqButton.setText("Cancel friend Request");

                                               // msignUpBtn.setVisibility(View.INVISIBLE);
                                                //msignUpBtn.setEnabled(false);

                                               // mbackToLogin.setVisibility(View.VISIBLE);
                                                //mbackToLogin.setEnabled(true);


                                            }
                                        }
                                    });
                        }
                    }
                });

    }


    public void backbb(View view) {

        onBackPressed();
    }

}


