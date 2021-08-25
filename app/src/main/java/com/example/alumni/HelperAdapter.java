package com.example.alumni;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HelperAdapter extends RecyclerView.Adapter implements View.OnClickListener{


    List<UserData> userDataList;
    Context context;
    String list_keys;

    public HelperAdapter(List<UserData> userDataList, Context context, String list_keys) {
        this.userDataList = userDataList;
        this.context = context;
        this.list_keys = list_keys;



    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_display_layout, parent, false);
        final ViewHolderClass viewHolderClass = new ViewHolderClass(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolderClass.getAdapterPosition();
                UserData userData = userDataList.get(position);

                //final String PostKey = getRef(position).getKey();


                //  Toast.makeText(mContext, "You clicked an item", Toast.LENGTH_SHORT).show();
                final String ItemId = userData.getSrn();
                //   Intent intent = new Intent(mContext, SignupProfile.class);
                //  intent.putExtra("user_visit_id", ItemId);
                //  mContext.startActivity(intent);
            }
        });
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        final UserData temp = userDataList.get(position);
        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;
        UserData userData = userDataList.get(position);
        viewHolderClass.signupSrn.setText(userData.getSrn());
        viewHolderClass.signupFullName.setText(userData.getFullname());
        viewHolderClass.signupEmail.setText(userData.getEmail());


        ((ViewHolderClass) holder).viewDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, SignupProfile.class);
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
        //int position = holder.getAdapterPosition();
        //DataFish newCurrent=data.get(getAdapterPosition());


    }




    public class ViewHolderClass extends RecyclerView.ViewHolder{


        TextView signupSrn, signupFullName, signupEmail;
        Button viewDetailsBtn;


        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            signupSrn = itemView.findViewById(R.id.signupSrn);
            signupFullName = itemView.findViewById(R.id.signupFullName);
            signupEmail = itemView.findViewById(R.id.signupEmail);
            viewDetailsBtn = itemView.findViewById(R.id.viewDetailsBtn);

        }


    }

    public interface RecyclerViewItemClick {

        public void OnItemClickListener(RecyclerView.ViewHolder holder, int position);

    }





}
