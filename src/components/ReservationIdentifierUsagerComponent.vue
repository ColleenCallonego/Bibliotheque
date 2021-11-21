<template>
  <div>
    <b-card style="max-width: 50rem; box-shadow: 0px 5px 10px darkgray">
        <div class="input-class">
        <b-icon class="bIcone" icon="person-fill"></b-icon>
        <input type="text" v-model="nomUsagerReservationRecherche">
        <label>Nom usager</label>
        <input type="text" v-model="prenomUsagerReservationRecherche">
        <label>Prénom usager</label>

        <b-button style="background-color: #e5dcd1;color: black" v-bind:disabled="changeClicRechercheUsager" v-on:click="recupererIdUsager()">OK</b-button>
      </div>
    </b-card>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      nomUsagerReservationRecherche: '',
      prenomUsagerReservationRecherche: '',
      usagerIdRecherche: null,
    }
  },
  computed: {
    changeClicRechercheUsager: function (){
      if (this.nomUsagerReservationRecherche != '' && this.prenomUsagerReservationRecherche != ''){
        return false
      }
      else {
        return true
      }
    }
  },
  methods: {
    recupererIdUsager() {
      let param = new URLSearchParams()
      param.append('nom', this.nomUsagerReservationRecherche)
      param.append('prenom', this.prenomUsagerReservationRecherche)
      axios.get('/usagers/identification', {params: param})
          .then(response => {
            if (response.data != ''){
              this.usagerIdRecherche = response.data.id
              this.$emit('recupUsager', this.usagerIdRecherche)
            }
            else{
              this.usagerIdRecherche = 'inexistant'
              this.$emit('recupUsager', this.usagerIdRecherche)
            }
          })
    }
  }
}
</script>
