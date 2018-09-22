package ba.unsa.etf.rma.semin_palalic.rma15_17054;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.net.NetworkInterface;

public class wifiBroadcastReceiver extends BroadcastReceiver {
    public wifiBroadcastReceiver() {
    }

    boolean wifi = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if(activeNetworkInfo != null && activeNetworkInfo.isConnected()){
                Toast.makeText(context, "Konekcija uspostavljena!", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Konekcija prekinuta!", Toast.LENGTH_SHORT).show();
    }
}
