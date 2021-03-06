package fr.ul.miage.boundary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ul.miage.entity.Exemplaire;
import fr.ul.miage.entity.Oeuvre;
import fr.ul.miage.entity.Reservation;

@RestController
@RequestMapping(value = "/exemplaires")
@ExposesResourceFor(Exemplaire.class)
public class ExemplaireRepresentation {
    @Autowired
    ExemplaireResource repository;
    @Autowired
    OeuvreResource repositoryO;
    @Autowired
    ReservationResource repositoryR;

    @PostMapping(value = "/creer")
    @Transactional
    public String creerExemplaire(Oeuvre oeuvre, String edition, String dateParution, String codeExemplaire)
            throws ParseException {
        Exemplaire e = new Exemplaire(oeuvre.getId(), "En rayon", edition,
                new SimpleDateFormat("yyyy-MM-dd").parse(dateParution), codeExemplaire);
        repository.save(e);
        Set<Exemplaire> set = oeuvre.getExemplaires();
        set.add(e);
        oeuvre.setExemplaires(set);
        repositoryO.save(oeuvre);
        if (oeuvre.getNbRes() != 0) {
            oeuvre.setNbRes(oeuvre.getNbRes() - 1);
            repositoryO.save(oeuvre);
            e.setEtat("Reserve");
            repository.save(e);
            Reservation reservation = repositoryR.findReservationByOeuvreEtDate(oeuvre.getId()).get(0);
            reservation.setEtat("Prete");
            repositoryR.save(reservation);
        }
        return "Exemplaire crée";
    }

    @DeleteMapping(value = "/supprimer")
    @Transactional
    public String supprimer(Exemplaire exemplaire) {
        Oeuvre o = repositoryO.getById(exemplaire.getOeuvre());
        Set<Exemplaire> set = o.getExemplaires();
        set.remove(exemplaire);
        o.setExemplaires(set);
        repositoryO.save(o);
        repository.delete(exemplaire);
        return "Exemplaire supprimé";
    }

    @PatchMapping(value = "/modifier")
    @Transactional
    public String modifier(Exemplaire exemplaire, String etat) {
        exemplaire.setEtat(etat);
        repository.save(exemplaire);
        return "Exemplaire modifié";
    }

    @GetMapping(value = "/exemplaireDisponible")
    public Exemplaire exemplaireDispo(Oeuvre oeuvre) {
        List<Exemplaire> list = repository.findByEtatEtOeuvre(oeuvre.getId());
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @GetMapping(value = "/identification")
    public Exemplaire identifier(Oeuvre oeuvre, String codeExemplaire) {
        return repository.findByOeuvreEtCode(oeuvre.getId(), codeExemplaire);
    }

    @GetMapping(value = "/exemplairePourOeuvre")
    public List<Exemplaire> exemplairesOeuvre(Oeuvre oeuvre) {
        return repository.findByOeuvre(oeuvre.getId());
    }

    @GetMapping(value = "/exemplaireReserve")
    public Exemplaire exemplaireReserve(Oeuvre oeuvre) {
        List<Exemplaire> list = repository.findByEtatEtOeuvreReserve(oeuvre.getId());
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
