package gestionRessource.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FournisseurDto {
    private String nomSociete;
    private Integer id;
    private String adresse;
    private String gerant;

    private String email;
}
