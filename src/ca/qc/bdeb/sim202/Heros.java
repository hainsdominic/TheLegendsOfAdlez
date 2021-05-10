package ca.qc.bdeb.sim202;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Heros extends Personnage {
    private ArrayList<CristalMagique> cristaux = new ArrayList<>();

    public Heros(int[] position, int vie, int force) {
        super(position, vie, force);
    }

    public ArrayList<CristalMagique> getCristaux() { // Nombre de cristal magique porté par le héros
        return cristaux;
    }

    /**
     * Permet d'ajouter un cristal magique que le héros a sur lui
     * @param cristal
     */
    public void ajouterCristal(CristalMagique cristal) {
        cristaux.add(cristal);
    }

    /**
     * Effectue une action selon la touche choisie par le joueur
     * @param coup touche choisie
     * @param plateau plateau de jeu
     */
    public void action(char coup, Tuile[][] plateau, ArrayList<Monstre> monstres) {
        int[] position = super.getPosition();
        int[] nouvellePosition = {position[0], position[1]};
        switch (coup) {
            case 'w' -> { // Se déplace vers le haut
                nouvellePosition[0]--;
                if (plateau[nouvellePosition[0]][nouvellePosition[1]].isMarchable()) {
                    super.setPosition(nouvellePosition);
                }
            }
            case 'a' -> { // Se déplace vers la gauche
                nouvellePosition[1]--;
                if (plateau[nouvellePosition[0]][nouvellePosition[1]].isMarchable()) {
                    super.setPosition(nouvellePosition);
                }
            }
            case 's' -> { // Se déplace vers la droite
                nouvellePosition[0]++;
                if (plateau[nouvellePosition[0]][nouvellePosition[1]].isMarchable()) {
                    super.setPosition(nouvellePosition);
                }
            }
            case 'd' -> { // Se déplace vers le bas
                nouvellePosition[1]++;
                if (plateau[nouvellePosition[0]][nouvellePosition[1]].isMarchable()) {
                    super.setPosition(nouvellePosition);
                }
            }
            case 'c' -> interaction(plateau, position, monstres); // Interagis aves les tuiles
            case 'x' -> attaque(position, monstres); // Attaque les monstres
        }
    }

    /**
     * Interagis avec les objets autour ou sur le heros
     * @param plateau plateau de jeu
     * @param position position du heros
     */
    private void interaction(Tuile[][] plateau, int[] position, ArrayList<Monstre> monstres) {
        //faire un array de toutes les cases autour dans un périmètre de 1 case
        Tuile[] tuilesProche = new Tuile[9];
        tuilesProche[0] = plateau[position[0]+1][position[1]+1];
        tuilesProche[1] = plateau[position[0]-1][position[1]-1];
        tuilesProche[2] = plateau[position[0]+1][position[1]-1];
        tuilesProche[3] = plateau[position[0]-1][position[1]+1];
        tuilesProche[4] = plateau[position[0]][position[1]+1];
        tuilesProche[5] = plateau[position[0]][position[1]-1];
        tuilesProche[6] = plateau[position[0]+1][position[1]];
        tuilesProche[7] = plateau[position[0]-1][position[1]];
        tuilesProche[8] = plateau[position[0]][position[1]];

        //loop et faire leurs actions
        for (Tuile tuile : tuilesProche) {
            switch (tuile.getSymbole()) {
                case '$' -> {
                    // si c'est un tresor on get l'item
                    // et on utilise l'item
                    Tresor tresor = (Tresor) tuile;

                    switch (tresor.getItem().getType()) {
                        case "CristalMagique" -> {
                            System.out.println(tresor.getItem().getInfo());
                            this.ajouterCristal((CristalMagique) tresor.getItem());
                        }
                        case "PotionVie" -> {
                            System.out.println(tresor.getItem().getInfo());
                            this.setVie(6);
                        }
                        case "PotionForce" -> {
                            System.out.println("Le tresor contient une potion de force");
                            System.out.println(tresor.getItem().getInfo());
                            this.setForce(getForce() + 1);
                        }
                    }
                }
                case '!' -> {
                    // si c'est une pancarte on la lis
                    Pancarte pancarte = (Pancarte) tuile;
                    pancarte.lire();
                }
                case '*' -> {
                    // si c'est un tp on get position
                    Teleporteur teleporteur = (Teleporteur) tuile;
                    super.setPosition(teleporteur.getPosition());
                }
            }
        }

    }

    /**
     * Cette méthode regarde autour du héros pour savoir si son attaque touche un monstre ou non
     * @param positionHeros La position du héros sur le plateau
     * @param monstres Le nombre de monstres qu'il y a dans un niveau
     */
    public void attaque(int[] positionHeros, ArrayList<Monstre> monstres) {

        for (Monstre monstre : monstres) {
            int[] position = monstre.getPosition();
            if (position[0] == positionHeros[0] && position[1] == positionHeros[1]) {
                monstre.setVie(monstre.getVie() - getForce());
                break;
            }
            if (position[0] == positionHeros[0] - 1 && position[1] == positionHeros[1]) {
                monstre.setVie(monstre.getVie() - getForce());
                break;
            }
            if (position[0] == positionHeros[0] + 1 && position[1] == positionHeros[1]) {
                monstre.setVie(monstre.getVie() - getForce());
                break;
            }
            if (position[0] == positionHeros[0] && position[1] == positionHeros[1] - 1) {
                monstre.setVie(monstre.getVie() - getForce());
                break;
            }
            if (position[0] == positionHeros[0] && position[1] == positionHeros[1] + 1) {
                monstre.setVie(monstre.getVie() - getForce());
                break;
            }
            if (position[0] == positionHeros[0] + 1 && position[1] == positionHeros[1] + 1) {
                monstre.setVie(monstre.getVie() - getForce());
                break;
            }
            if (position[0] == positionHeros[0] - 1 && position[1] == positionHeros[1] - 1) {
                monstre.setVie(monstre.getVie() - getForce());
                break;
            }
            if (position[0] == positionHeros[0] - 1 && position[1] == positionHeros[1] + 1) {
                monstre.setVie(monstre.getVie() - getForce());
                break;
            }
            if (position[0] == positionHeros[0] + 1 && position[1] == positionHeros[1] - 1) {
                monstre.setVie(monstre.getVie() - getForce());
                break;
            }
        }
        // Raye le monstre de la carte lorsque sa vie = 0
        if (!(monstres.size() == 0)) {
            for (int i = 0; i < monstres.size(); i++) {
                Monstre monstre = monstres.get(i);
                if (monstre.getVie() == 0) {
                    monstres.remove(i);
                }
            }
        }

    }

}