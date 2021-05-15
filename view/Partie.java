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
    	
    	boutton = new JButton[game.longueur][game.largeur];//tableau de JButton() qui servira pour la grille de jeu
    	draw();
    }


    
    
    public void draw() {//fonction qui sert a mettre en place l'interface de jeu
    	panel = new JPanel(new BorderLayout());
    	
    	creation_boutton();
    	
    	if(!game.pasDeCaseVide()) {
    		remplissage_grille();
    		bot_joue();
    		panel.add(grille, BorderLayout.CENTER);
    		frame.setContentPane(panel);
    		
    		frame.revalidate();
    		frame.repaint();
    	}
    }
    
    
    
    
    public void creation_boutton() {//fonction qui crée les bouttons en haut de l'interface de jeu
    	haut = new JPanel(new GridLayout(1,2));
    	menu = new JButton("Menu");
    	botTour = new JButton("Bot Joue");
	menu.addActionListener((event) -> {frame.dispose(); new Fenetre();});
    	haut.add(menu);
    	haut.add(botTour);
    	
    	panel.add(haut, BorderLayout.NORTH);
    	
    }
    
    

    public void preparation_grille() {
    	Case[][] tab = game.p.getTableau();//on recupere la tableau de case du modele
    	
    	int longueur = game.longueur;
    	int largeur = game.largeur;
    	for(int i = 0; i < longueur; i++) {
	    for(int j = 0; j < largeur; j++) {
		final int a = i;
		final int b = j;
		JButton cellule = new JButton();//boutton qui correspond a une case
		if(tab[i][j].getEtat() == ' ') cellule.setBackground(new Color(255,255,255));//on definit la couleur a afficher en fonction de l'etat de la case
		else if(tab[i][j].getEtat() == 'X') cellule.setBackground(new Color(255,0,0));
		else if(tab[i][j].getEtat() == 'O') cellule.setBackground(new Color(0,0,255));
		if (game.aquiletour && !victoire()) {
		    if(game instanceof PuissanceK) {//change l'etat des case jouer en fonction du jeu choisit
			cellule.addActionListener((event) -> {game.tourdejeu(game.casePossible(b),b); draw();});
		    } else {
			cellule.addActionListener((event) -> {game.tourdejeu(a,b); draw();});
		    }
		}
		else cellule.setEnabled(false);//si ce n'est pas au joueur de jouer on desactive la grille de jeu pour qu'elle ne soit pas cliquable
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
		grille.add(boutton[i][j]);//ajoute les bouttons 1 a 1 grace au boutton[][]
	    }
    	}
    	
    }
    
    
    public void bot_joue() {//fonction qui definit le bouttons "bot Joue" pour faire jouer le bot quand c'est son tour
	Random r = new Random();
    	if(!game.aquiletour && !victoire()) {
	    ArrayList<XY> maxEvals = game.getMaxEvals();
	    ArrayList<XY> bestEvals = game.getBestEvals(maxEvals); 
	    int rand = r.nextInt(bestEvals.size());
	    XY choice = bestEvals.get(rand);	//on recupere les coordonne de la case la mieux place pour jouer 
	    botTour.addActionListener((event) -> {
		    game.tourOrdinateur(choice.x, choice.y); //fait jouer le bot a cette case
		    draw();
		});
    	}
    	else {
	    botTour.setEnabled(false);//si la partie est finis ou que ce n'est pas au tour du bot de jouer on desactive le boutton
    	}
    }
    
    
    public boolean victoire() {//fonction qui definit a la fin de la partie qui a gagné
    	NewDriver driv = new NewDriver();
    	int[] res = driv.MAJscores(game.k);
    	int maxX = res[0];
    	int maxY = res[1];
    	int highEval = game.getHighEval();
    	if (highEval==maxX) {
	    JLabel label = new JLabel("Vous avez gagné félicitation");
	    panel.add(label, BorderLayout.SOUTH);//si le joueur a gagne on ajoute ce label au bas de la page pour lui indiquer qu'il a gagner
	    return true;
	} else if (highEval==maxY) {
	    JLabel label = new JLabel("Vous avez perdu");
	    panel.add(label, BorderLayout.SOUTH);//si le bot a gagne on ajoute ce label au bas de la page pour lui indiquer que le bot a gagner
	    return true;
	}
	if (game.pasDeCaseVide()) {
	    JLabel label = new JLabel("La partie est nulle");
	    panel.add(label, BorderLayout.SOUTH);//si la partie est nulle on ajoute ce label au bas de la page pour  l'indiquer au joueur
	    return true;
	}
	return false;
    }
    
    
    
}
	


