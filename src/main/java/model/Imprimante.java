package model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Imprimante extends Ressource {

	@Column(name = "resolution")
	private int resolution;

	@Column(name = "vitesseImpression")
	private int vitesseImpression;

	public Imprimante() {
		super();
	}

	public Imprimante(Long id, String codeInventaire, EtatDemande etatDemande, User user, User enseignant,
			AppelDoffre appelDoffre, Detail detail, Departement departement, List<Panne> pannes, String typeRessource,
			Date dateCreation) {
		super(id, codeInventaire, etatDemande, user, enseignant, appelDoffre, detail, departement, pannes,
				typeRessource, dateCreation);
	}

	public Imprimante(int resolution, int vitesseImpression) {
		super();
		this.resolution = resolution;
		this.vitesseImpression = vitesseImpression;
	}

	public int getResolution() {
		return resolution;
	}

	public void setResolution(int resolution) {
		this.resolution = resolution;
	}

	public int getVitesseImpression() {
		return vitesseImpression;
	}

	public void setVitesseImpression(int vitesseImpression) {
		this.vitesseImpression = vitesseImpression;
	}

}
