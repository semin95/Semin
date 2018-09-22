package ba.unsa.etf.rma.semin_palalic.rma15_17054;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ETF-LAB-1-11 on 5/30/2016.
 */
public class MuzicarDBOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mojaBaza.db";
    public static final String DATABASE_TABLE = "Muzicari";
    public static final int DATABASE_VERSION = 1;
    public static final String MUZICAR_ID ="_id";
    public static final String MUZICAR_IME ="ime";
    public static final String MUZICAR_ZANR ="zanr";
    public static final String MUZICAR_ZANROVI = "zanrovi";
    public static final String MUZICAR_WEBSTRANICA = "webstranica";
    public static final String MUZICAR_PJESME = "pjesme";
    public static final String MUZICAR_ALBUMI = "albumi";
    //... ostale potrebne kolone
// SQL upit za kreiranje baze
    private static final String DATABASE_CREATE = "create table " +
            DATABASE_TABLE + " (" + MUZICAR_ID +
            " integer primary key autoincrement, " +
            MUZICAR_IME + " text not null, " +
            MUZICAR_ZANR + " ," +
            MUZICAR_ZANROVI + " ," +
            MUZICAR_WEBSTRANICA + " ," +
            MUZICAR_PJESME + " ," +
            MUZICAR_ALBUMI + ");";
            //MUZICAR_ZANR + " text not null);";
    public MuzicarDBOpenHelper(Context context, String name,
                               SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //Poziva se kada ne postoji baza
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }
    // Poziva se kada se ne poklapaju verzije baze na disku i trenutne baze
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Brisanje stare verzije
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
        // Kreiranje nove
        onCreate(db);
    }
}