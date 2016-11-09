package zergFakeData;

import model.Profile;

/**
 * Create a fake profile for test purpouse
 * Created by Klaussius on 09/11/2016.
 */
public class CreateProfile {

    private String name = "Arthur";
    private String surname = "Dent";
    private String description = "A Brithish citizen from the former-Earth. Sector ZZ-Plural-Z-Alpha";
    private String image ="arthurDent.jpg";

    /**
     * Constructor for the class
     */
    public CreateProfile() {
    }

    /**
     * Return the fake profile
     * @return fake profile
     */
    public Profile createFakeProfile(){
        return new Profile(name,surname,description,image);
    }

}
