package ca.qc.bdeb.sim202;
import java.io.*;
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
          /* if (lireBinaire() != null ) {
               Scanner input = new Scanner(System.in);
               System.out.println("Souhaitez reprendre à partir de la dernière sauvegarde? o pour oui/ n pour non");
               String sauvegarde = input.nextLine();
               try {
                   while (sauvegarde != "o" || sauvegarde != "n") {
                       System.out.println("Cette commande n'est pas valide, réessayez!");
                       System.out.println("Souhaitez reprendre à partir de la dernière sauvegarde? o pour oui / n pour non");
                       sauvegarde = input.nextLine();
                   }
                   if (sauvegarde.equals("o")) {
                       ecrireBinaire(niveau);
                   } else if (sauvegarde.equals("n")) {
                       niveau = new Niveau(numeroNiveau, heros);
                   }
               } catch (InputMismatchException e) {
                   System.out.println("Commande invalide");
               }

           } else */if (niveau == null || niveau.getNumero() != numeroNiveau) {
                niveau = new Niveau(numeroNiveau, heros);
            }
            afficherEtat(niveau.getHeros());
            afficherPlateau(niveau);

            for (char commande : getCommandes(niveau)) {
                niveau.getHeros().action(commande, niveau.getTuiles(), niveau.getMonstres());
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
    public static char[] getCommandes(Niveau niveau) {
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
                                System.out.println("Voulez-vous sauvegarder la partie? o pour oui/ n pour non");
                                String sauvegarde = input.nextLine();
                                try {
                                    while (!sauvegarde.equals("o") && !sauvegarde.equals("n")) {
                                        System.out.println("Cette commande n'est pas valide, réessayez!");
                                        System.out.println("Voulez-vous sauvegarder la partie? o pour oui/ n pour non");
                                        sauvegarde = input.nextLine();
                                    }
                                    if (sauvegarde.equals("o")) {
                                        ecrireBinaire(niveau);
                                        System.out.println("Votre niveau a été sauvegardé!");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Commande invalide");
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

    public static void ecrireBinaire(Niveau partie) {
        File file = new File("partie.sav");
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file, false);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(partie);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            System.out.println("Erreur");
            System.exit(1);
        }
        System.exit(0);
    }

    public static Niveau lireBinaire() {
        Niveau niveau = null;
        String nomfichier = "partie.sav";
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new FileInputStream(nomfichier));
            niveau = (Niveau) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Une erreur est survenue.");
            System.exit(0);
        }
        return niveau;
    }

}
