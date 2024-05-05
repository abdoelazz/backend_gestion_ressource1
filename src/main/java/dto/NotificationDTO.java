package dto;

import java.util.List;

public class NotificationDTO {
	private String message;
	private Long userId;
	private List<Long> listeUserId;
	private Long depId;
	private Long emetteur_id;
	private List<Long> listeNotifId;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Long> getListeUserId() {
		return listeUserId;
	}

	public void setListeUserId(List<Long> listeUserId) {
		this.listeUserId = listeUserId;
	}

	public Long getDepId() {
		return depId;
	}

	public void setDepId(Long depId) {
		this.depId = depId;
	}

	public Long getEmetteur_id() {
		return emetteur_id;
	}

	public void setEmetteur_id(Long emetteur_id) {
		this.emetteur_id = emetteur_id;
	}

	public List<Long> getListeNotifId() {
		return listeNotifId;
	}

	public void setListeNotifId(List<Long> listeNotifId) {
		this.listeNotifId = listeNotifId;
	}

}
