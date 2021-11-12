package fr.ul.miage.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class Magazine extends Oeuvre {
    private String numero;
    private String categorie;

    public Magazine() {
    }

    public Magazine(String titre, String auteur, Integer nbPage, String numero, String categorie, Integer dureePret) {
        super(titre, auteur, nbPage, 0, dureePret);
        this.numero = numero;
        this.categorie = categorie;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCategorie() {
        return this.categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

}
