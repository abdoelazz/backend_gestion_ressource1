package gestionRessource.backend.DTO;

import gestionRessource.backend.Models.MaterielState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterielDto {
    private int id;
    private String enseignant;
    private String marque;
    private String code_barre;
    private LocalDate date_affectation;
    private int duree_garentie;
    private boolean enPanne;
    private MaterielState materielState;
    private LocalDate date_livraison;
}
