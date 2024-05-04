package gestionRessource.backend.Presentation;


import gestionRessource.backend.controller.NotificationController;
import gestionRessource.backend.controller.UserController;
import gestionRessource.backend.model.Notification;
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
import java.util.Objects;

@Controller
public class Home {

    @Autowired
    private NotificationController notificationController;
    @Autowired
    private UserController userController;

    @GetMapping("/home")
    public String showHomePage(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") instanceof User) {
            User currentUser = (User) session.getAttribute("user");
            List<Notification> notifications = notificationController.getNotificationByUser(currentUser.getId());
            if (Objects.nonNull(notifications)) {
                session.setAttribute("notifications", notifications);
            }
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

}
