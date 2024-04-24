package gestionRessource.backend.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestionRessource.backend.model.Detail;
import gestionRessource.backend.repository.DetailRepository;
import gestionRessource.backend.service.DetailService;

@Service
public class DetailServiceImpl implements DetailService {
	@Autowired
	private DetailRepository detailRepository;

	@Override
	public Detail saveDetail(Detail detail) {
		return detailRepository.save(detail);
	}
}
