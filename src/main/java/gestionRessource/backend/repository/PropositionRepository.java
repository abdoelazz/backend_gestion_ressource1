package gestionRessource.backend.repository;

import java.util.List;

import gestionRessource.backend.model.AppelDoffre;
import gestionRessource.backend.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import gestionRessource.backend.model.Proposition;

public interface PropositionRepository extends JpaRepository<Proposition, Long> {
	List<Proposition> findByOrderByMontantTotalAsc();

	List<Proposition> findByFournisseur(Fournisseur fournisseur);

	Proposition findByFournisseurAndAppelDoffre(Fournisseur fournisseur, AppelDoffre appelDoffre);

}
