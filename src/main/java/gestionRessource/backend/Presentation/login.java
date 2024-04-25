package gestionRessource.backend.Presentation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import gestionRessource.backend.controler.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gestionRessource.backend.controler.UserControler;
import gestionRessource.backend.dto.AuthentificationDTO;
import gestionRessource.backend.model.User;

@Controller
public class login {

    @Autowired
    private UserControler userControler;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // This will look for login.jsp or login.html
    }

    @PostMapping("/Login")
    public String handleLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            RedirectAttributes redirectAttributes,
            Model model,
            HttpServletRequest request // Inject HttpServletRequest object

    ) {
        AuthentificationDTO authDto = new AuthentificationDTO();
        authDto.setLogin(username);
        authDto.setPassword(password);

        User user = userControler.authentification(authDto);

        if (user != null) {
            // Login successful
            // Redirect to a secure page, or set user in session, etc.
            redirectAttributes.addFlashAttribute("message", "Login successful!");
            HttpSession session = request.getSession();
            // Store data in the session
            session.setAttribute("username", "john_doe");
            System.out.println(session.getAttribute("username"));
            return "redirect:/home"; // Change to your index page
        } else {
            // Login failed
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Return to login page with error message
        }
    }


}