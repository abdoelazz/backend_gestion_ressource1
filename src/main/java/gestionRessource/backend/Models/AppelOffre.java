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
public class AppelOffre {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private LocalDate dateDebut;
    @Column
    private LocalDate dateFin;
}