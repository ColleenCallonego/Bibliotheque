package fr.ul.miage.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import fr.ul.miage.boundary.ExemplaireRepresentation;
import fr.ul.miage.entity.Exemplaire;

@Component
public class ExemplaireAssembler implements RepresentationModelAssembler<Exemplaire, EntityModel<Exemplaire>> {
    @Override
    public EntityModel<Exemplaire> toModel(Exemplaire exemplaire) {
        return EntityModel.of(exemplaire,
                linkTo(methodOn(ExemplaireRepresentation.class).getOneExemplaire(exemplaire.getId())).withSelfRel(),
                linkTo(methodOn(ExemplaireRepresentation.class).getAllExemplaires()).withRel("collection"));
    }

    public CollectionModel<EntityModel<Exemplaire>> toCollectionModel(Iterable<? extends Exemplaire> entities) {
        List<EntityModel<Exemplaire>> exemplaireModel = StreamSupport.stream(entities.spliterator(), false)
                .map(i -> toModel(i)).collect(Collectors.toList());
        return CollectionModel.of(exemplaireModel,
                linkTo(methodOn(ExemplaireRepresentation.class).getAllExemplaires()).withSelfRel());
    }
}
