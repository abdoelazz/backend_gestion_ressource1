package gestionRessource.backend.Presentation;

import gestionRessource.backend.controller.UserController;
import gestionRessource.backend.dto.AuthentificationDTO;
import gestionRessource.backend.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class UpdatePassword {
        @Autowired
        private UserController userController;
        //updatePassword
        @PostMapping("/updatePassword")
        public String handleUpdatePassword(
                @RequestParam("password") String password,
                HttpServletRequest request,
                RedirectAttributes redirectAttributes
        ) {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("user") instanceof User) {
                User currentUser = (User) session.getAttribute("user");
                userController.modifyPasswordUser(currentUser.getId(),password);
                redirectAttributes.addFlashAttribute("successMessage", "Mot de passe a ete modifié avec succee");
                return "redirect:/home";
            } else {
                return "redirect:/login";
            }


        }

    }
