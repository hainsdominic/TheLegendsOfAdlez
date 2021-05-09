package ca.qc.bdeb.sim202;

public class Monstre extends Personnage {

    public Monstre(int[] position, int vie, int force) {
        super(position, vie, force);
    }

    // @Override ?
    public void deplacer() {
        /*
        Si Adlez est dans une des 9 cases avoisinantes (8 cases voisines + la case sur laquelle le monstrese trouve), attaquer Adlez
        Si Adlez est plus loin, avancer vers elle :
            – Regarder la coordonnée x du monstre et calculer le déplacement horizontal deplacementX
            à faire pour se rapprocher de Adlez
                ∗ Si Adlez est à droite, deplacementX = +1
                ∗ Si Adlez est à gauche, deplacementX = −1
            – Regarder la coordonnée y et calculer le déplacement vertical deplacementY similairement
            – Si la case (
        */

    }

    public void interact(Tuile[][] plateau, int[] position, Heros heros) {
        //faire un array de toutes les cases autour
        Tuile[] tuilesProche = new Tuile[9];
        tuilesProche[0] = plateau[position[0]+1][position[1]+1];
        tuilesProche[1] = plateau[position[0]-1][position[1]-1];
        tuilesProche[2] = plateau[position[0]+1][position[1]-1];
        tuilesProche[3] = plateau[position[0]-1][position[1]+1];
        tuilesProche[4] = plateau[position[0]][position[1]+1];
        tuilesProche[5] = plateau[position[0]][position[1]-1];
        tuilesProche[6] = plateau[position[0]+1][position[1]];
        tuilesProche[7] = plateau[position[0]-1][position[1]];
        tuilesProche[8] = plateau[position[0]][position[1]];

        for (Tuile tuiles : tuilesProche) {
            if (tuiles.getSymbole() == '&') {
                attaque(heros);
                if (heros.getVie() <= 0) {
                    System.out.println("Vous n'avez plus de vie! Vous avez perdu la partie!");
                    System.exit(0);
                }
            }

        }
    }

    private void attaque(Heros heros) {
        heros.setVie((heros.getVie()-getForce()));

    }

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

            }else if (positionHeros[0] > positionMonstre[0]){
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
