package gestionRessource.backend.Presentation;

import gestionRessource.backend.controller.NotificationController;
import gestionRessource.backend.controller.RessourceController;
import gestionRessource.backend.model.Notification;
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
import java.util.Objects;

@Controller
public class DemandeDepartement {

    @Autowired
    private RessourceService ressourceService;
    @Autowired
    private NotificationController notificationController;
    @GetMapping("/consulterDemandes")
    public String showDemandeDepartement(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") instanceof User) {
            User currentUser = (User) session.getAttribute("user");
            List<Notification> notifications = notificationController.getNotificationByUser(currentUser.getId());
            if (Objects.nonNull(notifications)) {
                session.setAttribute("notifications", notifications);
            }
            User user= (User) session.getAttribute("user");
            Long departmentId = user.getDepartement().getId();
            List<Ressource> ressources = ressourceService.getRessourcesEnseignantsByDepartement(departmentId);
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
