<template>
  <div style="padding: 10px 0 0 20px">
    <h1>Réservation d'oeuvres</h1>
    <ReservationIdentifierUsagerComponent class="component-card" v-on:recupUsager="recupererListeReservationEtUsager($event)"></ReservationIdentifierUsagerComponent>
    <ReservationComponent class="component-card" v-bind:id-usager="usagerReservations.id" v-bind:reservations-tab="usagerReservations.reservationsTab" v-on:changerReservation="recupererListeReservation"></ReservationComponent>
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
    recupererListeReservationEtUsager(idUsager){
      this.usagerReservations.id = idUsager
      this.recupererListeReservation()
    },
    recupererListeReservation(){
      let param = new URLSearchParams()
      param.append('usager', this.usagerReservations.id)
      axios.get('/reservations/pourUsager', {params: param})
          .then(response => {
            if (response.data != ''){
              this.usagerReservations.reservationsTab = response.data
            }
            else{
              this.usagerReservations.reservationsTab = 'inexistant'
            }
          })

    }
  }
}
</script>