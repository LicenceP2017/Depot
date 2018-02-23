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
												
//Objet pour se connecter à la base de données
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
//Création de la requète
	statement = conn.prepareStatement("SELECT nom_utilisateur, prenom_utilisateur FROM utilisateurs WHERE login_utilisateur = '"+login+"' And mdp_utilisateur ='"+password+"'");
																																						
	resultat = statement.executeQuery();

if(resultat.next())
{
JOptionPane.showMessageDialog(null,"Connexion réussie ! ","Success",JOptionPane.PLAIN_MESSAGE);
}
else {
JOptionPane.showMessageDialog(null,"Identifiants incorects! ","Error",1);
}
classGeste = new Geste();






conn.close();
}
catch (SQLException e) {
System.out.println(e.getMessage());
}
}
}