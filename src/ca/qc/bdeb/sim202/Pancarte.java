package ca.qc.bdeb.sim202;

public class Pancarte extends Tuile{
    private String message;
    public Pancarte(String message) {
        super(true, '!');
        this.message = message;
    }

    public void action() {/* affiche message */}
}
