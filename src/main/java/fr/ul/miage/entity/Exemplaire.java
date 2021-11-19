package fr.ul.miage.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "exemplaire")
public class Exemplaire {

    @Id
    private String id;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Oeuvre oeuvre;
    private String etat;
    private String edition;
    private Date dateParution;
    private String codeExemplaire;

    public Exemplaire() {
    }

    public Exemplaire(Oeuvre oeuvre, String etat, String edition, Date dateParution, String codeExemplaire) {
        this.id = UUID.randomUUID().toString();
        this.oeuvre = oeuvre;
        this.etat = etat;
        this.edition = edition;
        this.dateParution = dateParution;
        this.codeExemplaire = codeExemplaire;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Oeuvre getOeuvre() {
        return this.oeuvre;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    public String getEtat() {
        return this.etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getEdition() {
        return this.edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Date getDateParution() {
        return this.dateParution;
    }

    public void setDateParution(Date dateParution) {
        this.dateParution = dateParution;
    }

    public String getCodeExemplaire() {
        return this.codeExemplaire;
    }

    public void setCodeExemplaire(String codeExemplaire) {
        this.codeExemplaire = codeExemplaire;
    }

}
