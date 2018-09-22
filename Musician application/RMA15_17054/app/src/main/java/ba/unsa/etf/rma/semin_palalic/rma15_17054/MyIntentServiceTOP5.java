package ba.unsa.etf.rma.semin_palalic.rma15_17054;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
 * Created by Semin on 21/05/2016.
 */
public class MyIntentServiceTOP5 extends IntentService {

    String[] t = new String[16];
    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;
    public static boolean d = false;

    public MyIntentServiceTOP5() {
        super(null);
    }
    public MyIntentServiceTOP5(String name) {
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

        //u istom servisu dobavljam top 5 pjesma muzicara i top 5 albuma muzicara
        //jer aplikacija nije zahtjeva, pa vremenski ne treba puno da se izvrsi
        //ukoliko bi dobavljanje trajalo duze, onda bi koristio 2 servisa

        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        Bundle bundle = new Bundle();
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

            JSONObject artist = items.getJSONObject(0);
            //na t[10] stavljam ime i prezime muzicara
            t[10] = artist.getString("name");

            try {
                //top 5 pjesama muzicara
                String id = artist.getString("id");
                String url2 = "https://api.spotify.com/v1/artists/" + id + "/top-tracks?country=SE";
                URL url_p = new URL(url2);
                HttpURLConnection urlConnection2 = (HttpURLConnection) url_p.openConnection();
                InputStream in2 = new BufferedInputStream(urlConnection2.getInputStream());
                String rezultat2 = convertStreamToString(in2);
                JSONObject jo2 = new JSONObject(rezultat2);
                JSONArray items2 = jo2.getJSONArray("tracks");

                for (int j = 0; j < items2.length(); j++) {
                    if (j == 5) break;
                    JSONObject pom = items2.getJSONObject(j);
                    t[j] = pom.getString("name");
                }

                //top 5 albuma muzicara
                String url3 = "https://api.spotify.com/v1/artists/" + id + "/albums";
                URL urlp = new URL(url3);
                HttpURLConnection urlConnection3 = (HttpURLConnection) urlp.openConnection();
                InputStream in3 = new BufferedInputStream(urlConnection3.getInputStream());
                String rezultat3 = convertStreamToString(in3);
                JSONObject jo3 = new JSONObject(rezultat3);
                JSONArray items3 = jo3.getJSONArray("items");

                for (int d = 0 ; d < items3.length() ; d++)
                {
                    if(d == 5) break;
                    JSONObject pom = items3.getJSONObject(d);
                    t[d + 5] = pom.getString("name");
                    JSONObject link = pom.getJSONObject("external_urls");
                    t[d + 11] = link.getString("spotify");
                }

            }
            catch(Exception e){
                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),e.toString() ,Toast.LENGTH_LONG).show();
        }
        //od t[0] do t[4] se nalaze top 5 pjesama muzicara
        //od t[5] do t[9] se nalaze top 5 albuma muzicara
        //na t[10] se nalazi ime muzicara
        bundle.putStringArray("top5", t);
        receiver.send(STATUS_FINISHED, bundle);

        if(d == false) {
            //ispis
            try {

                String[] koloneRezulat = new String[]{MuzicarDBOpenHelper.MUZICAR_ID, MuzicarDBOpenHelper.MUZICAR_IME, MuzicarDBOpenHelper.MUZICAR_ZANR,
                        MuzicarDBOpenHelper.MUZICAR_ZANROVI, MuzicarDBOpenHelper.MUZICAR_WEBSTRANICA,
                        MuzicarDBOpenHelper.MUZICAR_PJESME, MuzicarDBOpenHelper.MUZICAR_ALBUMI};

                String where = null;

                String whereArgs[] = null;
                String groupBy = null;
                String having = null;
                String order = null;

                MuzicarDBOpenHelper muzicarDBOpenHelper = new MuzicarDBOpenHelper(getApplicationContext(), MuzicarDBOpenHelper.DATABASE_NAME, null, 1);
                SQLiteDatabase db = muzicarDBOpenHelper.getWritableDatabase();

                Cursor cursor = db.query(MuzicarDBOpenHelper.DATABASE_TABLE,
                        koloneRezulat, where,
                        whereArgs, groupBy, having, order);

                int INDEX_KOLONE_ID = cursor.getColumnIndexOrThrow(MuzicarDBOpenHelper.MUZICAR_ID);

                cursor.moveToLast();
                String id = cursor.getString(INDEX_KOLONE_ID);

                String top5Pjesama = "";
                for (int i = 0; i < 5; i++)
                    top5Pjesama = top5Pjesama + t[i] + "-";

                String top5Albuma = "";
                for (int i = 5; i < 10; i++)
                    top5Albuma = top5Albuma + t[i] + "-";

                top5Pjesama = top5Pjesama.replaceAll("\"" , "");
                top5Albuma = top5Albuma.replaceAll("\"" , "");
                //top5Pjesama = top5Pjesama.replaceAll("," , "");
                //top5Albuma = top5Albuma.replaceAll("," , "");
                top5Pjesama = top5Pjesama.replaceAll("'" , "");
                top5Albuma = top5Albuma.replaceAll("'" , "");

                db.execSQL("UPDATE Muzicari SET " + MuzicarDBOpenHelper.MUZICAR_PJESME + " = '" + top5Pjesama + "', "
                        + MuzicarDBOpenHelper.MUZICAR_ALBUMI + " = '" + top5Albuma + "' WHERE " + MuzicarDBOpenHelper.MUZICAR_ID
                        + " = " + id + ";");

            /*while(cursor.moveToNext()){
                String ime = cursor.getString(INDEX_KOLONE_IME);
                Toast.makeText(getApplicationContext() , ime , Toast.LENGTH_LONG).show();
            }*/

                cursor.close();
                d = true;
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        }

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
