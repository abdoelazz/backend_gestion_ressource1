package gestionRessource.backend.service;

import gestionRessource.backend.model.Fournisseur;

public interface FournisseurService {
	Fournisseur getFournisseurById(Long id);

	Fournisseur ajouterFournisseur(Fournisseur fournisseur);
}
