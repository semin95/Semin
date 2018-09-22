package ba.unsa.etf.rma.semin_palalic.rma15_17054;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ETF-LAB-1-11 on 3/28/2016.
 */
public class FragmentLista extends Fragment implements SearchArtist.OnMuzicarSearchDone,MojResultReceiver.Receiver{

    private ListView lv;

    @Override
    public void onDone(ArrayList<Muzicar> p){
        ArrayList<Muzicar> muzicari = p;

        MuzicarAdapter aa = new MuzicarAdapter(
                getActivity(),
                R.layout.row_layout,
                muzicari);
//Povezujemo adapter na listview
        lv.setAdapter(aa);

    }

    private View pogled;

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case MyIntentService.STATUS_RUNNING:
 /* Ovdje ide kod koji obavještava korisnika da je poziv upućen */
                //Toast.makeText(getActivity() , "Lista" , Toast.LENGTH_LONG).show();
                break;
            case MyIntentService.STATUS_FINISHED:
 /* Dohvatanje rezultata i update UI */
                ArrayList<Muzicar> m =new ArrayList<Muzicar>();
                m = resultData.getParcelableArrayList("muzicari");


                MuzicarAdapter aa = new MuzicarAdapter(
                        getActivity(),
                        R.layout.row_layout,
                        m);
//Povezujemo adapter na listview
                lv.setAdapter(aa);

                break;
            case MyIntentService.STATUS_ERROR:
 /* Slučaj kada je došlo do greške */
                String error = resultData.getString(Intent.EXTRA_TEXT);
                //Toast.makeText(this, error, Toast.LENGTH_LONG).show();
                break;
        }
    }


    public interface OnItemClick {
        public void onItemClicked(int pos);
    }

    private OnItemClick oic;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
//Povezujemo layout fragmenta sa samim objektom fragmenta koji je kreiranje
// XML layouta –- prelazi --> View objekat
// rview će da sadrži View objekat fragmenta, koji predstavlja layout sadržaja fragmenta
        View rview =inflater.inflate(R.layout.fragment_lista, container, false);
        pogled = rview;
        lv = (ListView)rview.findViewById(R.id.listView);
        final Button b = (Button)rview.findViewById(R.id.button);
        final EditText p = (EditText)rview.findViewById((R.id.editText));
//Ukoliko je kao argument prosljeđena vrijednost koja ima ključ Alista
// možemo dohvatiti niz i povezati ga na adapter
        if(getArguments().containsKey("Alista")){
//Dohvatamo proslijeđeni niz muzicara iz argumenata fragmenta
            ArrayList<Muzicar> muzicari = getArguments().getParcelableArrayList("Alista");
//Dohvatamo referencu na listview u fragmentu
//koristimo rview jer on sadrži sve ui elemente fragmenta

//Kreiramo novi adapter koji sadrži niz muzičara
            MuzicarAdapter aa = new MuzicarAdapter(
                    getActivity(),
                    R.layout.row_layout,
                    muzicari);
//Povezujemo adapter na listview
            lv.setAdapter(aa);
        }



        try {
//oic definišite kao privatni atribut klase FragmentLista
//u sljedećoj liniji dohvatamo referencu na roditeljsku aktivnost
//kako ona implementira interfejs OnItemClick moguće ju je castati u navedeni interfejs
            oic = (OnItemClick)getActivity();
        } catch (ClassCastException e) {
//u slučaju da se u roditeljskoj aktivnosti nije implementirao interfejs OnItemClick
//baca se izuzetak
            throw new ClassCastException(getActivity().toString() +
                    "Treba implementirati OnItemClick");
        }
