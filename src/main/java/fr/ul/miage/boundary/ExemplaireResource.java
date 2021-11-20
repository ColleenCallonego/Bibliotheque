package fr.ul.miage.boundary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ul.miage.entity.Exemplaire;

public interface ExemplaireResource extends JpaRepository<Exemplaire, String> {

    @Query("SELECT e FROM Exemplaire e WHERE etat ='En rayon' AND oeuvre = ?1")
    public List<Exemplaire> findByEtatEtOeuvre(String oeuvre);

    @Query("SELECT e FROM Exemplaire e WHERE oeuvre = ?1 AND codeExemplaire = ?2")
    public Exemplaire findByOeuvreEtCode(String oeuvre, String codeExemplaire);

    @Query("SELECT e FROM Exemplaire e WHERE oeuvre = ?1")
    public List<Exemplaire> findByOeuvre(String oeuvre);

    @Query("SELECT e FROM Exemplaire e WHERE etat ='Reserve' AND oeuvre = ?1")
    public List<Exemplaire> findByEtatEtOeuvreReserve(String oeuvre);
}
