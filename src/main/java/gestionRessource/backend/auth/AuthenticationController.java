package gestionRessource.backend.auth;


import gestionRessource.backend.DTO.DepartementDto;
import gestionRessource.backend.config.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;
  private final LogoutService logoutService;

  @CrossOrigin(origins="*")
  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
          @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @CrossOrigin(origins="*")
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
          @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }
  @CrossOrigin("*")
  @GetMapping("/logout")
  public ResponseEntity<String> logout(HttpServletRequest request) {
    logoutService.logout(request,null,null);
    return new ResponseEntity<>("déconnexion réussie", HttpStatus.OK);
  }
  @GetMapping("/getDepartement/{id}")
  public DepartementDto getDepartement(@PathVariable String id) {
    return service.getDepartement(Integer.parseInt(id));

  }


}