package gestionRessource.backend.Presentation;

import gestionRessource.backend.model.*;
import gestionRessource.backend.service.RessourceService;
import jakarta.servlet.http.HttpServletRequest;
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
    public String showDemandeForm(Model model) {
        return "chefDepartement/ajouterDemandeRessource"; // Return the view for adding resources
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
            Ressource ressource = createRessource(typeDeRess, cpu, ram, ecran, disqueDur, resolution, vitesseImpression);
            if (ressource != null) {
                ressourceService.saveRessource(ressource);
                redirectAttributes.addFlashAttribute("successMessage", "Demande créée avec succès.");
                return "redirect:/home"; // Redirect to resource list view or dashboard
            } else {
                model.addAttribute("errorMessage", "Invalid resource type");
                return "home"; // Stay on the same page and show error
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
            return "home"; // Stay on the same page and show error
        }
    }

    private Ressource createRessource(String typeDeRess, String cpu, String ram, String ecran, String disqueDur, Integer resolution, Integer vitesseImpression) {
        Ressource ressource = null;
        if ("Ordinateur".equals(typeDeRess)) {
            ressource = new Ordinateur();
            ((Ordinateur) ressource).setCpu(cpu);
            ((Ordinateur) ressource).setRam(ram);
            ((Ordinateur) ressource).setEcran(ecran);
            ((Ordinateur) ressource).setDisqueDur(disqueDur);
        } else if ("Imprimante".equals(typeDeRess)) {
            ressource = new Imprimante();
            ((Imprimante) ressource).setResolution(resolution);
            ((Imprimante) ressource).setVitesseImpression(vitesseImpression);
        }
        if (ressource != null) {
            ressource.setDateCreation(new java.sql.Date(System.currentTimeMillis()));
            ressource.setEtatDemande(EtatDemande.créée);
        }
        return ressource;
    }
}
