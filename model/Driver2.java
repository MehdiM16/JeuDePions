import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @version 2.0
 * @author l'équipe
 */
public class Driver2 {
	static int winner = 0;
	static Jeu jeu = null;
	static int lon, larg, pions;
	static int choix = 0;
	int maxX = -1;
	int maxY = -1;
	boolean endGame = false;
	
	/**
	 * permet de collecter les caracteristiques du jeu
	 */
	public static void caracteristiques_game (Scanner sc) {
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
	
	/**
	 * met à jour le champ aquiletour du champ jeu afin de voir qui commence
	 */
	public void tirageausort_aqlt (Random random) {
		int res = random.nextInt(2);
		jeu.aquiletour = res == 0 ? true : false;
	}	
	
	public void play (Scanner sc) {
		if (jeu.aquiletour) {
			int[]xy = jeu.prompt(sc);
			System.out.println("Vous avez jou� sur ("+xy[0]+","+" "+xy[1]+")");
			jeu.tourdejeu(xy[0], xy[1]);
		}
		else {
			ArrayList<XY> maxEvals = jeu.getMaxEvals();
			ArrayList<XY> bestEvals = jeu.getBestEvals(maxEvals); 
			int rand = r.nextInt(bestEvals.size());
		        XY choice = bestEvals.get(rand);
		        System.out.println("L'ordinateur a jou� sur ("+choice.x+","+" "+choice.y+")");
		        jeu.tourOrdinateur(choice.x, choice.y);
		}
		int highEval = jeu.getHighEval();
		if (highEval == maxX) {
			winner = 1;
			endGame = true;
		}
		else if (highEval == maxY) {
			winner = 0;
			endGame = true;
		}
		else if (!endGame && jeu.pasDeCaseVide()) {
			winner = 2;
			endGame = true;
		}
		System.out.println(jeu.p.toString());
	}
	
	public static void main (String[]args) {
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		caracteristiques_game();
		if (choix == 1) { jeu = new Jeu (lon, larg, pions); }
		else if (choix == 2) { jeu = new PuissanceK (lon, larg, pions); }
		maxscores_update();
		jeu.p.toString();
		tirageausort_aqlt(r);
		while (!endGame) {
			play(sc);
		}
		if (winner==0) { System.out.println("Echec! Vous avez perdu face à l'ordinateur."); }
		else if (winner==1) { System.out.println("Félicitations! Vous avez gagné"); }
		else System.out.println("Partie nulle!");
		sc.close();
	}
	
}
