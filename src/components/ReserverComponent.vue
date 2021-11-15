<template>
  <div>
    <h2>Réserver une oeuvre</h2>
    <div>
      <input type="radio" value="livre" v-model="typeOeuvreReservation" v-on:click="titreOeuvreReservation = '';numeroMagazineReservation = ''">
      <label>Livre</label>
      <input type="radio" value="magazine" v-model="typeOeuvreReservation" v-on:click="titreOeuvreReservation = '';sousTitreLivreReservation = ''">
      <label>Magazine</label><br>

      <input type="text" v-model="titreOeuvreReservation">
      <label>Titre</label>
      <span v-if="typeOeuvreReservation == 'livre'">
        <input type="text" v-model="sousTitreLivreReservation">
        <label>Sous-titre</label>
      </span>
      <span v-else-if="typeOeuvreReservation == 'magazine'">
        <input type="number" min="1" v-model="numeroMagazineReservation">
        <label>Numéro</label>
      </span>

      <br>
      <button v-bind:disabled="changeClicReserver" v-on:click="postFormReserver()">RESERVER</button><span>{{reponseAPI}}</span><br>

      <p v-if="oeuvreIdReservation == 'inexistant'">Nous ne trouvons pas l'oeuvre que vous voulez réserver, vérifiez l'orthographe.</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  props: ['idUsager'],
  data() {
    return {
      titreOeuvreReservation: '',
      typeOeuvreReservation: null,
      sousTitreLivreReservation: '',
      numeroMagazineReservation: '',
      oeuvreIdReservation: null,

      reponseAPI: null,
      errors: [],
    }
  },
  computed: {
    changeClicReserver(){
      if (this.titreOeuvreReservation != '' && (this.sousTitreLivreReservation != '' || this.numeroMagazineReservation != '')){
        return false
      }
      else {
        return true
      }
    }
  },
  methods: {
    recupererIdOeuvre(){
      let param = new URLSearchParams()
      param.append('nom', this.titreOeuvreReservation)
      if (this.typeOeuvreReservation == 'livre'){
        param.append('sousNom', this.sousTitreLivreReservation)
        axios.get('/oeuvres/identificationLivre', {params: param})
            .then(response => {
              if (response.data != ''){
                this.oeuvreIdReservation = response.data.id
                this.creerReservation()
              }
              else{
                this.oeuvreIdReservation = 'inexistant'
              }
            })
      }
      else {
        param.append('numero', this.numeroMagazineReservation)
        axios.get('/oeuvres/identificationMagazine', {params: param})
            .then(response => {
              if (response.data != ''){
                this.oeuvreIdReservation = response.data.id
                this.creerReservation()
              }
              else{
                this.oeuvreIdReservation = 'inexistant'
              }
            })
      }
    },
    creerReservation(){
      let param = new URLSearchParams()
      param.append('oeuvre', this.oeuvreIdReservation)
      param.append('usager', this.idUsager)
      axios.post('/reservations/creer', param)
          .then(response => (this.reponseAPI = response.data))
    },
    postFormReserver(){
      this.oeuvreIdReservation = null
      this.recupererIdOeuvre()
    }
  }
}
</script>
