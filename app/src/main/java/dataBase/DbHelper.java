package dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Helper for the database Management
 * Created by Klaussius on 09/11/2016.
 */
class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=7;
    private static final String DATABASE_NAME="OffersEAC3.db";

    /**
     * Constructor
     * @param context contexto
     */
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Crea la base de datos
     * @param db database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ DbInterfaceOffers.TABLE_NAME+" ("
                + DbInterfaceOffers._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DbInterfaceOffers.TITULO+" TEXT NOT NULL, "
                + DbInterfaceOffers.DESCRIPTION+" TEXT NOT NULL, "
                + DbInterfaceOffers.LATITUDE+" REAL, "
                + DbInterfaceOffers.LONGITUDE+" REAL, "
                + DbInterfaceOffers.PHONE+" TEXT, "
                + DbInterfaceOffers.DAY+" INTEGER, "
                + DbInterfaceOffers.MONTH+" INTEGER, "
                + DbInterfaceOffers.YEAR+" INTEGER)"
        );
        db.execSQL("CREATE TABLE "+DbInterfaceProfile.TABLE_NAME+"("
                + DbInterfaceProfile._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DbInterfaceProfile.NAME+" TEXT NOT NULL, "
                + DbInterfaceProfile.SURNAME+" TEXT NOT NULL, "
                + DbInterfaceProfile.DESCRIPTION+" TEXT NOT NULL, "
                + DbInterfaceProfile.IMAGE+" TEXT NOT NULL)"
        );
    }

    /**
     * Cuando subimos de versión de base de datos
     * @param db database
     * @param oldVersion old version for the database
     * @param newVersion new version for the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Actualizando la bdd de la versión" + oldVersion + " a " + newVersion + ". Destruirá todos los datos.");
        db.execSQL("DROP TABLE IF EXISTS " + DbInterfaceOffers.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DbInterfaceProfile.TABLE_NAME);
        onCreate(db);
    }
}
