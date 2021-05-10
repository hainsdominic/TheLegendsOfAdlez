package ca.qc.bdeb.sim202;

import java.io.Serializable;

public abstract class Item implements Serializable {
    public abstract String getType(); // Donne le type de l'item
    public abstract String getInfo();// Donne l'information sur l'item récolté
}
