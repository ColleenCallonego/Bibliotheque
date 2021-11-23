package fr.ul.miage.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "usager")
public class Usager {

    @Id
    private String id;
    private String nom;
    private String prenom;
    private String mail;
    private String adresse;
    private Integer penalite;

    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Reservation> reservations;

    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Emprunt> emprunts;

    public Usager() {

    }

    public Usager(String nom, String prenom, String mail, String adresse, Integer penalite) {
        this.id = UUID.randomUUID().toString();
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.adresse = adresse;
        this.penalite = penalite;
        this.reservations = new HashSet<>();
        this.emprunts = new HashSet<>();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Reservation> getReservations() {
        return this.reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Emprunt> getEmprunts() {
        return this.emprunts;
    }

    public void setEmprunts(Set<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getPenalite() {
        return this.penalite;
    }

    public void setPenalite(Integer penalite) {
        this.penalite = penalite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void ajouterPenalite() {
        Integer p = this.penalite;
        this.penalite = (p + 1);
    }
}
