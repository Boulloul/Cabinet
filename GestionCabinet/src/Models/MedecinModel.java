package Models;

public class MedecinModel {
	private int ID;
	private int Version;
	private String Titre;
	private String Nom;
	private String Prenom;
	//CTOR
	public MedecinModel() {}
	
	public MedecinModel(int iD, int version, String titre, String nom, String prenom) {
		ID = iD;
		Version = version;
		Titre = titre;
		Nom = nom;
		Prenom = prenom;
	}
	
	
	//getters Seteres
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getVersion() {
		return Version;
	}
	public void setVersion(int version) {
		Version = version;
	}
	public String getTitre() {
		return Titre;
	}
	public void setTitre(String titre) {
		Titre = titre;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	
}
