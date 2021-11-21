package fr.ul.miage.boundary;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ul.miage.entity.Livre;
import fr.ul.miage.entity.Magazine;
import fr.ul.miage.entity.Oeuvre;

@RestController
@RequestMapping(value = "/oeuvres")
@ExposesResourceFor(Oeuvre.class)

public class OeuvreRepresentation {
    @Autowired
    OeuvreResource repositoryO;
    @Autowired
    LivreResource repositoryL;
    @Autowired
    MagazineResource repositoryM;

    @GetMapping(value = "/identificationParId")
    public Oeuvre identificationParId(String id){
        return repositoryO.findById(id).get();
    }

    @PostMapping(value = "/creerLivre")
    @Transactional
    public String creerLivre(String nom, String auteur, Integer nbPage, String sousNom, String resume,
            Integer dureePret) {
        Livre l = new Livre(nom, auteur, nbPage, sousNom, resume, dureePret);
        repositoryL.save(l);
        return "Oeuvre créée";
    }

    @PostMapping(value = "/creerMagazine")
    @Transactional
    public String creerMagazine(String nom, String auteur, Integer nbPage, String numero, String categorie,
            Integer dureePret) {
        Magazine m = new Magazine(nom, auteur, nbPage, numero, categorie, dureePret);
        repositoryM.save(m);
        return "Oeuvre créee";
    }

    @GetMapping(value = "/identificationLivre")
    public Oeuvre identifierLivre(String nom, String sousNom) {
        Oeuvre o = repositoryO.findLivreByNomEtSousNom(nom, sousNom);
        return o;
    }

    @GetMapping(value = "/identificationMagazine")
    public Oeuvre identifierMagazine(String nom, String numero) {
        Oeuvre o = repositoryO.findMagazineByNomEtNumero(nom, numero);
        return o;
    }

    @DeleteMapping(value = "/supprimer")
    public String supprimer(Oeuvre oeuvre) {
        repositoryO.delete(oeuvre);
        return "Oeuvre supprimée";
    }

}
