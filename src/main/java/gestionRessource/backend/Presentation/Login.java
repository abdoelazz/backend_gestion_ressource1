package gestionRessource.backend.Presentation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gestionRessource.backend.controller.UserController;
import gestionRessource.backend.dto.AuthentificationDTO;
import gestionRessource.backend.model.User;
import gestionRessource.backend.model.Role;

@Controller
public class Login {

    @Autowired
    private UserController userController;

    @GetMapping("/login")
    public String showLoginPage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User currentUser = (User) session.getAttribute("user");
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            RedirectAttributes redirectAttributes,
            Model model,
            HttpServletRequest request
    ) {
        AuthentificationDTO authDto = new AuthentificationDTO();
        authDto.setLogin(username);
        authDto.setPassword(password);

        User user = userController.authentification(authDto);

        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            return "redirect:/home";
        } else {
            model.addAttribute("loginError", "Invalid username or password");
            return "login";
        }
    }

}
