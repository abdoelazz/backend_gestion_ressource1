package service.Impl;

import model.Proposition;
import repository.PropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Detail;
import repository.DetailRepository;
import service.DetailService;

import java.util.List;
import java.util.Optional;

@Service
public class DetailServiceImpl implements DetailService {
	@Autowired
	private DetailRepository detailRepository;

	@Autowired
	private PropositionRepository propositionRepository;

	@Override
	public Detail saveDetail(Detail detail) {
		return detailRepository.save(detail);
	}

	@Override
	public List<Detail> getDetailByProposition(Long propositionId) {
		Optional<Proposition> propositionOptional = propositionRepository.findById(propositionId);
		Proposition proposition = propositionOptional.get();
		return detailRepository.findByProposition(proposition);
	}
}
