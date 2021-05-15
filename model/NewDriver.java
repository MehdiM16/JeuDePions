package model;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

/**
 * cette classe constitue la porte d'entrée dans notre programme 
 * sa particularité : une méthode main trois fois moins longue
 * @version 1.1
 * @author les membres du projet 
 */
public class NewDriver {
	
	/** 
	 * permet d'interagir avec l'user pour obtenir les dimensions du plateau
	 * @param sc objet de type scanner 
	 * @param a une variable qui va se substituer à 'choix' (voir + bas)
	 * @return tab qui va contenir la longueur, la largeur, le nbre de pions et le choix Gomoku vs PuissanceK
	 */
	public static int[] caracteristiques (Scanner sc, int a) {
		int [] tab = new int [4];
		while (true) {	// boucler
			try {
				System.out.println("Bonjour, vous allez jouer au Gomoku contre l'ordi. Quelle sera la longueur du plateau ?");
				tab[0] = Integer.parseInt(sc.next());
				break;	// s'il y a pas d'erreur de saisie alors conserver la longueur et arreter la boucle
			} catch (Exception e) {
				System.out.println("Erreur! "+e.getMessage());
			}
		}
		while (true) {	// boucler
			try {
				System.out.println("Très bien, et sa largeur ?");
				tab[1] = Integer.parseInt(sc.next());
				break;	// s'il y a pas d'erreur de saisie alors conserver la largeur et arreter la boucle
			} catch (Exception e) {
				System.out.println("Erreur! "+e.getMessage());
			}
		}
		while (true) {	// boucler
			try {
				System.out.println("Combien de pions à aligner pour gagner ?");
				tab[2] = Integer.parseInt(sc.next());
				break;	// s'il y a pas d'erreur de saisie alors conserver le nombre de pions et arreter la boucle
			} catch (Exception e) {
				System.out.println("Erreur! "+e.getMessage());
			}
		}
		while (a<1 || a>2) {	// boucler tant que le choix saisi n'est pas valide
			System.out.println("Gomoku ou PuissanceK (1 pour Gomoku et 2 pour PuissanceK) ?");
			try {
				tab[3] = Integer.parseInt(sc.next());
				a = tab[3];
			} catch (Exception e) {
				System.out.println("Erreur! "+e.getMessage());
			}
		}
		return tab;
	}
	
	/**
	 * tire au sort un boolean et le retourne pour voir qui commence la partie
	 * @return true si l'user commence, false si l'ordi commence
	 */
	public static boolean tirage () {
		Random r = new Random();
		int res = r.nextInt(2);
		if (res == 1) return true;
		return false;
	}
	
	/**
	 * renvoie les scores maximaux des Kuplets de la partie
	 * @param pions le nombre de pions à aligner - renseigné par l'user
	 * @return tab contenant maxX et maxY
	 */
	public static int[] MAJscores (int pions) {
		int[]tab = new int [2];
		if (pions > 5) {
			tab[0] = pions*2; 
			tab[1] = pions*2 + 1; 
		} else {
			switch(pions) {
			case 1 : 
				tab[0] = 15;
				tab[1] = 35;
				break;
			case 2 : 
				tab[0] = 400;
				tab[1] = 800;
				break;
			case 3 : 
				tab[0] = 1800;
				tab[1] = 15000;
				break;
			case 4 : 
				tab[0] = 100000;
				tab[1] = 800000;
				break;
			case 5 : 
				tab[0] = 800001;
				tab[1] = 800002;
				break;
			}
		}
		return tab;
	}
	
	/**
	 * affiche un message pour la fin de partie en fonction du vainqueur
	 * @param n entier qui se substitue à 'winner'
	 */
	public static void messageVainqueur (int n) {
		if (n==0) System.out.println("Echec! Vous avez perdu face à l'ordinateur.");
		else if (n==1) System.out.println("Félicitation! Vous avez gagné");
		else System.out.println("Partie nulle!");
	}
	
	/**
	 * la méthode main
	 */	
	public static void main (String[]args) {
		int choix = 0;
		int winner = 0;
		int lon, larg, pions;
		Jeu jeu = null;
		int maxX = -1;
		int maxY = -1;
		Scanner sc = new Scanner (System.in);
		Random r = new Random();
		boolean endGame = false;
		int[]t1 = caracteristiques(sc, choix);
		lon = t1[0];
		larg = t1[1];
		pions = t1[2];
		choix = t1[3];
		if (choix == 1) { jeu = new Jeu (lon, larg, pions); }
		else if (choix == 2) { jeu = new PuissanceK (lon, larg, pions); }
		jeu.aquiletour = tirage();
		int[]t2 = MAJscores(pions);
		maxX = t2[0];
		maxY = t2[1];
		System.out.println(jeu.p.toString());
		boolean start = true;
		while (!endGame) {
			if (jeu.aquiletour) {
				int xy[] = jeu.promptXY(sc);
				System.out.println("Vous avez joué sur ("+xy[0]+","+" "+xy[1]+")");
				jeu.tourdejeu(xy[0], xy[1]);	
			}
			else {	
				if (jeu.p.sameScore() || start) {
					jeu.p.initNbKuplets(pions);
				    XY res = jeu.p.getCaseMieuxPlacee();
				    jeu.tourOrdinateur(res.x, res.y);
				    start = false;
				}
				else {
				    ArrayList<XY> maxEvals = jeu.getMaxEvals();
				    ArrayList<XY> bestEvals = jeu.getBestEvals(maxEvals); 
				    int rand = r.nextInt(bestEvals.size());
				    XY choice = bestEvals.get(rand);	
				    System.out.println("L'ordinateur a joué sur ("+choice.x+","+" "+choice.y+")");
				    jeu.tourOrdinateur(choice.x, choice.y);
				}
			}
			int highEval = jeu.getHighEval();
			if (highEval==maxX) {
				winner = 1;
				endGame = true;
			} else if (highEval==maxY) {
				winner = 0;	
				endGame = true;
			}
			if (!endGame && jeu.pasDeCaseVide()) {
				winner = 2;			
				endGame = true;
			}
			System.out.println(jeu.p.toString());	
		}
		messageVainqueur(winner);
		sc.close();
	}
}
		
