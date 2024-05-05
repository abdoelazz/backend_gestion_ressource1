package gestionRessource.backend.dto;

import java.util.List;

public class DepartementDTO {
	private String nomDepartement;
	private List<Long> usersId;

	public DepartementDTO() {
		super();
	}

	public DepartementDTO(String nomDepartement, List<Long> usersId) {
		super();
		this.nomDepartement = nomDepartement;
		this.usersId = usersId;
	}

	public String getNomDepartement() {
		return nomDepartement;
	}

	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
	}

	public List<Long> getUsersId() {
		return usersId;
	}

	public void setUsersId(List<Long> usersId) {
		this.usersId = usersId;
	}

}
