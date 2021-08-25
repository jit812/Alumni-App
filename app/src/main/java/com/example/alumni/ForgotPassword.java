package com.example.alumni;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class ForgotPassword extends AppCompatActivity {


    EditText memailForgot;
    Button msendMailBtn;
    ProgressBar mprogressBarForgot;


    public FirebaseAuth mAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        memailForgot = findViewById(R.id.emailForgot);
        memailForgot = findViewById(R.id.emailForgot);
        msendMailBtn = findViewById(R.id.sendMailBtn);
        mprogressBarForgot = findViewById(R.id.progressBarForgot);


        mAuth = FirebaseAuth.getInstance();
        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Register");



        msendMailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailAddress = memailForgot.getText().toString();

                dbRef.orderByChild("email").equalTo(emailAddress).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        sendPasswordReset(emailAddress);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(ForgotPassword.this, "Please check your entered emailid", Toast.LENGTH_LONG).show();
                    }
                });




            }
        });


    }

    private void sendPasswordReset(String emailAddress) {


        //new code begins
        if (TextUtils.isEmpty(emailAddress)) {
            Toast.makeText(ForgotPassword.this, "Please write your valid email address first... ", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(ForgotPassword.this, "Please check your Email account, If you want to reset your password... ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ForgotPassword.this, Login.class));
                    } else {
                        String message = task.getException().getMessage();
                        Toast.makeText(ForgotPassword.this, "Error Occured: " + message, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        //new code ends here
    }
        public static void redirectActivity(Activity activity, Class aClass) {
        //Initialize intent
        Intent intent = new Intent(activity,aClass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //start activity
        activity.startActivity(intent);
    }

    public void ClickBack(View view){
        //Redirect activity to dashboard
        redirectActivity(this,Login.class);
    }

}