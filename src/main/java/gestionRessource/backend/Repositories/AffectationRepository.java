package gestionRessource.backend.Repositories;


import gestionRessource.backend.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffectationRepository extends JpaRepository<Affectation,Integer> {
    List<Affectation> findAffectationByEnseignant(Enseignant enseignant);

}
