package gestionRessource.backend.dto;

import java.util.List;

import gestionRessource.backend.model.Notification;
import gestionRessource.backend.model.Role;

public class UserDTO {
	private String first_name;
	private String last_name;
	private String login;
	private String password;
	private List<Notification> notificationList;
	private Role role;
	private Long departementId;

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Notification> getNotificationList() {
		return notificationList;
	}

	public void setNotificationList(List<Notification> notificationList) {
		this.notificationList = notificationList;
	}

	public Long getDepartementId() {
		return departementId;
	}

	public void setDepartementId(Long departementId) {
		this.departementId = departementId;
	}

}
