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
import fr.ul.miage.boundary.EmpruntRepresentation;
import fr.ul.miage.entity.Emprunt;

@Component
public class EmpruntAssembler implements RepresentationModelAssembler<Emprunt, EntityModel<Emprunt>> {
    @Override
    public EntityModel<Emprunt> toModel(Emprunt emprunt) {
        return EntityModel.of(emprunt,
                linkTo(methodOn(EmpruntRepresentation.class).getOneEmprunt(emprunt.getId())).withSelfRel(),
                linkTo(methodOn(EmpruntRepresentation.class).getAllEmprunts()).withRel("collection"));
    }

    public CollectionModel<EntityModel<Emprunt>> toCollectionModel(Iterable<? extends Emprunt> entities) {
        List<EntityModel<Emprunt>> empruntModel = StreamSupport.stream(entities.spliterator(), false)
                .map(i -> toModel(i)).collect(Collectors.toList());
        return CollectionModel.of(empruntModel,
                linkTo(methodOn(EmpruntRepresentation.class).getAllEmprunts()).withSelfRel());
    }
}
