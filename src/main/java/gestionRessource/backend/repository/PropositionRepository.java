package gestionRessource.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gestionRessource.backend.model.Proposition;

public interface PropositionRepository extends JpaRepository<Proposition, Long> {
	List<Proposition> findByOrderByMontantTotalAsc();

}
