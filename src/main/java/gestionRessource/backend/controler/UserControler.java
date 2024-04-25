package gestionRessource.backend.controler;

import java.util.List;

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

import gestionRessource.backend.convert.UserConvert;
import gestionRessource.backend.dto.AuthentificationDTO;
import gestionRessource.backend.dto.UserDTO;
import gestionRessource.backend.model.Departement;
import gestionRessource.backend.model.User;
import gestionRessource.backend.service.DepartementService;
import gestionRessource.backend.service.UserService;
import gestionRessource.backend.utils.PasswordEncoderUtil;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserControler {

	@Autowired
	private UserService userService;

	@Autowired
	private DepartementService departementService;

	public ResponseEntity<?> authentification( AuthentificationDTO authentification) {
		String encodedPassword = PasswordEncoderUtil.encodePassword(authentification.getPassword());
			User user = userService.getUserByLoginPassword(authentification.getLogin(), encodedPassword);
			if (user != null) {
				System.out.println(user.getLogin());
				return ResponseEntity.ok(user);
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login or Password incorrect");
			}




	}

	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody UserDTO userdto) {
		User user = UserConvert.convertUserDtoToUser(userdto);
		User userAdded = userService.saveUser(user);
		if (userAdded != null) {
			return ResponseEntity.ok("User added successfully");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add user");
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
	public User modifyUser(@RequestParam Long id, @RequestBody UserDTO userdto) {
		User oldUser = userService.getUserById(id);
		Departement departement = departementService.getDepartementById(userdto.getDepartementId());
		oldUser.setDepartement(departement);
		oldUser.setFirst_name(userdto.getFirst_name());
		oldUser.setLast_name(userdto.getLast_name());
		oldUser.setLogin(userdto.getLogin());
		return userService.saveUser(oldUser);
	}

	@PutMapping("/modifyPasswordUser")
	public User modifyPasswordUser(@RequestParam Long user_id, @RequestBody String password) {
		User oldUser = userService.getUserById(user_id);
		String encodedPassword = PasswordEncoderUtil.encodePassword(password);
		oldUser.setPassword(encodedPassword);
		return userService.saveUser(oldUser);
	}

	@PostMapping("/getUsersByRoleAndDep")
	public List<User> getUsersByRoleAndDep(@RequestBody UserDTO userDTO) {
		return userService.getUsersByRoleAndDep(userDTO.getRole(), userDTO.getDepartementId());
	}
}