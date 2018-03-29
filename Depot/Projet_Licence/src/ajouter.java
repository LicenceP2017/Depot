import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;


import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ajouter extends JFrame {

	DefaultListModel<String> addList = new DefaultListModel<String>();
	DefaultListModel<String> rmvList = new DefaultListModel<String>();
	private JPanel contentPane;
	private JTextField textField;
	private File file;
	String Video, Image = null;
	JLabel LabelVideo,LabelImage;
	
	
	String nomGeste;
	Geste g;
	ArrayList<String> motCle;
	ArrayList<String> lesmotCle;
	ArrayList<String> lesmotCleAff;
	ArrayList<String> categorie;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ajouter(String nom) throws SQLException {
		nomGeste = nom;
		
		
		setBounds(100, 100, 944, 582);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		textField = new JTextField();
		textField.setBounds(399, 60, 200, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton openFile = new JButton( "Open Image" );
        openFile.addActionListener(
           new ActionListener() {
              public void actionPerformed( ActionEvent e )
              {
                 openFileImage();
              }
           }
        );
       // getContentPane().add( openFile, BorderLayout.NORTH );
     
      
        
        JButton openFile1 = new JButton( "Open Vidéo" );
        openFile1.addActionListener(
           new ActionListener() {
              public void actionPerformed( ActionEvent e )
              {
                 openFileVideo();
              }
           }
        );
     //   getContentPane().add( openFile1, BorderLayout.SOUTH );
		
		

	
		
		contentPane.add( openFile, BorderLayout.NORTH );
        setSize( 955, 812 );	
		
		openFile.setBounds(399, 162, 160, 25);
		contentPane.add(openFile);
		
		contentPane.add( openFile1, BorderLayout.NORTH );
        setSize( 955, 812 );	
		
		openFile1.setBounds(600, 162, 160, 25);
		contentPane.add(openFile1);
		
		JLabel lblCatgorie = new JLabel("Catégorie :");
		lblCatgorie.setBounds(160, 115, 111, 15);
		contentPane.add(lblCatgorie);
		
		JComboBox combo = new JComboBox();
		combo.setBounds(399, 110, 169, 25);
		contentPane.add(combo);


		
		
		JLabel lblCommentaires = new JLabel("Explications :");
		lblCommentaires.setBounds(160, 235, 160, 15);
		contentPane.add(lblCommentaires);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(399, 235, 310, 47);
		contentPane.add(textArea);
		
		Button buttonAjout = new Button("Ajouter");
		buttonAjout.setFont(new Font("Dialog", Font.BOLD, 14));
		buttonAjout.setBounds(446, 750, 98, 27);
		contentPane.add(buttonAjout);
		
		LabelImage = new JLabel("LabelImage");
		LabelImage.setBounds(399, 199, 170, 15);
		contentPane.add(LabelImage);
		
		JLabel lblAvantages = new JLabel("Avantage(s)  :");
		lblAvantages.setBounds(160, 326, 129, 15);
		contentPane.add(lblAvantages);
		
		JLabel lblInconvgnents = new JLabel("Inconvégnent(s) :");
		lblInconvgnents.setBounds(160, 392, 129, 15);
		contentPane.add(lblInconvgnents);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(399, 326, 310, 27);
		contentPane.add(textArea_1);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(399, 392, 310, 27);
		contentPane.add(textArea_2);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setBounds(399, 450, 121, 27);
		contentPane.add(textArea_3);
		
		JLabel lblValeur = new JLabel("Valeur :");
		lblValeur.setBounds(160, 450, 70, 15);
		contentPane.add(lblValeur);
		
		LabelVideo = new JLabel("LabelVideo");
		LabelVideo.setBounds(600, 199, 170, 15);
		contentPane.add(LabelVideo);
		
		JList<String> list = new JList<String>(rmvList);
		list.setBounds(399, 538, 100, 150);
		contentPane.add(list);
		
		JList<String> list_1 = new JList<String>(addList);
		list_1.setBounds(670, 538, 100, 150);
		contentPane.add(list_1);
		
		JButton btnAdd = new JButton("Add >>");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAdd.setBounds(538, 547, 93, 25);
		contentPane.add(btnAdd);
		
		JButton btnRmv = new JButton("<< Rmv");
		btnRmv.setBounds(538, 638, 93, 25);
		contentPane.add(btnRmv);
		
		JLabel lblMotscls = new JLabel("Mots-Clés :");
		lblMotscls.setBounds(160, 600, 86, 15);
		contentPane.add(lblMotscls);
		
		JLabel lblNewLabel = new JLabel("Nom Mouvement :");
		lblNewLabel.setBounds(160, 60, 129, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNiveauDeDifficult = new JLabel("Niveau de difficulté :");
		lblNiveauDeDifficult.setBounds(160, 490, 170, 15);
		contentPane.add(lblNiveauDeDifficult);
		
		
		JComboBox<Integer> comboBox = new JComboBox<Integer>();
		comboBox.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {0, 1, 2, 3, 4, 5}));
		comboBox.setBounds(399, 490, 121, 27);
		contentPane.add(comboBox);
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				motCle.add(list.getSelectedValue());
				chargerMotCle();
			}
		});
		
		btnRmv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				motCle.remove(list_1.getSelectedValue());
				chargerMotCle();
			}
		});
		
		
		g = new Geste();
		categorie = new ArrayList<String>();
		categorie = g.toutesCategorie();
		for(String c : categorie)
		{
			combo.addItem(c);
		}
		
		
		if(nomGeste != "")
		{
			JButton buttonsupprimer = new JButton("Supprimer");
			buttonsupprimer.setBounds(560, 750, 98, 27);
			contentPane.add(buttonsupprimer);
			
			buttonsupprimer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					try {
						g.supprGeste(g);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			g = g.recupGeste(nomGeste);
			
			textField.setText(g.nomGeste);

			String maCategorie = g.recupCategorie(g.idGeste);
			combo.setSelectedIndex(categorie.indexOf(maCategorie));
			
			textArea.setText(g.explication);
			textArea_1.setText(g.avantage);
			textArea_2.setText(g.inconvenient);
			textArea_3.setText(g.valeur);
			comboBox.setSelectedIndex(g.difficulte);

		}
		
		
		motCle = new ArrayList<String>();
		motCle = g.recupMotCle(g.idGeste);
		
		lesmotCle = new ArrayList<String>();
		lesmotCle = g.toutMotCle();
		chargerMotCle();
		
		buttonAjout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String nom = textField.getText();
				String categorie = combo.getSelectedItem().toString();
				String avantage = textArea_1.getText();
				String incovenient = textArea_2.getText();
				String explication = textArea.getText();
				int diff = comboBox.getSelectedIndex();
				String valeur = textArea_3.getText();
				int idvideo;
				
				String nomvideo = LabelVideo.getText();
				
				boolean test = true;
				
				if(!nomGeste.equals(""))	{ // pour la modification
					System.out.println("modif");
					try {
						g.deleteMotCleEtGeste(g.nomGeste);
						for(String i : motCle)	{
							g.lieMotCleEtGeste(i, g.nomGeste);
						}
						g.deleteCategorieEtGeste(categorie, g.nomGeste);
						g.lieCategorieEtGeste(categorie, g.nomGeste);
						
						if(!g.nomGeste.equals(nom))	{
							System.out.println("rentre dans la boucle");
							if(g.existGeste(nom) == false) {
								g.nomGeste = nom;
							}
							else {
								JOptionPane.showMessageDialog(null,"Nom du geste deja existant ","Probleme",JOptionPane.PLAIN_MESSAGE);
								test = false;
							}
						}
						
						g.avantage = avantage;
						g.inconvenient = incovenient;
						g.explication = explication;
						g.difficulte = diff;
						g.valeur = valeur;
						
						if(g.existVideo(nomvideo) == false) {
							g.nouvelleVideo(nomvideo);
						}
						idvideo = g.retIdVideo(nomvideo);
						
						g.idvideo = idvideo;
						
						if(test == true) { 	g.modifGeste(g);  	}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {  //////////////////// pour l'ajout
					System.out.println("ajout");
					
					try {
						
						
						if(g.existGeste(nom) == false) {
							g.nomGeste = nom;
						}
						else {
							JOptionPane.showMessageDialog(null,"Nom du geste deja existant ","Probleme",JOptionPane.PLAIN_MESSAGE);
							test = false;
						}
						
						g.avantage = avantage;
						g.inconvenient = incovenient;
						g.explication = explication;
						g.difficulte = diff;
						g.valeur = valeur;
						
						
						if(g.existVideo(nomvideo) == false) {
							g.nouvelleVideo(nomvideo);
						}
						idvideo = g.retIdVideo(nomvideo);
						g.idvideo = idvideo;
						
						System.out.println(g.nomGeste);
						System.out.println(g.avantage);
						System.out.println(g.inconvenient);
						System.out.println(g.explication);
						System.out.println(g.difficulte);
						System.out.println(g.valeur);
						System.out.println(g.idvideo);
						
						if(test == true) { 	
							g.nouveauGeste(g.nomGeste, g.avantage, g.inconvenient, g.explication, g.difficulte, g.valeur, g.idvideo); 
							
							for(String i : motCle)	{
								g.lieMotCleEtGeste(i, g.nomGeste);
							}
							g.lieCategorieEtGeste(categorie, g.nomGeste);
							
						}
						
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		
		//contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{LabelImage, LabelVideo, textField, openFile}));
	}
	
	private void chargerMotCle()
	{
		rmvList.clear();
		addList.clear();
		
		for(String i : lesmotCle)
		{
			if(!motCle.contains(i))
				rmvList.addElement(i);
		}
			
		
		for(String i : motCle)
			addList.addElement(i);
	}
	
	// méthodes
	 private void openFileImage()
	    {      
	       JFileChooser fileChooser = new JFileChooser();
	  
	       fileChooser.setFileSelectionMode(
	          JFileChooser.FILES_ONLY );
	       int result = fileChooser.showOpenDialog( this );
	  
	       // user clicked Cancel button on dialog
	       if ( result == JFileChooser.CANCEL_OPTION )
	       {  file = null;
	    }
	       
	       else {
	          file = fileChooser.getSelectedFile();
	          Image= file.getName();
	          LabelImage.setText(Image);
	          
		       
	          System.out.println(Image);
	       }
	          
	       
	       
	    }
	    
	    
	    private void openFileVideo()
	    {      
	       JFileChooser fileChooser = new JFileChooser();
	  
	       fileChooser.setFileSelectionMode(
	          JFileChooser.FILES_ONLY );
	       int result = fileChooser.showOpenDialog( this );
	  
	       // user clicked Cancel button on dialog
	       if ( result == JFileChooser.CANCEL_OPTION )
	          file = null;
	       else
	          file = fileChooser.getSelectedFile();
	         Video= file.getName();
	         LabelVideo.setText(Video);
	         
	        
		       
	          System.out.println(Video);

	         
	       
	       
	    }
}

