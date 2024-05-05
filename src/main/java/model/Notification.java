package model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Notification")
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "contenu")
	private String contenu;

	@Column(name = "date_envoi")
	private Date date_envoi;

	@Column(name = "etat_lu")
	private boolean etat_lu;

	@ManyToOne
	@JsonIncludeProperties({ "login" })
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "emetteur_id")
	@JsonIncludeProperties({ "id" })
	private User emetteur;

	public Notification() {
		super();
	}

	public Notification(Long id, String contenu, Date date_envoi, boolean etat_lu, User user, User emetteur) {
		super();
		this.id = id;
		this.contenu = contenu;
		this.date_envoi = date_envoi;
		this.etat_lu = etat_lu;
		this.user = user;
		this.emetteur = emetteur;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Date getDate_envoi() {
		return date_envoi;
	}

	public void setDate_envoi(Date date_envoi) {
		this.date_envoi = date_envoi;
	}

	public boolean isEtat_lu() {
		return etat_lu;
	}

	public void setEtat_lu(boolean etat_lu) {
		this.etat_lu = etat_lu;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getEmetteur() {
		return emetteur;
	}

	public void setEmetteur(User emetteur) {
		this.emetteur = emetteur;
	}

}
