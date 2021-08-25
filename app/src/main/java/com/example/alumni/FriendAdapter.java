package com.example.alumni;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter implements View.OnClickListener{


    List<UserData> userDataList;
    Context context;
    String list_keys;

    public FriendAdapter(List<UserData> userDataList, Context context, String list_keys) {
        this.userDataList = userDataList;
        this.context = context;
        this.list_keys = list_keys;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchuser, parent, false);
        final FriendAdapter.ViewHolderClass viewHolderClass = new FriendAdapter.ViewHolderClass(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolderClass.getAdapterPosition();
                UserData userData = userDataList.get(position);
                final String ItemId = userData.getSrn();
            }
        });
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        final UserData temp = userDataList.get(position);
        FriendAdapter.ViewHolderClass viewHolderClass = (FriendAdapter.ViewHolderClass)holder;
        UserData userData = userDataList.get(position);

        viewHolderClass.srnText.setText(userData.getSrn());
        viewHolderClass.fullnameText.setText(userData.getFullname());

        String url = userData.getImage();

        Glide.with(context)
                .load(url).placeholder(R.drawable.user).circleCrop()
                .into(viewHolderClass.avtarImg);

        ((FriendAdapter.ViewHolderClass) holder).detailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, userProfile.class);
                intent.putExtra("fullnamekey", temp.getFullname());
                intent.putExtra("srnkey", temp.getSrn());
                intent.putExtra("emailkey", temp.getEmail());
                intent.putExtra("phonekey", temp.getPhone());
                intent.putExtra("passedyearkey", temp.getPassedyear());
                intent.putExtra("schoolkey", temp.getSchool());
                intent.putExtra("uid", list_keys);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return userDataList.size();
    }


    public void onClick(View v) {

    }




    public class ViewHolderClass extends RecyclerView.ViewHolder{


        TextView fullnameText, srnText;
        Button detailsBtn;
        ImageView avtarImg;


        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);

            fullnameText = itemView.findViewById(R.id.fullnameText);
            srnText = itemView.findViewById(R.id.srnText);
            detailsBtn = itemView.findViewById(R.id.detailsBtn);
            avtarImg = itemView.findViewById(R.id.avtarImg);
        }


    }

    public interface RecyclerViewItemClick {

        public void OnItemClickListener(RecyclerView.ViewHolder holder, int position);

    }
}
