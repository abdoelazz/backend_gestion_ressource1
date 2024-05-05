package service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Fournisseur;
import repository.FournisseurRepository;
import service.FournisseurService;

@Service
public class FournisseurServiceImpl implements FournisseurService {

	@Autowired
	private FournisseurRepository fournisseurRepository;

	@Override
	public Fournisseur getFournisseurById(Long id) {
		Optional<Fournisseur> fournisseurOptional = fournisseurRepository.findById(id);
		return fournisseurOptional.get();
	}

	@Override
	public Fournisseur ajouterFournisseur(Fournisseur fournisseur) {
		return fournisseurRepository.save(fournisseur);
	}

}
