package ca.qc.bdeb.sim202;

import java.io.Serializable;

public abstract class Personnage implements Serializable {
    private int vie;
    private int force;
    private int[] position;

    public Personnage(int[] position, int vie, int force) { // Un personnage a de la vie, de la force e une position
        this.vie = vie;
        this.force = force;
        this.position = position;
    }

    public int getVie() {
        return vie;
    }

    public int getForce() {
        return force;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] nouvellePosition) {
        this.position = nouvellePosition;
    }
}
