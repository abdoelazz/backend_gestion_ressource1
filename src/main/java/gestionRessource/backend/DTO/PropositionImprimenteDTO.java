package gestionRessource.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropositionImprimenteDTO {

    private String marque;
    private String resolution;
    private double prix;
    private String vitesse;
    private Integer qte;
}
