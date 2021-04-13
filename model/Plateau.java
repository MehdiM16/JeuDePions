/**
 * cette classe permet d'instancier des plateaux de jeu
 * @version 1.0
 * @author les membres du projet
 */
public class Plateau {
	private Case[][] tableau;
	private int longueur;
	private int largeur;

	/**
	 * permet de construire un plateau
	 * @param m la longueur du plateau
	 * @param n la largeur du plateau
	 */
	public Plateau (int m, int n) {
		tableau = new Case[m][n];
		longueur = m;
		largeur = n;
		for (int i=0; i<longueur; i++) {
			for (int j=0; j<largeur; j++) {
				tableau[i][j] = new Case();
			}
		}
	}
	
	/**
	 * getter 
	 * @return la grille de jeu
	 */
	public Case[][] getTableau () {
		return tableau;
	}
	
	/**
	 * @return true si toutes les cases ont le même score, false sinon
	 */
	public boolean sameScore () {
		int tmp = tableau[0][0].getScore();
		for (int i=0; i<longueur; i++) {
			for (int j=0; j<largeur; j++) {
				if (tableau[i][j].getScore() != tmp) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * @return les coordonnées de la case la mieux placée
	 */
	public XY getCaseMieuxPlacee () {
		int maxKuplets = 0;
		XY res = new XY (-1,-1,-1,-1);
		Case[][]tab = p.getTableau();
		for (int i=0; i<longueur; i++) {
			for (int j=0; j<largeur; j++) {
				if (tab[i][j].getNbrKuplets() > maxKuplets) {
					maxKuplets = tab[i][j].getNbrKuplets();
					res.x = i;
					res.y = j;
					res.score = tab[i][j].score;
					res.nbrKuplets = maxKuplets;
				}
			}
		}
		return res;
	}
		
		
	/**
	 * fournit une description textuelle d'un plateau 
	 * @return le tableau sous forme de String
	 */
	public String toString () {
		String s = "";
		for (int i=0; i<longueur; i++) {
			for (int j=0; j<largeur; j++) {
			s += tableau[i][j].toString();
			}
			s += "\n";
		}
		s+="\n";
		return s;
	}

}
			
