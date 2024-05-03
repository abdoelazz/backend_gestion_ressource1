package gestionRessource.backend.Presentation;

import gestionRessource.backend.controller.PanneController;
import gestionRessource.backend.model.*;
import gestionRessource.backend.service.RessourceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SupprimerPanne {
    @Autowired
    private PanneController panneController;

    @PostMapping("/deletePanne")
    public String handleSupprimerPanne(
            @RequestParam("panneId") String panneId,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes
    ) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") instanceof User) {
            panneController.
            redirectAttributes.addFlashAttribute("successMessage", "Mot de passe a ete modifié avec succee");
            return "redirect:/home"; // Redirect to resource list view or dashboard
        } else {
            return "redirect:/login";
        }


    }
}
