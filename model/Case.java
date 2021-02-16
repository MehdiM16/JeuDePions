import java.util.ArrayList;

/**
 * Cette classe permet d'instancier des cases
 * @version 1.0
 * @author les membres du groupe
 */
public class Case {
	private char etat;
	private int score;
	private ArrayList Kuplets; // liste des K-uplets concernés par la case

	/**
	 * constructeur pour initialiser des Cases
	 */
	public Case () {
		etat = ' ';
		score = 0;
		Kuplets = new ArrayList();
	}
	
	/**
	 * setter 
	 * @param nouvel etat de la case
	 */
	public void setEtat (char c) {
		etat = c;
	}
	
	/**
	 * getter
	 * @return l'etat de la case
	 */
	public char getEtat () {
		return etat;
	}

	/**
	 * description textuelle d'une case
	 * @return une chaîne de caractère qui va apparaître sur le terminal
	 */
	public String toString () {
		return "[" + etat + "] ";
	}
}

