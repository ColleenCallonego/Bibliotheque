<template>
  <div>
    <ReservationIdentifierUsagerComponent v-on:recupUsager="usagerReservations.num = $event"></ReservationIdentifierUsagerComponent>
    <ReservationComponent v-bind:num-usager="usagerReservations.num" v-bind:reservations-tab="usagerReservations.reservationsTab"></ReservationComponent>
  </div>
</template>

<script>
import  ReservationIdentifierUsagerComponent from "@/components/ReservationIdentifierUsagerComponent";
import  ReservationComponent from "@/components/ReservationComponent";

export default {
  components: {
    ReservationIdentifierUsagerComponent, ReservationComponent
  },
  data() {
    return {
      usagerReservations: {
        num: null,
        reservationsTab: [
          {
            id:1,
            reservation: "L'épée de vérité"
          },
          {
            id:2,
            reservation: "Les chevaliers d'Enkidiev"
          },
          {
            id:3,
            reservation: "L'assassin royal"
          }
        ]
      } //les donner recuperer par le ReservationIdentifierUsager
    }
  }
}
</script>