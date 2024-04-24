package gestionRessource.backend.service;

import java.util.List;

import gestionRessource.backend.model.Constat;
import gestionRessource.backend.model.Panne;

public interface ConstatService {

	Constat saveConstat(Constat constat);

	Constat getConstatById(Long constat_id);

	List<Constat> getAllConstat();

	List<Constat> getConstatByPanne(Panne panne);
}
