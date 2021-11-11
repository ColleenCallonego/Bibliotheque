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

import fr.ul.miage.boundary.OeuvreRepresentation;
import fr.ul.miage.entity.Oeuvre;

@Component
public class OeuvreAssembler implements RepresentationModelAssembler<Oeuvre, EntityModel<Oeuvre>> {

    @Override
    public EntityModel<Oeuvre> toModel(Oeuvre oeuvre) {
        return EntityModel.of(oeuvre,
                linkTo(methodOn(OeuvreRepresentation.class).getOneOeuvre(oeuvre.getId())).withSelfRel(),
                linkTo(methodOn(OeuvreRepresentation.class).getAllOeuvres()).withRel("collection"));
    }

    public CollectionModel<EntityModel<Oeuvre>> toCollectionModel(Iterable<? extends Oeuvre> entities) {
        List<EntityModel<Oeuvre>> oeuvreModel = StreamSupport.stream(entities.spliterator(), false).map(i -> toModel(i))
                .collect(Collectors.toList());
        return CollectionModel.of(oeuvreModel,
                linkTo(methodOn(OeuvreRepresentation.class).getAllOeuvres()).withSelfRel());
    }
}
