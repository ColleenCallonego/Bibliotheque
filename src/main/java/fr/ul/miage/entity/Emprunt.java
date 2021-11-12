package fr.ul.miage.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "emprunt")
public class Emprunt {

    @Id
    private String id;
    private LocalDateTime date;
    private LocalDateTime dateRendu;
    private String Etat;
    @ManyToOne
    private Exemplaire exemplaire;
    @ManyToOne
    private Usager usager;

    public Emprunt() {
    }

    public Emprunt(String etat, Exemplaire exemplaire, Usager usager) {
        this.id = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
        this.dateRendu = this.date.plusDays(exemplaire.getOeuvre().getDureePret());
        this.Etat = etat;
        this.exemplaire = exemplaire;
        this.usager = usager;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDateRendu() {
        return this.dateRendu;
    }

    public void setDateRendu(LocalDateTime dateRendu) {
        this.dateRendu = dateRendu;
    }

    public String getEtat() {
        return this.Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public Exemplaire getExemplaire() {
        return this.exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Usager getUsager() {
        return this.usager;
    }

    public void setUsager(Usager usager) {
        this.usager = usager;
    }

}
