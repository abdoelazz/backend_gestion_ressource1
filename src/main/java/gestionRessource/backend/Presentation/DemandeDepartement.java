package gestionRessource.backend.Presentation;

import gestionRessource.backend.controller.RessourceController;
import gestionRessource.backend.model.Ressource;
import gestionRessource.backend.model.User;
import gestionRessource.backend.service.RessourceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class DemandeDepartement {

    @Autowired
    private RessourceService ressourceService;
    @GetMapping("/consulterDemandes")
    public String showDemandeDepartement(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user= (User) session.getAttribute("user");
            Long departmentId = user.getDepartement().getId();
            List<Ressource> ressources = ressourceService.getRessourcesByDep(departmentId);
            for (Ressource ressource:ressources
                 ) {
                ressource.getTypeRessource();
            }
            model.addAttribute("resources", ressources);
            return "chefDepartement/consulterDemandes";
        } else {

            return "redirect:/login";
        }

    }
}
