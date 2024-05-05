package service;

import java.util.List;

import model.Constat;
import model.Panne;

public interface ConstatService {

	Constat saveConstat(Constat constat);

	Constat getConstatById(Long constat_id);

	List<Constat> getAllConstat();

	List<Constat> getConstatByPanne(Panne panne);
}
