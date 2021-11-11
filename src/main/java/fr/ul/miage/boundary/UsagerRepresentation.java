package fr.ul.miage.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.web.bind.annotation.*;

import fr.ul.miage.entity.Usager;

import javax.transaction.Transactional;

@RestController
@RequestMapping(value = "/usagers")
@ExposesResourceFor(Usager.class)

public class UsagerRepresentation {
    @Autowired
    UsagerResource repository;

    @PostMapping(value = "/creer")
    @Transactional
    @ResponseBody
    public String creerUsager(String nom, String prenom, String mail, String adresse) {
        Usager u = new Usager(nom, prenom, mail, adresse, 0);
        repository.save(u);
        return "Usager créé";
    }

    @GetMapping(value = "/identification")
    @ResponseBody
    public String identifier(String nom, String prenom) {
        String id = repository.findByNomEtPrenom(nom, prenom);
        System.out.println(id);
        return id;
    }

    @DeleteMapping(value = "/supprimer")
    @Transactional
    public String supprimer(String nom, String prenom) {
        String id = repository.findByNomEtPrenom(nom, prenom);
        repository.deleteById(id);
        return "Usager supprimé";
    }

    @PutMapping(value = "/modifier")
    @Transactional
    public String modifier(String nom, String prenom, String mail, String adresse) {
        String id = repository.findByNomEtPrenom(nom, prenom);
        Integer penalite = repository.findPenalite(id);
        Usager u = new Usager(nom, prenom, mail, adresse, penalite);
        u.setId(id);
        repository.save(u);
        return "Usager modifié";
    }
}