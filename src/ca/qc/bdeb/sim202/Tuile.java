package ca.qc.bdeb.sim202;

import java.io.Serializable;

public abstract class Tuile implements Serializable {
    boolean marchable;
    char symbole;

    public Tuile(boolean marchable, char symbole) { // Les tuiles ont un symbole et peuvent être marchables ou non
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
