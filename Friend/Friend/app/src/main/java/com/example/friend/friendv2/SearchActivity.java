package com.example.friend.friendv2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class SearchActivity extends AppCompatActivity {

    private EditText unos_t;
    private ImageButton button;
    private DatabaseReference bazaO;
    private RecyclerView friendList;
    private DatabaseReference baza;
    private ProgressDialog progres;

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        unos_t = (EditText) findViewById(R.id.unos_search);
        button = (ImageButton) findViewById(R.id.search);
        baza = FirebaseDatabase.getInstance().getReference().child("Users");
        progres = new ProgressDialog(this);
        friendList = (RecyclerView) findViewById(R.id.friendList);
        LinearLayoutManager l = new LinearLayoutManager(getApplicationContext());
        l.setReverseLayout(true);
        l.setStackFromEnd(true);
        friendList.setLayoutManager(l);

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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progres.setMessage("Searching ...");
                progres.show();
                final String unos = unos_t.getText().toString().trim();

                FirebaseRecyclerAdapter<Friend, friendViewHolder> friendFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Friend, friendViewHolder>(Friend.class, R.layout.friend_red, friendViewHolder.class, baza) {
                    @Override
                    protected void populateViewHolder(friendViewHolder viewHolder, Friend model,final int position) {
                        viewHolder.postaviVidljivost(false);

                        if(viewHolder.pretraga(unos, model.getName(), model.getSurname())){
                            viewHolder.postaviImeIPrezime(model.getSurname() + " " + model.getName());
                            viewHolder.postaviSliku(getApplicationContext() ,model.getPicture());
                            viewHolder.postaviVidljivost(true);
                        }

                        viewHolder.view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(SearchActivity.this, ProfileActivity.class);
                                intent.putExtra("sifra", "nema");
                                intent.putExtra("id", getRef(position).getKey());
                                startActivity(intent);
                                //finish();
                            }
                        });
                    }
                };

                friendList.setAdapter(friendFirebaseRecyclerAdapter);
                progres.dismiss();

            }
        });

    }

    public static class friendViewHolder extends RecyclerView.ViewHolder{

        View view;
        LinearLayout linearLayout;
        TextView ime;
        CircleImageView profilna;

        public friendViewHolder(View itemView){
            super(itemView);
            view = itemView;

            linearLayout = (LinearLayout) view.findViewById(R.id.friendRedLinearLayout);
            ime = (TextView) view.findViewById(R.id.ImePrezimeFriend);
            profilna = (CircleImageView) view.findViewById(R.id.profilna_friend);
        }

        public void postaviImeIPrezime(String imeIPrezime){
            ime.setText(imeIPrezime);
        }

        public void postaviSliku(Context ctx, String slika){

            Picasso.with(ctx).load(slika).resize(150,150).centerCrop().into(profilna);
        }

        public void postaviVidljivost(boolean b){
            if(b){
                linearLayout.setVisibility(View.VISIBLE);
            }
            else{
                linearLayout.setVisibility(View.GONE);
            }
        }

        public boolean pretraga(String unos, String ime, String prezime){

            unos = unos.toLowerCase();
            ime = ime.toLowerCase();
            prezime = prezime.toLowerCase();

            if(ime.startsWith(unos) || prezime.startsWith(unos)){
                return true;
            }

            String imeIPrezime = ime + " " + prezime;
            String prezimeIIme = prezime + " " + ime;

            if(imeIPrezime.startsWith(unos) || prezimeIIme.startsWith(unos)){
                return true;
            }

            return false;
        }

    }

}
