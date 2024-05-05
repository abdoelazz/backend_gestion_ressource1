package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import dto.FournisseurDTO;
import model.Fournisseur;
import model.Role;
import service.FournisseurService;
import utils.PasswordEncoderUtil;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/fournisseur")
public class FournisseurController {
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

	@PostMapping("/addFournisseurToBlackList")
	public Fournisseur addFournisseurToBlackList(@RequestParam String fournisseur_id) {
		Fournisseur oldFournisseur = fournisseurService.getFournisseurById(Long.parseLong(fournisseur_id));
		oldFournisseur.setEtatFournisseur("BlackList");

		return fournisseurService.ajouterFournisseur(oldFournisseur);
	}
	@GetMapping("/getFournisseurById")
	public Fournisseur 	getFournisseurById(@RequestParam Long fournisseur_id) {
		Fournisseur oldFournisseur = fournisseurService.getFournisseurById(fournisseur_id);

		return oldFournisseur;
	}
}
