package gestionRessource.backend.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Imprimente extends Materiel{
    @Column
    private String resolution;
    @Column
    private String vitesse;
}
