package gestionRessource.backend.convert;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import gestionRessource.backend.dto.UserDTO;
import gestionRessource.backend.model.Notification;
import gestionRessource.backend.model.User;
import gestionRessource.backend.utils.PasswordEncoderUtil;

public class UserConvert {

	public static User convertUserDtoToUser(UserDTO userDto) {
		User user = new User();
		user.setFirst_name(userDto.getFirst_name());
		user.setLogin(userDto.getLogin());
		user.setLast_name(userDto.getLast_name());
		String encodedPassword = PasswordEncoderUtil.encodePassword(userDto.getPassword());
		user.setPassword(encodedPassword);
		user.setRole(userDto.getRole());
		return user;
	}

	public static UserDTO convertUserToUserDto(User user) {
		UserDTO userDto = new UserDTO();
		userDto.setFirst_name(user.getFirst_name());
		userDto.setLogin(user.getLogin());
		userDto.setLast_name(user.getLast_name());
		userDto.setRole(user.getRole());
		if (user.getNotifications() != null && !user.getNotifications().isEmpty()) {
			Comparator<Notification> comparator = Comparator.comparing(Notification::isEtat_lu)
					.thenComparing(Notification::getDate_envoi, Comparator.reverseOrder());
			List<Notification> sortedNotifications = user.getNotifications().stream().sorted(comparator)
					.collect(Collectors.toList());
			userDto.setNotificationList(sortedNotifications);

		}
		return userDto;
	}

}
