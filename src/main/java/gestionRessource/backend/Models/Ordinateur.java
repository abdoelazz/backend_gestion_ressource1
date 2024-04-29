package gestionRessource.backend.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Ordinateur extends Materiel{
    @Column
    private String cpu;
    @Column
    private String disque;
    @Column
    private String ecran;
    @Column
    private String ram;


}
