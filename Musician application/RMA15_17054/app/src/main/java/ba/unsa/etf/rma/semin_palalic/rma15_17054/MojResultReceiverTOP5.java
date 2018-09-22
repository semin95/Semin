package ba.unsa.etf.rma.semin_palalic.rma15_17054;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by Semin on 21/05/2016.
 */
public class MojResultReceiverTOP5 extends ResultReceiver {
    private Receiver mReceiver;

    public MojResultReceiverTOP5(Handler handler)
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
