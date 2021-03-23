import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @version 2.0
 * @author l'équipe
 */
public class Driver {
	int winner = 0;
	Scanner sc;
	Jeu jeu = null;
	int lon, larg, pions;
	int choix = 0;
	int maxX = -1;
	int maxY = -1;
	
	/**
	 * permet de collecter les caracteristiques du jeu
	 */
	public void caracteristiques_game () {
		while (true) {
			try {
				System.out.println("Bonjour, vous allez jouer au Gomoku contre l'ordi. Quelle sera la longueur du plateau ?");
				lon = Integer.parseInt(sc.next());
				break;	
			} catch (Exception e) { System.out.println("Erreur! "+e.getMessage()); }
		}
		while (true) {
			try {
				System.out.println("Très bien, et sa largeur ?");
				larg = Integer.parseInt(sc.next());
				break;
			} catch (Exception e) { System.out.println("Erreur! "+e.getMessage()); }
		}
		while (true) {
			try {
				System.out.println("Combien de pions à aligner pour gagner ?");
				pions = Integer.parseInt(sc.next());
				break; 
			} catch (Exception e) { System.out.println("Erreur! "+e.getMessage()); }
		}
		while (choix<1 || choix>2) {
			System.out.println("Gomoku ou PuissanceK ? (1 pour Gomoku et 2 pour PuissanceK)");
			try {
				choix = Integer.parseInt(sc.next());
			} catch (Exception e) { System.out.println("Erreur! "+e.getMessage()); }
		}
	}
	
	/**
	 * MAJ du score maximal d'un Kuplet en fonction de 'pions'
	 */
	public void maxscores_update () {
		if (pions > 5) {
			maxX = pions*2; 
			maxY = pions*2 + 1; 
		} else {
			switch (pions) {
			case 1 : 
				maxX = 15;
				maxY = 35;
				break;
			case 2 : 
				maxX = 400;
				maxY = 800;
				break;
			case 3 : 
				maxX = 1800;
				maxY = 15000;
				break;
			case 4 : 
				maxX = 100000;
				maxY = 800000;
				break;
			case 5 : 
				maxX = 800001;
				maxY = 800002;
				break;
			}
		}
	}
	
	public static void main (String[]args) {
		sc = new Scanner(System.in);
		Random r = new Random();
		caracteristiques_game();
		if (choix == 1) { jeu = new Jeu (lon, larg, pions); }
		else if (choix == 2) { jeu = new PuissanceK (lon, larg, pions); }
		maxscores_update();
		sc.close();
	}
