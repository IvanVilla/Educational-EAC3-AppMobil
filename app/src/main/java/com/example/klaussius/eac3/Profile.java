package com.example.klaussius.eac3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import dataBase.DbInterfaceProfile;

/**
 * Class for profile edit
 */
public class Profile extends AppCompatActivity {
    TextView tvContent;

    /**
     * OnCreate inicialice the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Content
        tvContent=(TextView)findViewById(R.id.tvContent);
        tvContent.setText(showProfile());
    }

    /**
     * Read profile from the database
     * @return profile
     */
    public String showProfile(){
        String text ="";
        model.Profile myProfile;
        DbInterfaceProfile dbInterfaceProfile = new DbInterfaceProfile(this);
        myProfile = dbInterfaceProfile.getProfile();
        Log.i("Database","The profile was read.");
        text = text+myProfile.toString()+"\n\n";
        return text;
    }
}
