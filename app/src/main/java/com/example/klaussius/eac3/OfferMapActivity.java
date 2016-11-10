package com.example.klaussius.eac3;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import dataBase.DbInterfaceOffers;
import model.Offer;

public class OfferMapActivity extends FragmentActivity implements OnMapReadyCallback, android.location.LocationListener {

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
        //mMap.setMyLocationEnabled(true);

        // Add markers in our map
        for (Offer offer : readDbOffers()) {
            LatLng offerLatLng = new LatLng(offer.getLatitude(), offer.getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .position(offerLatLng)
                    .title(offer.getTitle())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.offericon)));
        }
        Log.i("Map", "Offers located in the map");

        // Markers events
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Log.i("Map", "You clicked the info window!");
                openDetail(marker.getTitle());
            }
        });

        // Set my location
        //this.getMyLocation();
    }

    // Events

    /**
     * OpenDetail, with the title of the Marker
     * @param title
     */
    public void openDetail(String title){
        Intent openDetails = new Intent(this,OfferDetailsActivity.class);
        openDetails.putExtra("title",title);
        startActivity(openDetails);
    }


    // My Code
    /**
     * We get our location
     */
    public void getMyLocation() {
        // LocationManager
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Options
        Criteria options = new Criteria();
        options.setAccuracy(Criteria.ACCURACY_FINE);
        options.setPowerRequirement(Criteria.POWER_MEDIUM);
        options.setAltitudeRequired(false);
        options.setBearingRequired(false);
        options.setSpeedRequired(false);

        // Best Provider
        String provider = locationManager.getBestProvider(options, true);

        // Permissions
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

        // Take last location know
        Location location = locationManager.getLastKnownLocation(provider);

        // Request location each 20 secs
        locationManager.requestLocationUpdates(provider,20000,0,this);
    }

    /**
     * Get the offers from the DataBase
     * @return offers
     */
    public ArrayList<Offer> readDbOffers(){
        DbInterfaceOffers dbInterfaceOffers = new DbInterfaceOffers(this);
        return dbInterfaceOffers.getAllOffers();
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }
}
