import java.util.Scanner;
import java.util.ArrayList;

/**
 * cette classe constitue un deuxième point d'entrée du programme si l'on souhaite jouer à une variante du Gomoku
 * @author les membres du projet
 * @version 1.0
 */
public class PuissanceK extends Jeu {

	/**
	 * permet d'instancier une partie entre l'ordi et le joueur
	 * @param l1 et l2 les dimensions de la grille
	 * @param k le nombre de pions à aligner pour gagner
	 */
	public PuissanceK (int l1, int l2, int k) {
		super(l1,l2,k);
	}
	
	/**
	 * permet l'exploitation des coordonnees entrees par l'utilisateur
	 * @param scanner qui interagit sur le terminal
	 * @return int[] tableau des coordonnees souhaitees
	 */
	public int[] promptXY(Scanner sc) {
		int[] point = new int[2];
		int ligne = -1;
		int colonne = -1;
		while (ligne<0 || colonne<0 || colonne>=largeur) {
			System.out.println("Donnez le numéro de colonne :");
			try {
				colonne = Integer.parseInt(sc.next());
				if (colonne<0 || colonne>=largeur) {
					System.out.println("erreur, la colonne choisie n'existe pas dans le plateau");
				}
				else {
					point[1] = colonne;
					ligne =  dernCaseVide(point[1]);	// appel la méthode dernCaseVide
					if (ligne<0) {		// si ligne = -1 alors pas de case vide dans cette colonne
						System.out.println("Pas de case vide dans cette colonne");
					}
					else point[0] = ligne;
				}
			} catch (Exception e) {
				System.out.println("Erreur! "+e.getMessage());
			}
		}
		return point;
	}
	
	/**
	 * méthode qui prend le nombre de colonne comme paramètre et renvoie le numéro de ligne
	 * @param la colonne
	 * @return la ligne 
	 */
	public int dernCaseVide(int colonne) {
		Case[][] tmp = p.getTableau();
		for (int i=longueur-1; i>=0; i--) {		// on commence le parcours du bas vers le haut
			if (tmp[i][colonne].getEtat()==' ') {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * renvoie un tableau de cases vides (dernière case vide dans chaque colonne) 
	 * qui correspondent à l'évaluation maximale
	 */
	public ArrayList<XY> getMaxEvals() {
		ArrayList<XY> points = new ArrayList<XY>();
		Case[][] tmp = p.getTableau();
		int max=0;
		for (int i=0; i<longueur; i++) {
			for (int j=0; j<largeur; j++) {
				if (estDernierVide(i, j)) {
					max = tmp[i][j].evaluate();
					break;
				}
			}
		}
		for (int i=0; i<longueur; i++) {
			for (int j=0; j<largeur; j++) {
				if (estDernierVide(i, j)) {
					if (max<tmp[i][j].evaluate()) {
						max = tmp[i][j].evaluate();
					}
				}
			}
		}
		for (int i=0; i<longueur; i++) {
			for (int j=0; j<largeur; j++) {
				if (estDernierVide(i, j)) {
					if (max==tmp[i][j].evaluate()) {
						XY xy = new XY(i, j, max);
						points.add(xy);
					}
				}
			}
		}
		return points;
	}
	
	/**
	 * méthode qui test si la case avec les cordonnées données est la dernière vide ou non
	 * renvoie vrai si elle est la dernière vide, sinon renvoie faux
	 */
	public boolean estDernierVide(int ligne, int colonne) {
		Case[][] tmp = p.getTableau();
		if (tmp[ligne][colonne].getEtat()!=' ')
			return false;
		for (int i=ligne+1; i<longueur; i++) {
			if (tmp[i][colonne].getEtat()==' ') 
				return false;
		}
		return true;
	}


	
}
