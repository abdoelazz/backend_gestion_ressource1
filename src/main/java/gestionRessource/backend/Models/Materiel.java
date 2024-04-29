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

@Inheritance(strategy = InheritanceType.JOINED)
public class Materiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String marque;
    @Column(unique = true)
    private String codeBarre;
    @Column
    private double prix;
    @Column(columnDefinition = "boolean default false")
    private boolean panne;
    @Enumerated(EnumType.STRING)
    private MaterielState materielState;
    @Column
    private LocalDate date_livraison;
    @Column
    private Integer duree_garentie;
    @Column(columnDefinition = "boolean default false")
    private boolean verifie;
    @ManyToOne
    @JoinColumn(name = "appel_offre_id")
    private AppelOffre appelOffre;
    @ManyToOne
    @JoinColumn(name = "enseignant_id")
    //@JsonIgnore
    private Enseignant enseignant;
}