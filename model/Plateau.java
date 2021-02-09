package model;

/**
 * cette classe permet d'instancier des plateaux de jeu
 * @version 1.0
 * @author les membres du projet
 */
public class Plateau {
	private Case[] tableau;
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
	}
	
	/**
	 * fournit une description textuelle d'un plateau 
	 */
	public void toString () {
		for (int i=0; i<longueur; i++) {
			for (int j=0; j<largeur; j++) {
				System.out.print(tableau[i][j].toString());
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
}
			
