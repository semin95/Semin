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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IspisiNiz extends AppCompatActivity {

    private ArrayList<Integer> niz;
    private ListView lista;
    private Button pokreniAlgoritam;
    private int k;
    private int kraj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ispisi_niz);

        try {
            niz = getIntent().getIntegerArrayListExtra("niz");
            k = getIntent().getIntExtra("k", 255);
            kraj = getIntent().getIntExtra("kraj", 255);

            lista = (ListView) findViewById(R.id.brojevi);
            pokreniAlgoritam = (Button) findViewById(R.id.pokreniAlgoritam);

            List<String> brojevi = new ArrayList<String>();
            for(int i = 0; i < niz.size(); i++){
                brojevi.add(niz.get(i).toString());
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.broj, brojevi);
            lista.setAdapter(arrayAdapter);

            if(kraj == 1){
                pokreniAlgoritam.setText("End of the algorithm");
            }

            pokreniAlgoritam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(kraj == 1){
                        Intent intent = new Intent(IspisiNiz.this, GenerisiBrojeve.class);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(IspisiNiz.this, Algoritam.class);
                        intent.putIntegerArrayListExtra("niz", (ArrayList<Integer>) niz);
                        intent.putExtra("k", k);
                        startActivity(intent);
                        finish();
                    }
                }
            });

        }
        catch(Exception e){
            Toast.makeText(IspisiNiz.this, e.toString() , Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ispisi_niz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        int id = item.getItemId();
        switch (id){
            case R.id.action_new:
                intent = new Intent(IspisiNiz.this, GenerisiBrojeve.class);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(IspisiNiz.this, GenerisiBrojeve.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}
