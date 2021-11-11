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

    @PostMapping(value = "/create")
    @Transactional
    @ResponseBody
    public String createUsager(String nom, String prenom, String mail, String adresse) {
        Usager u = new Usager(nom, prenom, mail, adresse, 0);
        repository.save(u);
        return "Usager is created";
    }

}