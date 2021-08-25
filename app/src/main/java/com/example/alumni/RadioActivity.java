package com.example.alumni;

import androidx.appcompat.app.AppCompatActivity;

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RadioActivity extends AppCompatActivity {
    private Button button;
    private EditText srn,year_of_completion,designation,suggestions;
    private RadioButton ex1,vg1,g1,a1,p1,ex2,vg2,g2,a2,p2,ex3,vg3,g3,a3,p3,ex4,vg4,g4,a4,p4,ex7,vg7,g7,a7,p7,ex8,vg8,g8,a8,p8,ex9,vg9,g9,a9,p9,ex10,vg10,g10,a10,p10,ex11,vg11,g11,a11,p11;
    int i=0;
    private Radio radio;
    private DatabaseReference reference;
    private Spinner s1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        InitializeFields();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        reference= FirebaseDatabase.getInstance().getReference().child("Feedback");



        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                createFeedback();
                //   sendToNextPage();
            }
        });

    }




  /*  private void sendToNextPage() {
        Intent intent =new Intent(RadioActivity.this,RadioRetrieve.class);
        startActivity(intent);
    }*/

    private void createFeedback() {

        String ex01=ex1.getText().toString();
        String vg01=vg1.getText().toString();
        String g01=g1.getText().toString();
        String a01=a1.getText().toString();
        String p01=p1.getText().toString();
        String ex02=ex2.getText().toString();
        String vg02=vg2.getText().toString();
        String g02=g2.getText().toString();
        String a02=a2.getText().toString();
        String p02=p2.getText().toString();
        String ex03=ex3.getText().toString();
        String vg03=vg3.getText().toString();
        String g03=g3.getText().toString();
        String a03=a3.getText().toString();
        String p03=p3.getText().toString();
        String ex04=ex4.getText().toString();
        String vg04=vg4.getText().toString();
        String g04=g4.getText().toString();
        String a04=a4.getText().toString();
        String p04=p4.getText().toString();
        String ex07=ex7.getText().toString();
        String vg07=vg7.getText().toString();
        String g07=g7.getText().toString();
        String a07=a7.getText().toString();
        String p07=p7.getText().toString();
        String ex08=ex8.getText().toString();
        String vg08=vg8.getText().toString();
        String g08=g8.getText().toString();
        String a08=a8.getText().toString();
        String p08=p8.getText().toString();
        String ex09=ex9.getText().toString();
        String vg09=vg9.getText().toString();
        String g09=g9.getText().toString();
        String a09=a9.getText().toString();
        String p09=p9.getText().toString();
        String ex010=ex10.getText().toString();
        String vg010=vg10.getText().toString();
        String g010=g10.getText().toString();
        String a010=a10.getText().toString();
        String p010=p10.getText().toString();
        String ex011=ex11.getText().toString();
        String vg011=vg11.getText().toString();
        String g011=g11.getText().toString();
        String a011=a11.getText().toString();
        String p011=p11.getText().toString();


        radio.setSrn(srn.getText().toString());

        radio.setYear_of_completion(year_of_completion.getText().toString());

        radio.setDesignation(designation.getText().toString());

        radio.setSugg(suggestions.getText().toString());

        radio.setBranch(s1.getSelectedItem().toString());

        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        radio.setDate(date);


        if (ex1.isChecked()) {
            radio.setAbout_reva(ex01);

        }
        else if (vg1.isChecked()) {
            radio.setAbout_reva(vg01);
        }
        else if (g1.isChecked()) {
            radio.setAbout_reva(g01);
        }
        else if (a1.isChecked()) {
            radio.setAbout_reva(a01);

        } else if (p1.isChecked()) {
            radio.setAbout_reva(p01);

        }
        if (ex2.isChecked()) {
            radio.setAbout_app(ex02);

        } else if (vg2.isChecked()) {
            radio.setAbout_app(vg02);

        } else if (g2.isChecked()) {
            radio.setAbout_app(g02);

        } else if (a2.isChecked()) {
            radio.setAbout_app(a02);

        } else if (p2.isChecked()) {
            radio.setAbout_app(p02);

        }
        if (ex3.isChecked()) {
            radio.setCurriculum(ex03);

        } else if (vg3.isChecked()) {
            radio.setCurriculum(vg03);

        } else if (g3.isChecked()) {
            radio.setCurriculum(g03);

        } else if (a3.isChecked()) {
            radio.setCurriculum(a03);

        } else if (p3.isChecked()) {
            radio.setCurriculum(p03);

        }
        if (ex4.isChecked()) {
            radio.setReva_website(ex04);

        } else if (vg4.isChecked()) {
            radio.setReva_website(vg04);

        } else if (g4.isChecked()) {
            radio.setReva_website(g04);

        } else if (a4.isChecked()) {
            radio.setReva_website(a04);

        } else if (p4.isChecked()) {
            radio.setReva_website(p04);

        }

        if (ex7.isChecked()) {
            radio.setLab_infra(ex07);

        } else if (vg7.isChecked()) {
            radio.setLab_infra(vg07);

        } else if (g7.isChecked()) {
            radio.setLab_infra(g07);

        } else if (a7.isChecked()) {
            radio.setLab_infra(a07);

        } else if (p7.isChecked()) {
            radio.setLab_infra(p07);

        }
        if (ex8.isChecked()) {
            radio.setFaculty(ex08);

        } else if (vg8.isChecked()) {
            radio.setFaculty(vg08);

        } else if (g8.isChecked()) {
            radio.setFaculty(g08);

        } else if (a8.isChecked()) {
            radio.setFaculty(a08);

        } else if (p8.isChecked()) {
            radio.setFaculty(p08);

        }
        if (ex9.isChecked()) {
            radio.setLib(ex09);

        } else if (vg9.isChecked()) {
            radio.setLib(vg09);

        } else if (g9.isChecked()) {
            radio.setLib(g09);

        } else if (a9.isChecked()) {
            radio.setLib(a09);

        } else if (p9.isChecked()) {
            radio.setLib(p09);

        }
        if (ex10.isChecked()) {
            radio.setOfc_staff(ex010);

        } else if (vg10.isChecked()) {
            radio.setOfc_staff(vg010);

        } else if (g10.isChecked()) {
            radio.setOfc_staff(g010);

        } else if (a10.isChecked()) {
            radio.setOfc_staff(a010);

        } else if (p10.isChecked()) {
            radio.setOfc_staff(p010);

        }
        if (ex11.isChecked()) {
            radio.setHostel_facilities(ex011);

        } else if (vg11.isChecked()) {
            radio.setHostel_facilities(vg011);

        } else if (g11.isChecked()) {
            radio.setHostel_facilities(g011);

        } else if (a11.isChecked()) {
            radio.setHostel_facilities(a011);

        }else if (p11.isChecked()){
            radio.setHostel_facilities(p011);
        }
        else if (!p11.isChecked() || !ex1.isChecked() ||!vg1.isChecked() || !a1.isChecked() || !p1.isChecked() ||  !ex2.isChecked() || !vg2.isChecked() || !g2.isChecked() || !a2.isChecked() || !p2.isChecked() || !ex3.isChecked() || !vg3.isChecked() || !g3.isChecked() || !a3.isChecked() || !p3.isChecked() || !ex4.isChecked() || !vg4.isChecked() || !g4.isChecked() || !a4.isChecked() || !p4.isChecked() || !ex7.isChecked() || !vg7.isChecked() || !g7.isChecked() || !a7.isChecked() || !p7.isChecked() || !ex8.isChecked() || !vg8.isChecked() || !g8.isChecked() || !a8.isChecked() || !p8.isChecked() || !ex9.isChecked() || !vg9.isChecked() || !g9.isChecked() || !a9.isChecked() || !p9.isChecked() || !ex10.isChecked() || !vg10.isChecked() || !g10.isChecked() || !a10.isChecked() || !p10.isChecked() || !ex11.isChecked() || !vg11.isChecked() || !g11.isChecked() || !a11.isChecked() || !p11.isChecked()
        ) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show();
            return;
        }



        reference.push().setValue(radio);
        Toast.makeText(this, "Submitted", Toast.LENGTH_LONG).show();



        srn.setText("");
        year_of_completion.setText("");
        designation.setText("");
        suggestions.setText("");

        ex1.setChecked(false);
        vg1.setChecked(false);
        a1.setChecked(false);
        g1.setChecked(false);
        p1.setChecked(false);
        ex2.setChecked(false);
        vg2.setChecked(false);
        a2.setChecked(false);
        g2.setChecked(false);
        p2.setChecked(false);
        ex3.setChecked(false);
        vg3.setChecked(false);
        a3.setChecked(false);
        g3.setChecked(false);
        p3.setChecked(false);
        ex4.setChecked(false);
        vg4.setChecked(false);
        a4.setChecked(false);
        g4.setChecked(false);
        p4.setChecked(false);
        ex7.setChecked(false);
        vg7.setChecked(false);
        a7.setChecked(false);
        g7.setChecked(false);
        p7.setChecked(false);
        ex8.setChecked(false);
        vg8.setChecked(false);
        a8.setChecked(false);
        g8.setChecked(false);
        p8.setChecked(false);
        ex9.setChecked(false);
        vg9.setChecked(false);
        a9.setChecked(false);
        g9.setChecked(false);
        p9.setChecked(false);
        ex10.setChecked(false);
        vg10.setChecked(false);
        a10.setChecked(false);
        g10.setChecked(false);
        p10.setChecked(false);
        ex11.setChecked(false);
        vg11.setChecked(false);
        a11.setChecked(false);
        g11.setChecked(false);
        p11.setChecked(false);
        s1.setEnabled(false);

    }




    private void InitializeFields()
    {
        button=(Button) findViewById(R.id.send_FeedBack);
        radio= new Radio();
        ex1=(RadioButton) findViewById(R.id.excellent_1);
        vg1=(RadioButton) findViewById(R.id.very_good_1);
        g1=(RadioButton) findViewById(R.id.good_1);
        a1=(RadioButton)findViewById(R.id.average_1);
        p1=(RadioButton) findViewById(R.id.poor_1);
        ex2=(RadioButton) findViewById(R.id.excellent_2);
        vg2=(RadioButton) findViewById(R.id.very_good_2);
        g2=(RadioButton) findViewById(R.id.good_2);
        a2=(RadioButton)findViewById(R.id.average_2);
        p2=(RadioButton) findViewById(R.id.poor_2);
        ex3=(RadioButton) findViewById(R.id.excellent_3);
        vg3=(RadioButton) findViewById(R.id.very_good_3);
        g3=(RadioButton) findViewById(R.id.good_3);
        a3=(RadioButton)findViewById(R.id.average_3);
        p3=(RadioButton) findViewById(R.id.poor_3);
        ex4=(RadioButton) findViewById(R.id.excellent_4);
        vg4=(RadioButton) findViewById(R.id.very_good_4);
        g4=(RadioButton) findViewById(R.id.good_4);
        a4=(RadioButton)findViewById(R.id.average_4);
        p4=(RadioButton) findViewById(R.id.poor_4);
        ex7=(RadioButton) findViewById(R.id.excellent_7);
        vg7=(RadioButton) findViewById(R.id.very_good_7);
        g7=(RadioButton) findViewById(R.id.good_7);
        a7=(RadioButton)findViewById(R.id.average_7);
        p7=(RadioButton) findViewById(R.id.poor_7);
        ex8=(RadioButton) findViewById(R.id.excellent_8);
        vg8=(RadioButton) findViewById(R.id.very_good_8);
        g8=(RadioButton) findViewById(R.id.good_8);
        a8=(RadioButton)findViewById(R.id.average_8);
        p8=(RadioButton) findViewById(R.id.poor_8);
        ex9=(RadioButton) findViewById(R.id.excellent_9);
        vg9=(RadioButton) findViewById(R.id.very_good_9);
        g9=(RadioButton) findViewById(R.id.good_9);
        a9=(RadioButton)findViewById(R.id.average_9);
        p9=(RadioButton) findViewById(R.id.poor_9);
        ex10=(RadioButton) findViewById(R.id.excellent_10);
        vg10=(RadioButton) findViewById(R.id.very_good_10);
        g10=(RadioButton) findViewById(R.id.good_10);
        a10=(RadioButton)findViewById(R.id.average_10);
        p10=(RadioButton) findViewById(R.id.poor_10);
        ex11=(RadioButton) findViewById(R.id.excellent_11);
        vg11=(RadioButton) findViewById(R.id.very_good_11);
        g11=(RadioButton) findViewById(R.id.good_11);
        a11=(RadioButton)findViewById(R.id.average_11);
        p11=(RadioButton) findViewById(R.id.poor_11);
        year_of_completion=(EditText) findViewById(R.id.input_year);
        designation=(EditText) findViewById(R.id.input_occupation);
        srn=(EditText) findViewById(R.id.input_srn);
        suggestions=(EditText) findViewById(R.id.suggestions1);
        s1=(Spinner)findViewById(R.id.spinner);
        String[] school = getResources().getStringArray(R.array.Schools);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_list, school);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        s1.setAdapter(arrayAdapter);

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