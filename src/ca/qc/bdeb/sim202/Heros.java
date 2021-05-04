package ca.qc.bdeb.sim202;

import java.util.ArrayList;

public class Heros extends Personnage {
    private ArrayList<CristalMagique> cristaux;

    public Heros(int[] position, int vie, int force) {
        super(position, vie, force);
        this.cristaux = new ArrayList<>();
    }

    @Override
    public int getVie() {
        return super.getVie();
    }

    @Override
    public int getForce() {
        return super.getForce();
    }

    @Override
    public void setVie(int vie) {
        super.setVie(vie);
    }

    @Override
    public void setForce(int force) {
        super.setForce(force);
    }

    @Override
    public void setPosition(int[] position) {
        super.setPosition(position);
    }

    @Override
    public int[] getPosition() {
        return super.getPosition();
    }



    public ArrayList<CristalMagique> getCristaux() {
        return cristaux;
    }

    public void ajouterCristal(CristalMagique cristal) {
        //ajouter a cristaux
    }

    public void action(char touche) {
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