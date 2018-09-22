package com.example.friend.friendv2;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;

/**
 * Created by semin on 5/25/17.
 */

public class Post {

    private String Opis;
    private String Slika;
    private String ImeIPrezime;
    private String ProfilnaSlika;
    private String Datum;
    private String Id;


    public Post(String opis, String slika, String ime_i_prezime, String profilnaSlika, String datum){
        this.Opis = opis;
        this.Slika = slika;
        this.ImeIPrezime = ime_i_prezime;
        this.ProfilnaSlika = profilnaSlika;
        this.Datum = datum;
    }

    public Post(){

    }


    public String getOpis() {
        return Opis;
    }

    public void setOpis(String opis) {
        Opis = opis;
    }

    public String getSlika() {
        return Slika;
    }

    public void setSlika(String slika) {
        Slika = slika;
    }

    public String getImeIPrezime() {
        return ImeIPrezime;
    }

    public void setImeIPrezime(String imeIPrezime) {
        ImeIPrezime = imeIPrezime;
    }

    public String getProfilnaSlika() {
        return ProfilnaSlika;
    }

    public void setProfilnaSlika(String profilnaSlika) {
        ProfilnaSlika = profilnaSlika;
    }

    public String getDatum() {
        return Datum;
    }

    public void setDatum(String datum) {
        Datum = datum;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
