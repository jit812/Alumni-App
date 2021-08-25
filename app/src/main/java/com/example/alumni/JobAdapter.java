package com.example.alumni;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class JobAdapter extends FirebaseRecyclerAdapter<Jobs,JobAdapter.MyJobHolder> {


    public JobAdapter(@NonNull FirebaseRecyclerOptions<Jobs> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull JobAdapter.MyJobHolder holder, int position, @NonNull Jobs model) {
        holder.company_name.setText(model.getCompanyname());
        holder.designation.setText(model.getDesignation());
        holder.job_description.setText(model.getJobdes());
        holder.employee_type.setText(model.getEmpType());
        holder.experience.setText(model.getExp());
        holder.qualification.setText(model.getQualification());
        holder.date.setText(model.getDate());

    }

    @NonNull
    @Override
    public JobAdapter.MyJobHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.job_view,parent,false);
        return new JobAdapter.MyJobHolder(view);

    }

    class MyJobHolder extends RecyclerView.ViewHolder{

    private TextView company_name,designation,job_description,employee_type,experience,qualification,date;
        public MyJobHolder(@NonNull View itemView) {
            super(itemView);
            company_name=(TextView)itemView.findViewById(R.id.companyname1);
            designation=(TextView)itemView.findViewById(R.id.designation1);
            job_description=(TextView)itemView.findViewById(R.id.job_description);
            employee_type=(TextView)itemView.findViewById(R.id.employee_Type);
            experience=(TextView)itemView.findViewById(R.id.experience);
            qualification=(TextView)itemView.findViewById(R.id.qualification1);
            date=itemView.findViewById(R.id.date_job);
        }
    }
}
