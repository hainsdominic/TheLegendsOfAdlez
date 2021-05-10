package ca.qc.bdeb.sim202;

public class Tresor extends Tuile {
    Item item;

    public Tresor(Item item) {
        super(false, '$');
        this.item = item;
    }

    public Item getItem() {
        super.setSymbole('_');// Change le symbole du trésor l'orsqu'il est ouvert
        return item;
    }
}
