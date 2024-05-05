package Presentation;

import controller.AppelDoffreController;
import controller.DetailController;
import controller.PropositionController;
import dto.DetailRessourceDTO;
import dto.PropositionDTO;
import model.AppelDoffre;
import model.Detail;
import model.Proposition;
import model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FournisseurPropositionController {
	
	@Autowired
	private AppelDoffreController appelDoffreController;
	
	@Autowired
	private PropositionController propositionController;
	
	@Autowired
	private DetailController detailController;
	//fournisseur
	@GetMapping("/fournisseur/propositions")
	public String showMyPropositions(HttpServletRequest request, Model model) {
		
		// Testing if the user has a sessoin
		HttpSession session = request.getSession();
		User fournisseur = (User) session.getAttribute("fournisseur");
		
		if (fournisseur != null) {
	    	List<Proposition> propositions = this.propositionController.getPropositionForFournisseur(fournisseur.getId());
			model.addAttribute("propositions", propositions);
	    	return "Fournisseur/myPropositions";
	    } else {
	    	return "redirect:/fournisseur/login";
	    }
	}
	
	@GetMapping("/fournisseur/soumettre-proposition")
	public String soumettreProposition(@RequestParam("id") Long appelID, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		
		// Testing if the user has a sessoin
		HttpSession session = request.getSession();
		User fournisseur = (User) session.getAttribute("fournisseur");
		
		if (fournisseur != null) {
			if (this.propositionController.checkPropositionForFournisseur(fournisseur.getId(), appelID) == null) {
				AppelDoffre appelDoffre = this.appelDoffreController.getAppelDoffreById(appelID);
				model.addAttribute("appelDoffre", appelDoffre);
							
				return "Fournisseur/soumettreProposition";
			} else {
				// un message d'erreur
				redirectAttributes.addFlashAttribute("warningMessage", "Vous avez déjà ajouté votre proposition pour cette appel d'offre.");
				return "redirect:/appels-d-offres";
			}
			
		} else {
			return "redirect:/fournisseur/login";
	    }
	}
	
	@PostMapping("soumettre-proposition-in")
	public String addProposition(
			 HttpServletRequest request,
	        @RequestParam("dateLivraison") Date dateLivraison,
	        @RequestParam("idAppelDoffre") Long idAppelDoffre,
	        RedirectAttributes redirectAttributes
	) {
		
		HttpSession session = request.getSession();
		User fournisseur = (User) session.getAttribute("fournisseur");
		
		String[] marques = request.getParameterValues("marque[]");
		String[] dureeGaranties = request.getParameterValues("dureeGarantie[]");
		String[] Prix = request.getParameterValues("prix[]");
		String[] idRessources = request.getParameterValues("idRessource[]");
		
		
		double total = 0.0;
		List<DetailRessourceDTO> detailRessourceDTOs = new ArrayList<>();
		
		// Parcours des tableaux
        for (int i = 0; i < marques.length; i++) {
            // Création d'un nouveau DetailRessourceDTO
            DetailRessourceDTO dto = new DetailRessourceDTO();

            // Assignation des valeurs aux champs du DTO
            dto.setMarque(marques[i]);
            dto.setDureeGarantie(Integer.parseInt(dureeGaranties[i]));
            dto.setPrix(Double.parseDouble(Prix[i]));
            dto.setIdRessource(Long.parseLong(idRessources[i]));

            // Ajout du DTO à la liste
            detailRessourceDTOs.add(dto);

            // Calcul du total des prix
            total += dto.getPrix();
        }
		
		PropositionDTO propositionDTO = new PropositionDTO();
		propositionDTO.setIdAppelDoffre(idAppelDoffre);
		propositionDTO.setDateLivraison(dateLivraison);
		propositionDTO.setDateProposition(new Date(System.currentTimeMillis()));
		propositionDTO.setFournisseur_id(fournisseur.getId());
		propositionDTO.setMontantTotal(total);
		propositionDTO.setDetailRessourceDto(detailRessourceDTOs);		
	   
		if(this.propositionController.addProposition(propositionDTO) != null) {
			// un message d'erreur
			redirectAttributes.addFlashAttribute("succesMessage", "Vous proposition a été ajouté avec succès.");
			return "redirect:/appels-d-offres";
		} else {
			// un message d'erreur
			redirectAttributes.addFlashAttribute("warningMessage", "Un erreur se prrduite lors de l'ajout de votre proposition, veuillez essayez à nouveau.");
			return "redirect:/appels-d-offres";
		}
	    
	}
	
	@GetMapping("/fournisseur/propositions/detail")
	public String showPropositionDetail(@RequestParam("idProposition") Long idProposition, HttpServletRequest request, Model model) {
		
		// Testing if the user has a sessoin
		HttpSession session = request.getSession();
		User fournisseur = (User) session.getAttribute("fournisseur");
		
		if (fournisseur != null) {
			List<Detail> details = detailController.getDetailByProposition(idProposition);
			model.addAttribute("details", details);
			return "Fournisseur/detailProposition";
		} else {
			return "redirect:/fournisseur/login";
		}
	}


}
