package com.example.klaussius.eac3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class OfferDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView tvTitle;
        TextView tvDescription;
        TextView tvPhone;
        TextView date;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Take the title
        Intent intent = getIntent();
        String titulo =""+intent.getExtras().getString("title");

        //TextViews

    }
}
