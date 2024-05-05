package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Departement;
import model.Ressource;
import model.User;

public interface RessourceRepository extends JpaRepository<Ressource, Long> {
	List<Ressource> findByUserIdOrderByEtatDemandeDesc(Long userId);

	List<Ressource> findAllByOrderByEtatDemandeDesc();

	List<Ressource> findByDepartement(Departement departement);

	List<Ressource> findByEnseignant(User enseignant);

	@Query("SELECT r FROM Ressource r WHERE r.codeInventaire IS NOT NULL AND r.codeInventaire <> ''")
	List<Ressource> findAllByCodeInventaireEmpty();

	@Query("SELECT r FROM Ressource r JOIN r.user u WHERE u.role = 'Enseignant' AND u.departement.id = :deptId")
	List<Ressource> getRessourcesEnseignantsByDepartement(@Param("deptId") Long deptId);

}
