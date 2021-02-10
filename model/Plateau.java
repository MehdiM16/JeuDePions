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

	public static void main (String[]args) {
		Plateau p = new Plateau(10,10);
		System.out.println(p.toString());
	}
}
			
