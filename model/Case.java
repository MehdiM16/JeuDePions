import java.util.ArrayList;
package model;

/**
 * Cette classe permet d'instancier des cases
 * @version 1.0
 * @author les membres du groupe
 */
public class Case {
	private char etat;
	private int score;
	private ArrayList Kuplets;

	/**
	 * constructeur pour initialiser des Cases
	 */
	public Case () {
		etat = ' ';
		score = 0;
		Kuplets = new ArrayList();
	}

	/**
	 * description textuelle d'une case
	 * @return une chaîne de caractère qui va apparaître sur le terminal
	 */
	public String toString () {
		return '[' + etat + ']' + ' ';
	}
}

