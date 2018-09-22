package com.example.friend.friendv2;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.vision.text.Text;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {

    private static int SIGN_IN_REQUEST_CODE = 1;
    private RelativeLayout activity_main;
    private FloatingActionButton send;
    private String idChat;
    private DatabaseReference bazaKorisnik;
    private DatabaseReference bazaChatI;
    private DatabaseReference bazaOnline;
    private String sender;
    private String receiver;
    private String senderID;
    private String receiverID;
    private String slika;
    private boolean additionalInformation = false;
    private boolean predhodnaPoruka = false;
    private RecyclerView chat;
    private String surnameName;
    private String idPrijavljenogKorisnika;
    private CircleImageView predhodnaSlika;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        idPrijavljenogKorisnika = FirebaseAuth.getInstance().getCurrentUser().getUid();

        activity_main = (RelativeLayout) findViewById(R.id.activity_chat);
        Intent intent = getIntent();
        idChat = intent.getExtras().getString("idChat");
        bazaKorisnik = FirebaseDatabase.getInstance().getReference().child("Users");
        chat = (RecyclerView) findViewById(R.id.chat);

        LinearLayoutManager l = new LinearLayoutManager(getApplicationContext());
        chat.setLayoutManager(l);

        bazaOnline = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        bazaOnline.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot != null){
                    bazaOnline.child("Online").onDisconnect().setValue("false");
                    bazaOnline.child("Online").setValue("true");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        bazaChatI = FirebaseDatabase.getInstance().getReference().child("Chat").child(idChat).child("UsersInformation");
        bazaChatI.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String p = dataSnapshot.child("User1").child("SenderId").getValue().toString();
                if(p.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                    receiver = "User1";
                    sender = "User2";
                }
                else{
                    receiver = "User2";
                    sender = "User1";
                }

                senderID = dataSnapshot.child(sender).child("SenderId").getValue().toString();
                receiverID = dataSnapshot.child(receiver).child("SenderId").getValue().toString();

                setTitle(dataSnapshot.child(sender).child("SurnameName").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        send = (FloatingActionButton) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText input = (EditText)findViewById(R.id.input);
                FirebaseDatabase.getInstance().getReference().child("Chat").child(idChat).child("Messages").push().setValue(new ChatMessage(input.getText().toString(),
                        idPrijavljenogKorisnika, "false"));
                input.setText("");
                FirebaseDatabase.getInstance().getReference().child("Chat").child(idChat).child(senderID).child("Seen").setValue("false");

            }
        });

        if(FirebaseAuth.getInstance().getCurrentUser() == null){

            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), SIGN_IN_REQUEST_CODE);

        }
        else{
            displayChatMessage();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent intent;
        switch (id){

            case R.id.action_openProfile:
                intent = new Intent(ChatActivity.this, ProfileActivity.class);
                intent.putExtra("sifra", "nema");
                intent.putExtra("id", senderID);
                startActivity(intent);
                finish();
                break;


            default:

        }

        return super.onOptionsItemSelected(item);
    }

    private void displayChatMessage() {

        final FirebaseRecyclerAdapter<ChatMessage, chatViewHolder> chatFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ChatMessage, chatViewHolder>(ChatMessage.class, R.layout.list_item, chatViewHolder.class, FirebaseDatabase.getInstance().getReference().child("Chat").child(idChat).child("Messages")) {
            @Override
            protected void populateViewHolder(final chatViewHolder viewHolder,final ChatMessage model,final int position) {

                viewHolder.postaviTekstPoruke(model.getMessageText());
                viewHolder.postaviSeen(model.getSeen());
                viewHolder.postaviPozicijuSeen(model.getSenderId(), senderID);

                if(!receiverID.equals(model.getSenderId())) {
                    FirebaseDatabase.getInstance().getReference().child("Chat").child(idChat).child(receiverID).child("Seen").setValue("true");
                }
                if(predhodnaPoruka && receiverID.equals(model.getSenderId())){
                    viewHolder.postaviMarginTop();
                }
                else{
                    predhodnaPoruka = false;
                }

                if(!predhodnaPoruka && senderID.equals(model.getSenderId())){
                    viewHolder.postaviMarginTop();
                }

                String trenutnoVrijeme = DateFormat.format("dd-MM-yyyy", new Date().getTime()).toString();
                String vrijemePoruke = DateFormat.format("dd-MM-yyyy", model.getMessageTime()).toString();
                String trenutnoVrijeme2 = DateFormat.format("yyyy", new Date().getTime()).toString();
                String vrijemePoruke2 = DateFormat.format("yyyy", model.getMessageTime()).toString();
                if(trenutnoVrijeme.equals(vrijemePoruke)){
                    CharSequence datum = DateFormat.format("HH:mm",model.getMessageTime());
                    viewHolder.postaviDatumPoruke(datum);
                }
                else if(trenutnoVrijeme2.equals(vrijemePoruke2)){
                    CharSequence datum = DateFormat.format("dd MMM, HH:mm",model.getMessageTime());
                    viewHolder.postaviDatumPoruke(datum);
                }
                else {
                    CharSequence datum = DateFormat.format("dd MMM yyyy, HH:mm", model.getMessageTime());
                    viewHolder.postaviDatumPoruke(datum);
                }


                bazaKorisnik.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        slika = dataSnapshot.child(model.getSenderId()).child("Picture").getValue().toString();
                        viewHolder.postaviSliku(getApplicationContext(), slika);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                if(model.getSenderId().equals(receiverID)){
                    viewHolder.postaviVidljivostSlike(false);
                    viewHolder.poravnanjeTeksta();
                    predhodnaPoruka = true;
                }
                else{
                    viewHolder.izgledPrimljenePoruke();
                }

                viewHolder.tekstPoruke.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        viewHolder.postaviDodatneInformacije(additionalInformation);
                        if(!additionalInformation){
                            additionalInformation = true;
                        }
                        else{
                            additionalInformation = false;
                        }

                    }
                });

                if(senderID.equals(model.getSenderId())){
                    FirebaseDatabase.getInstance().getReference().child("Chat").child(idChat).child("Messages").child(getRef(position).getKey()).child("seen").setValue("true");
                }


            }
        };
        chat.setAdapter(chatFirebaseRecyclerAdapter);
    }


    public static class chatViewHolder extends RecyclerView.ViewHolder{

        View view;
        TextView tekstPoruke;
        TextView datumPoruke;
        TextView seen;
        CircleImageView profilna;
        LinearLayout linearLayout;

        public chatViewHolder(View itemView){
            super(itemView);
            view = itemView;

            tekstPoruke = (TextView)view.findViewById(R.id.tekstPoruke);
            seen = (TextView) view.findViewById(R.id.seenPoruke);
            datumPoruke = (TextView) view.findViewById(R.id.datumPoruke);
            profilna = (CircleImageView) view.findViewById(R.id.profilna_user);
            linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);

        }

        public void postaviTekstPoruke(String s){
            tekstPoruke.setText(s);
        }

        public void postaviDatumPoruke(CharSequence s){
            datumPoruke.setText(s);
        }

        public void postaviSeen(String s){
            if(s.equals("true")){
                seen.setText("Seen");
            }
            else{
                seen.setText("Nije vidjeno");
            }
        }

        public void postaviPozicijuSeen(String id, String senderId){
            if(id.equals(senderId)){
                //lijevo
                seen.setGravity(Gravity.LEFT);
                seen.setPadding(230,5,0,5);
            }
            else{
                //desno
                seen.setGravity(Gravity.RIGHT);
                seen.setPadding(0,5,100,5);
            }
        }

        public void postaviVidljivostSlike(boolean b){
            if(!b){
                profilna.setVisibility(View.INVISIBLE);
            }
            else{
                profilna.setVisibility(View.VISIBLE);
            }
        }

        public void postaviSliku(Context ctx, String slika){


            Picasso.with(ctx).load(slika).resize(150,150).centerCrop().into(profilna);
        }

        public void postaviDodatneInformacije(boolean b) {
            if(!b){
                datumPoruke.setVisibility(View.VISIBLE);
                seen.setVisibility(View.VISIBLE);
            }
            else{
                datumPoruke.setVisibility(View.GONE);
                seen.setVisibility(View.GONE);
            }
        }

        public void postaviMarginTop() {
            linearLayout.setPadding(5,0,5,0);
        }

        public void poravnanjeTeksta() {

        }

        public void izgledPrimljenePoruke() {
            tekstPoruke.setBackgroundResource(R.drawable.bg_grey);
        }
    }
}
