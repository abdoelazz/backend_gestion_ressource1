package controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import dto.DetailRessourceDTO;
import dto.PropositionDTO;
import model.AppelDoffre;
import model.Detail;
import model.EtatProposition;
import model.Fournisseur;
import model.Proposition;
import model.Ressource;
import service.AppelDoffreService;
import service.DetailService;
import service.FournisseurService;
import service.PropositionService;
import service.RessourceService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/proposition")
public class PropositionController {
	@Autowired
	private PropositionService propositionService;

	@Autowired
	private RessourceService ressourceService;

	@Autowired
	private DetailService detailService;

	@Autowired
	private FournisseurService fournisseurService;

	@Autowired
	private AppelDoffreService appelDoffreService;

	@PostMapping("/addProposition")
	public Proposition addProposition(@RequestBody PropositionDTO propositionDto) {

		AppelDoffre appelDoffre = appelDoffreService.getAppelDoffreById(propositionDto.getIdAppelDoffre());
		Fournisseur fournisseur = fournisseurService.getFournisseurById(propositionDto.getFournisseur_id());
		Proposition proposition = new Proposition();
		proposition.setAppelDoffre(appelDoffre);
		proposition.setDateProposition(propositionDto.getDateProposition());
		proposition.setDateLivraison(propositionDto.getDateLivraison());
		proposition.setEtatProposition(EtatProposition.NonTraite);
		proposition.setMontantTotal(propositionDto.getMontantTotal());
		proposition.setFournisseur(fournisseur);
		propositionService.saveProposition(proposition);
		if (propositionDto.getDetailRessourceDto() != null && !propositionDto.getDetailRessourceDto().isEmpty()) {
			for (DetailRessourceDTO detailRessourceDto : propositionDto.getDetailRessourceDto()) {
				Ressource ressource = ressourceService.getRessourceById(detailRessourceDto.getIdRessource());
				Detail detail = new Detail();
				detail.setDureeGarantie(detailRessourceDto.getDureeGarantie());
				detail.setMarque(detailRessourceDto.getMarque());
				detail.setPrix(detailRessourceDto.getPrix());
				detail.setRessource(ressource);
				detail.setProposition(proposition);
				ressource.setDetail(detail);
				detailService.saveDetail(detail);
				ressourceService.saveRessource(ressource);

			}
		}

		return propositionService.saveProposition(proposition);
	}

	@GetMapping("/getPropositionOrderByMoinsDisant")
	public List<Proposition> getPropositionOrderByMoinsDisant() {
		return propositionService.getPropositionOrderByMoinsDisant();
	}

	@GetMapping("/getPropositionForFournisseur")
	public List<Proposition> getPropositionForFournisseur(@RequestParam Long fournisseurId) {
		List<Proposition> propositions = propositionService.getPropositionByFournisseur(fournisseurId);
		return propositions;
	}

	@GetMapping("/checkPropositionForFournisseur")
	public Proposition checkPropositionForFournisseur(@RequestParam Long fournisseurId,
													  @RequestParam Long appelDoffreId) {
		Proposition proposition = propositionService.getPropositionByFournisseurAndAppelDoffre(fournisseurId,
				appelDoffreId);
		return proposition;
	}

	@GetMapping("/getAllPropositions")
	public List<Proposition> getAllPropositions() {
		List<Proposition> proposition = propositionService.getAllPropositions();
		return proposition;
	}

	@GetMapping("/Propositionbyid")
	public Optional<Proposition> getPropositionById(Long id) {
		Optional<Proposition> proposition = propositionService.PropositionbyID(id);
		return proposition;
	}

	@PutMapping("/refuse")
	public Proposition RefuserProposition(@RequestParam Long proposition_id) {
		Proposition oldProposition = propositionService.getPropositionbyId(proposition_id);
		oldProposition.setEtatProposition(EtatProposition.refuse);
		return propositionService.saveProposition(oldProposition);
	}

	@PutMapping("/accepte")
	public Proposition AccepterProposition(@RequestParam Long proposition_id) {
		Proposition oldProposition = propositionService.getPropositionbyId(proposition_id);
		oldProposition.setEtatProposition(EtatProposition.accepte);
		return propositionService.saveProposition(oldProposition);
	}
}
