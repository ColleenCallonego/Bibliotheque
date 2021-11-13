package fr.ul.miage.boundary;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ul.miage.assembler.EmpruntAssembler;
import fr.ul.miage.entity.Emprunt;
import fr.ul.miage.entity.Exemplaire;
import fr.ul.miage.entity.Usager;

@RestController
@RequestMapping(value = "/emprunts")
@ExposesResourceFor(Emprunt.class)
public class EmpruntRepresentation {
    @Autowired
    EmpruntResource repository;
    @Autowired
    EmpruntAssembler assembler;

    @GetMapping(value = "/{empruntId}")
    public ResponseEntity<?> getOneEmprunt(@PathVariable("empruntId") String id) {
        return Optional.ofNullable(repository.findById(id)).filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(assembler.toModel(i.get()))).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> getAllEmprunts() {
        return ResponseEntity.ok(assembler.toCollectionModel(repository.findAll()));
    }

    @PostMapping(value = "/creer")
    public String creerEmprunt(Exemplaire exemplaire, Usager usager) {
        Emprunt e = new Emprunt("En cours", exemplaire, usager);
        repository.save(e);
        return "Emprunt créé";
    }

    @DeleteMapping(value = "/supprimer")
    public String supprimerEmprunt(Emprunt emprunt) {
        repository.delete(emprunt);
        return "Emprunt supprimé";
    }

    @PatchMapping(value = "/modifier")
    public String modifier(Emprunt emprunt, String etat) {
        emprunt.setEtat(etat);
        repository.save(emprunt);
        return "Emprunt modifié";
    }

    @GetMapping(value = "/identification")
    public Emprunt identifier(Exemplaire exemplaire, Usager usager) {
        Emprunt emprunt = repository.findEmpruntByExemplaireEtUsager(exemplaire, usager);
        return emprunt;
    }
}
