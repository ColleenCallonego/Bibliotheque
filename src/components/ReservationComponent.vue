<template>
  <div>
    <b-card style="max-width: 99%; box-shadow: 0px 5px 10px darkgray">
      <p v-if="selectionUsager == true">Selectionnez un usager afin de visualiser ses reservations en cours.</p>
      <p v-else-if="selectionUsager == 'inexistant'">Nous n'avons pas trouvé votre compte usager, vérifiez l'orthographe ou créez-vous un compte.</p>
      <div v-else>
        <ReserverComponent v-bind:id-usager="idUsager" v-on:changerReservation="$emit('changerReservation')"></ReserverComponent>
        <ListeReservationComponent v-bind:id-usager="idUsager" v-bind:reservations-tab="reservationsTab" v-on:changerReservation="$emit('changerReservation')"></ListeReservationComponent>
      </div>
    </b-card>
  </div>
</template>

<script>
import ReserverComponent from "@/components/ReserverComponent";
import ListeReservationComponent from "@/components/ListeReservationComponent";

export default {
  components: {
    ReserverComponent,ListeReservationComponent
  },
  props: ['idUsager', 'reservationsTab'],
  computed: {
    selectionUsager(){
      if (this.idUsager == null){
        return true
      }
      else if (this.idUsager == 'inexistant'){
        return this.idUsager
      }
      else{
        return false
      }
    }
  }
}
</script>
