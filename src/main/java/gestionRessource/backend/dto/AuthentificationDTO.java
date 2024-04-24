package gestionRessource.backend.dto;

public class AuthentificationDTO {
	private String login;
	private String password;


	public String getLogin() {
		return login;
	}

	public AuthentificationDTO setLogin(String login) {
		this.login = login;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public AuthentificationDTO setPassword(String password) {
		this.password = password;
		return this;
	}

}
