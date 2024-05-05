package service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Departement;
import repository.DepartementRepository;
import service.DepartementService;

@Service
public class DepartementServiceImpl implements DepartementService {

	@Autowired
	private DepartementRepository departementRepository;

	@Override
	public Departement getDepartementById(Long id) {
		Optional<Departement> departementOptional = departementRepository.findById(id);
		return departementOptional.get();
	}

	@Override
	public Departement ajouterDepartement(Departement departement) {
		return departementRepository.save(departement);
	}

	@Override
	public void deleteDepartement(Long id) {
		departementRepository.deleteById(id);
	}

	@Override
	public List<Departement> getAllDepartement() {
		return departementRepository.findAll();
	}

}
