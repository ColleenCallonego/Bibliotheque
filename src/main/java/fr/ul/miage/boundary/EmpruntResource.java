package fr.ul.miage.boundary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ul.miage.entity.Emprunt;

public interface EmpruntResource extends JpaRepository<Emprunt, String> {

    @Query("SELECT e FROM Emprunt e WHERE exemplaire = ?1 AND usager = ?2 AND etat = 'En cours'")
    public Emprunt findEmpruntByExemplaireEtUsager(String exemplaire, String usager);
}
