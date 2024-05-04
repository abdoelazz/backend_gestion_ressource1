package gestionRessource.backend.Presentation;

import gestionRessource.backend.controller.*;
import gestionRessource.backend.model.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
public class DeclarerPanne {

    @Autowired
    private RessourceController ressourceController;
    @Autowired
    private PanneController panneController;
    @Autowired
    private NotificationController notificationController;
    @GetMapping("/declarerPanne")
    public String showDeclarerPannePage(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Retrieve existing session or null if no session exists
        if (session != null && session.getAttribute("user") instanceof User) {
            User currentUser = (User) session.getAttribute("user");
            List<Notification> notifications = notificationController.getNotificationByUser(currentUser.getId());
            if (Objects.nonNull(notifications)) {
                session.setAttribute("notifications", notifications);
            }
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
            @RequestParam("typeDeRess") String typeDeRess,
            @RequestParam(value = "ordinateur" , required = false) Long ressourceId1,
            @RequestParam(value = "imprimante" , required = false) Long ressourceId2,
            @RequestParam("detail") String detail,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        System.out.println(typeDeRess);
        try {
            if(typeDeRess.equals("Ordinateur"))
            {
                panneController.addPanneToRessource(ressourceId1,detail);
                redirectAttributes.addFlashAttribute("successMessage", "Declaration créée avec succès.");
                return "redirect:/home"; // Redirect to resource list view or dashboard
            } else if (typeDeRess.equals("Imprimante")) {
                panneController.addPanneToRessource(ressourceId2,detail);
                redirectAttributes.addFlashAttribute("successMessage", "Declaration créée avec succès.");
                return "redirect:/home"; // Redirect to resource list view or dashboard

            }
            else
            redirectAttributes.addFlashAttribute("errorMessage", "Une erreur est servenue.");
            return "panne"; // Stay on the same page and show error
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
            return "panne"; // Stay on the same page and show error
        }
    }
}
