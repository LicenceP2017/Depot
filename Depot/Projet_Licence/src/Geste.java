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
	 	getNomGesteRecherche(String rec) : meme chose mais avec la recherche
	 	getNomGesteCategorie(String rec) : meme chose avec les categories
	 	recupGeste(String nomGeste) : récupère de la base toutes les données pour un geste, permet l'affichage
	 	recupMotCle(String nomGeste) recupere les mot clé d'un geste
	 	toutMotCle() : recupere tous les mots cle
	 	recupImage(String nomGeste) recupere les image pour un geste
	 	recupCategorie(String nomGeste) recupere les categories
	 	toutesCategorie() :  recupere toutes les categories de la base
	 	recupVideo(int id) recupere le nom de la video en fonction de son id
		modifGeste() : enregistre les changement sur l'objet geste dans la classe              Attention : créer un test pour le nom du geste
		nouveauGeste(..) : enregistre un nouveau geste dans la base, ne prend pas d'id, auto increment dans la base
		supprGeste() : supprime le geste actuellement chargé
		nouveauMotCle(String m) : créer un nouveau mot clé
		nouvelleCategorie(String m) : créer nouvelle catégorie
		nouvelleImage(String m) : créer nouvelle image
		nouvelleVideo(String m) creer une nouvelle video
		lieMotCleEtGeste(String mot, String gest) : insere dans la table correspond l'id du geste et du mot clé, prend en parametre le mot clé et le nom du geste associé
		deleteMotCleEtGeste
		lieCategorieEtGeste(String cat, String gest )
		deleteCategorieEtGeste
		lieImageEtGeste(String im, String gest)
		deleteImageEtGeste
		retIdGeste(String nom) : retourne l'id du nom du geste passé en param
		retIdCategorie(String nom) //
		retIdMotCle(String nom) //
		retIdImage(String nom) //
		retIdVideo(String nom) //
		existMotCle(String m)  boolean qui retourne true si le nom passé en param existe déja
		existCategorie(String m)
		existImage(String m)
		existVideo(String m)
		existGeste(String m)
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
	String nomVideo;
	
	
	ArrayList<String> listeNomGeste;
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
	
	
	
	
	public ArrayList<String> getNomGeste() throws SQLException // permet de charger la liste des nom des geste pour la liste déroulante
	{
		listeNomGeste = new ArrayList<>();
		nouvelleConnexion("SELECT nom_geste FROM geste_technique");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			String nom = resultat.getString("nom_geste");
			listeNomGeste.add(nom);
		}
		
		return listeNomGeste;
	}
	
	public ArrayList<String> getNomGesteRecherche(String rec) throws SQLException // permet de charger la liste des nom des geste pour la liste déroulante
	{
		listeNomGeste = new ArrayList<>();
		ArrayList<Integer> idMotCle = new ArrayList<>();
		ArrayList<Integer> idGeste = new ArrayList<>();
		
		nouvelleConnexion("SELECT m.id_motcle FROM mot_cle AS m INNER JOIN correspond AS c ON m.id_motcle = c.id_motcle WHERE nom_motcle LIKE '%"+rec+"%'");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			int ret = resultat.getInt("id_motcle");
			idMotCle.add(ret);
		}
		
		for(int id : idMotCle)
		{
			nouvelleConnexion("SELECT id_geste FROM correspond WHERE id_motcle = "+ id +"");
			resultat = statement.executeQuery();
			
			while(resultat.next()){ 
				int ret = resultat.getInt("id_geste");
				if(!idGeste.contains(ret))	{ idGeste.add(ret); }
			}
		}
		for(int id : idGeste)
		{
			nouvelleConnexion("SELECT nom_geste FROM geste_technique WHERE id_geste = "+ id +"");
			resultat = statement.executeQuery();
			while(resultat.next()){ 
				String nom = resultat.getString("nom_geste");
				listeNomGeste.add(nom);
			}
		}
		return listeNomGeste;
	}
	
	public ArrayList<String> getNomGesteCategorie(String rec) throws SQLException // permet de charger la liste des nom des geste pour la liste déroulante
	{
		listeNomGeste = new ArrayList<>();
		ArrayList<Integer> idCategorie = new ArrayList<>();
		ArrayList<Integer> idGeste = new ArrayList<>();
		
		nouvelleConnexion("SELECT m.id_categorie FROM categorie AS m INNER JOIN possede AS c ON m.id_categorie = c.id_categorie WHERE nom_categorie LIKE '%"+rec+"%'");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			int ret = resultat.getInt("id_categorie");
			idCategorie.add(ret);
		}
		
		for(int id : idCategorie)
		{
			nouvelleConnexion("SELECT id_geste FROM possede WHERE id_categorie = "+ id +"");
			resultat = statement.executeQuery();
			
			while(resultat.next()){ 
				int ret = resultat.getInt("id_geste");
				if(!idGeste.contains(ret))	{ idGeste.add(ret); }
			}
		}
		for(int id : idGeste)
		{
			nouvelleConnexion("SELECT nom_geste FROM geste_technique WHERE id_geste = "+ id +"");
			resultat = statement.executeQuery();
			while(resultat.next()){ 
				String nom = resultat.getString("nom_geste");
				listeNomGeste.add(nom);
			}
		}
		return listeNomGeste;
	}
	
	
	
	public Geste recupGeste(String nomGeste) throws SQLException // permet de récupérer un geste pour l'affichage 
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
		return ungeste;
	}
	
	
	public ArrayList<String> recupMotCle(int idgeste) throws SQLException
	{
		nouvelleConnexion("SELECT nom_motcle FROM mot_cle AS m INNER JOIN correspond AS c ON m.id_motcle = c.id_motcle WHERE id_geste = "+idgeste+"");
		resultat = statement.executeQuery();
		
		motCle = new ArrayList<>();
		while(resultat.next()){ 
			String nom = resultat.getString("nom_motcle");
			motCle.add(nom);
		}
		
		return motCle;
	}
	public ArrayList<String> toutMotCle() throws SQLException
	{
		nouvelleConnexion("SELECT nom_motcle FROM mot_cle");
		resultat = statement.executeQuery();
		
		motCle = new ArrayList<>();
		while(resultat.next()){ 
			String nom = resultat.getString("nom_motcle");
			motCle.add(nom);
		}
		
		return motCle;
	}
	
	public ArrayList<String> recupImage(int idgeste) throws SQLException
	{
		nouvelleConnexion("SELECT nom_image FROM image AS i INNER JOIN illustre AS g ON i.id_image = g.id_image WHERE id_geste = '"+idgeste+"'");
		resultat = statement.executeQuery();
		
		images = new ArrayList<>();
		while(resultat.next()){ 
			String nom = resultat.getString("nom_image");
			images.add(nom);
		}
		return images;
	}
	
	public String recupCategorie(int idgeste) throws SQLException
	{
		nouvelleConnexion("SELECT nom_categorie FROM categorie AS c INNER JOIN possede AS g ON c.id_categorie = g.id_categorie WHERE id_geste = '"+idgeste+"'");
		resultat = statement.executeQuery();
		
		String nom = "";
		while(resultat.next()){ 
			nom = resultat.getString("nom_categorie");
		}
		return nom;
	}
	
	public ArrayList<String> toutesCategorie() throws SQLException
	{
		nouvelleConnexion("SELECT nom_categorie FROM categorie");
		resultat = statement.executeQuery();
		
		categories = new ArrayList<>();
		while(resultat.next()){ 
			String nom = resultat.getString("nom_categorie");
			categories.add(nom);
		}
		return categories;
	}
	
	public ArrayList<String> chargerCategorie() throws SQLException
	{
		nouvelleConnexion("SELECT nom_categorie FROM categorie");
		resultat = statement.executeQuery();
		
		categories = new ArrayList<>();
		while(resultat.next()){ 
			String nom = resultat.getString("nom_categorie");
			categories.add(nom);
		}
		return categories;
	}
	
	public String recupVideo(int id) throws SQLException
	{
		nouvelleConnexion("SELECT nom_video FROM video WHERE id_video = '"+id+"'");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			nomVideo = resultat.getString("nom_video");
		}
		return nomVideo;
	}
	
	
	public void modifGeste(Geste g) throws SQLException // permet de modifier un geste dans la base
	{
		nouvelleConnexion("UPDATE geste_technique SET nom_geste = '"+g.nomGeste
				+"', avantage = '"+g.avantage
				+"', inconvenient = '"+g.inconvenient
				+"', explication = '"+g.explication
				+"', niveau_difficulte = '"+g.difficulte
				+"', valeur = '"+g.valeur
				+"', id_video = '"+g.idvideo
				+"' WHERE id_geste = "+g.idGeste+"");
		
		statement.executeUpdate();
		
		
	}
	
	public void nouveauGeste(String nom, String av, String in, String ex, int dif, String val, int vi) throws SQLException // permet de modifier un geste dans la base
	{
		
		nouvelleConnexion("INSERT INTO geste_technique"
			+ " (`nom_geste`, `avantage`, `inconvenient`, `explication`, `niveau_difficulte`, `valeur`, `id_video`) "
			+ "VALUES ('"+nom+"','"+av+"','"+in+"','"+ex+"','"+dif+"','"+val+"','"+vi+"')");
		
		statement.executeUpdate();
		
	}
	
	public void supprGeste(Geste g) throws SQLException // permet de supprimer un geste de la base
	{
		nouvelleConnexion("DELETE FROM correspond WHERE id_geste = '"+g.idGeste+"'");
		statement.executeUpdate();
		
		nouvelleConnexion("DELETE FROM illustre WHERE id_geste = '"+g.idGeste+"'");
		statement.executeUpdate();
		
		nouvelleConnexion("DELETE FROM possede WHERE id_geste = '"+g.idGeste+"'");
		statement.executeUpdate();
		
		
		nouvelleConnexion("DELETE FROM geste_technique WHERE id_geste = '"+g.idGeste+"'");
		statement.executeUpdate();
	}
	
	
	
	
	public void nouveauMotCle(String m) throws SQLException
	{
		if(existMotCle(m) == true)	{	JOptionPane.showMessageDialog(null,"mot cle deja existant ","Probleme",JOptionPane.PLAIN_MESSAGE);	}
		else {
			nouvelleConnexion("INSERT INTO mot_cle (`nom_motcle`) VALUES ('"+m+"')");
			statement.executeUpdate();
		}
	}
	
	
	
	public void nouvelleCategorie(String m) throws SQLException
	{
		nouvelleConnexion("INSERT INTO categorie (`nom_categorie`) VALUES ('"+m+"')");
		statement.executeUpdate();
	}
	
	public void nouvelleImage(String m) throws SQLException
	{
		if(existImage(m) == true) {		JOptionPane.showMessageDialog(null,"Image deja existante ","Probleme",JOptionPane.PLAIN_MESSAGE); }
		
		else {
			nouvelleConnexion("INSERT INTO image (`nom_image`) VALUES ('"+m+"')");
			statement.executeUpdate();
		}
	}
	
	public void nouvelleVideo(String m) throws SQLException
	{
		if(existVideo(m) == true) {		JOptionPane.showMessageDialog(null,"Video deja existante ","Probleme",JOptionPane.PLAIN_MESSAGE); }
		
		else {
			nouvelleConnexion("INSERT INTO Video (`nom_video`) VALUES ('"+m+"')");
			statement.executeUpdate();
		}
	}
	
	
	//////////////////////////////////////////////////////////////////  crée les liens avec les autres tables
	
	
	public void lieMotCleEtGeste(String mot, String gest) throws SQLException // prend en parametre le mot clé et le geste à associer
	{ // permet de faire le lien entre le motcle et le geste dans la table correspond
		
		
		int motCle = retIdMotCle(mot);
		int geste = retIdGeste(gest);
		if(motCle != 0 && geste != 0)
		{
			nouvelleConnexion("INSERT INTO correspond (`id_motcle`, `id_geste`) VALUES ('"+motCle+"','"+geste+"')");
			statement.executeUpdate();
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Le lien entre "+mot+" et "+gest+" n'a pas abouti ","Probleme",JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public void deleteMotCleEtGeste(String gest) throws SQLException //
	{ 
		int geste = retIdGeste(gest);
		if(geste != 0)
		{
			nouvelleConnexion("DELETE FROM correspond WHERE id_geste = '"+geste+"'");
			statement.executeUpdate();
		}
		else
		{
			JOptionPane.showMessageDialog(null,"La suppression des mots clés n'a pas abouti ","Probleme",JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public void lieCategorieEtGeste(String cat, String gest ) throws SQLException // prend en parametre la categorie et le geste à associer
	{ // permet de faire le lien entre la categorie et le geste dans la table possede
		
		int categorie = retIdCategorie(cat);
		int geste = retIdGeste(gest);
		if(categorie != 0 && geste != 0)
		{
			nouvelleConnexion("INSERT INTO possede (`id_categorie`, `id_geste`) VALUES ('"+categorie+"','"+geste+"')");
			statement.executeUpdate();
		}
		else JOptionPane.showMessageDialog(null,"Le lien entre "+cat+" et "+gest+" n'a pas abouti ","Probleme",JOptionPane.PLAIN_MESSAGE);
		
	}
	
	public void deleteCategorieEtGeste(String cat, String gest ) throws SQLException // prend en parametre la categorie et le geste à associer
	{ // permet de faire le lien entre la categorie et le geste dans la table possede
		
		int categorie = retIdCategorie(cat);
		int geste = retIdGeste(gest);
		if(categorie != 0 && geste != 0)
		{
			nouvelleConnexion("DELETE FROM possede WHERE id_geste = '"+geste+"' AND id_categorie = '"+categorie+"'");
			statement.executeUpdate();
		}
		else JOptionPane.showMessageDialog(null,"Le lien entre "+cat+" et "+gest+" n'a pas abouti ","Probleme",JOptionPane.PLAIN_MESSAGE);
		
	}
	
	
	public void lieImageEtGeste(String im, String gest ) throws SQLException // prend en parametre le nom de l'image et le geste à associer
	{ // permet de faire le lien entre l'image et le geste dans la table illustre
		
		int image = retIdImage(im);
		int geste = retIdGeste(gest);
		if(image != 0 && geste != 0)
		{
			nouvelleConnexion("INSERT INTO illustre (`id_geste`, `id_image`) VALUES ('"+geste+"','"+image+"')");
			statement.executeUpdate();
		}
		else JOptionPane.showMessageDialog(null,"Le lien entre "+im+" et "+gest+" n'a pas abouti ","Probleme",JOptionPane.PLAIN_MESSAGE);
		
	}
	//////////////////////////////////////////////////////////////////// retourne les id en fonction des noms
	public int retIdGeste(String nom) throws SQLException
	{
		int res = 0;
		nouvelleConnexion("SELECT id_geste FROM geste_technique WHERE nom_geste = '"+nom+"'");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			res = resultat.getInt("id_geste");
		}
		if(res == 0) {	JOptionPane.showMessageDialog(null,"Nom du geste technique introuvable ","Probleme",JOptionPane.PLAIN_MESSAGE);	}
		
		return res;
		
	}
	
	public int retIdCategorie(String nom) throws SQLException
	{
		int res = 0;
		nouvelleConnexion("SELECT id_categorie FROM categorie WHERE nom_categorie = '"+nom+"'");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			res = resultat.getInt("id_categorie");
		}
		if(res == 0) {	JOptionPane.showMessageDialog(null,"Nom de la categorie introuvable ","Probleme",JOptionPane.PLAIN_MESSAGE);	}
		
		return res;
	}
	
	public int retIdMotCle(String nom) throws SQLException
	{
		int res = 0;
		nouvelleConnexion("SELECT id_motcle FROM mot_cle WHERE nom_motcle = '"+nom+"'");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			res = resultat.getInt("id_motcle");
		}
		if(res == 0) {	JOptionPane.showMessageDialog(null,"Nom du mot Cle introuvable ","Probleme",JOptionPane.PLAIN_MESSAGE);	}
		
		return res;
	}
	
	public int retIdImage(String nom) throws SQLException
	{
		int res = 0;
		nouvelleConnexion("SELECT id_image FROM image WHERE nom_image = '"+nom+"'");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			res = resultat.getInt("id_image");
		}
		if(res == 0) {	JOptionPane.showMessageDialog(null,"Nom de l'image introuvable ","Probleme",JOptionPane.PLAIN_MESSAGE);	}
		
		return res;
	}
	
	public int retIdVideo(String nom) throws SQLException
	{
		int res = 0;
		nouvelleConnexion("SELECT id_video FROM video WHERE nom_video = '"+nom+"'");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			res = resultat.getInt("id_video");
		}
		if(res == 0) {	JOptionPane.showMessageDialog(null,"Nom de la video introuvable ","Probleme",JOptionPane.PLAIN_MESSAGE);	}
		
		return res;
	}
	
	
	
	//////////////////////////////////////////////////////////////  test des duplicatas
	public boolean existMotCle(String m) throws SQLException
	{
		boolean test = false;
		ArrayList<String> toutLesMotsCle = new ArrayList<>();
		
		nouvelleConnexion("SELECT nom_motcle FROM mot_cle");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			String nom = resultat.getString("nom_motcle");
			toutLesMotsCle.add(nom);
		}
		
		if(toutLesMotsCle.contains(m)) {
			test = true;
		}
		return test;
	}
	
	public boolean existCategorie(String m) throws SQLException
	{
		boolean test = false;
		
		ArrayList<String> toutesLesCategories = new ArrayList<>();
		
		nouvelleConnexion("SELECT nom_categorie FROM categorie");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			String nom = resultat.getString("nom_categorie");
			toutesLesCategories.add(nom);
		}
		
		if(toutesLesCategories.contains(m)) {
			test = true;
		}
		return test;
	}
	
	public boolean existImage(String m) throws SQLException
	{
		boolean test = false;
		
		ArrayList<String> toutesLesImages = new ArrayList<>();
		
		nouvelleConnexion("SELECT nom_image FROM image");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			String nom = resultat.getString("nom_image");
			toutesLesImages.add(nom);
		}
		
		if(toutesLesImages.contains(m)) {
			test = true;
		}		
		return test;
	}
	
	public boolean existVideo(String m) throws SQLException
	{
		boolean test = false;
		
		ArrayList<String> toutesLesvideo = new ArrayList<>();
		
		nouvelleConnexion("SELECT nom_video FROM video");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			String nom = resultat.getString("nom_video");
			toutesLesvideo.add(nom);
		}
		
		if(toutesLesvideo.contains(m)) {
			test = true;
		}		
		return test;
	}
	
	public boolean existGeste(String m) throws SQLException
	{
		boolean test = false;
		
		ArrayList<String> toutesLesgeste = new ArrayList<>();
		
		nouvelleConnexion("SELECT nom_geste FROM geste_technique");
		resultat = statement.executeQuery();
		
		while(resultat.next()){ 
			String nom = resultat.getString("nom_geste");
			toutesLesgeste.add(nom);
		}
		
		if(toutesLesgeste.contains(m)) {
			test = true;
		}		
		return test;
	}
	
	

}
