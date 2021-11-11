package fr.ul.miage.boundary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ul.miage.entity.Usager;

public interface UsagerResource extends JpaRepository<Usager, String> {

    @Query("SELECT id FROM Usager WHERE nom = ?1")
    String findByNom(String nom);
}
