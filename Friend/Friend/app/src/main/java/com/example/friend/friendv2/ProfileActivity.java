package com.example.friend.friendv2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class ProfileActivity extends AppCompatActivity {

    private DatabaseReference bazaPost;
    private DatabaseReference bazaUsers;
    private String ime = "";
    private String prezime = "";
    private String slikaProfila = "";
    private String userId = "";
    private String college = "";
    private String highSchool = "";
    private String work = "";
    private String relationshipStatus = "";
    private String currentCity = "";
    private String hometownCity = "";
    private String describe = "";

    private TextView collegeT;
    private TextView highSchoolT;
    private TextView workT;
    private TextView relationshipStatusT;
    private TextView currentCityT;
    private TextView hometownCityT;
    private TextView aboutProfile;

    private TextView imeIPrezime;
    private CircleImageView profilna;


    private Button zahtjev;
    private DatabaseReference bazaZahtjev;
    private DatabaseReference bazaO;
    private String surnameName;
    private String picture;
    private RecyclerView postList;
    private boolean like;
    private boolean dislike;
    private DatabaseReference bazaLikeDislike;
    private boolean b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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

        collegeT = (TextView) findViewById(R.id.faculty_profile);
        highSchoolT = (TextView) findViewById(R.id.school_profile);
        workT = (TextView) findViewById(R.id.work_profile);
        relationshipStatusT = (TextView) findViewById(R.id.relationship_profile);
        currentCityT = (TextView) findViewById(R.id.currentCuty_profile);
        hometownCityT = (TextView) findViewById(R.id.hometown_profile);
        aboutProfile = (TextView) findViewById(R.id.about_profile);

        imeIPrezime = (TextView) findViewById(R.id.profile_ime);
        profilna = (CircleImageView) findViewById(R.id.profilna_user);

        Intent intent = getIntent();
        final String sifra = intent.getExtras().getString("sifra");
        final String ID = intent.getExtras().getString("id");

        postList = (RecyclerView) findViewById(R.id.postList);

        LinearLayoutManager l = new LinearLayoutManager(getApplicationContext());
        l.setReverseLayout(true);
        l.setStackFromEnd(true);
        postList.setLayoutManager(l);

        postList.setHasFixedSize(true);

        postList.setItemViewCacheSize(20);
        postList.setDrawingCacheEnabled(true);
        postList.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        bazaPost = FirebaseDatabase.getInstance().getReference().child("Post");

        if(!sifra.equals("nema")) {

            b = true;
            bazaPost.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    userId = dataSnapshot.child(sifra).child("Id").getValue().toString();
                    if(b){
                        popuniPosts();
                        b = false;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else{
            userId = ID;
            popuniPosts();
        }

        bazaUsers = FirebaseDatabase.getInstance().getReference().child("Users");
        bazaUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ime = dataSnapshot.child(userId).child("Name").getValue().toString();
                prezime = dataSnapshot.child(userId).child("Surname").getValue().toString();
                imeIPrezime.setText(prezime + " " + ime);

                slikaProfila = dataSnapshot.child(userId).child("Picture").getValue().toString();

                Picasso.with(ProfileActivity.this).load(slikaProfila).into(profilna);

                boolean ima = false;
                if(dataSnapshot.child(userId).child("Additional").child("College").getValue() != null) {
                    college = dataSnapshot.child(userId).child("Additional").child("College").getValue().toString();
                    ima = true;
                }
                if(dataSnapshot.child(userId).child("Additional").child("HighSchool").getValue() != null) {
                    highSchool = dataSnapshot.child(userId).child("Additional").child("HighSchool").getValue().toString();
                    ima = true;
                }
                if(dataSnapshot.child(userId).child("Additional").child("Work").getValue() != null) {
                    work = dataSnapshot.child(userId).child("Additional").child("Work").getValue().toString();
                    ima = true;
                }
                if(dataSnapshot.child(userId).child("Additional").child("RelationshipStatus").getValue() != null) {
                    relationshipStatus = dataSnapshot.child(userId).child("Additional").child("RelationshipStatus").getValue().toString();
                    ima = true;
                }
                if(dataSnapshot.child(userId).child("Additional").child("CurrentCity").getValue() != null) {
                    currentCity = dataSnapshot.child(userId).child("Additional").child("CurrentCity").getValue().toString();
                    ima = true;
                }
                if(dataSnapshot.child(userId).child("Additional").child("Hometown").getValue() != null) {
                    hometownCity = dataSnapshot.child(userId).child("Additional").child("Hometown").getValue().toString();
                    ima = true;
                }
                if(dataSnapshot.child(userId).child("Additional").child("Describe").getValue() != null) {
                    describe = dataSnapshot.child(userId).child("Additional").child("Describe").getValue().toString();
                    ima = true;
                }
                if(!ima){
                    aboutProfile.setVisibility(View.GONE);
                }

                if(!college.isEmpty()){
                    collegeT.setText("Studied at " + college);
                }
                else{
                    RelativeLayout r = (RelativeLayout) findViewById(R.id.fakultet_profile);
                    r.setVisibility(View.GONE);
                }

                if(!highSchool.isEmpty()){
                    highSchoolT.setText("Went to " + highSchool);
                }
                else{
                    RelativeLayout r = (RelativeLayout) findViewById(R.id.skola_profile);
                    r.setVisibility(View.GONE);
                }

                if(!work.isEmpty()){
                    workT.setText("Works in " + work);
                }
                else{
                    RelativeLayout r = (RelativeLayout) findViewById(R.id.posao_profile);
                    r.setVisibility(View.GONE);
                }

                if(!relationshipStatus.isEmpty()){
                    relationshipStatusT.setText(relationshipStatus);
                }
                else{
                    RelativeLayout r = (RelativeLayout) findViewById(R.id.status_profile);
                    r.setVisibility(View.GONE);
                }

                if(!currentCity.isEmpty()){
                    currentCityT.setText("Lives in " + currentCity);
                }
                else{
                    RelativeLayout r = (RelativeLayout) findViewById(R.id.trenutniGrad_profile);
                    r.setVisibility(View.GONE);
                }

                if(!hometownCity.isEmpty()){
                    hometownCityT.setText("From " + hometownCity);
                }
                else{
                    RelativeLayout r = (RelativeLayout) findViewById(R.id.iz_profile);
                    r.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        profilna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChat();
            }
        });

        final String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        zahtjev = (Button) findViewById(R.id.posaljiZahtjev);
        final CircleImageView online = (CircleImageView) findViewById(R.id.online);
        final TextView profile_online = (TextView) findViewById(R.id.profile_online);
        DatabaseReference baza = FirebaseDatabase.getInstance().getReference().child("Users");
        baza.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(id).child("FriendList").hasChild(userId)){
                    zahtjev.setVisibility(View.GONE);
                    if(dataSnapshot.child(userId).child("Online").getValue().toString().equals("true")){
                        //stavi zelenu
                        online.setBorderColor(Color.parseColor("#90EE90"));
                        profile_online.setText("Online");
                    }
                    else{
                        //sivu i offline
                        online.setBorderColor(Color.parseColor("#D3D3D3"));
                        profile_online.setText("Offline");
                    }
                    online.setVisibility(View.VISIBLE);
                    profile_online.setVisibility(View.VISIBLE);
                }
                else{
                    online.setVisibility(View.GONE);
                    profile_online.setVisibility(View.GONE);

                    if(userId.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        zahtjev.setVisibility(View.GONE);
                    }
                    else{
                        zahtjev.setVisibility(View.VISIBLE);
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        bazaZahtjev = FirebaseDatabase.getInstance().getReference().child("Users");
        //samo dodati child("BasicInformation")
        bazaZahtjev.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                surnameName = dataSnapshot.child(id).child("Surname").getValue().toString() + " " + dataSnapshot.child(id).child("Name").getValue().toString();
                picture = dataSnapshot.child(id).child("Picture").getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        zahtjev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bazaZahtjev.child(userId).child("RequestForFriendship").child(id).child("SenderSurnameName").setValue(surnameName);
                bazaZahtjev.child(userId).child("RequestForFriendship").child(id).child("SenderPicture").setValue(picture);

                bazaUsers.child(id).child("RequestSend").child(userId).child("Status").setValue("Send");
            }
        });

    }

    public void popuniPosts(){

        FirebaseRecyclerAdapter<Post, PostViewHolder> postFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(Post.class, R.layout.post_red, PostViewHolder.class, bazaPost.orderByChild("Id").equalTo(userId)) {
            @Override
            protected void populateViewHolder(final PostViewHolder viewHolder, Post model,final int position) {

                viewHolder.postaviOpis(model.getOpis());
                viewHolder.postaviSliku(ProfileActivity.this, model.getSlika());
                viewHolder.postaviImeIPrezime(model.getImeIPrezime());
                viewHolder.postaviProfilnuSliku(ProfileActivity.this, model.getProfilnaSlika());
                viewHolder.postaviDatum(model.getDatum());

                viewHolder.postaviIkonuZaLike(FirebaseAuth.getInstance().getCurrentUser().getUid(), getRef(position).getKey());
                viewHolder.postaviIkonuZaDislike(FirebaseAuth.getInstance().getCurrentUser().getUid(), getRef(position).getKey());
                viewHolder.postaviLikeDislike(getRef(position).getKey());


                viewHolder.post_imeIPrezime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                        intent.putExtra("sifra", getRef(position).getKey());
                        intent.putExtra("id", "nema");
                        startActivity(intent);

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

                                if(like){

                                    if(dataSnapshot.child(getRef(position).getKey()).child("Like").hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                                        bazaLikeDislike.child(getRef(position).getKey()).child("Like").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
                                        like = false;
                                    }
                                    else{
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

                                if(dislike){

                                    if(dataSnapshot.child(getRef(position).getKey()).child("Dislike").hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                                        bazaLikeDislike.child(getRef(position).getKey()).child("Dislike").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
                                        dislike = false;
                                    }
                                    else{
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

            }
        };

        postList.setAdapter(postFirebaseRecyclerAdapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.action_chat:
                openChat();
                break;
            case R.id.action_delete_friend:
                deleteFriend();
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    public void openChat(){

        DatabaseReference baza = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("FriendList").child(userId);
        baza.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String idChat = dataSnapshot.child("IdChat").getValue().toString();

                Intent intent = new Intent(ProfileActivity.this, ChatActivity.class);
                intent.putExtra("idChat", idChat);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void deleteFriend(){

        DatabaseReference baza = FirebaseDatabase.getInstance().getReference().child("Users");
        baza.child(userId).child("FriendList").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
        baza.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("FriendList").child(userId).removeValue();

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
            Picasso.with(ctx).load(slika).resize(150,150).centerCrop().into(post_sliku);
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
