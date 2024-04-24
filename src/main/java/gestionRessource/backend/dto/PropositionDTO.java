package gestionRessource.backend.dto;

import java.sql.Date;
import java.util.List;

public class PropositionDTO {
	private Long idAppelDoffre;
	private Date dateProposition;
	private Date dateLivraison;
	private double montantTotal;
	private Long fournisseur_id;
	private List<DetailRessourceDTO> detailRessourceDto;

	public Long getIdAppelDoffre() {
		return idAppelDoffre;
	}

	public void setIdAppelDoffre(Long idAppelDoffre) {
		this.idAppelDoffre = idAppelDoffre;
	}

	public Date getDateProposition() {
		return dateProposition;
	}

	public void setDateProposition(Date dateProposition) {
		this.dateProposition = dateProposition;
	}

	public Date getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Date dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public double getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(double montantTotal) {
		this.montantTotal = montantTotal;
	}

	public List<DetailRessourceDTO> getDetailRessourceDto() {
		return detailRessourceDto;
	}

	public void setDetailRessourceDto(List<DetailRessourceDTO> detailRessourceDto) {
		this.detailRessourceDto = detailRessourceDto;
	}

	public Long getFournisseur_id() {
		return fournisseur_id;
	}

	public void setFournisseur_id(Long fournisseur_id) {
		this.fournisseur_id = fournisseur_id;
	}

}
