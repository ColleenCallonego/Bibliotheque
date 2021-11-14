package fr.ul.miage.boundary;

import java.time.LocalDateTime;
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
import fr.ul.miage.entity.Oeuvre;
import fr.ul.miage.entity.Reservation;
import fr.ul.miage.entity.Usager;

@RestController
@RequestMapping(value = "/emprunts")
@ExposesResourceFor(Emprunt.class)
public class EmpruntRepresentation {
    @Autowired
    EmpruntResource repositoryE;
    @Autowired
    EmpruntAssembler assembler;
    @Autowired
    ExemplaireResource repositoryEx;
    @Autowired
    OeuvreResource repositoryO;
    @Autowired
    ReservationResource repositoryR;
    @Autowired
    UsagerResource repositoryU;

    @GetMapping(value = "/{empruntId}")
    public ResponseEntity<?> getOneEmprunt(@PathVariable("empruntId") String id) {
        return Optional.ofNullable(repositoryE.findById(id)).filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(assembler.toModel(i.get()))).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> getAllEmprunts() {
        return ResponseEntity.ok(assembler.toCollectionModel(repositoryE.findAll()));
    }

    @PostMapping(value = "/creer")
    public String creerEmprunt(Exemplaire exemplaire, Usager usager) {
        exemplaire.setEtat("Emprunte");
        repositoryEx.save(exemplaire);
        Emprunt e = new Emprunt("En cours", exemplaire, usager);
        repositoryE.save(e);
        return "Emprunt créé";
    }

    @DeleteMapping(value = "/supprimer")
    public String supprimerEmprunt(Emprunt emprunt) {
        repositoryE.delete(emprunt);
        return "Emprunt supprimé";
    }

    @PatchMapping(value = "/modifier")
    public String modifier(Emprunt emprunt, String etatEx) {
        Usager usager = emprunt.getUsager();
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(emprunt.getDateRendu())) {
            emprunt.setEtat("Rendu en retard");
            usager.ajouterPenalite();
            repositoryU.save(usager);
        } else {
            emprunt.setEtat("Rendu");
        }
        repositoryE.save(emprunt);
        Oeuvre oeuvre = emprunt.getExemplaire().getOeuvre();
        Exemplaire exemplaire = emprunt.getExemplaire();
        if (oeuvre.getNbRes() != 0 && !etatEx.equals("Abime")) {
            oeuvre.setNbRes(oeuvre.getNbRes() - 1);
            repositoryO.save(oeuvre);
            exemplaire.setEtat("Reserve");
            repositoryEx.save(exemplaire);
            Reservation reservation = repositoryR.findReservationByOeuvreEtDate(oeuvre).get(0);
            reservation.setEtat("Prete");
            repositoryR.save(reservation);
        } else if (etatEx.equals("Abime")) {
            exemplaire.setEtat(etatEx);
            usager.ajouterPenalite();
            repositoryU.save(usager);
        } else {
            exemplaire.setEtat(etatEx);
        }
        return "Emprunt modifié";
    }

    @GetMapping(value = "/identification")
    public Emprunt identifier(Exemplaire exemplaire, Usager usager) {
        Emprunt emprunt = repositoryE.findEmpruntByExemplaireEtUsager(exemplaire, usager);
        return emprunt;
    }
}
