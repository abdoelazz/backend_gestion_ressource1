package gestionRessource.backend.dto;

import java.sql.Date;

import gestionRessource.backend.model.FrequenceConstat;
import gestionRessource.backend.model.Ordre;

public class ConstatDTO {
	private String explication;
	private FrequenceConstat frequenceConstat;
	private Ordre ordre;
	private Long panne_id;
	private Date dateApparition;

	public ConstatDTO(String explication, FrequenceConstat frequenceConstat, Ordre ordre, Long panne_id,
			Date dateApparition) {
		super();
		this.explication = explication;
		this.frequenceConstat = frequenceConstat;
		this.ordre = ordre;
		this.panne_id = panne_id;
		this.dateApparition = dateApparition;
	}

	public String getExplication() {
		return explication;
	}

	public void setExplication(String explication) {
		this.explication = explication;
	}

	public FrequenceConstat getFrequenceConstat() {
		return frequenceConstat;
	}

	public void setFrequenceConstat(FrequenceConstat frequenceConstat) {
		this.frequenceConstat = frequenceConstat;
	}

	public Ordre getOrdre() {
		return ordre;
	}

	public void setOrdre(Ordre ordre) {
		this.ordre = ordre;
	}

	public Long getPanne_id() {
		return panne_id;
	}

	public void setPanne_id(Long panne_id) {
		this.panne_id = panne_id;
	}

	public Date getDateApparition() {
		return dateApparition;
	}

	public void setDateApparition(Date dateApparition) {
		this.dateApparition = dateApparition;
	}

}
