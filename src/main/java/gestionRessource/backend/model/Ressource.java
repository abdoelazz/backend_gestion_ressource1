package gestionRessource.backend.model;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Ressource")
@Inheritance(strategy = InheritanceType.JOINED)
public class Ressource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "codeInventaire")
	private String codeInventaire;

	@Enumerated(EnumType.STRING)
	private EtatDemande etatDemande;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties({ "notifications", "password", "departement" })
	private User user;

	@ManyToOne
	@JoinColumn(name = "enseignant_id")
	@JsonIgnoreProperties({ "notifications", "password", "departement" })
	private User enseignant;

	@ManyToOne
	@JoinColumn(name = "appelDoffre_id")
	@JsonIgnoreProperties({ "ressources", "propositions" })
	private AppelDoffre appelDoffre;

	@ManyToOne
	@JoinColumn(name = "detail_id")
	@JsonIgnoreProperties({ "ressource", "proposition" })
	private Detail detail;

	@ManyToOne
	@JoinColumn(name = "departement_id")
	@JsonIgnoreProperties({ "ressources", "users" })
	private Departement departement;

	@OneToMany(mappedBy = "ressource")
	private List<Panne> pannes;

	@Column(name = "typeRessource")
	private String typeRessource;

	@Column(name = "dateCreation")
	private Date dateCreation;

	public Ressource() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ressource(Long id, String codeInventaire, EtatDemande etatDemande, User user, User enseignant,
			AppelDoffre appelDoffre, Detail detail, Departement departement, List<Panne> pannes, String typeRessource,
			Date dateCreation) {
		super();
		this.id = id;
		this.codeInventaire = codeInventaire;
		this.etatDemande = etatDemande;
		this.user = user;
		this.enseignant = enseignant;
		this.appelDoffre = appelDoffre;
		this.detail = detail;
		this.departement = departement;
		this.pannes = pannes;
		this.typeRessource = typeRessource;
		this.dateCreation = dateCreation;
	}



	public String getCodeInventaire() {
		return codeInventaire;
	}

	public void setCodeInventaire(String codeInventaire) {
		this.codeInventaire = codeInventaire;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EtatDemande getEtatDemande() {
		return etatDemande;
	}

	public void setEtatDemande(EtatDemande etatDemande) {
		this.etatDemande = etatDemande;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(User enseignant) {
		this.enseignant = enseignant;
	}

	public AppelDoffre getAppelDoffre() {
		return appelDoffre;
	}

	public void setAppelDoffre(AppelDoffre appelDoffre) {
		this.appelDoffre = appelDoffre;
	}

	public Detail getDetail() {
		return detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}

	public List<Panne> getPannes() {
		return pannes;
	}

	public void setPannes(List<Panne> pannes) {
		this.pannes = pannes;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	public String getTypeRessource() {
		return typeRessource;
	}

	public void setTypeRessource(String typeRessource) {
		this.typeRessource = typeRessource;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

}