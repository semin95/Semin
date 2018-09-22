package ba.unsa.etf.rma.semin_palalic.rma15_17054;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Semin on 13/06/2016.
 */
public class PretragaDBOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mojaBaza2.db";
    public static final String DATABASE_TABLE = "Pretrage";
    public static final int DATABASE_VERSION = 1;
    public static final String PRETRAGA_ID ="_id";
    public static final String PRETRAGA_PRETRAGA ="pretraga";
    public static final String PRETRAGA_VRIJEME ="vrijeme";
    public static final String PRETRAGA_TIP = "tip";
    //... ostale potrebne kolone
// SQL upit za kreiranje baze
    private static final String DATABASE_CREATE = "create table " +
            DATABASE_TABLE + " (" + PRETRAGA_ID +
            " integer primary key autoincrement, " +
            PRETRAGA_PRETRAGA + " text not null, " +
            PRETRAGA_VRIJEME + " text not null," +
            PRETRAGA_TIP + " text not null" + ");";
    public PretragaDBOpenHelper(Context context, String name,
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
