package claudia.com.sqlitefavoris.stockage;

/**
 * Objet correspondant à la table Favoris
 */
public class Favoris {

    private long id ;
    private String couleur;
    private String animal;

    public Favoris(String animal, String couleur) {
        this.animal = animal;
        this.couleur = couleur;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }
}
