package gestionRessource.backend.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gestionRessource.backend.dto.NotificationDTO;
import gestionRessource.backend.model.Notification;
import gestionRessource.backend.model.Role;
import gestionRessource.backend.model.User;
import gestionRessource.backend.service.NotificationService;
import gestionRessource.backend.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/notification")
public class NotificationController {
	@Autowired
	private NotificationService notificationService;

	@Autowired
	private UserService userService;

	@PostMapping("/addNotificationForDepartement")
	public List<Notification> addNotificationForDepartement(@RequestBody NotificationDTO notificationDto) {
		List<Notification> notifList = new ArrayList<Notification>();
		List<User> userList = userService.getUsersByRoleAndDep(Role.Enseignant, notificationDto.getDepId());
		User emetteur = userService.getUserById(notificationDto.getEmetteur_id());
		if (userList != null && !userList.isEmpty()) {
			for (User user : userList) {
				Notification notif = new Notification();
				notif.setContenu(notificationDto.getMessage());
				notif.setDate_envoi(new Date(System.currentTimeMillis()));
				notif.setEtat_lu(false);
				notif.setUser(user);
				notif.setEmetteur(emetteur);
				notificationService.AjouterNotification(notif);
				notifList.add(notif);

			}
		}
		return notifList;
	}

	@PostMapping("/addNotificationForUser")
	public Notification addNotificationForUser(@RequestBody NotificationDTO notificationDto) {
		User user = userService.getUserById(notificationDto.getUserId());
		User emetteur = userService.getUserById(notificationDto.getEmetteur_id());
		if (user != null) {
			Notification notif = new Notification();
			notif.setContenu(notificationDto.getMessage());
			notif.setDate_envoi(new Date(System.currentTimeMillis()));
			notif.setEtat_lu(false);
			notif.setUser(user);
			notif.setEmetteur(emetteur);
			notificationService.AjouterNotification(notif);
			return notif;
		}

		return null;
	}

	@PostMapping("/addNotificationForListUser")
	public List<Notification> addNotificationForListUser(@RequestBody NotificationDTO notificationDto) {
		List<Notification> notifList = new ArrayList<Notification>();
		User emetteur = userService.getUserById(notificationDto.getEmetteur_id());
		if (notificationDto.getListeUserId() != null && !notificationDto.getListeUserId().isEmpty()) {
			for (Long userId : notificationDto.getListeUserId()) {
				User user = userService.getUserById(userId);
				if (user != null) {
					Notification notif = new Notification();
					notif.setContenu(notificationDto.getMessage());
					notif.setDate_envoi(new Date(System.currentTimeMillis()));
					notif.setEtat_lu(false);
					notif.setUser(user);
					notif.setEmetteur(emetteur);
					notificationService.AjouterNotification(notif);
					notifList.add(notif);
				}

			}
		}
		return notifList;
	}

	@GetMapping("/getNotificationByUser")
	public List<Notification> getNotificationByUser(@RequestParam Long user_id) {
		return notificationService.getNotificationByUser(user_id);
	}

	@PutMapping("/modifierEtatNotification")
	public List<Notification> modifierEtatNotification(@RequestBody NotificationDTO notifDto) {
		List<Notification> notifications = new ArrayList<Notification>();
		if (notifDto.getListeNotifId() != null && !notifDto.getListeNotifId().isEmpty()) {
			for (Long notifId : notifDto.getListeNotifId()) {
				Notification notif = notificationService.getNotificationrById(notifId);
				notif.setEtat_lu(true);
				notificationService.AjouterNotification(notif);
				notifications.add(notif);
			}
		}
		return notifications;

	}

}
