package com.example.klaussius.eac3;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;

import dataBase.DbInterfaceProfile;
import model.Profile;

/**
 * Class for profile edit
 */
public class ProfileEditActivity extends AppCompatActivity {

    private Button btSaveProfile;
    private Button btClearProfile;
    private Button btTakeBack;
    private Button btTakePhoto;
    private EditText etName;
    private EditText etSurname;
    private EditText etDescription;
    private ImageView ivProfileImage;
    private Toolbar toolbar;

    private Uri pictureId = null;
    public static final int CAMERA_APP = 0;
    public static final String PATH = "/ProfilePhotos/";

    /**
     * OnCreate inicialice the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // We check permissions for this
        if (!this.checkPermissions()) {
            ActivityCompat.requestPermissions(this, new String[]{
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    0
            );
        }
        setContentView(R.layout.activity_profile_edit);
        // Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Edit text
        etName=(EditText)findViewById(R.id.etName);
        etSurname=(EditText)findViewById(R.id.etSurname);
        etDescription=(EditText)findViewById(R.id.etDescription);
        // Buttons
        btSaveProfile=(Button)findViewById(R.id.btSaveProfile);
        btClearProfile =(Button)findViewById(R.id.btClearProfile);
        btTakeBack=(Button)findViewById(R.id.btTakeBack);
        btTakePhoto=(Button)findViewById(R.id.btTakePhoto);
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
        btTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });
        // ProfileImage
        ivProfileImage=(ImageView)findViewById(R.id.ivProfileImage);
        ivProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });
    }

    //My code

    //Actions for the buttons
    /**
     * Take the photo
     */
    public void takePhoto(){
        // We create the intent and the file
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File path = new File(Environment.getExternalStorageDirectory().toString() + PATH);
        if (!path.exists()) {
            path.mkdir();
        }
        File picture = new File(path.getAbsolutePath(),"Foto.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(picture));
        // We have the path for the photo
        this.pictureId = Uri.fromFile(picture);
        // Begin the camera activity
        startActivityForResult(intent, CAMERA_APP);
    }

    /**
     * We have the image, we need to resize it
     * @param requestCode code for send
     * @param resultCode code for result
     * @param data data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_APP:
                if (resultCode == Activity.RESULT_OK) {
                    //Accedemos al contenido de la imagen
                    ContentResolver contRes = getContentResolver();
                    contRes.notifyChange(this.pictureId, null);
                    Bitmap picture;
                    try {
                        picture = android.provider.MediaStore.Images.Media
                                .getBitmap(contRes, pictureId);
                        //Reducimos la imagen
                        int height = (int) (picture.getHeight() * 800 / picture.getWidth());
                        Bitmap resized = Bitmap.createScaledBitmap(picture, 800, height, true);
                        //Guardamos el mapa de bits
                        FileOutputStream stream = new FileOutputStream(pictureId.toString().replace("file://", ""));
                        resized.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        stream.flush();
                        stream.close();
                        //Lo asignamos al imageview
                        ivProfileImage.setImageBitmap(resized);
                    } catch (Exception e) {
                        Log.i("Photo", "Something were wrong!");
                    }
                }
        }
    }

    /**
     * Saves profile
     */
    public void saveProfile(){
        if (validateData()){
            Log.i("ProfileEdit","Data fields are filled properly.");
            DbInterfaceProfile dbInterfaceProfile = new DbInterfaceProfile(this);
            Profile profile = new Profile();
            profile.setName(etName.getText().toString());
            profile.setSurname(etSurname.getText().toString());
            profile.setDescription(etDescription.getText().toString());
            profile.setImage("Foto.jpg");
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
    }

    /**
     * Close the activity
     */
    public void takeBack(){
        this.finish();
    }

    /**
     * Validate the data on the form
     * @return validated data
     */
    public boolean validateData(){
        boolean dataOk=true;
        if (etName.getText().toString().equals("")){
            Log.i("ProfileEditActivity","Name field is void.");
            dataOk=false;
        }
        if (etSurname.getText().toString().equals("")){
            Log.i("ProfileEditActivity","Surname field is void.");
            dataOk=false;
        }
        if (etDescription.getText().toString().equals("")){
            Log.i("ProfileEditActivity","Description field is void.");
            dataOk=false;
        }
        return dataOk;
    }

    // Permission...
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 0:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                //takePicture();

                } else {

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);

                }
        }
    }

    private boolean checkPermissions() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return false;

        } else {
            return true;
        }
    }
}
