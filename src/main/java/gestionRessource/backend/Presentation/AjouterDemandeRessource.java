package gestionRessource.backend.Presentation;

import gestionRessource.backend.model.*;
import gestionRessource.backend.service.RessourceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AjouterDemandeRessource {
    @Autowired
    private RessourceService ressourceService;

    @GetMapping("/ajouterRessource")
    public String showDemandeForm(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") instanceof User) {
            User currentUser = (User) session.getAttribute("user");
            if(currentUser.getRole() == Role.ChefDepartement)
            {
                return "chefDepartement/ajouterDemandeRessource";
            } else if (currentUser.getRole() == Role.Enseignant) {
                return "enseignant/ajouterDemandeRessource";
            }else {
                return "error";
            }
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/ajouterRessource")
    public String addRessource(
            @RequestParam("typeDeRess") String typeDeRess,
            @RequestParam(value = "cpu", required = false) String cpu,
            @RequestParam(value = "ram", required = false) String ram,
            @RequestParam(value = "ecran", required = false) String ecran,
            @RequestParam(value = "disqueDur", required = false) String disqueDur,
            @RequestParam(value = "resolution", required = false) Integer resolution,
            @RequestParam(value = "vitesseimpression", required = false) Integer vitesseImpression,
            Model model,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        try {
            Ressource ressource = createRessource(typeDeRess, cpu, ram, ecran, disqueDur, resolution, vitesseImpression,request);
            if (ressource != null) {
                ressourceService.saveRessource(ressource);
                redirectAttributes.addFlashAttribute("successMessage", "Demande créée avec succès.");
                return "redirect:/home";
            } else {
                model.addAttribute("errorMessage", "Invalid resource type");
                return "home";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
            return "home";
        }
    }

    private Ressource createRessource(String typeDeRess, String cpu, String ram, String ecran, String disqueDur, Integer resolution, Integer vitesseImpression,HttpServletRequest request) {
        Ressource ressource = null;
        if ("Ordinateur".equals(typeDeRess)) {
            ressource = new Ordinateur();
            ressource.setTypeRessource("Ordinateur");
            ((Ordinateur) ressource).setCpu(cpu);
            ((Ordinateur) ressource).setRam(ram);
            ((Ordinateur) ressource).setEcran(ecran);
            ((Ordinateur) ressource).setDisqueDur(disqueDur);
        } else if ("Imprimante".equals(typeDeRess)) {
            ressource = new Imprimante();
            ressource.setTypeRessource("Imprimante");
            ((Imprimante) ressource).setResolution(resolution);
            ((Imprimante) ressource).setVitesseImpression(vitesseImpression);
        }
        if (ressource != null) {
            ressource.setDateCreation(new java.sql.Date(System.currentTimeMillis()));
            HttpSession session = request.getSession(false);
            User currentUser = (User) session.getAttribute("user");
            ressource.setUser(currentUser);
            ressource.setEtatDemande(EtatDemande.créée);
        }
        return ressource;
    }
}
