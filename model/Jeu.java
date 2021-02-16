import java.util.Scanner;

/**
 * cette classe constitue le point d'entrée du programme
 * @author les membres du projet
 * @version 1.0
 */
public class Jeu {
	Plateau p;
	int longueur;
	int largeur;
	int k;
	boolean aquiletour; // true si c'est au joueur de jouer
	
	/**
	 * permet d'instancier une partie entre un joueur et l'ordinateur
	 * @param l1 et l2 les dimensions du plateau
	 * @param k le nombre de pions à aligner pour gagner
	 */
	public Jeu (int l1, int l2, int k) {
		longueur = l1;
		largeur = l2;
		this.k = k;
		aquiletour = true;
		p = new Plateau (l1,l2);
	}
	
	/**
	 * permet d'exécuter un tour de la part du joueur
	 * @param a et b les coordonnées de la case où il faut jouer
	 */
	public void tourdejeu (int a, int b) {
		if (aquiletour && a<longueur && b<largeur) {
			Case[][] tmp = p.getTableau();
			if (tmp[a][b].getEtat() != ' ') {
				System.out.println("erreur, la case choisie est déjà remplie");
			}
			else {
				tmp[a][b].setEtat('X');
				aquiletour = false;
			}
		}
	}		
	
	
	/**
	 * permet de lancement de notre Gomoku
	 * @param args le contenu de la ligne de commande
	 */
	public static void main (String[]args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Bonjour, vous allez jouer au Gomoku contre l'ordi. Quelle sera la longueur du plateau ?");
		int lon = Integer.parseInt(sc.next());
		System.out.println("Très bien, et sa largeur ?");
		int larg = Integer.parseInt(sc.next());
		System.out.println("Combien de pions à aligner pour gagner ?");
		int pions = Integer.parseInt(sc.next());
		Jeu jeu = new Jeu (lon, larg, pions);
		System.out.println(jeu.p.toString());
		sc.close();
	}
}
		
		
		
		
		
