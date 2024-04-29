package gestionRessource.backend.auth;



import gestionRessource.backend.DTO.DepartementDto;
import gestionRessource.backend.Models.*;
import gestionRessource.backend.Repositories.EnseignantRepository;
import gestionRessource.backend.Repositories.FournisseurRepository;
import gestionRessource.backend.Repositories.TokenRepository;
import gestionRessource.backend.Repositories.UserRepository;
import gestionRessource.backend.config.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService {
    private final UserRepository repository;
    private final EnseignantRepository enseignantRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final FournisseurRepository fournisseurRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = new User();
        if(request.getRole().name().equals("ENSEIGNANT") || request.getRole().name().equals("CHEF_DEPARTEMENT")){
            Enseignant enseignant=new Enseignant();
            enseignant.setDepartement(request.getDepartement());
            enseignantRepository.save(enseignant);
            user = repository.findUserById(enseignant.getId());
            user.setLogin(request.getLogin());
            user.setNom(request.getNom());
            user.setPrenom(request.getPrenom());
            user.setTelephone(request.getTelephone());
            user.setPass(passwordEncoder.encode(request.getPassword()));
            user.setRole(request.getRole());
        }else
        if(request.getRole().name().equals("FOURNISSEUR")){
                Fournisseur fournisseur = Fournisseur.builder()
                        .nomSociete(request.getLogin())
                        .pass(passwordEncoder.encode(request.getPassword()))
                        .build();
                fournisseurRepository.save(fournisseur);
                user = User.builder()
                        .login(request.getLogin())
                        .role(request.getRole())
                        .pass(passwordEncoder.encode(request.getPassword())).build();
            }
            else{
                user = User.builder()
                        .login(request.getLogin())
                        .nom(request.getNom())
                        .prenom(request.getPrenom())
                        .pass(passwordEncoder.encode(request.getPassword()))
                        .role(request.getRole())
                        .telephone(request.getTelephone())
                        .build();
            }
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        var user = repository.findByLogin(request.getLogin()).orElseThrow();
        var role = repository.findRoleByLogin(request.getLogin());
        var id = repository.findIdByLogin(request.getLogin());
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(role)
                .id(id)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
    public DepartementDto getDepartement(Integer id){
        DepartementDto departementDto=new DepartementDto();
        departementDto.setDepartement(enseignantRepository.findById(id).get().getDepartement());
        return departementDto;
    }
}