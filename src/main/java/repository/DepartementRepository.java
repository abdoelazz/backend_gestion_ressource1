package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Departement;

public interface DepartementRepository extends JpaRepository<Departement, Long> {

}
