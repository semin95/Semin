package ba.unsa.etf.rma.semin_palalic.rma15_17054;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Semin on 16/05/2016.
 */

public class MyIntentService extends IntentService {
    int[] slike_zanrovi_resource = { R.drawable.jazz ,R.drawable.electronic_music ,
            R.drawable.metal , R.drawable.pop_music , R.drawable.rock};

    ArrayList<Muzicar> rez = new ArrayList<Muzicar>();
    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;

    public MyIntentService() {
        super(null);
    }
    public MyIntentService(String name) {
        super(name);
        // Sav posao koji treba da obavi konstruktor treba da se
        // nalazi ovdje
    }
    @Override
    public void onCreate() {
        super.onCreate();
        // Akcije koje se trebaju obaviti pri kreiranju servisa
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        // Kod koji se nalazi ovdje će se izvršavati u posebnoj niti
        // Ovdje treba da se nalazi funkcionalnost servisa koja je
        // vremenski zahtjevna

        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        Bundle bundle = new Bundle();

        /* Update UI: Početak taska */
        receiver.send(STATUS_RUNNING, Bundle.EMPTY);

        String upit = intent.getStringExtra("upit");

        String query = null;
        try {
            query = URLEncoder.encode(upit, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url1 = "https://api.spotify.com/v1/search?q=" + query + "&type=artist&limit=50";
        try {
            URL url = new URL(url1);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String rezultat = convertStreamToString(in);

            JSONObject jo = new JSONObject(rezultat);
            JSONObject artists = jo.getJSONObject("artists");
            JSONArray items = artists.getJSONArray("items");
            ArrayList<Muzicar> muzicari = new ArrayList<Muzicar>();

            //rez.add(new Muzicar(slike_zanrovi_resource[0],"Semin","Palalic", "aaaa" , "bbbb"));
            for (int i = 0; i < items.length(); i++) {
                JSONObject artist = items.getJSONObject(i);
                String name = artist.getString("name");
                //String zanr = artist.getString("genres");
                JSONArray listaZanrova = artist.getJSONArray("genres");
                String web = artist.getString("external_urls");
                String zanr;
                try {
                    zanr = listaZanrova.getString(0);
                }
                catch(Exception e){
                    zanr = "Nema zanra!";
                }


                // provjera zanra
                int slika;
                if(zanr.contains("jazz"))
                    slika =  slike_zanrovi_resource[0];
                else if(zanr.contains("electronic"))
                    slika = slike_zanrovi_resource[1];
                else if(zanr.contains("metal"))
                    slika = slike_zanrovi_resource[2];
                else if(zanr.contains("pop"))
                    slika = slike_zanrovi_resource[3];
                else if(zanr.contains("rock"))
                    slika = slike_zanrovi_resource[4];
                else
                    slika = R.drawable.slika;

                zanr = Character.toString(zanr.charAt(0)).toUpperCase()+zanr.substring(1);



                String webBiografija = artist.getString("href");
                String zanrovi = "Zanrovi: \n";
                for(int j = 0 ; j < listaZanrova.length() ; j++){
                    String pomocna;
                    try {
                        pomocna = listaZanrova.getString(j);
                    }
                    catch(Exception e){
                        continue;
                    }
                    pomocna = Character.toString(pomocna.charAt(0)).toUpperCase()+pomocna.substring(1);
                    zanrovi = zanrovi + "\n" + pomocna;

                }

                rez.add(new Muzicar(slika , name , zanr , webBiografija , zanrovi));

                //rez.add(new Muzicar(slike_zanrovi_resource[0] , name , "ll" , webBiografija , zanrovi));

                //Ovdje trebate dodati kreiranje objekta Muzičara i dodavanje u listu
                //Ovo uradite na sličan način kako ste radili i kada ste hardkodirali podatke
                // samo što sada koristite stvarne podatke


            }

            //rez = muzicari;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        bundle.putParcelableArrayList("muzicari", rez);
        receiver.send(STATUS_FINISHED, bundle);
    }
    public String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
        } finally {
            try {
                is.close();
            } catch (IOException e) {
            }
        }
        return sb.toString();
    }
}
