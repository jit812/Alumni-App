package com.example.alumni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResetPassword extends AppCompatActivity {

    EditText moldPassReset, mnewPassReset, mconfirmPassReset;
    TextView mtryOtherWay ,mresetPassTitle;
    Button mresetBtn;
    ProgressBar mprogressBarReset;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        mresetPassTitle = findViewById(R.id.resetPassTitle);
        mtryOtherWay = findViewById(R.id.tryOtherWay);
        moldPassReset = findViewById(R.id.oldPassReset);
        mnewPassReset = findViewById(R.id.newPassReset);
        mconfirmPassReset = findViewById(R.id.confirmPassReset);
        mresetBtn = findViewById(R.id.resetBtn);
        mprogressBarReset = findViewById(R.id.progressBarReset);

        mAuth = FirebaseAuth.getInstance();

        mtryOtherWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.alumni.ResetPassword.this, ForgotPassword.class);
                startActivity(intent);
                finish();
            }
        });

        mresetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();

            }

            private void resetPassword() {
                String currentPass = moldPassReset.getText().toString().trim();
                final String newPass = mnewPassReset.getText().toString().trim();
                String conPass = mconfirmPassReset.getText().toString().trim();


                if (!TextUtils.isEmpty(currentPass)) {
                    if (!TextUtils.isEmpty(newPass)) {
                        if (!TextUtils.isEmpty(conPass)) {
                            if (newPass.equals(conPass)) {
                                mprogressBarReset.setVisibility(View.VISIBLE);
                                mprogressBarReset.setEnabled(true);
                                final FirebaseUser user = mAuth.getCurrentUser();
                                if ((user != null) && (user.getEmail() != null)) {
                                    AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), newPass);
                                    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            user.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        mprogressBarReset.setVisibility(View.GONE);

                                                        final String user_id = mAuth.getCurrentUser().getUid();
                                                        DatabaseReference regRef = FirebaseDatabase.getInstance().getReference("Register");
                                                        regRef.child(user_id).child("email").setValue(newPass);
                                                        Toast.makeText(ResetPassword.this, "User password updated successfully.", Toast.LENGTH_SHORT).show();
                                                        mAuth.signOut();
                                                        Intent intent = new Intent(com.example.alumni.ResetPassword.this, Login.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }else {
                                                        mprogressBarReset.setVisibility(View.GONE);

                                                        Toast.makeText(ResetPassword.this, "User password update is failed", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }
                                    });
                                }
                            } else {

                                Toast.makeText(ResetPassword.this, "Password mismatch!", Toast.LENGTH_SHORT).show();
                            }
                        } else {

                            Toast.makeText(ResetPassword.this, "Confirm password field is empty!, Fill it to proceed", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        Toast.makeText(ResetPassword.this, "new password field is empty!, Fill it to proceed", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(ResetPassword.this, "Fields are empty!, fill it to proceed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}