import java.util.Objects;

public abstract class Media {
    private String titre;
    private int anneePublication;

    public Media() {
    }

    public Media(String titre, int anneePublication) {
        this.titre = titre;
        this.anneePublication = anneePublication;
    }

    public String getTitre() {
        return titre;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public abstract String getDescription();


    public String toString() {
        return titre + " (" + anneePublication + ")";
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Media)) return false;
        Media media = (Media) o;
        return anneePublication == media.anneePublication &&
               Objects.equals(titre, media.titre);
    }


    public int hashCode() {
        return Objects.hash(titre, anneePublication);
    }

    public void afficherDetails() {
        System.out.println(this.toString() + " → " + getDescription());
    }
}
