package com.example.klaussius.eac3;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import dataBase.DbInterfaceOffers;
import model.Offer;

public class OfferMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Settings for the map
        UiSettings mapSettings = mMap.getUiSettings();
        mapSettings.setZoomControlsEnabled(true);

        // Add markers in our map
        for (Offer offer : readDbOffers()){
            LatLng offerLatLng = new LatLng(offer.getLatitude(),offer.getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .position(offerLatLng)
                    .title(offer.getTitle())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.offericon)));
        }

        Log.i("Map","Offers located in the map");
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
