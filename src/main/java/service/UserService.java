package service;

import java.util.List;

import model.Role;
import model.User;

public interface UserService {
	User getUserByLoginPassword(String login, String password);

	User saveUser(User user);

	User getUserById(Long userId);

	List<User> getAllUsers();

	User getUserByLogin(String login);

	List<User> getUsersByRoleAndDep(Role role, Long depId);
	void deleteUser(String login);

}
