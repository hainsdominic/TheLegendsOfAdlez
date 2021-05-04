package ca.qc.bdeb.sim202;

public class Pancarte extends Tuile{
    private final String message;
    public Pancarte(String message) {
        super(true, '!');
        this.message = message;
    }

    /**
     * Lis ce qui est ecris sur la pancarte
     */
    public void lire() {
        System.out.println("Est ecris sur la pancarte: ");
        System.out.println(message);
    }
}
