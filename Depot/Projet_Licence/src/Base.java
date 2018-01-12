import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;

class Base {

//Variable pour la connexion	
public Connection conn;

public void ConnexionBD()
{
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
}
catch(Exception ex)
{ 
System.out.print("Erreur de Chargement");
System.exit(0);
}
try
{
String url = "jdbc:mysql://localhost/TP1?autoReconnect=true&useSSL=false";
String user = "root";
String passwd = "root";
																					out.println("Ca fonctionne");
conn = DriverManager.getConnection(url, user, passwd);
}
catch(Exception ex)
{
System.out.print("Erreur de connexion à la BD");
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