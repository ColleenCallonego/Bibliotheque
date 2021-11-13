package fr.ul.miage.boundary;

import java.time.LocalDateTime;
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

import fr.ul.miage.assembler.ReservationAssembler;
import fr.ul.miage.entity.Oeuvre;
import fr.ul.miage.entity.Reservation;
import fr.ul.miage.entity.Usager;

@RestController
@RequestMapping(value = "/reservations")
@ExposesResourceFor(Reservation.class)
public class ReservationRepresentation {
    @Autowired
    ReservationResource repositoryR;
    @Autowired
    ReservationAssembler assembler;
    @Autowired
    OeuvreResource repositoryO;

    @GetMapping(value = "/{reservationId}")
    public ResponseEntity<?> getOneReservation(@PathVariable("reservationId") String id) {
        return Optional.ofNullable(repositoryR.findById(id)).filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(assembler.toModel(i.get()))).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> getAllReservations() {
        return ResponseEntity.ok(assembler.toCollectionModel(repositoryR.findAll()));
    }

    @PostMapping(value = "/creer")
    @Transactional
    public String creerReservation(Oeuvre oeuvre, Usager usager) {
        LocalDateTime date = LocalDateTime.now();
        Reservation r = new Reservation(date, "En cours", oeuvre, usager);
        repositoryR.save(r);
        oeuvre.setNbRes(oeuvre.getNbRes() + 1);
        repositoryO.save(oeuvre);
        return "Réservation créée";
    }

    @DeleteMapping(value = "/supprimer")
    @Transactional
    public String supprimer(Reservation reservation) {
        repositoryR.delete(reservation);
        return "Réservation supprimée";
    }

    @PatchMapping(value = "/modifier")
    @Transactional
    public String modifier(Reservation reservation, String etat) {
        reservation.setEtat(etat);
        repositoryR.save(reservation);
        Oeuvre oeuvre = reservation.getOeuvre();
        oeuvre.setNbRes(oeuvre.getNbRes() - 1);
        return "Réservation modifiée";
    }

    @GetMapping(value = "/identification")
    public Reservation identifier(Oeuvre oeuvre, Usager usager) {
        Reservation reservation = repositoryR.findReservationByOeuvreEtUsagerEtEtat(oeuvre, usager);
        return reservation;
    }

    @GetMapping(value = "/pourUsager")
    public List<Reservation> ReservationPourUsager(Usager usager) {
        List<Reservation> list = repositoryR.findReservationByUsager(usager);
        return list;
    }
}
