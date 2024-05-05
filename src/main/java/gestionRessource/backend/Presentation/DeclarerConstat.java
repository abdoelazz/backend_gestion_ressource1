package gestionRessource.backend.Presentation;

import gestionRessource.backend.controller.ConstatController;
import gestionRessource.backend.controller.UserController;
import gestionRessource.backend.dto.ConstatDTO;
import gestionRessource.backend.model.FrequenceConstat;
import gestionRessource.backend.model.Ordre;
import gestionRessource.backend.model.Role;
import gestionRessource.backend.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;

@Controller
public class DeclarerConstat {
        @Autowired
        private ConstatController constatController;
        @PostMapping("/declarerConstat")
        public String handleUpdatePassword(
                @RequestParam("panneId") Long panneId,
                @RequestParam("explication") String explication,
                @RequestParam("frequenceConstat")
                FrequenceConstat frequenceConstat,
                @RequestParam("ordre")
                Ordre ordre,
                @RequestParam("dateApparition")
                Date dateApparition,
                HttpServletRequest request,
                RedirectAttributes redirectAttributes
        ) {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("user") instanceof User) {
                User currentUser = (User) session.getAttribute("user");
                if(currentUser.getRole()== Role.Technicien)
                {
                    ConstatDTO constatDTO=new ConstatDTO(explication,frequenceConstat,ordre,panneId,dateApparition);
                    constatController.addConstat(constatDTO);
                    redirectAttributes.addFlashAttribute("successMessage", "Mot de passe a ete modifié avec succee");
                    return "redirect:/home";
                }
                return "home";

            } else {
                return "redirect:/login";
            }


        }

    }
