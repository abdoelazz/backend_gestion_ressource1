package gestionRessource.backend.Presentation;

import gestionRessource.backend.model.Role;
import gestionRessource.backend.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

public class index {
    @GetMapping("/")
    public String index() {

        return "login";  // This will look for /WEB-INF/jsp/index.jsp
    }
    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") instanceof User) {
            User currentUser = (User) session.getAttribute("user");

            return "error";
        } else {

            return "redirect:/login";
        }
    }

    private String determineRedirectByRole(Role role) {
        switch (role) {
            case ChefDepartement:
                return "chefDepartement/error";
            case Responsable:
                return "responsable/error";
            case Enseignant:
                return "enseignant/error";
            case Fournisseur:
                return "fournisseur/error";
            case Technicien:
                return "technicien/error";
            default:
                return "/login";
        }
    }
}
