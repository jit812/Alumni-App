package com.example.alumni;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangePassword extends AppCompatActivity {

    EditText mnewPass, mconPass;
    Button mupdateBtn;
    FirebaseAuth mAuth;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        mnewPass = findViewById(R.id.newPass);
        mconPass = findViewById(R.id.conPass);
        mupdateBtn = findViewById(R.id.updateBtn);


        dbRef = FirebaseDatabase.getInstance().getReference().child("UsersData");
        final String newPass = mnewPass.getText().toString().trim();
        final String conPass = mconPass.getText().toString().trim();

        mupdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (newPass!= null){
                    if (conPass!= null){
                        if (newPass == conPass){

                            dbRef.child("srn").child("phone").setValue(newPass);
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                            finish(); 

                        }else {
                            Toast.makeText(ChangePassword.this, "Password missmatch!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(ChangePassword.this, "Confirm password field is empty, Please fill it", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(ChangePassword.this,"New password field is empty, Please fill it", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}