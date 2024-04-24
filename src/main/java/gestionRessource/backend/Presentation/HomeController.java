package gestionRessource.backend.Presentation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {

        return "index";  // This will look for /WEB-INF/jsp/index.jsp
    }
}
