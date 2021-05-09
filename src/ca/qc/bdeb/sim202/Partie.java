package ca.qc.bdeb.sim202;

import java.io.*;

public class Partie implements Serializable {
    /*
    To do

    ***Done***     Héros ligne 86. Rendre texte une propriété d'item pour afficher ses caractéristiques
    Doner l'option de sauvegarde
    Sérializer
    ***Done*** Monstres déplacement
    Monstres interaction
    Fonction attaquer pour héros

        Java doc

     */
    private static Partie lireBinaire() {
        Partie partie = null;
        String nomfichier = "partie.sav";
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new FileInputStream(nomfichier));
            partie = (Partie) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Une erreur est survenue.");
            System.exit(0);
        }
        return partie;
    }

    private static void ecrireBinaire(Partie partie) {
        File file = new File("partie.sav");
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file, false);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(partie);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            System.out.println("Erreur");
            System.exit(1);
        }
        System.exit(0);
    }

}
