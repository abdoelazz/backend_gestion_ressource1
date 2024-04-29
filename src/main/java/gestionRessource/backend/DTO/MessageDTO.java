package gestionRessource.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDTO {
    private Integer idFournisseur;
private Integer id;
    private String message;
    private String emteur;
    private LocalDate date;
    private  Integer idem;
    private  Integer idrec;
    private boolean exsist;



}
