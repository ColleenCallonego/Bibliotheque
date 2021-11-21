<template>
  <div v-if="reservationsTab != 'inexistant'">
    <h4>Liste des réservations en cours </h4>
    <ListeReservationItem
        v-for="reservation in reservationsTab"
        v-bind:key="reservation.id"
        v-bind:reservation="reservation"
        v-bind:id-usager="idUsager"
        v-on:changerReservation="$emit('changerReservation')"
    >
    </ListeReservationItem>
  </div>

  <div v-else>
    <h4>Aucune réservation en cours</h4>
  </div>
</template>

<script>
import ListeReservationItem from "@/components/ListeReservationItem";

export default {
  components: {
    ListeReservationItem
  },
  props: ['idUsager', 'reservationsTab'],
}
</script>
