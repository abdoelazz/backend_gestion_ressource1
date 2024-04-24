package gestionRessource.backend.service;

import java.util.List;

import gestionRessource.backend.model.Departement;

public interface DepartementService {

	Departement getDepartementById(Long id);

	Departement ajouterDepartement(Departement departement);

	void deleteDepartement(Long id);

	List<Departement> getAllDepartement();

}
