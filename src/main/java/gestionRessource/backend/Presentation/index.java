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

        return "login";
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
                return "error";
            case Responsable:
                return "error";
            case Enseignant:
                return "error";
            case Fournisseur:
                return "error";
            case Technicien:
                return "error";
            default:
                return "/login";
        }
    }
}
