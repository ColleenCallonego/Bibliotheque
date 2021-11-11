package fr.ul.miage.entity;

import java.util.UUID;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "oeuvre")
@DiscriminatorColumn(name = "oeuvreType")
public class Oeuvre {

    @Id
    private String id;
    private String nom;
    private String Auteur;
    private Integer nbPage;
    private Integer nbRes;

    public Oeuvre() {

    }

    public Oeuvre(String nom, String Auteur, Integer nbPage, Integer nbRes) {
        this.id = UUID.randomUUID().toString();
        this.nom = nom;
        this.Auteur = Auteur;
        this.nbPage = nbPage;
        this.nbRes = nbRes;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String titre) {
        this.nom = titre;
    }

    public String getAuteur() {
        return this.Auteur;
    }

    public void setAuteur(String Auteur) {
        this.Auteur = Auteur;
    }

    public Integer getNbPage() {
        return this.nbPage;
    }

    public void setNbPage(Integer nbPage) {
        this.nbPage = nbPage;
    }

    public Integer getNbRes() {
        return this.nbRes;
    }

    public void setNbRes(Integer nbRes) {
        this.nbRes = nbRes;
    }

}
