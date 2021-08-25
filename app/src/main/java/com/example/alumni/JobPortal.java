package com.example.alumni;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class JobPortal extends AppCompatActivity {
    private Button button;
    private TextView justifythis;
    private EditText Companyname,Designation,jobdes,qualification;
    private Spinner empType,exp;
    private DatabaseReference RootRef;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_portal);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        InitializeFields();
        justifythis=findViewById(R.id.justifythis);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            justifythis.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }
        RootRef= FirebaseDatabase.getInstance().getReference().child("Job Portal");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createNewJob();
                 // SendUserToNextPage();
            }
        });

    }

    /*private void SendUserToNextPage() {
        Intent intent = new Intent(JobPortal.this, JobRetrieveActivity.class);
        startActivity(intent);
    }*/

    private void createNewJob() {
        String cpname=Companyname.getText().toString();
        String emptype=empType.getSelectedItem().toString();
        String ex=exp.getSelectedItem().toString();
        String jdesp=jobdes.getText().toString();
        String desig=Designation.getText().toString();
        String quali=qualification.getText().toString();

        if(TextUtils.isEmpty(cpname) || TextUtils.isEmpty(emptype) || TextUtils.isEmpty(ex) || TextUtils.isEmpty(jdesp)|| TextUtils.isEmpty(desig) || TextUtils.isEmpty(quali))
        {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }

        else {

            String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());


            Jobs job = new Jobs(cpname,desig,jdesp,quali,emptype,ex,date);
            RootRef.push().setValue(job);

            Toast.makeText(JobPortal.this, "Successfully updated", Toast.LENGTH_SHORT).show();


            Companyname.setText("");
            empType.setEnabled(false);
            exp.setEnabled(false);
            jobdes.setText("");
            Designation.setText("");
            qualification.setText("");
        }
    }

    private void InitializeFields() {
        button=(Button) findViewById(R.id.submit);
        Companyname=(EditText) findViewById(R.id.cmpyname);
        empType=(Spinner) findViewById(R.id.spinnerJB);
        String[] emp = getResources().getStringArray(R.array.EmployeeType);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_list, emp);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        empType.setAdapter(arrayAdapter);
        exp=(Spinner) findViewById(R.id.spinnerexp);
        String[] ex = getResources().getStringArray(R.array.Exp);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.spinner_list, ex);
        adapter.setDropDownViewResource(R.layout.spinner_list);
        exp.setAdapter(adapter);
        Designation=(EditText)findViewById(R.id.designation);
        jobdes=(EditText)findViewById(R.id.desp);
        qualification=(EditText)findViewById(R.id.qualification);
    }

    public void ClickBack(View view) {
        redirectActivity(this,DashBoard.class);
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        //Initialize intent
        Intent intent = new Intent(activity,aClass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //start activity
        activity.startActivity(intent);
    }

    public void backbb(View view) {
        onBackPressed();
    }
}