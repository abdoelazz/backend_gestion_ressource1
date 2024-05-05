package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Departement;
import model.Role;
import model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByLoginAndPassword(String login, String password);

	User findByLogin(String login);

	List<User> findByRoleAndDepartement(Role role, Departement departement);

}
