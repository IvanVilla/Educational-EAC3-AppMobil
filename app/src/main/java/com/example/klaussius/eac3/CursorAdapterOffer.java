package com.example.klaussius.eac3;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Adapter for the listview
 * Created by Klaussius on 09/11/2016.
 */
public class CursorAdapterOffer extends CursorAdapter {

    public CursorAdapterOffer(Context context, Cursor myOffers){
        super(context, myOffers, 0);
    }

    // Inflate new item
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.offer_for_listview, parent, false);
    }

    // Extract data and fill item
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Select View container
        TextView offertTitle = (TextView) view.findViewById(R.id.tvOfferTitle);
        TextView offertDescription = (TextView) view.findViewById(R.id.tvOfferDescription);

        // Extract info from cursor
        String offerTitle = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        String offerDescription = cursor.getString(cursor.getColumnIndexOrThrow("description"));

        // Fill
        offertTitle.setText(offerTitle);
        offertDescription.setText(offerDescription);
    }
}
