package gestionRessource.backend.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Affectation {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String departement;
    @ManyToOne
    @JoinColumn(name = "materiel_id")
    private Materiel materiel;
    @ManyToOne
    @JoinColumn(name = "enseignant_id")
    private Enseignant enseignant;
    @Column
    private LocalDate date_affectation;
}