package gestionRessource.backend.Presentation;

import gestionRessource.backend.controller.RessourceController;
import gestionRessource.backend.controller.UserController;
import gestionRessource.backend.model.Ressource;
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
public class OrdinateurEnseignant {


        @Autowired
        private RessourceController ressourceController;


        @Autowired
        private UserController userController;

        @GetMapping("/ordinateurEnseignant")
        public String showOrdinateurEnseignantPage(HttpServletRequest request) {
            HttpSession session = request.getSession(false); // Retrieve existing session or null if no session exists
            if (session != null && session.getAttribute("user") != null) {
                User user= (User) session.getAttribute("user");
                List<Ressource> ressources= ressourceController.getRessourcesByUserId(user.getId());

                request.setAttribute("ressources", ressources);
                return "enseignant/Ordinateurs";
            } else {
                // If no session exists or no user is logged in, return the login page
                return "redirect:/login";
            }

        }


        @PostMapping("/ordinateurEnseignant")
        public String handleOrdinateurEnseignant(
                @RequestParam("username") String username,
                @RequestParam("password") String password,
                RedirectAttributes redirectAttributes


        ) {


            return username;
        }
    }
