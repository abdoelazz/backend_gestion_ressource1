package service;

import java.util.List;

import model.Panne;

public interface PanneService {
	Panne savePanne(Panne panne);

	List<Panne> getAllPannes();

	Panne getPanneById(Long panne_id);

	List<Panne> getPannesByUser(Long user_id);

	List<Panne> getPanneByRessourceUser(Long user_id);
}
