<template>
  <div v-if="this.magazine.nom.indexOf(this.nomRecherche) != -1 && this.magazine.numero.indexOf(this.numeroRecherche) != -1">
    <h3>{{this.magazine.nom}} N°{{this.magazine.numero}}</h3>
    <div>
      <p>Auteur : {{this.magazine.auteur}}</p>
      <p>Catégorie : {{this.magazine.categorie}}</p>
      <p>{{this.magazine.nbPage}} pages</p>

      <button v-on:click="supprimerMagazine">SUPPRIMER</button>
      <button v-on:click="ouvrePopUpAfficherExemplaire">AFFICHER EXEMPLAIRES</button>
      <PopUpAfficherExemplaire v-if="popUpEtat" v-on:fermerPopUp="popUpEtat = false" v-bind:id-oeuvre="this.oeuvreId" v-bind:nom-entier="collerNom"></PopUpAfficherExemplaire>
    </div>
  </div>
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
