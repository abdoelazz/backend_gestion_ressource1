package gestionRessource.backend.Presentation;
import gestionRessource.backend.controller.UserController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Home {


    @Autowired
    private UserController userControler;

    @GetMapping("/home")
    public String showHomePage(HttpServletRequest request) {
//        HttpSession session = request.getSession(false); // Retrieve existing session or null if no session exists
//        if (session != null && session.getAttribute("user") != null) {
//            // If a session exists and a user is logged in, redirect to the home page
//            return "home";
//        } else {
//            // If no session exists or no user is logged in, return the login page
//            return "redirect:/login";
//        }
        return "homeResponsable";
    }


    @PostMapping("/home")
    public String handleHome(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            RedirectAttributes redirectAttributes


    ) {


        return username;
    }









    @GetMapping("/")
    public String index() {

        return "index";  // This will look for /WEB-INF/jsp/index.jsp
    }
    @ExceptionHandler(Exception.class)
    public String handleError() {
        return "error";  // Redirect to the error JSP page
    }
}
