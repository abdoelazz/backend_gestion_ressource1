package Presentation;

import controller.NotificationController;
import controller.UserController;
import model.Notification;
import model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/chefDepartement")
public class ChefDepartement {

    @Autowired
    private UserController userController;
    @Autowired
    private NotificationController notificationController;
    //home
    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") instanceof User) {
            User currentUser = (User) session.getAttribute("user");
            List<Notification> notifications = notificationController.getNotificationByUser(currentUser.getId());
            if (Objects.nonNull(notifications)) {
                session.setAttribute("notifications", notifications);
            }
            model.addAttribute("fullName", currentUser.getFirst_name() + " " + currentUser.getLast_name());
            return "chefDepartement/homeChefDepartement";
        } else {

            return "redirect:/login";
        }

    }
}
