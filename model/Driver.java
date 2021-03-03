import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * la classe principale qui contient la m?thode main
 * @author les membres du projet
 * @version 1.0
 */
public class Driver {

	public static void main(String[] args) {
		int winner=0;	// un drapeau indiquant le gagnant 
		Scanner sc = new Scanner(System.in);
		int lon, larg, pions;
		Jeu jeu=null;
		
		while (true) {	
			try {
				System.out.println("Bonjour, vous allez jouer au Gomoku contre l'ordi. Quelle sera la longueur du plateau ?");
				lon = Integer.parseInt(sc.next());
				break;	// s'il y a pas d'erreur de saisie alors conserver la longueur et arreter la boucle
			} catch (Exception e) {
				System.out.println("Erreur! "+e.getMessage());
			}
		}
		while (true) {	
			try {
				System.out.println("Très bien, et sa largeur ?");
				larg = Integer.parseInt(sc.next());
				break;	// s'il y a pas d'erreur de saisie alors conserver la largeur et arreter la boucle
			} catch (Exception e) {
				System.out.println("Erreur! "+e.getMessage());
			}
		}
		while (true) {	
			try {
				System.out.println("Combien de pions à aligner pour gagner ?");
				pions = Integer.parseInt(sc.next());
				break;	// s'il y a pas d'erreur de saisie alors conserver le nombre de pions et arreter la boucle
			} catch (Exception e) {
				System.out.println("Erreur! "+e.getMessage());
			}
		}
			
		int choix = 0; // indique le choix de jeu (1 pour Gomoku et 2 pour PuissanceK)
		while (choix<1 || choix>2) {	// boucler tant que le choix saisi n'est pas valide
			System.out.println("Gomoku ou PuissanceK (1 pour Gomoku et 2 pour PuissanceK) ?");
			try {
				choix = Integer.parseInt(sc.next());
				if (choix==1) {
					jeu = new Jeu (lon, larg, pions);
				}
				else if (choix==2) {			
					jeu = new PuissanceK(lon, larg, pions);
				}
			} catch (Exception e) {
				System.out.println("Erreur! "+e.getMessage());
			}
		}
		System.out.println(jeu.p.toString());	// affiche le plateau 
		Random r = new Random();
		int rand = r.nextInt(2);
		jeu.aquiletour = rand == 0 ? true : false;
		boolean endGame = false;	

		while (!endGame) {		
			if (jeu.aquiletour) {	// si c'est le tour du joueur alors
				int[] xy = jeu.promptXY(sc);	
				System.out.println("Vous avez joue sur ("+xy[0]+","+" "+xy[1]+")");
				jeu.tourdejeu(xy[0], xy[1]);
			}
			else {		// si c'est le tour de l'ordinateur alors : un peu d'intelligence artificielle
				ArrayList<XY> maxEvals = jeu.getMaxEvals();
				ArrayList<XY> bestEvals = jeu.getBestEvals(maxEvals); 
				rand = r.nextInt(bestEvals.size());
		        	XY choice = bestEvals.get(rand);	// choisir aleatoirement une case du tableau 'bestEvals'
		        	System.out.println("L'ordinateur a joue sur ("+choice.x+","+" "+choice.y+")");
		        	jeu.tourOrdinateur(choice.x, choice.y);	
			}
			int highEval = jeu.getHighEval();
			if (highEval==jeu.k*2) { // corriger les conditions et modifier la methode highEval dans Jeu
				winner = 1;
				endGame = true;
			} else if (highEval==jeu.k*2+1) {
				winner = 0;
				endGame = true;
			}
			if (!endGame && jeu.pasDeCaseVide()) {
				winner = 2;	
				endGame = true;
			}
			System.out.println(jeu.p.toString());	
		}
		if (winner==0) System.out.println("Echec! Vous avez perdu face a l'ordinateur.");
		else if (winner==1) System.out.println("Felicitation! Vous avez gagne");
		else System.out.println("Partie nulle!");
		sc.close();
	}

}