//ukoliko je aktivnos uspješno cast-ana u interfejs tada njoj prosljeđujemo event
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//poziva se implementirana metoda početne aktivnosti iz interfejsa OnItemClick
//kao parametar se prosljeđuje pozicija u ListView-u na koju je korisnik kliknuo
                oic.onItemClicked(position);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*lv.setAdapter(null);
                String upit = p.getText().toString();
                new SearchArtist((SearchArtist.OnMuzicarSearchDone)FragmentLista.this).execute(upit);*/

                //za pretrage
                boolean konekcija = false;
                if (isNetworkAvailable()) {
                    konekcija = true;
                } else {
                    Toast.makeText(getActivity() , "Nema konekcije" , Toast.LENGTH_LONG).show();
                }

                //upisivanje u bazu pretrage
                try{

                    ContentValues novi = new ContentValues();
                    novi.put(PretragaDBOpenHelper.PRETRAGA_PRETRAGA, p.getText().toString());

                    String trenutnoVrijeme = DateFormat.getDateTimeInstance().format(new Date());
                    novi.put(PretragaDBOpenHelper.PRETRAGA_VRIJEME, trenutnoVrijeme);
                    if(konekcija == true)
                        novi.put(PretragaDBOpenHelper.PRETRAGA_TIP,"Uspjesna");
                    else
                        novi.put(PretragaDBOpenHelper.PRETRAGA_TIP,"Neuspjesna");


                    PretragaDBOpenHelper pretragaDBOpenHelper = new PretragaDBOpenHelper(getActivity() , PretragaDBOpenHelper.DATABASE_NAME , null , 1);
                    SQLiteDatabase db = pretragaDBOpenHelper.getWritableDatabase();
                    db.insert(PretragaDBOpenHelper.DATABASE_TABLE, null, novi);


                }
                catch(Exception e){
                    Toast.makeText(getActivity() , e.toString() , Toast.LENGTH_LONG).show();
                }

                if(konekcija == false)
                    return;

                //upis u bazu
                try {

                    ContentValues novi = new ContentValues();
                    novi.put(MuzicarDBOpenHelper.MUZICAR_IME, p.getText().toString());
                    novi.put(MuzicarDBOpenHelper.MUZICAR_ZANR, "-Pretraga-");
                    novi.putNull(MuzicarDBOpenHelper.MUZICAR_ZANROVI);
                    novi.putNull(MuzicarDBOpenHelper.MUZICAR_WEBSTRANICA);
                    novi.putNull(MuzicarDBOpenHelper.MUZICAR_PJESME);
                    novi.putNull(MuzicarDBOpenHelper.MUZICAR_ALBUMI);

                    MuzicarDBOpenHelper muzicarDBOpenHelper = new MuzicarDBOpenHelper(getActivity(), MuzicarDBOpenHelper.DATABASE_NAME, null, 1);
                    SQLiteDatabase db = muzicarDBOpenHelper.getWritableDatabase();
                    db.insert(MuzicarDBOpenHelper.DATABASE_TABLE, null, novi);

                    int brojRedova = (int) DatabaseUtils.queryNumEntries(db, "Muzicari");


                    if (brojRedova > 5) {

                        String[] koloneRezulat = new String[]{MuzicarDBOpenHelper.MUZICAR_ID, MuzicarDBOpenHelper.MUZICAR_IME, MuzicarDBOpenHelper.MUZICAR_ZANR,
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

                        cursor.close();
                        db.execSQL("DELETE FROM Muzicari WHERE " + MuzicarDBOpenHelper.MUZICAR_ID + " = " + id + ";");

                    }

                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                }


                String upit = p.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SYNC, null, getActivity(), MyIntentService.class);
                MojResultReceiver mReceiver = new MojResultReceiver(new Handler());
                mReceiver.setReceiver(FragmentLista.this);

                intent.putExtra("upit", upit);
                intent.putExtra("receiver", mReceiver);
                getActivity().startService(intent);


            }
        });

        //za prethodne pretrage
        Button dugme = (Button)rview.findViewById(R.id.pretraga_b);
        dugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ispis
                try{

                    String[] koloneRezulat = new String[]{ PretragaDBOpenHelper.PRETRAGA_ID, PretragaDBOpenHelper.PRETRAGA_PRETRAGA,
                            PretragaDBOpenHelper.PRETRAGA_VRIJEME, PretragaDBOpenHelper.PRETRAGA_TIP};


                    String where = null;

                    String whereArgs[] = null;
                    String groupBy = null;
                    String having = null;
                    String order = null;

                    PretragaDBOpenHelper pretragaDBOpenHelper = new PretragaDBOpenHelper(getActivity() , PretragaDBOpenHelper.DATABASE_NAME , null , 1);
                    SQLiteDatabase db = pretragaDBOpenHelper.getWritableDatabase();

                    Cursor cursor = db.query(PretragaDBOpenHelper.DATABASE_TABLE,
                            koloneRezulat, where,
                            whereArgs, groupBy, having, order);

                    int INDEX_KOLONE_PRETRAGA = cursor.getColumnIndexOrThrow(PretragaDBOpenHelper.PRETRAGA_PRETRAGA);
                    int INDEX_KOLONE_VRIJEME = cursor.getColumnIndexOrThrow(PretragaDBOpenHelper.PRETRAGA_VRIJEME);
                    int INDEX_KOLONE_TIP = cursor.getColumnIndexOrThrow(PretragaDBOpenHelper.PRETRAGA_TIP);

                    ArrayList<Pretraga> pretrage = new ArrayList<Pretraga>();
                    while(cursor.moveToNext()){
                        String pretraga = cursor.getString(INDEX_KOLONE_PRETRAGA);
                        String vrijeme = cursor.getString(INDEX_KOLONE_VRIJEME);
                        String tip = cursor.getString(INDEX_KOLONE_TIP);
                        Pretraga p = new Pretraga(R.drawable.slika , pretraga , vrijeme , tip);
                        pretrage.add(p);

                    }

                    cursor.close();

                    lv.setAdapter(null);
                    PretragaAdapter aa = new PretragaAdapter(
                            getActivity(),
                            R.layout.row_layout,
                            pretrage);
//Povezujemo adapter na listview
                    lv.setAdapter(aa);


                }
                catch(Exception e){
                    Toast.makeText(getActivity() , e.toString() , Toast.LENGTH_LONG).show();
                }

            }
        });

        return rview;

    }
}

