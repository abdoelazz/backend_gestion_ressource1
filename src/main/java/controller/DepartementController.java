package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.DepartementDTO;
import model.Departement;
import model.User;
import service.DepartementService;
import service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/departement")
public class DepartementController {
	@Autowired
	private DepartementService departementService;

	@Autowired
	private UserService userService;

	@PostMapping("/addDepartement")
	private Departement addDepartement(@RequestBody DepartementDTO departementDto) {
		Departement departement = new Departement();
		departement.setNomDepartement(departementDto.getNomDepartement());
		if (departementDto.getUsersId() != null && !departementDto.getUsersId().isEmpty()) {
			List<User> userList = new ArrayList<User>();
			for (Long userId : departementDto.getUsersId()) {
				User user = userService.getUserById(userId);
				user.setDepartement(departement);
				userList.add(user);
			}
			departement.setUsers(userList);
		}
		return departementService.ajouterDepartement(departement);
	}

	@DeleteMapping("/deleteDepartement")
	public ResponseEntity<String> deleteDepartement(@RequestParam Long id) {
		Departement departement = departementService.getDepartementById(id);
		if (departement != null) {
			List<User> users = departement.getUsers();
			if (users != null && !users.isEmpty()) {
				for (User user : users) {
					user.setDepartement(null);
					userService.saveUser(user);
				}

			}
		}
		departementService.deleteDepartement(id);
		return new ResponseEntity<String>("Le département est supprimé avec succées", HttpStatus.CREATED);
	}

	@GetMapping("/getAllDepartements")
	public List<Departement> getAllDepartements() {
		return departementService.getAllDepartement();
	}
}
