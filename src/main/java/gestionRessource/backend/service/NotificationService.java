package gestionRessource.backend.service;

import java.util.List;

import gestionRessource.backend.model.Notification;

public interface NotificationService {
	void AjouterNotification(Notification notification);

	List<Notification> getNotificationByUser(Long user_id);

	Notification getNotificationrById(Long notificationId);

}
