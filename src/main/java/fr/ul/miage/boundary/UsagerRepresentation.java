package fr.ul.miage.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.ul.miage.assembler.UsagerAssembler;
import fr.ul.miage.entity.Usager;

import java.util.Optional;

import javax.transaction.Transactional;

@RestController
@RequestMapping(value = "/usagers")
@ExposesResourceFor(Usager.class)

public class UsagerRepresentation {
    @Autowired
    UsagerResource repository;
    @Autowired
    UsagerAssembler assembler;

    @GetMapping(value = "/{usagerId}")
    public ResponseEntity<?> getOneUsager(@PathVariable("usagerId") String id) {
        return Optional.ofNullable(repository.findById(id)).filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(assembler.toModel(i.get()))).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> getAllUsagers() {
        return ResponseEntity.ok(assembler.toCollectionModel(repository.findAll()));
    }

    @PostMapping(value = "/creer")
    @Transactional
    public String creerUsager(String nom, String prenom, String mail, String adresse) {
        Usager u = new Usager(nom, prenom, mail, adresse, 0);
        repository.save(u);
        return "Usager créé";
    }

    @GetMapping(value = "/identification")
    public Usager identifier(String nom, String prenom) {
        return repository.findByNomEtPrenom(nom, prenom);
    }

    @DeleteMapping(value = "/supprimer")
    @Transactional
    public String supprimer(Usager usager) {
        repository.delete(usager);
        return "Usager supprimé";
    }

    @PatchMapping(value = "/modifier")
    @Transactional
    public String modifier(Usager usager, String nom, String prenom, String mail, String adresse) {
        usager.setNom(nom);
        usager.setPrenom(prenom);
        usager.setMail(mail);
        usager.setAdresse(adresse);
        repository.save(usager);
        return "Usager modifié";
    }
}