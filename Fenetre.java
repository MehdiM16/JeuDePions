
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.*;
import java.awt.*;
import java.io.*;

 
 
public class Fenetre extends JFrame{
	
    JPanel menu = new JPanel();
    JPanel gomoku= new JPanel();
    JPanel puissancek= new JPanel();
    
    JTextField pseudo=new JTextField("pseudo");
    JButton entrer=new JButton("Jouer");
   
    
     JButton bgomoku = new JButton("Gomoku");
     JButton bpuissancek =new JButton("Puissancek");
     JButton rp= new JButton("retour");
     JButton rg= new JButton("retour");
     JLabel p= new JLabel("Votre pseudo:");
     JLabel espace= new JLabel("                                           ");
    public Fenetre(){
        //ParamÃ¨tres de la fenetre
        super("Jeu De Pion");
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        setLayout( null);
        	       
        JLabel label = new JLabel("      Jeu de Pion         ");
        
        label.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,90));
      
         espace.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,100));
      
 
       
 
        p.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,25));
       
    	pseudo.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,25));
    
    	pseudo.setColumns(15);
       
    	
              
  entrer.setPreferredSize(new Dimension(150,34));
  bpuissancek. setPreferredSize(new Dimension(250,55));
  bgomoku. setPreferredSize(new Dimension(250,55));

        
    
    	
    	this.entrer.addActionListener(new textPseudo());
    	this.bpuissancek.addActionListener(new EcouteurBoutonPuissancek());
    	this.bgomoku.addActionListener(new EcouteurBoutonGomoku());
        this.rp.addActionListener(new EcouteurBoutonRetourMenu ());
        this.rg.addActionListener(new EcouteurBoutonRetourMenu ());
      
        this.menu.add(label); 
        this.menu.add(espace);
        this.menu.add(p);
        this.menu.add(pseudo);
        this.menu.add(entrer);
        
        this.puissancek.add(this.rp); 
        this.gomoku.add(this.rg); 

       
      
        
        this.setContentPane(this.menu);
        

        //Couleur des panels (pour voir le changement)
        
        
      // this.bpuissancek.setBackground(Color.CYAN);
        this.puissancek.setBackground(Color.CYAN);
        this.menu.setBackground(Color.lightGray);
        this.gomoku.setBackground(Color.pink);
        
      


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
        this.revalidate();
        pseudo.setVisible(false);
        p.setVisible(false);
    }
  
    public void allerGomoku(){
        this.setContentPane(this.gomoku);
        pseudo.setVisible(false);
       p.setVisible(false);

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
            //Appelle la mÃ©thode de changement de panel
            Fenetre.this.allerGomoku();
        }
    }
    public class EcouteurBoutonRetourMenu implements ActionListener{
        public void actionPerformed(ActionEvent clic) {
            //Appelle la mÃ©thode de changement de panel
            Fenetre.this.retourMenu();
        }
    }
   
   public JPanel getGomoku() {
	   return gomoku;
   }
    public void  setGomoku(JLabel t) {
    	
    gomoku.add(t);
    }
    
    //Lanceur
    public static void main(String[] args){
        Fenetre fen = new Fenetre();
    }
}
