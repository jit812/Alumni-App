package com.example.alumni;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class support extends AppCompatActivity {


    private EditText ssrn,transactionId,syearofgraduation;
    private Spinner sbranch,sdocuments;
    private Button sbtnsubmit;

    DatabaseReference supportDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);



        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        ssrn = findViewById(R.id.srn);
        transactionId = findViewById(R.id.transaction_id);
        syearofgraduation = findViewById(R.id.year_of_graduation);
        sbranch = findViewById(R.id.spinnerSu);
        sdocuments = findViewById(R.id.required_documents);
        String[] school = getResources().getStringArray(R.array.Schools);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_list, school);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        sbranch.setAdapter(arrayAdapter);



        sdocuments = findViewById(R.id.required_documents);
        String[] documents = getResources().getStringArray(R.array.documents);
        ArrayAdapter<String> arraAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_list, documents);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        sdocuments.setAdapter(arraAdapter);


        sbtnsubmit = findViewById(R.id.ssubmit);

        supportDb = FirebaseDatabase.getInstance().getReference().child("support");

        sbtnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertSupportData();
                //   sendUser();

            }
        });

    }

    private void validateEmailAddress(EditText semail) {
    }


    private void insertSupportData() {

       String transaction = transactionId.getText().toString();
        String srn = ssrn.getText().toString();
        String yearofgraduation = syearofgraduation.getText().toString();
        String branch = sbranch.getSelectedItem().toString();
        String document = sdocuments.getSelectedItem().toString();

        String checked="0";
        if(TextUtils.isEmpty(srn) ||  TextUtils.isEmpty(transaction) || TextUtils.isEmpty(yearofgraduation) || TextUtils.isEmpty(branch) || TextUtils.isEmpty(document))
        {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }

        else {

            Suppots suppots = new Suppots(srn,transaction, yearofgraduation, branch, document,checked);
            supportDb.child(checked).child(document).child(transaction).setValue(suppots);

            Toast.makeText(support.this, "Request Sent!", Toast.LENGTH_SHORT).show();

            transactionId.setText("");
            ssrn.setText("");
            syearofgraduation.setText("");
            sbranch.setEnabled(false);
            sdocuments.setEnabled(false);
            Intent intent = new Intent(support.this,upload.class);
            startActivity(intent);
        }

    }
 /*   private void sendUser() {
        Intent intent = new Intent(support.this,admin_support.class);
        startActivity(intent);
    }*/

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
        redirectActivity(this,support_fee_details.class);
    }

    public void clickdash(View view) {

        Intent i=new Intent(getApplicationContext(),DashBoard.class);
        startActivity(i);

    }
}