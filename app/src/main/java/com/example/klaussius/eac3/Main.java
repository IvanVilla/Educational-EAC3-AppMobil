package com.example.klaussius.eac3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import dataBase.OffersDbInterface;
import model.Offer;
import zergFakeData.CreateOffers;

public class Main extends AppCompatActivity {

    TextView tvMain;

    /**
     * OnCreate Main Activity
     * @param savedInstanceState saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Controls
        tvMain = (TextView)findViewById(R.id.tvMain);

        // My code
        //createFakeOffers();
        tvMain.setText(readDatabase());
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // My Code
    /**
     * Clean the database, create a fake collection on offers and save it on the database
     */
    public void createFakeOffers(){
        OffersDbInterface offersDbInterface = new OffersDbInterface(this);
        offersDbInterface.cleanDB();
        CreateOffers offers = new CreateOffers();
        offers.createFakeCollection();
        for (Offer item : offers.getOffers()){
            offersDbInterface.insertOffer(item);
        }
        Log.i("Database",offers.getOffers().size()+" items were inserted.");
    }

    /**
     * Read data fron the database
     */
    public String readDatabase(){
        String text="";
        List<Offer> myOffers;
        OffersDbInterface offersDbInterface = new OffersDbInterface(this);
        myOffers = offersDbInterface.getAllOffers();
        Log.i("Database",myOffers.size()+" items were read.");
        for (Offer offer : myOffers){
            text=text+offer.toString()+"\n";
        }
        return text;
    }
}
