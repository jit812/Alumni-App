package com.example.alumni;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Radioadapter extends FirebaseRecyclerAdapter<Radio,Radioadapter.Myradioholder> {


    public Radioadapter(@NonNull FirebaseRecyclerOptions<Radio> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Radioadapter.Myradioholder holder, int position, @NonNull Radio model) {

        holder.srn.setText(model.getSrn());
        holder.branch.setText(model.getBranch());
        holder.year.setText(model.getYear_of_completion());
        holder.occupation.setText(model.getDesignation());
        holder.about_reva.setText(model.getAbout_reva());
        holder.about_app.setText(model.getAbout_app());
        holder.about_curriculum.setText(model.getCurriculum());
        holder.about_website.setText(model.getReva_website());
        holder.about_infra.setText(model.getLab_infra());
        holder.about_lib.setText(model.getLib());
        holder.about_faculty.setText(model.getFaculty());
        holder.staffs.setText(model.getOfc_staff());
        holder.hostel.setText(model.getHostel_facilities());
        holder.suggession.setText(model.getSugg());
        holder.date.setText(model.getDate());

    }

    @NonNull
    @Override
    public Radioadapter.Myradioholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_retrieve,parent,false);
        return new Myradioholder(view);
    }
    class Myradioholder extends RecyclerView.ViewHolder{
        private TextView srn,branch,year,occupation,about_reva,about_app,about_curriculum,about_website,about_infra,about_faculty,about_lib,staffs,hostel,suggession,date;

        public Myradioholder(@NonNull View itemView) {
            super(itemView);

            srn = (TextView)itemView.findViewById(R.id.srn1);
            branch = (TextView)itemView.findViewById(R.id.input_branch);
            year= (TextView)itemView.findViewById(R.id.input_year1);
            occupation = (TextView)itemView.findViewById(R.id.input_occupation1);
            about_reva= (TextView)itemView.findViewById(R.id.about_reva);
            about_app= (TextView)itemView.findViewById(R.id.about_app);
            about_curriculum= (TextView)itemView.findViewById(R.id.about_curriculum);
            about_website= (TextView)itemView.findViewById(R.id.about_website);
            about_infra= (TextView)itemView.findViewById(R.id.about_infra);
            about_faculty= (TextView)itemView.findViewById(R.id.about_faculty);
            about_lib = (TextView)itemView.findViewById(R.id.about_lib);
           hostel = (TextView)itemView.findViewById(R.id.about_hostel);
           staffs = (TextView)itemView.findViewById(R.id.staffs);
            suggession = (TextView)itemView.findViewById(R.id.sugg1);
            date=itemView.findViewById(R.id.date_fb);
        }
    }

}
