package com.example.friend.friendv2;

/**
 * Created by semin on 5/21/17.
 */


import android.content.Context;
import android.content.Intent;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;


public class Tab1 extends Fragment{

    private RecyclerView postLista;
    private DatabaseReference baza;
    private DatabaseReference bazaKorisnik;
    private DatabaseReference bazaLikeDislike;
    boolean like = false;
    boolean dislike = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);


        baza = FirebaseDatabase.getInstance().getReference().child("Post");
        bazaKorisnik = FirebaseDatabase.getInstance().getReference().child("Users");
        bazaKorisnik.keepSynced(true);
        postLista = (RecyclerView)rootView.findViewById(R.id.postList);
        postLista.setHasFixedSize(true);

        LinearLayoutManager l = new LinearLayoutManager(getActivity());
        l.setReverseLayout(true);
        l.setStackFromEnd(true);
        postLista.setLayoutManager(l);

        postLista.setHasFixedSize(true);

        postLista.setItemViewCacheSize(20);
        postLista.setDrawingCacheEnabled(true);
        postLista.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseRecyclerAdapter<Post, PostViewHolder> postFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(Post.class, R.layout.post_red, PostViewHolder.class, baza) {
                @Override
                protected void populateViewHolder(final PostViewHolder viewHolder, Post model, final int position) {

                    final String post_sifra = getRef(position).getKey();

                    viewHolder.postaviOpis(model.getOpis());
                    viewHolder.postaviSliku(getContext(), model.getSlika());
                    viewHolder.postaviImeIPrezime(model.getImeIPrezime());
                    viewHolder.postaviProfilnuSliku(getContext(), model.getProfilnaSlika());
                    viewHolder.postaviDatum(model.getDatum());

                    viewHolder.postaviIkonuZaLike(FirebaseAuth.getInstance().getCurrentUser().getUid(), getRef(position).getKey());
                    viewHolder.postaviIkonuZaDislike(FirebaseAuth.getInstance().getCurrentUser().getUid(), getRef(position).getKey());
                    viewHolder.postaviLikeDislike(getRef(position).getKey());

                    viewHolder.post_imeIPrezime.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //Toast.makeText(getContext(), getRef(position).getKey(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), ProfileActivity.class);
                            intent.putExtra("sifra", getRef(position).getKey());
                            intent.putExtra("id", "nema");
                            getContext().startActivity(intent);
                            getActivity().finish();

                        }
                    });

                    bazaLikeDislike = FirebaseDatabase.getInstance().getReference().child("Post");
                    viewHolder.post_like.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            like = true;
                            bazaLikeDislike.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    if (like) {

                                        if (dataSnapshot.child(getRef(position).getKey()).child("Like").hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                                            bazaLikeDislike.child(getRef(position).getKey()).child("Like").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
                                            like = false;
                                        } else {
                                            bazaLikeDislike.child(getRef(position).getKey()).child("Like").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue("Like");
                                            like = false;
                                        }
                                        bazaLikeDislike.child(getRef(position).getKey()).child("Dislike").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();

                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        }
                    });

                    viewHolder.post_dislike.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dislike = true;
                            bazaLikeDislike.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    if (dislike) {

                                        if (dataSnapshot.child(getRef(position).getKey()).child("Dislike").hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                                            bazaLikeDislike.child(getRef(position).getKey()).child("Dislike").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
                                            dislike = false;
                                        } else {
                                            bazaLikeDislike.child(getRef(position).getKey()).child("Dislike").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue("Dislike");
                                            dislike = false;
                                        }
                                        bazaLikeDislike.child(getRef(position).getKey()).child("Like").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();

                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        }
                    });

                    viewHolder.post_sliku.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(), ProfileActivity.class);
                            getContext().startActivity(intent);
                            getActivity().finish();
                        }
                    });

                }
            };

            postLista.setAdapter(postFirebaseRecyclerAdapter);
        }

    }


    public static class PostViewHolder extends RecyclerView.ViewHolder{

        View view;
        TextView post_imeIPrezime;
        CircleImageView post_sliku;
        ImageButton post_like;
        ImageButton post_dislike;
        TextView like;
        TextView dislike;
        boolean like_b;
        boolean dislike_b;
        boolean likeDislike_b;

        public PostViewHolder(View itemView) {
            super(itemView);
            view = itemView;


            post_imeIPrezime = (TextView) view.findViewById(R.id.post_ime_i_prezime);

            post_sliku = (CircleImageView) view.findViewById(R.id.post_profilna);
            post_like = (ImageButton) view.findViewById(R.id.post_like);
            post_dislike = (ImageButton) view.findViewById(R.id.post_dislike);
            like = (TextView) view.findViewById(R.id.number_of_likes);
            dislike = (TextView) view.findViewById(R.id.number_of_dislike);

        }


        public void postaviOpis(String opis){

            TextView post_opis = (TextView) view.findViewById(R.id.post_opis);
            post_opis.setText(opis);
        }

        public void postaviSliku(Context ctx, String slika){

            ImageView post_sliku = (ImageView) view.findViewById(R.id.slika_post);
            if(!slika.equals("")) {
                Picasso.with(ctx).load(slika)
                        .fit()
                        .into(post_sliku);
                post_sliku.setVisibility(View.VISIBLE);
            }
            else{
                post_sliku.setVisibility(View.GONE);
            }
        }

        public void postaviImeIPrezime(String imeIPrezime){
            post_imeIPrezime.setText(imeIPrezime);
        }

        public void postaviProfilnuSliku(Context ctx, String slika){

            Picasso.with(ctx).load(slika)
                    .resize(150,150)
                    .centerCrop()
                    .into(post_sliku);

        }

        public void postaviDatum(String datum){

            TextView post_datum = (TextView) view.findViewById(R.id.post_datum);
            Long l = Long.parseLong(datum);

            String trenutnoVrijeme = DateFormat.format("dd-MM-yyyy", new Date().getTime()).toString();
            String vrijemePoruke = DateFormat.format("dd-MM-yyyy", l).toString();
            String trenutnoVrijeme2 = DateFormat.format("yyyy", new Date().getTime()).toString();
            String vrijemePoruke2 = DateFormat.format("yyyy", l).toString();


            if(trenutnoVrijeme.equals(vrijemePoruke)){
                CharSequence d = DateFormat.format("HH:mm",l);
                post_datum.setText(d);
            }
            else if(trenutnoVrijeme2.equals(vrijemePoruke2)){
                CharSequence d = DateFormat.format("dd MMM, HH:mm",l);
                post_datum.setText(d);
            }
            else {
                CharSequence d = DateFormat.format("dd MMM yyyy, HH:mm", l);
                post_datum.setText(d);
            }

        }

        public void postaviLikeDislike(String idPost){

            likeDislike_b = true;
            DatabaseReference baza = FirebaseDatabase.getInstance().getReference().child("Post").child(idPost);
            baza.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(likeDislike_b) {
                        like.setText(dataSnapshot.child("Like").getChildrenCount() + "");
                        dislike.setText(dataSnapshot.child("Dislike").getChildrenCount() + "");
                        likeDislike_b = false;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        public void postaviIkonuZaLike(final String id, String idPost){

            like_b = true;
            DatabaseReference baza = FirebaseDatabase.getInstance().getReference().child("Post").child(idPost);
            baza.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if(like_b) {
                        if (dataSnapshot.child("Like").hasChild(id)) {
                            post_like.setImageResource(R.mipmap.ic_thumb_up_blue_24dp);
                        } else {
                            post_like.setImageResource(R.mipmap.ic_thumb_up_black_24dp);
                        }
                        like_b = false;
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        public void postaviIkonuZaDislike(final String id, String idPost){

            dislike_b = true;
            DatabaseReference baza = FirebaseDatabase.getInstance().getReference().child("Post").child(idPost);
            baza.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if(dislike_b) {
                        if (dataSnapshot.child("Dislike").hasChild(id)) {
                            post_dislike.setImageResource(R.mipmap.ic_thumb_down_red_24dp);
                        } else {
                            post_dislike.setImageResource(R.mipmap.ic_thumb_down_black_24dp);
                        }
                        dislike_b = false;
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

    }

}
