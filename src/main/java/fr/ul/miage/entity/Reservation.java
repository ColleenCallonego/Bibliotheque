package fr.ul.miage.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    private String id;
    private Date dateReservation;
    private String Etat;
    @ManyToOne
    private Oeuvre oeuvre;
    @ManyToOne
    private Usager usager;

    public Reservation() {
    }

    public Reservation(Date dateReservation, String Etat, Oeuvre oeuvre, Usager usager) {
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

    public Date getDateReservation() {
        return this.dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getEtat() {
        return this.Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public Oeuvre getOeuvre() {
        return this.oeuvre;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    public Usager getUsager() {
        return this.usager;
    }

    public void setUsager(Usager usager) {
        this.usager = usager;
    }

}
