package model;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "AppelDoffre")
public class AppelDoffre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "dateDebut")
	private Date dateDebut;

	@Column(name = "dateFin")
	private Date dateFin;

	@Column(name = "etatDisponibilite")
	private boolean etatDisponibilite;

	@OneToMany(mappedBy = "appelDoffre")
	@JsonIgnoreProperties({ "user", "appelDoffre" })
	private List<Ressource> ressources;

	@OneToMany(mappedBy = "appelDoffre")
	@JsonIgnoreProperties({ "appelDoffre" })
	private List<Proposition> propositions;

	public AppelDoffre() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppelDoffre(Long id, Date dateDebut, Date dateFin, boolean etatDisponibilite, List<Ressource> ressources,
			List<Proposition> propositions) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.etatDisponibilite = etatDisponibilite;
		this.ressources = ressources;
		this.propositions = propositions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public boolean isEtatDisponibilite() {
		return etatDisponibilite;
	}

	public void setEtatDisponibilite(boolean etatDisponibilite) {
		this.etatDisponibilite = etatDisponibilite;
	}

	public List<Ressource> getRessources() {
		return ressources;
	}

	public void setRessources(List<Ressource> ressources) {
		this.ressources = ressources;
	}

	public List<Proposition> getPropositions() {
		return propositions;
	}

	public void setPropositions(List<Proposition> propositions) {
		this.propositions = propositions;
	}

}
