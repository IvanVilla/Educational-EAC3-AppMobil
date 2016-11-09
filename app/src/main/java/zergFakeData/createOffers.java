package zergFakeData;

import java.util.ArrayList;

import model.Offer;

/**
 * Create a collection of fake data for testing purpouses
 * Created by Klaussius on 09/11/2016.
 */

public class createOffers {
    private final static String offersFakeSource[][]={
            {"Oferta 1","Descripción 1","0","0","600000001","1","1","2016"},
            {"Oferta 2","Descripción 2","0","0","600000002","2","2","2016"},
            {"Oferta 3","Descripción 3","0","0","600000003","3","3","2016"},
            {"Oferta 4","Descripción 4","0","0","600000004","4","4","2016"},
            {"Oferta 5","Descripción 5","0","0","600000005","5","5","2016"},
            {"Oferta 6","Descripción 6","0","0","600000006","6","6","2016"}
    };

    private ArrayList<Offer> offers;

    /**
     * Constructor
     */
    public createOffers() {
        offers = new ArrayList<>();
    }

    public void createFakeCollection(){
        for (String[] element:offersFakeSource){
            String title = element[0];
            String description = element[1];
            float latitude = Float.parseFloat(element[2]);
            float longitude = Float.parseFloat(element[3]);
            String phone = element[4];
            int day = Integer.parseInt(element[4]);
            int month = Integer.parseInt(element[4]);
            int year = Integer.parseInt(element[4]);
            offers.add(new Offer(title,description,latitude,longitude,phone,day,month,year));
        }
    }
}
