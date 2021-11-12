package fr.ul.miage.boundary;

import java.util.Date;
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
    ReservationResource repository;
    @Autowired
    ReservationAssembler assembler;

    @GetMapping(value = "/{reservationId}")
    public ResponseEntity<?> getOneReservation(@PathVariable("reservationId") String id) {
        return Optional.ofNullable(repository.findById(id)).filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(assembler.toModel(i.get()))).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> getAllReservations() {
        return ResponseEntity.ok(assembler.toCollectionModel(repository.findAll()));
    }

    @PostMapping(value = "/creer")
    @Transactional
    public String creerReservation(Oeuvre oeuvre, Usager usager) {
        Date date = new Date();
        Reservation r = new Reservation(date, "En cours", oeuvre, usager);
        repository.save(r);
        return "Réservation créée";
    }

    @DeleteMapping(value = "/supprimer")
    @Transactional
    public String supprimer(Reservation reservation) {
        repository.delete(reservation);
        return "Réservation supprimée";
    }

    @PatchMapping(value = "/modifier")
    @Transactional
    public String modifier(Reservation reservation, String etat) {
        reservation.setEtat(etat);
        repository.save(reservation);
        return "Réservation modifiée";
    }

    @GetMapping(value = "/identification")
    public Reservation identifier(Oeuvre oeuvre, Usager usager) {
        Reservation reservation = repository.findReservationByOeuvreEtUsagerEtEtat(oeuvre, usager);
        return reservation;
    }

}
