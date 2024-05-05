package service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Departement;
import model.Ressource;
import model.User;
import repository.DepartementRepository;
import repository.RessourceRepository;
import repository.UserRepository;
import service.RessourceService;

@Service
public class RessourceServiceImpl implements RessourceService {

	@Autowired
	private RessourceRepository ressourceRepository;

	@Autowired
	private DepartementRepository departementRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Ressource> getAllResources() {
		return ressourceRepository.findAllByOrderByEtatDemandeDesc();
	}

	@Override
	public void deleteRessource(Long id) {
		ressourceRepository.deleteById(id);
	}

	@Override
	public Ressource updateRessource(Ressource ressource) {
		return ressourceRepository.save(ressource);
	}

	@Override
	public Ressource getRessourceById(Long id) {
		Optional<Ressource> optionalRessource = ressourceRepository.findById(id);
		return optionalRessource.get();
	}

	@Override
	public Ressource saveRessource(Ressource ressource) {
		return ressourceRepository.save(ressource);
	}

	@Override
	public List<Ressource> getRessourceByUserId(Long userId) {
		return ressourceRepository.findByUserIdOrderByEtatDemandeDesc(userId);
	}

	@Override
	public List<Ressource> getRessourceDelivred() {
		return ressourceRepository.findAllByCodeInventaireEmpty();
	}

	@Override
	public List<Ressource> getRessourcesByDep(Long depId) {
		Optional<Departement> optionalDep = departementRepository.findById(depId);
		Departement dep = optionalDep.get();
		return ressourceRepository.findByDepartement(dep);
	}

	@Override
	public List<Ressource> getRessourcesEnseignantsByDepartement(Long deptId) {
		return ressourceRepository.getRessourcesEnseignantsByDepartement(deptId);
	}

	@Override
	public List<Ressource> getRessourcesByEnseignant(Long enseignantId) {
		Optional<User> optionalUser = userRepository.findById(enseignantId);
		User enseignant = optionalUser.get();
		return ressourceRepository.findByEnseignant(enseignant);
	}

}
