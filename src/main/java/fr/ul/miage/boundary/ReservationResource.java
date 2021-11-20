package fr.ul.miage.boundary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ul.miage.entity.Reservation;

public interface ReservationResource extends JpaRepository<Reservation, String> {

    @Query("SELECT r FROM Reservation r WHERE oeuvre = ?1 AND usager = ?2 AND etat = 'En cours'")
    public Reservation findReservationByOeuvreEtUsagerEtEtat(String oeuvre, String usager);

    @Query("SELECT r FROM Reservation r WHERE usager = ?1 AND (etat = 'En cours' OR etat = 'Prete')")
    public List<Reservation> findReservationByUsager(String usager);

    @Query("SELECT r FROM Reservation r WHERE oeuvre = ?1 ORDER BY dateReservation ASC")
    public List<Reservation> findReservationByOeuvreEtDate(String oeuvre);
}
