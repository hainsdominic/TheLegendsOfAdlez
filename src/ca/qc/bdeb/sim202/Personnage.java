package ca.qc.bdeb.sim202;

public abstract class Personnage {
    int vie;
    int force;

    public Personnage(int vie, int force) {
        this.vie = vie;
        this.force = force;
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
}
