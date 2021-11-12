<template>
  <div>
    <p v-if="selectionUsager">Selectionnez un usager afin de visualiser ses reservations en cours</p>
    <div v-else>
      <ReserverComponent v-bind:num-usager="numUsager"></ReserverComponent>
      <ListeReservationComponent v-bind:num-usager="numUsager" v-bind:reservations-tab="reservationsTab"></ListeReservationComponent>
    </div>
  </div>
</template>

<script>
import ReserverComponent from "@/components/ReserverComponent";
import ListeReservationComponent from "@/components/ListeReservationComponent";

export default {
  components: {
    ReserverComponent,ListeReservationComponent
  },
  data () {
    return {
      usagerPasSelectionne: false

    }
  },
  props: ['numUsager', 'reservationsTab'],
  computed: {
    selectionUsager(){
      return this.numUsager == null //si aucun usager n'est selectionner doit retourner true
    }
  }
}
</script>
