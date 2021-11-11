package fr.ul.miage.boundary;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ul.miage.entity.Usager;

public interface UsagerResource extends JpaRepository<Usager, String> {

}
