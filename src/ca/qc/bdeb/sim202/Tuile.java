package ca.qc.bdeb.sim202;

public abstract class Tuile {
    boolean marchable;
    char symbole;

    public Tuile(boolean marchable, char symbole) {
        this.marchable = marchable;
        this.symbole = symbole;
    }

    public boolean isMarchable() {
        return marchable;
    }

    public abstract void action();

    public char getSymbole() {
        return symbole;
    }

}
