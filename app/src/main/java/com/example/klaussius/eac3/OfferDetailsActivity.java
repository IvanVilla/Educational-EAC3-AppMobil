package com.example.klaussius.eac3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import dataBase.DbInterfaceOffers;
import model.Offer;

/**
 * Details of the offer
 */
public class OfferDetailsActivity extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvPhone;
    private TextView tvDate;
    // Map
    private Float latitude;
    private Float longitude;

    private View map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Retrieve the offer_for_listview
        Offer offer = getOffer(getIntent().getExtras().getString("title"));

        //TextViews
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvDescription = (TextView)findViewById(R.id.tvDescription);
        tvPhone = (TextView)findViewById(R.id.tvPhone);
        tvDate = (TextView)findViewById(R.id.tvDate);

        //Set values
        tvTitle.setText(offer.getTitle());
        tvDescription.setText(offer.getDescription());
        tvPhone.setText(offer.getPhone());
        tvDate.setText(offer.getDay()+"/"+offer.getMonth()+"/"+offer.getYear());
        latitude = offer.getLatitude();
        longitude = offer.getLongitude();

        //Map
        map = (View)findViewById(R.id.map);

    }

    /**
     * Get the offer object
     * @param title
     * @return
     */
    public Offer getOffer(String title){
        DbInterfaceOffers dbInterfaceOffers = new DbInterfaceOffers(this);
        return dbInterfaceOffers.getOfferByTitle(title);
    }
}
