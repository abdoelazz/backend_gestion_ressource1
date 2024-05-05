package model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Fournisseur extends User {

	@Column(name = "adresse")
	private String adresse;

	@Column(name = "etatFournisseur")
	private String etatFournisseur;

	@Column(name = "gerant")
	private String gerant;

	@Column(name = "lieu")
	private String lieu;

	@Column(name = "scorePanne")
	private int scorePanne;

	@Column(name = "siteInternet")
	private String siteInternet;

	@Column(name = "societe")
	private String societe;

	@OneToMany(mappedBy = "fournisseur")
	private List<Proposition> propositions;

	public Fournisseur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Fournisseur(String adresse, String etatFournisseur, String gerant, String lieu, int scorePanne,
			String siteInternet, String societe, List<Proposition> propositions) {
		super();
		this.adresse = adresse;
		this.etatFournisseur = etatFournisseur;
		this.gerant = gerant;
		this.lieu = lieu;
		this.scorePanne = scorePanne;
		this.siteInternet = siteInternet;
		this.societe = societe;
		this.propositions = propositions;
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

	public List<Proposition> getPropositions() {
		return propositions;
	}

	public void setPropositions(List<Proposition> propositions) {
		this.propositions = propositions;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

}
