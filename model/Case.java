package model;

import java.util.ArrayList;

/**
 * Cette classe permet d'instancier des cases
 * @version 1.0
 * @author les membres du groupe
 */
public class Case {
	private char etat;
	private int score;
	private ArrayList<Kuplet> Kuplets;
	private int nbrKuplets; // liste des K-uplets concernes par la case

	/**
	 * constructeur pour initialiser des Cases
	 */
	public Case () {
		etat = ' ';
		score = 0;
		Kuplets = new ArrayList<Kuplet>();
	}
	
	/**
	 * setter 
	 * @param nouvel etat de la case
	 */
	public void setEtat (char c) {
		etat = c;
	}
	
	/**
	 * setter
	 * @return le nombre de Kuplets concernés par la case
	 */
	public int getNbrKuplets () {
		return nbrKuplets;
	}


    public  int setNbrKuplets(int n) {
    	nbrKuplets = n;
    	return nbrKuplets;
    }
	
	/**
	 * getter
	 * @return l'etat de la case
	 */
	public char getEtat () {
		return etat;
	}
	
	/**
	 * getter
	 * @return la liste des Kuplets
	 */
	public ArrayList<Kuplet> getKuplets () {
		return Kuplets;
	}
	
	/**
	 * getter
	 * @return le score de la case
	 */
	public int getScore () {
		return score;
	}
	
	/**
	 * setter
	 * @param le nouveau score de la case
	 */
	public void setScore (int s) {
		score = s;
	}
	
	/**
	 * modifie la liste des Kuplets rattaches a la case
	 * @param uplet un nouveau kuplet
	 */
	public void addKuplet (Kuplet uplet) {
		Kuplets.add(uplet);
	}

	/**
	 * description textuelle d'une case
	 * @return une chaîne de caractère qui va apparaitre sur le terminal
	 */
	
	
	public String toString () {
		return "[" + etat + "] ";
	}
	
	public int evaluate() {
		int eval = Kuplets.get(0).evaluate();
		for (int i=1; i<Kuplets.size(); i++) {
			int eval2 = Kuplets.get(i).evaluate();
			if (eval < eval2) { eval = eval2; }
		}
		score = eval;		// on met à jour le champ "score" par la valeur de l'évaluation maximale
		return score;		// on retourne l'évaluation maximale
	}

}

