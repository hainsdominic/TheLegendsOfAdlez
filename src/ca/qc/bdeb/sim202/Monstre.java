package ca.qc.bdeb.sim202;

public class Monstre extends Personnage {

    public Monstre(int[] position, int vie, int force) {
        super(position, vie, force);
    }

    // @Override ?
    public void deplacer() {
        /*
        Si Adlez est dans une des 9 cases avoisinantes (8 cases voisines + la case sur laquelle le monstrese trouve), attaquer Adlez
        Si Adlez est plus loin, avancer vers elle :
            – Regarder la coordonnée x du monstre et calculer le déplacement horizontal deplacementX
            à faire pour se rapprocher de Adlez
                ∗ Si Adlez est à droite, deplacementX = +1
                ∗ Si Adlez est à gauche, deplacementX = −1
            – Regarder la coordonnée y et calculer le déplacement vertical deplacementY similairement
            – Si la case (
        */
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
    public void setPosition(int[] position) {
        super.setPosition(position);
    }

    @Override
    public int[] getPosition() {
        return super.getPosition();
    }

}
