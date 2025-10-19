import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Membre {
    private String nom;
    private int id;
    private List<Media> mediasEmpruntes;

    public Membre(String nom, int id) {
        this.nom = nom;
        this.id = id;
        this.mediasEmpruntes = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public List<Media> getMediasEmpruntes() {
        return mediasEmpruntes;
    }

    public void emprunterMedia(Media media) {
        mediasEmpruntes.add(media);
    }


    public String toString() {
        return "Membre {" + nom + ", " + id + "}";
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Membre)) return false;
        Membre membre = (Membre) o;
        return id == membre.id;
    }


    public int hashCode() {
        return Objects.hash(id);
    }
}
