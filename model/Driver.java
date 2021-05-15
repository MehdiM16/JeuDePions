package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Driver {

	public static void main(String[] args) {
		int winner=0;	// un drapeau indiquant le gagnant 
						// (0 pour l'ordinateur, 1 pour le joueur, 2 pour partie nulle )
		Scanner sc = new Scanner(System.in);
		int lon, larg, pions;	// longueur du plateau, largeur, et nombre de pions align�s pour gagner
		Jeu jeu=null;		// d�clarer une instance de classe Jeu
							// l'utilisateur va d�cider dans ce qui suit, 
							// si �a sera un objet Jeu (Gomoku) ou un objet PuissanceK
		
		while (true) {	// boucler
			try {
				System.out.println("Bonjour, vous allez jouer au Gomoku contre l'ordi. Quelle sera la longueur du plateau ?");
				lon = Integer.parseInt(sc.next());
				break;	// s'il y a pas d'erreur de saisie alors conserver la longueur et arreter la boucle
			} catch (Exception e) {
				System.out.println("Erreur! "+e.getMessage());
			}
		}
		while (true) {	// boucler
			try {
				System.out.println("Très bien, et sa largeur ?");
				larg = Integer.parseInt(sc.next());
				break;	// s'il y a pas d'erreur de saisie alors conserver la largeur et arreter la boucle
			} catch (Exception e) {
				System.out.println("Erreur! "+e.getMessage());
			}
		}
		while (true) {	// boucler
			try {
				System.out.println("Combien de pions à aligner pour gagner ?");
				pions = Integer.parseInt(sc.next());
				break;	// s'il y a pas d'erreur de saisie alors conserver le nombre de pions et arreter la boucle
			} catch (Exception e) {
				System.out.println("Erreur! "+e.getMessage());
			}
		}
		
		// calculer le maximum d'�valuation n�cessaire pour que le joueur gagne
		// et le maximum d'�valuation n�cessaire pour que l'ordinateur gagne
		int maxX=-1, maxY=-1;
		if (pions > 5) {
			maxX = pions*2; 
			maxY = pions*2 + 1; 
		} else {
			switch(pions) {
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
		
		int choix=0;	// indique le choix de jeu (1 pour Gomoku et 2 pour PuissanceK)
		
		while (choix<1 || choix>2) {	// boucler tant que le choix saisi n'est pas valide
			System.out.println("Gomoku ou PuissanceK (1 pour Gomoku et 2 pour PuissanceK) ?");
			try {
				choix = Integer.parseInt(sc.next());
				
				if (choix==1) {		// si choix=1 on cr�e un objet Jeu (Gomoku)
					jeu = new Jeu (lon, larg, pions);
				}
				else if (choix==2) {	// si choix=2 on cr�e un objet PuissanceK			
					jeu = new PuissanceK(lon, larg, pions);
				}
			} catch (Exception e) {
				System.out.println("Erreur! "+e.getMessage());
			}
		}
		
		System.out.println(jeu.p.toString());	// affiche le plateau (il est vide � cette position)
		Random r = new Random();	// initialise un objet Random pour g�n�rer un nombre al�atoire
		int rand = r.nextInt(2);	// g�n�rer un nombre al�atoire entre 0 et 1 (0 et 1 inclus)
									// pour d�cider al�atoirement qui doit commencer le jeu
		jeu.aquiletour = rand == 0 ? true : false;	// si le nombre al�atoire = 0 alors 
													// aquiletour = true (le joueur commence)
													// sinon aquiletour = false (l'ordinateur commence)
		boolean endGame = false;	// variable boole�nne qui indique la fin du jeu ou non
									// initialis�e � false pour permettre la boucle
		
		while (!endGame) {			// boucler tant que endGame = false
			
			if (jeu.aquiletour) {	// si c'est le tour du joueur alors
				int xy[] = jeu.promptXY(sc);	// on fait l'appel � la m�thode promptXY qui va 
												// retourner un tableau de 2 valeurs 
												// (la ligne et la colonne de la case � jouer)
				
				System.out.println("Vous avez jou� sur ("+xy[0]+","+" "+xy[1]+")");
				jeu.tourdejeu(xy[0], xy[1]);	// appel � la m�thode tourdejeu qui change l'�tat de 
												// la case cible � 'X'
			}
			else {		// si c'est le tour de l'ordinateur alors : un peu d'intelligence artificielle
				ArrayList<XY> maxEvals = jeu.getMaxEvals();	// appel � la m�thode getMaxEvals qui 
						// renvoie un tableau de cases vides qui correspondent � l'�valuation maximale
				
				ArrayList<XY> bestEvals = jeu.getBestEvals(maxEvals); // appel � la m�thode getBestEvals qui
						// prends le tableau pr�c�dent comme param�tre puis �limine les cases qui font partie
						// des kuplets avec �valuation = 0 (kuplets qui contiennent au moins un 'X' et un 'O')
						// renvoie un nouveau tableau (plus optimal) de cases vides
				
				rand = r.nextInt(bestEvals.size());	// g�n�rer un nombre al�atoire entre 0 et le nombre d'�l�ments 
						// du tableau r�sultat de l'instruction pr�c�dente
						// pour choisir al�atoirement une case parmi l'ensemble des cases obtenues
				
		        XY choice = bestEvals.get(rand);	// choisir al�atoirement une case du tableau 'bestEvals'
		        System.out.println("L'ordinateur a jou� sur ("+choice.x+","+" "+choice.y+")");
				
		        jeu.tourOrdinateur(choice.x, choice.y);	// appel � la m�thode tourOrdinateur qui change  
						// l'�tat de la case cible � 'O'
			}
			int highEval = jeu.getHighEval();	// appel � la m�thode getHighEval qui renvoie l'�valuation
												// maximale de l'ensemble des cases du plteau
			
			if (highEval==maxX) {	// si cette �valuation = le maximum pour le joueur  
				winner = 1;				// le joueur donc a gagn� le jeu
				endGame = true;
			} else if (highEval==maxY) {	// sinon si cette �valuation = le maximum pour l'ordinateur alors 
				winner = 0;						// l'ordinateur a gagn� le jeu
				endGame = true;
			}
			if (!endGame && jeu.pasDeCaseVide()) {	// si ni l'un ni l'autre et y a pas de case
				winner = 2;							// vide dans le plateau, alors partie nulle
				endGame = true;
			}
			System.out.println(jeu.p.toString());	// afficher le plateau � jour apr�s chaque tour du jeu
		}
		if (winner==0)
			System.out.println("Echec! Vous avez perdu face � l'ordinateur.");
		else if (winner==1)
			System.out.println("F�licitation! Vous avez gagnez");
		else
			System.out.println("Partie nulle!");
		
		sc.close();

	}

}
