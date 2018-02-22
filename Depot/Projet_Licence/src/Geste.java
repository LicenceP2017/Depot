import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Geste {

	
	int idGeste;
	String nomGeste;
	String avantage;
	String inconvenient;
	String explication;
	int difficulte;
	String valeur;
	int idvideo;
	ArrayList<String> motCle;
	ArrayList<String> images;
	ArrayList<String> categories;
	
	
	ArrayList<String> listeNomGeste = new ArrayList<>();
	Geste ungeste;
	
	
	
	public Base b = new Base();
	public Connection conn;
    PreparedStatement statement = null;	//Objet PreparedStatement
    ResultSet resultat = null;    //Objet ResultSet
	
    
    private void nouvelleConnexion(String sql) throws SQLException // creer la connexion base et execute la requete passé en paramètre
    {    	
    	b.ConnexionBD();
        conn = b.getConnect();
		statement = conn.prepareStatement(sql);
		
		System.out.println(statement); //à  supprimer
		
		//resultat = statement.executeQuery();    ne fonctionne que sur les requetes sql contenant un Select
		
    }
	
	public Geste()	{	}
	public Geste(int id, String nom, String av, String in, String ex, int dif, String val, int vi) {
		idGeste = id;
		nomGeste = nom;
		avantage = av;
		inconvenient = in;
		explication = ex;
		difficulte = dif;
		valeur = val;
		idvideo = vi;
	}
	
	
	
	
	public void getNomGeste() throws SQLException // permet de charger la liste des nom des geste pour la liste déroulante
	{
		
		nouvelleConnexion("SELECT nom_geste FROM geste_technique");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			String nom = resultat.getString("nom_geste");
			listeNomGeste.add(nom);
		}
		
		System.out.println(listeNomGeste); //à  supprimer
	}
	
	
	
	public void recupGeste(String nomGeste) throws SQLException // permet de récupérer un geste pour l'affichage 
	{
		nouvelleConnexion("SELECT * FROM geste_technique WHERE nom_geste = '"+nomGeste+"'");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			ungeste = new Geste(
					resultat.getInt("id_geste")
					,resultat.getString("nom_geste")
					,resultat.getString("avantage")
					,resultat.getString("inconvenient")
					,resultat.getString("explication")
					,resultat.getInt("niveau_difficulte")
					,resultat.getString("valeur")
					,resultat.getInt("id_video"));
			
			
		}
		
		nouvelleConnexion("SELECT nom_motcle FROM mot_cle AS m INNER JOIN correspond AS c ON m.id_motcle = c.id_motcle WHERE id_geste = "+ungeste.idGeste+"");
		resultat = statement.executeQuery();

		while(resultat.next()){ 
			String nom = resultat.getString("nom_motcle");
			motCle = new ArrayList<>();
			motCle.add(nom);
		}
		
		nouvelleConnexion("SELECT nom_image FROM image AS i INNER JOIN illustre AS g ON i.id_image = g.id_image WHERE id_geste = '"+ungeste.idGeste+"'");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			String nom = resultat.getString("nom_image");
			images = new ArrayList<>();
			images.add(nom);
		}
		
		nouvelleConnexion("SELECT nom_categorie FROM categorie AS c INNER JOIN possede AS g ON c.id_categorie = g.id_categorie WHERE id_geste = '"+ungeste.idGeste+"'");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			String nom = resultat.getString("nom_categorie");
			categories = new ArrayList<>();
			categories.add(nom);
		}
		
		System.out.println(ungeste.idGeste);
		System.out.println(ungeste.nomGeste);
		System.out.println(ungeste.avantage);
		System.out.println(ungeste.inconvenient);
		System.out.println(ungeste.explication);
		System.out.println(ungeste.difficulte);
		System.out.println(ungeste.valeur);
		System.out.println(ungeste.idvideo);
		
	}
	
	public void modifGeste() throws SQLException // permet de modifier un geste dans la base
	{
		nouvelleConnexion("UPDATE geste_technique SET nom_geste = '"+ungeste.nomGeste
				+"', avantage = '"+ungeste.avantage
				+"', inconvenient = '"+ungeste.inconvenient
				+"', explication = '"+ungeste.explication
				+"', niveau_difficulte = '"+ungeste.difficulte
				+"', valeur = '"+ungeste.valeur
				+"', id_video = '"+ungeste.idvideo
				+"' WHERE id_geste = "+ungeste.idGeste+"");
		
		statement.executeUpdate();
		
		
	}
	
	public void supprGeste() throws SQLException // permet de supprimer un geste de la base
	{
		nouvelleConnexion("DELETE FROM geste_technique WHERE id_geste =  '"+ungeste.idGeste+"'");
	}
	
	
	
	

}
