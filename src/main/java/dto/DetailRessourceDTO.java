package dto;

public class DetailRessourceDTO {

	private int dureeGarantie;
	private String marque;
	private double prix;
	private Long idRessource;

	public int getDureeGarantie() {
		return dureeGarantie;
	}

	public void setDureeGarantie(int dureeGarantie) {
		this.dureeGarantie = dureeGarantie;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Long getIdRessource() {
		return idRessource;
	}

	public void setIdRessource(Long idRessource) {
		this.idRessource = idRessource;
	}

}
