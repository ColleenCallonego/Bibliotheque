package fr.ul.miage.entity;

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

    public Usager() {

    }

    public Usager(String nom, String prenom, String mail, String adresse, Integer penalite) {
        this.id = UUID.randomUUID().toString();
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.adresse = adresse;
        this.penalite = penalite;
    }

    public String getId() {
        return this.id;
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
}
