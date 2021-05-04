package ca.qc.bdeb.sim202;

public class Teleporteur extends Tuile{
    private int[] position;
    public Teleporteur(int[] position) {
        super(true, '*');
        this.position = position;
    }

    /**
     * @return la position finale du heros apres teleportation
     */
    public int[] getPosition() {
        return position;
    }
}
