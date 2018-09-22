package ba.unsa.etf.rma.semin_palalic.rma15_17054;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semin on 15/03/2016.
 */
public class MuzicarAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public MuzicarAdapter(Context context , int resource, ArrayList<Muzicar> lm){
        super(context, resource);
        list=lm;
    }

    static class DataHandler
    {
        ImageView Poster;
        TextView ime_i_prezime;
        TextView zan;
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
            handler.ime_i_prezime = (TextView)row.findViewById(R.id.ime_muzicara);
            handler.zan = (TextView)row.findViewById(R.id.zanr);
            row.setTag(handler);

        }
        else
        {
            handler = (DataHandler)row.getTag();
        }
        Muzicar m;
        m = (Muzicar)this.getItem(position);
        handler.Poster.setImageResource(m.getSlika_zanr_resources());
        handler.ime_i_prezime.setText(m.getImeIPrezime());
        handler.zan.setText(m.getZanr());

        return row;
    }
}
