package com.veliparis.objet;

import java.util.Date;

/**
 * Created by claudia on 23/08/2016.
 */
public class Course {

    private int idUtilisateur;
    private String depart;
    private String arrive;
    private int duree;
    private Date date;


    public Course(String arrive, Date date, String depart, int duree, int idUtilisateur) {
        this.arrive = arrive;
        this.date = date;
        this.depart = depart;
        this.duree = duree;
        this.idUtilisateur = idUtilisateur;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
}
