package gestionRessource.backend.Services;


import gestionRessource.backend.Models.*;
import gestionRessource.backend.DTO.*;
import gestionRessource.backend.Repositories.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MaterielService {
    @Autowired
    private AffectationRepository affectationRepository;
    @Autowired
    private ConstatRepository constatRepository;
    @Autowired
    private MatereilRepository matereilRepository;
    @Autowired
    private OrdinateurRepository ordinateurRepository;
    @Autowired
    private ImprimanteRepository imprimanteRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private PanneRepository panneRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private MessageRepository messageRepository;
    public void saveOrdinateur(Ordinateur ordinateur, int id){
        Enseignant ens=enseignantRepository.findById(id).get();
        ordinateur.setEnseignant(ens);
        ordinateurRepository.save(ordinateur);
    }
    public void saveImprimante(Imprimente imprimente,int id){
        Enseignant ens=enseignantRepository.findById(id).get();
        imprimente.setEnseignant(ens);
        imprimanteRepository.save(imprimente);

    }
    public List<MaterielDto> getMateriels(int id) throws Exception {
        List<MaterielDto> list = new ArrayList<>();
        Enseignant ens = getEnseignant(id);
        if (ens != null){
            List<Affectation> affectations = affectationRepository.findAffectationByEnseignant(ens);
            //List<Materiel> matList = matereilRepository.findMaterielByEnseignantAndAppelOffreNotNull(ens);
            for (int i =0;i<affectations.size();i++){
                Materiel mat = affectations.get(i).getMateriel();
                MaterielDto materielDto = MaterielDto.builder()
                        .id(mat.getId())
                        .marque(mat.getMarque())
                        .date_affectation(mat.getDate_livraison())
                        .duree_garentie(mat.getDuree_garentie())
                        .code_barre(mat.getCodeBarre())
                        .enseignant(ens.getNom())
                        .enPanne(mat.isPanne())
                        .materielState(mat.getMaterielState())
                        .build();
                list.add(materielDto);
            }
            return  list;
        }
        else
            throw new Exception("Enseignant n'existe pas");
    }
    public List<OrdinateurDto> getBesoinsOrdinateursOfEns(int id) throws Exception {
        List<OrdinateurDto> list = new ArrayList<>();
        Enseignant ens = getEnseignant(id);
        List<Materiel> malist =matereilRepository.findMaterielByEnseignantAndAppelOffreNullAndVerifieIsFalse(ens);
        for (int i =0;i<malist.size();i++){
            Materiel mat = malist.get(i);
            Ordinateur ordinateur= getordinateur(mat.getId());
            if (ordinateur!=null){
                OrdinateurDto ordinateurDto = OrdinateurDto.builder()
                        .id(mat.getId())
                        .cpu(ordinateur.getCpu())
                        .ram(ordinateur.getRam())
                        .ecran(ordinateur.getEcran())
                        .disque(ordinateur.getDisque())

                        .build();
                list.add(ordinateurDto);}
        }
        return  list;
    }

    public List<ImprimanteDto> getBesoinsImprimentesOfEns(int id) throws Exception {
        List<ImprimanteDto> list = new ArrayList<>();
        Enseignant ens = getEnseignant(id);
        List<Materiel> malist =matereilRepository.findMaterielByEnseignantAndAppelOffreNullAndVerifieIsFalse(ens);
        for (int i =0;i<malist.size();i++){
            Materiel mat = malist.get(i);
            Imprimente imprimente= getImprimente(mat.getId());
            if (imprimente!=null){
                ImprimanteDto imprimanteDto = ImprimanteDto.builder()
                        .id(mat.getId())
                        .resolution(imprimente.getResolution())
                        .vitesse(imprimente.getVitesse())

                        .build();
                list.add(imprimanteDto);}
        }
        return  list;
    }

    public Enseignant getEnseignant(int id){
        Optional<Enseignant> optionalEnseignant = enseignantRepository.findById(id);
        return optionalEnseignant.orElse(null);
    }
    public Ordinateur getordinateur(int id){
        return ordinateurRepository.findOrdinateurById(id);
    }
    public Imprimente getImprimente(int id){
        return imprimanteRepository.findImprimenteById(id);
    }
    public void supprimerMaterielOrdinateur(int id) {
        Ordinateur ordinateur = ordinateurRepository.findById(id).orElse(null);
        if (ordinateur != null) {
            ordinateurRepository.deleteById(id);
        }
    }
    public void supprimerMaterielImprimente(int id) {
        Imprimente imprimente = imprimanteRepository.findById(id).orElse(null);
        if (imprimente != null) {
            imprimanteRepository.deleteById(id);
        }
    }
    public void enPanne(int id){
        Materiel mat=matereilRepository.findMaterielById(id);
        Panne panne = Panne.builder().materiel(mat).datePanne(LocalDate.now()).build();
        mat.setPanne(true);
        mat.setMaterielState(MaterielState.EnPanne);
        matereilRepository.save(mat);
        panneRepository.save(panne);
    }
    public void materielstate(String id, String state,int id_constat, HttpServletRequest request){
        // id : materiel!!!!!!!!!!!!!!!!!
        String token = request.getHeader("Authorization");
        String jwt = token.substring(7);
        User user = tokenRepository.findTokenByToken(jwt).getUser();
        Materiel mat=matereilRepository.findMaterielByCodeBarre(id);
        if(mat != null){
            MaterielState materielState = MaterielState.valueOf(state);
            if(state.equals("REPAREE")){
                Panne panne = panneRepository.findPanneByMaterielAndTreatedIsFalse(mat);
                panne.setTreated(true);
                panne.setTechnicien(user);
                panneRepository.save(panne);
            }
            if(state.equals("EnReparation") || state.equals("DoitChange")){
                Constat constat = constatRepository.findById(id_constat).get();
                constat.setTreated(true);
                constatRepository.save(constat);
            }
            mat.setMaterielState(materielState);
            matereilRepository.save(mat);
        }
    }
    public void editOrdinateur(Ordinateur newOrdinateur){
        Ordinateur ordinateur =ordinateurRepository.findOrdinateurById(newOrdinateur.getId());
        newOrdinateur.setEnseignant(ordinateur.getEnseignant());
        //newOrdinateur.getEnseignant().setId(idEnsi);
        this.ordinateurRepository.save(newOrdinateur);
    }
    public void editImprimente(Imprimente newImprimente){
        Imprimente imprimente1=imprimanteRepository.findImprimenteById(newImprimente.getId());
        newImprimente.setEnseignant(imprimente1.getEnseignant());
        this.imprimanteRepository.save(newImprimente);
    }
    public List<BesoinChefOrdinateurDto> getMaterielsOrdinateursBesoins(HttpServletRequest request) throws Exception {
        String token = request.getHeader("Authorization");
        String jwt = token.substring(7);
        User userChef = userRepository.findUserById(tokenRepository.findTokenByToken(jwt).getUser().getId());
        Optional<Enseignant> Enseignant = enseignantRepository.findById(userChef.getId());
        if(Enseignant.isPresent()){
            List<BesoinChefOrdinateurDto> list = new ArrayList<>();
            List<Enseignant> ens=enseignantRepository.findEnseignantByDepartementEquals(Enseignant.get().getDepartement());
            for (int i =0;i<ens.size();i++){
                System.out.println("enseignat"+ens.get(i).getNom());
                List<Materiel> matList = matereilRepository.findMaterielByEnseignantAndAppelOffreNullAndVerifieIsFalse(ens.get(i));
                for (int j =0;j<matList.size();j++){
                    Materiel mat = matList.get(j);
                    if(mat!=null){
                        Ordinateur ordinateur=ordinateurRepository.findOrdinateurById(mat.getId());
                        if (ordinateur!=null){
                            User user= userRepository.findUserById(ens.get(i).getId());
                            if(user!=null){
                                BesoinChefOrdinateurDto materielOrdinateurDTO= BesoinChefOrdinateurDto.builder()
                                        .id(mat.getId())
                                        .nom(user.getNom())
                                        .prenom(user.getPrenom())
                                        .cpu(ordinateur.getCpu())
                                        .ram(ordinateur.getRam())
                                        .ecran(ordinateur.getEcran())
                                        .disque(ordinateur.getDisque())
                                        .build();
                                list.add(materielOrdinateurDTO);
                            }}
                    }}
            }
            return list;
        }
        return null;
    }

    public List<BesoinChefImprimenteDto> getMaterielsImprimentesBesoins(HttpServletRequest request) throws Exception {
        String token = request.getHeader("Authorization");
        String jwt = token.substring(7);
        User userChef = userRepository.findUserById(tokenRepository.findTokenByToken(jwt).getUser().getId());
        Optional<Enseignant> Enseignant = enseignantRepository.findById(userChef.getId());
        if(Enseignant.isPresent()){
            List<BesoinChefImprimenteDto> list = new ArrayList<>();
            List<Enseignant> ens=enseignantRepository.findEnseignantByDepartementEquals(Enseignant.get().getDepartement());
            for (int i =0;i<ens.size();i++){
                System.out.println("enseignat"+ens.get(i).getNom());
                List<Materiel> matList = matereilRepository.findMaterielByEnseignantAndAppelOffreNullAndVerifieIsFalse(ens.get(i));
                for (int j =0;j<matList.size();j++){
                    Materiel mat = matList.get(j);
                    if(mat!=null){
                        Imprimente imprimente=imprimanteRepository.findImprimenteById(mat.getId());
                        if (imprimente!=null){
                            User user= userRepository.findUserById(ens.get(i).getId());
                            BesoinChefImprimenteDto besoinChefImprimenteDto= BesoinChefImprimenteDto.builder()
                                    .id(mat.getId())
                                    .nom(user.getNom())
                                    .prenom(user.getPrenom())
                                    .resolution(imprimente.getResolution())
                                    .vitesse(imprimente.getVitesse())
                                    .build();
                            list.add(besoinChefImprimenteDto);
                        }
                    }}
            }
            return list;
        }
        return null;
    }
    public void validOrdinateurChef(Ordinateur newOrdinateur){
        Ordinateur ordinateur =ordinateurRepository.findOrdinateurById(newOrdinateur.getId());
        newOrdinateur.setEnseignant(ordinateur.getEnseignant());
        newOrdinateur.setVerifie(true);
        this.ordinateurRepository.save(newOrdinateur);
        sendMessageNewBesoinAreAded(ordinateur.getEnseignant());
    }
    public void validImprimenteChef(Imprimente newImprimente){
        Imprimente imprimente1=imprimanteRepository.findImprimenteById(newImprimente.getId());
        newImprimente.setEnseignant(imprimente1.getEnseignant());
        newImprimente.setVerifie(true);
        this.imprimanteRepository.save(newImprimente);
        sendMessageNewBesoinAreAded(newImprimente.getEnseignant());
    }

    private void sendMessageNewBesoinAreAded(Enseignant enseignant) {
        User admin = userRepository.findUserByRoleEquals(Role.RESPONSABLE);
        User chef = enseignantRepository.findEnseignantByDepartementAndRole(enseignant.getDepartement(), Role.CHEF_DEPARTEMENT);
        Message message = Message.builder()
                .message(Notification.NewBesoin.getValue())
                .recepteur(admin)
                .emetteur(chef)
                .date(LocalDate.now())
                .vue(false)
                .build();
        messageRepository.save(message);
    }

}