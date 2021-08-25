package com.example.alumni;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class AdmissionReferenceActivity extends AppCompatActivity
{

    private Button button;
    private EditText Fullname,Email,phonenumber,dob,address,state,country,pincode,srn,parentno;
    private Spinner course;
    private RadioGroup gender;
    private DatabaseReference RootRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission_reference);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        InitializeFields();
        RootRef= FirebaseDatabase.getInstance().getReference().child("Admission Reference");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createNewReference();
                //  SendUserToNextPage();
            }
        });
    }

 /*   private void SendUserToNextPage()
    {
        Intent intent =new Intent(AdmissionReferenceActivity.this,AdmissionRetrieveActivity.class);
        startActivity(intent);

    }*/


    private void InitializeFields()
    {
        button=(Button) findViewById(R.id.submit);
        Fullname=(EditText) findViewById(R.id.fullname);
        gender=(RadioGroup) findViewById(R.id.radioGender);
        course=(Spinner) findViewById(R.id.spinnerAd);
        String[] school = getResources().getStringArray(R.array.Programs);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_list, school);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        course.setAdapter(arrayAdapter);
        Email=(EditText)findViewById(R.id.others);
        phonenumber=(EditText)findViewById(R.id.phonenumber11);
        dob=(EditText)findViewById(R.id.dob);
        address=(EditText)findViewById(R.id.address);
        state=(EditText)findViewById(R.id.state);
        country=(EditText)findViewById(R.id.country);
        pincode=(EditText)findViewById(R.id.pincode);
        srn=(EditText)findViewById(R.id.srnal);
        parentno=(EditText)findViewById(R.id.phonenumber);
    }

    private void createNewReference()
    {
        String fname=Fullname.getText().toString();
        String cname=course.getSelectedItem().toString();
        String email=Email.getText().toString();
        String phone=phonenumber.getText().toString();
        String DofB=dob.getText().toString();
        String add=address.getText().toString();
        String stat=state.getText().toString();
        String cntry=country.getText().toString();
        String pin=pincode.getText().toString();
        String parent=parentno.getText().toString();
        String srno=srn.getText().toString();
        @SuppressLint("ResourceType") String gend=(gender.getCheckedRadioButtonId()==1)?"Male":"Female";



        if(TextUtils.isEmpty(fname) || TextUtils.isEmpty(gend) || TextUtils.isEmpty(email) || TextUtils.isEmpty(cname)|| TextUtils.isEmpty(phone) || TextUtils.isEmpty(DofB) || TextUtils.isEmpty(add) || TextUtils.isEmpty(stat) || TextUtils.isEmpty(cntry) || TextUtils.isEmpty(pin)||TextUtils.isEmpty(parent)||TextUtils.isEmpty(srno))
        {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }



        else {

            String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

            Reference reference = new Reference(fname,cname, email, phone, DofB, add, stat, cntry, pin, gend,parent,srno,date);
            RootRef.push().setValue(reference);

            Toast.makeText(AdmissionReferenceActivity.this, "Successfully updated", Toast.LENGTH_SHORT).show();


            Fullname.setText("");
            gender.setEnabled(false);
            course.setEnabled(false);
            Email.setText("");
            phonenumber.setText("");
            dob.setText("");
            address.setText("");
            state.setText("");
            country.setText("");
            pincode.setText("");
            parentno.setText("");
            srn.setText("");

        }

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
        redirectActivity(this,DashBoard.class);
    }

    public void backbb(View view) {
        onBackPressed();
    }
}