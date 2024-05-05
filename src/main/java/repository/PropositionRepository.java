package repository;

import java.util.List;

import model.AppelDoffre;
import model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import model.Proposition;

public interface PropositionRepository extends JpaRepository<Proposition, Long> {
	List<Proposition> findByOrderByMontantTotalAsc();

	List<Proposition> findByFournisseur(Fournisseur fournisseur);

	Proposition findByFournisseurAndAppelDoffre(Fournisseur fournisseur, AppelDoffre appelDoffre);

}
