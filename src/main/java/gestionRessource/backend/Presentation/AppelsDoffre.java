package gestionRessource.backend.Presentation;

import gestionRessource.backend.controller.AppelDoffreController;
import gestionRessource.backend.model.AppelDoffre;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AppelsDoffre {
        @Autowired
        AppelDoffreController appelDoffreController;
        @GetMapping("/appelsDoffre")
        public String showAppelsDoffrePage(HttpServletRequest request) {
            List<AppelDoffre> appelDoffres= appelDoffreController.getAppelDoffresNew();
            request.setAttribute("appelDoffres", appelDoffres);
            return "appelsDoffre";
        }

        @PostMapping("/appelsDoffre")
        public String handleAppelsDoffre(
                @RequestParam("username") String username,
                @RequestParam("password") String password,
                RedirectAttributes redirectAttributes
        ) {
            return username;
        }
    }
