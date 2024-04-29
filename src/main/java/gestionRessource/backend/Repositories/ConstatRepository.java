package gestionRessource.backend.Repositories;


import gestionRessource.backend.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstatRepository extends JpaRepository<Constat,Integer> {
    List<Constat> findAllBySendIsTrue();
    List<Constat> findAllBySendIsFalse();
    List<Constat> findAllByPanne(Panne panne);
    Constat findConstatByPanne(Panne panne);
}
