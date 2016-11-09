package model;

/**
 * Este código almacena las ofertas para poder trabajar con ellas en la aplicación
 * Created by Klaussius on 09/11/2016.
 */

public class Offer {
    private String title;
    private String description;
    private float latitude;
    private float longitude;
    private String phone;
    private int day;
    private int month;
    private int year;

    /**
     * Constructor without parameters
     */
    public Offer() {
    }

    /**
     * Constructor with parameters
     * @param title título
     * @param description descripción
     * @param latitude latitud geográfica
     * @param longitude longitud geográfica
     * @param phone teléfono
     * @param day día
     * @param month mes
     * @param year año
     */
    public Offer(String title, String description, float latitude, float longitude, String phone, int day, int month, int year) {
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Get title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title
     * @param title title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get latitude
     * @return latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * Set latitude
     * @param latitude latitude
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * Get longitude
     * @return longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * Set longitude
     * @param longitude longitude
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * Get the phone
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set the phone
     * @param phone phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Get the day
     * @return day
     */
    public int getDay() {
        return day;
    }

    /**
     * Set the day
     * @param day day
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Get the month
     * @return month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Set the month
     * @param month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Get the year
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Set the year
     * @param year year
     */
    public void setYear(int year) {
        this.year = year;
    }
}
