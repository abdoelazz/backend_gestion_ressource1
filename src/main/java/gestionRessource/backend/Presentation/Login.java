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

@Controller
public class Login {

    @Autowired
    private UserController userControler;

    @GetMapping("/login")
    public String showLoginPage(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Retrieve existing session or null if no session exists
        if (session != null && session.getAttribute("user") != null) {
            // If a session exists and a user is logged in, redirect to the home page
            return "redirect:/home";
        } else {
            // If no session exists or no user is logged in, return the login page
            return "login";
        }
    }


    @PostMapping("/Login")
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

        User user = userControler.authentification(authDto);

        if (user != null) {
            // Authentication successful
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "redirect:/home"; // Redirect to home page upon successful login
        } else {
            // Authentication failed
            model.addAttribute("loginError", "Invalid username or password");
            return "login"; // Stay on login page and show error
        }
    }



}


