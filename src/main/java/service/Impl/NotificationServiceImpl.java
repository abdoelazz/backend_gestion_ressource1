package service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Notification;
import model.User;
import repository.NotificationRepository;
import repository.UserRepository;
import service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	public NotificationRepository notificationRepository;

	@Autowired
	public UserRepository userRepository;

	@Override
	public void AjouterNotification(Notification notification) {
		notificationRepository.save(notification);
	}

	@Override
	public List<Notification> getNotificationByUser(Long user_id) {
		Optional<User> optionalUser = userRepository.findById(user_id);
		User user = optionalUser.get();
		return notificationRepository.findByUser(user);
	}

	@Override
	public Notification getNotificationrById(Long notificationId) {
		Optional<Notification> notificationOptional = notificationRepository.findById(notificationId);
		return notificationOptional.orElse(null);

	}

}
