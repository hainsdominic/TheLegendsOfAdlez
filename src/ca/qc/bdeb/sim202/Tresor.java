package ca.qc.bdeb.sim202;

public class Tresor extends Tuile {
    Item item;

    public Tresor(Item item) {
        super(false, '$');
        this.item = item;
    }

    public void action() {
        /* Lorsqu’on ouvre un trésor, l’effet de l’item contenu dedans s’applique tout de suite sur
        Adlez

        mettre le symbole a _
        */
    }
}
