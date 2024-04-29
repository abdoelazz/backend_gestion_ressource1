package gestionRessource.backend.Services;


import gestionRessource.backend.DTO.AffectationDto;
import gestionRessource.backend.Models.*;
import gestionRessource.backend.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AffectationService {
    @Autowired
    private AffectationRepository affectationRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private MaterielPropositionRepository materielPropositionRepository;
    @Autowired
    private OrdinateurRepository ordinateurRepository;
    @Autowired
    private ImprimanteRepository imprimanteRepository;
    @Autowired
    private MatereilRepository matereilRepository;
    public List<Affectation> getAffectedRessources(){
        return affectationRepository.findAll();
    }
    public List<Materiel> getNonAffectedRessources() {
        List<Affectation> list = affectationRepository.findAll();
        List<Materiel> allMateriel = matereilRepository.findAllByCodeBarreIsNotNull();
        List<Materiel> nonAffectedMateriel = new ArrayList<>();

        for (Materiel m : allMateriel) {
            boolean found = false;
            for (Affectation a : list) {
                if (a.getMateriel().equals(m)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                nonAffectedMateriel.add(m);
            }
        }

        return nonAffectedMateriel;
    }
    public void retirerRessourceAffecte(Integer id){
        affectationRepository.deleteById(id);
    }
    public void retirerRessourceNonAffecte(Integer id){
        Materiel_Proposition materielProposition=materielPropositionRepository.findMateriel_PropositionByMaterielId(id);
        if(materielProposition!=null){
            materielPropositionRepository.deleteById(materielProposition.getId());
        }else {
            matereilRepository.deleteById(id);
        }

    }
    public  List<Enseignant> getEnsOfdepartement(String departeemnt){
return enseignantRepository.findEnseignantByDepartementEquals(departeemnt);
    }
    public  void addAffectation(AffectationDto affectationDto){
        Affectation affectation=new Affectation();
        Enseignant enseignant =enseignantRepository.findById(affectationDto.getId_enseignant()).get();
        Materiel materiel=matereilRepository.findById(affectationDto.getId_materiel()).get();
        affectation.setEnseignant(enseignant);
        affectation.setMateriel(materiel);
        affectation.setDate_affectation(affectationDto.getDate_affecatation());
        affectation.setDepartement(affectationDto.getDepartement());
        affectationRepository.save(affectation);
    }

}
