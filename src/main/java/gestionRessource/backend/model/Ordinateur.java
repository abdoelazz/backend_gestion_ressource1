package gestionRessource.backend.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Ordinateur extends Ressource {

	@Column(name = "cpu")
	private String cpu;

	@Column(name = "ram")
	private String ram;

	@Column(name = "disqueDur")
	private String disqueDur;

	@Column(name = "ecran")
	private String ecran;

	public Ordinateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ordinateur(Long id, String codeInventaire, EtatDemande etatDemande, User user, User enseignant,
			AppelDoffre appelDoffre, Detail detail, Departement departement, List<Panne> pannes, String typeRessource,
			Date dateCreation) {
		super(id, codeInventaire, etatDemande, user, enseignant, appelDoffre, detail, departement, pannes,
				typeRessource, dateCreation);
		// TODO Auto-generated constructor stub
	}

	public Ordinateur(String cpu, String ram, String disqueDur, String ecran) {
		super();
		this.cpu = cpu;
		this.ram = ram;
		this.disqueDur = disqueDur;
		this.ecran = ecran;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getDisqueDur() {
		return disqueDur;
	}

	public void setDisqueDur(String disqueDur) {
		this.disqueDur = disqueDur;
	}

	public String getEcran() {
		return ecran;
	}

	public void setEcran(String ecran) {
		this.ecran = ecran;
	}

}
