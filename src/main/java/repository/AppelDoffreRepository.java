package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.AppelDoffre;

public interface AppelDoffreRepository extends JpaRepository<AppelDoffre, Long> {

	List<AppelDoffre> findByEtatDisponibilite(boolean etatDispo);

}
