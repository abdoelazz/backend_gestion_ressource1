package gestionRessource.backend.Utils;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("L'utilisateur n'existe pas.");
    }
}