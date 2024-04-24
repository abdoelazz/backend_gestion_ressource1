package gestionRessource.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestionRessource.backend.model.Departement;
import gestionRessource.backend.model.Role;
import gestionRessource.backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByLoginAndPassword(String login, String password);

	User findByLogin(String login);

	List<User> findByRoleAndDepartement(Role role, Departement departement);

}
