package com.example.alumni;


import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Newsadapter extends FirebaseRecyclerAdapter<News,Newsadapter.Myviewholder>
{

    private List<News>mUploads;
    Context c;
    public Newsadapter(@NonNull FirebaseRecyclerOptions<News> options) {
        super(options);

    }




    @Override
    protected void onBindViewHolder(@NonNull Newsadapter.Myviewholder holder, int position, @NonNull final News model) {
        holder.news.setText(model.getNews());
        Glide.with(c).load(model.getImageUrl()).centerCrop().into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(c);

                builder.setTitle("Delete News");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog

                        DatabaseReference r= FirebaseDatabase.getInstance().getReference().child("Admin News");
                        r.child(model.getRandstring()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(c,"Successfully Removed",Toast.LENGTH_SHORT).show();
                            }
                        });


                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing



                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });



    }


    @NonNull
    @Override
    public Newsadapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_layout,parent,false);
        return new Myviewholder(view);
    }
    class Myviewholder extends RecyclerView.ViewHolder{
        private TextView news;
        private ImageView imageView;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            news = (TextView)itemView.findViewById(R.id.news1);
            imageView=(ImageView)itemView.findViewById(R.id.image_view_upload);
            c=itemView.getContext();
        }
    }
}
