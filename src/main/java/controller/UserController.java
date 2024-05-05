package controller;

import java.util.List;

import model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import convert.UserConvert;
import dto.AuthentificationDTO;
import dto.UserDTO;
import model.Departement;
import model.User;
import service.DepartementService;
import service.UserService;
import utils.PasswordEncoderUtil;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private DepartementService departementService;

	@PostMapping("/authentification")
	public User authentification(@RequestBody AuthentificationDTO authentification) {
		String encodedPassword = PasswordEncoderUtil.encodePassword(authentification.getPassword());
		User user = userService.getUserByLoginPassword(authentification.getLogin(), encodedPassword);
		if (user != null) {
			return user;


		} else {
			System.out.println("Login ou Password incorrecte");
			return null;
		}
	}

	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody UserDTO userdto) {
		User user = UserConvert.convertUserDtoToUser(userdto);
		if(user.getRole()== Role.Enseignant || user.getRole() == Role.ChefDepartement)
		{
			Departement departement = departementService.getDepartementById(userdto.getDepartementId());
			user.setDepartement(departement);
		}
		else {
			user.setDepartement(null);
		}
		User userAdded = userService.saveUser(user);
		if (userAdded != null) {
			return new ResponseEntity<>("User added successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Failed to add user", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/getUserByLogin")
	public User getUserByLogin(@RequestParam String login) {
		return userService.getUserByLogin(login);
	}

	@PutMapping("/modifyUser")
	public User modifyUser(@RequestParam Long id, @RequestBody String login , @RequestBody String firstname, @RequestBody String lastname, @RequestBody Long departementId, @RequestBody Role role) {
		User oldUser = userService.getUserById(id);
		Departement departement = departementService.getDepartementById(departementId);
		oldUser.setDepartement(departement);
		oldUser.setFirst_name(firstname);
		oldUser.setLast_name(lastname);
		oldUser.setLogin(login);
		oldUser.setRole(role);
		User userModified = userService.saveUser(oldUser);
		if (userModified != null) {
			return userModified;
		} else {
			return null;
		}
	}

	@PutMapping("/modifyPasswordUser")
	public User modifyPasswordUser(@RequestParam Long user_id, @RequestBody String password) {
		User oldUser = userService.getUserById(user_id);
		String encodedPassword = PasswordEncoderUtil.encodePassword(password);
		oldUser.setPassword(encodedPassword);
		User userModified = userService.saveUser(oldUser);
		if (userModified != null) {
			return userModified;
		} else {
			return null;
		}
	}

	@PostMapping("/getUsersByRoleAndDep")
	public List<User> getUsersByRoleAndDep(@RequestBody UserDTO userDTO) {
		return userService.getUsersByRoleAndDep(userDTO.getRole(), userDTO.getDepartementId());
	}
	@PutMapping("/DeleteUser")
	public void DeleteUser( @RequestBody String login ) {
		userService.deleteUser(login);
	}


}