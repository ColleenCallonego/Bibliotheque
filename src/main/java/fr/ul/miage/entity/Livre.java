package fr.ul.miage.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("L")
public class Livre extends Oeuvre {

    private String sousNom;
    private String resume;

    public Livre() {
    }

    public Livre(String titre, String auteur, Integer nbPage, String sousNom, String resume, Integer dureePret) {
        super(titre, auteur, nbPage, 0, dureePret);
        this.sousNom = sousNom;
        this.resume = resume;
    }

    public String getSousNom() {
        return this.sousNom;
    }

    public void setSousNom(String sousNom) {
        this.sousNom = sousNom;
    }

    public String getResume() {
        return this.resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

}
