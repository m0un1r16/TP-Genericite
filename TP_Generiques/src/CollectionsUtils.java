import java.util.*;
import java.util.function.Predicate;

public class CollectionsUtils {

    public static <T> void afficherListe(List<T> liste) {
        for (T e : liste) {
            System.out.println(e);
        }
    }

    public static <T> List<T> filtrer(List<T> liste, Predicate<T> critere) {
        List<T> resultat = new ArrayList<>();
        for (T e : liste) {
            if (critere.test(e)) {
                resultat.add(e);
            }
        }
        return resultat;
    }

    public static <T> void copierCollection(Collection<T> source, Collection<T> destination) {
        destination.addAll(source);
    }
}
