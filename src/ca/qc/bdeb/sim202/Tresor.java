package ca.qc.bdeb.sim202;

public class Tresor extends Tuile {
    Item item;
    boolean isOpen = false;

    public Tresor(Item item) {
        super(false, '$');
        this.item = item;
        isOpen = false;
    }

    public Item getItem() {
        if (isOpen = true) {
            item = null;
        }
        if (isOpen = false) {
          item = item;
          isOpen = true;
        }

        return item;
    }
}
