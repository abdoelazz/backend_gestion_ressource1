package gestionRessource.backend.Repositories;


import gestionRessource.backend.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Integer> {
    @Override
    List<Enseignant> findAll();

    List<Enseignant> findEnseignantByDepartementEquals(String deparetemt);

    Enseignant getById(Integer id);

//    @Query(nativeQuery = true , value =
//            "SELECT e.* FROM Enseignant e INNER JOIN Materiel m ON m.enseignant_id = e.id"
//    )
//    List<Enseignant> findEnseignantByMateriel();
/**************************************/
    @Query(nativeQuery = true,value="select ens.departement,user.* from enseignant ens,user user, materiel m where user.id=ens.id and ens.id=m.enseignant_id and m.id= :id ")
    Enseignant getEnseignant(@Param("id") Integer id);


    //    @Query(nativeQuery = true , value =
//            "SELECT e.* FROM Enseignant e INNER JOIN Materiel m ON m.enseignant_id = e.id"
//    )
//    List<Enseignant> findEnseignantByMateriel();

    /**************************************/

    List<Enseignant> findEnseignantsByDepartement (String dept);

    Enseignant findEnseignantByDepartementAndRole(String dept, Role role);


}
