package com.example.alumni;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;




public class Profile extends AppCompatActivity {

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
    Button frnsBtn, frnsRequestSentBtn, frnsRequestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        final String currentUserId = mAuth.getCurrentUser().getUid();

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
        emll = findViewById(R.id.emll);
        uemll = findViewById(R.id.unemll);
        Edit = findViewById(R.id.Editbutton);
        mgearBtn = findViewById(R.id.gearBtn);
        frnsBtn = findViewById(R.id.frnsBtn);
        frnsRequestSentBtn = findViewById(R.id.frnsRequestSentBtn);
        frnsRequestBtn = findViewById(R.id.frnsRequestBtn);

        frnsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Friends.class);
                startActivity(intent);
            }
        });

        frnsRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, FriendsRequest.class);
                startActivity(intent);
            }
        });

        frnsRequestSentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, FriendsRequestSent.class);
                startActivity(intent);
            }
        });

        courseSprinner = findViewById(R.id.branchList);
        schoolsSpinner = findViewById(R.id.schoolsList);

        String[] school = getResources().getStringArray(R.array.Programs);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_list, school);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        courseSprinner.setAdapter(arrayAdapter);


        CategoryImagesRef = FirebaseStorage.getInstance().getReference().child("User Images");
        CategoryRef = FirebaseDatabase.getInstance().getReference().child("Register").child(currentUserId).child("image");

        progressdialog = new ProgressDialog(Profile.this);
        progressdialog.setMessage("Please Wait");

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.radioem) {

                    emll.setVisibility(View.VISIBLE);
                    uemll.setVisibility(View.GONE);
                    final DatabaseReference regRef = FirebaseDatabase.getInstance().getReference("Register");
                    regRef.orderByKey().equalTo(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {



                               for (DataSnapshot ds:snapshot.getChildren()) {
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
                    regRef.orderByKey().equalTo(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {

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

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();

            }
        });


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);


        final DatabaseReference profileUserRef = FirebaseDatabase.getInstance().getReference().child("Register").child(currentUserId);

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
       // mschoolsEdit = findViewById(R.id.schoolsEdit);


        mjobSeeking = findViewById(R.id.jobSeeking);
        mdesignationText = findViewById(R.id.designationText);
        mdomainText = findViewById(R.id.domainText);
        mcompanyName = findViewById(R.id.companyName);
        mjobprofileInterest = findViewById(R.id.jobprofileInterest);
        mdomainInterested = findViewById(R.id.domainInterested);
        mskillset = findViewById(R.id.skillset);

        mupdateBtn = findViewById(R.id.updateProfileBtn);





        final DatabaseReference regRef = FirebaseDatabase.getInstance().getReference("Register");
        regRef.orderByKey().equalTo(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
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
                        //malumniOf.setText(myug);
                        //mschoolsEdit.setText(schoolname);
                        //c.setText(mypg);

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


/*
                        ruem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (ruem.isChecked()){

                                    String myprofileinterest = ds.child("profileinterest").getValue().toString();
                                    String mydomaininterest = ds.child("domaininterest").getValue().toString();
                                    String myskillset = ds.child("skillset").getValue().toString();

                                    mjobprofileInterest.setText(myprofileinterest);
                                    mdomainInterested.setText(mydomaininterest);
                                    mskillset.setText(myskillset);
                                }
                            }
                        });

 */


                    }
                    else {
                        String myProfileName = ds.child("fullname").getValue().toString();
                        String myemail = ds.child("email").getValue().toString();
                        String myphone = ds.child("phone").getValue().toString();
                        String mysrn = ds.child("srn").getValue().toString();

                        String mybranch = ds.child("branch").getValue().toString();
                        String myYearPassed = ds.child("passedyear").getValue().toString();
                        String coursename = ds.child("course").getValue().toString();
                        String schoolname = ds.child("school").getValue().toString();
                       // String myug = ds.child("ug").getValue().toString();
                        //String mypg = ds.child("pg").getValue().toString();

                        String mydob = ds.child("dob").getValue().toString();
                        String myaddress = ds.child("address").getValue().toString();
                        String mypincode = ds.child("pincode").getValue().toString();
                        String myfathername = ds.child("fathername").getValue().toString();
                        String mybloodgroup = ds.child("bloodgroup").getValue().toString();


                        mfullnameProfileText.setText(myProfileName);
                        memailProfileText.setText(myemail);
                        mphoneProfileText.setText(myphone);
                        msrnProfileText.setText(mysrn);

                       // mbranchProflieText.setText(mybranch);
                        myearPassedText.setText(myYearPassed);
                        mcourseProflieText.setText(coursename);
                       // mschoolsEdit.setText(schoolname);
                        //mugText.setText(myug);
                       // mpgText.setText(mypg);

                        mdobpersonal.setText(mydob);
                        maddresspersonal.setText(myaddress);
                        mpincodepersonal.setText(mypincode);
                        mfathername.setText(myfathername);
                        mbloodgroup.setText(mybloodgroup);

/*
                        rem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (rem.isChecked()){

                                    String mycompanyname = ds.child("companyname").getValue().toString();
                                    String mydomain = ds.child("domain").getValue().toString();
                                    String mydesignation = ds.child("designation").getValue().toString();

                                    mcompanyName.setText(mycompanyname);
                                    mdomainText.setText(mydomain);
                                    mdesignationText.setText(mydesignation);

                                }
                            }
                        });

 */
                        /*

                        ruem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (ruem.isChecked()){


                                    String myprofileinterest = ds.child("profileinterest").getValue().toString();
                                    String mydomaininterest = ds.child("domaininterest").getValue().toString();
                                    String myskillset = ds.child("skillset").getValue().toString();

                                    mjobprofileInterest.setText(myprofileinterest);
                                    mdomainInterested.setText(mydomaininterest);
                                    mskillset.setText(myskillset);
                                }
                            }
                        });

                         */

                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*
        rem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rem.isChecked()){
                    final DatabaseReference regRef = FirebaseDatabase.getInstance().getReference("Register");
                    regRef.orderByKey().equalTo(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (final DataSnapshot ds : snapshot.getChildren()) {


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
            }
        });

         */
/*
        ruem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ruem.isChecked()){
                    final DatabaseReference regRef = FirebaseDatabase.getInstance().getReference("Register");
                    regRef.orderByKey().equalTo(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {

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

 */

        mgearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Profile.this, v);
                //popup.setOnMenuItemClickListener(MainActivity.this);
                popup.inflate(R.menu.settings);
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){

                            case R.id.reset_pass:
                            {
                                Intent intent = new Intent(com.example.alumni.Profile.this, com.example.alumni.ResetPassword.class);
                                startActivity(intent);
                                break;
                            }
                            case R.id.update_email:
                            {
                                updateEmail();
                                break;
                            }

                            case R.id.delete_account:
                            {
                                deleteAccount();
                                break;
                            }
                        }
                        return true;
                    }

                    private void updateEmail() {

                        // get alert_dialog.xml view
                        LayoutInflater li = LayoutInflater.from(getApplicationContext());
                        View promptsView = li.inflate(R.layout.alert_dialog, null);

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                Profile.this);

                        // set alert_dialog.xml to alertdialog builder
                        alertDialogBuilder.setView(promptsView);

                        final EditText userInput = (EditText) promptsView.findViewById(R.id.emailAlert);


                        // set dialog message
                        alertDialogBuilder
                                .setCancelable(false)
                                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
                                        final String emailnew = userInput.getText().toString();
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        final String user_id = mAuth.getCurrentUser().getUid();
                                        user.updateEmail(emailnew).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    DatabaseReference regRef = FirebaseDatabase.getInstance().getReference("Register");

                                                    if (!TextUtils.isEmpty(emailnew)) {
                                                        regRef.child(user_id).child("email").setValue(emailnew);
                                                        mAuth.sendPasswordResetEmail(emailnew).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    Toast.makeText(Profile.this, "Please check your Email account, If you want to reset your password... ", Toast.LENGTH_SHORT).show();
                                                                    startActivity(new Intent(Profile.this, Login.class));
                                                                } else {
                                                                    String message = task.getException().getMessage();
                                                                    Toast.makeText(Profile.this, "Error Occured: " + message, Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                                        Toast.makeText(Profile.this, "Email has been updated successfully. validate it to login!", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            }
                                        });
                                        //Toast.makeText(getApplicationContext(), "Entered: "+userInput.getText().toString(), Toast.LENGTH_LONG).show();
                                    }
                                })
                                .setNegativeButton("Cancel",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });

                        // create alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // show it
                        alertDialog.show();
                    }



                    private void deleteAccount() {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                Profile.this);

                        // set title
                        alertDialogBuilder.setTitle("Delete Account");

                        // set dialog message
                        alertDialogBuilder
                                .setMessage("Are You sure that you want to delete this account?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        final String user_id = mAuth.getCurrentUser().getUid();

                                        FirebaseUser user = mAuth.getCurrentUser();
                                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful()){


                                                    DatabaseReference regRef = FirebaseDatabase.getInstance().getReference("Register");
                                                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users");
                                                    DatabaseReference reqRef = FirebaseDatabase.getInstance().getReference("Requests");
                                                    regRef.child(user_id).removeValue();
                                                    userRef.child(user_id).removeValue();
                                                    reqRef.child(user_id).removeValue();

                                                    Toast.makeText(Profile.this,"User Account deleted!", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(com.example.alumni.Profile.this, Login.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }
                                        });

                                    }
                                })
                                .setNegativeButton("Cancel",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });


                        // create alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // show it
                        alertDialog.show();
                    }
                });

            }
        });



        mupdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressdialog.show();


                if (ImageUri == null) {
                    flag = 1;
                    String fullname = mfullnameProfileText.getText().toString();
                   // String email = memailProfileText.getText().toString();
                    String phone = mphoneProfileText.getText().toString();

                    // String mysrn = msrnProfileText.getText().toString();
                    String mycource = courseSprinner.getSelectedItem().toString();
                    String myYearPassed = myearPassedText.getText().toString();
                    String myschool = schoolsSpinner.getSelectedItem().toString();
                   // String myug = mugText.getText().toString();
                   // String mypg = mpgText.getText().toString();

                    String mydob = mdobpersonal.getText().toString();
                    String myaddress = maddresspersonal.getText().toString();
                    String mypincode = mpincodepersonal.getText().toString();
                    String myfathername = mfathername.getText().toString();
                    String mybloodgroup = mbloodgroup.getText().toString();

                    String mycompanyname = mcompanyName.getText().toString();
                    String mydomain = mdomainText.getText().toString();
                    String mydesignation = mdesignationText.getText().toString();

                    String myprofileinterest = mjobprofileInterest.getText().toString();
                    String mydomaininterest = mdomainInterested.getText().toString();
                    String myskillset = mskillset.getText().toString();


                    HashMap hashMap = new HashMap();
                    hashMap.put("fullname", fullname);
                    //hashMap.put("email", email);
                    hashMap.put("phone", phone);
                    //hashMap.put("srn", mysrn);
                    hashMap.put("course", mycource);
                    hashMap.put("passedyear", myYearPassed);
                    hashMap.put("school", myschool);
                   // hashMap.put("pg", mypg);
                    hashMap.put("dob", mydob);
                    hashMap.put("address", myaddress);
                    hashMap.put("pincode", mypincode);
                    hashMap.put("fathername", myfathername);
                    hashMap.put("bloodgroup", mybloodgroup);

                    hashMap.put("companyname", mycompanyname);
                    hashMap.put("domain", mydomain);
                    hashMap.put("designation", mydesignation);

                    hashMap.put("profileinterest", myprofileinterest);
                    hashMap.put("domaininterest", mydomaininterest);
                    hashMap.put("skillset", myskillset);


                    final DatabaseReference regRef = FirebaseDatabase.getInstance().getReference("Register");
                    regRef.orderByKey().equalTo(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {



                            for (DataSnapshot ds:snapshot.getChildren()) {
                                String mycouse = ds.child("course").getValue().toString();

                                mcourseProflieText.setText(mycouse);

                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    /*
                    rem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (rem.isChecked()){


                                String mycompanyname = mcompanyName.getText().toString();
                                String mydomain = mdomainText.getText().toString();
                                String mydesignation = mdesignationText.getText().toString();

                                HashMap hashMap = new HashMap();
                                hashMap.put("companyname", mycompanyname);
                                hashMap.put("domain", mydomain);
                                hashMap.put("designation", mydesignation);

                            }
                        }
                    });

                    ruem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (ruem.isChecked()){



                                String myprofileinterest = mjobprofileInterest.getText().toString();
                                String mydomaininterest = mdomainInterested.getText().toString();
                                String myskillset = mskillset.getText().toString();

                                HashMap hashMap = new HashMap();
                                hashMap.put("profileinterest", myprofileinterest);
                                hashMap.put("domaininterest", mydomaininterest);
                                hashMap.put("skillset", myskillset);
                            }
                        }
                    });

                     */


                    //final DatabaseReference regRef = FirebaseDatabase.getInstance().getReference("Register");


                    regRef.child(currentUserId).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {

//                            Toast.makeText(Profile.this, "Your data has been successfully updated", Toast.LENGTH_SHORT).show();
                            progressdialog.dismiss();
                        }
                    });
                }
                else {
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat currentDate = new SimpleDateFormat("MMMddyy");
                    savedate = currentDate.format(calendar.getTime());

                    SimpleDateFormat currentTime = new SimpleDateFormat("HHmmss");
                    savetime = currentTime.format(calendar.getTime());
                    categoryRandomKey = savedate + savetime;


                    final StorageReference filepath = CategoryImagesRef.child(ImageUri.getLastPathSegment() + categoryRandomKey + ".jpg");

                    final UploadTask uploadTask = filepath.putFile(ImageUri);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            String message = e.toString();
                            Toast.makeText(getApplicationContext(), "Error" + message, Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                @Override
                                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {


                                    if (!task.isSuccessful()) {
                                        throw task.getException();

                                    }

                                    downloadImageUrl = filepath.getDownloadUrl().toString();
                                    return filepath.getDownloadUrl();

                                }
                            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {

                                    if (task.isSuccessful()) {

                                        downloadImageUrl = task.getResult().toString();
                                        String profileinterest = mjobprofileInterest.getText().toString();
                                        String domaininterest = mdomainInterested.getText().toString();
                                        String skillset = mskillset.getText().toString();

                                        String fullname = mfullnameProfileText.getText().toString();
                                        //String email = memailProfileText.getText().toString();
                                        String phone = mphoneProfileText.getText().toString();
                                       // String srnacademic = msrnProfileText.getText().toString();
                                       // String mycource = courseSprinner.getSelectedItem().toString();
                                       // String myschool = schoolsSpinner.getSelectedItem().toString();
                                        String myaddress = maddresspersonal.getText().toString();
                                        String mypincode = mphoneProfileText.getText().toString();
                                        String myfathername = mfathername.getText().toString();
                                        String mybloodgroup = mbloodgroup.getText().toString();
                                        String mydesignation = mdesignationText.getText().toString();
                                        String mydomain = mdomainText.getText().toString();
                                        String mycompanyname = mcompanyName.getText().toString();

                                        String dobpersonal = mdobpersonal.getText().toString();
                                        String branchacademic = maddresspersonal.getText().toString();
                                        String passedyearacademic = mpincodepersonal.getText().toString();
                                       // String ugacademic = mfathername.getText().toString();
                                       // String pgacademic = mbloodgroup.getText().toString();
                                        String coursename = courseSprinner.getSelectedItem().toString();
                                        String schoolname = schoolsSpinner.getSelectedItem().toString();


                                        HashMap hashMap = new HashMap();
                                        hashMap.put("fullname", fullname);
                                       // hashMap.put("email", email);
                                        hashMap.put("phone", phone);
                                        hashMap.put("profileinterest", profileinterest);
                                        hashMap.put("domaininterest", domaininterest);
                                        hashMap.put("skillset", skillset);
                                        hashMap.put("dob", dobpersonal);
                                        hashMap.put("address", myaddress);
                                        hashMap.put("pincode", mypincode);
                                        hashMap.put("fathername", myfathername);
                                        hashMap.put("bloodgroup", mybloodgroup);
                                        //hashMap.put("school", schoolname);
                                        hashMap.put("course", coursename);
                                        hashMap.put("passedyear", passedyearacademic);
                                        hashMap.put("cource", coursename);
                                        hashMap.put("school", schoolname);
                                        hashMap.put("designation", mydesignation);
                                        hashMap.put("domain", mydomain);
                                        hashMap.put("companyname", mycompanyname);
                                        hashMap.put("image", downloadImageUrl);
                                        progressdialog.dismiss();

                                        profileUserRef.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                            @Override
                                            public void onSuccess(Object o) {

                                                Toast.makeText(Profile.this, "Your data has been successfully updated", Toast.LENGTH_SHORT).show();
                                            }
                                        });


                                    }
                                }
                            });

                        }
                    });

                }

                Edit.setVisibility(View.VISIBLE);
                mupdateBtn.setVisibility(View.GONE);
                mjobSeeking.setEnabled(false);
                mdomainInterested.setEnabled(false);
                mjobprofileInterest.setEnabled(false);
                mskillset.setEnabled(false );
                mdesignationText.setEnabled(false);
                mdomainText.setEnabled(false);
                mcompanyName.setEnabled(false);
                mfullnameProfileText.setEnabled(false);
                memailProfileText.setEnabled(false);
                mphoneProfileText.setEnabled(false);
                mdobpersonal.setEnabled(false);
                maddresspersonal.setEnabled(false);
                mpincodepersonal.setEnabled(false  );
                mfathername.setEnabled(false);
                mbloodgroup.setEnabled(false);
                msrnProfileText.setEnabled(false);
                mcourseProflieText.setEnabled(false);
                myearPassedText.setEnabled(false);
               // mugText.setEnabled(false);
                //malumniOf.setEnabled(false);
                mjobSeeking.setEnabled(false);
                mdesignationText.setEnabled(false);
                mdomainText.setEnabled(false);
                mcompanyName.setEnabled(false);
                mjobprofileInterest.setEnabled(false);
                    mdomainInterested.setEnabled(false);
                mskillset.setEnabled(false);
                }
        });


        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mupdateBtn.setVisibility(View.VISIBLE);
                Edit.setVisibility(View.GONE);
                mfullnameProfileText.setEnabled(true);
                memailProfileText.setEnabled(false);
                mphoneProfileText.setEnabled(true);
                mdobpersonal.setEnabled(true);
                maddresspersonal.setEnabled(true);
                mpincodepersonal.setEnabled(true);
                mfathername.setEnabled(true);
                mbloodgroup.setEnabled(true);
                msrnProfileText.setEnabled(true);
                mcourseProflieText.setEnabled(true);
                myearPassedText.setEnabled(true);
                //mschoolsEdit.setEnabled(false);
                //malumniOf.setEnabled(false);
                mjobSeeking.setEnabled(false);
                mdesignationText.setEnabled(true);
                mdomainText.setEnabled(true);
                mcompanyName.setEnabled(true);
                mjobprofileInterest.setEnabled(true);
                mdomainInterested.setEnabled(true);
                mskillset.setEnabled(true);



                rem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (rem.isChecked()){


                            mdesignationText.setEnabled(true);
                            mdomainText.setEnabled(true);
                            mcompanyName.setEnabled(true);

                        }
                    }
                });

                ruem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (ruem.isChecked()){



                            mjobSeeking.setEnabled(false);
                            mdomainInterested.setEnabled(true);
                            mjobprofileInterest.setEnabled(true);
                            mskillset.setEnabled(true);
                        }
                    }
                });


            }
        });

    }



    public void backbb(View view) {

        onBackPressed();
    }

    private void OpenGallery(){

        Intent galleryIntent =new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,GalleryPick);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {



        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GalleryPick && resultCode==RESULT_OK && data!=null)
        {
            ImageUri =data.getData();
            imageView.setImageURI(ImageUri);
        }

    }

}


