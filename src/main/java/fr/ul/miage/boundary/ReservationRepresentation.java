package fr.ul.miage.boundary;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    OeuvreResource repositoryO;
    @Autowired
    UsagerResource repositoryU;

    @PostMapping(value = "/creer")
    @Transactional
    public String creerReservation(Oeuvre oeuvre, Usager usager) {
        LocalDateTime date = LocalDateTime.now();
        Reservation r = new Reservation(date, "En cours", oeuvre.getId(), usager.getId());
        repositoryR.save(r);
        oeuvre.setNbRes(oeuvre.getNbRes() + 1);
        Set<Reservation> setReservations = oeuvre.getReservations();
        setReservations.add(r);
        oeuvre.setReservations(setReservations);
        repositoryO.save(oeuvre);
        Set<Reservation> setReservations2 = usager.getReservations();
        setReservations2.add(r);
        usager.setReservations(setReservations2);
        repositoryU.save(usager);
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
        Oeuvre oeuvre = repositoryO.findById(reservation.getOeuvre()).get();
        if (etat.equals("Annulee")) {
            oeuvre.setNbRes(oeuvre.getNbRes() - 1);
        }
        return "Réservation modifiée";
    }

    @GetMapping(value = "/identification")
    public Reservation identifier(Oeuvre oeuvre, Usager usager) {
        Reservation reservation = repositoryR.findReservationByOeuvreEtUsagerEtEtat(oeuvre.getId(), usager.getId());
        return reservation;
    }

    @GetMapping(value = "/pourUsager")
    public List<Reservation> ReservationPourUsager(Usager usager) {
        List<Reservation> list = repositoryR.findReservationByUsager(usager.getId());
        return list;
    }
}
