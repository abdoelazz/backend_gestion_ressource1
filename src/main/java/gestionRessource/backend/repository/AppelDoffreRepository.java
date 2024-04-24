package gestionRessource.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gestionRessource.backend.model.AppelDoffre;

public interface AppelDoffreRepository extends JpaRepository<AppelDoffre, Long> {

	List<AppelDoffre> findByEtatDisponibilite(boolean etatDispo);

}
