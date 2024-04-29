package gestionRessource.backend.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Value;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Fournisseur{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String nomSociete;
    @Column
    private String pass;
    @Column
    private String adresse;
    @Column
    @Value("${listeNoir:false}")
    private  boolean listeNoir;
    @Column
    private String email;
    @Column
    private String gerant;
}