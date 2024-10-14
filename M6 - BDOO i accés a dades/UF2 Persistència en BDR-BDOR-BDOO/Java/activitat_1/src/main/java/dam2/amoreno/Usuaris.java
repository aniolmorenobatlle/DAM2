package dam2.amoreno;

public class Usuaris {

	private int id;
	private String nom;
	private String cognoms;
	private String data_naixement;
	private String email;


	public Usuaris() {}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getCognoms() {
		return cognoms;
	}


	public void setCognoms(String cognoms) {
		this.cognoms = cognoms;
	}


	public String getData_naixement() {
		return data_naixement;
	}


	public void setData_naixement(String data_naixement) {
		this.data_naixement = data_naixement;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
}
