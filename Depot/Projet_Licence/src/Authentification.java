//import static java.lang.System.out;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

class Authentification implements ActionListener {
												
//Objet pour se connecter � la base de donn�es
public Base b = new Base();
public Connection conn;
private InterfaceConnexion IC;

public Geste classGeste;


static JTextField user, mdp;

	//Objet PreparedStatement
    PreparedStatement statement = null;
    //Objet ResultSet
    ResultSet resultat = null;

public Authentification(InterfaceConnexion IC) {
	this.IC=IC;

}

public void actionPerformed(ActionEvent ae)
{
		String login = IC.returnUSER();
															
        String password = IC.returnMDP();
        
        b.ConnexionBD();
        conn = b.getConnect();

//Manipulation
try {
//Cr�ation de la requ�te
	statement = conn.prepareStatement("SELECT nom_utilisateur, prenom_utilisateur FROM utilisateurs WHERE login_utilisateur = '"+login+"' And mdp_utilisateur ='"+password+"'");
																					
	System.out.println(statement); //� supprimer

																					
	resultat = statement.executeQuery();
	
	System.out.println(resultat); //� supprimer

if(resultat.next())
{
JOptionPane.showMessageDialog(null,"Connexion r�ussie ! ","Success",JOptionPane.PLAIN_MESSAGE);
}
else {
JOptionPane.showMessageDialog(null,"Identifiants incorects! ","Error",1);
}
	classGeste = new Geste();

	classGeste.getNomGeste(); // recup�re la liste de tous les nom geste pour la liste d�roulante

	classGeste.recupGeste("tirer"); // r�cup�ration des donn�es du geste "tirer" de la base
	
	classGeste.ungeste.difficulte = 3; // test de la modification du niveau de difficult�
	classGeste.ungeste.nomGeste = "pointer"; // test de la modification du nom du geste
	
	classGeste.modifGeste(); // enregistre les modifications si dessus

	classGeste.recupGeste("tirer"); // recharge les donn�es, pour l'actualisation



conn.close();
}
catch (SQLException e) {
System.out.println(e.getMessage());
}
}
}