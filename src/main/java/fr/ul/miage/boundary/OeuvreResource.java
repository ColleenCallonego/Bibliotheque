package fr.ul.miage.boundary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ul.miage.entity.Oeuvre;

public interface OeuvreResource extends JpaRepository<Oeuvre, String> {

    @Query("SELECT id FROM Oeuvre WHERE oeuvre_type = 'L' AND nom = ?1 AND sousNom = ?2")
    String findLivreByNomEtSousNom(String nom, String sousNom);

    @Query("SELECT id FROM Oeuvre WHERE oeuvre_type = 'M' AND nom = ?1 AND numero = ?2")
    String findMagazineByNomEtNumero(String nom, String numero);
}
