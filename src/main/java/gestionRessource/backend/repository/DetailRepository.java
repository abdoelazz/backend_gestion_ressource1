package gestionRessource.backend.repository;

import gestionRessource.backend.model.Proposition;
import org.springframework.data.jpa.repository.JpaRepository;

import gestionRessource.backend.model.Detail;

import java.util.List;

public interface DetailRepository extends JpaRepository<Detail, Long> {
    List<Detail> findByProposition(Proposition proposition);

}
