package ba.unsa.etf.rma.semin_palalic.rma15_17054;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Semin on 15/03/2016.
 */
public class Muzicar implements Parcelable {
    private int slika_zanr_resources;

    protected Muzicar(Parcel in) {
        slika_zanr_resources = in.readInt();
        imeIPrezime = in.readString();
        zanr = in.readString();
        webStranica = in.readString();
        biografija = in.readString();
    }

    public static final Creator<Muzicar> CREATOR = new Creator<Muzicar>() {
        @Override
        public Muzicar createFromParcel(Parcel in) {
            return new Muzicar(in);
        }

        @Override
        public Muzicar[] newArray(int size) {
            return new Muzicar[size];
        }
    };

    public String getImeIPrezime() {
        return imeIPrezime;
    }

    public Muzicar(int slika_zanr_resources , String imeIPrezime , String zanr , String webStranica , String biografija){
        this.setSlika_zanr_resources(slika_zanr_resources);
        this.setImeIPrezime(imeIPrezime);
        this.setZanr(zanr);
        this.setWebStranica(webStranica);
        this.setBiografija(biografija);
    }

    public void setImeIPrezime(String imeIPrezime) {
        this.imeIPrezime = imeIPrezime;
    }

    public int getSlika_zanr_resources() {
        return slika_zanr_resources;
    }

    public void setSlika_zanr_resources(int slika_zanr_resources) {
        this.slika_zanr_resources = slika_zanr_resources;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    private String imeIPrezime;
    private String zanr;

    public String getWebStranica() {
        return webStranica;
    }

    public void setWebStranica(String webStranica) {
        this.webStranica = webStranica;
    }

    public String getBiografija() {
        return biografija;
    }

    public void setBiografija(String biografija) {
        this.biografija = biografija;
    }

    private String webStranica;
    private String biografija;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(slika_zanr_resources);
        dest.writeString(imeIPrezime);
        dest.writeString(zanr);
        dest.writeString(webStranica);
        dest.writeString(biografija);
    }
}
