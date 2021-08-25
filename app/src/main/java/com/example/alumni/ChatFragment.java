package com.example.alumni;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ChatFragment extends Fragment {
    private RecyclerView mFriendsList;

    private DatabaseReference mFriendsDatabase;
    private DatabaseReference mUsersDatabase;
    private DatabaseReference messageRef;
    private FirebaseAuth mAuth;
    List<UserData> userdata;

    private String mCurrent_user_id;

    private View mMainView;

    public ChatFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.fragment_chat, container, false);

        mFriendsList = (RecyclerView) mMainView.findViewById(R.id.chat_list);
        mAuth = FirebaseAuth.getInstance();
        mCurrent_user_id = mAuth.getCurrentUser().getUid();

        mFriendsDatabase = FirebaseDatabase.getInstance().getReference().child("Friends").child(mCurrent_user_id);
        mFriendsDatabase.keepSynced(true);
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Register");
        mUsersDatabase.keepSynced(true);

        messageRef = FirebaseDatabase.getInstance().getReference().child("messages");
        messageRef.keepSynced(true);

        mFriendsList.setHasFixedSize(true);
        mFriendsList.setLayoutManager(new LinearLayoutManager(getContext()));

        return mMainView;

    }

    @Override
    public void onStart() {
        super.onStart();

        Query query = messageRef;

        FirebaseRecyclerOptions<UserData> options =
                new FirebaseRecyclerOptions.Builder<UserData>()
                        .setQuery(query, UserData.class)
                        .build();

        FirebaseRecyclerAdapter<UserData, ChatFragment.FriendsViewHolder> friendsRecyclerViewAdapter = new FirebaseRecyclerAdapter<UserData, ChatFragment.FriendsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ChatFragment.FriendsViewHolder holder, int position, @NonNull UserData model) {
                final String list_user_id = getRef(position).getKey();
                    int index = list_user_id.indexOf("_");
                    final String user_id = list_user_id.substring(index + 1);
                messageRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                            String keys = ds.getRef().getKey();
                            if (keys.contains(mCurrent_user_id)) {
                                mUsersDatabase.child(user_id).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            if (snapshot.child("image").exists()) {
                                                String url = snapshot.child("image").getValue().toString();
                                                Glide.with(getContext())

                                                        .load(url).placeholder(R.drawable.user).circleCrop()
                                                        .into(holder.avtarImgFf);
                                                final String name = snapshot.child("fullname").getValue().toString();
                                                holder.fullnameTextFf.setText(name);

                                            } else {
                                                final String name = snapshot.child("fullname").getValue().toString();
                                                holder.fullnameTextFf.setText(name);
                                            }
                                        }

                                        holder.fullnameTextFf.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent profileIntent = new Intent(getContext(), Chat.class);
                                                profileIntent.putExtra("user-id", user_id);
                                                profileIntent.putExtra("user_name", holder.fullnameTextFf.getText());
                                                startActivity(profileIntent);

                                            }
                                        });
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }
                        }
                    }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public ChatFragment.FriendsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.users_single_layout,parent,false);
                return new ChatFragment.FriendsViewHolder(view);
            }
        };

        friendsRecyclerViewAdapter.startListening();
        mFriendsList.setAdapter(friendsRecyclerViewAdapter);

    }

    public static class FriendsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView fullnameTextFf;
        ImageView avtarImgFf;
        public FriendsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            fullnameTextFf = (TextView) itemView.findViewById(R.id.fullnameTextFf);
            avtarImgFf = (ImageView) itemView.findViewById(R.id.avtarImgFf);
        }
    }

}