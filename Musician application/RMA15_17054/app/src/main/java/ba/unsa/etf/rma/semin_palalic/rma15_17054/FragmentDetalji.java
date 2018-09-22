package ba.unsa.etf.rma.semin_palalic.rma15_17054;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ETF-LAB-1-11 on 3/28/2016.
 */
public class FragmentDetalji extends Fragment implements MojResultReceiverTOP5.Receiver {

    boolean status = false;
    String[] pjesme;
    View pogled;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
//Ovdje se dodjeljuje layout fragmentu, tj. šta će se nalaziti unutar fragmenta
        //Ovu liniju ćemo poslije promijeniti
        View nw =inflater.inflate(R.layout.fragment_detalji, container, false);
        pogled = nw;
        final Muzicar muz = getArguments().getParcelable("muzicar");

        final Button podijeli_b = (Button)nw.findViewById(R.id.podijeli_b);
        final ListView l = (ListView)nw.findViewById(R.id.topPet_l);

        final TextView imeIPrezime = (TextView)nw.findViewById(R.id.imeIPrezime_t);
        final TextView w = (TextView)nw.findViewById(R.id.webStranica_t);
        final TextView b = (TextView)nw.findViewById(R.id.biografija_t);
        ImageView slika = (ImageView)nw.findViewById(R.id.slika_muzicara);
        String[] pjesme;

        imeIPrezime.setText(muz.getImeIPrezime());
        w.setText(muz.getWebStranica());
        b.setText(muz.getBiografija());

