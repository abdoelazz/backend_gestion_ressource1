package service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Departement;
import model.Role;
import model.User;
import repository.DepartementRepository;
import repository.UserRepository;
import service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DepartementRepository departementRepository;

	@Override
	public User getUserByLoginPassword(String login, String password) {
		return userRepository.findByLoginAndPassword(login, password);
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		return userOptional.orElse(null);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserByLogin(String login) {
		return userRepository.findByLogin(login);
	}

	@Override
	public List<User> getUsersByRoleAndDep(Role role, Long depId) {
		Optional<Departement> departementOptional = departementRepository.findById(depId);
		Departement departement = departementOptional.get();
		return userRepository.findByRoleAndDepartement(role, departement);

	}

	@Override
	public void deleteUser(String login) {
		userRepository.delete(getUserByLogin(login));
	}
}
