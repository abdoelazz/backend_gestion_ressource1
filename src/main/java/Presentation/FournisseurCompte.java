package Presentation;

import model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FournisseurCompte {
	//fournisseur
	@GetMapping("/fournisseur/compte")
	public String showFournisseurCompte(HttpServletRequest request) {
		// Testing if the user has a sessoin
		HttpSession session = request.getSession();
		User fournisseur = (User) session.getAttribute("fournisseur");
		
	    if (fournisseur != null) {
	    	return "Fournisseur/compte";
	    } else {
	    	return "redirect:/appels-d-offres";
	    }
	}
}
    