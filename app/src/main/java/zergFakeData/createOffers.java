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
            {"Presidente del Gobierno","Requiere movilidad geográfica. Sin experiencia.","40.445355","-3.734257","600000001","1","1","2016"},
            {"Futbolista","Debe superar una batería de pruebas físicas y médicas.","36.502768","-6.272932","600000002","2","2","2016"},
            {"Piloto de Combate Espacial","Academia Militar. Para personas de edades entre 18 y 21 años. 5 años de formación remunerada + contrato indefinido.","40.759299","-111.9015647","600000003","3","3","2016"},
            {"Comercial de Tiempo","Sector turístico - viajes en el tiempo. Movilidad geográfica y temporal. Clientes de alto standing. Comisiones. No se requiere experiencia.","-33.954850","25.562235","600000004","4","4","2016"},
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
