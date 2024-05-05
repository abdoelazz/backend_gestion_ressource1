package dto;

public class FournisseurDTO {
	private String societe;
	private String password;
	private String adresse;
	private String etatFournisseur;
	private String gerant;
	private String lieu;
	private int scorePanne;
	private String siteInternet;

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEtatFournisseur() {
		return etatFournisseur;
	}

	public void setEtatFournisseur(String etatFournisseur) {
		this.etatFournisseur = etatFournisseur;
	}

	public String getGerant() {
		return gerant;
	}

	public void setGerant(String gerant) {
		this.gerant = gerant;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public int getScorePanne() {
		return scorePanne;
	}

	public void setScorePanne(int scorePanne) {
		this.scorePanne = scorePanne;
	}

	public String getSiteInternet() {
		return siteInternet;
	}

	public void setSiteInternet(String siteInternet) {
		this.siteInternet = siteInternet;
	}

}
