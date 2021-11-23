<template>
  <b-card style="min-width: 300px;height: 150px; box-shadow: 0px 5px 10px darkgray; display: inline-block; margin: 15px 15px 15px 15px; vertical-align: middle">

    <!--Livre-->
    <div v-if="typeOeuvre == 'L'">
      <h5>{{oeuvre.nom}} - {{oeuvre.sousNom}}</h5>
      <div>
        <p v-if="reservation.etat == 'Prete'">Disponible<b-button variant="success" style="float: right" v-on:click="recupererReservation">RECUPERER</b-button></p>
        <p v-else>Pas disponible</p>
        <b-button variant="danger" style="float: bottom" v-on:click="modifierReservation('Annulee')">ANNULER</b-button>
      </div>
    </div>
    <!--Magazine-->
    <div v-else>
      <h5>{{oeuvre.nom}} N°{{oeuvre.numero}}</h5>
      <div>
        <p v-if="reservation.etat == 'Prete'">Disponible<b-button variant="success" style="float: right" v-on:click="recupererReservation">RECUPERER</b-button></p>
        <p v-else>Pas disponible</p>
        <b-button variant="danger" style="float: bottom" v-on:click="modifierReservation('Annulee')">ANNULER</b-button>
      </div>
    </div>
  </b-card>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      reponseAPI: null,
      idExemplaire: null,
      oeuvre: [],
    }
  },
  props: ['reservation', 'idUsager'],
  computed: {
    typeOeuvre: function (){
      if (this.oeuvre.sousNom == null){
        return 'M'
      }
      else{
        return 'L'
      }
    }
  },
  mounted() {
    let param = new URLSearchParams()
    param.append('id', this.reservation.oeuvre)
    axios.get('/oeuvres/identificationParId', {params: param})
        .then(response => {
          this.oeuvre = response.data
        })
  },
  methods: {
    recupererExemplaireReservation(){
      let param = new URLSearchParams()
      param.append('oeuvre', this.oeuvre.id)
      axios.get('/exemplaires/exemplaireReserve', {params: param})
          .then(response => {
            this.idExemplaire = response.data.id
            this.creerEmpruntReservation()
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
    modifierReservation(etatModif){
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
      this.modifierReservation('Terminee')
      this.recupererExemplaireReservation()
    }
  }
}
</script>
