package gestionRessource.backend.Presentation;

import gestionRessource.backend.dto.UserDTO;
import gestionRessource.backend.model.User;
import gestionRessource.backend.service.DepartementService;
import gestionRessource.backend.service.UserService;
import gestionRessource.backend.utils.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Registration {

    @Autowired
    private UserService userService;

    @Autowired
    private DepartementService departementService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("departments", departementService.getAllDepartement());
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
        newUser.setPassword(PasswordEncoderUtil.encodePassword(password));
        // Assuming that you have setters for other fields like email and nomSociete
        // newUser.setEmail(email);
        // newUser.setNomSociete(nomSociete);

        // Assuming that the user's role and department are identified by departementId
        // and you have a way to set the role, perhaps with a default role value or a dedicated method.
        // newUser.setRole(defaultUserRole); // You would set this according to your business logic
        newUser.setDepartement(departementService.getDepartementById(departementId)); // Setting the department

        try {
            User savedUser = userService.saveUser(newUser);
            if (savedUser != null) {
                return "redirect:/login"; // Redirect to login after successful registration
            } else {
                model.addAttribute("errorMessage", "Registration failed: User could not be saved.");
                return "register"; // Returns back to the registration form with an error message
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Registration failed: " + e.getMessage());
            return "register"; // Returns back to the registration form with an error message
        }
    }
}
