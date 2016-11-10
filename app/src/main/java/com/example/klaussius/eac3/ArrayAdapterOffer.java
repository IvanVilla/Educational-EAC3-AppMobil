package com.example.klaussius.eac3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import model.Offer;

/**
 * Created by Klaussius on 09/11/2016.
 */

public class ArrayAdapterOffer extends ArrayAdapter<Offer> {
    /**
     * Constructor
     * @param context
     * @param objects
     */
    public ArrayAdapterOffer(Context context, List<Offer> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Get the data item for this position
        Offer offer = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.offer_for_listview, parent, false);
        }

        // Lookup view for data population

        TextView tvOfferTitle = (TextView) convertView.findViewById(R.id.tvOfferTitle);
        TextView tvOfferDescription = (TextView) convertView.findViewById(R.id.tvOfferDescription);

        // Populate the data into the template view using the data object
        tvOfferTitle.setText(offer.getTitle());
        tvOfferDescription.setText(offer.getDescription());

        // Return the completed view to render on screen
        return convertView;
    }
}
