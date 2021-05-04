package ca.qc.bdeb.sim202;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Niveau niveau = new Niveau(6);
        afficherPlateau(niveau);

    }

    /**
     * Affiche le niveau dans la console
     * @param niveau Niveau a afficher
     */
    public static void afficherPlateau(Niveau niveau) {
        Tuile[][] tuiles = niveau.getTuiles();
        int[][] positionMonstres = new int[niveau.getMonstres().size()][2];
        for (int i = 0; i < niveau.getMonstres().size(); i++) {
            positionMonstres[i] = niveau.getMonstres().get(i).getPosition();
        }
        for (int i = 0; i < tuiles.length; i++) {
            Tuile[] ligne = tuiles[i];
            for (int j = 0; j < ligne.length; j++) {
                Tuile tuile = ligne[j];
                //si c'est position d'un monstre mettre un @
                char symbole = tuile.getSymbole();

                for (int[] positionMonstre : positionMonstres)
                    if (positionMonstre[0] == i && positionMonstre[1] == j) {
                        symbole = '@';
                        break;
                    }
                if (niveau.getHeros().getPosition()[0] == i && niveau.getHeros().getPosition()[1] == j) symbole = '&';
                System.out.print(symbole);
            }

            System.out.print('\n');
        }
    }


}
