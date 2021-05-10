package ca.qc.bdeb.sim202;

import java.io.Serializable;

public class Monstre extends Personnage {

    public Monstre(int[] position, int vie, int force) {
        super(position, vie, force);
    }

    // @Override ?
    public void deplacer() {


    }

    /**
     * Cette méthode appartenant à la classe Monstre vérifie les alentours dans un périmètre de 1
     *
     * @param plateau  plateau du niveau
     * @param position position du héros
     * @param heros    héros du niveau
     */
    public void interact(Tuile[][] plateau, int[] position, Heros heros) {
        int[] herosPosition = heros.getPosition();
        int[] monstrePosition = getPosition();

        if (((herosPosition[0] - monstrePosition[0]) <= 1 && 1 >= (monstrePosition[0] - herosPosition[0]))
                && ((herosPosition[1] - monstrePosition[1]) <= 1 && 1 >= (monstrePosition[1] - herosPosition[1]))) {
            attaque(heros);
        }
    }

    /**
     * Attaque des monstres sur le héros
     * @param heros héros du niveau
     */
    private void attaque(Heros heros) {
        heros.setVie((heros.getVie() - getForce()));

    }

    /**
     * Cette méthode appartenant à la classe Monstre fait avancer le monstre en direction du héros,
     * soit en diagonale, soit verticalement, soit horizontalement et le monstre peut interagir avec le héros
     * si il est suffisament près de lui.
     * 0 = axe des y
     * 1 = axe des x
     * @param plateau plateau du niveau
     * @param heros héros du niveau
     */
    public void movement(Tuile[][] plateau, Heros heros) {
        int[] positionMonstre = super.getPosition();
        int[] nouvellePosition = {positionMonstre[0], positionMonstre[1]};
        int[] positionHeros = heros.getPosition();

        // Si la position du héros est plsu petite en y que celle du monstre
        if (positionHeros[0] < positionMonstre[0]) {
            if (positionHeros[1] < positionMonstre[1]) { // Si la position du héros est plus petite que celle
                // du monstre en x,
                if (plateau[positionMonstre[0]--][positionMonstre[1]--].isMarchable()) { // Si la case en diagonale est
                    // disponible pour se rapprocher le plus près du héros
                    nouvellePosition[0]--;
                    super.setPosition(nouvellePosition);
                    nouvellePosition[1]--;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                } else if (plateau[positionMonstre[0]][positionMonstre[1]--].isMarchable()) {// Si la case à gauche
                    // est disponible pour se rapprocher le plus près du héros
                    nouvellePosition[1]--;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                } else if (plateau[positionMonstre[0]--][positionMonstre[1]].isMarchable()) {// Si la case en haut est
                    // disponible pour se rapprocher le plus près du héros
                    nouvellePosition[0]--;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                }
            } else if (positionHeros[1] == positionMonstre[1]) {// Si la position du monstre et du héros est égale en x
                if (plateau[positionMonstre[0]][positionMonstre[1]--].isMarchable()) {// Si la case en haut est
                    // disponible pour se rapprocher le plus près du héros
                    nouvellePosition[0]--;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                }

            } else { //Si la postion en x du héros est plus grande que celle du monstre et déplacement du monstre vers
                // la case qui se rapproche le plus du héros

                if (plateau[positionMonstre[0]--][positionMonstre[1]++].isMarchable()) {
                    nouvellePosition[0]--;
                    super.setPosition(nouvellePosition);
                    nouvellePosition[1]++;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                } else if (plateau[positionMonstre[0]][positionMonstre[1]++].isMarchable()) {
                    nouvellePosition[1]++;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                } else if (plateau[positionMonstre[0]--][positionMonstre[1]].isMarchable()) {
                    nouvellePosition[0]--;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                }
            }

        } else if (positionHeros[0] == positionMonstre[0]) { // Quand la position du mosntre et du héros
            // sont pareils en y
            if (positionHeros[1] < positionMonstre[1]) {
                if (plateau[positionMonstre[0]][positionMonstre[1]--].isMarchable()) {
                    nouvellePosition[1]--;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                }
            } else if (positionHeros[1] == positionMonstre[1]) {
                //Ne bouge pas, si sur la même case
                interact(plateau, positionHeros, heros);
            } else {
                if (plateau[positionMonstre[0]][positionMonstre[1]++].isMarchable()) {
                    nouvellePosition[1]++;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                }
            }

        } else { // Quand la position du héros est plus grande en x que celle du monstre et déplacement du
            // monstre vers la case qui se rapproche le plus du héros
            if (positionHeros[1] > positionMonstre[1]) {
                if (plateau[positionMonstre[0]++][positionMonstre[1]++].isMarchable()) {
                    nouvellePosition[0]++;
                    super.setPosition(nouvellePosition);
                    nouvellePosition[1]++;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                } else if (plateau[positionMonstre[0]][positionMonstre[1]++].isMarchable()) {
                    nouvellePosition[1]++;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                } else if (plateau[positionMonstre[0]++][positionMonstre[1]].isMarchable()) {
                    nouvellePosition[0]++;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                }
            } else if (positionHeros[1] == positionMonstre[1]) {
                if (plateau[positionMonstre[0]--][positionMonstre[1]].isMarchable()) {
                    nouvellePosition[0]++;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                }

            } else {// Déplacement du monstre vers la case qui se rapproche le plus du héros

                if (plateau[positionMonstre[0]++][positionMonstre[1]--].isMarchable()) {
                    nouvellePosition[0]++;
                    super.setPosition(nouvellePosition);
                    nouvellePosition[1]--;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                } else if (plateau[positionMonstre[0]][positionMonstre[1]--].isMarchable()) {
                    nouvellePosition[1]--;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                } else if (plateau[positionMonstre[0]++][positionMonstre[1]].isMarchable()) {
                    nouvellePosition[0]++;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                }
            }
        }
    }

    @Override
    public int getVie() {
        return super.getVie();
    } // Retourne la vie du monstre

    @Override
    public int getForce() {
        return super.getForce();
    } //Retourne la force du monstre

    @Override
    public void setVie(int vie) {
        super.setVie(vie);
    } // Set la vie du monstre


    @Override
    public void setPosition(int[] position) {
        super.setPosition(position);
    } // Set la position du monstre

    @Override
    public int[] getPosition() {
        return super.getPosition();
    } // Retourne la position du monstre

}
