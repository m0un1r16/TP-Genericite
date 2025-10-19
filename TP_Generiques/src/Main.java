import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Media> stock = new ArrayList<>();
        stock.add(new Livre("Le Petit Prince", 1943, "Antoine de Saint-Exupéry", 96));
        stock.add(new CD("Thriller", 1982, "Michael Jackson", 42));
        stock.add(new Livre("Clean Code", 2008, "Robert C. Martin", 464));
        stock.add(new CD("Random Access Memories", 2013, "Daft Punk", 74));
        stock.add(new Livre("Effective Java", 2018, "Joshua Bloch", 416));
        stock.add(new CD("25", 2015, "Adele", 48));

        Set<Membre> membres = new HashSet<>();
        Membre alice = new Membre("Alice", 1);
        Membre bob = new Membre("Bob", 2);
        Membre anna = new Membre("Anna", 3);
        membres.add(alice);
        membres.add(bob);
        membres.add(anna);
        membres.add(new Membre("AliceDup", 1));

        Map<Membre, List<Media>> emprunts = new HashMap<>();
        alice.emprunterMedia(stock.get(0));
        alice.emprunterMedia(stock.get(5));
        emprunts.put(alice, alice.getMediasEmpruntes());

        bob.emprunterMedia(stock.get(2));
        emprunts.put(bob, bob.getMediasEmpruntes());

        emprunts.putIfAbsent(anna, anna.getMediasEmpruntes());

        System.out.println("=== Stock complet ===");
        CollectionsUtils.afficherListe(stock);

        System.out.println("\n=== Membres (Set) ===");
        CollectionsUtils.afficherListe(new ArrayList<>(membres));

        System.out.println("\n=== Médias publiés après 2010 ===");
        List<Media> mediasApres2010 = CollectionsUtils.filtrer(stock, m -> m.getAnneePublication() > 2010);
        CollectionsUtils.afficherListe(mediasApres2010);

        System.out.println("\n=== Membres dont le nom commence par 'A' ===");
        List<Membre> membresCommencentA = CollectionsUtils.filtrer(new ArrayList<>(membres),
                mem -> mem.getNom().startsWith("A"));
        CollectionsUtils.afficherListe(membresCommencentA);

        Comparator<Media> compMedia = Comparator
                .comparingInt(Media::getAnneePublication).reversed()
                .thenComparing(Media::getTitre);

        Comparator<Livre> compLivre = Comparator
                .comparing(Livre::getAuteur)
                .thenComparing(Livre::getTitre);

        List<Media> mediasPourTri = new ArrayList<>(stock);
        mediasPourTri.sort(compMedia);
        System.out.println("\n=== Médias triés (année décroissante, puis titre) ===");
        CollectionsUtils.afficherListe(mediasPourTri);

        List<Livre> livres = new ArrayList<>();
        for (Media m : stock) {
            if (m instanceof Livre) {
                livres.add((Livre) m);
            }
        }
        livres.sort(compLivre);
        System.out.println("\n=== Livres triés (auteur, puis titre) ===");
        CollectionsUtils.afficherListe(livres);

        List<Media> copieStock = new ArrayList<>();
        CollectionsUtils.copierCollection(stock, copieStock);
        System.out.println("\n=== Copie du stock (vérification) ===");
        CollectionsUtils.afficherListe(copieStock);

        System.out.println("\n=== Médias empruntés (sans doublons) ===");
        Set<Media> mediasEmpruntesSet = new HashSet<>();
        for (List<Media> listeMedias : emprunts.values()) {
            mediasEmpruntesSet.addAll(listeMedias);
        }
        CollectionsUtils.afficherListe(new ArrayList<>(mediasEmpruntesSet));

        System.out.println("\n=== Filtrage polymorphe : uniquement les Livres ===");
        List<Media> seulsLivresMedia = CollectionsUtils.filtrer(stock, m -> m instanceof Livre);
        List<Livre> seulsLivres = new ArrayList<>();
        for (Media m : seulsLivresMedia) {
            seulsLivres.add((Livre) m);
        }
        CollectionsUtils.afficherListe(seulsLivres);

        System.out.println("\n=== Afficher détails (polymorphisme) sur tout le stock ===");
        for (Media m : stock) {
            m.afficherDetails();
        }

        System.out.println("\n=== Map emprunts (Membre -> emprunts) ===");
        for (Map.Entry<Membre, List<Media>> entry : emprunts.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}