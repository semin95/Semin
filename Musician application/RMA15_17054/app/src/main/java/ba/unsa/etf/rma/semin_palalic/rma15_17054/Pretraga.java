package ba.unsa.etf.rma.semin_palalic.rma15_17054;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Semin on 13/06/2016.
 */
public class Pretraga implements Parcelable {
    private int slika_zanr_resources;

    protected Pretraga(Parcel in) {
        slika_zanr_resources = in.readInt();
        Pretraga = in.readString();
        Vrijeme = in.readString();
        Tip = in.readString();
    }

    public static final Creator<Pretraga> CREATOR = new Creator<Pretraga>() {
        @Override
        public Pretraga createFromParcel(Parcel in) {
            return new Pretraga(in);
        }

        @Override
        public Pretraga[] newArray(int size) {
            return new Pretraga[size];
        }
    };

    public String getPretraga() {
        return Pretraga;
    }

    public Pretraga(int slika_zanr_resources , String Pretraga , String Vrijeme , String Tip){
        this.setSlika_zanr_resources(slika_zanr_resources);
        this.setPretraga(Pretraga);
        this.setVrijeme(Vrijeme);
        this.setTip(Tip);
    }

    public void setPretraga(String Pretraga) {
        this.Pretraga = Pretraga;
    }

    public int getSlika_zanr_resources() {
        return slika_zanr_resources;
    }

    public void setSlika_zanr_resources(int slika_zanr_resources) {
        this.slika_zanr_resources = slika_zanr_resources;
    }

    public String getVrijeme() {
        return Vrijeme;
    }

    public void setVrijeme(String Vrijeme) {
        this.Vrijeme = Vrijeme;
    }

    private String Pretraga;
    private String Vrijeme;

    public String getTip() {
        return Tip;
    }

    public void setTip(String Tip) {
        this.Tip = Tip;
    }

    private String Tip;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(slika_zanr_resources);
        dest.writeString(Pretraga);
        dest.writeString(Vrijeme);
        dest.writeString(Tip);
    }
}
