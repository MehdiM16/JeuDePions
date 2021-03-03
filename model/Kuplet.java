/**
 * Cette classe permet d'instancier des K-uplets suivant ce que veut l'user
 * @version 1.0
 * @author les membres du projet
 */
public class Kuplet {
	private int score;
	private Case[] tableau;
	private int k;
	
	/**
	 * constructeur pour initialiser des K-uplets
	 * @param n le nombre de pions alignés pour gagner la partie
	 */
	public Kuplet (int n) {
		score = 0;
		tableau = new Case[n];
		k = n;
	}
	
	/**
	 * getter 
	 * @return score du Kuplet
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * setter
	 * @param nouveau score du Kuplet
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * getter
	 * @return le tableau des cases
	 */
	public Case[] getTableau() {
		return tableau;
	}

	/**
	 * setter
	 * @param modifie le tableau des cases
	 */
	public void setTableau(Case[] tableau) {
		this.tableau = tableau;
	}
	
	/**
	 * méthode qui compte le nombre de 'O' dans un kuplet
	 * @return nbrO le nombre de O dans le Kuplet
	 */
	public int evaluateO() {
		int nbrO = 0;
		for (int i=0; i<k; i++) {
			if (tableau[i].getEtat()=='O')
				nbrO++;
		}
		
		return nbrO;
	}
	
	/**
	 * méthode qui compte le nombre de 'X' dans un kuplet
	 * @return nbrX le nombre de X dans le Kuplet
	 */
	public int evaluateX() {
		int nbrX = 0;
		for (int i=0; i<k; i++) {
			if (tableau[i].getEtat()=='X')
				nbrX++;
		}
		
		return nbrX;
	}
	
	/**
	 * fait parvenir le score d'un Kuplet en fonction du nombre de pions alignes
	 * @param le nombre de pions deja alignes
	 * @return le score du Kuplet
	 */
	public int scoreX (int x) {
		if (x==1) return 15;
		else if (x==2) return 400;
		else if (x==3) return 1800;
		else return 100000;
	} 
	
	/**
	 * fait parvenir le score d'un Kuplet en fonction du nombre de pions alignes
	 * @param le nombre de pions deja alignes
	 * @return le score du Kuplet
	 */
	public int scoreO (int o) {
		if (o==1) return 35;
		else if (o==2) return 800;
		else if (o==3) return 15000;
		else return 800000;
	} 
	
	public int evaluate() {
		if (evaluateX() == 0 && evaluateO() == 0) { score = 7; } // Kuplet vide
		else {
			if (evaluateX() == 0) {
				score = scoreO(evaluateO());
			}	
			else if (evaluateO() == 0) {
			    	score = scoreX(evaluateX());
			 }
			else score = 0; // s'il y a au moins 1 pion 'X' ET au moins un pion 'O'
		}
		return score;
	}




}
