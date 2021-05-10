package ca.qc.bdeb.sim202;

import java.io.*;

public class Partie {
    public static void ecrireBinaire(Niveau partie) {
        File file = new File("partie.sav");
        ObjectOutputStream oos;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file, false);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(partie);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            System.out.println("Une erreur est survenue.");
            System.exit(1);
        }
        System.exit(0);
    }

    public static Niveau lireBinaire() {
        Niveau niveau = null;
        String nomfichier = "partie.sav";
        ObjectInputStream ois;

        try {
            ois = new ObjectInputStream(new FileInputStream(nomfichier));
            niveau = (Niveau) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException ignored) {

        }
        return niveau;
    }

    }


