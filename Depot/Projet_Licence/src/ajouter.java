
import javax.swing.JFrame;
import javax.awt.BorderLayout;
import javax.swing.*;

public class ajouter extends JFrame {
	
	private JLabel lb1 = new JLabel("Intitulé :");
	private JButton bouton = new JButton("Ajouter");
	private JPanel conteneur = new JPanel();
	private JTextField tf1 = new JTextField(10);
	
	
	
	
	// constructeur 
	public Fenetre(){
		
		setTitle("Ajout d'une vidéo");
		setSize(300, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//BorderLayout
		conteneur.setLayout(BorderLayout());
		
		//remplissage du conteneur
		conteneur.add(bouton,BorderLayout.CENTER);
		conteneur.add(lb1);
		conteneur.add(tf1);
		setContentPane(conteneur);
		pack();
	}

}
