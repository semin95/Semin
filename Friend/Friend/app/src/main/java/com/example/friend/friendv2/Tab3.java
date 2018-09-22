package com.example.friend.friendv2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class Tab3 extends Fragment {

    private RecyclerView requestList;
    private DatabaseReference bazaZahtjev;
    private DatabaseReference bazaUser;

    private String senderNameSurname;
    private String senderPicture;
    private String receiverNameSurname;
    private String receiverPicture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3, container, false);

        requestList = (RecyclerView) rootView.findViewById(R.id.requestList);
        requestList.setHasFixedSize(true);

        bazaZahtjev = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("RequestForFriendship");
        bazaUser = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("FriendList");

        LinearLayoutManager l = new LinearLayoutManager(getActivity());
        l.setReverseLayout(true);
        l.setStackFromEnd(true);
        requestList.setLayoutManager(l);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        bazaZahtjev.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){

                    FirebaseRecyclerAdapter<Request, RequestViewHolder> requestFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Request, RequestViewHolder>(Request.class, R.layout.zahtjev_red, RequestViewHolder.class, bazaZahtjev) {
                        @Override
                        protected void populateViewHolder(RequestViewHolder viewHolder, Request model,final int position) {

                            viewHolder.postaviImeIPrezime(model.getSenderSurnameName());
                            viewHolder.postaviSliku(getContext(), model.getSenderPicture());


                            viewHolder.ime.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(getContext(), ProfileActivity.class);
                                    intent.putExtra("sifra", "nema");
                                    intent.putExtra("id", getRef(position).getKey());
                                    getContext().startActivity(intent);

                                }
                            });

                            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(getContext(), ProfileActivity.class);
                                    intent.putExtra("sifra", "nema");
                                    intent.putExtra("id", getRef(position).getKey());
                                    getContext().startActivity(intent);

                                }
                            });

                            viewHolder.add.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    final DatabaseReference bazaUser = FirebaseDatabase.getInstance().getReference().child("Users");
                                    final String sender = getRef(position).getKey();
                                    final String receiver = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    final String idChat = sender + receiver;


                                    bazaUser.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            senderNameSurname = dataSnapshot.child(sender).child("Surname").getValue().toString() + " " + dataSnapshot.child(sender).child("Name").getValue().toString();
                                            senderPicture = dataSnapshot.child(sender).child("Picture").getValue().toString();
                                            receiverNameSurname = dataSnapshot.child(receiver).child("Surname").getValue().toString() + " " + dataSnapshot.child(receiver).child("Name").getValue().toString();
                                            receiverPicture = dataSnapshot.child(receiver).child("Picture").getValue().toString();

                                            //dodavanje u listu prijatelja
                                            bazaUser.child(sender).child("FriendList").child(receiver).child("Picture").setValue(receiverPicture);
                                            bazaUser.child(sender).child("FriendList").child(receiver).child("SurnameName").setValue(receiverNameSurname);
                                            bazaUser.child(sender).child("FriendList").child(receiver).child("IdChat").setValue(idChat);
                                            bazaUser.child(receiver).child("FriendList").child(sender).child("Picture").setValue(senderPicture);
                                            bazaUser.child(receiver).child("FriendList").child(sender).child("SurnameName").setValue(senderNameSurname);
                                            bazaUser.child(receiver).child("FriendList").child(sender).child("IdChat").setValue(idChat);

                                            //dodavanje informacija o korisnicima u chat
                                            DatabaseReference bazaChat = FirebaseDatabase.getInstance().getReference().child("Chat").child(idChat).child("UsersInformation");
                                            bazaChat.child("User1").child("SurnameName").setValue(senderNameSurname);
                                            bazaChat.child("User1").child("Picture").setValue(senderPicture);
                                            bazaChat.child("User1").child("SenderId").setValue(sender);

                                            bazaChat.child("User2").child("SurnameName").setValue(receiverNameSurname);
                                            bazaChat.child("User2").child("Picture").setValue(receiverPicture);
                                            bazaChat.child("User2").child("SenderId").setValue(receiver);

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

                                    //brisanje zahtjeva
                                    bazaUser.child(sender).child("RequestSend").child(receiver).removeValue();
                                    bazaZahtjev.child(sender).removeValue();

                                }
                            });

                            viewHolder.reject.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    DatabaseReference bazaPosiljaoc = FirebaseDatabase.getInstance().getReference().child("Users").child(getRef(position).getKey()).child("RequestSend");
                                    bazaPosiljaoc.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
                                    bazaZahtjev.child(getRef(position).getKey()).removeValue();

                                }
                            });

                        }
                    };
                    requestList.setAdapter(requestFirebaseRecyclerAdapter);

                }
                else{

                    FirebaseRecyclerAdapter<User, userViewHolder> userFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User, userViewHolder>(User.class, R.layout.user_red, userViewHolder.class, bazaUser.orderByChild("SurnameName")) {
                        @Override
                        protected void populateViewHolder(final userViewHolder viewHolder, User model,final int position) {

                            viewHolder.postaviImeIPrezime(model.getSurnameName());
                            viewHolder.postaviSliku(getContext(), model.getPicture());

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
                                    Intent intent = new Intent(getContext(), ProfileActivity.class);
                                    intent.putExtra("sifra", "nema");
                                    intent.putExtra("id", getRef(position).getKey());
                                    getContext().startActivity(intent);
                                }
                            });

                            viewHolder.ime.setOnClickListener(new View.OnClickListener() {
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
                    requestList.setAdapter(userFirebaseRecyclerAdapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder{

        View view;
        ImageView imageView;
        TextView ime;
        ImageView add;
        ImageView reject;

        public RequestViewHolder(View itemView){
            super(itemView);
            view = itemView;

            imageView = (ImageView) view.findViewById(R.id.profilna_user);
            ime = (TextView) view.findViewById(R.id.ImePrezimeZahtjev);
            add = (ImageView) view.findViewById(R.id.add);
            reject = (ImageView) view.findViewById(R.id.reject);

        }

        public void postaviImeIPrezime(String imeIPrezime){
            ime.setText(imeIPrezime);
        }

        public void postaviSliku(Context ctx, String slika){
            Picasso.with(ctx).load(slika).resize(150,150).centerCrop().into(imageView);
        }

    }

    public static class userViewHolder extends RecyclerView.ViewHolder{

        View view;
        CircleImageView profilna;
        TextView ime;

        public userViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            profilna = (CircleImageView) view.findViewById(R.id.profilna_user);
            ime = (TextView) view.findViewById(R.id.ImePrezimeUser);

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

    }

}