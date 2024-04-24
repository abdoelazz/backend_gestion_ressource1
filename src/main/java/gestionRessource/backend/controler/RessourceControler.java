package gestionRessource.backend.controler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gestionRessource.backend.dto.RessourceDTO;
import gestionRessource.backend.model.Departement;
import gestionRessource.backend.model.EtatDemande;
import gestionRessource.backend.model.Imprimante;
import gestionRessource.backend.model.Ordinateur;
import gestionRessource.backend.model.Ressource;
import gestionRessource.backend.model.User;
import gestionRessource.backend.service.DepartementService;
import gestionRessource.backend.service.RessourceService;
import gestionRessource.backend.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/ressource")
public class RessourceControler {

	@Autowired
	private RessourceService ressourceService;

	@Autowired
	private UserService userService;

	@Autowired
	private DepartementService departementService;

	@GetMapping("/getAllRessources")
	public List<Ressource> getAllRessources() {
		return ressourceService.getAllResources();
	}

	@DeleteMapping("/deleteRessource")
	public ResponseEntity<String> deleteRessource(@RequestParam Long id) {
		ressourceService.deleteRessource(id);
		return new ResponseEntity<String>("La ressource est supprimée avec succées", HttpStatus.CREATED);
	}

	@PutMapping("/updateRessource")
	public Ressource updateRessource(@RequestParam Long id, @RequestBody RessourceDTO ressourceDto) {
		Ressource oldRessource = ressourceService.getRessourceById(id);
		Departement departement = null;
		if (ressourceDto.getDepartementId() != 0L) {
			departement = departementService.getDepartementById(ressourceDto.getDepartementId());
		}
		if (ressourceDto.getTypeRessource().equals("Ordinateur") && oldRessource instanceof Ordinateur) {
			oldRessource.setCodeInventaire(ressourceDto.getCodeInventaire());
			oldRessource.setEtatDemande(ressourceDto.getEtatDemande());
			((Ordinateur) oldRessource).setCpu(ressourceDto.getCpu());
			((Ordinateur) oldRessource).setRam(ressourceDto.getRam());
			((Ordinateur) oldRessource).setDisqueDur(ressourceDto.getDisqueDur());
			((Ordinateur) oldRessource).setEcran(ressourceDto.getEcran());
			((Ordinateur) oldRessource).setTypeRessource(ressourceDto.getTypeRessource());
			((Ordinateur) oldRessource).setDepartement(departement);
			return ressourceService.updateRessource(oldRessource);

		} else if (ressourceDto.getTypeRessource().equals("Imprimante") && oldRessource instanceof Imprimante) {
			oldRessource.setCodeInventaire(ressourceDto.getCodeInventaire());
			oldRessource.setEtatDemande(ressourceDto.getEtatDemande());
			((Imprimante) oldRessource).setResolution(ressourceDto.getResolution());
			((Imprimante) oldRessource).setVitesseImpression(ressourceDto.getVitesseImpression());
			((Imprimante) oldRessource).setTypeRessource(ressourceDto.getTypeRessource());
			((Imprimante) oldRessource).setDepartement(departement);

			return ressourceService.updateRessource(oldRessource);
		}
		System.out.println("les données modifiées ne sont pas compatible");
		return null;

	}

	@GetMapping("/getRessourcesById")
	public ResponseEntity<Ressource> getRessourceById(@RequestParam Long id) {
		return new ResponseEntity<Ressource>(ressourceService.getRessourceById(id), HttpStatus.CREATED);
	}

	@PostMapping("/addRessource")
	public Ressource addRessource(@RequestBody RessourceDTO ressourceDto) {
		User user = userService.getUserById(ressourceDto.getUserId());
		Departement departement = null;
		if (ressourceDto.getDepartementId() != 0L) {
			departement = departementService.getDepartementById(ressourceDto.getDepartementId());
		}
		if (ressourceDto.getTypeRessource().equals("Ordinateur")) {
			Ordinateur ordi = new Ordinateur();
			ordi.setEtatDemande(EtatDemande.créée);
			ordi.setCpu(ressourceDto.getCpu());
			ordi.setDisqueDur(ressourceDto.getDisqueDur());
			ordi.setEcran(ressourceDto.getEcran());
			ordi.setRam(ressourceDto.getRam());
			ordi.setTypeRessource(ressourceDto.getTypeRessource());
			ordi.setDateCreation(new Date(System.currentTimeMillis()));
			ordi.setUser(user);
			ordi.setDepartement(departement);
			return ressourceService.saveRessource(ordi);

		} else if (ressourceDto.getTypeRessource().equals("Imprimante")) {
			Imprimante impr = new Imprimante();
			impr.setEtatDemande(EtatDemande.créée);
			impr.setResolution(ressourceDto.getResolution());
			impr.setTypeRessource(ressourceDto.getTypeRessource());
			impr.setVitesseImpression(ressourceDto.getVitesseImpression());
			impr.setDateCreation(new Date(System.currentTimeMillis()));
			impr.setUser(user);
			impr.setDepartement(departement);

			return ressourceService.saveRessource(impr);
		}
		System.out.println("y'a eu un probleme lors de l'ajout de la ressource");
		return null;
	}

