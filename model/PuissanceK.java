import java.util.Scanner;

/**
 * cette classe constitue un deuxième point d'entrée du programme si l'on souhaite jouer à une variante du Gomoku
 * @author les membres du projet
 * @version 1.0
 */
public class PuissanceK extends Jeu {

	/**
	 * permet d'instancier une partie entre l'ordi et le joueur
	 * @param l1 et l2 les dimensions de la grille
	 * @param k le nombre de pions à aligner pour gagner
	 */
	public PuissanceK (int l1, int l2, int k) {
		super(l1,l2,k);
	}
	
}
