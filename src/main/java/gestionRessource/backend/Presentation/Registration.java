package gestionRessource.backend.Presentation;


import ch.qos.logback.core.model.Model;
import gestionRessource.backend.Models.User;
import gestionRessource.backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Registration {

    @Autowired
    private UserService userService;



    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "register"; // Looks for /WEB-INF/jsp/register.jsp
    }

    @PostMapping("/register")
    public String registerUserAccount(@RequestParam(name = "username") String username,
                                      @RequestParam(name = "password") String password,
                                      // ... include other parameters as needed
                                      @RequestParam(name = "departementId") Long departementId, // Assuming this is the selected department
                                      Model model) {
        User newUser = new User();
        newUser.setLogin(username);
        // Assuming that you have setters for other fields like email and nomSociete
        // newUser.setEmail(email);
        // newUser.setNomSociete(nomSociete);

        // Assuming that the user's role and department are identified by departementId
        // and you have a way to set the role, perhaps with a default role value or a dedicated method.
        // newUser.setRole(defaultUserRole); // You would set this according to your business logic

//        try {
//            User savedUser = userService.saveUser(newUser);
//            if (savedUser != null) {
//                return "redirect:/login"; // Redirect to login after successful registration
//            } else {
//                model.addAttribute("errorMessage", "Registration failed: User could not be saved.");
//                return "register"; // Returns back to the registration form with an error message
//            }
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", "Registration failed: " + e.getMessage());
//            return "register"; // Returns back to the registration form with an error message
//        }
        return "register";
    }
}
