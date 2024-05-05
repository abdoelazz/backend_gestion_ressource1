package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

}
