package fr.ul.miage.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    private String id;
    private LocalDateTime dateReservation;
    private String Etat;
    private String oeuvre;
    private String usager;

    public Reservation() {
    }

    public Reservation(LocalDateTime dateReservation, String Etat, String oeuvre, String usager) {
        this.id = UUID.randomUUID().toString();
        this.dateReservation = dateReservation;
        this.Etat = Etat;
        this.oeuvre = oeuvre;
        this.usager = usager;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDateReservation() {
        return this.dateReservation;
    }

    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getEtat() {
        return this.Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public String getOeuvre() {
        return this.oeuvre;
    }

    public void setOeuvre(String oeuvre) {
        this.oeuvre = oeuvre;
    }

    public String getUsager() {
        return this.usager;
    }

    public void setUsager(String usager) {
        this.usager = usager;
    }

}
