package service.Impl;

import java.util.List;
import java.util.Optional;

import model.AppelDoffre;
import model.Fournisseur;
import repository.AppelDoffreRepository;
import repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Proposition;
import repository.PropositionRepository;
import service.PropositionService;

@Service
public class PropositionServiceImpl implements PropositionService {

	@Autowired
	private PropositionRepository propositionRepository;

	@Autowired
	private FournisseurRepository fournisseurRepository;

	@Autowired
	private AppelDoffreRepository appelDoffreRepository;

	@Override
	public Proposition saveProposition(Proposition proposition) {
		return propositionRepository.save(proposition);
	}

	@Override
	public List<Proposition> getPropositionOrderByMoinsDisant() {
		return propositionRepository.findByOrderByMontantTotalAsc();
	}

	@Override
	public List<Proposition> getPropositionByFournisseur(Long fournisseurId) {
		Optional<Fournisseur> optionalFournisseur = fournisseurRepository.findById(fournisseurId);
		Fournisseur fournisseur = optionalFournisseur.get();
		return propositionRepository.findByFournisseur(fournisseur);
	}

	@Override
	public Proposition getPropositionByFournisseurAndAppelDoffre(Long fournisseurId, Long appelDoffreId) {
		Optional<Fournisseur> optionalFournisseur = fournisseurRepository.findById(fournisseurId);
		Fournisseur fournisseur = optionalFournisseur.get();
		Optional<AppelDoffre> optionalAppelDoffre = appelDoffreRepository.findById(appelDoffreId);
		AppelDoffre appelDoffre = optionalAppelDoffre.get();
		return propositionRepository.findByFournisseurAndAppelDoffre(fournisseur, appelDoffre);
	}

	@Override
	public List<Proposition> getAllPropositions() {
		return propositionRepository.findAll();
	}

	@Override
	public Optional<Proposition> PropositionbyID(Long id) {
		return propositionRepository.findById(id);
	}

	@Override
	public Proposition getPropositionbyId(Long id) {
		Optional<Proposition> propositionOptional = propositionRepository.findById(id);
		return propositionOptional.get();
	}


}
