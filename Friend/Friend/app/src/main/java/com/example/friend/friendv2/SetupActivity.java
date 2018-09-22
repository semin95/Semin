package com.example.friend.friendv2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class SetupActivity extends AppCompatActivity {

    private ImageButton profilna;
    private Button button;
    private static final int zahtjev_slika = 1;
    private Uri slikaUri = null;
    private DatabaseReference bazaKorisnik;
    private FirebaseAuth auth;
    private StorageReference pohrana;
    private ProgressDialog progres;
    private String online_s;

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        try {
            Intent intent = getIntent();
            online_s = intent.getExtras().getString("Online");
        }
        catch (Exception e){

        }

        profilna = (ImageButton) findViewById(R.id.setup_profilna);
        button = (Button) findViewById(R.id.setup_button);
        bazaKorisnik = FirebaseDatabase.getInstance().getReference().child("Users");
        auth = FirebaseAuth.getInstance();
        pohrana = FirebaseStorage.getInstance().getReference().child("Slike profila");
        progres = new ProgressDialog(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setupAccount();

            }
        });

        profilna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, zahtjev_slika);

            }
        });

    }

    private void setupAccount() {

        final String id = auth.getCurrentUser().getUid();

        if(slikaUri != null){

            progres.setMessage("Finishing Setup ...");
            progres.show();

            StorageReference putanja = pohrana.child(slikaUri.getLastPathSegment());
            putanja.putFile(slikaUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    String uri = taskSnapshot.getDownloadUrl().toString();
                    bazaKorisnik.child(id).child("Picture").setValue(uri);
                    progres.dismiss();
                    Intent intent = new Intent(SetupActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }
            });

        }

        if(slikaUri == null && online_s.equals("true")){
            Intent intent = new Intent(SetupActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == zahtjev_slika && resultCode == RESULT_OK){

            Uri slika_Uri = data.getData();
            CropImage.activity(slika_Uri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                slikaUri = result.getUri();
                profilna.setImageURI(slikaUri);


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();

            }

        }

    }
}
