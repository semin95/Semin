package ba.unsa.etf.rma.semin_palalic.rma15_17054;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semin on 13/06/2016.
 */
public class PretragaAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public PretragaAdapter(Context context , int resource, ArrayList<Pretraga> lm){
        super(context, resource);
        list=lm;
    }

    static class DataHandler
    {
        ImageView Poster;
        TextView Pretraga;
        TextView Vrijeme;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        DataHandler handler;
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_layout,parent,false);
            handler = new DataHandler();
            handler.Poster = (ImageView)row.findViewById(R.id.slike_zanrovi);
            handler.Pretraga = (TextView)row.findViewById(R.id.ime_muzicara);
            handler.Vrijeme = (TextView)row.findViewById(R.id.zanr);

            Pretraga pom;
            pom = (Pretraga)this.getItem(position);
            if(pom.getTip().equals("Uspjesna")){
                row.setBackgroundColor(Color.rgb(124,207,114));
            }
            else {
                row.setBackgroundColor(Color.rgb(245,113,121));
            }

            row.setTag(handler);

        }
        else
        {
            handler = (DataHandler)row.getTag();
        }
        Pretraga m;
        m = (Pretraga)this.getItem(position);
        handler.Poster.setImageResource(m.getSlika_zanr_resources());
        handler.Pretraga.setText(m.getPretraga());
        handler.Vrijeme.setText(m.getVrijeme());

        return row;
    }


}