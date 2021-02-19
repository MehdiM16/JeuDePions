import java.util.Scanner;

/**
 * cette classe constitue un deuxième point d'entrée du programme si l'on souhaite jouer à une variante du Gomoku
 *
 * @author les membres du projet
 * @version 1.0
 */
public class PuissanceK extends Jeu {

    /**
     * permet d'instancier une partie entre l'ordi et le joueur
     *
     * @param l1 et l2 les dimensions de la grille
     * @param k  le nombre de pions à aligner pour gagner
     */
    public PuissanceK(int l1, int l2, int k) {
        super(l1, l2, k);
    }

    public void partieFini(){
    int nbrcoloneremplis=0;
    	for(int j=0;j<t[0].lenth;j++){
    		if(t[0][j].setEtat()!= null){
    		nbrcoloneremplis++;
    		}
    	}
    if(nbrcoloneremplis==t[0].lenth){
    System.out.println(PartieFini);
    }
  }
    boolean coloneNonRemplis(col) {
        if (tableau[0][col] != null) {
            return true;
        }
        return false;
    }

    void MethodeFinalGravite() {
        int nbrReccursion = 1;
        int j = col;
        for (int i = 0; i < t.length; i++) {
            if (t[i][j].setEtat() != null) {
                for (int y = i; y < t.length; y++) {
                    if (t[y][j].setEtat() == null) {
                        nbrReccursion += y;
                    }
                }

            }
        }
        for (int c = 0; c < nbrReccursion; c++) {
            annexGravite(col);
        }
        //System.out.println(nbrReccursion);
    }


    void annexGravite(int col) {

        int j = col;
        //ici on parcour la colonne col
        for (int i = 0; i < tableau.length; i++) {

            if ((tableau[i][j].setEtat() != null) && (i < tableau.length - 1)) {


                if (tableau[i + 1][j].setEtat() == null) {

                    tableau[i + 1][j].setEtat() = tableau[i][j].setEtat();

                    tableau[i][j].setEtat() = null;
                }

            }

        }
    }

    /**
     * permet d'exécuter un tour de la part du joueur
     *
     * @param col la collone de la case où il faut jouer
     */
    public void(int col) {
        if (aquiletour && coloneNonRemplis(col) && col < largeur && col > 0) {
            tableau[0][col].setEtat()= 'X';
            aquiletour = false;		
			
			else{
                System.out.println("impossible de mettre un pion dans cette collonne");
                aquiletour = true;
                partieFini();
            }

        }
    }

    }
