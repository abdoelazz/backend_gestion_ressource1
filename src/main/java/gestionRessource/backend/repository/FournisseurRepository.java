package gestionRessource.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gestionRessource.backend.model.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

}
