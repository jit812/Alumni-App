package com.example.alumni;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class Register extends AppCompatActivity {

    private static final String TAG = "TAG";

    TextView msignUpTitle, mschoolEdit;
    EditText mfullNameSignUp, msrnSignUp, memailSignUp, mphoneSignUp, mpasswordSignUp, mrePassword, myearPassed, merrorView;
    Button msaveBtn, msignUpBtn, mbackToLogin;
    ProgressBar mprogressBarRegister;
    FirebaseAuth fAuth;
    CheckBox mcheckBoxRegister;
    public FirebaseAuth mAuth;
    Spinner mschoolsListSpinner;

    private FirebaseAuth.AuthStateListener authStateListener;
/*
    int year;
    int month;
    int day;

 */

    private DatabaseReference registerRef, requestRef;
    private String senderUserId, CURRENT_STATE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        msignUpTitle = findViewById(R.id.signUpTitle);
        mfullNameSignUp = findViewById(R.id.fullNameSignUp);
        msrnSignUp = findViewById(R.id.srnSignUp);
        memailSignUp = findViewById(R.id.emailSignUp);
        mphoneSignUp = findViewById(R.id.phoneSignUp);
        mpasswordSignUp = findViewById(R.id.passwordSignUp);
        mrePassword = findViewById(R.id.rePassword);
        myearPassed = findViewById(R.id.yearPassed);
        msaveBtn = findViewById(R.id.saveBtn);
        mprogressBarRegister = findViewById(R.id.progressBarRegister);
        mcheckBoxRegister = findViewById(R.id.checkBoxRegister);
        merrorView = findViewById(R.id.errorView);
        msignUpBtn = findViewById(R.id.signUpBtn);
        mbackToLogin = findViewById(R.id.backToLogin);
        mschoolEdit = findViewById(R.id.schoolEdit);
        mschoolsListSpinner = findViewById(R.id.schoolsListSpinner);


        String[] school = getResources().getStringArray(R.array.Schools);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this,
                R.layout.spinner_list, school);
        arrayAdapter1.setDropDownViewResource(R.layout.spinner_list);


        fAuth = FirebaseAuth.getInstance();

      //  final Calendar calendar = Calendar.getInstance();

        registerRef = FirebaseDatabase.getInstance().getReference().child("Register");
        requestRef = FirebaseDatabase.getInstance().getReference().child("Requests");
        // usersRef = FirebaseDatabase.getInstance().getReference().child("Users");

   mbackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.alumni.Register.this, com.example.alumni.Login.class);
                startActivity(intent);
            }
        });


        msignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signInWithEmailAndPassword((memailSignUp.getText().toString()), (mpasswordSignUp.getText().toString()))
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){
                                    FirebaseUser user = fAuth.getCurrentUser();
                                    if (user != null) {
                                        boolean emailVerified = user.isEmailVerified();
                                        if (emailVerified == true){
                                            SendRequestToAdmin();
                                        }else {
                                            Toast.makeText(Register.this, "Do verify your email id to process further", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        });



            }

            private void SendRequestToAdmin()
            {

                senderUserId = fAuth.getCurrentUser().getUid();
                requestRef.child(senderUserId).child("S87fylxtp3cEf7ZRXvLhC6E9xQu1")
                        .setValue("sent")
                        .addOnCompleteListener(new OnCompleteListener<Void>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                if(task.isSuccessful())
                                {
                                    requestRef.child("S87fylxtp3cEf7ZRXvLhC6E9xQu1").child(senderUserId).setValue("received")
                                            .addOnCompleteListener(new OnCompleteListener<Void>()
                                            {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task)
                                                {
                                                    if(task.isSuccessful())
                                                    {
                                                        DatabaseReference regRef = FirebaseDatabase.getInstance().getReference("Register").child(senderUserId);
                                                        regRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (snapshot.exists()){
                                                                    String schoolname = snapshot.child("school").getValue().toString();
                                                                    Toast.makeText(Register.this, "school:" +schoolname, Toast.LENGTH_SHORT).show();
                                                                    if (schoolname.contains("School of CSA")){
                                                                        requestRef.child(senderUserId).child("U1rKePSC6jM7UU4f76XZOeLXzDb2")
                                                                                .setValue("sent")
                                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<Void> task) {

                                                                                        if (task.isSuccessful()){
                                                                                            requestRef.child("U1rKePSC6jM7UU4f76XZOeLXzDb2").child(senderUserId).setValue("received")
                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                        @Override
                                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                                            if(task.isSuccessful()){
                                                                                                                msignUpBtn.setEnabled(true);

                                                                                                                Toast.makeText(Register.this, "Thanks for registering, Wait for admin approval!", Toast.LENGTH_SHORT).show();
                                                                                                                CURRENT_STATE = "request_sent";
                                                                                                                //SendFriendReqButton.setText("Cancel friend Request");

                                                                                                                msignUpBtn.setVisibility(View.INVISIBLE);
                                                                                                                msignUpBtn.setEnabled(false);

                                                                                                                mbackToLogin.setVisibility(View.VISIBLE);
                                                                                                                mbackToLogin.setEnabled(true);
                                                                                                            }
                                                                                                        }
                                                                                                    });
                                                                                        }
                                                                                    }
                                                                                });
                                                                    } else {

                                                                        requestRef.child(senderUserId).child("jdZY0dfI5ua9Zpq4Dmtm9iPtOn83")
                                                                                .setValue("sent")
                                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<Void> task) {

                                                                                        if (task.isSuccessful()){
                                                                                            requestRef.child("jdZY0dfI5ua9Zpq4Dmtm9iPtOn83").child(senderUserId).setValue("received")
                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                        @Override
                                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                                            if(task.isSuccessful()){
                                                                                                                msignUpBtn.setEnabled(true);

                                                                                                                Toast.makeText(Register.this, "Thanks for registering, Wait for admin approval!", Toast.LENGTH_SHORT).show();
                                                                                                                CURRENT_STATE = "request_sent";
                                                                                                                //SendFriendReqButton.setText("Cancel friend Request");

                                                                                                                msignUpBtn.setVisibility(View.INVISIBLE);
                                                                                                                msignUpBtn.setEnabled(false);

                                                                                                                mbackToLogin.setVisibility(View.VISIBLE);
                                                                                                                mbackToLogin.setEnabled(true);
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
                                                        msignUpBtn.setEnabled(true);

                                                        Toast.makeText(Register.this, "Thanks for registering, Verify your email and wait for admin approval!", Toast.LENGTH_SHORT).show();
                                                        CURRENT_STATE = "request_sent";
                                                        //SendFriendReqButton.setText("Cancel friend Request");

                                                        msignUpBtn.setVisibility(View.INVISIBLE);
                                                        msignUpBtn.setEnabled(false);

                                                        mbackToLogin.setVisibility(View.VISIBLE);
                                                        mbackToLogin.setEnabled(true);


                                                    }
                                                }
                                            });
                                }
                            }
                        });


            }

        });


        msaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullNameSignUp = mfullNameSignUp.getText().toString().trim();
                final String srnNum = msrnSignUp.getText().toString().trim();
                final String email = memailSignUp.getText().toString().trim();
                final String password = mpasswordSignUp.getText().toString().trim();
                String rePassword = mrePassword.getText().toString().trim();
                final String yearPassed = myearPassed.getText().toString();
                final String PhoneNum = mphoneSignUp.getText().toString();
                final String schoolname = mschoolsListSpinner.getSelectedItem().toString();



                if (TextUtils.isEmpty(fullNameSignUp)) {
                    mfullNameSignUp.setError("Full Name is Required");
                    return;
                }


                if (TextUtils.isEmpty(srnNum)) {
                    msrnSignUp.setError("Srn is Required");
                    return;
                }

                if (TextUtils.isEmpty(PhoneNum)) {
                    mphoneSignUp.setError("Phone is Required");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    memailSignUp.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mpasswordSignUp.setError("Password is Required");
                    return;
                }
                if (TextUtils.isEmpty(schoolname))
                {
                    mrePassword.setError("School name is Required to confirm");
                    return;
                }
                if (!rePassword.equals(password)) {
                    Toast.makeText(com.example.alumni.Register.this, "Password do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    mpasswordSignUp.setError("Password must be >= 6 Characters");
                    return;
                }
                if (TextUtils.isEmpty(rePassword))
                {
                    mrePassword.setError("Password is Required to confirm");
                    return;
                }


                if (!mcheckBoxRegister.isChecked()) {
                    mcheckBoxRegister.setError("Check the box to Register");
                    return;

                }
                if (TextUtils.isEmpty(yearPassed)){
                    myearPassed.setError("Date is Required");
                    return;
                }

                else {

                    fAuth.createUserWithEmailAndPassword(memailSignUp.getText().toString(), mpasswordSignUp.getText().toString()).addOnCompleteListener(com.example.alumni.Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");



                                FirebaseUser user = fAuth.getCurrentUser();

                                try {
                                    if (user != null)
                                        user.sendEmailVerification()
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Log.d(TAG, "Email sent.");
/*
                                                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                                                    Register.this);

                                                            // set title
                                                            alertDialogBuilder.setTitle("Please Verify Your EmailID");

                                                            // set dialog message
                                                            alertDialogBuilder
                                                                    .setMessage("A verification Email Is Sent To Your Registered EmailID, please click on the link and Sign in again!")
                                                                    .setCancelable(false)
                                                                    .setPositiveButton("Sign In", new DialogInterface.OnClickListener() {
                                                                        public void onClick(DialogInterface dialog, int id) {

                                                                            Intent signInIntent = new Intent(Register.this, Login.class);
                                                                            Register.this.finish();
                                                                        }
                                                                    });

                                                            // create alert dialog
                                                            AlertDialog alertDialog = alertDialogBuilder.create();

                                                            // show it
                                                            alertDialog.show();


 */

                                                        }
                                                    }
                                                });

                                } catch (Exception e) {
                                    merrorView.setText(e.getMessage());
                                }

                                String fieldsvalue = "";

                                Map map = new HashMap();
                                map.put("fullname", fullNameSignUp);
                                map.put("srn", srnNum);
                                map.put("email", email);
                                map.put("phone", PhoneNum);
                                map.put("password", password);
                                map.put("passedyear", yearPassed);
                                map.put("school", schoolname);
                                map.put("course", fieldsvalue);
                                map.put("dob", fieldsvalue);
                                map.put("profileinterest", fieldsvalue);
                                map.put("domaininterest", fieldsvalue);
                                map.put("skillset", fieldsvalue);
                                map.put("address", fieldsvalue);
                                map.put("pincode", fieldsvalue);
                                map.put("fathername", fieldsvalue);
                                map.put("bloodgroup", fieldsvalue);
                                map.put("branch", fieldsvalue);
                               // map.put("ug", fieldsvalue);
                               // map.put("pg", fieldsvalue);
                                map.put("companyname", fieldsvalue);
                                map.put("domain", fieldsvalue);
                                map.put("designation", fieldsvalue);




                                final String key = srnNum;
                                registerRef.child(fAuth.getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        mprogressBarRegister.setVisibility(View.VISIBLE);
                                        if (task.isSuccessful()){

                                            Toast.makeText(getApplicationContext(), "Added successfully, do validate your email id!", Toast.LENGTH_LONG).show();
                                            mprogressBarRegister.setVisibility(View.GONE);
                                            msaveBtn.setVisibility(View.GONE);
                                            msaveBtn.setEnabled(false);
                                            msignUpBtn.setVisibility(View.VISIBLE);
                                            msignUpBtn.setEnabled(true);



                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(), "Not Added successfully, check all the fields once again!", Toast.LENGTH_LONG).show();
                                        }

                                    }
                                });





                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(com.example.alumni.Register.this, "Authentication failed. Email ID already exists!",
                                        Toast.LENGTH_SHORT).show();



                                if (task.getException() != null) {
                                    merrorView.setText(task.getException().getMessage());
                                }

                            }

                        }
                    });

                }

            }



        });





    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = fAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }
    // [END on_start_check_user]



}