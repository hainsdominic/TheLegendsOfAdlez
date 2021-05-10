package ca.qc.bdeb.sim202;

import java.io.Serializable;

public abstract class Item implements Serializable {
    public abstract String getType();
    public abstract String getInfo();
}
