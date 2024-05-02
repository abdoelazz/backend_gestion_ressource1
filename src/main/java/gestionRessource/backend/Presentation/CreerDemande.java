package gestionRessource.backend.Presentation;

import ch.qos.logback.core.model.Model;
import gestionRessource.backend.controller.UserController;
import gestionRessource.backend.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CreerDemande {

    @Autowired
    private UserController userController;
    @GetMapping("/creerDemande")
    public String home(HttpServletRequest request, org.springframework.ui.Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") instanceof User) {
            User currentUser = (User) session.getAttribute("user");
            model.addAttribute("fullName", currentUser.getFirst_name() + " " + currentUser.getLast_name());
            return "chefDepartement/creerDemande";
        } else {
            // If no session exists or no user is logged in, redirect to the login page
            return "redirect:/login"; // Use redirect to change the path, ensure /login is correctly mapped
        }

    }

}
