package ba.unsa.etf.rma.semin_palalic.rma15_17054;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Semin on 16/03/2016.
 */
public class detaljiOMuzicaru extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalji_o_muzicaru);

        final Button podijeli_b = (Button)findViewById(R.id.podijeli_b);
        final ListView l = (ListView)findViewById(R.id.topPet_l);

        final TextView imeIPrezime = (TextView)findViewById(R.id.imeIPrezime_t);
        final TextView w = (TextView)findViewById(R.id.webStranica_t);
        final TextView b = (TextView)findViewById(R.id.biografija_t);
        ImageView slika = (ImageView)findViewById(R.id.slika_muzicara);
        String[] pjesme;

        final Bundle extras = getIntent().getExtras();
        if (extras != null) {

            String ime = extras.getString("ime_i_prezime");
            String z = extras.getString("zanr");
            String webStranica = extras.getString("webStranica");
            String biografija = extras.getString("biografija");
            pjesme = extras.getStringArray("pjesme");

            imeIPrezime.setText(ime);
            w.setText(webStranica);
            b.setText(biografija);


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


            ArrayList<String> lista_pjesama = new ArrayList<String>();
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista_pjesama);
            l.setAdapter(adapter);
            for (String p : pjesme) {
                lista_pjesama.add(0, p);
                adapter.notifyDataSetChanged();
            }
        }

        w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://" + w.getText().toString();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int itemPosition = position;
                String p = (String) l.getItemAtPosition(itemPosition);
                p = p + " " + extras.getString("ime_i_prezime");
                Intent intent = new Intent(Intent.ACTION_SEARCH);
                intent.setPackage("com.google.android.youtube");
                intent.putExtra("query", p);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

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

    }


}
