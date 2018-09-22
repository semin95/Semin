package com.example.friend.friendv2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class OnlineActivity extends AppCompatActivity {

    private RecyclerView onlineList;
    private DatabaseReference bazaUser;
    private DatabaseReference bazaOnline;
    private DatabaseReference bazaO;
    private String online;

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);

        getSupportActionBar().setTitle("Online friends");

        onlineList = (RecyclerView) findViewById(R.id.onlineList);
        onlineList.setHasFixedSize(true);
        bazaUser = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("FriendList");
        bazaOnline = FirebaseDatabase.getInstance().getReference().child("Users");

        LinearLayoutManager l = new LinearLayoutManager(getApplicationContext());
        l.setReverseLayout(true);
        l.setStackFromEnd(true);
        onlineList.setLayoutManager(l);

        bazaO = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        bazaO.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot != null){
                    bazaO.child("Online").onDisconnect().setValue("false");
                    bazaO.child("Online").setValue("true");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<User, onlineViewHolder> onlineFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User, onlineViewHolder>(User.class, R.layout.chat_red, onlineViewHolder.class, bazaUser) {
            @Override
            protected void populateViewHolder(final onlineViewHolder viewHolder,final User model,final int position) {

                viewHolder.postaviImeIPrezime(model.getSurnameName());
                viewHolder.postaviSliku(getApplicationContext(), model.getPicture());

                bazaOnline.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        online = dataSnapshot.child(getRef(position).getKey()).child("Online").getValue().toString();

                        if(online.equals("true")){
                            viewHolder.postaviVidljivost(true);
                            viewHolder.postaviBoju(online);
                        }
                        else{
                            viewHolder.postaviVidljivost(false);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.ime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(OnlineActivity.this, getRef(position).getKey() , Toast.LENGTH_SHORT).show();

                        bazaUser.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String idChat = dataSnapshot.child(getRef(position).getKey()).child("IdChat").getValue().toString();
                                Intent intent = new Intent(OnlineActivity.this, ChatActivity.class);
                                intent.putExtra("idChat", idChat);
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }
                });

                viewHolder.profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(OnlineActivity.this, ProfileActivity.class);
                        intent.putExtra("sifra", "nema");
                        intent.putExtra("id", getRef(position).getKey());
                        startActivity(intent);

                    }
                });

            }
        };
        onlineList.setAdapter(onlineFirebaseRecyclerAdapter);
    }


    public static class onlineViewHolder extends RecyclerView.ViewHolder{

        View view;
        TextView ime;
        CircleImageView profilna;
        LinearLayout userRedLinearLayout;
        ImageView profile;
        LinearLayout linearLayout;

        public onlineViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            ime = (TextView) view.findViewById(R.id.ImePrezimeUser);
            profilna = (CircleImageView) view.findViewById(R.id.profilna_user);
            userRedLinearLayout = (LinearLayout) view.findViewById(R.id.userRedLinearLayout);
            profile = (ImageView) view.findViewById(R.id.profile_chat_red);
            linearLayout = (LinearLayout) view.findViewById(R.id.userRedLinearLayout);

        }

        public void postaviImeIPrezime(String imeIPrezime){
            ime.setText(imeIPrezime);
        }

        public void postaviSliku(Context ctx, String slika){

            Picasso.with(ctx).load(slika).resize(150,150).centerCrop().into(profilna);
        }

        public void postaviBoju(String s){

            if(s != null) {
                if (s.equals("true")) {
                    profilna.setBorderColor(Color.parseColor("#90EE90"));

                } else {
                    userRedLinearLayout.setVisibility(View.GONE);
                }
            }
        }

        public void postaviVidljivost(boolean b){
            if(b){
                linearLayout.setVisibility(View.VISIBLE);
            }
            else{
                linearLayout.setVisibility(View.GONE);
            }
        }


    }
}
