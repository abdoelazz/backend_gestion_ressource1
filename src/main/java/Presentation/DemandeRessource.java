package Presentation;

import dto.RessourceDTO;
import model.*;
import service.RessourceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DemandeRessource {
    @Autowired
    private RessourceService ressourceService;
    //ajouterRessource
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
    @DeleteMapping("/deleteRessource")
    public ResponseEntity<String> deleteRessource(@RequestParam Long id) {
        try {
            ressourceService.deleteRessource(id);
            return new ResponseEntity<>("Resource deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting resource: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/updateDemande/{id}")
    public ResponseEntity<Ressource> updateRessource(@PathVariable Long id, @RequestBody RessourceDTO ressourceDto) {
        if (ressourceDto.getTypeRessource() == null) {
            return ResponseEntity.badRequest().build();
        }
        Ressource oldRessource = ressourceService.getRessourceById(id);
        if (ressourceDto.getTypeRessource().equals("Ordinateur") && oldRessource instanceof Ordinateur) {
            ((Ordinateur) oldRessource).setCpu(ressourceDto.getCpu());
            ((Ordinateur) oldRessource).setRam(ressourceDto.getRam());
            ((Ordinateur) oldRessource).setDisqueDur(ressourceDto.getDisqueDur());
            ((Ordinateur) oldRessource).setEcran(ressourceDto.getEcran());
        } else if (ressourceDto.getTypeRessource().equals("Imprimante") && oldRessource instanceof Imprimante) {
            oldRessource.setCodeInventaire(ressourceDto.getCodeInventaire());
            oldRessource.setEtatDemande(ressourceDto.getEtatDemande());
            ((Imprimante) oldRessource).setResolution(ressourceDto.getResolution());
            ((Imprimante) oldRessource).setVitesseImpression(ressourceDto.getVitesseImpression());
        } else {
            return ResponseEntity.badRequest().build();
        }

        Ressource updatedRessource = ressourceService.updateRessource(oldRessource);
        return ResponseEntity.ok(updatedRessource);
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
            ressource.setDepartement(currentUser.getDepartement());
            ressource.setUser(currentUser);
            ressource.setEtatDemande(EtatDemande.créée);
        }
        return ressource;
    }
}
