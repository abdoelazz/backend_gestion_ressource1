package repository;

import model.Proposition;
import org.springframework.data.jpa.repository.JpaRepository;

import model.Detail;

import java.util.List;

public interface DetailRepository extends JpaRepository<Detail, Long> {
    List<Detail> findByProposition(Proposition proposition);

}
