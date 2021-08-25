package com.example.alumni;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.Banner;

public class DashBoard extends AppCompatActivity {

    private RecyclerView recyclerView, searchRecyclerview;
    private Newsadapterforuser newsadapter;
    DrawerLayout drawerLayout;
    ImageView profileBtn,insta1,linkedin1,fb1;
    HashMap<String,String> image_list;
    SliderLayout mSlider;
    FirebaseDatabase database;
    private DatabaseReference banner;
    FirebaseAuth mAuth;
    EditText searchText;
    ImageView chatImg;

    List<UserData> userdata;
    SearchAdapter searchAdapter;
    String currentID, list_keys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        profileBtn = findViewById(R.id.profileBtn);
        database=FirebaseDatabase.getInstance();
        insta1=findViewById(R.id.insta1);
        linkedin1=findViewById(R.id.linkedin1);
        fb1=findViewById(R.id.fb1);
        searchRecyclerview = findViewById(R.id.searchRecyclerview);
        searchText = findViewById(R.id.searchText);
        chatImg = findViewById(R.id.chatImg);

        mAuth = FirebaseAuth.getInstance();
        final String currentUserId = mAuth.getCurrentUser().getUid();

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());
            }
        });

        chatImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.alumni.DashBoard.this, com.example.alumni.ChatScreen.class);
                startActivity(intent);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.alumni.DashBoard.this, com.example.alumni.Profile.class);
                startActivity(intent);
            }
        });

        insta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/_u/reva_university");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/reva_university")));
                }
            }
        });

        linkedin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/school/reva-university"));
                final PackageManager packageManager =getPackageManager();
                final List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                if (list.isEmpty()) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/school/reva-university"));
                }
                startActivity(intent);
            }
        });

        fb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = null;
                try {
                    getPackageManager().getPackageInfo("com.facebook.katana", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/REVAUniversity/"));
                    //tried this also
                    //intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/<id here>"));
                } catch (Exception e) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/REVAUniversity/"));
                }
                startActivity(intent);
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);




        final DatabaseReference profileUserRef = FirebaseDatabase.getInstance().getReference().child("Register").child(currentUserId);
        profileUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("image").exists()) {
                    String url = snapshot.child("image").getValue().toString();
                    Glide.with(getApplicationContext())

                            .load(url).placeholder(R.drawable.user).circleCrop()
                            .into(profileBtn);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        InitializeFields();

        FirebaseRecyclerOptions<News> options=
                new FirebaseRecyclerOptions.Builder<News>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Admin News"),News.class)
                        .build();

        newsadapter = new Newsadapterforuser(options);
        recyclerView.setAdapter(newsadapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        setupSlider();

    }

    private void filter(String text) {
        final String searchname = searchText.getText().toString();
        final DatabaseReference regRef = FirebaseDatabase.getInstance().getReference("Register");
        final DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Requests");

        searchAdapter = new SearchAdapter(userdata, getApplicationContext(), list_keys);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        searchRecyclerview.setLayoutManager(new LinearLayoutManager(DashBoard.this));
        userdata = new ArrayList<>();

                    regRef.orderByChild("fullname").startAt(searchname).endAt(searchname + "\uf8ff")
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot ds: snapshot.getChildren()){
                                        final String list_keys = ds.getRef().getKey();
                                        UserData data = ds.getValue(UserData.class);
                                        userdata.add(data);

                                    }
                                    searchAdapter = new SearchAdapter(userdata, getApplicationContext(), list_keys);
                                    searchRecyclerview.setAdapter(searchAdapter);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                }




    public void ClickMenu(View view){
        //open drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        //close drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //close drawer layout
        //check condition
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //when drawer is open
            //close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void ClickSupport(View view){
        //Redirect activity to dashboard
        redirectActivity(this,support_fee_details.class);
    }
    public void ClickContactDetails(View view){
        //Redirect activity to dashboard
        redirectActivity(this, Contacts.class);
    }

    public void ClickAdmissionReference(View view){
        //Redirect activity to dashboard
        redirectActivity(this,AdmissionReferenceActivity.class);
    }

    public void ClickFeedBack(View view){
        //Redirect activity to dashboard
        redirectActivity(this,RadioActivity.class);
    }
    public void ClickJobr(View view){
        redirectActivity(this,JobRetrieveActivity.class);
    }

    public void ClickAboutAlumni(View view){
        //Redirect activity to dashboard
        redirectActivity(this,about_alumni.class);
    }


    public void ClickJob(View view){
        redirectActivity(this,JobPortal.class);
    }
    public void ClickBr(View view){
        redirectActivity(this,brand_store.class);
    }

    public void ClickAboutUs(View view){
        //Redirect activity to dashboard
        redirectActivity(this, AboutUs.class);
    }
    public void ClickLog(View view){

        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        mAuth.signOut();
        redirectActivity(this,Auth.class);

    }
    public void ClickFe(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        String[] to = {"timeline.error404@gmail.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.setType("message/rfc822");
        Intent chooser =Intent.createChooser(intent,"Send Feedback");
        startActivity(chooser);}
        public void ClickShr(View view)
        {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT,"News");
            String sAux = "\nLet me recommend you this news app\n\n";
            sAux = sAux + "A link will  be put here once this app is available for the public in Google play store\n\nThank you from the team of Error404\n\n";
            intent.putExtra(Intent.EXTRA_TEXT,sAux);
            startActivity(Intent.createChooser(intent,"Share app via:"));
        }
    public static void redirectActivity(Activity activity, Class aClass) {
        //Initialize intent
        Intent intent = new Intent(activity,aClass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //start activity
        activity.startActivity(intent);
    }
    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        closeDrawer(drawerLayout);
    }
    @Override
    protected void onStart() {
        super.onStart();
        newsadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        newsadapter.stopListening();
    }

    private void InitializeFields() {
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupSlider() {

        mSlider=(SliderLayout)findViewById(R.id.flipper);
        image_list=new HashMap<>();

        banner =database.getReference("Banner");
        //banner.keepSynced(true);


        banner.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                for (DataSnapshot postSnapShot : datasnapshot.getChildren()) {
                    Banner banner = postSnapShot.getValue(Banner.class);
                    image_list.put(banner.getName() + "_" + banner.getId(), banner.getImage());
                }

                for(String key:image_list.keySet())
                {
                    String[] keySplit=key.split("_");
                    String name=keySplit[0];
                    String id=keySplit[1];

                    final DefaultSliderView textSliderView =new DefaultSliderView(getBaseContext());

                    textSliderView.image(image_list.get(key)).setScaleType(BaseSliderView.ScaleType.Fit);


                    textSliderView.bundle(new Bundle());
                    textSliderView.getBundle().putString("ID",id);
                    mSlider.addSlider(textSliderView);


                }

            }



            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSlider.setDuration(5000);
    }

}