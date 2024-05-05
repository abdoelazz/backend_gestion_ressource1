package gestionRessource.backend.Presentation;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/responsable")
public class Responsable {
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        return "responsable/dashboard";
    }
}
