package ba.unsa.etf.rma.semin_palalic.rma15_17054;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by Semin on 16/05/2016.
 */
public class MojResultReceiver extends ResultReceiver {
    private Receiver mReceiver;

    public MojResultReceiver(Handler handler)
    {
        super(handler);
    }

    public void setReceiver(Receiver receiver)
    {
        mReceiver = receiver;
    }

    /* Deklaracija interfejsa koji će se trebati implementirati */
    public interface Receiver
    {
        public void onReceiveResult(int resultCode, Bundle resultData);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) {
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }
}
