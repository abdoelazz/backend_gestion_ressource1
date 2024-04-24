package gestionRessource.backend.controler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gestionRessource.backend.dto.AppelDoffreDTO;
import gestionRessource.backend.model.AppelDoffre;
import gestionRessource.backend.model.Ressource;
import gestionRessource.backend.service.AppelDoffreService;
import gestionRessource.backend.service.RessourceService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/appelDoffre")
public class AppelDoffreControler {
	@Autowired
	private AppelDoffreService appelDoffreService;
	@Autowired
	private RessourceService ressourceService;

	@GetMapping("/getAllAppelDoffres")
	public List<AppelDoffre> getAllAppelDoffres() {
		return appelDoffreService.getAllAppelDoffres();
	}

	@GetMapping("/getAppelDoffresNew")
	public List<AppelDoffre> getAppelDoffresNew() {
		return appelDoffreService.getAppelDoffresNew();
	}

	@GetMapping("/getAppelDoffreById")
	public AppelDoffre getAppelDoffreById(@RequestParam Long id) {
		return appelDoffreService.getAppelDoffreById(id);
	}

	@PostMapping("/addAppelDoffre")
	public AppelDoffre addAppelDoffre(@RequestBody AppelDoffreDTO appelDoffreDto) {
		AppelDoffre appelDoffre = new AppelDoffre();
		appelDoffre.setDateDebut(appelDoffreDto.getDateDebut());
		appelDoffre.setDateFin(appelDoffreDto.getDateFin());
		appelDoffre.setEtatDisponibilite(true);
		// chercher et setter la liste des ressources à partir des ressources id
		if (appelDoffreDto.getRessourceIdList() != null && !appelDoffreDto.getRessourceIdList().isEmpty()) {
			List<Ressource> ressourceList = new ArrayList<Ressource>();
			for (Long idRessource : appelDoffreDto.getRessourceIdList()) {
				Ressource ressource = ressourceService.getRessourceById(idRessource);
				ressourceList.add(ressource);
			}
			appelDoffre.setRessources(ressourceList);

		}
		return appelDoffreService.AjouterAppelDoffre(appelDoffre);

	}

}
