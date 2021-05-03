package ca.qc.bdeb.sim202;

public abstract class Personnage {
    int vie;
    int force;
    int[] position;

    public Personnage(int[] position, int vie, int force) {
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

    public void setPosition(int[] position) {
        this.position = position;
    }
}
