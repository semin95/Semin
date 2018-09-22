package com.example.friend.friendv2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SetupAdditional extends AppCompatActivity {

    private Button button;
    private FirebaseAuth auth;
    private DatabaseReference bazaKorisnik;
    private ProgressDialog progres;
    private EditText currentCity;
    private EditText hometown;
    private EditText college;
    private EditText highSchool;
    private EditText work;
    private EditText relationshipStatus;
    private EditText describe;
    private DatabaseReference bazaUser;
    private String online_s;

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_additional);

        try {
            Intent intent = getIntent();
            online_s = intent.getExtras().getString("Online");
        }
        catch (Exception e){

        }
        button = (Button) findViewById(R.id.setup_button);
        auth = FirebaseAuth.getInstance();
        bazaKorisnik = FirebaseDatabase.getInstance().getReference().child("Users");

        currentCity = (EditText) findViewById(R.id.currentCity);
        hometown = (EditText) findViewById(R.id.hometown);
        college = (EditText) findViewById(R.id.college);
        highSchool = (EditText) findViewById(R.id.highScool);
        work = (EditText) findViewById(R.id.work);
        relationshipStatus = (EditText) findViewById(R.id.relationshipStatus);
        describe = (EditText) findViewById(R.id.describe);

        if(FirebaseAuth.getInstance().getCurrentUser() != null) {

                bazaUser = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Additional");
                bazaUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child("CurrentCity").getValue() != null) {
                            currentCity.setText(dataSnapshot.child("CurrentCity").getValue().toString());
                        }
                        if(dataSnapshot.child("Hometown").getValue() != null) {
                            hometown.setText(dataSnapshot.child("Hometown").getValue().toString());
                        }
                        if(dataSnapshot.child("College").getValue() != null) {
                            college.setText(dataSnapshot.child("College").getValue().toString());
                        }
                        if(dataSnapshot.child("HighSchool").getValue() != null) {
                            highSchool.setText(dataSnapshot.child("HighSchool").getValue().toString());
                        }
                        if(dataSnapshot.child("Work").getValue() != null) {
                            work.setText(dataSnapshot.child("Work").getValue().toString());
                        }
                        if(dataSnapshot.child("RelationshipStatus").getValue() != null) {
                            relationshipStatus.setText(dataSnapshot.child("RelationshipStatus").getValue().toString());
                        }
                        if(dataSnapshot.child("Describe").getValue() != null) {
                            describe.setText(dataSnapshot.child("Describe").getValue().toString());
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        }
        progres = new ProgressDialog(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submit();

            }
        });

    }

    private void submit() {

        String id = auth.getCurrentUser().getUid();
        String currentCity_s = currentCity.getText().toString().trim();
        String hometown_s = hometown.getText().toString().trim();
        String college_s = college.getText().toString().trim();
        String highSchool_s = highSchool.getText().toString().trim();
        String work_s = work.getText().toString().trim();
        String relationshipStatus_s = relationshipStatus.getText().toString().trim();
        String describe_s = describe.getText().toString().trim();

        progres.setMessage("Finishing Setup ...");
        progres.show();

        bazaKorisnik.child(id).child("Additional").child("CurrentCity").setValue(currentCity_s);
        bazaKorisnik.child(id).child("Additional").child("Hometown").setValue(hometown_s);
        bazaKorisnik.child(id).child("Additional").child("College").setValue(college_s);
        bazaKorisnik.child(id).child("Additional").child("HighSchool").setValue(highSchool_s);
        bazaKorisnik.child(id).child("Additional").child("Work").setValue(work_s);
        bazaKorisnik.child(id).child("Additional").child("RelationshipStatus").setValue(relationshipStatus_s);
        bazaKorisnik.child(id).child("Additional").child("Describe").setValue(describe_s);

        String s = bazaKorisnik.child(id).child("Additional").child("Describe").getKey();

        progres.dismiss();

        if(!online_s.equals("true")) {
            Intent intent = new Intent(SetupAdditional.this, SetupActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        else{
            finish();
        }
    }
}
