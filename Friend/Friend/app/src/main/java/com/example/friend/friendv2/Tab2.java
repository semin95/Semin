package com.example.friend.friendv2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

/**
 * Created by semin on 5/21/17.
 */

public class Tab2 extends Fragment {

    private RecyclerView chatList;
    private DatabaseReference bazaUser;
    private DatabaseReference bazaChat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2, container, false);


        chatList = (RecyclerView) rootView.findViewById(R.id.chatList);
        chatList.setHasFixedSize(true);
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            bazaUser = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("FriendList");
        }

        bazaChat = FirebaseDatabase.getInstance().getReference().child("Chat");

        LinearLayoutManager l = new LinearLayoutManager(getActivity());
        l.setReverseLayout(true);
        l.setStackFromEnd(true);
        chatList.setLayoutManager(l);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseRecyclerAdapter<User, userViewHolder> userFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User, userViewHolder>(User.class, R.layout.chat_red, userViewHolder.class, bazaUser.orderByChild("SurnameName")) {
                @Override
                protected void populateViewHolder(final userViewHolder viewHolder, final User model, final int position) {

                    bazaChat.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            try{
                                if (!dataSnapshot.child(model.getIdChat()).child("Messages").hasChildren()) {
                                    viewHolder.postaviVidljivost(false);
                                }
                                else{
                                    viewHolder.postaviVidljivost(true);
                                    viewHolder.postaviImeIPrezime(model.getSurnameName());
                                    viewHolder.postaviSliku(getContext(), model.getPicture());
                                }
                            }
                            catch (Exception e){

                            }
                            try {
                                String s = dataSnapshot.child(model.getIdChat()).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Seen").getValue().toString();
                                viewHolder.postaviIkonuZaPoruku(s);
                            }
                            catch (Exception e){

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    DatabaseReference bazaOnline = FirebaseDatabase.getInstance().getReference().child("Users");
                    bazaOnline.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            viewHolder.postaviBoju(dataSnapshot.child(getRef(position).getKey()).child("Online").getValue().toString());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    viewHolder.profilna.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bazaUser.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String idChat = dataSnapshot.child(getRef(position).getKey()).child("IdChat").getValue().toString();
                                    Intent intent = new Intent(getContext(), ChatActivity.class);
                                    intent.putExtra("idChat", idChat);
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    });

                    viewHolder.ime.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bazaUser.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String idChat = dataSnapshot.child(getRef(position).getKey()).child("IdChat").getValue().toString();
                                    Intent intent = new Intent(getContext(), ChatActivity.class);
                                    intent.putExtra("idChat", idChat);
                                    startActivity(intent);
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

                            Intent intent = new Intent(getContext(), ProfileActivity.class);
                            intent.putExtra("sifra", "nema");
                            intent.putExtra("id", getRef(position).getKey());
                            getContext().startActivity(intent);

                        }
                    });

                }
            };
            chatList.setAdapter(userFirebaseRecyclerAdapter);
        }
    }


    public static class userViewHolder extends RecyclerView.ViewHolder{

        View view;
        CircleImageView profilna;
        TextView ime;
        ImageView profile;
        ImageView poruka;
        LinearLayout linearLayout;

        public userViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            profilna = (CircleImageView) view.findViewById(R.id.profilna_user);
            ime = (TextView) view.findViewById(R.id.ImePrezimeUser);
            profile = (ImageView) view.findViewById(R.id.profile_chat_red);
            poruka = (ImageView) view.findViewById(R.id.poruka_chat_red);
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
                    profilna.setBorderColor(Color.parseColor("#C0C0C0"));
                }
            }
        }

        public void postaviIkonuZaPoruku(String s){
            if(s.equals("false")){
                poruka.setVisibility(View.VISIBLE);
            }
            else{
                poruka.setVisibility(View.GONE);
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

