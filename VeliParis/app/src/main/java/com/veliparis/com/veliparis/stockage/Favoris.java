package com.veliparis.com.veliparis.stockage;

/**
 * Created by claudia on 04/02/2017.
 */
public class Favoris {

    private long id;
    private double longitude;
    private double latitude ;
    private String nom;


    public Favoris(long id, double latitude, double longitude, String nom) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nom = nom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
