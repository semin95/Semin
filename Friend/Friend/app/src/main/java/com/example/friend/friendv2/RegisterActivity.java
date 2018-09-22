    package com.example.friend.friendv2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

    public class RegisterActivity extends AppCompatActivity {

    private EditText ime;
    private EditText prezime;
    private EditText email;
    private EditText password;
    private EditText confirm_password;
    private RadioButton zenski;
    private EditText godinaRodjenja;
    private Button create_profile;
    private FirebaseAuth auth;
    private ProgressDialog progres;
    private DatabaseReference baza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ime = (EditText) findViewById(R.id.ime);
        prezime = (EditText) findViewById(R.id.prezime);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
        zenski = (RadioButton) findViewById(R.id.spol_muski);
        zenski.setChecked(true);
        godinaRodjenja = (EditText) findViewById(R.id.birthday);
        create_profile = (Button) findViewById(R.id.create_profile);
        auth = FirebaseAuth.getInstance();
        progres = new ProgressDialog(this);
        baza = FirebaseDatabase.getInstance().getReference().child("Users");

        create_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                zapocniRegistraciju();

            }
        });

    }

        @Override
        public void onBackPressed() {

            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

            super.onBackPressed();
        }

        private void zapocniRegistraciju(){

        final String ime_s = ime.getText().toString().trim();
        final String prezime_s = prezime.getText().toString().trim();
        String email_s = email.getText().toString().trim();
        String password_s = password.getText().toString().trim();
        String confirm_password_s = confirm_password.getText().toString().trim();
        final String spol;
        final String godinaRodjenja_s = godinaRodjenja.getText().toString().trim();

        if(zenski.isChecked()){
            spol = "Female";
        }
        else{
            spol = "Male";
        }

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(!(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)){
            Toast.makeText(RegisterActivity.this, "Check connection!", Toast.LENGTH_SHORT).show();
            return;
        }

        /*if(!password_s.equals(confirm_password_s)){
            Toast.makeText(RegisterActivity.this, "Passwords didn't match!", Toast.LENGTH_SHORT).show();
            return;
        }*/

        if(!godinaRodjenja_s.matches("^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$")){
            Toast.makeText(RegisterActivity.this, "Incorrect date format!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!TextUtils.isEmpty(ime_s) && !TextUtils.isEmpty(prezime_s) && !TextUtils.isEmpty(email_s) && !TextUtils.isEmpty(password_s) && !TextUtils.isEmpty(godinaRodjenja_s)){

            progres.setMessage("Signin up ...");
            progres.show();

            auth.createUserWithEmailAndPassword(email_s, password_s).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        String id = auth.getCurrentUser().getUid();
                        DatabaseReference trenutniKorisnik = baza.child(id);
                        trenutniKorisnik.child("Name").setValue(ime_s);
                        trenutniKorisnik.child("Surname").setValue(prezime_s);
                        trenutniKorisnik.child("Sex").setValue(spol);
                        trenutniKorisnik.child("Picture").setValue("default");
                        trenutniKorisnik.child("Birthday").setValue(godinaRodjenja_s);
                        progres.dismiss();

                        //Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        Intent intent = new Intent(RegisterActivity.this, SetupAdditional.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    }

                }
            });

            Toast.makeText(RegisterActivity.this, "Email already exists!", Toast.LENGTH_SHORT).show();
            progres.dismiss();

        }

    }

}