	@GetMapping("/getRessourcesByUserId")
	public List<Ressource> getRessourcesByUserId(@RequestParam Long id) {
		List<Ressource> ressources = ressourceService.getRessourceByUserId(id);
		return ressources;

	}

	@GetMapping("/getRessourcesDelivred")
	public List<Ressource> getRessourcesDelivred() {
		List<Ressource> ressources = ressourceService.getRessourceDelivred();
		return ressources;

	}

	@PutMapping("/updateRessourceForLivraison")
	public Ressource updateRessourceForLivraison(@RequestParam Long id, @RequestBody RessourceDTO ressourceDto) {
		Ressource oldRessource = ressourceService.getRessourceById(id);
		if (oldRessource != null) {
			oldRessource.setCodeInventaire(ressourceDto.getCodeInventaire());
			ressourceService.saveRessource(oldRessource);
		}
		return oldRessource;

	}

	@PutMapping("/affectOrNotRessourceToDep")
	public Ressource affectOrNotRessourceToDep(@RequestParam Long id, @RequestBody RessourceDTO ressourceDto) {
		Ressource oldRessource = ressourceService.getRessourceById(id);
		Departement departement = null;
		Long departementId = ressourceDto.getDepartementId();
		if (departementId != 0L) {
			departement = departementService.getDepartementById(ressourceDto.getDepartementId());

		}
		if (oldRessource != null) {
			oldRessource.setDepartement(departement);
			ressourceService.saveRessource(oldRessource);
		}
		return oldRessource;

	}

	@GetMapping("/getRessourcesByDep")
	public List<Ressource> getRessourcesByDep(@RequestParam Long depId) {
		List<Ressource> ressources = ressourceService.getRessourcesByDep(depId);
		return ressources;
	}

	@GetMapping("/getRessourcesEnseignantsByDepartement")
	public List<Ressource> getRessourcesEnseignantsByDepartement(@RequestParam Long depId) {
		return ressourceService.getRessourcesEnseignantsByDepartement(depId);
	}

	@PutMapping("/changeStatusToTraité")
	public List<Ressource> changeStatusToTraité(@RequestParam Long depId) {
		List<Ressource> ressources = ressourceService.getRessourcesEnseignantsByDepartement(depId);
		for (Ressource ressource : ressources) {
			if (ressource.getEtatDemande() != EtatDemande.Traité) {
				ressource.setEtatDemande(EtatDemande.Traité);
				ressourceService.saveRessource(ressource);
			}
		}
		return ressources;

	}

	@PutMapping("/affectRessourceToEnseignant")
	public Ressource affectRessourceToEnseignant(@RequestParam Long ressourceId, @RequestParam Long enseignantId) {
		Ressource ressource = ressourceService.getRessourceById(ressourceId);
		User enseignant = userService.getUserById(enseignantId);
		if (ressource != null && enseignant != null) {
			ressource.setEnseignant(enseignant);
			ressourceService.saveRessource(ressource);

		}

		return ressource;

	}

	@GetMapping("/getRessourcesByEnseignant")
	public List<Ressource> getRessourcesByEnseignant(@RequestParam Long enseignantId) {
		List<Ressource> ressources = ressourceService.getRessourcesByEnseignant(enseignantId);
		return ressources;
	}

	@PutMapping("/modifyStatusRessources")
	public List<Ressource> modifyStatusRessources(@RequestBody RessourceDTO ressourceDto) {
		List<Ressource> ressources = new ArrayList<Ressource>();
		if (ressourceDto.getRessourceIdList() != null && !ressourceDto.getRessourceIdList().isEmpty()) {
			for (Long ressourceId : ressourceDto.getRessourceIdList()) {
				Ressource ressource = ressourceService.getRessourceById(ressourceId);
				if (ressource != null) {
					ressource.setEtatDemande(ressourceDto.getEtatDemande());
					ressourceService.saveRessource(ressource);
					ressources.add(ressource);
				}
			}
		}
		return ressources;
	}

}
