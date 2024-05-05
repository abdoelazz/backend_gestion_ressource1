package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Notification;
import model.User;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

	List<Notification> findByUser(User user);
}
