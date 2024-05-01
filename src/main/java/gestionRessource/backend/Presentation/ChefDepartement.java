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
    private UserController userController;  // Ensure UserController is appropriately annotated to be autowired.

    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false); // Retrieve existing session or null if no session exists
        if (session != null && session.getAttribute("user") instanceof User) { // Ensure the session contains a User object
            User currentUser = (User) session.getAttribute("user");
            model.addAttribute("fullName", currentUser.getFirst_name() + " " + currentUser.getLast_name());
            return "chefDepartement/homeChefDepartement"; // Make sure the path is correct
        } else {
            // If no session exists or no user is logged in, redirect to the login page
            return "redirect:/login"; // Use redirect to change the path, ensure /login is correctly mapped
        }
    }
}
