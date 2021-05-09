package ca.qc.bdeb.sim202;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Niveau niveau = null;
        Heros heros = null; //stocke le heros du niveau precedent
        int numeroNiveau = 1; //numero du niveau
        boolean perdue = false;
        boolean gagnee = false;

        Messages.afficherIntro();

        do {
            if (niveau == null || niveau.getNumero() != numeroNiveau) {
                niveau = new Niveau(numeroNiveau, heros);
            }
            afficherEtat(niveau.getHeros());
            afficherPlateau(niveau);

            for (char commande : getCommandes()) {
                niveau.getHeros().action(commande, niveau.getTuiles());
                niveau.bougerMonstres(niveau.getTuiles());

                if (niveau.getHeros().getVie() < 1) { //check si adlez est encore vivant
                    perdue = true;
                }

                if (niveau.getHeros().getCristaux().size() == numeroNiveau) {
                    heros = niveau.getHeros();
                    if (numeroNiveau == 6){
                        gagnee = true;
                    }
                    numeroNiveau++;
                }
            }


        } while (!perdue && !gagnee);

        if (gagnee){
            Messages.afficherVictoire();
        } else {
            Messages.afficherDefaite();
        }
    }

    /**
     * Demande a l'utilisateur les commandes et les verifies
     * @return la liste des commandes entrees par l'utilisateur
     */
    public static char[] getCommandes() {
        boolean erreur = true;
        char[] listeCoups = null;
        do{ //get les mouvements de l'utilisateur
            try {
                Scanner input = new Scanner(System.in);  // Create a Scanner object
                System.out.print("Entrez vos coups: ");
                String coups = input.nextLine();
                erreur = false;
                if (coups.equals("")) {
                    System.out.println("SVP jouer un coup");
                    erreur = true;
                }
                if (!erreur) {
                    listeCoups = coups.toCharArray();
                    for (char coup: listeCoups) {
                        switch (coup) {
                            case 'w':
                            case 'a':
                            case 's':
                            case 'd':
                            case 'c':
                            case 'x':
                                break;
                            case 'q':
                                System.out.println("Sauvegarde de la partie!");
                                try {
                                    ObjectOutputStream ecrire =
                                            new ObjectOutputStream(new FileOutputStream("Zelda.dat"));
                                   // ecrire.writeObject(niveau);

                                    ecrire.close();
                                } catch (IOException e) {
                                    System.out.println("Erreur d'entrées-sorties");

                                //} catch (ClassNotFoundException e) {
                                    System.out.println("Erreur classe introuvable");

                                }
                                System.exit(0);
                                break;
                            default:
                                System.out.println( "'"+coup+"'"+" est un coup invalide");
                                erreur = true;
                        }

                    }
                }
            } catch (InputMismatchException e){
                System.out.println("Commandes invalides");
            }
        }while (erreur);
        
        return listeCoups;
    }

    /**
     * Affiche le niveau dans la console
     * @param niveau Niveau a afficher
     */
    public static void afficherPlateau(Niveau niveau) {
        Tuile[][] tuiles = niveau.getTuiles();
        int[][] positionMonstres = new int[niveau.getMonstres().size()][2];
        for (int i = 0; i < niveau.getMonstres().size(); i++) {
            positionMonstres[i] = niveau.getMonstres().get(i).getPosition();
        }
        for (int i = 0; i < tuiles.length; i++) {
            Tuile[] ligne = tuiles[i];
            for (int j = 0; j < ligne.length; j++) {
                Tuile tuile = ligne[j];
                //si c'est position d'un monstre mettre un @
                char symbole = tuile.getSymbole();

                for (int[] positionMonstre : positionMonstres)
                    if (positionMonstre[0] == i && positionMonstre[1] == j) {
                        symbole = '@';
                        break;
                    }
                if (niveau.getHeros().getPosition()[0] == i && niveau.getHeros().getPosition()[1] == j) symbole = '&';
                System.out.print(symbole);
            }

            System.out.print('\n');
        }
    }

    /**
     * affiche l'etat du heros
     * @param heros heros dont on veut afficher l'information
     */
    public static void afficherEtat(Heros heros){
        int vie = heros.getVie();
        int force = heros.getForce();
        int nbCristaux = heros.getCristaux().size();
        System.out.println("Vies: "+vie+" Force: " +force+" Cristaux: "+heros.getCristaux().size());
    }


}
