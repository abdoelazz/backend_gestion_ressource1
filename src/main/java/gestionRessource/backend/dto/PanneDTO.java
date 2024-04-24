package gestionRessource.backend.dto;

import java.sql.Date;

import gestionRessource.backend.model.EtatPanne;

public class PanneDTO {
	private Date dateSignal;
	private EtatPanne etatPanne;

	public PanneDTO(Date dateSignal, EtatPanne etatPanne) {
		super();
		this.dateSignal = dateSignal;
		this.etatPanne = etatPanne;
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

}
