
import java.awt.BorderLayout;
import javax.swing.*;

public class ajouter extends JFrame {
	
	private JLabel lb1 = new JLabel("Intitulé :");
	private JButton bouton = new JButton("Ajouter");
	private JPanel conteneur = new JPanel();
	private JTextField tf1 = new JTextField(10);
	private JComboBox<String> combo = new JComboBox<String>();
	
	// TODO Constructeur de la fenêtre
	
	public ajouter(){
		
		// TODO Modifier le titre de la fenêtre
		setTitle("Ajouter une vidéo");
		// TODO Modifier la taille
		setSize(400, 300);
		// TODO Taille non modifiable par l'utilisateur
		setResizable(false);
		// TODO Un click sur croix entraine fermeture de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// TODO Centrer la fenêtre par rapport à l'écran de l'odinateur
		setLocationRelativeTo(null);
		
		// ajout bouton sur le conteneur
		conteneur.add(bouton);
		// ajout label
		conteneur.add(lb1);
		//ajout textField
		conteneur.add(tf1);
		//ajout éléments dans la combo
		combo.addItem("Thème1");
		combo.addItem("Thème2");
		combo.addItem("Thème3");
		// affichage de la combo
		conteneur .add(combo);
	
		
		setContentPane(conteneur);
	}
}
