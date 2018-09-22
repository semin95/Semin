package ba.unsa.etf.rma.semin_palalic.rma15_17054;

import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Pocetna extends AppCompatActivity implements FragmentLista.OnItemClick {

    boolean siriL;
    boolean d = false;

    int[] slike_zanrovi_resource = { R.drawable.jazz ,R.drawable.electronic_music ,
        R.drawable.metal , R.drawable.pop_music , R.drawable.rock};

    String[] imenaMuzicara;
    String[] zanrovi;
    String[] web_stranice;
    String[] biografije;
    String[] pjesme;

    MuzicarAdapter adapter;

    private ArrayList<Muzicar> mi = new ArrayList<Muzicar>(); //??
    private ArrayList<Muzicar> last5 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetna);

        //dohvatanje iz tabele
        //ispis
        //if(d == false) {
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

                int INDEX_KOLONE_IME = cursor.getColumnIndexOrThrow(MuzicarDBOpenHelper.MUZICAR_IME);
                int INDEX_KOLONE_ZANR = cursor.getColumnIndexOrThrow(MuzicarDBOpenHelper.MUZICAR_ZANR);

                int i = 0;
                while (cursor.moveToNext()) {
                    if (i == 5) break;
                    String imeMuzicara = cursor.getString(INDEX_KOLONE_IME);
                    String zanr = cursor.getString(INDEX_KOLONE_ZANR);
                    Muzicar m = new Muzicar(R.drawable.slika, imeMuzicara, zanr, "", "");
                    mi.add(m);
                    i++;
                }

                cursor.close();
                //d = true;
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        //}

        /*
        //upis
        try{

            ContentValues novi = new ContentValues();
            novi.put(MuzicarDBOpenHelper.MUZICAR_IME,"Semin");
            novi.put(MuzicarDBOpenHelper.MUZICAR_ZANR,"pop");


            MuzicarDBOpenHelper muzicarDBOpenHelper = new MuzicarDBOpenHelper(getApplicationContext() , MuzicarDBOpenHelper.DATABASE_NAME , null , 1);
            SQLiteDatabase db = muzicarDBOpenHelper.getWritableDatabase();
            db.insert(MuzicarDBOpenHelper.DATABASE_TABLE, null, novi);


        }
        catch(Exception e){
            Toast.makeText(getApplicationContext() , e.toString() , Toast.LENGTH_LONG).show();
        }


        //ispis
        try{

            String[] koloneRezulat = new String[]{ MuzicarDBOpenHelper.MUZICAR_ID, MuzicarDBOpenHelper.MUZICAR_IME, MuzicarDBOpenHelper.MUZICAR_ZANR};

            //String where = MuzicarDBOpenHelper.MUZICAR_ZANR + "=pop";
            String where = null;

            String whereArgs[] = null;
            String groupBy = null;
            String having = null;
            String order = null;

            MuzicarDBOpenHelper muzicarDBOpenHelper = new MuzicarDBOpenHelper(getApplicationContext() , MuzicarDBOpenHelper.DATABASE_NAME , null , 1);
            SQLiteDatabase db = muzicarDBOpenHelper.getWritableDatabase();

            Cursor cursor = db.query(MuzicarDBOpenHelper.DATABASE_TABLE,
                    koloneRezulat, where,
                    whereArgs, groupBy, having, order);

            int INDEX_KOLONE_IME = cursor.getColumnIndexOrThrow(MuzicarDBOpenHelper.MUZICAR_IME);

            while(cursor.moveToNext()){
                String ime = cursor.getString(INDEX_KOLONE_IME);
                Toast.makeText(getApplicationContext() , ime , Toast.LENGTH_LONG).show();
            }

            cursor.close();
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext() , e.toString() , Toast.LENGTH_LONG).show();
        }


        //brisanje
        try{

            MuzicarDBOpenHelper muzicarDBOpenHelper = new MuzicarDBOpenHelper(getApplicationContext() , MuzicarDBOpenHelper.DATABASE_NAME , null , 1);
            SQLiteDatabase db = muzicarDBOpenHelper.getWritableDatabase();
            db.execSQL("DELETE FROM " + muzicarDBOpenHelper.DATABASE_TABLE + ";");

        }
        catch(Exception e){
            Toast.makeText(getApplicationContext() , e.toString() , Toast.LENGTH_LONG).show();
        }*/








        /*Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        EditText s = (EditText)findViewById(R.id.editText);

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
                if (sharedText != null) {
                    s.setText(sharedText);
                }
            }
        }

        final ListView lista = (ListView) findViewById(R.id.listView);
*/

        imenaMuzicara = getResources().getStringArray(R.array.imena_muzicara);
        zanrovi = getResources().getStringArray(R.array.zanrovi);
        web_stranice = getResources().getStringArray(R.array.web_stranice);
        biografije = getResources().getStringArray(R.array.biografije);
        //pjesme = getResources().getStringArray(R.array.pjesme);

        int i = 0;
        //adapter = new MuzicarAdapter(getApplicationContext(), R.layout.row_layout);
        //lista.setAdapter(adapter);
       // mi = new ArrayList<Muzicar>(); // ???
/*

        for (String z : zanrovi) {
            Muzicar muzicari = new Muzicar(slike_zanrovi_resource[i], imenaMuzicara[i], z , web_stranice[i] , biografije[i]);
            mi.add(muzicari);
            i++;
        }
*/

