<template>
  <b-card style="min-width: 300px;height: 250px; box-shadow: 0px 5px 10px darkgray; display: inline-block; margin: 15px 15px 15px 15px; vertical-align: middle" v-if="this.livre.nom.indexOf(this.titreRecherche) != -1 && this.livre.sousNom.indexOf(this.sousTitreRecherche) != -1">
    <h4>{{this.livre.nom}} - {{this.livre.sousNom}}</h4>
    <div>
      <p>Auteur : {{this.livre.auteur}}</p>
      <p>Résumé : {{this.livre.resume}}</p>
      <p>{{this.livre.nbPage}} pages</p>

      <b-button variant="danger" v-on:click="supprimerLivre">SUPPRIMER</b-button>
      <b-button style="background-color: #e5dcd1;color: black;float: right" v-on:click="ouvrePopUpAfficherExemplaire">AFFICHER EXEMPLAIRES</b-button>
      <PopUpAfficherExemplaire v-if="popUpEtat" v-on:fermerPopUp="popUpEtat = false" v-bind:id-oeuvre="this.oeuvreId"  v-bind:nom-entier="collerNom"></PopUpAfficherExemplaire>
    </div>
  </b-card>
</template>

<script>
import PopUpAfficherExemplaire from "@/components/PopUpAfficherExemplaire";
import axios from "axios";

export default {
  components: {
    PopUpAfficherExemplaire
  },
  data() {
    return {
      popUpEtat: false,
      oeuvreId: null,
      rep: null,
    }
  },
  computed: {
    collerNom: function (){
      return (this.livre.nom + ' - ' + this.livre.sousNom)
    }
  },
  mounted() {
    let param = new URLSearchParams()
    param.append('nom', this.livre.nom)
    param.append('sousNom', this.livre.sousNom)
    axios.get('/oeuvres/identificationLivre', {params: param})
        .then(response => (this.oeuvreId = response.data.id))
  },
  props: ['livre', 'titreRecherche', 'sousTitreRecherche'],
  methods: {
    ouvrePopUpAfficherExemplaire() {
      this.popUpEtat = true
    },
    supprimerLivre() {
      let param = new URLSearchParams()
      param.append('oeuvre', this.oeuvreId)
      axios.delete('/oeuvres/supprimer', {params: param})
          .then(response => {
            this.truc = response.data
            this.$emit('supprimerOeuvre')
          })
    }
  }
}
</script>
