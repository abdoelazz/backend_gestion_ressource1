package Presentation;

import model.Fournisseur;
import model.Role;
import service.FournisseurService;
import utils.PasswordEncoderUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Registration {

    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "register";
    }
    //register
    @PostMapping("/register")
    public String registerFournisseurAccount(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "societe") String societe,
            @RequestParam(name = "first_name") String first_name,
            @RequestParam(name = "last_name") String last_name,
            Model model,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {

        Fournisseur newFournisseur = new Fournisseur();
        newFournisseur.setLogin(username);
        newFournisseur.setPassword(PasswordEncoderUtil.encodePassword(password));
        newFournisseur.setSociete(societe);
        newFournisseur.setFirst_name(first_name);
        newFournisseur.setLast_name(last_name);
        newFournisseur.setRole(Role.Fournisseur);

        try {
            Fournisseur savedFournisseur = fournisseurService.ajouterFournisseur(newFournisseur);
            if (savedFournisseur != null) {
                redirectAttributes.addFlashAttribute("registrationSuccess", true);
                model.addAttribute("successMessage", "Registration completed successfully.");
                return "redirect:/login";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Registration failed. Please try again.");
                model.addAttribute("errorMessage", "Registration failed: Fournisseur could not be saved.");
                return "register";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Registration failed: " + e.getMessage());
            return "register";
        }
    }

}
