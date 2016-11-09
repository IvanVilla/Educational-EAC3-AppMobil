package dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Klaussius on 09/11/2016.
 */

public class OffersDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="OffersEAC3.db";

    public OffersDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ OffersDbInterface.TABLE_NAME+" ("+
                OffersDbInterface._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                OffersDbInterface.TITULO+" TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Actualizando la bdd de la versión" + oldVersion + " a " + newVersion + ". Destruirá todos los datos.");
        db.execSQL("DROP TABLE IF EXISTS " + OffersDbInterface.TABLE_NAME);
        onCreate(db);
    }
}
