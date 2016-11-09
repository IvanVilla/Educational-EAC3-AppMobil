package dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import model.Offer;

/**
 * Manages the table offer
 * Created by Klaussius on 09/11/2016.
 */

public class OffersDbInterface {

    static final String TABLE_NAME = "offer";

    static final String _ID = "_id";
    static final String TITULO = "titulo";
    static final String DESCRIPTION = "description";
    static final String LATITUDE = "latitude";
    static final String LONGITUDE = "longitude";
    static final String PHONE = "phone";
    static final String DAY = "day";
    static final String MONTH = "month";
    static final String YEAR = "year";

    private final Context context;
    private OffersDbHelper myHelp;
    private SQLiteDatabase bd;

    /**
     * Constructor
     * @param context contexto
     */
    public OffersDbInterface(Context context) {
        this.context = context;
        myHelp = new OffersDbHelper(context);
    }

    /**
     * Insert one offer in the dataBase
     * @param myOffer offer to insert
     */
    public void insertOffer(Offer myOffer){
        open();
        ContentValues initialValues = new ContentValues();
        initialValues.put(TITULO,myOffer.getTitle());
        initialValues.put(DESCRIPTION,myOffer.getDescription());
        initialValues.put(LONGITUDE,myOffer.getLongitude());
        initialValues.put(PHONE,myOffer.getPhone());
        initialValues.put(DAY,myOffer.getDay());
        initialValues.put(MONTH,myOffer.getMonth());
        initialValues.put(YEAR,myOffer.getYear());
        bd.insert(TABLE_NAME,null,initialValues);
        close();
    }

    /**
     * Return arrayList with all the offers in the database
     * @return Database Offers
     */
    public ArrayList<Offer> getAllOffers(){
        ArrayList<Offer> myOffers = new ArrayList<>();
        open();
        Cursor result = bd.query(TABLE_NAME, new String[]{_ID,TITULO,DESCRIPTION,LATITUDE,LONGITUDE,PHONE,DAY,MONTH,YEAR}, null,null,null,null,null);
        while (result.moveToNext()) {
            Offer myOffer = new Offer();
            myOffer.setTitle(result.getString(1));
            myOffer.setDescription(result.getString(2));
            myOffer.setLatitude(result.getFloat(3));
            myOffer.setLongitude(result.getFloat(4));
            myOffer.setPhone(result.getString(5));
            myOffer.setDay(result.getInt(6));
            myOffer.setMonth(result.getInt(7));
            myOffer.setYear(result.getInt(8));
            myOffers.add(myOffer);
        }
        result.close();
        close();
        return myOffers;
    }

    /**
     * Open la BDD
     * @return este objeto
     */
    public OffersDbInterface open(){
        bd= myHelp.getWritableDatabase();
        return this;
    }

    /**
     * Close the BDD
     */
    private void close(){
        myHelp.close();
    }

    /**
     * Clean the dataBase
     */
    public void cleanDB(){
        open();
        bd.delete(TABLE_NAME,null,null);
        close();
    }
}
