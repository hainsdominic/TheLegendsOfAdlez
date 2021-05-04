package ca.qc.bdeb.sim202;

import java.util.ArrayList;

public class Heros extends Personnage {
    private ArrayList<CristalMagique> cristaux;

    public Heros(int[] position, int vie, int force) {
        super(position, vie, force);
        this.cristaux = new ArrayList<>();
    }

    public ArrayList<CristalMagique> getCristaux() {
        return cristaux;
    }

    public void ajouterCristal(CristalMagique cristal) {
        cristaux.add(cristal);
    }

    public void action(char coup, Tuile[][] plateau) {
        int[] position = super.getPosition();
        int[] nouvellePosition = {position[0], position[1]};
        switch (coup) {
            case 'w':
                nouvellePosition[0]--;
                if (plateau[nouvellePosition[0]][nouvellePosition[1]].isMarchable()) {
                    super.setPosition(nouvellePosition);
                }
                break;
            case 'a':
                nouvellePosition[1]--;
                if (plateau[nouvellePosition[0]][nouvellePosition[1]].isMarchable()) {
                    super.setPosition(nouvellePosition);
                }
                break;
            case 's':
                nouvellePosition[0]++;
                if (plateau[nouvellePosition[0]][nouvellePosition[1]].isMarchable()) {
                    super.setPosition(nouvellePosition);
                }
                break;
            case 'd':
                nouvellePosition[1]++;
                if (plateau[nouvellePosition[0]][nouvellePosition[1]].isMarchable()) {
                    super.setPosition(nouvellePosition);
                }
                break;
            case 'c':
                action(plateau, position);
                break;
            case 'x':
                break;
        }
    }

    private void action(Tuile[][] plateau, int[] position) {
        //faire un array de toutes les cases autour
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
                            System.out.println("Le tresor contient un cristal magique, prochain niveau!");
                            this.ajouterCristal((CristalMagique) tresor.getItem());
                        }
                        case "PotionVie" -> {
                            System.out.println("Le tresor contient une potion de vie");
                            this.setVie(6);
                        }
                        case "PotionForce" -> {
                            System.out.println("Le tresor contient une potion de force");
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
}