package view;

import model.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;




public class Partie {

    private JPanel panel, grille, haut;
    private JFrame frame;
    private Jeu game;
    private JButton[][] boutton;
    private JButton menu, botTour, parametre;


    public Partie(Jeu j) {
    	game = j;	
    	frame = new JFrame("Gomoku");
    	frame.setVisible(true);
    	frame.setSize(700,700);
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
    		panel.add(grille, BorderLayout.CENTER);
    		frame.setContentPane(panel);
    		
    		frame.revalidate();
    		frame.repaint();
    	}
    }
    
    
    /*
     * FAIRE LES REPAINT ET REVALIDATE
     * FAIRE FONCTION DRAW DE SORTE QUE LE LE JOUEUR PUISSE JOUER TOUT SEUL POUR L'INSTANT
     */

    
    public void creation_boutton() {
    	haut = new JPanel(new GridLayout(1,3));
    	menu = new JButton("Menu");
    	parametre = new JButton("Changer de plateau");
    	botTour = new JButton("Bot Joue");
    	haut.add(menu);
    	haut.add(parametre);
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
    			cellule.addActionListener((event) -> {System.out.println("le chouch"); game.tourdejeu(a,b); draw();});
    			boutton[i][j] = cellule;
    			
    		}
    	}
    	//panel.add(grille, BorderLayout.CENTER);
    	//frame.setContentPane(panel);
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
    	
    }
    
    
    public static void main(String[] args) {
    	Partie p = new Partie(new Jeu(10,10,4));
    }
    
    
}
	

