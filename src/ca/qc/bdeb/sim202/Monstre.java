package ca.qc.bdeb.sim202;

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
     * @param plateau plateau du niveau
     * @param heros héros du niveau
     */
    public void movement(Tuile[][] plateau, Heros heros) {
        int[] positionMonstre = super.getPosition();
        int[] nouvellePosition = {positionMonstre[0], positionMonstre[1]};
        int[] positionHeros = heros.getPosition();


        if (positionHeros[0] < positionMonstre[0]) {
            if (positionHeros[1] < positionMonstre[1]) {
                if (plateau[positionMonstre[0]--][positionMonstre[1]--].isMarchable()) {
                    nouvellePosition[0]--;
                    super.setPosition(nouvellePosition);
                    nouvellePosition[1]--;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                } else if (plateau[positionMonstre[0]][positionMonstre[1]--].isMarchable()) {
                    nouvellePosition[1]--;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                } else if (plateau[positionMonstre[0]--][positionMonstre[1]].isMarchable()) {
                    nouvellePosition[0]--;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                }
            } else if (positionHeros[1] == positionMonstre[1]) {
                if (plateau[positionMonstre[0]][positionMonstre[1]--].isMarchable()) {
                    nouvellePosition[0]--;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                }

            } else if (positionHeros[1] > positionMonstre[1]) {

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

        } else if (positionHeros[0] == positionMonstre[0]) {
            if (positionHeros[1] < positionMonstre[1]) {
                if (plateau[positionMonstre[0]][positionMonstre[1]--].isMarchable()) {
                    nouvellePosition[1]--;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                }
            } else if (positionHeros[1] == positionMonstre[1]) {
                //Ne bouge pas, si sur la même case
                interact(plateau, positionHeros, heros);
            } else if (positionHeros[1] > positionMonstre[1]) {
                if (plateau[positionMonstre[0]][positionMonstre[1]++].isMarchable()) {
                    nouvellePosition[1]++;
                    super.setPosition(nouvellePosition);
                    interact(plateau, positionHeros, heros);
                }
            }

        } else if (positionHeros[0] > positionMonstre[0]) {
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

            } else if (positionHeros[1] < positionMonstre[1]) {

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
    }

    @Override
    public int getForce() {
        return super.getForce();
    }

    @Override
    public void setVie(int vie) {
        super.setVie(vie);
    }


    @Override
    public void setPosition(int[] position) {
        super.setPosition(position);
    }

    @Override
    public int[] getPosition() {
        return super.getPosition();
    }

}
