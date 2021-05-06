package model;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * cette classe constitue le point d'entrée du programme
 * @author les membres du projet
 * @version 1.0
 */
public class Jeu {
	public Plateau p;
	public int longueur;
	public int largeur;
	public int k;
	public boolean aquiletour; // true si c'est au joueur de jouer
	
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
		initialize();
	}
	
	public void initialize() {
		Case[][] tmp = p.getTableau();	// prend les cases du plateau dans une variable temporaire
		for (int i=0; i<longueur; i++) {	// parcourt les lignes
			for (int j=0; j<largeur-k+1; j++) {		// parcourt les colonnes jusqu'à largeur-k
				Kuplet uplet = new Kuplet(k);		// créer un objet kuplet avec k cases
				Case[] tmpTableau = uplet.getTableau(); 	// prend les cases de cet uplet dans un tableau temporaire
				for (int index=0; index<k; index++) {	
					tmpTableau[index] = tmp[i][j+index];	// remplir le tableau par les cases du plateau
				}											// (cases voisines horizontalement)
				
				for (int index=0; index<k; index++) {		// ajouter l'uplet rempli à ces cases concernées
					tmp[i][j+index].addKuplet(uplet);	
				} 	
			}
		}
		for (int i=0; i<largeur; i++) {		// parcourt les colonnes
			for (int j=0; j<longueur-k+1; j++) {	// parcourt les lignes jusqu'à longueur-k
				Kuplet uplet = new Kuplet(k);		// créer un objet kuplet avec k cases
				Case[] tmpTableau = uplet.getTableau(); 	// prend les cases de cet uplet dans un tableau temporaire
				for (int index=0; index<k; index++) {
					tmpTableau[index] = tmp[j+index][i];	// remplir le tableau par les cases du plateau
				}											// (cases voisines verticalement)
				
				for (int index=0; index<k; index++) {		// ajouter l'uplet rempli à ces cases concernées
					tmp[j+index][i].addKuplet(uplet);	
				} 	
			}
		}
		for (int i=0; i<longueur-k+1; i++) {	// parcourt les lignes jusqu'à longueur-k
			for (int j=0; j<largeur-k+1; j++) {		// parcourt les colonnes jusqu'à largeur-k
				Kuplet uplet = new Kuplet(k);		// créer un objet kuplet avec k cases
				Case[] tmpTableau = uplet.getTableau(); 	// prend les cases de cet uplet dans un tableau temporaire
				for (int index=0; index<k; index++) {
					tmpTableau[index] = tmp[index+i][j+index];	// remplir le tableau par les cases du plateau
				}												// (cases voisines diagonalement de heut en bas, de gauche à droite)
				
				for (int index=0; index<k; index++) {		// ajouter l'uplet rempli à ces cases concernées	
					tmp[index+i][j+index].addKuplet(uplet);	
				} 	
			}
		}
		for (int i=k-1; i<longueur; i++) {		// parcourt les lignes
			for (int j=0; j<largeur-k+1; j++) {		// parcourt les colonnes jusqu'à largeur-k
				Kuplet uplet = new Kuplet(k);		// créer un objet kuplet avec k cases
				Case[] tmpTableau = uplet.getTableau(); 	// prend les cases de cet uplet dans un tableau temporaire
				for (int index=0; index<k; index++) {
					tmpTableau[index] = tmp[i-index][j+index];	// remplir le tableau par les cases du plateau
				}												// (cases voisines diagonalement de heut en bas, de droite à gauche)
				
				for (int index=0; index<k; index++) {		// ajouter l'uplet rempli à ces cases concernées
					tmp[i-index][j+index].addKuplet(uplet);	
				} 	
			}
		}
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
	
	
	public int casePossible(int colonne) {
		if(this instanceof PuissanceK) {
			return this.casePossible(colonne);
		} else return -1;
	}		
	
	/**
	 * permet d'executer un tour de la part de l'ordinateur
	 * @param a et b les coordonnées de la case qu'il faut jouer
	 */
	public void tourOrdinateur (int a, int b) {
		if (!aquiletour && a<longueur && b<largeur) {
			Case[][] tmp = p.getTableau();
			if (tmp[a][b].getEtat() != ' ') {
				System.out.println("erreur, la case choisie est deja remplie");
				//System.out.println(a + "**************************" + b);
			}
			else {
				tmp[a][b].setEtat('O');
				aquiletour = true;
			}
		}
	}
	
	/**
	 * renvoie un tableau de cases vides qui correspondent à l'evaluation maximale
	 * @return une ArrayList de coordonnees de cases
	 */
	public ArrayList<XY> getMaxEvals() {
		ArrayList<XY> points = new ArrayList<XY>();
		Case[][] tmp = p.getTableau();
		int max=0;
		for (int i=0; i<longueur; i++) {
			for (int j=0; j<largeur; j++) {
				if (tmp[i][j].getEtat()==' ') {		// max va prendre l'évaluation de la 1ère case vide
					max = tmp[i][j].evaluate();		// puis arrète la boucle
					break;
				}
			}
		}
		for (int i=0; i<longueur; i++) {		// boucler
			for (int j=0; j<largeur; j++) {
				if (tmp[i][j].getEtat()==' ') {
					if (max<tmp[i][j].evaluate()) {		// tester max avec les évals des autres cases vides
						max = tmp[i][j].evaluate();		// et prend toujours la valeur supérieure 
					}
				}
			}
		}
		for (int i=0; i<longueur; i++) {		// on pourra avoir plusieurs cases qui ont des évals
			for (int j=0; j<largeur; j++) {		// égale à l'évaluation maximale, on boucle donc pour 
				if (tmp[i][j].getEtat()==' ') {		// remplir toutes ces cases dans notre tableau 'points'
					if (max==tmp[i][j].evaluate()) {
						XY xy = new XY(i, j, max, tmp[i][j].getNbrKuplets());
						points.add(xy);
					}
				}
			}
		}
		return points;		// renvoie le tableau 'points'
	}
	
	/**
	 * méthode qui demande à l'utilisateur de saisir le numéro de ligne et colonne
	 * @param le scanner qui va permettre a l'utilisateur de parler au programme
	 * @return les coordonnees a remplir
	 */
	public int[] promptXY(Scanner sc) {
		int[] point = new int[2];	
		int ligne = -1;
		int colonne = -1;
		while (ligne<0 || ligne>=longueur) {	
			System.out.println("Donnez le numéro de ligne :");
			try {
				ligne = Integer.parseInt(sc.next());
				if (ligne<0 || ligne>=longueur) {
					System.out.println("erreur, la ligne choisie n'existe pas dans le plateau");
				}
				else {
					point[0] = ligne;
				}
			} catch (Exception e) {
				System.out.println("Erreur! "+e.getMessage());
			}
		}
		while (colonne<0 || colonne>=largeur) {	// boucler tant que le numéro de colonne est invalide
			System.out.println("Donnez le numéro de colonne :");
			try {
				colonne = Integer.parseInt(sc.next());
				if (colonne<0 || colonne>=largeur) {
					System.out.println("erreur, la colonne choisie n'existe pas dans le plateau");
				}
				else {
					point[1] = colonne;
				}
			} catch (Exception e) {
				System.out.println("Erreur! "+e.getMessage());
			}
		}
		return point;
	}
	
	/**
	 * méthode qui renvoie l'évaluation maximale de l'ensemble des cases du plteau (vides et non vides)
	 */
	public int getHighEval() {
		int eval = 0;
		Case[][] tmp = p.getTableau();
		for (int i=0; i<longueur; i++) {
			for (int j=0; j<largeur; j++) {
				if (eval<tmp[i][j].evaluate()) {
						eval = tmp[i][j].evaluate();
				}
			}
		}
		return eval;
	}
	
	/**
	 * méthode qui prend en paramètre un tableau de cases, puis élimine les cases
	 * qui font partie à des kuplets avec évaluation = 0 (kuplets qui contiennent 
	 * au moins un 'X' et un 'O') pour le but d'optimiser les choix
	 * renvoie un nouveau tableau (plus optimal) de cases vides
	 */
	public ArrayList<XY> getBestEvals(ArrayList<XY> maxEvals) {
		Case[][] tmp = p.getTableau();
		ArrayList<XY> points = new ArrayList<XY>();
		for (XY xy:maxEvals) {
			points.add(xy);		// ajouter au tableau points tous les cases du tableau d'entrée
		}
		for (XY xy:maxEvals) {	// parcourt toutes les cases une par une
			for (Kuplet uplet:tmp[xy.x][xy.y].getKuplets()) {	// parcourt pour chacune les kuplets
				if (uplet.evaluate()==0) {						// concernés par cette case,
					points.remove(xy);				// s'il existe un kuplet avec évaluation = 0 alors
					break;							// supprimer la case et passer à la case suivante
				}
			}
		}
		if (points.size()==0) {		// si le tableau points résultat devient vide alors
			points = maxEvals;		// toutes les cases contiennent des kuplet avec eval = 0
		}							// et on a que garder notre tableau d'entrée
		return points;
	}
	
	/**
	 * méthode qui parcourt le plateau et vérifie s'il existe encore de cases vides
	 * @return true si le plateau est complet et false sinon
	 */
	public boolean pasDeCaseVide() {
		Case[][] tmp = p.getTableau();
		for (int i=0; i<longueur; i++) {
			for (int j=0; j<largeur; j++) {
				if (tmp[i][j].getEtat()==' ') {
						return false;
				}
			}
		}
		return true;
	}
	
}
		
		
		
		
		
