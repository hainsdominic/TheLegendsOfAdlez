package ca.qc.bdeb.sim202;

public abstract class Tuile {
    boolean marchable;
    char symbole;

    public Tuile(boolean marchable, char symbole) {
        this.marchable = marchable;
        this.symbole = symbole;
    }

    /**
     * @return si le heros peut marcher sur la tuile
     */
    public boolean isMarchable() {
        return marchable;
    }

    public char getSymbole() {
        return symbole;
    }

    public void setSymbole(char symbole) {
        this.symbole = symbole;
    }
}
