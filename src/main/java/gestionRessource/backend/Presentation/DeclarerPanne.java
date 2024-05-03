package gestionRessource.backend.Presentation;

import gestionRessource.backend.controller.DepartementController;
import gestionRessource.backend.controller.RessourceController;
import gestionRessource.backend.controller.UserController;
import gestionRessource.backend.model.Departement;
import gestionRessource.backend.model.Ressource;
import gestionRessource.backend.model.Role;
import gestionRessource.backend.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class DeclarerPanne {

    @Autowired
    private RessourceController ressourceController;
    @GetMapping("/declarerPanne")
    public String showDeclarerPannePage(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Retrieve existing session or null if no session exists
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<Ressource> ressources= ressourceController.getRessourcesByUserId(user.getId());

            request.setAttribute("ressources", ressources);
            if(user.getRole() == Role.Enseignant)
            {
                return "enseignant/panne";
            } else if (user.getRole() == Role.ChefDepartement) {

                return "chefDepartement/panne";
            }else {
                return "redirect:/login";
            }
        } else {
            return "redirect:/login";
        }
    }


    @PostMapping("/declarerPanne")
    public String handleDeclarerPanne(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            RedirectAttributes redirectAttributes


    ) {


        return username;
    }
}
