package gestionRessource.backend.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestionRessource.backend.model.Constat;
import gestionRessource.backend.model.Panne;
import gestionRessource.backend.repository.ConstatRepository;
import gestionRessource.backend.service.ConstatService;

@Service
public class ConstatServiceImpl implements ConstatService {
	@Autowired
	private ConstatRepository constatRepository;

	@Override
	public Constat saveConstat(Constat constat) {
		return constatRepository.save(constat);
	}

	@Override
	public Constat getConstatById(Long constat_id) {
		Optional<Constat> constatOptional = constatRepository.findById(constat_id);
		return constatOptional.get();
	}

	@Override
	public List<Constat> getAllConstat() {
		return constatRepository.findAll();
	}

	@Override
	public List<Constat> getConstatByPanne(Panne panne) {
		return constatRepository.findByPanne(panne);
	}
}
