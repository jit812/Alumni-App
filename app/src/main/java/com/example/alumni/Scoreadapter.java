package com.example.alumni;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Scoreadapter extends FirebaseRecyclerAdapter<Score,Scoreadapter.MyScoreholder> {



    public Scoreadapter(@NonNull FirebaseRecyclerOptions<Score> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Scoreadapter.MyScoreholder holder, int position, @NonNull Score model) {

        holder.srn.setText(model.getSrn());
        holder.school.setText(model.getSchool());
        holder.name.setText(model.getName());
        holder.topic.setText(model.getTopic());
        holder.event.setText(model.getEvent());
        holder.about_session.setText(model.getAbout_session());
        holder.about_content.setText(model.getAbout_content());
        holder.about_useful.setText(model.getAbout_useful());
        holder.about_time.setText(model.getAbout_time());
        holder.about_topic.setText(model.getAbout_topic());
        holder.suggession.setText(model.getSugg());
        holder.date.setText(model.getDate());
    }

    @NonNull
    @Override
    public Scoreadapter.MyScoreholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_layout,parent,false);
        return new Scoreadapter.MyScoreholder(view);
    }
    class MyScoreholder extends RecyclerView.ViewHolder{

        private TextView name,srn,school,event,topic,about_session,about_content,about_useful,about_topic,about_time,suggession,date;


        public MyScoreholder(@NonNull View itemView) {
            super(itemView);

            srn = (TextView)itemView.findViewById(R.id.srn_alu1);
            school= (TextView)itemView.findViewById(R.id.about_school_alu);
            name= (TextView)itemView.findViewById(R.id.input_name_alu);
            topic = (TextView)itemView.findViewById(R.id.input_topic_alu);
            event= (TextView)itemView.findViewById(R.id.input_event_alu);
            about_session= (TextView)itemView.findViewById(R.id.about_session_alu);
            about_content= (TextView)itemView.findViewById(R.id.about_content_alu);
            about_time= (TextView)itemView.findViewById(R.id.about_time_alu);
            about_useful= (TextView)itemView.findViewById(R.id.about_useful_alu);
            about_topic = (TextView)itemView.findViewById(R.id.about_topic_alu);
            suggession= (TextView)itemView.findViewById(R.id.about_sugg_alu);
            date=itemView.findViewById(R.id.date_myscore);

        }
    }
}