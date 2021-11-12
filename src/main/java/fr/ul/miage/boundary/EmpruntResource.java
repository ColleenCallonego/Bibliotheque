package fr.ul.miage.boundary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ul.miage.entity.Emprunt;
import fr.ul.miage.entity.Exemplaire;
import fr.ul.miage.entity.Usager;

public interface EmpruntResource extends JpaRepository<Emprunt, String> {

    @Query("SELECT e FROM Emprunt e WHERE exemplaire_id = ?1 AND usager_id = ?2 AND etat = 'En cours'")
    public Emprunt findEmpruntByExemplaireEtUsager(Exemplaire exemplaire, Usager usager);
}
