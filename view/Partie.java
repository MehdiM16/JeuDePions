package view;

import model.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;




public class Partie {

    private JPanel panel, grille, haut;
    private JFrame frame;
    private Jeu game;
    private JButton[][] boutton;
    private JButton menu, botTour;


    public Partie(Jeu j) {
    	game = j;	
    	frame = new JFrame("Gomoku");
    	frame.setVisible(true);
    	frame.setSize(700,700);
		frame.setLocationRelativeTo(null);
    	ImageIcon img = new ImageIcon("./icone.png");
    	frame.setIconImage(img.getImage());
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	boutton = new JButton[game.longueur][game.largeur];
    	draw();
    }


    
    
    public void draw() {
    	panel = new JPanel(new BorderLayout());
    	
    	creation_boutton();
    	
    	if(!game.pasDeCaseVide()) {
    		System.out.println("bonjour");
    		System.out.println(game.p.toString());
    		remplissage_grille();
    		bot_joue();
    		panel.add(grille, BorderLayout.CENTER);
    		frame.setContentPane(panel);
    		
    		frame.revalidate();
    		frame.repaint();
    	}
    }
    
    
    
    
    public void creation_boutton() {
    	haut = new JPanel(new GridLayout(1,2));
    	menu = new JButton("Menu");
    	botTour = new JButton("Bot Joue");
	menu.addActionListener((event) -> {frame.dispose(); new Fenetre();});
    	haut.add(menu);
    	haut.add(botTour);
    	
    	panel.add(haut, BorderLayout.NORTH);
    	
    }
    
    

    public void preparation_grille() {
    	Case[][] tab = game.p.getTableau();
    	
    	int longueur = game.longueur;
    	int largeur = game.largeur;
    	for(int i = 0; i < longueur; i++) {
    		for(int j = 0; j < largeur; j++) {
    			final int a = i;
    			final int b = j;
    			JButton cellule = new JButton();
    			if(tab[i][j].getEtat() == ' ') cellule.setBackground(new Color(255,255,255));
    			else if(tab[i][j].getEtat() == 'X') cellule.setBackground(new Color(255,0,0));
    			else if(tab[i][j].getEtat() == 'O') cellule.setBackground(new Color(0,0,255));
    			if (game.aquiletour && !victoire()) {
			    if(game instanceof PuissanceK) {
				cellule.addActionListener((event) -> {game.tourdejeu(game.casePossible(b),b); draw();});
			    } else {
				cellule.addActionListener((event) -> {game.tourdejeu(a,b); draw();});
			    }
			}
    			else cellule.setEnabled(false);
    			boutton[i][j] = cellule;
    			
    		}
    	}
    }
    
    
    public void remplissage_grille() {
    	preparation_grille();
    	int longueur = game.longueur;
    	int largeur = game.largeur;
    	grille = new JPanel(new GridLayout(longueur, largeur));
    	for(int i = 0; i < longueur; i++) {
    		for(int j = 0; j < largeur; j++) {
    			grille.add(boutton[i][j]);
    		}
    	}
    	
    }
    
    
    public void bot_joue() {
    	boolean endGame = false;
    	int maxX = -1;
	int maxY = -1;
	int winner = 0;
    	Random r = new Random();
    	if(!game.aquiletour && !victoire()) {
	    if (game.p.sameScore()) {
		game.p.initNbKuplets(game.k);
		XY res = game.p.getCaseMieuxPlacee();
		botTour.addActionListener((event) -> {game.tourOrdinateur(res.x, res.y); draw();});
	    }
	    else {
		ArrayList<XY> maxEvals = game.getMaxEvals();
		ArrayList<XY> bestEvals = game.getBestEvals(maxEvals); 
		int rand = r.nextInt(bestEvals.size());
		XY choice = bestEvals.get(rand);	
		//System.out.println("L'ordinateur a joué sur ("+choice.x+","+" "+choice.y+")");
		botTour.addActionListener((event) -> {
			game.tourOrdinateur(choice.x, choice.y);
			System.out.println("L'ordinateur a joué sur ("+choice.x+","+" "+choice.y+")"); 
			draw();
		    });
	    }
    	}
    	else {
	    botTour.setEnabled(false);
    	}
    	int highEval = game.getHighEval();
    }
    
    
    public boolean victoire() {
    	NewDriver driv = new NewDriver();
    	int[] res = driv.MAJscores(game.k);
    	int maxX = res[0];
    	int maxY = res[1];
    	int winner = -1;
    	int highEval = game.getHighEval();
    	if (highEval==maxX) {
			JLabel label = new JLabel("Vous avez gagné félicitation");
			panel.add(label, BorderLayout.SOUTH);
			return true;
		} else if (highEval==maxY) {
			JLabel label = new JLabel("Vous avez perdu");
			panel.add(label, BorderLayout.SOUTH);
			return true;
		}
		if (game.pasDeCaseVide()) {
			JLabel label = new JLabel("La partie est nulle");
			panel.add(label, BorderLayout.SOUTH);
			return true;
		}
		return false;
    }
    
    
    
    
    
    /*public void deroulement() {
    	draw();
    	boolean endGame = false;
    	boolean start = true;
    	int maxX = -1;
		int maxY = -1;
		int winner = 0;
    	Random r = new Random();
		while (!endGame) {
			if (game.aquiletour) {
				//int xy[] = jeu.promptXY(sc);
				//System.out.println("Vous avez joué sur ("+xy[0]+","+" "+xy[1]+")");
				//jeu.tourdejeu(xy[0], xy[1]);
				remplissage_grille();
			}
			else {	
				if (game.p.sameScore() || start) {
					game.p.initNbKuplets(game.k);
				    XY res = game.p.getCaseMieuxPlacee();
				    botTour.addActionListener((event) -> {game.tourOrdinateur(res.x, res.y); draw();});
				    //jeu.tourOrdinateur(res.x, res.y);
				    start = false;
				}
				else {
				    ArrayList<XY> maxEvals = game.getMaxEvals();
				    ArrayList<XY> bestEvals = game.getBestEvals(maxEvals); 
				    int rand = r.nextInt(bestEvals.size());
				    XY choice = bestEvals.get(rand);	
				    System.out.println("L'ordinateur a joué sur ("+choice.x+","+" "+choice.y+")");
				    //jeu.tourOrdinateur(choice.x, choice.y);
				    botTour.addActionListener((event) -> {game.tourOrdinateur(choice.x, choice.y); draw();});
				}
			}
			int highEval = game.getHighEval();
			if (highEval==maxX) {
				winner = 1;
				endGame = true;
			} else if (highEval==maxY) {
				winner = 0;	
				endGame = true;
			}
			if (!endGame && game.pasDeCaseVide()) {
				winner = 2;			
				endGame = true;
			}
			System.out.println(game.p.toString());	
		}
		//messageVainqueur(winner);
    }
    */
    
    /*public static void main(String[] args) {
    	Partie p = new Partie(new PuissanceK(10,10,4));
	//Partie go= new Partie(new Jeu(Fenetre.getLong(),Fenetre.getLarg(),Fenetre.getK()));
	}*/
    
    
}
	

