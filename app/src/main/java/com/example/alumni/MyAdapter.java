package com.example.alumni;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class MyAdapter extends FirebaseRecyclerAdapter<Reference,MyAdapter.myviewholder> {

    public MyAdapter(@NonNull FirebaseRecyclerOptions<Reference> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Reference model)
    {
        holder.fullname.setText(model.getFullname());
     //   holder.middlename.setText(model.getMiddlename());
     //   holder.lastname.setText(model.getLastname());
        holder.gender.setText(model.getGender());
        holder.course.setText(model.getCourse());
        holder.email.setText(model.getEmail());
        holder.phonenumber.setText(model.getPhonenumber());
        holder.dob.setText(model.getDob());
        holder.address.setText(model.getAddress());
        holder.state.setText(model.getState());
        holder.country.setText(model.getCountry());
        holder.pincode.setText(model.getPincode());
        holder.parentno.setText(model.getParent());
        holder.srn.setText(model.getSrn());
        holder.date.setText(model.getDate());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
        private TextView fullname,middlename,lastname,gender,course,email,phonenumber,dob,address,state,country,pincode,parentno,srn,date;

        public myviewholder(@NonNull View itemView) {

            super(itemView);
            fullname=(TextView)itemView.findViewById(R.id.fullname1);
            //middlename=(TextView)itemView.findViewById(R.id.middlename1);
            //lastname=(TextView) itemView.findViewById(R.id.lastname1);
            gender=(TextView) itemView.findViewById(R.id.gender1);
            course=(TextView) itemView.findViewById(R.id.course1);
            email=(TextView) itemView.findViewById(R.id.email1);
            phonenumber=(TextView) itemView.findViewById(R.id.phonenumber19);
            dob=(TextView) itemView.findViewById(R.id.dob1);
            address=(TextView) itemView.findViewById(R.id.address1);
            state=(TextView) itemView.findViewById(R.id.state1);
            country=(TextView) itemView.findViewById(R.id.country1);
            pincode=(TextView) itemView.findViewById(R.id.pincode1);
            parentno=(TextView) itemView.findViewById(R.id.phonenumber1);
            srn=(TextView) itemView.findViewById(R.id.srn1);
            date=itemView.findViewById(R.id.date_adref);
        }

    }
}

