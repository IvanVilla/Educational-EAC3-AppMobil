package com.example.klaussius.eac3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import dataBase.DbInterfaceOffers;
import model.Offer;
import zfakeData.CreateOffers;

/**
 * Main activity
 *
 * EXTRA:
 * Me hubiera encantado implementar una lista en la cual aparecieran las ofertas de más reciente
 * a más antigua, por un lado, y otro filtro de lejanía con respecto a nuestra posición actual,
 * usando el teorema de pitágoras para calcular la distancia desde el punto actual.
 *
 * Por desgracia por falta de tiempo y cuestiones personales, no he podido llegar a hacerlo.
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvOffersNumber;
    private ListView lvOffers;

    /**
     * OnCreate MainActivity Activity
     * @param savedInstanceState saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        createFakeData(); // Create fake data to database
        final ArrayList<Offer>myOffers=readDbOffers();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //TextView
        tvOffersNumber=(TextView)findViewById(R.id.tvOffersNumber);
        tvOffersNumber.setText("Mostrando "+myOffers.size()+" elementos.");
        //ListView
        lvOffers=(ListView)findViewById(R.id.lvOffers);
        ArrayAdapterOffer arrayAdapterOffer = new ArrayAdapterOffer(this,myOffers);
        lvOffers.setAdapter(arrayAdapterOffer);
        lvOffers.setClickable(true);
        lvOffers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openDetail(myOffers.get(i).getTitle());
            }
        });
    }

    /**
     * OnCreate Options Menu
     * @param menu menu
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    /**
     * OnSelect Option Item
     * @param item item
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        // Abro el mapa
        if (id==R.id.mapButton){
            openOfferMap();
        }

        // Abro el perfil
        if (id==R.id.userButton){
            openProfile();
        }
        return super.onOptionsItemSelected(item);
    }

    // Button Actions

    /**
     * Open the offer_for_listview with the title
     * @param title title of the offer_for_listview
     */
    public void openDetail(String title){
        Intent openDetails = new Intent(this,OfferDetailsActivity.class);
        openDetails.putExtra("title",title);
        startActivity(openDetails);
    }

    /**
     * Open the profile menu
     */
    public void openProfile(){
        Intent profile = new Intent(this, ProfileShowActivity.class);
        startActivity(profile);
    }

    /**
     * Open the offer_for_listview map menu
     */
    public void openOfferMap(){
        Intent offerMap = new Intent(this, OfferMapActivity.class);
        startActivity(offerMap);
    }

    // My Code
    /**
     * Clean the database
     * Create a fake collection on offer_for_listview and save it on the database
     * Create a fake profile on profile and save it on the database
     */
    public void createFakeData(){
        // Fake Offers
        DbInterfaceOffers dbInterfaceOffers = new DbInterfaceOffers(this);
        dbInterfaceOffers.cleanTable();
        CreateOffers offers = new CreateOffers();
        offers.createFakeCollection();
        for (Offer item : offers.getOffers()){
            dbInterfaceOffers.insertOffer(item);
        }
        Log.i("Database",offers.getOffers().size()+" items were inserted.");
    }

    /**
     * Get the offers from the DataBase
     * @return offers
     */
    public ArrayList<Offer> readDbOffers(){
        DbInterfaceOffers dbInterfaceOffers = new DbInterfaceOffers(this);
        return dbInterfaceOffers.getAllOffers();
    }
}
