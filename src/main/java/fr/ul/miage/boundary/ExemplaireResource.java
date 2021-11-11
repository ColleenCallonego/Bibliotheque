package fr.ul.miage.boundary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ul.miage.entity.Exemplaire;
import fr.ul.miage.entity.Oeuvre;

public interface ExemplaireResource extends JpaRepository<Exemplaire, String> {

    @Query("SELECT e FROM Exemplaire e WHERE etat ='En rayon' AND oeuvre_id = ?1")
    public Exemplaire findByEtatEtOeuvre(String id);

    @Query("SELECT e FROM Exemplaire e WHERE oeuvre_id = ?1 AND codeExemplaire = ?2")
    public Exemplaire findByOeuvreEtCode(Oeuvre oeuvre, String codeExemplaire);
}
