package com.example.radix_sort.radixsort;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GenerisiBrojeve extends AppCompatActivity {

    private EditText pocetakIntervala;
    private EditText krajIntervala;
    private EditText brojElemenata;
    private Button generisi;
    private List<Integer> niz = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generisi_brojeve);

        pocetakIntervala = (EditText) findViewById(R.id.pocetakIntervala);
        krajIntervala = (EditText) findViewById(R.id.krajIntervala);
        brojElemenata = (EditText) findViewById(R.id.brojElemenata);
        generisi = (Button) findViewById(R.id.generisi);

        generisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    int a = Integer.parseInt(pocetakIntervala.getText().toString());
                    int b = Integer.parseInt(krajIntervala.getText().toString());

                    int k = Integer.parseInt(brojElemenata.getText().toString());
                    for(int i = 0; i < k; i++) {
                        Random r = new Random();
                        int broj = r.nextInt(b - a + 1) + a;
                        niz.add(broj);
                    }

                    Intent intent = new Intent(GenerisiBrojeve.this, IspisiNiz.class);
                    intent.putIntegerArrayListExtra("niz", (ArrayList<Integer>) niz);
                    intent.putExtra("k", 0);
                    intent.putExtra("kraj", 0);
                    startActivity(intent);
                    finish();

                }
                catch (Exception e){
                    Toast.makeText(GenerisiBrojeve.this, e.toString() , Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        getMenuInflater().inflate(R.menu.menu_generisi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        int id = item.getItemId();
        switch (id){
            case R.id.action_exit:
                intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(GenerisiBrojeve.this, Pocetna.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}
