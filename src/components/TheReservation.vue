<template>
  <div>
    {{usagerReservations.reservationsTab}}
    <h1>Réservation d'oeuvres</h1>
    <ReservationIdentifierUsagerComponent v-on:recupUsager="recupererListeReservation($event)"></ReservationIdentifierUsagerComponent>
    <ReservationComponent v-bind:id-usager="usagerReservations.id" v-bind:reservations-tab="usagerReservations.reservationsTab"></ReservationComponent>
  </div>
</template>

<script>
import  ReservationIdentifierUsagerComponent from "@/components/ReservationIdentifierUsagerComponent";
import  ReservationComponent from "@/components/ReservationComponent";
import axios from "axios";

export default {
  components: {
    ReservationIdentifierUsagerComponent, ReservationComponent
  },
  data() {
    return {
      usagerReservations: {
        id: null,
        reservationsTab: [],
      }
    }
  },
  methods: {
    recupererListeReservation(idUsager){
      this.usagerReservations.id = idUsager

      let param = new URLSearchParams()
      param.append('usager', this.usagerReservations.id)
      axios.get('/reservations/pourUsager', {params: param})
          .then(response => {
            if (response.data != ''){
              this.usagerReservations.reservationsTab = response.data
              console.log(response.data)
            }
            else{
              this.usagerReservations.reservationsTab = 'inexistant'
            }
          })

    }
  }
}
</script>