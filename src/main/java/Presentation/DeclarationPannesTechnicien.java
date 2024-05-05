package Presentation;


import controller.NotificationController;
import controller.PanneController;
import controller.RessourceController;
import model.Ressource;
import model.Role;
import model.User;
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

@Controller
public class DeclarationPannesTechnicien {

    @Autowired
    private RessourceController ressourceController;
    @Autowired
    private PanneController panneController;
    @Autowired
    private NotificationController notificationController;
    //resoudrePannes
    @GetMapping("/resoudrePannes")
    public String showResoudrePannesPage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") instanceof User) {
            User currentUser = (User) session.getAttribute("user");
            List<Ressource> ressources= ressourceController.getAllRessources();

            request.setAttribute("ressources", ressources);
            if(currentUser.getRole() == Role.Technicien)
            {
                return "technicien/declarationsPanne";
            }else {
                return "redirect:/login";
            }
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/resoudrePannes")
    public String handleResoudrePannes(
            @RequestParam("typeDeRess") String typeDeRess,
            @RequestParam(value = "ordinateur" , required = false) Long ressourceId1,
            @RequestParam(value = "imprimante" , required = false) Long ressourceId2,
            @RequestParam("detail") String detail,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        try {
            if(typeDeRess.equals("Ordinateur"))
            {
                panneController.addPanneToRessource(ressourceId1,detail);
                redirectAttributes.addFlashAttribute("successMessage", "Declaration créée avec succès.");
                return "redirect:/home";
            } else if (typeDeRess.equals("Imprimante")) {
                panneController.addPanneToRessource(ressourceId2,detail);
                redirectAttributes.addFlashAttribute("successMessage", "Declaration créée avec succès.");
                return "redirect:/home";

            }
            else
                redirectAttributes.addFlashAttribute("errorMessage", "Une erreur est servenue.");
            return "panne";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
            return "panne";
        }
    }
}
