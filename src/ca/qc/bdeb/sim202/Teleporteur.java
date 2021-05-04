package ca.qc.bdeb.sim202;

public class Teleporteur extends Tuile{
    private int[] position;
    public Teleporteur(int[] position) {
        super(true, '*');
        this.position = position;
    }

    public int[] getPosition() {
        return position;
    }
}
