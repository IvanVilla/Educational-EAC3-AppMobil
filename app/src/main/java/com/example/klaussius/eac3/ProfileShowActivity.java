package com.example.klaussius.eac3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dataBase.DbInterfaceProfile;
import model.Profile;

/**
 * Show us the user profile
 */
public class ProfileShowActivity extends AppCompatActivity {
    TextView tvName;
    TextView tvSurname;
    TextView tvDescription;
    Button btEdit;
    Button btTakeBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_show);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TextViews
        tvName = (TextView)findViewById(R.id.tvName);
        tvSurname = (TextView)findViewById(R.id.tvSurname);
        tvDescription = (TextView)findViewById(R.id.tvDescription);

        // Buttons
        btEdit = (Button)findViewById(R.id.btEdit);
        btTakeBack = (Button)findViewById(R.id.btTakeBack);
        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit();
            }
        });
        btTakeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeMeBack();
            }
        });

        // Fill the fields
        fillFields();
    }


    //My Code
    public void fillFields(){
        DbInterfaceProfile dbInterfaceProfile = new DbInterfaceProfile(this);
        Profile profile = dbInterfaceProfile.getProfile();
        tvName.setText(profile.getName());
        tvSurname.setText(profile.getSurname());
        tvDescription.setText(profile.getDescription());
    }


    // Actions for the buttons

    /**
     * Start activity to edit profile
     */
    public void edit(){
        Intent myIntent = new Intent(this,ProfileEditActivity.class);
        startActivity(myIntent);
    }

    /**
     * Finished this activity and take us back
     */
    public void takeMeBack(){
        this.finish();
    }
}
