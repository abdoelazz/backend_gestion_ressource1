package model;

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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Panne")
public class Panne {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "dateSignal")
	private Date dateSignal;
	
	@Column(name = "datePanne")
	private Date datePanne;
	
	@Enumerated(EnumType.STRING)
	private EtatPanne etatPanne;

	@OneToMany(mappedBy = "panne")
	private List<Constat> constats;

	@ManyToOne
	@JoinColumn(name = "ressource_id")
	@JsonIgnoreProperties({ "user", "details", "appelDoffre", "pannes" })
	private Ressource ressource;

	@Column(name = "detail")
	private String detail;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Panne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Panne(Long id, Date dateSignal, EtatPanne etatPanne, List<Constat> constats, Ressource ressource,
			User user,String detail) {
		super();
		this.id = id;
		this.dateSignal = dateSignal;
		this.etatPanne = etatPanne;
		this.constats = constats;
		this.ressource = ressource;
		this.user = user;
		this.detail=detail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateSignal() {
		return dateSignal;
	}

	public void setDateSignal(Date dateSignal) {
		this.dateSignal = dateSignal;
	}

	public EtatPanne getEtatPanne() {
		return etatPanne;
	}

	public void setEtatPanne(EtatPanne etatPanne) {
		this.etatPanne = etatPanne;
	}

	public List<Constat> getConstats() {
		return constats;
	}

	public void setConstats(List<Constat> constats) {
		this.constats = constats;
	}

	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
