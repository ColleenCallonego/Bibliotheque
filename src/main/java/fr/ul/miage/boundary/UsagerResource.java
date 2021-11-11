package fr.ul.miage.boundary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ul.miage.entity.Usager;

public interface UsagerResource extends JpaRepository<Usager, String> {

    @Query("SELECT u FROM Usager u WHERE nom = ?1 AND prenom = ?2")
    Usager findByNomEtPrenom(String nom, String prenom);
}
