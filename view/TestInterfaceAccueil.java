package view;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class TestInterfaceAccueil {
    JFrame window;
    JPanel panel;
    JLabel label;
    JButton puissance,gomoku,close;
    
    public TestInterfaceAccueil () {
	window = new JFrame("Jeu de pions");
	window.setVisible(true);
	window.setSize(700,700);
	ImageIcon img = new ImageIcon("./icone.png");
	window.setIconImage(img.getImage());
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	panel = new JPanel(new GridLayout(3,1));
	label = new JLabel("Bienvenue dans le jeu de pion");
	close = new JButton("Quitter");
	puissance = new JButton("Puissance 4");
	gomoku = new JButton("Gomoku");

	JPanel haut = new JPanel();
	haut.add(label);
	label.setHorizontalAlignment(SwingConstants.CENTER);
	panel.add(haut);
	
	JPanel inter = new JPanel(new GridLayout(1,3));
	inter.add(new JPanel());
	JPanel disposition = new JPanel(new GridLayout(3,1));
	disposition.add(gomoku);
	disposition.add(puissance);
	disposition.add(close);
	inter.add(disposition);
	inter.add(new JPanel());
	panel.add(inter);

	window.setContentPane(panel);
    }
	
    public static void main (String[]args) {
	TestInterfaceAccueil x = new TestInterfaceAccueil();
    }
}