        //pozivanje servisa da bi se odmah upisalo u bazu top5 pjesama i albuma
        String upit = imeIPrezime.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SYNC, null, getActivity(), MyIntentServiceTOP5.class);
        MojResultReceiverTOP5 mReceiver = new MojResultReceiverTOP5(new Handler());
        mReceiver.setReceiver(FragmentDetalji.this);

        intent.putExtra("upit", upit);
        intent.putExtra("receiver", mReceiver);
        getActivity().startService(intent);


        try {

            ContentValues novi = new ContentValues();
            novi.put(MuzicarDBOpenHelper.MUZICAR_IME, muz.getImeIPrezime());
            novi.put(MuzicarDBOpenHelper.MUZICAR_ZANR, muz.getZanr());
            novi.put(MuzicarDBOpenHelper.MUZICAR_ZANROVI, muz.getBiografija());
            novi.put(MuzicarDBOpenHelper.MUZICAR_WEBSTRANICA, muz.getWebStranica());
            novi.putNull(MuzicarDBOpenHelper.MUZICAR_PJESME);
            novi.putNull(MuzicarDBOpenHelper.MUZICAR_ALBUMI);

            MuzicarDBOpenHelper muzicarDBOpenHelper = new MuzicarDBOpenHelper(getActivity(), MuzicarDBOpenHelper.DATABASE_NAME, null, 1);
            SQLiteDatabase db = muzicarDBOpenHelper.getWritableDatabase();


            String[] koloneRezulat = new String[]{ MuzicarDBOpenHelper.MUZICAR_ID, MuzicarDBOpenHelper.MUZICAR_IME, MuzicarDBOpenHelper.MUZICAR_ZANR,
                    MuzicarDBOpenHelper.MUZICAR_ZANROVI, MuzicarDBOpenHelper.MUZICAR_WEBSTRANICA,
                    MuzicarDBOpenHelper.MUZICAR_PJESME, MuzicarDBOpenHelper.MUZICAR_ALBUMI};

            String where = null;
            String whereArgs[] = null;
            String groupBy = null;
            String having = null;
            String order = null;

            Cursor cursor = db.query(MuzicarDBOpenHelper.DATABASE_TABLE,
                    koloneRezulat, where,
                    whereArgs, groupBy, having, order);


            int INDEX_KOLONE_ID = cursor.getColumnIndexOrThrow(MuzicarDBOpenHelper.MUZICAR_ID);

            String id = "";
            while (cursor.moveToNext()) {
                id = cursor.getString(INDEX_KOLONE_ID);
                break;
            }

            int INDEX_KOLONE_IME = cursor.getColumnIndexOrThrow(MuzicarDBOpenHelper.MUZICAR_IME);
            String id_p = "";
            cursor.moveToFirst();
            while(cursor.moveToNext()){
                if(cursor.getString(INDEX_KOLONE_IME).equals(muz.getImeIPrezime())){
                    id_p = cursor.getString(INDEX_KOLONE_ID);
                    db.execSQL("DELETE FROM Muzicari WHERE " + MuzicarDBOpenHelper.MUZICAR_ID + " = " + id_p + ";");
                    break;
                }
            }

            int brojRedova = (int) DatabaseUtils.queryNumEntries(db, "Muzicari");
            if(brojRedova > 5){
                cursor.close();
                db.execSQL("DELETE FROM Muzicari WHERE " + MuzicarDBOpenHelper.MUZICAR_ID + " = " + id + ";");
            }

            db.insert(MuzicarDBOpenHelper.DATABASE_TABLE, null, novi);


        } catch (Exception e) {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }


        String z = muz.getZanr();
        if(z.contentEquals("Jazz"))
            slika.setImageResource(R.drawable.jazz);
        else if(z.contentEquals("Electronic music"))
            slika.setImageResource(R.drawable.electronic_music);
        else if(z.contentEquals("Metal"))
            slika.setImageResource(R.drawable.metal);
        else if(z.contentEquals("Pop music"))
            slika.setImageResource(R.drawable.pop_music);
        else if(z.contentEquals("Rock"))
            slika.setImageResource(R.drawable.rock);
        else
            slika.setImageResource(R.drawable.slika);

        final Button promjenaFragmenta = (Button)nw.findViewById(R.id.promjena_b);
        promjenaFragmenta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    //pozivanje servisa
                    String upit = imeIPrezime.getText().toString();
                    Intent intent = new Intent(Intent.ACTION_SYNC, null, getActivity(), MyIntentServiceTOP5.class);
                    MojResultReceiverTOP5 mReceiver = new MojResultReceiverTOP5(new Handler());
                    mReceiver.setReceiver(FragmentDetalji.this);

                    intent.putExtra("upit", upit);
                    intent.putExtra("receiver", mReceiver);
                    getActivity().startService(intent);

            }
        });
        //web stranica
        w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://" + w.getText().toString();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        //podijeli
        podijeli_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT , b.getText());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        return  nw;
    }


    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

        final Button promjenaFragmenta = (Button)pogled.findViewById(R.id.promjena_b);

        switch (resultCode) {
            case MyIntentServiceTOP5.STATUS_RUNNING:
 /* Ovdje ide kod koji obavještava korisnika da je poziv upućen */
                break;
            case MyIntentServiceTOP5.STATUS_FINISHED:
 /* Dohvatanje rezultata i update UI */
                String[] results = resultData.getStringArray("top5");

                if(!status){

                    try {

                        android.app.FragmentManager fManager = getFragmentManager();
                        android.app.FragmentTransaction fTransaction = fManager.beginTransaction();
                        Bundle bundle = new Bundle();
                        String[] pjesmeLista = new String[5];

                        for (int i = 0; i < 5; i++) {
                            pjesmeLista[i] = results[i];
                        }

                        bundle.putStringArray("pjesme", pjesmeLista);
                        bundle.putString("imeIPrezime", results[10]);
                        Fragment_pjesme fpjesme = new Fragment_pjesme();
                        fpjesme.setArguments(bundle);
                        fTransaction.replace(R.id.fragment_container, fpjesme);

                        fTransaction.commit();

                        promjenaFragmenta.setText("Prikazi slicne muzicare");
                        status = true;

                    }
                    catch(Exception e){
                        Toast.makeText(getActivity(),e.toString() , Toast.LENGTH_LONG).show();
                    }
                }
                else {

                    try {
                        android.app.FragmentManager fManager = getFragmentManager();
                        android.app.FragmentTransaction fTransaction = fManager.beginTransaction();
                        Bundle bundle = new Bundle();
                        String[] muzicari = new String[5];

                        for (int i = 5; i < 10; i++) {
                            muzicari[i-5] = results[i];
                        }

                        String[] linkovi = new String[5];

                        for(int i = 0 ; i < 5 ; i++){
                            linkovi[i] = results[i + 11];
                        }

                        bundle.putStringArray("muzicari", muzicari);
                        bundle.putStringArray("linkovi" , linkovi);
                        fragmentMuzicari fMuzicari = new fragmentMuzicari();
                        fMuzicari.setArguments(bundle);
                        fTransaction.replace(R.id.fragment_container, fMuzicari);
                        fTransaction.commit();


                        promjenaFragmenta.setText("Prikazi pjesme muzicara");
                        status = false;
                    }
                    catch(Exception e){
                        Toast.makeText(getActivity(), e.toString() , Toast.LENGTH_LONG).show();
                    }

                }

                break;
            case MyIntentServiceTOP5.STATUS_ERROR:
 /* Slučaj kada je došlo do greške */
                String error = resultData.getString(Intent.EXTRA_TEXT);

                Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
                break;
        }
    }

}
