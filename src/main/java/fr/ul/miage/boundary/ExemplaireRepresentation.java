package fr.ul.miage.boundary;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ul.miage.entity.Exemplaire;
import fr.ul.miage.entity.Oeuvre;

@RestController
@RequestMapping(value = "/exemplaires")
@ExposesResourceFor(Exemplaire.class)
public class ExemplaireRepresentation {
    @Autowired
    ExemplaireResource repository;

    @PostMapping(value = "/creer")
    @Transactional
    public String creerExemplaire(Oeuvre oeuvre, String edition, Date dateParution, String codeExemplaire) {
        Exemplaire e = new Exemplaire(oeuvre, "En rayon", edition, dateParution, codeExemplaire);
        repository.save(e);
        return "Exemplaire crée";
    }

    @DeleteMapping(value = "/supprimer")
    @Transactional
    public String supprimer(Exemplaire exemplaire) {
        repository.delete(exemplaire);
        return "Exemplaire supprimé";
    }

    @PatchMapping(value = "/modifier")
    @Transactional
    public String modifier(String id, String etat) {
        Optional<Exemplaire> body = repository.findById(id);
        Exemplaire e = body.get();
        e.setEtat(etat);
        repository.save(e);
        return "Exemlaire modidié";
    }

    @GetMapping(value = "/exemplaireDisponible")
    public Exemplaire exemplaireDispo(String id) {
        Exemplaire exemplaire = repository.findByEtatEtOeuvre(id);
        return exemplaire;
    }

    @GetMapping(value = "/identification")
    public Exemplaire identifier(Oeuvre oeuvre, String codeExemplaire) {
        Exemplaire exemplaire = repository.findByOeuvreEtCode(oeuvre, codeExemplaire);
        return exemplaire;
    }
}
