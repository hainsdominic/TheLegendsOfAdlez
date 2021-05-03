package ca.qc.bdeb.sim202;

import java.util.ArrayList;

public class Niveau {
    // numero du niveau
    int numero;
    // liste de tuiles
    Tuile[][] tuiles;
    // liste de monstres
    Monstre[] monstres;
    // Heros
    Heros heros;

    public Niveau(int numero, Tuile[][] tuiles, Monstre[] monstres, Heros heros) {
        this.numero = numero;
        this.tuiles = tuiles;
        this.monstres = monstres;
        this.heros = heros;
    }
}