/*
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Pocetna.this , detaljiOMuzicaru.class);

                int itemPosition = position;
                Muzicar pom_m = (Muzicar)lista.getItemAtPosition(itemPosition);

                i.putExtra("ime_i_prezime",pom_m.getImeIPrezime());
                i.putExtra("zanr" , pom_m.getZanr());
                i.putExtra("webStranica" , pom_m.getWebStranica());
                i.putExtra("biografija" , pom_m.getBiografija());

                if(pom_m.getImeIPrezime().contentEquals("Ray Charles")){
                    pjesme = getResources().getStringArray(R.array.Ray_Charles_p);
                    i.putExtra("pjesme" , pjesme);
                }
                else if(pom_m.getImeIPrezime().contentEquals("Daft Punk")){
                    pjesme = getResources().getStringArray(R.array.Daft_Punk_p);
                    i.putExtra("pjesme" , pjesme);
                }
                else if(pom_m.getImeIPrezime().contentEquals("Metallica")){
                    pjesme = getResources().getStringArray(R.array.Metallica_p);
                    i.putExtra("pjesme" , pjesme);
                }
                else if(pom_m.getImeIPrezime().contentEquals("Michael Jackson")){
                    pjesme = getResources().getStringArray(R.array.Michael_Jackson_p);
                    i.putExtra("pjesme" , pjesme);
                }
                else if(pom_m.getImeIPrezime().contentEquals("AC/DC")){
                    pjesme = getResources().getStringArray(R.array.AC_DC_p);
                    i.putExtra("pjesme" , pjesme);
                }
                else {
                    pjesme = getResources().getStringArray(R.array.pjesme);
                    i.putExtra("pjesme" , pjesme);
                }

                //i.putExtra("pjesme" , pjesme);

                Pocetna.this.startActivity(i);

            }
        });*/

        //


        siriL=false;
        FragmentManager fm = getFragmentManager(); //dohvatanje FragmentManager-a
        FrameLayout ldetalji = (FrameLayout)findViewById(R.id.detalji_muzicar);
        if(ldetalji!=null){//slucaj layouta za široke ekrane
            siriL=true;
            FragmentDetalji fd;
            fd = (FragmentDetalji)fm.findFragmentById(R.id.detalji_muzicar);
            //provjerimo da li je fragment detalji već kreiran
            if(fd==null) {
                //kreiramo novi fragment FragmentDetalji ukoliko već nije kreiran

                fd = new FragmentDetalji();
                Bundle arguments = new Bundle();
                arguments.putParcelable("muzicar", mi.get(0));
                        fd.setArguments(arguments);
                fm.beginTransaction().replace(R.id.detalji_muzicar, fd).commit();
            }
        }

//Dodjeljivanje fragmenta FragmentLista
        FragmentLista fl = (FragmentLista)fm.findFragmentByTag("Lista");
//provjerimo da li je već kreiran navedeni fragment
        if(fl==null){
            //ukoliko nije, kreiramo
            fl = new FragmentLista();
            Bundle argumenti=new Bundle();
//unosi su ranije incializirana lista muzičara
//na način kako ste radili na projektnom zadatku 1
            //argumenti.putParcelableArrayList("Alista",mi);

            argumenti.putParcelableArrayList("Alista" ,mi);
            fl.setArguments(argumenti);
            fm.beginTransaction().replace(R.id.muzicari_lista, fl, "Lista").commit();
        }else{
            //slučaj kada mjenjamo orjentaciju uređaja
            //iz portrait (uspravna) u landscape (vodoravna)
            //a u aktivnosti je bio otvoren fragment FragmentDetalji
            //tada je potrebno skinuti FragmentDetalji sa steka
            //kako ne bi bio dodan na mjesto fragmenta FragmentLista
          //  fm.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }


    }

    @Override
    public void onItemClicked(int pos) {
        //Priprema novog fragmenta FragmentDetalji

        boolean pretraga = false;
        ListView l = (ListView)findViewById(R.id.listView);
        try{

            Pretraga p = ((Pretraga) l.getItemAtPosition(pos));

            EditText e = (EditText)findViewById(R.id.editText);
            e.setText(p.getPretraga());
            Button b = (Button)findViewById(R.id.button);
            b.performClick();
            pretraga = true;
        }
        catch(Exception e){
            //ukoliko se desio izuzetak samo nastaviti sa izvrsavanjem
        }
        if(pretraga == true)
            return;

        Muzicar muz = ((Muzicar) l.getItemAtPosition(pos));

        if(muz.getZanr().equals("-Pretraga-"))
            return;

        Bundle arguments = new Bundle();
        //ubacivanje muzicara u mi
        mi.clear();
        int brojElemenata = l.getAdapter().getCount();
        for (int i = 0 ; i < brojElemenata ; i++){
            Muzicar m =((Muzicar)l.getItemAtPosition(i));
            if(!mi.contains(m))
                mi.add(m);
        }

        arguments.putParcelable("muzicar", muz);
        //arguments.putParcelable("muzicar", mi.get(pos));

        FragmentDetalji fd = new FragmentDetalji();
        fd.setArguments(arguments);
        if(siriL){

            //Slučaj za ekrane sa širom dijagonalom
            getFragmentManager().beginTransaction()
                    .replace(R.id.detalji_muzicar, fd)
                    .commit();
        }
        else{

            //Slučaj za ekrane sa početno zadanom širinom
            getFragmentManager().beginTransaction()
                    .replace(R.id.muzicari_lista, fd)
                    .addToBackStack(null)
                    .commit();
            //Primjetite liniju .addToBackStack(null)
        }
    }


    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }



}
