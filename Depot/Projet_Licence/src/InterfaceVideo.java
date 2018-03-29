import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class InterfaceVideo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//JPanel panelHaut, panelBas, panelCentre;
	JPanel panelGauche,panelDroite;
	JLabel NomVideo = new JLabel("Nom Vidéo");
	JLabel image = new JLabel( new ImageIcon("")); // avec la base
	JLabel imageLogo = new JLabel( new ImageIcon( "D:\\Depot\\Depot\\Projet_Licence\\src\\image\\logo.jpg"));
	JButton bouton = new JButton("Voir la vidéo");
	DefaultListModel<String> model = new DefaultListModel<String>();
	JList<String> jlist2 = new JList<String>(model);
	protected JTextField textField;
    protected JTextArea textArea;
    JMenuBar menuBar;
    JMenu mnAdmin;
    JMenu mnCategorie;
    JMenu mnAide;
    
    private String nomGeste;
    Geste g;
    ArrayList<String> motCle;
	ArrayList<String> images;
	ArrayList<String> categories;
	String nomvideo;
    
    public void chargerGeste(String ge) throws SQLException
    {
    	g = new Geste();
    	g = g.recupGeste(ge);
    	NomVideo.setText(g.nomGeste);
    	textArea.setText("avantage : " + g.avantage + "\n inconvenient : " + g.inconvenient);
    	
    	images = g.recupImage(g.idGeste);
    	String i = images.get(0);
    	image.setIcon(new ImageIcon("D:\\Depot\\Depot\\Projet_Licence\\src\\image\\"+i));
    	
    	nomvideo = g.recupVideo(g.idvideo);
    	
    }
    
    public void afficherListeGeste() throws SQLException
    {
    	g = new Geste();
    	ArrayList<String> lesGeste = g.getNomGeste();
    	for(String s : lesGeste){
    		model.addElement(s);
    	}
    }
    
    public void affichertri(ArrayList<String> lesGestes)
    {
    	model.clear();
    	for(String s : lesGestes){
	  		model.addElement(s);
	  	}
    }
    
	
    public InterfaceVideo(User unUser) throws SQLException {

super();




//############################################ Barre de Menu #############################################################
menuBar = new JMenuBar();
setJMenuBar(menuBar);

if(unUser.getDroit() == 1) 
{ 	
	mnAdmin = new JMenu("Menu Administrateur");
	menuBar.add(mnAdmin);
	
	JMenuItem mntmAjouter_1 = new JMenuItem("Ajouter");

	mntmAjouter_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			ajouter ia;
			try {
				ia = new ajouter("");
				ia.setSize(1200,900);
				ia.setTitle("Forme d'ajout de geste technique");
				ia.setVisible(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	mnAdmin.add(mntmAjouter_1);

	JMenuItem mntmModifier_1 = new JMenuItem("Modifier");

	mntmModifier_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			
            if(jlist2.getSelectedIndex() == -1)	JOptionPane.showMessageDialog(null,"Selectionnez d'abord un geste technique ! ","Probleme",JOptionPane.PLAIN_MESSAGE);
			
            
            else {
            	ajouter ia;
				try {
					ia = new ajouter(jlist2.getSelectedValue());
					ia.setTitle("Modification du geste technique");
	            	ia.setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
		}
	});
	mnAdmin.add(mntmModifier_1);
	
	JMenuItem gestionCat = new JMenuItem("Gestion des Catégories");

	gestionCat.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			gestion  ia = new gestion();
			ia.setSize(1200,900);
			ia.setTitle("Gestion des Catégories");
			ia.setVisible(true);
		}
	});
	mnAdmin.add(gestionCat);
	
	JMenuItem gestionMot = new JMenuItem("Gestion des Mots Cles");

	gestionMot.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			gestion  ia = new gestion();
			ia.setSize(1200,900);
			ia.setTitle("Gestion des Mots Cles");
			ia.setVisible(true);
		}
	});
	mnAdmin.add(gestionMot);
}



mnCategorie = new JMenu("Categorie");
menuBar.add(mnCategorie);

g = new Geste();
ArrayList<String> lesCategories = g.chargerCategorie();
for(String s : lesCategories)	{
	JMenuItem menu = new JMenuItem(s);

	menu.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			ArrayList<String> lesgeste = new ArrayList<>();
			String rec = menu.getText();

			try {
				lesgeste = g.getNomGesteCategorie(rec);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			affichertri(lesgeste);
		}
	});
	mnCategorie.add(menu);
}


