package gestionRessource.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gestionRessource.backend.model.Departement;

public interface DepartementRepository extends JpaRepository<Departement, Long> {

}
