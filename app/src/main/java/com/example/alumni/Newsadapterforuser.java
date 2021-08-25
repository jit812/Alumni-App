package com.example.alumni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class Newsadapterforuser extends FirebaseRecyclerAdapter<News, Newsadapterforuser.Myviewholder>
{

    private List<News> mUploads;
    Context c;
    public Newsadapterforuser(@NonNull FirebaseRecyclerOptions<News> options) {
        super(options);

    }




    @Override
    protected void onBindViewHolder(@NonNull Newsadapterforuser.Myviewholder holder, int position, @NonNull final News model) {
        holder.news.setText(model.getNews());
        Glide.with(c).load(model.getImageUrl()).centerCrop().into(holder.imageView);





    }


    @NonNull
    @Override
    public Newsadapterforuser.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
