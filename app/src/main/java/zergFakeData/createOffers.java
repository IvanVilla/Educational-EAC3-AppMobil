package zergFakeData;

import android.util.Log;

import java.util.ArrayList;

import model.Offer;

/**
 * Create a collection of fake data for testing purpouses
 * Created by Klaussius on 09/11/2016.
 */

public class CreateOffers {
    private final static String offersFakeSource[][]={
            {"Oferta 1","Descripción 1","0","0","600000001","1","1","2016"},
            {"Oferta 2","Descripción 2","0","0","600000002","2","2","2016"},
            {"Oferta 3","Descripción 3","0","0","600000003","3","3","2016"},
            {"Oferta 4","Descripción 4","0","0","600000004","4","4","2016"},
    };

    private ArrayList<Offer> offers;

    /**
     * Constructor
     */
    public CreateOffers() {
        offers = new ArrayList<>();
    }

    public void createFakeCollection() {
        for (String[] element : offersFakeSource) {
            String title = element[0];
            String description = element[1];
            float latitude = Float.parseFloat(element[2]);
            float longitude = Float.parseFloat(element[3]);
            String phone = element[4];
            int day = Integer.parseInt(element[5]);
            int month = Integer.parseInt(element[6]);
            int year = Integer.parseInt(element[7]);
            offers.add(new Offer(title, description, latitude, longitude, phone, day, month, year));
        }
        Log.i("Info", "Created " + offers.size() + " fake objects.");
    }

    /***
     * Get the fake offers list
     * @return fake offers
     */
    public ArrayList<Offer> getOffers() {
        return offers;
    }
}
