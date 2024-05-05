package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Constat;
import model.Panne;

public interface ConstatRepository extends JpaRepository<Constat, Long> {
	List<Constat> findByPanne(Panne panne);
}
