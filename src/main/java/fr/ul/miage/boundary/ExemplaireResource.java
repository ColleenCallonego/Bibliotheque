package fr.ul.miage.boundary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ul.miage.entity.Exemplaire;
import fr.ul.miage.entity.Oeuvre;

public interface ExemplaireResource extends JpaRepository<Exemplaire, String> {

    @Query("SELECT e FROM Exemplaire e WHERE etat ='En rayon' AND oeuvre_id = ?1")
    public List<Exemplaire> findByEtatEtOeuvre(Oeuvre oeuvre);

    @Query("SELECT e FROM Exemplaire e WHERE oeuvre_id = ?1 AND codeExemplaire = ?2")
    public Exemplaire findByOeuvreEtCode(Oeuvre oeuvre, String codeExemplaire);

    @Query("SELECT e FROM Exemplaire e WHERE oeuvre_id = ?1")
    public List<Exemplaire> findByOeuvre(Oeuvre oeuvre);

    @Query("SELECT e FROM Exemplaire e WHERE etat ='Reserve' AND oeuvre_id = ?1")
    public List<Exemplaire> findByEtatEtOeuvreReserve(Oeuvre oeuvre);
}
