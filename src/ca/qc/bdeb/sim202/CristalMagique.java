package ca.qc.bdeb.sim202;

public class CristalMagique extends Item {

    @Override
    public String getType() {
        return "CristalMagique";
    } // Retourne l'item cristal magique
    public String getInfo() {
        return "Vous avez trouvé un cristal magique, prochain niveau!"; //Indique ce que le héros trouve dans le coffre
    }

}
