package gestionRessource.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gestionRessource.backend.model.Constat;
import gestionRessource.backend.model.Panne;

public interface ConstatRepository extends JpaRepository<Constat, Long> {
	List<Constat> findByPanne(Panne panne);
}
