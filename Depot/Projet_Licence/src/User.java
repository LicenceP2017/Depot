
public class User {

	private String nom;
	private String prenom;
	private int droit;
	
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getDroit() {
		return droit;
	}
	public void setDroit(int droit) {
		this.droit = droit;
	}
	
	public User() {}
	
	public User(String prenom, String nom, int droit)
	{
		this.prenom = prenom;
		this.nom = nom;
		this.droit = droit;
	}
	
	
}
