import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Geste {

	/*
	 Sommaire :
	 	nouvelleConnexion : permet de rouvrir une connexion dans le but d'interrogé la base
	 	Geste(): un constructeur du geste technique vide
	 	Geste(...) : cette fois ci avec les parametres
	 	getNomGeste() : permet de récupérer dans une liste tous les nom des gestes techniques
	 	recupGeste(String nomGeste) : récupère de la base toutes les données pour un geste, permet l'affichage
		modifGeste() : enregistre les changement sur l'objet geste dans la classe              Attention : créer un test pour le nom du geste
		nouveauGeste(..) : enregistre un nouveau geste dans la base, ne prend pas d'id, auto increment dans la base
		supprGeste() : supprime le geste actuellement chargé
		recupIdGeste(String nom) : récupere l'id du geste technique du nom passé en paramètre
		nouveauMotCle(String m) : créer un nouveau mot clé
		lieMotCleEtGeste(String m, int id) : insere dans la table correspond l'id du geste et du mot clé, prend en parametre le mot clé et l'id du geste associé
		
	
	*/
	
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
		
		motCle = new ArrayList<>();
		while(resultat.next()){ 
			String nom = resultat.getString("nom_motcle");
			motCle.add(nom);
		}
		
		nouvelleConnexion("SELECT nom_image FROM image AS i INNER JOIN illustre AS g ON i.id_image = g.id_image WHERE id_geste = '"+ungeste.idGeste+"'");
		resultat = statement.executeQuery();
		
		images = new ArrayList<>();
		while(resultat.next()){ 
			String nom = resultat.getString("nom_image");
			images.add(nom);
		}
		
		nouvelleConnexion("SELECT nom_categorie FROM categorie AS c INNER JOIN possede AS g ON c.id_categorie = g.id_categorie WHERE id_geste = '"+ungeste.idGeste+"'");
		resultat = statement.executeQuery();
		
		categories = new ArrayList<>();
		while(resultat.next()){ 
			String nom = resultat.getString("nom_categorie");
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
	
	public void nouveauGeste(String nom, String av, String in, String ex, int dif, String val, int vi) throws SQLException // permet de modifier un geste dans la base
	{
		if(listeNomGeste.contains(nom))
		{
			JOptionPane.showMessageDialog(null,"nom du geste technique deja existant ","Probleme",JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			nouvelleConnexion("INSERT INTO geste_technique"
				+ " (`nom_geste`, `avantage`, `inconvenient`, `explication`, `niveau_difficulte`, `valeur`, `id_video`) "
				+ "VALUES ('"+nom+"','"+av+"','"+in+"','"+ex+"','"+dif+"','"+val+"','"+vi+"')");
		
			statement.executeUpdate();
		}
	}
	
	public void supprGeste() throws SQLException // permet de supprimer un geste de la base
	{
		nouvelleConnexion("DELETE FROM geste_technique WHERE id_geste = '"+ungeste.idGeste+"'");
		statement.executeUpdate();
	}
	
	public int recupIdGeste(String nom) throws SQLException
	{
		int res = 0;
		
		nouvelleConnexion("SELECT id_geste FROM geste_technique WHERE nom_geste = '"+nom+"'");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			res = resultat.getInt("id_geste");
		}
		if(res == 0) {
		
			JOptionPane.showMessageDialog(null,"Nom du geste technique introuvable ","Probleme",JOptionPane.PLAIN_MESSAGE);
		}
		return res;
		
	}
	
	
	public void nouveauMotCle(String m) throws SQLException
	{
		ArrayList<String> toutLesMotsCle = new ArrayList<>();
		
		nouvelleConnexion("SELECT nom_motcle FROM mot_cle");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			String nom = resultat.getString("nom_motcle");
			toutLesMotsCle.add(nom);
		}
		
		if(toutLesMotsCle.contains(m)) {
			JOptionPane.showMessageDialog(null,"mot cle deja existant ","Probleme",JOptionPane.PLAIN_MESSAGE);
		}
		else {
			nouvelleConnexion("INSERT INTO mot_cle (`nom_motcle`) VALUES ('"+m+"')");
			statement.executeUpdate();
		}
	}
	
	public void lieMotCleEtGeste(String m, int id) throws SQLException // prend en parametre le mot clé et l'id du geste associé
	{ // permet de faire le lien entre le motcle et le geste dans la table correspond
		
		int res = 0;
		
		nouvelleConnexion("SELECT id_motcle FROM mot_cle WHERE nom_motcle = '"+m+"'");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			res = resultat.getInt("id_motcle");
		}
		if(res != 0)
		{
			nouvelleConnexion("INSERT INTO correspond (`id_motcle`, `id_geste`) VALUES ('"+res+"','"+m+"')");
			statement.executeUpdate();
		}
		else
		{
			JOptionPane.showMessageDialog(null,"identifiants du mot Cle introuvable ","Probleme",JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public void nouvelleCategorie(String m) throws SQLException
	{
		ArrayList<String> toutesLesCategories = new ArrayList<>();
		
		nouvelleConnexion("SELECT nom_categorie FROM categorie");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			String nom = resultat.getString("nom_categorie");
			toutesLesCategories.add(nom);
		}
		
		if(toutesLesCategories.contains(m)) {
			JOptionPane.showMessageDialog(null,"Categorie deja existante ","Probleme",JOptionPane.PLAIN_MESSAGE);
		}
		else {
			nouvelleConnexion("INSERT INTO categorie (`nom_categorie`) VALUES ('"+m+"')");
			statement.executeUpdate();
		}
	}
	
	public void lieCategorieEtGeste(String m, int id) throws SQLException // prend en parametre la categorie et l'id du geste associé
	{ // permet de faire le lien entre la categorie et le geste dans la table possede
		
		int res = 0;
		
		nouvelleConnexion("SELECT id_categorie FROM categorie WHERE nom_categorie = '"+m+"'");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			res = resultat.getInt("id_categorie");
		}
		if(res != 0)
		{
			nouvelleConnexion("INSERT INTO possede (`id_categorie`, `id_geste`) VALUES ('"+res+"','"+m+"')");
			statement.executeUpdate();
		}
		else
		{
			JOptionPane.showMessageDialog(null,"identifiants du mot Cle introuvable ","Probleme",JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	
	

}
