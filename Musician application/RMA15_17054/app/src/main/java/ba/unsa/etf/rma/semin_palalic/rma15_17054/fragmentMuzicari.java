package ba.unsa.etf.rma.semin_palalic.rma15_17054;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ba.unsa.etf.rma.semin_palalic.rma15_17054.R;

public class fragmentMuzicari extends Fragment {


    public fragmentMuzicari() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_muzicari, container, false);
        Bundle bundle = this.getArguments();
        final ListView lista = (ListView) v.findViewById(R.id.lista_muzicara);
        String[] muzicari = bundle.getStringArray("muzicari");
        final String[] linkovi = bundle.getStringArray("linkovi");


        ArrayList<String> lista_pjesama = new ArrayList<String>();
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, lista_pjesama);
        lista.setAdapter(adapter);
        for (String p : muzicari) {
            lista_pjesama.add(0, p);
            adapter.notifyDataSetChanged();
        }

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int itemPosition = position;

                String url = linkovi[itemPosition];
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);


                /*String p = (String) lista.getItemAtPosition(itemPosition);
                Intent intent = new Intent(Intent.ACTION_SEARCH);
                intent.setPackage("com.google.android.youtube");
                intent.putExtra("query", p);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);*/

            }
        });

        return v;
    }
}