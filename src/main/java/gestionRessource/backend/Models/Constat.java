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
public class Constat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private LocalDate date_apparition;
    @Column
    private String explication_panne;
    @Column
    private String frequence;
    @Column
    private String ordre;
    @Column(columnDefinition = "boolean default false")
    private Boolean treated;
    @Column(columnDefinition = "boolean default false")
    private Boolean send;
    @ManyToOne
    @JoinColumn(name = "panne_id")
    private Panne panne;
}
