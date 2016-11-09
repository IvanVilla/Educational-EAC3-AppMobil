package dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import model.Offer;
import model.Profile;

/**
 * Created by Klaussius on 09/11/2016.
 */
public class DbInterfaceProfile {

    protected static final String TABLE_NAME = "profile";

    protected static final String _ID = "_id";
    protected static final String NAME = "name";
    protected static final String SURNAME = "surname";
    protected static final String DESCRIPTION = "description";
    protected static final String IMAGE = "image";

    private final Context context;
    private DbHelper myHelp;
    private SQLiteDatabase bd;

    /**
     * Constructor
     * @param context contexto
     */
    public DbInterfaceProfile(Context context) {
        this.context = context;
        myHelp = new DbHelper(context);
    }

    /**
     * Insert one offer in the dataBase
     * @param myProfile profile to insert
     */
    public void insertProfile(Profile myProfile){
        open();
        ContentValues initialValues = new ContentValues();
        initialValues.put(NAME,myProfile.getName());
        initialValues.put(SURNAME,myProfile.getSurname());
        initialValues.put(DESCRIPTION,myProfile.getDescription());
        initialValues.put(IMAGE,myProfile.getImage());
        bd.insert(TABLE_NAME,null,initialValues);
        close();
    }

    /**
     * Return arrayList with all the offers in the database
     * @return Database Offers
     */
    public Profile getProfile(){
        ArrayList<Offer> myOffers = new ArrayList<>();
        open();
        Profile myProfile = new Profile();
        Cursor result = bd.query(TABLE_NAME, new String[]{_ID,NAME,SURNAME,DESCRIPTION,IMAGE}, null,null,null,null,null);
        while (result.moveToNext()) {
            myProfile.setName(result.getString(1));
            myProfile.setSurname(result.getString(2));
            myProfile.setDescription(result.getString(3));
            myProfile.setImage(result.getString(4));
        }
        result.close();
        close();
        return myProfile;
    }

    /**
     * Open la BDD
     * @return este objeto
     */
    public DbInterfaceProfile open(){
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
    public void cleanTable(){
        open();
        bd.delete(TABLE_NAME,null,null);
        close();
    }
}
