package gestionRessource.backend.service;

import java.util.List;

import gestionRessource.backend.model.Role;
import gestionRessource.backend.model.User;

public interface UserService {
	User getUserByLoginPassword(String login, String password);

	User saveUser(User user);

	User getUserById(Long userId);

	List<User> getAllUsers();

	User getUserByLogin(String login);

	List<User> getUsersByRoleAndDep(Role role, Long depId);
	void deleteUser(String login);

}
