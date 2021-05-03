package ca.qc.bdeb.sim202;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Niveau {
    // numero du niveau
    private int numero;
    // liste de tuiles
    private Tuile[][] tuiles;
    // liste de monstres
    private ArrayList<Monstre> monstres = new ArrayList<Monstre>(); //mettre ca en arrayList
    // Heros
    private Heros heros;

    /**
     * Lis le niveau et remplis les attributs requis
     * @param numero le numero du niveau
     */
    public Niveau(int numero) {
        try {
            File f = new File("src/ca/qc/bdeb/sim202/"+numero+".txt");
            Scanner r = new Scanner(f);
            int numeroLigne = 0; //numero de la ligne
            boolean lectureNiveau = false; //true si la lecture du plateau est commencee
            int[] tailleNiveau = tailleNiveau(numero);
            // liste de tuiles
            assert tailleNiveau != null;
            this.tuiles = new Tuile[tailleNiveau[0]][tailleNiveau[1]];
            // liste de monstres

            while (r.hasNextLine()) {
                String ligne = r.nextLine();
                //premiere ligne = position initiale heros
                if (numeroLigne == 0) {
                    int[] position = new int[2];
                    for (int i = 0; i < ligne.split(",").length; i++) {
                        position[i] = Integer.parseInt(ligne.split(",")[i]);
                    }
                    this.heros = new Heros(position, 6, 1);
                }

                //place les tuiles
                if (ligne.toCharArray()[0] == '#') {

                }

                //place les objets
                if (ligne.indexOf(':') > 0) { // regarder si la ligne contient un ':'
                    switch (ligne.split(":")[0]) {
                        case "pancarte":
                            break;
                        case "tresor":
                            break;
                        case "monstre":
                            break;
                        case "teleporteur":
                            break;
                    }

                }


                numeroLigne++;
            }
            r.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable.");
        }
    }

    /**
     * @param numero Le numero du niveau
     * @return Les dimensions du niveau [ligne][colonnes]
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

    public int getNumero() {
        return numero;
    }

    public Tuile[][] getTuiles() {
        return tuiles;
    }

    public ArrayList<Monstre> getMonstres() {
        return monstres;
    }

    public Heros getHeros() {
        return heros;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setTuiles(Tuile[][] tuiles) {
        this.tuiles = tuiles;
    }

    public void setMonstres(ArrayList<Monstre> monstres) {
        this.monstres = monstres;
    }

    public void setHeros(Heros heros) {
        this.heros = heros;
    }
}
