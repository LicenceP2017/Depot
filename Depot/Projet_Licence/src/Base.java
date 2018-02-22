import java.sql.Connection;
import java.sql.DriverManager;

class Base {

//Variable pour la connexion	
public Connection conn;

public void ConnexionBD()
{
try
{
	Class.forName("com.mysql.jdbc.Driver");
}
catch(Exception ex)
{ 
	System.out.print("Erreur de Chargement");
	System.exit(0);
}
try
{
	String url = "jdbc:mysql://localhost/projetlicence?useSSL=false";
	String user = "root";
	String passwd = "root";
																					
	
	conn = DriverManager.getConnection(url, user, passwd);
}
catch(Exception ex)
{
	System.out.println("Erreur de connexion Ã  la BD");
	ex.printStackTrace();
}
}

public Connection getConnect()
{
	return conn;
}

public void DeconnexionBD()
{
	try {
		conn.close();
	}
	catch(Exception ex) {
		System.out.print("Déconnexion impossible");
	}
}
}