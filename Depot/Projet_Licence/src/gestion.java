import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class gestion extends JFrame {

	private JPanel JPanel;
	private JTextField textField;
	private JLabel lblAjouterCatgorie;
	private JButton btnAjouter;
	private JButton btnSupprimer;


	/**
	 * Create the frame.
	 */
	public gestion() {
		setTitle("Gestion catégorie");
		setBounds(100, 100, 775, 502);
		JPanel = new JPanel();
		JPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(JPanel);
		JPanel.setLayout(null);
		
		JList list = new JList();
		list.setBounds(330, 106, 200, 300);
		JPanel.add(list);
		
		textField = new JTextField();
		textField.setBounds(82, 141, 200, 25);
		JPanel.add(textField);
		textField.setColumns(10);
		
		lblAjouterCatgorie = new JLabel("Ajouter catégorie");
		lblAjouterCatgorie.setBounds(82, 107, 200, 15);
		JPanel.add(lblAjouterCatgorie);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(82, 181, 117, 25);
		JPanel.add(btnAjouter);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(587, 234, 117, 25);
		JPanel.add(btnSupprimer);
	}
}

