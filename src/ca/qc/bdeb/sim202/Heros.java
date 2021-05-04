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
        //ajouter a cristaux
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
                break;
            case 'x':
                break;
        }
        /*
        for qui prend chaque char et qui fait une action dans un switch

        [
        w:déplace Adlez une case vers le haut
        a:déplace Adlez une case vers la gauche
        s:déplace Adlez une case vers le bas
        d: déplace Adlez une case vers la droite
        c: bouton d'action
            Une "action" a un effet sur tous les éléments proches de Adelez
            Ça inclut :
            - Lire une pancarte
            - Ouvrir un trésor
            - Utiliser un téléporteur
        x: attaque tous les monstres dans le voisinage de Adlez
            Adlez est une guerrière féroce : elle attaque dans un voisinage de 9
            cases, incluant haut/bas/gauche/droite + les 4 diagonales + la case
            sur laquelle Adlez se trouve (si jamais un monstre est sur la même
            case que Adlez)
        q: quitte le jeu
        ]
        */
    }
}