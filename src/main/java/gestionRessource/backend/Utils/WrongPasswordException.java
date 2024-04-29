package gestionRessource.backend.Utils;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("Le mot de passe actuel est incorrect.");
    }
}
