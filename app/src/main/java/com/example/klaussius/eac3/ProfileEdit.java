package com.example.klaussius.eac3;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dataBase.DbInterfaceProfile;
import model.Profile;

/**
 * Class for profile edit
 */
public class ProfileEdit extends AppCompatActivity {

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
        setContentView(R.layout.activity_profile_edit);
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
    }

    //Actions for the buttons
    /**
     * Saves profile
     */
    public void saveProfile(){
        if (validateData()){
            Log.i("Save ProfileEdit","Data fields are filled properly.");
            DbInterfaceProfile dbInterfaceProfile = new DbInterfaceProfile(this);
            Profile profile = new Profile();
            profile.setName(etName.getText().toString());
            profile.setSurname(etSurname.getText().toString());
            profile.setDescription(etDescription.getText().toString());
            profile.setImage(etImage.getText().toString());
            dbInterfaceProfile.insertProfile(profile);
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
            Log.i("Save ProfileEdit","Name field is void.");
            dataOk=false;
        }
        if (etSurname.getText().toString().equals("")){
            Log.i("Save ProfileEdit","Surname field is void.");
            dataOk=false;
        }
        if (etDescription.getText().toString().equals("")){
            Log.i("Save ProfileEdit","Description field is void.");
            dataOk=false;
        }
        if (etImage.getText().toString().equals("")){
            Log.i("Save ProfileEdit","Image field is void");
            dataOk=false;
        }
        return dataOk;
    }
}
