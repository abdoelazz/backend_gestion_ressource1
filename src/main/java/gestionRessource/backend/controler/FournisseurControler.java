package gestionRessource.backend.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gestionRessource.backend.dto.FournisseurDTO;
import gestionRessource.backend.model.Fournisseur;
import gestionRessource.backend.model.Role;
import gestionRessource.backend.service.FournisseurService;
import gestionRessource.backend.utils.PasswordEncoderUtil;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/fournisseur")
public class FournisseurControler {
	@Autowired
	private FournisseurService fournisseurService;

	@PostMapping("/addFournisseur")
	public Fournisseur addFournisseur(@RequestBody FournisseurDTO fournisseurDto) {
		Fournisseur fournisseur = new Fournisseur();
		fournisseur.setSociete(fournisseurDto.getSociete());
		fournisseur.setLogin(fournisseurDto.getSociete());
		fournisseur.setRole(Role.Fournisseur);
		String encodedPassword = PasswordEncoderUtil.encodePassword(fournisseurDto.getPassword());
		fournisseur.setPassword(encodedPassword);
		return fournisseurService.ajouterFournisseur(fournisseur);

	}

	@PutMapping("/modifyFournisseur")
	public Fournisseur modifyFournisseur(@RequestParam Long fournisseur_id,
			@RequestBody FournisseurDTO fournisseurDto) {
		Fournisseur oldFournisseur = fournisseurService.getFournisseurById(fournisseur_id);
		oldFournisseur.setAdresse(fournisseurDto.getAdresse());
		oldFournisseur.setGerant(fournisseurDto.getGerant());
		oldFournisseur.setLieu(fournisseurDto.getLieu());
		oldFournisseur.setSiteInternet(fournisseurDto.getSiteInternet());
		oldFournisseur.setScorePanne(fournisseurDto.getScorePanne());
		oldFournisseur.setEtatFournisseur(fournisseurDto.getEtatFournisseur());

		return fournisseurService.ajouterFournisseur(oldFournisseur);
	}
}
