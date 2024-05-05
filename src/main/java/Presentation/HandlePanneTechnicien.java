package Presentation;

import controller.PanneController;
import dto.PanneDTO;
import model.EtatPanne;
import model.Role;
import model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HandlePanneTechnicien {
        @Autowired
        private PanneController panneController;
        //affecterPanne
        @PostMapping("/affecterPanne")
        public String handleAffecterPanneTechnicien(
                @RequestParam("panneId") Long panneId,
                HttpServletRequest request,
                RedirectAttributes redirectAttributes
        ) {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("user") instanceof User) {
                User currentUser = (User) session.getAttribute("user");
                if(currentUser.getRole() == Role.Technicien)
                {
                    panneController.affectPanneToTechnicien(panneId,currentUser.getId());
                    redirectAttributes.addFlashAttribute("successMessage", "Panne affectée avec succee");
                    return "redirect:/home";

                }
                return "redirect:/login";

            } else {
                return "redirect:/login";
            }


        }

    @PostMapping("/changerEtatPanne")
    public String handlEchangerEtatPanne(
            @RequestParam("panneId") Long panneId,
            @RequestParam("nouvelEtat") String nouvelEtat,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes
    ) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") instanceof User) {
            User currentUser = (User) session.getAttribute("user");
            EtatPanne etatPanne=EtatPanne.NonRepare;
            if(currentUser.getRole() == Role.Technicien)
            {
                switch (nouvelEtat) {
                    case "NonTraite":
                        etatPanne = EtatPanne.NonRepare;
                        break;
                    case "Repare":
                        etatPanne = EtatPanne.Repare;
                        break;
                    case "EnCours":
                        etatPanne = EtatPanne.EnCours;
                        break;
                    case "Severe":
                        etatPanne = EtatPanne.Severe;
                        break;
                }
                PanneDTO panneDTO = new PanneDTO(etatPanne);
                panneController.modifyPanne(panneId,panneDTO);
                redirectAttributes.addFlashAttribute("successMessage", "Etat de la panne changé avec succee");
                return "redirect:/home";

            }
            return "redirect:/login";

        } else {
            return "redirect:/login";
        }
    }
    }
