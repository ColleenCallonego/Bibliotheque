package fr.ul.miage.boundary;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ul.miage.entity.Livre;

public interface LivreResource extends JpaRepository<Livre, String> {

}
