package gestionRessource.backend.Models;

public enum Notification {
    AjoutBesoin("Vous pouvez maintenat ajouter vous besoin"),
    Accept("Votre proposition est acceptee"),
    Refus("Votre proposition est refusee"),
    Elimination,
    NewBesoin("Nouvelles besoins sont ajoutes pour generer appel d'offre");

    private String value;
    private Notification(){}

    private Notification(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
