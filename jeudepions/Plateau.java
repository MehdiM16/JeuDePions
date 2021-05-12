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
		for (int i=0; i<longueur; i++) {
			for (int j=0; j<largeur; j++) {
				if (tableau[i][j].getNbrKuplets() > maxKuplets) {
					maxKuplets = tableau[i][j].getNbrKuplets();
					res.x = i;
					res.y = j;
					res.score = tableau[i][j].getScore();
					res.nbrKuplets = maxKuplets;
				}
			}
		}
		return res;
	}
	
	/**
	 * calcule le nombre de Kuplets par case et met à jour l'attribut nbrKuplets 
	 */
	public void initNbKuplets(int k) {
		for(int i = 0; i < longueur; i++) {
			for(int j = 0; j < largeur; j++) {
				int tot = 0;
				if(i - k + 1 >= 0 && i + k - 1 < longueur) {//on compte les kuplet en longueur
					tot += k;
				} else if(i - k + 1 < 0) {
					tot += i+1;
				} else if(i + k - 1 >= longueur) {
					tot += longueur-i;
				}
				if(j - k + 1 >= 0 && j + k - 1 < largeur) {//on compte les kuplet en largeur
					tot += k;
				} else if(j - k + 1 < 0) {
					tot += j+1;
				} else if(j + k - 1 >= largeur) {
					tot += largeur-j;
				}
				//on ne compte pas les kuplet en diagonales 
				//car les cases possedant le plus de kuplets en longueur et largeur en possederont forcement plus en rajoutant les kuplet en diagonales 
				tableau[i][j].setNbrKuplets(tot);
			}
		}
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
			
