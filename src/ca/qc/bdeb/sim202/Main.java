package ca.qc.bdeb.sim202;

public class Main {

    public static void main(String[] args) {
        Niveau niveau = new Niveau(4);
        afficherTuiles(niveau.getTuiles());

    }

    public static void afficherTuiles(Tuile[][] tuiles) {
        for (Tuile[] tuile : tuiles) {
            for (Tuile value : tuile) {
                System.out.print(value.getSymbole());
            }

            System.out.print('\n');
        }
    }


}
