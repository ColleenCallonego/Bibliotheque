package fr.ul.miage.boundary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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

import fr.ul.miage.assembler.ExemplaireAssembler;
import fr.ul.miage.entity.Exemplaire;
import fr.ul.miage.entity.Oeuvre;

@RestController
@RequestMapping(value = "/exemplaires")
@ExposesResourceFor(Exemplaire.class)
public class ExemplaireRepresentation {
    @Autowired
    ExemplaireResource repository;
    @Autowired
    ExemplaireAssembler assembler;

    @GetMapping(value = "/{exemplaireId}")
    public ResponseEntity<?> getOneExemplaire(@PathVariable("exemplaireId") String id) {
        return Optional.ofNullable(repository.findById(id)).filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(assembler.toModel(i.get()))).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> getAllExemplaires() {
        return ResponseEntity.ok(assembler.toCollectionModel(repository.findAll()));
    }

    @PostMapping(value = "/creer")
    @Transactional
    public String creerExemplaire(Oeuvre oeuvre, String edition, String dateParution, String codeExemplaire) throws ParseException {
        Exemplaire e = new Exemplaire(oeuvre, "En rayon", edition, new SimpleDateFormat("yyyy-MM-dd").parse(dateParution), codeExemplaire);
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
    public String modifier(Exemplaire exemplaire, String etat) {
        exemplaire.setEtat(etat);
        repository.save(exemplaire);
        return "Exemplaire modifié";
    }

    @GetMapping(value = "/exemplaireDisponible")
    public Exemplaire exemplaireDispo(Oeuvre oeuvre) {
        return repository.findByEtatEtOeuvre(oeuvre).get(0);
    }

    @GetMapping(value = "/identification")
    public Exemplaire identifier(Oeuvre oeuvre, String codeExemplaire) {
        return repository.findByOeuvreEtCode(oeuvre, codeExemplaire);
    }

    @GetMapping(value = "/exemplairePourOeuvre")
    public List<Exemplaire> exemplairesOeuvre(Oeuvre oeuvre) {
        return repository.findByOeuvre(oeuvre);
    }

    @GetMapping(value = "/exemplaireReserve")
    public Exemplaire exemplaireReserve(Oeuvre oeuvre) {
        return repository.findByEtatEtOeuvreReserve(oeuvre).get(0);
    }
}