mnAide = new JMenu("Aide");
menuBar.add(mnAide);

JMenuItem aide = new JMenuItem("Lexique");

aide.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) 
	{
		//InterfaceAjoutModification  ia = new InterfaceAjoutModification();
		//ia.setTitle("Test");
		//ia.setVisible(true);
	}
});
mnAide.add(aide);

JMenuItem guide = new JMenuItem("Guide d'utilisation");

guide.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) 
	{
		//InterfaceAjoutModification  ia = new InterfaceAjoutModification();
		//ia.setTitle("Test");
		//ia.setVisible(true);
	}
});
mnAide.add(guide);



//############################################ Fin Barre de Menu #############################################################

// ######### Liste Déroulante (ListSelectionModele) ###############"



afficherListeGeste();

jlist2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );

JScrollPane scrollPane2 = new JScrollPane(jlist2);

jlist2.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent evt) {
        JList list = (JList)evt.getSource();
        if (evt.getClickCount() == 2) {

            // Double-click detected
            int index = list.locationToIndex(evt.getPoint());
            nomGeste = model.getElementAt(index);      
            try {
				chargerGeste(nomGeste);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
});


//######### Fin Liste Déroulante (ListSelectionModele) ###############"


textField = new JTextField();
textField.setColumns(10);

textField.addKeyListener(new KeyAdapter() { /// recherche par mot cle
    public void keyReleased(KeyEvent e) {
      JTextField textField = (JTextField) e.getSource();
      String text = textField.getText();
      //textField.setText(text.toUpperCase());
      g = new Geste();
  	  ArrayList<String> lesGeste = new ArrayList<>();

	try {
		lesGeste = g.getNomGesteRecherche(text);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	affichertri(lesGeste);
	
    }
});

textArea = new JTextArea(5, 20);
textArea.setEditable(false);


bouton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) 
	{
		if(nomvideo.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Selectionnez d'abord un geste technique ! ","Probleme",JOptionPane.PLAIN_MESSAGE);
		}
		else {
			Video  video = new Video(""+nomvideo,0,0,true); //Mettre le nom de la video grace a l'id video (depuis bdd)
			video.setTitle("Test");
			video.setVisible(true);
		}
	}
});


/*Initialisation du JFrame*/
Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
setBounds(10,10,1000, 600);


//On ne pourra pas agrandir la fenetre intitulée.
this.setResizable (false); 						  //Peux pas resize
this.setDefaultCloseOperation(EXIT_ON_CLOSE); // a enlever apres car sinon ferme tout le programme en ferant la fenetre de connexion

/*Récupération du ContentPane*/
Container contenu = this.getContentPane();

/*Création des JPanel avec leur Layout Manager*/
panelGauche = new JPanel(new GridLayout (3,1));
panelDroite = new JPanel(new GridLayout (4,1)); //(Lignes , Colonnes)
//panelCentre = new JPanel(new GridLayout (0,1));
//panelHaut = new JPanel(new GridLayout (1,1));
//panelBas = new JPanel(new GridLayout (1,1));


/*Ajout des panneaux au ContentPane*/
   // contenu.add(panelHaut, BorderLayout.NORTH);
   // contenu.add(panelBas, BorderLayout.SOUTH);
    contenu.add(panelGauche, BorderLayout.WEST);
    contenu.add(panelDroite);
   // contenu.add(panelCentre, BorderLayout.CENTER);

    /*Ajout du formulaire de connexion à panelCentre*/
    panelDroite.setBorder(new TitledBorder("Espace Vidéo"));
    panelGauche.setBorder(new TitledBorder("Espace Recherche"));
    
    panelDroite.add(NomVideo); //Titre Video
    panelDroite.add(bouton);//Video 
    panelDroite.add(textArea);//description   
    panelDroite.add(image);
    
    panelGauche.add(imageLogo); 
    panelGauche.add(textField);//Barre de recherche
    panelGauche.add(scrollPane2);


panelDroite.setPreferredSize(new Dimension(600,400));
panelGauche.setPreferredSize(new Dimension(600,400));

   // pack(); //(Necessaire?)
  
	}
}
