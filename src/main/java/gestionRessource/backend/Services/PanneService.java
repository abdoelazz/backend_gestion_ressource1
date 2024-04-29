package gestionRessource.backend.Services;

import gestionRessource.backend.Models.*;
import gestionRessource.backend.DTO.*;
import gestionRessource.backend.Repositories.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class PanneService {
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MatereilRepository matereilRepository;
    @Autowired
    private ConstatRepository constatRepository;
    @Autowired
    private PanneRepository panneRepository;
    @Autowired
    private OrdinateurRepository ordinateurRepository;
    @Autowired
    private ImprimanteRepository imprimanteRepository;

    public List<PanneOrdinateurDTO> ordinateurEnPanne(){
        List<Panne> pannes = panneRepository.findAllByTreatedIsFalse();
        System.err.println(pannes.size());
        List<PanneOrdinateurDTO> materiels = new ArrayList<>();
        for(int i=0;i< pannes.size();i++){
            if(pannes.get(i).getMateriel().getMaterielState().name().equals("EnPanne")){
                Integer id = pannes.get(i).getMateriel().getId();
                Ordinateur ordinateur = ordinateurRepository.findOrdinateurById(id);
                Enseignant Enseignant = pannes.get(i).getMateriel().getEnseignant();
                if(ordinateur != null){
                    PanneOrdinateurDTO panneOrdinateurDTO=PanneOrdinateurDTO.builder()
                            .id_mat(ordinateur.getId())
                            .enseignant(Enseignant.getNom()+" "+Enseignant.getPrenom())
                            .code_barre(ordinateur.getCodeBarre())
                            .marque(ordinateur.getMarque())
                            .cpu(ordinateur.getCpu())
                            .ram(ordinateur.getRam())
                            .ecran(ordinateur.getEcran())
                            .disque(ordinateur.getDisque())
                            .build();
                    materiels.add(panneOrdinateurDTO);
                }
            }
        }
        return materiels;
    }
    public List<PanneImprimenteDTO> imprimenteEnPanne(){
        List<Panne> pannes = panneRepository.findAllByTreatedIsFalse();
        List<PanneImprimenteDTO> materiels = new ArrayList<>();
        for(int i=0;i< pannes.size();i++){
            Integer id = pannes.get(i).getMateriel().getId();
            Imprimente imprimente = imprimanteRepository.findImprimenteById(id);
            Enseignant Enseignant = pannes.get(i).getMateriel().getEnseignant();
            if(imprimente != null) {
                if (pannes.get(i).getMateriel().getMaterielState().name().equals("EnPanne")) {
                    PanneImprimenteDTO panneImprimenteDTO = PanneImprimenteDTO.builder()
                            .id_mat(imprimente.getId())
                            .enseignant(Enseignant.getNom() + " " + Enseignant.getPrenom())
                            .code_barre(imprimente.getCodeBarre())
                            .marque(imprimente.getMarque())
                            .resolution(imprimente.getResolution())
                            .vitesse(imprimente.getVitesse())
                            .build();
                    materiels.add(panneImprimenteDTO);
                }
            }
        }
        return materiels;
    }

    public void ajouterConstat(ConstatDTO constatDTO){
        Materiel materiel = matereilRepository.findMaterielByCodeBarreAndMaterielState(constatDTO.getCode_barre(),MaterielState.EnPanne);
        if(materiel != null){
            Panne panne = panneRepository.findPanneByMaterielAndTreatedIsFalse(materiel);
            if (panne != null){
                panne.setTechnicien(userRepository.findUserById(constatDTO.getId_technicien()));
                panne.setTreated(true);
                panneRepository.save(panne);
                Constat constat = Constat.builder()
                        .date_apparition(constatDTO.getDate_apparition())
                        .explication_panne(constatDTO.getExplication_panne())
                        .frequence(constatDTO.getFrequence())
                        .ordre(constatDTO.getOrdre())
                        .panne(panne)
                        .treated(false)
                        .send(false)
                        .build();
                constatRepository.save(constat);
            }
        }
        else{
            throw new RuntimeException("materiel n'existe pas");
        }
    }

    public List<ConstatDTO> getConstats(HttpServletRequest request){
        List<Constat> constats = null;
        String token = request.getHeader("Authorization");
        String jwt = token.substring(7);
        User user = tokenRepository.findTokenByToken(jwt).getUser();
        if (user.getRole().equals(Role.RESPONSABLE)){
            constats = constatRepository.findAllBySendIsTrue();
        }
        else{
            /*List<Panne> pannes = panneRepository.findAllByTechnicien(user);
            System.out.println(constatRepository.findConstatByPanne(pannes.get(0)).getExplication_panne());
            for(int i =0;i<pannes.size();i++){
                constats.add(constatRepository.findConstatByPanne(pannes.get(i)));
            }*/
            constats = constatRepository.findAllBySendIsFalse();
        }
        List<ConstatDTO> constatDTOS = new ArrayList<>();
        if(constats != null){
            for(int i=0;i< constats.size();i++){
                ConstatDTO constatDTO = ConstatDTO.builder()
                        .id_constat(constats.get(i).getId())
                        .code_barre(constats.get(i).getPanne().getMateriel().getCodeBarre())
                        .date_apparition(constats.get(i).getDate_apparition())
                        .explication_panne(constats.get(i).getExplication_panne())
                        .frequence(constats.get(i).getFrequence())
                        .ordre(constats.get(i).getOrdre())
                        .treated(constats.get(i).getTreated())
                        .send(constats.get(i).getSend())
                        .build();
                constatDTOS.add(constatDTO);
            }
        }
        return constatDTOS;
    }

    public void sendConstat(Integer id) {
        Constat constat = constatRepository.findById(id).get();
        if(constat != null){
            constat.setSend(true);
            constatRepository.save(constat);
        }
    }

    public void deleteConstat(Integer id) {
        Constat constat = constatRepository.findById(id).get();
        if(constat != null){
           constatRepository.deleteById(id);
        }
    }
}
