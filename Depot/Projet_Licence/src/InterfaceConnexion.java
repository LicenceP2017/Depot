import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class InterfaceConnexion extends JFrame {
/**
 * 
 */
private static final long serialVersionUID = 1L;

JPanel panelHaut, panelBas, panelGauche, panelDroite, panelCentre, panelConnexion;

JLabel user = new JLabel("Login");
JLabel mdp = new JLabel("Mot de passe");
JButton valider = new JButton("Valider");
//JButton annuler = new JButton("Annuler");
JTextField user_txt = new JTextField();
JPasswordField mdp_txt = new JPasswordField();

//static JTextField user, mdp;


public String returnUSER()
{
	return user_txt.getText();
}

@SuppressWarnings("deprecation")
public String returnMDP()
{
	return mdp_txt.getText();
}
    
    public InterfaceConnexion() {

super();

/*Initialisation du JFrame*/
this.setSize (new Dimension (600,400));
//On ne pourra pas agrandir la fenetre intitulée.
this.setResizable (false);

/*Récupération du ContentPane*/
Container contenu = this.getContentPane();

/*Création des JPanel avec leur Layout Manager*/
panelHaut = new JPanel(new GridLayout (1,1));
panelBas = new JPanel(new GridLayout (1,1));
panelGauche = new JPanel(new GridLayout (1,1));
panelDroite = new JPanel(new GridLayout (1,1));
panelCentre = new JPanel(new GridLayout (0,1));
panelConnexion = new JPanel();

/*Ajout des panneaux au ContentPane*/
    contenu.add(panelHaut, BorderLayout.NORTH);
    contenu.add(panelBas, BorderLayout.SOUTH);
    contenu.add(panelGauche, BorderLayout.EAST);
    contenu.add(panelDroite, BorderLayout.WEST);
    contenu.add(panelCentre, BorderLayout.CENTER);

    /*Ajout du formulaire de connexion à panelCentre*/
    panelCentre.setBorder(new TitledBorder("Connexion"));
    panelCentre.add(panelConnexion);
    panelCentre.add(user);
    panelCentre.add(user_txt);
    panelCentre.add(mdp);          
    panelCentre.add(mdp_txt);
    panelCentre.add(valider);
        valider.addActionListener(new Authentification(this));
    //panelCentre.add(annuler);
    
    /*Ajout du texte de panelConnexion*/
    panelConnexion.add(new JLabel ("CONNEXION"));
}

}


