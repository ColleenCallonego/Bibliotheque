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

import fr.ul.miage.boundary.ReservationRepresentation;
import fr.ul.miage.entity.Reservation;

@Component
public class ReservationAssembler implements RepresentationModelAssembler<Reservation, EntityModel<Reservation>> {
    @Override
    public EntityModel<Reservation> toModel(Reservation reservation) {
        return EntityModel.of(reservation,
                linkTo(methodOn(ReservationRepresentation.class).getOneReservation(reservation.getId())).withSelfRel(),
                linkTo(methodOn(ReservationRepresentation.class).getAllReservations()).withRel("collection"));
    }

    public CollectionModel<EntityModel<Reservation>> toCollectionModel(Iterable<? extends Reservation> entities) {
        List<EntityModel<Reservation>> reservationModel = StreamSupport.stream(entities.spliterator(), false)
                .map(i -> toModel(i)).collect(Collectors.toList());
        return CollectionModel.of(reservationModel,
                linkTo(methodOn(ReservationRepresentation.class).getAllReservations()).withSelfRel());
    }
}
