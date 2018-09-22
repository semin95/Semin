package com.example.radix_sort.radixsort;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

public class Algoritam extends AppCompatActivity {

    private ArrayList<Integer> niz;
    private ArrayList<ArrayList<String>> brojevi = new ArrayList<ArrayList<String>>(10);
    private int k;
    private TextView brojT;
    private TextView kantaT;
    private ListView listaL;
    private Button button;
    private int trenutniBroj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algoritam);

        for(int i = 0; i < 10; i++)
            brojevi.add(new ArrayList<String>());
        brojT= (TextView) findViewById(R.id.brojAlgoritam);
        kantaT = (TextView) findViewById(R.id.kantaAlgoritam);
        listaL = (ListView) findViewById(R.id.brojeviUKanti);
        button = (Button) findViewById(R.id.buttonAlgoritam);
        trenutniBroj = 0;

        try {
            niz = getIntent().getIntegerArrayListExtra("niz");
            k = getIntent().getIntExtra("k", 255);
            Ispis(niz.get(0).toString());
            trenutniBroj += 1;

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(trenutniBroj == niz.size()){
                        k += 1;
                        popuniNiz();
                        Intent intent = new Intent(Algoritam.this, IspisiNiz.class);
                        intent.putIntegerArrayListExtra("niz", (ArrayList<Integer>) niz);
                        intent.putExtra("k", k);
                        int b = Collections.max(niz);
                        int brojCifara = 0;
                        while(b > 0){
                            b = b / 10;
                            brojCifara += 1;
                        }
                        if(brojCifara == k){
                            intent.putExtra("kraj", 1);
                        }
                        else {
                            intent.putExtra("kraj", 0);
                        }
                        startActivity(intent);
                        Toast.makeText(Algoritam.this, Integer.toString(trenutniBroj), Toast.LENGTH_SHORT).show();
                    }
                    else if(trenutniBroj == niz.size() - 1){
                        Ispis(niz.get(trenutniBroj).toString());
                        trenutniBroj += 1;
                        button.setText("Finish " + Integer.toString(k + 1) + ". iteration");
                    }
                    else {
                        Ispis(niz.get(trenutniBroj).toString());
                        trenutniBroj += 1;
                    }
                }
            });

        }
        catch(Exception e){
            Toast.makeText(Algoritam.this, e.toString() , Toast.LENGTH_SHORT).show();
        }

    }

    private void popuniNiz() {
        niz = new ArrayList<Integer>();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < brojevi.get(i).size(); j++){
                Integer n = Integer.parseInt(brojevi.get(i).get(j));
                niz.add(n);
            }
        }
    }

    private void Ispis(String broj) {
        IspisBroja(broj);
        brojevi.get(dajCifru(broj)).add(broj);
        kantaT.setText("Bucket " + Integer.toString(dajCifru(broj)) + ":");
        IspisiBrojeve(broj);
    }

    private String vratiStringBroj(String broj){
        int b = Collections.max(niz);
        int brojCifara = 0;
        while(b > 0){
            b = b / 10;
            brojCifara += 1;
        }
        int brojNula = brojCifara - broj.length();
        for(int i = 0 ; i < brojNula; i++){
            broj = "0" + broj;
        }
        return broj;
    }

    private void IspisBroja(String broj) {
        broj = vratiStringBroj(broj);
        int p = broj.length() - k - 1;
        String pomocniBroj = "";
        for(int i = 0; i < broj.length() ; i++){
            if(i == p){
                pomocniBroj = pomocniBroj + "'" + broj.charAt(i) + "'";
            }
            else{
                pomocniBroj = pomocniBroj + broj.charAt(i);
            }
        }
        brojT.setText(pomocniBroj);
    }

    private int dajCifru(String broj){
        broj = vratiStringBroj(broj);
        String cifra = broj.charAt(broj.length() - k - 1) + "";
        return Integer.parseInt(cifra);
    }

    private void ubrzanoPopunjavanje() {
        for(int i = 0; i < 10; i++)
            brojevi.add(new ArrayList<String>());
        for(int i = 0; i < niz.size(); i++){
            String broj = Integer.toString(niz.get(i));
            brojevi.get(dajCifru(broj)).add(broj);
        }
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        getMenuInflater().inflate(R.menu.menu_algoritam, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        int id = item.getItemId();
        switch (id){
            case R.id.action_skip:
                ubrzanoPopunjavanje();
                k += 1;
                popuniNiz();
                intent = new Intent(Algoritam.this, IspisiNiz.class);
                intent.putIntegerArrayListExtra("niz", (ArrayList<Integer>) niz);
                intent.putExtra("k", k);
                int b = Collections.max(niz);
                int brojCifara = 0;
                while(b > 0){
                    b = b / 10;
                    brojCifara += 1;
                }
                if(brojCifara == k){
                    intent.putExtra("kraj", 1);
                }
                else {
                    intent.putExtra("kraj", 0);
                }
                startActivity(intent);
                finish();
                break;
            case R.id.action_new:
                intent = new Intent(Algoritam.this, GenerisiBrojeve.class);
                startActivity(intent);
                finish();
                break;
            case R.id.action_exit:
                intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    private void IspisiBrojeve(String broj) {
        int x = dajCifru(broj);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.broj, brojevi.get(x));
        listaL.setAdapter(arrayAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Algoritam.this, GenerisiBrojeve.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}
