package com.example.alumni;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Map;

public class Supportadapter extends FirebaseRecyclerAdapter<Suppots,Supportadapter.MyViewholder> {
    Context c;

    private List<Suppots> suppotsList;


    public Supportadapter(@NonNull FirebaseRecyclerOptions<Suppots> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull final Supportadapter.MyViewholder holder, int position, @NonNull final Suppots model) {

        holder.srn.setText(model.getSrn());
        holder.transactio_id.setText(model.getTransactionId());
        holder.branch.setText(model.getBranch());
        holder.year_of_graduation.setText(model.getYearofgraduation());
        holder.required_documents.setText(model.getDocument());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder17 = new AlertDialog.Builder(c);

                builder17.setTitle("Move this data to Checked?");
                builder17.setMessage("Are you sure?");

                builder17.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        String data = ((Activity) c).getIntent().getStringExtra("node");
                        String check=((Activity) c).getIntent().getStringExtra("checkornot");



                        DatabaseReference fromcopy=FirebaseDatabase.getInstance().getReference().child("support").child("0")
                                .child(data).child(model.getTransactionId());


                        final DatabaseReference tocopy=FirebaseDatabase.getInstance().getReference().child("support").child("1")
                                .child(data).child(model.getTransactionId());


                        fromcopy.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {


                                    for (DataSnapshot ds : snapshot.getChildren()) {


                                        tocopy.child("branch").setValue(model.getBranch());
                                        tocopy.child("yearofgraduation").setValue(model.getYearofgraduation());
                                        tocopy.child("transactionId").setValue(model.getTransactionId());
                                        tocopy.child("srn").setValue(model.getSrn());
                                        tocopy.child("document").setValue(model.getDocument());

                                    }





                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });



                        DatabaseReference remove= FirebaseDatabase.getInstance().getReference().child("support").child("0")
                                .child(data).child(model.getTransactionId());

                        remove.removeValue();


                        Toast.makeText(c, ""+data+"   "+check, Toast.LENGTH_SHORT).show();

                        dialog.dismiss();
                    }
                });

                builder17.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing




                        dialog.dismiss();
                    }
                });

                AlertDialog alert17 = builder17.create();
                alert17.show();

            }
        });

    }

    @NonNull
    @Override
    public Supportadapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.support_layout,parent,false);
        return new MyViewholder(view);
    }

    class MyViewholder extends RecyclerView.ViewHolder{
        private TextView srn,transactio_id,year_of_graduation,branch,required_documents;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            srn=(TextView)itemView.findViewById(R.id.srn2);
            transactio_id=(TextView)itemView.findViewById(R.id.trans_id);
            branch=(TextView)itemView.findViewById(R.id.specification_branch2);
            year_of_graduation=(TextView)itemView.findViewById(R.id.year_of_graduation2);
            required_documents=(TextView)itemView.findViewById(R.id.required_documents2);
            c=itemView.getContext();

        }
    }
}
