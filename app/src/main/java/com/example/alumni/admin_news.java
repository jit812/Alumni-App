package com.example.alumni;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class admin_news extends AppCompatActivity {

    private EditText news;
    private Button updatenews,attach;
    private ImageView imageView;
    private Uri uri;
    private DatabaseReference newsDb;
    private StorageReference storageReference;
    private static final int PICK_IMAGE_CODE = 1;
    private StorageTask mUploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_news);

        news = findViewById(R.id.news);
        updatenews = findViewById(R.id.update_news);
        attach = findViewById(R.id.attach);
        imageView=findViewById(R.id.image_view);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        storageReference= FirebaseStorage.getInstance().getReference("uploadimage");
        newsDb = FirebaseDatabase.getInstance().getReference().child("Admin News");


        attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openFileChooser();
            }
        });
        updatenews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(admin_news.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {*/
                    createNews();
                    sendUser();



            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            uri = data.getData();
            Glide.with(this).load(uri).into(imageView);

        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void createNews() {


        if (uri != null) {
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    + "." + getFileExtension(uri));
            UploadTask uploadTask = fileReference.putFile(uri);

            // UploadTask mUploadTask=filePath.putFile(resultUri);
            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        Toast.makeText(admin_news.this, "Successfully uploaded", Toast.LENGTH_SHORT).show();
                        if (downloadUri != null) {

                            String anews = news.getText().toString();
                            String downloadUrl = downloadUri.toString();
                            String randstring= UUID.randomUUID().toString().toUpperCase();

                            News nw = new News(anews, downloadUrl,randstring);
                            newsDb.child(randstring).setValue(nw)

                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (!task.isSuccessful()) {
                                                String error = task.getException().toString();
                                                Toast.makeText(admin_news.this, "Error : " + error, Toast.LENGTH_LONG).show();
                                            } else {

                                            }
                                        }
                                    });
                        }

                    } else {
                        // Handle failures
                        // ...
                        Toast.makeText(admin_news.this, "Error", Toast.LENGTH_LONG).show();
                    }
                }
            });






      /*  if (uri != null) {
           final StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    + "." + getFileExtension(uri));
            mUploadTask = fileReference.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(admin_news.this, "Upload successful", Toast.LENGTH_LONG).show();
                            News upload = new News(news.getText().toString(),
                                    taskSnapshot.getStorage().getDownloadUrl().toString());
                            String uploadId = newsDb.push().getKey();
                            newsDb.child(uploadId).setValue(upload);

                            news.setText("");
                            imageView.setVisibility(View.INVISIBLE);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(admin_news.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }*/

       /* String anews = news.getText().toString();

        News nw = new News(anews);
        newsDb.push().setValue(nw);

        Toast.makeText(admin_news.this,"Successfully Updated",Toast.LENGTH_SHORT).show();

        news.setText("");*/
        }else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendUser() {
        Intent intent = new Intent(admin_news.this,RetrieveNews.class);
        startActivity(intent);
    }

    public void ClickBack(View view){
        //Redirect activity to dashboard
        redirectActivity(this,AdminDashboard.class);
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        //Initialize intent
        Intent intent = new Intent(activity,aClass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //start activity
        activity.startActivity(intent);
    }

}