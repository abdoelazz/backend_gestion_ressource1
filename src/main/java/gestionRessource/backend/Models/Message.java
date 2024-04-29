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
public class Message {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String message;
    private LocalDate date;
    @Column(columnDefinition = "boolean default false")
    private boolean vue;
    @ManyToOne
    @JoinColumn(name = "emetteur_id")
    private User emetteur;
    @ManyToOne
    @JoinColumn(name = "recepteur_id")
    private User recepteur;
    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;
}
