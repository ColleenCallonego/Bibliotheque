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

import fr.ul.miage.boundary.UsagerRepresentation;
import fr.ul.miage.entity.Usager;

@Component
public class UsagerAssembler implements RepresentationModelAssembler<Usager, EntityModel<Usager>> {

    @Override
    public EntityModel<Usager> toModel(Usager usager) {
        return EntityModel.of(usager,
                linkTo(methodOn(UsagerRepresentation.class).getOneUsager(usager.getId())).withSelfRel(),
                linkTo(methodOn(UsagerRepresentation.class).getAllUsagers()).withRel("collection"));
    }

    public CollectionModel<EntityModel<Usager>> toCollectionModel(Iterable<? extends Usager> entities) {
        List<EntityModel<Usager>> usagerModel = StreamSupport.stream(entities.spliterator(), false).map(i -> toModel(i))
                .collect(Collectors.toList());
        return CollectionModel.of(usagerModel,
                linkTo(methodOn(UsagerRepresentation.class).getAllUsagers()).withSelfRel());
    }
}
