package com.example.alumni;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class my_score extends AppCompatActivity {

    private Button button;
    private EditText name, srn, topic, suggestions;
    private RadioButton exalu1, vgalu1, galu1, aalu1, palu1, exalu2, vgalu2, galu2, aalu2, palu2, exalu3, vgalu3, galu3, aalu3, palu3, exalu4, vgalu4, galu4, aalu4, palu4, exalu5, vgalu5, galu5, aalu5, palu5;
    int i = 0;
    private Score score;
    private DatabaseReference reference;
    private Spinner s2, s3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_score);

        InitializeFields();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);

        reference = FirebaseDatabase.getInstance().getReference().child("MyScore");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createMyScore();
                redirectActivity(my_score.this,my_score_retrieve.class);
                //   sendToNextPage();

            }
        });


    }

    private void createMyScore() {

        String ex01 = exalu1.getText().toString();
        String vg01 = vgalu1.getText().toString();
        String g01 = galu1.getText().toString();
        String a01 = aalu1.getText().toString();
        String p01 = palu1.getText().toString();
        String ex02 = exalu2.getText().toString();
        String vg02 = vgalu2.getText().toString();
        String g02 = galu2.getText().toString();
        String a02 = aalu2.getText().toString();
        String p02 = palu2.getText().toString();
        String ex03 = exalu3.getText().toString();
        String vg03 = vgalu3.getText().toString();
        String g03 = galu3.getText().toString();
        String a03 = aalu3.getText().toString();
        String p03 = palu3.getText().toString();
        String ex04 = exalu4.getText().toString();
        String vg04 = vgalu4.getText().toString();
        String g04 = galu4.getText().toString();
        String a04 = aalu4.getText().toString();
        String p04 = palu4.getText().toString();
        String ex05 = exalu5.getText().toString();
        String vg05 = vgalu5.getText().toString();
        String g05 = galu5.getText().toString();
        String a05 = aalu5.getText().toString();
        String p05 = palu5.getText().toString();


        score.setSrn(srn.getText().toString());

        score.setName(name.getText().toString());

        score.setTopic(topic.getText().toString());

        score.setSugg(suggestions.getText().toString());

        score.setSchool(s3.getSelectedItem().toString());

        score.setEvent(s2.getSelectedItem().toString());


        if (exalu1.isChecked()) {
            score.setAbout_session(ex01);

        } else if (vgalu1.isChecked()) {
            score.setAbout_session(vg01);

        } else if (galu1.isChecked()) {
            score.setAbout_session(g01);

        } else if (aalu1.isChecked()) {
            score.setAbout_session(a01);

        } else if (palu1.isChecked()) {
            score.setAbout_session(p01);

        }
        if (exalu2.isChecked()) {
            score.setAbout_content(ex02);

        } else if (vgalu2.isChecked()) {
            score.setAbout_content(vg02);

        } else if (galu2.isChecked()) {
            score.setAbout_content(g02);

        } else if (aalu2.isChecked()) {
            score.setAbout_content(a02);

        } else if (palu2.isChecked()) {
            score.setAbout_content(p02);

        }
        if (exalu3.isChecked()) {
            score.setAbout_useful(ex03);

        } else if (vgalu3.isChecked()) {
            score.setAbout_useful(vg03);

        } else if (galu3.isChecked()) {
            score.setAbout_useful(g03);

        } else if (aalu3.isChecked()) {
            score.setAbout_useful(a03);

        } else if (palu3.isChecked()) {
            score.setAbout_useful(p03);

        }
        if (exalu4.isChecked()) {
            score.setAbout_topic(ex04);

        } else if (vgalu4.isChecked()) {
            score.setAbout_topic(vg04);

        } else if (galu4.isChecked()) {
            score.setAbout_topic(g04);

        } else if (aalu4.isChecked()) {
            score.setAbout_topic(a04);

        } else if (palu4.isChecked()) {
            score.setAbout_topic(p04);

        }

        if (exalu5.isChecked()) {
            score.setAbout_time(ex05);

        } else if (vgalu5.isChecked()) {
            score.setAbout_time(vg05);

        } else if (galu5.isChecked()) {
            score.setAbout_time(g05);

        } else if (aalu5.isChecked()) {
            score.setAbout_time(a05);

        } else if (palu5.isChecked()) {
            score.setAbout_time(p05);

        }

        else if (!palu5.isChecked() || !exalu1.isChecked() || !vgalu1.isChecked() || !aalu1.isChecked() || !palu1.isChecked() || !exalu2.isChecked() || !vgalu2.isChecked() || !galu2.isChecked() || !aalu2.isChecked() || !palu2.isChecked() || !exalu3.isChecked() || !vgalu3.isChecked() || !galu3.isChecked() || !aalu3.isChecked() || !palu3.isChecked() || !exalu4.isChecked() || !vgalu4.isChecked() || !galu4.isChecked() || !aalu4.isChecked() || !palu4.isChecked() || !exalu5.isChecked() || !vgalu5.isChecked() || !galu5.isChecked() || !aalu5.isChecked() || !palu5.isChecked()
        ) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        score.setDate(date);

        reference.push().setValue(score);
        Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show();


        srn.setText("");
        topic.setText("");
        name.setText("");
        suggestions.setText("");

        exalu1.setChecked(false);
        vgalu1.setChecked(false);
        aalu1.setChecked(false);
        galu1.setChecked(false);
        palu1.setChecked(false);
        exalu2.setChecked(false);
        vgalu2.setChecked(false);
        aalu2.setChecked(false);
        galu2.setChecked(false);
        palu2.setChecked(false);
        exalu3.setChecked(false);
        vgalu3.setChecked(false);
        aalu3.setChecked(false);
        galu3.setChecked(false);
        palu3.setChecked(false);
        exalu4.setChecked(false);
        vgalu4.setChecked(false);
        aalu4.setChecked(false);
        galu4.setChecked(false);
        palu4.setChecked(false);
        exalu5.setChecked(false);
        vgalu5.setChecked(false);
        aalu5.setChecked(false);
        galu5.setChecked(false);
        palu5.setChecked(false);
    }

    private void InitializeFields(){

        button=(Button) findViewById(R.id.send_FeedBack_alu);
        score= new Score();
        exalu1=(RadioButton) findViewById(R.id.excellent_alu1);
        vgalu1=(RadioButton) findViewById(R.id.very_good_alu1);
        galu1=(RadioButton) findViewById(R.id.good_alu1);
        aalu1=(RadioButton)findViewById(R.id.average_alu1);
        palu1=(RadioButton) findViewById(R.id.poor_alu1);
        exalu2=(RadioButton) findViewById(R.id.excellent_alu2);
        vgalu2=(RadioButton) findViewById(R.id.very_good_alu2);
        galu2=(RadioButton) findViewById(R.id.good_alu2);
        aalu2=(RadioButton)findViewById(R.id.average_alu2);
        palu2=(RadioButton) findViewById(R.id.poor_alu2);
        exalu3=(RadioButton) findViewById(R.id.excellent_alu3);
        vgalu3=(RadioButton) findViewById(R.id.very_good_alu3);
        galu3=(RadioButton) findViewById(R.id.good_alu3);
        aalu3=(RadioButton)findViewById(R.id.average_alu3);
        palu3=(RadioButton) findViewById(R.id.poor_alu3);
        exalu4=(RadioButton) findViewById(R.id.excellent_alu4);
        vgalu4=(RadioButton) findViewById(R.id.very_good_alu4);
        galu4=(RadioButton) findViewById(R.id.good_alu4);
        aalu4=(RadioButton)findViewById(R.id.average_alu4);
        palu4=(RadioButton) findViewById(R.id.poor_alu4);
        exalu5=(RadioButton) findViewById(R.id.excellent_alu5);
        vgalu5=(RadioButton) findViewById(R.id.very_good_alu5);
        galu5=(RadioButton) findViewById(R.id.good_alu5);
        aalu5=(RadioButton)findViewById(R.id.average_alu5);
        palu5=(RadioButton) findViewById(R.id.poor_alu5);
        srn=(EditText) findViewById(R.id.input_srn);
        name=(EditText) findViewById(R.id.input_namealu);
        suggestions=(EditText) findViewById(R.id.suggestions2);
        topic=(EditText) findViewById(R.id.input_topic);
        s2=(Spinner)findViewById(R.id.spinner2);
        String[] event = getResources().getStringArray(R.array.Events);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_list, event);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        s2.setAdapter(arrayAdapter);
        s3=(Spinner)findViewById(R.id.spinner3);
        String[] school = getResources().getStringArray(R.array.Schools);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this,
                R.layout.spinner_list, school);
        arrayAdapter1.setDropDownViewResource(R.layout.spinner_list);
        s3.setAdapter(arrayAdapter1);
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
        redirectActivity(this,AdminDashboard.class);
    }
}