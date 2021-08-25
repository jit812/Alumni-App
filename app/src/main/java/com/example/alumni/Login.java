package com.example.alumni;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText musernameLogin, mpasswordLogin;
    TextView mforgotPassword, mcreateAccount;
     Button mloginBtn;
     CheckBox mcheckBoxShowPass;
    Spinner mspinnerLogin;
    ProgressBar mprogressBarLogin;
    TextView logo;
    ConstraintLayout cl;
    private Animation slideUps;
    public FirebaseAuth mAuth;
    public DatabaseReference dbRef, adminRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cl=findViewById(R.id.constraintLayout);
        logo=findViewById(R.id.logoimg);
        slideUps = AnimationUtils.loadAnimation(this, R.anim.slideups);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.Orng));
        musernameLogin = findViewById(R.id.usernameLogin);
        mpasswordLogin = findViewById(R.id.passwordLogin);
        mforgotPassword = findViewById(R.id.forgotPassword);
        mcreateAccount = findViewById(R.id.createAccount);
        mloginBtn = findViewById(R.id.loginBtn);
        mspinnerLogin = findViewById(R.id.spinnerLogin);
        mprogressBarLogin = findViewById(R.id.progressBarLogin);
        mcheckBoxShowPass = findViewById(R.id.checkBoxShowPass);

        mAuth = FirebaseAuth.getInstance();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinnerLogin, R.layout.support_simple_spinner_dropdown_item);
        mspinnerLogin.setAdapter(adapter);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Users");
        adminRef = FirebaseDatabase.getInstance().getReference().child("Admin");

        mcheckBoxShowPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    //show password
                    mpasswordLogin.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }else {
                    mpasswordLogin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        mloginBtn.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             final String username = musernameLogin.getText().toString();
                                             final String pass = mpasswordLogin.getText().toString();
                                             final String item = mspinnerLogin.getSelectedItem().toString();

                                             mprogressBarLogin.setVisibility(View.VISIBLE);
                                             mprogressBarLogin.setEnabled(true);

                                             if (item.equals("Admin")) {

                                                 adminRef.orderByChild("email").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
                                                     @Override
                                                     public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                         if (snapshot.exists()) {
                                                             adminRef.orderByChild("password").equalTo(pass).addListenerForSingleValueEvent(new ValueEventListener() {
                                                                 @Override
                                                                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                     if (snapshot.exists()) {

                                                                         mAuth.signInWithEmailAndPassword(username, pass)
                                                                                 .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                                                                     @Override
                                                                                     public void onComplete(@NonNull Task<AuthResult> task) {
                                                                                         if (task.isSuccessful()) {
                                                                                             if (item.equals("Admin")) {
                                                                                                 mprogressBarLogin.setVisibility(View.GONE);
                                                                                                 Intent intent = new Intent(Login.this, AdminDashboard.class);
                                                                                                 startActivity(intent);
                                                                                                 finish();
                                                                                             } else {
                                                                                                 Toast.makeText(com.example.alumni.Login.this, "Please choose Admin", Toast.LENGTH_SHORT).show();
                                                                                             }
                                                                                         } else {
                                                                                             // there was an error
                                                                                             Toast.makeText(Login.this, "Authentication failed." + task.getException(),
                                                                                                     Toast.LENGTH_LONG).show();
                                                                                             Log.e("MyTag", task.getException().toString());

                                                                                         }
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

                                                     @Override
                                                     public void onCancelled(@NonNull DatabaseError error) {

                                                     }
                                                 });
                                             }


                                             else if (item.equals("User")){

                                                 LoginUser();

                                             }
                                             else{
                                                 Toast.makeText(com.example.alumni.Login.this, "Please check your credentials again", Toast.LENGTH_SHORT).show();
                                             }
                                         }
                                     }


        );



        mcreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(com.example.alumni.Login.this, Register.class);
                startActivity(intent);
            }
        });

        mforgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(com.example.alumni.Login.this, com.example.alumni.ForgotPassword.class);
                startActivity(intent);
            }
        });

    }

    private void LoginUser (){
        final String username1 = musernameLogin.getText().toString();
        final String pass1 = mpasswordLogin.getText().toString();
        mAuth.signInWithEmailAndPassword(username1, pass1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //Toast.makeText(Login.this, "outside if ", Toast.LENGTH_SHORT).show();
                        if (task.isSuccessful()) {
                            final String user_id = mAuth.getCurrentUser().getUid();
                           // Toast.makeText(Login.this, "id " + user_id, Toast.LENGTH_SHORT).show();
                            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users");
                            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot ds : snapshot.getChildren()) {
                                        String new_key = ds.getRef().getKey();
                                       // Toast.makeText(Login.this, "userid " + new_key, Toast.LENGTH_SHORT).show();
                                        if (user_id.equals(new_key)) {

                                           // Toast.makeText(Login.this, "enters if " , Toast.LENGTH_SHORT).show();
                                            //new code here

                                                mprogressBarLogin.setVisibility(View.GONE);
                                                Toast.makeText(Login.this, "Login Successful" , Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(com.example.alumni.Login.this, DashBoard.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                                finish();
                                                //break;


                                            // new code ends
                                        } else {
                                            mprogressBarLogin.setVisibility(View.GONE);
                                            //Toast.makeText(Login.this, "Wait for the admin approval!", Toast.LENGTH_SHORT).show();
                                            //break;
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    mprogressBarLogin.setVisibility(View.GONE);

                                    Toast.makeText(Login.this, "Login Canceled, if you have registered into this app then do verify your email and wait for the admin approval!", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }else {
                            mprogressBarLogin.setVisibility(View.GONE);
                            Toast.makeText(Login.this, "Wrong credentials!, check it once again.", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        logo.startAnimation(slideUps);
        cl.startAnimation(slideUps);
    }
}