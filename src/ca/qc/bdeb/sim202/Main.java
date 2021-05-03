package ca.qc.bdeb.sim202;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        parseNiveau(1);
    }

    /**
     * extrait les donnees des fichiers txt de niveau
     * @param numero est le numero du niveau
     */
    public static void parseNiveau(int numero){
        try {
            File f = new File("src/ca/qc/bdeb/sim202/"+numero+".txt");
            Scanner r = new Scanner(f);
            int numeroLigne = 0; //numero de la ligne
            boolean lectureNiveau = false; //true si la lecture du plateau est commencee
            int[] tailleNiveau = tailleNiveau(numero);
            // liste de tuiles
            Tuile[][] tuiles = new Tuile[tailleNiveau[0]][tailleNiveau[1]];
            // liste de monstres
            Monstre[] monstres;
            // Heros
            Heros heros;

            while (r.hasNextLine()) {
                String ligne = r.nextLine();
                //premiere ligne = position initiale heros
                if (numeroLigne == 0) {
                    int[] position = new int[2];
                    for (int i = 0; i < ligne.split(",").length; i++) {
                        position[i] = Integer.parseInt(ligne.split(",")[i]);
                    }
                    heros = new Heros(position, 6, 1);
                }
                //place les objets
                //place les tuiles

                numeroLigne++;
            }
            r.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable.");
        }
    }

    /**
     * @param numero est le numero du niveau
     * @return taille de la grille du niveau [ligne][colonne]
     */
    public static int[] tailleNiveau(int numero){
        try {
            File f = new File("src/ca/qc/bdeb/sim202/"+numero+".txt");
            Scanner r = new Scanner(f);
            int nbLignes = 0; //nombre de lignes
            int nbColonnes = 0; //nombre de colonnes
            boolean lectureNiveau = false; //true si la lecture du plateau est commencee
            while (r.hasNextLine()) {
                String ligne = r.nextLine();
                //premiere ligne = position initiale heros
                if (ligne.toCharArray()[0] == '#' && !lectureNiveau) {
                    nbColonnes = ligne.toCharArray().length;
                    lectureNiveau = true;
                }

                if (lectureNiveau && ligne.toCharArray()[0] == '#') {
                    nbLignes++;
                }
            }
            r.close();
            return (new int[]{nbLignes, nbColonnes});

        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable.");
        }
        return null;
    }
}
