package com.veliparis.objet;


public class Utilisateur {

    private String pseudo;
    private String mdp;
    private String email;
    private String urlPhoto;
    private String velib;

    public Utilisateur(String email, String pseudo, String mdp, String urlPhoto ) {
        this.email = email;
        this.mdp = mdp;
        this.urlPhoto = urlPhoto;
        this.pseudo  = pseudo;

    }

    //utilisateur sans photo et sans velib
    public Utilisateur(String email, String pseudo, String mdp ) {
        this.email = email;
        this.mdp = mdp;
        this.urlPhoto = urlPhoto;
        this.pseudo  = pseudo;

    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {this.email = email;}


    public String getMdp() {
        return mdp;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }


    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }


    public String getUrlPhoto() {
        return urlPhoto;
    }
    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
}
