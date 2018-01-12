// import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

	
	static String url = "jdbc:mysql://localhost/TP1?autoReconnect=true&useSSL=false";
	static String login = "root";
	static String passwd = "root";
	
	
	// public static void main(String[] args) {
	//	out.print("debut programme\n");
	//	lireEnBase();
	// }
	
	public static void sauverEnBase(String personne) {

		// Information d'accès à la base de données
		
		Connection cn =null;
		Statement st =null;

		try {

			// Etape 1 : Chargement du driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Etape 2 : récupération de la connexion
			cn = DriverManager.getConnection(url, login, passwd);

			// Etape 3 : Création d'un statement
			st = cn.createStatement();

			String sql = "INSERT INTO `javadb` (`personne`) VALUES ('"
					+ personne + "')";

			// Etape 4 : exécution requéte
			st.executeUpdate(sql);

			// Si récup données alors étapes 5 (parcours Resultset)

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
			// Etape 6 : libérer ressources de la mémoire.
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void lireEnBase() {

		System.out.println("methode lire en base");
		// Information d'accès à la base de données
		
		Connection cn =null;
		Statement st =null;
		ResultSet rs =null;
		
		try {

			// Etape 1 : Chargement du driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("etape 1");

			// Etape 2 : récupération de la connexion
			cn = DriverManager.getConnection(url, login, passwd);
			System.out.println("etape 2");
			
			// Etape 3 : Création d'un statement
			st = cn.createStatement();

			String sql = "SELECT * FROM Client";
			System.out.println("etape 3");

			// Etape 4 : exécution requéte
			rs = st.executeQuery(sql);
			System.out.println("etape 4");

			// Si récup données alors étapes 5 (parcours Resultset)

			while (rs.next()) {
				System.out.println(rs.getString("Nom_Client"));				
			}
			System.out.println("etape 5");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
			// Etape 6 : libérer ressources de la mémoire.
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
	