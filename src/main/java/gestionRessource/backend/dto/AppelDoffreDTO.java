package gestionRessource.backend.dto;

import java.sql.Date;
import java.util.List;

public class AppelDoffreDTO {
	private Date dateDebut;
	private Date dateFin;
	private boolean etatDisponibilite;
	private List<Long> ressourceIdList;

	public AppelDoffreDTO(Date dateDebut, Date dateFin, boolean etatDisponibilite, List<Long> ressourceIdList) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.etatDisponibilite = etatDisponibilite;
		this.ressourceIdList = ressourceIdList;
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

	public List<Long> getRessourceIdList() {
		return ressourceIdList;
	}

	public void setRessourceIdList(List<Long> ressourceIdList) {
		this.ressourceIdList = ressourceIdList;
	}

}
