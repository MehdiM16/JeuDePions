package model;

/**
 * Cette classe permet d'instancier des K-uplets suivant ce que veut l'user
 * @version 1.0
 * @author les membres du projet
 */
public class Kuplet {
	private int score;
	private Case[] tableau;
	
	/**
	 * constructeur pour initialiser des K-uplets
	 * @param n le nombre de pions alignés pour gagner la partie
	 */
	public Kuplet (int n) {
		score = 0;
		tableau = new Case[n];
	}
}
