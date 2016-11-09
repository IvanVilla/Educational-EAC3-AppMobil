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
    public static final String TABLE_NAME = "offer";

    public static final String _ID="_id";
    public static final String TITULO = "titulo";

    private final Context context;
    private OffersDbHelper myHelp;
    private SQLiteDatabase bd;

    /**
     * Constructor
     * @param context
     */
    public OffersDbInterface(Context context) {
        this.context = context;
        myHelp = new OffersDbHelper(context);
    }

    /**
     * Open la BDD
     * @return este objeto
     */
    public OffersDbInterface abrir(){
        bd= myHelp.getWritableDatabase();
        return this;
    }

    /**
     * Close the BDD
     */
    public void cerrar(){
        myHelp.close();
    }

    /**
     * Vacía la base de datos
     */
    public void cleanDB(){
        abrir();
        bd.delete(TABLE_NAME,null,null);
        cerrar();
    }

    /**
     * Insert one offer in the dataBase
     * @param myOffer
     * @return -1 si no se ha podido realizar
     */
    public void insertOffer(Offer myOffer){
        abrir();
        ContentValues initialValues = new ContentValues();
        initialValues.put(TITULO,myOffer.getTitle());
        bd.insert(TABLE_NAME,null,initialValues);
        cerrar();
    }

    /**
     * Return arrayList with all the offers in the database
     * @return Database Offers
     */
    public ArrayList<Offer> getAllOffers(){
        ArrayList<Offer> myOffers = new ArrayList<>();
        abrir();
        Cursor resultado= bd.query(TABLE_NAME, new String[]{_ID,TITULO}, null,null,null,null,null);
        while (resultado.moveToNext()) {
            Offer myOffer = new Offer();
            myOffer.setTitle(resultado.getString(1));
            myOffers.add(myOffer);
        }
        cerrar();
        return myOffers;
    }
}
