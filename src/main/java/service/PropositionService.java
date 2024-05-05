package service;

import java.util.List;
import java.util.Optional;

import model.Proposition;

public interface PropositionService {

	Proposition saveProposition(Proposition proposition);

	List<Proposition> getPropositionOrderByMoinsDisant();

	List<Proposition> getPropositionByFournisseur(Long fournisseurId);

	Proposition getPropositionByFournisseurAndAppelDoffre(Long fournisseurId, Long appelDoffreId);

	List<Proposition> getAllPropositions();

	Proposition getPropositionbyId(Long id);

	Optional<Proposition> PropositionbyID(Long id);


}
