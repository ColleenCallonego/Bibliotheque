package fr.ul.miage.boundary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ul.miage.entity.Oeuvre;
import fr.ul.miage.entity.Reservation;
import fr.ul.miage.entity.Usager;

public interface ReservationResource extends JpaRepository<Reservation, String> {

    @Query("SELECT r FROM Reservation r WHERE oeuvre_id = ?1 AND usager_id = ?2 AND etat = 'En cours'")
    public Reservation findReservationByOeuvreEtUsagerEtEtat(Oeuvre oeuvre, Usager usager);
}
