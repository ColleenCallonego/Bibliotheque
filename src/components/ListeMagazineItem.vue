<template>
  <b-card style="min-width: 300px;height: 250px; box-shadow: 0px 5px 10px darkgray; display: inline-block; margin: 15px 15px 15px 15px; vertical-align: middle" v-if="this.magazine.nom.indexOf(this.nomRecherche) != -1 && this.magazine.numero.indexOf(this.numeroRecherche) != -1">
    <h4>{{this.magazine.nom}} N°{{this.magazine.numero}}</h4>
    <div>
      <p>Auteur : {{this.magazine.auteur}}</p>
      <p>Catégorie : {{this.magazine.categorie}}</p>
      <p>{{this.magazine.nbPage}} pages</p>

      <b-button variant="danger" v-on:click="supprimerMagazine">SUPPRIMER</b-button>
      <b-button style="background-color: #e5dcd1;color: black;float: right" v-on:click="ouvrePopUpAfficherExemplaire">AFFICHER EXEMPLAIRES</b-button>
      <PopUpAfficherExemplaire v-if="popUpEtat" v-on:fermerPopUp="popUpEtat = false" v-bind:id-oeuvre="this.oeuvreId" v-bind:nom-entier="collerNom"></PopUpAfficherExemplaire>
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
      return (this.magazine.nom + ' N°' + this.magazine.numero)
    }
  },
  mounted() {
    let param = new URLSearchParams()
    param.append('nom', this.magazine.nom)
    param.append('numero', this.magazine.numero)
    axios.get('/oeuvres/identificationMagazine', {params: param})
        .then(response => (this.oeuvreId = response.data.id))
  },
  props: ['magazine', 'nomRecherche', 'numeroRecherche'],
  methods: {
    ouvrePopUpAfficherExemplaire() {
      this.popUpEtat = true
    },
    supprimerMagazine() {
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
