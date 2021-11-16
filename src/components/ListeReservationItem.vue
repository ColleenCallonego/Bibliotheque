<template>
  <!--Livre-->
  <div v-if="typeOeuvre == 'L'">
    <h3>{{reservation.oeuvre.nom}} - {{reservation.oeuvre.sousNom}}</h3>
    <div>
      <p v-if="reservation.etat == 'Prete'">Disponible<button v-on:click="recupererReservation">RECUPERER</button></p>
      <p v-else>Pas disponible</p>
      <button v-on:click="annulerReservation('Annulee')">ANNULER</button>
    </div>
  </div>
  <!--Magazine-->
  <div v-else>
    <h3>{{reservation.oeuvre.nom}} - {{reservation.oeuvre.numero}}</h3>
    <div>
      <p v-if="reservation.etat == 'Prete'">Disponible<button v-on:click="recupererReservation">RECUPERER</button></p>
      <p v-else>Pas disponible</p>
      <button v-on:click="annulerReservation('Annulee')">ANNULER</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      reponseAPI: null,
      idExemplaire: null,
    }
  },
  props: ['reservation', 'idUsager'],
  computed: {
    typeOeuvre: function (){
      if (this.reservation.oeuvre.sousNom == null){
        return 'M'
      }
      else{
        return 'L'
      }
    }
  },
  methods: {
    recupererExemplaireReservation(){
      let param = new URLSearchParams()
      param.append('oeuvre', this.reservation.oeuvre.id)
      axios.get('/exemplaires/exemplaireReserve', {params: param})
          .then(response => {
            this.idExemplaire = response.data.id
          })
    },
    creerEmpruntReservation(){
      let param = new URLSearchParams()
      param.append('exemplaire', this.idExemplaire)
      param.append('usager', this.idUsager)
      axios.post('/emprunts/creer', param)
          .then(response => {
            this.reponseAPI = response.data
          })
    },
    annulerReservation(etatModif){
      let param = new URLSearchParams()
      param.append('reservation', this.reservation.id)
      param.append('etat', etatModif)
      axios.patch('/reservations/modifier', param)
          .then(response => {
            this.reponseAPI = response.data
            this.$emit('changerReservation')
          })
    },
    recupererReservation(){
      this.annulerReservation('Terminee')
      this.recupererExemplaireReservation()
      this.creerEmpruntReservation()
    }
  }
}
</script>
