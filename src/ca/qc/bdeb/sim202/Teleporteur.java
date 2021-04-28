package ca.qc.bdeb.sim202;

public class Teleporteur extends Tuile{
    private String position;
    public Teleporteur(String position) {
        super(true, '*');
        this.position = position;
    }

    public void action() {/* teleporter adlez a un endroit */}
}
