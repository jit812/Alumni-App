package com.example.alumni;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminSupportList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_support_list);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
    }

    public void showList(View view) {

        switch (view.getId()) {



            case R.id.d11:

                AlertDialog.Builder builder = new AlertDialog.Builder(AdminSupportList.this);

                builder.setTitle("Which Data you want to check?");
                builder.setMessage("Checked OR Unchecked?");

                builder.setPositiveButton("Checked", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog


                      Intent  intent = new Intent(getApplicationContext(), CheckedSupport.class);
                        intent.putExtra("node","Transfer Certificate" );
                        intent.putExtra("checkornot","1");
                        startActivity(intent);

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Unchecked", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing

                        Intent intent2 = new Intent(getApplicationContext(), UncheckedSupport.class);
                        intent2.putExtra("node","Transfer Certificate" );
                        intent2.putExtra("checkornot","0");
                        startActivity(intent2);



                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

                break;
            case R.id.d12:


                AlertDialog.Builder builder12 = new AlertDialog.Builder(AdminSupportList.this);

                builder12.setTitle("Which Data you want to check?");
                builder12.setMessage("Checked OR Unchecked?");

                builder12.setPositiveButton("Checked", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog


                        Intent  intent = new Intent(getApplicationContext(), CheckedSupport.class);
                        intent.putExtra("node","Migration Certificate" );
                        intent.putExtra("checkornot","1");
                        startActivity(intent);

                        dialog.dismiss();
                    }
                });

                builder12.setNegativeButton("Unchecked", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing

                        Intent intent2 = new Intent(getApplicationContext(), UncheckedSupport.class);
                        intent2.putExtra("node","Migration Certificate" );
                        intent2.putExtra("checkornot","0");
                        startActivity(intent2);



                        dialog.dismiss();
                    }
                });

                AlertDialog alert12 = builder12.create();
                alert12.show();

                break;



            case R.id.d13:


                AlertDialog.Builder builder13 = new AlertDialog.Builder(AdminSupportList.this);

                builder13.setTitle("Which Data you want to check?");
                builder13.setMessage("Checked OR Unchecked?");

                builder13.setPositiveButton("Checked", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog


                        Intent  intent = new Intent(getApplicationContext(), CheckedSupport.class);
                        intent.putExtra("node","10th, 12th Marks Card" );
                        intent.putExtra("checkornot","1");
                        startActivity(intent);

                        dialog.dismiss();
                    }
                });

                builder13.setNegativeButton("Unchecked", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing

                        Intent intent2 = new Intent(getApplicationContext(), UncheckedSupport.class);
                        intent2.putExtra("node", "10th, 12th Marks Card");
                        intent2.putExtra("checkornot","0");
                        startActivity(intent2);



                        dialog.dismiss();
                    }
                });

                AlertDialog alert13 = builder13.create();
                alert13.show();

                break;





            case R.id.d14:


                AlertDialog.Builder builder14 = new AlertDialog.Builder(AdminSupportList.this);

                builder14.setTitle("Which Data you want to check?");
                builder14.setMessage("Checked OR Unchecked?");

                builder14.setPositiveButton("Checked", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog


                        Intent  intent = new Intent(getApplicationContext(), CheckedSupport.class);
                        intent.putExtra("node","Consolidated Marks" );
                        intent.putExtra("checkornot","1");
                        startActivity(intent);

                        dialog.dismiss();
                    }
                });

                builder14.setNegativeButton("Unchecked", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing

                        Intent intent2 = new Intent(getApplicationContext(), UncheckedSupport.class);
                        intent2.putExtra("node", "Consolidated Marks");
                        intent2.putExtra("checkornot","0");
                        startActivity(intent2);



                        dialog.dismiss();
                    }
                });

                AlertDialog alert14 = builder14.create();
                alert14.show();

                break;


            case R.id.d15:

                AlertDialog.Builder builder15 = new AlertDialog.Builder(AdminSupportList.this);

                builder15.setTitle("Which Data you want to check?");
                builder15.setMessage("Checked OR Unchecked?");

                builder15.setPositiveButton("Checked", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog


                        Intent  intent = new Intent(getApplicationContext(), CheckedSupport.class);
                        intent.putExtra("node","Provisional Certificate" );
                        intent.putExtra("checkornot","1");
                        startActivity(intent);

                        dialog.dismiss();
                    }
                });

                builder15.setNegativeButton("Unchecked", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing

                        Intent intent2 = new Intent(getApplicationContext(), UncheckedSupport.class);
                        intent2.putExtra("node", "Provisional Certificate");
                        intent2.putExtra("checkornot","0");
                        startActivity(intent2);



                        dialog.dismiss();
                    }
                });

                AlertDialog alert15 = builder15.create();
                alert15.show();

                break;

            case R.id.d16:

                AlertDialog.Builder builder16 = new AlertDialog.Builder(AdminSupportList.this);

                builder16.setTitle("Which Data you want to check?");
                builder16.setMessage("Checked OR Unchecked?");

                builder16.setPositiveButton("Checked", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog


                        Intent  intent = new Intent(getApplicationContext(), CheckedSupport.class);
                        intent.putExtra("node","Duplicate Marks Card" );
                        intent.putExtra("checkornot","1");
                        startActivity(intent);

                        dialog.dismiss();
                    }
                });

                builder16.setNegativeButton("Unchecked", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing

                        Intent intent2 = new Intent(getApplicationContext(), UncheckedSupport.class);
                        intent2.putExtra("node", "Duplicate Marks Card");
                        intent2.putExtra("checkornot","0");
                        startActivity(intent2);



                        dialog.dismiss();
                    }
                });

                AlertDialog alert16 = builder16.create();
                alert16.show();

                break;

            case R.id.d17:

                AlertDialog.Builder builder17 = new AlertDialog.Builder(AdminSupportList.this);

                builder17.setTitle("Which Data you want to check?");
                builder17.setMessage("Checked OR Unchecked?");


                builder17.setPositiveButton("Checked", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog


                        Intent  intent = new Intent(getApplicationContext(), CheckedSupport.class);
                        intent.putExtra("node","Transcript" );
                        intent.putExtra("checkornot","1");
                        startActivity(intent);

                        dialog.dismiss();
                    }
                });

                builder17.setNegativeButton("Unchecked", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing

                        Intent intent2 = new Intent(getApplicationContext(), UncheckedSupport.class);
                        intent2.putExtra("node", "Transcript");
                        intent2.putExtra("checkornot","0");
                        startActivity(intent2);



                        dialog.dismiss();
                    }
                });

                AlertDialog alert17 = builder17.create();
                alert17.show();

                break;


        }


    }
}