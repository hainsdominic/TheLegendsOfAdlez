package ca.qc.bdeb.sim202;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Niveau implements Serializable {
    // numero du niveau
    private int numero;
    // liste de tuiles
    private Tuile[][] tuiles;
    // liste de monstres
    private ArrayList<Monstre> monstres = new ArrayList<>(); //mettre ca en arrayList
    // Heros
    private Heros heros;

    /**
     * Construis le niveau et remplis les attributs requis
     * @param numero le numero du niveau
     */
    public Niveau(int numero, Heros heros) {
        this.numero = numero;
        try {
            File f = new File("src/ca/qc/bdeb/sim202/"+numero+".txt");
            Scanner r = new Scanner(f);
            int numeroLigne = 0; //numero de la ligne
            int numeroLignePlateau = 0; //numero de la ligne du plateau de jeu
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
                    position[0] = Integer.parseInt(ligne.split(",")[1]);
                    position[1] = Integer.parseInt(ligne.split(",")[0]);
                    if (heros == null) {
                        this.heros = new Heros(position, 6, 1);
                    } else {
                        this.heros = heros;
                        heros.setPosition(position);
                    }

                }

                //place les objets
                if (ligne.indexOf(':') > 0) { // regarder si la ligne contient un ':'
                    String data;
                    int posX;
                    int posY;
                    switch (ligne.split(":")[0]) {
                        case "pancarte" -> { // Permet d'introduire la pancarte au bon endroit dans le niveau
                            data = ligne.replace("pancarte:", "");
                            posX = Integer.parseInt(data.split(",")[0]);
                            posY = Integer.parseInt(data.split(",")[1]);
                            String message = data.replace(posX + "," + posY  + ",", "");
                            this.tuiles[posY][posX] = new Pancarte(message);
                        }
                        case "tresor" -> { // Permet d'introduire le tr??sor au bon endroit dans le niveau
                            data = ligne.replace("tresor:", "");
                            posX = Integer.parseInt(data.split(",")[0]);
                            posY = Integer.parseInt(data.split(",")[1]);
                            switch (data.split(",")[2]) {
                                case "PotionVie" -> this.tuiles[posY][posX] = new Tresor(new PotionVie());
                                case "PotionForce" -> this.tuiles[posY][posX] = new Tresor(new PotionForce());
                                case "CristalMagique" -> this.tuiles[posY][posX] = new Tresor(new CristalMagique());
                            }
                        }
                        case "teleporteur" -> { // Permet d'introduire le t??l??porteur au bon endroit dans le niveau
                            data = ligne.replace("teleporteur:", "");
                            posX = Integer.parseInt(data.split(",")[0]);
                            posY = Integer.parseInt(data.split(",")[1]);
                            int teleportationY = Integer.parseInt(data.split(",")[3]);
                            int teleportationX = Integer.parseInt(data.split(",")[2]);
                            int[] teleportation = {teleportationY, teleportationX};
                            this.tuiles[posY][posX] = new Teleporteur(teleportation);
                        }
                        case "monstre" -> {  // Permet d'introduire le.s monstre.s au bon endroit dans le niveau
                            data = ligne.replace("monstre:", "");
                            posX = Integer.parseInt(data.split(",")[0]);
                            posY = Integer.parseInt(data.split(",")[1]);
                            int[] position = {posY, posX};
                            int nbVies = Integer.parseInt(data.split(",")[2]);
                            int force = Integer.parseInt(data.split(",")[3]);
                            this.monstres.add(new Monstre(position, nbVies, force));
                        }
                    }
                }

                //place les tuiles
                if (ligne.toCharArray()[0] == '#') {
                    //regarde la ligne
                    //mets chaque caractere dans [][i] jusquau nombre de colonnes
                    for (int i = 0; i < tailleNiveau[1]; i++) {
                        switch (ligne.toCharArray()[i]) {
                            case '#':
                                if (this.tuiles[numeroLignePlateau][i] == null) {
                                    this.tuiles[numeroLignePlateau][i] = new Mur();
                                }
                                break;
                            case ' ':
                                if (this.tuiles[numeroLignePlateau][i] == null) {
                                    this.tuiles[numeroLignePlateau][i] = new Plancher();
                                }
                                break;

                        }
                    }
                    numeroLignePlateau++;
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

    /**
     * Cette m??thode fait d??placer les monstres dans le niveau en direction du h??ros
     * @param plateau plateau du niveau
     */
    public void bougerMonstres(Tuile[][] plateau) {
        for (int i = 0; i < monstres.size(); i++) {
            monstres.get(i).movement(plateau, heros );
        }


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
}
