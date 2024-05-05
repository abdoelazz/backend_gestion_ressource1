package service;

import model.Fournisseur;

public interface FournisseurService {
	Fournisseur getFournisseurById(Long id);

	Fournisseur ajouterFournisseur(Fournisseur fournisseur);
}
