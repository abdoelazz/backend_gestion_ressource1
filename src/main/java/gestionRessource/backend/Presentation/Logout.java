package gestionRessource.backend.Presentation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Logout {

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false); // Fetch the session and do not create a new one if it doesn't exist
        if (session != null) {
            session.invalidate(); // Invalidate the session if it exists
        }
        return "redirect:/login"; // Redirect to login page after logout
    }
}
