package fr.ul.miage.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "emprunt")
public class Emprunt {

    @Id
    private String id;
    private LocalDateTime date;
    private LocalDateTime dateRendu;
    private String Etat;
    private String exemplaire;
    private String usager;

    public Emprunt() {
    }

    public Emprunt(String etat, String exemplaire, String usager, int dureePret) {
        this.id = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
        this.dateRendu = this.date.plusDays(dureePret);
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

    public String getExemplaire() {
        return this.exemplaire;
    }

    public void setExemplaire(String exemplaire) {
        this.exemplaire = exemplaire;
    }

    public String getUsager() {
        return this.usager;
    }

    public void setUsager(String usager) {
        this.usager = usager;
    }

}
