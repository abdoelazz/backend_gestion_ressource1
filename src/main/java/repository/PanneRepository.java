package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Panne;
import model.User;

public interface PanneRepository extends JpaRepository<Panne, Long> {

	List<Panne> findByUser(User user);

	@Query("SELECT p FROM Panne p JOIN p.ressource r WHERE r.user.id = :user_id")
	List<Panne> getPanneByRessourceUser(Long user_id);
}
