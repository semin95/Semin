package com.example.friend.friendv2;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Created by semin on 5/26/17.
 */

public class SimplePost extends Application {

    private boolean logovan = false;
    private String idTrenutnogKorisnika = "";

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(false);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);

    }

    public boolean isLogovan() {
        return logovan;
    }

    public void setLogovan(boolean logovan) {
        this.logovan = logovan;
    }

    public String getIdTrenutnogKorisnika() {
        return idTrenutnogKorisnika;
    }

    public void setIdTrenutnogKorisnika(String idTrenutnogKorisnika) {
        this.idTrenutnogKorisnika = idTrenutnogKorisnika;
    }
}
