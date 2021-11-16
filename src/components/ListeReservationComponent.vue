<template>
  <div v-if="reservationsTab != 'inexistant'">
    <h2>Liste des réservations en cours </h2>
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
    <h2>Aucune réservation en cours</h2>
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
