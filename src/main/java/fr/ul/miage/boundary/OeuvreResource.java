package fr.ul.miage.boundary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ul.miage.entity.Oeuvre;

public interface OeuvreResource extends JpaRepository<Oeuvre, String> {

    @Query("SELECT o FROM Oeuvre o WHERE oeuvre_type = 'L' AND nom = ?1 AND sousNom = ?2")
    Oeuvre findLivreByNomEtSousNom(String nom, String sousNom);

    @Query("SELECT o FROM Oeuvre o WHERE oeuvre_type = 'M' AND nom = ?1 AND numero = ?2")
    Oeuvre findMagazineByNomEtNumero(String nom, String numero);
}
