package com.example.klaussius.eac3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;

import dataBase.DbInterfaceOffers;
import model.Offer;

public class OfferDetailsActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvDescription;
    TextView tvPhone;
    TextView tvDate;
    // Map
    private GoogleMap mMap;
    private Float latitude;
    private Float longitude;

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

    }

    public Offer getOffer(String title){
        DbInterfaceOffers dbInterfaceOffers = new DbInterfaceOffers(this);
        return dbInterfaceOffers.getOfferByTitle(title);
    }
}
