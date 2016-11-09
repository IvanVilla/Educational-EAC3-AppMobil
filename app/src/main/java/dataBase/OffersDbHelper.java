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
class OffersDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=2;
    private static final String DATABASE_NAME="OffersEAC3.db";

    /**
     * Constructor
     * @param context contexto
     */
    public OffersDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Crea la base de datos
     * @param db database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ OffersDbInterface.TABLE_NAME+" ("+
                OffersDbInterface._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + OffersDbInterface.TITULO+" TEXT NOT NULL, "
                + OffersDbInterface.DESCRIPTION+" TEXT NOT NULL, "
                + OffersDbInterface.LATITUDE+" REAL, "
                + OffersDbInterface.LONGITUDE+" REAL, "
                + OffersDbInterface.PHONE+" TEXT, "
                + OffersDbInterface.DAY+" INTEGER, "
                + OffersDbInterface.MONTH+" INTEGER, "
                + OffersDbInterface.YEAR+" INTEGER)"
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
        db.execSQL("DROP TABLE IF EXISTS " + OffersDbInterface.TABLE_NAME);
        onCreate(db);
    }
}
