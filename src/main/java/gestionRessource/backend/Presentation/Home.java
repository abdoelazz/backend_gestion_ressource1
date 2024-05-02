package gestionRessource.backend.Presentation;
import ch.qos.logback.core.model.Model;
import gestionRessource.backend.controller.UserController;

import gestionRessource.backend.model.Role;
import gestionRessource.backend.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Home {


    @Autowired
    private UserController userControler;

    @GetMapping("/home")
    public String showHomePage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") instanceof User) {
            User currentUser = (User) session.getAttribute("user");
            return determineRedirectByRole(currentUser.getRole());
        } else {

            return "redirect:/login";
        }

    }



    @PostMapping("/home")
    public String handleHome(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            RedirectAttributes redirectAttributes


    ) {


        return username;
    }

    private String determineRedirectByRole(Role role) {
        switch (role) {
            case ChefDepartement:
                return "chefDepartement/homeChefDepartement";
            case Responsable:
                return "responsable/homeResponsable";
            case Enseignant:
                return "enseignant/homeEnseignant";
            case Fournisseur:
                return "fournisseur/homeFournisseur";
            case Technicien:
                return "technicien/homeTechnicien";
            default:
                return "/login";
        }
    }







    @GetMapping("/")
    public String index() {

        return "login";  // This will look for /WEB-INF/jsp/index.jsp
    }
    @ExceptionHandler(Exception.class)
    public String handleError() {
        return "error";  // Redirect to the error JSP page
    }
}
