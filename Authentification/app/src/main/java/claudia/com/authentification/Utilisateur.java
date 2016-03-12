package claudia.com.authentification;


public class Utilisateur {

    private String nom;
    private String prenom;
    private String mdp;
    private String email;
    private boolean genre;

    public Utilisateur(String email, boolean genre, String mdp, String nom, String prenom) {
        this.email = email;
        this.genre = genre;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGenre() {
        return genre;
    }

    public void setGenre(boolean genre) {
        this.genre = genre;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
