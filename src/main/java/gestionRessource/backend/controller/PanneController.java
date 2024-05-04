package gestionRessource.backend.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gestionRessource.backend.dto.PanneDTO;
import gestionRessource.backend.model.EtatPanne;
import gestionRessource.backend.model.Panne;
import gestionRessource.backend.model.Ressource;
import gestionRessource.backend.model.User;
import gestionRessource.backend.service.PanneService;
import gestionRessource.backend.service.RessourceService;
import gestionRessource.backend.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/panne")
public class PanneController {

	@Autowired
	private PanneService panneService;

	@Autowired
	private RessourceService ressourceService;

	@Autowired
	private UserService userService;

	@PostMapping("/addPanneToRessource")
	public Panne addPanneToRessource(@RequestParam Long ressource_id,@RequestParam String detail) {
		Ressource ressource = ressourceService.getRessourceById(ressource_id);
		Panne panne = new Panne();
		panne.setDetail(detail);
		panne.setDateSignal(new Date(System.currentTimeMillis()));
		panne.setEtatPanne(EtatPanne.NonRepare);
		panne.setRessource(ressource);
		return panneService.savePanne(panne);

	}

	@PutMapping("/affectPanneToTechnicien")
	public Panne affectPanneToTechnicien(@RequestParam Long panneId, @RequestParam Long userId) {
		User user = userService.getUserById(userId);
		Panne panne = panneService.getPanneById(panneId);
		panne.setEtatPanne(EtatPanne.EnCours);
		panne.setUser(user);
		return panneService.savePanne(panne);

	}

	@GetMapping("/getPannes")
	public List<Panne> getPannes() {
		return panneService.getAllPannes();

	}

	@GetMapping("/getPanneById")
	public Panne getPanneById(@RequestParam Long panne_id) {
		return panneService.getPanneById(panne_id);
	}

	@GetMapping("/getPanneByUserId")
	public List<Panne> getPanneByUserId(@RequestParam Long user_id) {
		return panneService.getPannesByUser(user_id);
	}

	@PutMapping("/modifyPanne")
	public Panne modifyPanne(@RequestParam Long panne_id, @RequestBody PanneDTO panneDto) {
		Panne oldPanne = panneService.getPanneById(panne_id);
		oldPanne.setEtatPanne(panneDto.getEtatPanne());
		return panneService.savePanne(oldPanne);
	}

	@GetMapping("/getPanneByRessourceUser")
	public List<Panne> getPanneByRessourceUser(@RequestParam Long user_id) {
		return panneService.getPanneByRessourceUser(user_id);
	}
}
