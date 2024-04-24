package gestionRessource.backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Departement")
public class Departement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nomDepartement")
	private String nomDepartement;

	@OneToMany(mappedBy = "departement")
	@JsonIgnoreProperties({ "departement", "notifications" })
	private List<User> users;

	@OneToMany(mappedBy = "departement")
	@JsonIgnore
	private List<Ressource> ressources;

	public Departement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Departement(Long id, String nomDepartement, List<User> users) {
		super();
		this.id = id;
		this.nomDepartement = nomDepartement;
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomDepartement() {
		return nomDepartement;
	}

	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
