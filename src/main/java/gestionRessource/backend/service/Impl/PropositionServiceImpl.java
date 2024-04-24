package gestionRessource.backend.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestionRessource.backend.model.Proposition;
import gestionRessource.backend.repository.PropositionRepository;
import gestionRessource.backend.service.PropositionService;

@Service
public class PropositionServiceImpl implements PropositionService {

	@Autowired
	private PropositionRepository propositionRepository;

	@Override
	public Proposition saveProposition(Proposition proposition) {
		return propositionRepository.save(proposition);
	}

	@Override
	public List<Proposition> getPropositionOrderByMoinsDisant() {
		return propositionRepository.findByOrderByMontantTotalAsc();
	}

}
