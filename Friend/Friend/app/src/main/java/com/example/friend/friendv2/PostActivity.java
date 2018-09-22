package com.example.friend.friendv2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.Date;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


public class PostActivity extends AppCompatActivity {

    private ImageButton addImage;
    private EditText postDescription;
    private Button submitPost;
    private static final int zahtjev = 1;
    private Uri slikaUri = null;
    private StorageReference pohranaS;
    private ProgressDialog progres;
    private DatabaseReference pohranaBaza;
    private FirebaseAuth auth;
    private FirebaseUser korisnik;
    private DatabaseReference korisnikBaza;
    private DatabaseReference bazaOnline;
    private DatabaseReference bazaO;

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

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

        pohranaS = FirebaseStorage.getInstance().getReference();
        pohranaBaza = FirebaseDatabase.getInstance().getReference().child("Post");
        progres = new ProgressDialog(this);

        addImage = (ImageButton) findViewById(R.id.addImage);
        postDescription = (EditText) findViewById(R.id.postDescription);
        submitPost = (Button) findViewById(R.id.submitPost);

        auth = FirebaseAuth.getInstance();
        korisnik = auth.getCurrentUser();
        korisnikBaza = FirebaseDatabase.getInstance().getReference().child("Users").child(korisnik.getUid());
        bazaOnline = FirebaseDatabase.getInstance().getReference().child("Users");

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, zahtjev);

            }
        });

        submitPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submit_post();

            }
        });

    }

    private void submit_post() {

        progres.setMessage("Posting ...");
        progres.show();

        final String opis = postDescription.getText().toString().trim();

        if(!TextUtils.isEmpty(opis) && slikaUri != null){

            StorageReference putanja = pohranaS.child("Pictures").child(slikaUri.getLastPathSegment());
            putanja.putFile(slikaUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    final Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    final DatabaseReference novaObjava = pohranaBaza.push();

                    korisnikBaza.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            novaObjava.child("Opis").setValue(opis);
                            novaObjava.child("Slika").setValue(downloadUrl.toString());
                            novaObjava.child("Id").setValue(korisnik.getUid());
                            String imeIPrezime = dataSnapshot.child("Name").getValue().toString() + dataSnapshot.child("Surname").getValue().toString();
                            novaObjava.child("ImeIPrezime").setValue(imeIPrezime);
                            novaObjava.child("ProfilnaSlika").setValue(dataSnapshot.child("Picture").getValue());

                            long d = new Date().getTime();
                            String vrijemePoruke = d + "";
                            novaObjava.child("Datum").setValue(vrijemePoruke);


                            novaObjava.child("Ime").setValue(dataSnapshot.child("ime").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){

                                        Intent intent = new Intent(PostActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                    }

                                }
                            });


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {



                        }
                    });
                    progres.dismiss();



                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            Toast.makeText(PostActivity.this, "Nije uspjelo", Toast.LENGTH_SHORT).show();
                        }
                    });

        }
        else if(!TextUtils.isEmpty(opis)){

            final DatabaseReference novaObjava = pohranaBaza.push();

            korisnikBaza.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    novaObjava.child("Opis").setValue(opis);
                    novaObjava.child("Slika").setValue("");
                    novaObjava.child("Id").setValue(korisnik.getUid());
                    String imeIPrezime = dataSnapshot.child("Name").getValue().toString() + dataSnapshot.child("Surname").getValue().toString();
                    novaObjava.child("ImeIPrezime").setValue(imeIPrezime);
                    novaObjava.child("ProfilnaSlika").setValue(dataSnapshot.child("Picture").getValue());

                    long d = new Date().getTime();
                    String vrijemePoruke = d + "";
                    novaObjava.child("Datum").setValue(vrijemePoruke);


                    novaObjava.child("Ime").setValue(dataSnapshot.child("ime").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){

                                Intent intent = new Intent(PostActivity.this, MainActivity.class);
                                startActivity(intent);

                            }

                        }
                    });


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {



                }
            });
            progres.dismiss();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == zahtjev && resultCode == RESULT_OK){

            slikaUri = data.getData();
            CropImage.activity(slikaUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setOutputCompressQuality(30)
                    .start(this);

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                slikaUri = result.getUri();
                addImage.setImageURI(slikaUri);


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();

            }

        }

    }
}
