package model;

/**
 * Guarda el perfil de los usuarios
 * Created by Klaussius on 09/11/2016.
 */

public class Profile {
    private String name;
    private String surname;
    private String description;
    private String image;

    /**
     * Constructor sin parámetros
     */
    public Profile() {
    }

    /**
     * Constructor con parámetros
     * @param name name
     * @param surname surname
     * @param description descripción
     * @param image imagen
     */
    public Profile(String name, String surname, String description, String image) {
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.image = image;
    }

    /**
     * Get the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the surname
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set the surname
     * @param surname surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Get the description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the image
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * Set the image
     * @param image image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Return the profile toString
     * @return the profile in String format
     */
    @Override
    public String toString() {
        return "ProfileEditActivity{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
