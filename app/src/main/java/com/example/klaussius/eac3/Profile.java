package com.example.klaussius.eac3;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import dataBase.DbInterfaceProfile;

/**
 * Class for profile edit
 */
public class Profile extends AppCompatActivity {

    private TextView tvContent;
    private Button btSaveProfile;
    private Button btClearProfile;
    private Button btTakeBack;
    private EditText etName;
    private EditText etSurname;
    private EditText etDescription;
    private EditText etImage;
    private Toolbar toolbar;

    /**
     * OnCreate inicialice the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        // Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Edit text
        etName=(EditText)findViewById(R.id.etName);
        etSurname=(EditText)findViewById(R.id.etSurname);
        etDescription=(EditText)findViewById(R.id.etDescription);
        etImage=(EditText)findViewById(R.id.etImage);
        // Buttons
        btSaveProfile=(Button)findViewById(R.id.btSaveProfile);
        btClearProfile =(Button)findViewById(R.id.btClearProfile);
        btTakeBack=(Button)findViewById(R.id.btTakeBack);
        // Button Actions
        btSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProfile();
            }
        });
        btClearProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearForm();
            }
        });
        btTakeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeBack();
            }
        });
        // Content
        tvContent=(TextView)findViewById(R.id.tvContent);
        tvContent.setText(showProfile());
    }

    //Actions for the buttons
    /**
     * Saves profile
     */
    public void saveProfile(){
        if (validateData()){
            Log.i("Save Profile","Data fields are filled properly.");
            takeBack();
        }
        // Snackbar
        Snackbar snackbar =
                Snackbar.make(toolbar,"[ERROR] Debes rellenar todos los campos",Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void clearForm(){
        etName.setText("");
        etSurname.setText("");
        etDescription.setText("");
        etImage.setText("");
    }

    /**
     * Close the activity
     */
    public void takeBack(){
        this.finish();
    }

    //My code

    /**
     * Validate the data on the form
     * @return validated data
     */
    public boolean validateData(){
        boolean dataOk=true;
        if (etName.getText().toString().equals("")){
            Log.i("Save Profile","Name field is void.");
            dataOk=false;
        }
        if (etSurname.getText().toString().equals("")){
            Log.i("Save Profile","Surname field is void.");
            dataOk=false;
        }
        if (etDescription.getText().toString().equals("")){
            Log.i("Save Profile","Description field is void.");
            dataOk=false;
        }
        if (etImage.getText().toString().equals("")){
            Log.i("Save Profile","Image field is void");
            dataOk=false;
        }
        return dataOk;
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
