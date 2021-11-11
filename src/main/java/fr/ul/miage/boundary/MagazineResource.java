package fr.ul.miage.boundary;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ul.miage.entity.Magazine;

public interface MagazineResource extends JpaRepository<Magazine, String> {

}
