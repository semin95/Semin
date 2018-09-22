package ba.unsa.etf.rma.semin_palalic.rma15_17054;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ba.unsa.etf.rma.semin_palalic.rma15_17054.R;

public class Fragment_pjesme extends Fragment {


    public Fragment_pjesme() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pjesme, container, false);
        final Bundle bundle = this.getArguments();
        final ListView lista = (ListView)v.findViewById(R.id.lista_pjesama);
        String[] pjesme = bundle.getStringArray("pjesme");

        ArrayList<String> lista_pjesama = new ArrayList<String>();
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1 , lista_pjesama);
        lista.setAdapter(adapter);
        for (String p : pjesme) {
            lista_pjesama.add(0, p);
            adapter.notifyDataSetChanged();
        }

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int itemPosition = position;
                String p = (String) lista.getItemAtPosition(itemPosition);
                p = p + " " + bundle.getString("imeIPrezime");
                Intent intent = new Intent(Intent.ACTION_SEARCH);
                intent.setPackage("com.google.android.youtube");
                intent.putExtra("query", p);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

        return v;
    }
}