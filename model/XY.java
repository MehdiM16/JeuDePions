/**
 * la classe XY qui représente les cordonnées x et y d'une case
 * @author les membres du projet
 * @version 1.0
 */

public class XY {
	public int x;
	public int y;
	public int score;
	public int nbrKuplets;
	
	/**
	 * constructeur
	 * @param x et y les coordonnees d'une case
	 * @param score de la case et nombre de Kuplets sur la case
	 */
	public XY(int x, int y, int score, int nbrKuplets) {
		this.x = x;
		this.y = y;
		this.score = score;
		this.nbrKuplets = nbrKuplets;
	}
	
	/**
	 * constructeur
	 * @param x et y les coordonnees d'une case
	 * @param score de la case
	 */
	 public XY(int x, int y, int score) {
		this.x = x;
		this.y = y;
		this.score = score;
	 }
}

