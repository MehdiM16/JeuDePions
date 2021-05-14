package view;

import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.io.*;


import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.Math.*;


public class Fenetre extends JFrame{
    
    JPanel menu = new JPanel();
    JPanel gomoku= new JPanel();
    JPanel puissancek= new JPanel();
    
    JTextField pseudo=new JTextField("pseudo");
    JButton entrer=new JButton("Jouer");
    
    
    JButton bgomoku = new JButton("Gomoku");
    JButton bpuissancek =new JButton("Puissancek");
    JLabel p= new JLabel("Votre pseudo:");
    JLabel espace= new JLabel("                                           ");
    
    //pour les getteurs de dimensions
    private JComboBox xk = new JComboBox();
    private JComboBox xlong = new JComboBox();
    private JComboBox xlarg = new JComboBox();
    
    //pour panel Gomoku:
    JButton retour= new JButton("retour");
    JButton ok=new JButton("entrer");
    private JComboBox<Integer> longG = new JComboBox<Integer>();
    private JComboBox<Integer> largG = new JComboBox<Integer>();
    private JComboBox<Integer> kG = new JComboBox<Integer>();
    //Pour panel puissancek:
    JButton retourp= new JButton("retour");
    JButton okp=new JButton("entrer");
    private JComboBox<Integer> longGp = new JComboBox<Integer>();
    private JComboBox<Integer> largGp = new JComboBox<Integer>();
    private JComboBox<Integer> kGp = new JComboBox<Integer>();

    
    public Fenetre(){
        //Parametre de la fenetre
        super("Jeu De Pion");
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        setLayout( null);
        
        //Pour le menu
        JLabel label = new JLabel("      Jeu de Pion         ");
        
        label.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,90));
	
	espace.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,100));
	
        p.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,25));
	
    	pseudo.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,25));
	
    	pseudo.setColumns(15);
	
	entrer.setPreferredSize(new Dimension(150,34));  	  
	bpuissancek. setPreferredSize(new Dimension(250,55));
	bgomoku. setPreferredSize(new Dimension(250,55));
	
	//pour panel gomoku
	for(int i=1; i<41;i++) {
	    kG.addItem(i);
	    longG.addItem(i);
	    largG.addItem(i);
	    
	}
	
	kG.addItemListener(new ItemState());
	kG.setPreferredSize(new Dimension(100, 20));
	kG.setForeground(Color.blue);
	longG.addItemListener(new ItemState());
	longG.setPreferredSize(new Dimension(100, 20));
	longG.setForeground(Color.blue); 
	largG.addItemListener(new ItemState());
	largG.setPreferredSize(new Dimension(100, 20));
	largG.setForeground(Color.blue);
	
    	this.retour.addActionListener(new EcouteurBoutonRetourMenu ());
    	
    	//Pour la panel puissancek
    	
    	
	for(int i=1; i<41;i++) {
	    kGp.addItem(i);
	    longGp.addItem(i);
	    largGp.addItem(i);
	    
	}
	kGp.addItemListener(new ItemState());
	kGp.setPreferredSize(new Dimension(100, 20));
	kGp.setForeground(Color.blue);
	longGp.addItemListener(new ItemState());
	longGp.setPreferredSize(new Dimension(100, 20));
	longGp.setForeground(Color.blue); 
	largGp.addItemListener(new ItemState());
	largGp.setPreferredSize(new Dimension(100, 20));
	largGp.setForeground(Color.blue);
    	
	this.retourp.addActionListener(new EcouteurBoutonRetourMenu ());
    	
    	// pour le menu d entrer    	    
      	this.entrer.addActionListener(new textPseudo());
    	this.bpuissancek.addActionListener(new EcouteurBoutonPuissancek());
    	this.bgomoku.addActionListener(new EcouteurBoutonGomoku());
	
        this.menu.add(label); 
        this.menu.add(espace);
        this.menu.add(p);
        this.menu.add(pseudo);
        this.menu.add(entrer);
        
	
        this.setContentPane(this.menu);
        
        //panel gomoku
        JLabel longeurT=new JLabel("  longueur  ");
	JLabel largeurT=new JLabel("  largeur  ");
	JLabel nbrkT =new JLabel(" nombre de pions à aligner ");
	
	JLabel regleP=new JLabel("Regles du Jeu");
	JLabel regle1=new JLabel("Du Gomoku: ");
	regleP.setFont(new Font(Font.SERIF, Font.ITALIC,105));
	
	regle1.setFont(new Font(Font.SERIF, Font.ITALIC,70));
	
	JLabel reglePK= new JLabel( "<html>"+"Le but du jeu est d'etre le premier à avoir "
				    +" une ligne de k pions."+"<br>"+
				    "Ici k est le nombre de pions a aligner."+"<br>"+
				    " Avec k qui ne doit pas etre superieur à la longueur et à la largeur du plateau." + "<br>"
				    + " Pour commencer la partie veuillez completer: "+"</br></html>");    
	regle1.setFont(new Font(Font.SERIF, Font.ITALIC,60));
	reglePK.setFont(new Font(Font.SERIF, Font.ITALIC,20));
	
	
	gomoku.add(regleP);
	gomoku.add(regle1);
	JLabel espa =new JLabel("                                                            ");
	espa.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,100));
	
	gomoku.add(reglePK);       
	gomoku.add(espa);
	
	gomoku.add(longeurT);
	this.gomoku.add(largG);
	gomoku.add(largeurT);
	this.gomoku.add(longG);
	gomoku.add(nbrkT);
	this.gomoku.add(kG);
	
	
	this.gomoku.add(ok);
	ok.addActionListener(new Ecouteurok());
	this.gomoku.add(retour);
	
	
	//panel puissancek:
	
	JLabel longeurp=new JLabel("  longueur  ");
	JLabel largeurp=new JLabel("  largeur  ");
	JLabel nbrkp =new JLabel(" nombre de pions à alignier ");
	
	JLabel reglep=new JLabel("Regles du Jeu");
	JLabel reglepp=new JLabel("Du PuissanceK: ");
	reglep.setFont(new Font(Font.SERIF, Font.ITALIC,105));
	
	
	JLabel reglePp= new JLabel( "<html>"+"Le but du jeu est d'etre le premier à avoir "
				    +"k pions alignes."+"<br>"+" Il vous suffit de selectionner une colonne"+"<br>"
				    +"car les pions tombent par gravitee"+"<br>"+
				    "Ici k est le nombre de pions à aligner."+"<br>"+
				    "Avec k qui ne doit pas etre superieur à la longueur et à la largeur du plateau." + "<br>"
				    + "Pour commencer la partie veuillez completer: "+"</br></html>");    
	reglepp.setFont(new Font(Font.SERIF, Font.ITALIC,80));
	reglePp.setFont(new Font(Font.SERIF, Font.ITALIC,20));
	
	
	puissancek.add(reglep);
	puissancek.add(reglepp);
	JLabel espap =new JLabel("                                                            ");
	espap.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,100));

	puissancek.add(reglePp);       
	puissancek.add(espap);

	puissancek.add(longeurp);
	this.puissancek.add(largGp);
	puissancek.add(largeurp);
	this.puissancek.add(longGp);
	puissancek.add(nbrkp);
	this.puissancek.add(kGp);

	okp.addActionListener(new Ecouteurokp());
	this.puissancek.add(okp);
	this.puissancek.add(retourp);
       

	//Couleur des panels (pour voir le changement)
        
        this.puissancek.setBackground(Color.BLUE);
        this.menu.setBackground(Color.lightGray);
        this.gomoku.setBackground(Color.pink);
        
      


    }
    
    class ItemState implements ItemListener{
        public void itemStateChanged(ItemEvent e) {
	    getK();
	    getLarg();
	    getLong();

        }               
    }
    
    public int getK() {
	int a=xk.getSelectedItem().hashCode(); 
    
	return a;
    }
    public int getLarg () {
	int a=xlarg.getSelectedItem().hashCode();
	return a ;
    }
    public int getLong() {
	int a= xlong.getSelectedItem().hashCode();
	return a ;
    }
   
    public class textPseudo implements ActionListener{
        public void actionPerformed(ActionEvent clic) {
	    String user=pseudo.getText();
	    JLabel J = new JLabel("   Bienvenue "+user+"! Veuillez selectionner votre jeu:                ");
	    J.setBounds(20,50,20,20);
	    J.setFont(new Font(Font.SANS_SERIF, Font.HANGING_BASELINE,30));
 
	    menu.add(J);
	    menu.add(espace);
	    menu.add(bpuissancek);
	    menu.add(bgomoku);
	    Fenetre.this.retourMenu(); 
	    J.setVisible(true);
        
	    p.setVisible(false);
	    pseudo.setVisible(false);
	    entrer.setVisible(false);
        }  
    }
    public void allerPuissancek(){
        this.setContentPane(this.puissancek);
    
        pseudo.setVisible(false);
        p.setVisible(false);
      
	xk=kGp;
	xlarg=largGp; 
	xlong=longGp;
        this.revalidate();
    }
  
    public void allerGomoku(){
        this.setContentPane(this.gomoku);
        pseudo.setVisible(false);
	p.setVisible(false);

	xk=kG;  
	xlarg=largG; 
	xlong=longG;
       
	this.revalidate();
    }
    public void retourMenu(){
        this.setContentPane(this.menu);
        this.revalidate();
        
    }
    //Ecouteur de ton bouton
    public class EcouteurBoutonPuissancek implements ActionListener{
        public void actionPerformed(ActionEvent clic) {
	    Fenetre.this.allerPuissancek();
        	
        }
    } 
    //Ecouteur de ton bouton
    public class EcouteurBoutonGomoku implements ActionListener{
        public void actionPerformed(ActionEvent clic) {
            //Appelle la methode de changement de panel
            Fenetre.this.allerGomoku();
        }
    }
    public class EcouteurBoutonRetourMenu implements ActionListener{
        public void actionPerformed(ActionEvent clic) {
            //Appelle la methode de changement de panel
            Fenetre.this.retourMenu();
        }
    }
    
    
    public class Ecouteurokp implements ActionListener{
	public void actionPerformed(ActionEvent clic) {
	    if(getK() < Math.max(getLarg(),getLong())) {
		dispose(); 
		new Partie(new PuissanceK(getLong(),getLarg(),getK()));
	    }
	    
        }
    }
    public class Ecouteurok implements ActionListener{
	public void actionPerformed(ActionEvent clic){
	    if(getK() < Math.max(getLarg(),getLong())) {
		dispose();
		new Partie(new Jeu(getLong(),getLarg(),getK()));
	    }
	}
    }
    
    
    //Lanceur
    public static void main(String[] args){
        Fenetre fen = new Fenetre();
	
	
    }
}

