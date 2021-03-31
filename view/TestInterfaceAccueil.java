import javax.swing.*;
import java.awt.*;
import java.io.*;
public class TestInterfaceAccueil {
	JFrame window;
	JPanel panel;
	
	public TestInterfaceAccueil () {
		window = new JFrame("Jeu de pions");
		window.setVisible(true);
		window.setSize(700,700);
		ImageIcon img = new ImageIcon("./icone.png");
		window.setIconImage(img.getImage());	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
	}
	
	public static void main (String[]args) {
		TestInterfaceAccueil x = new TestInterfaceAccueil();
		}
}
