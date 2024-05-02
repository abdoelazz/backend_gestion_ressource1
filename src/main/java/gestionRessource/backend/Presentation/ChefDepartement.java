package gestionRessource.backend.Presentation;

import gestionRessource.backend.controller.UserController;
import gestionRessource.backend.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chefDepartement")
public class ChefDepartement {

    @Autowired
    private UserController userController;

    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") instanceof User) {
            User currentUser = (User) session.getAttribute("user");
            model.addAttribute("fullName", currentUser.getFirst_name() + " " + currentUser.getLast_name());
            return "chefDepartement/homeChefDepartement";
        } else {

            return "redirect:/login";
        }

    }
}
