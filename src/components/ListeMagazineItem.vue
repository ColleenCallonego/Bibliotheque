<template>
  <div v-if="this.magazine.nom.indexOf(this.nomRecherche) != -1 && this.magazine.numero.indexOf(this.numeroRecherche) != -1">
    <h3>{{this.magazine.nom}} N°{{this.magazine.numero}}</h3>
    <div>
      <p>Auteur : {{this.magazine.auteur}}</p>
      <p>Catégorie : {{this.magazine.categorie}}</p>
      <p>{{this.magazine.nbPage}} pages</p>


      <button>SUPPRIMER</button>
      <button v-on:click="ouvrePopUpAfficherExemplaire">AFFICHER EXEMPLAIRES</button>
      <PopUpAfficherExemplaire v-if="popUpEtat" v-on:fermerPopUp="popUpEtat = false" v-bind:id-oeuvre="this.magazine.id" v-bind:nom-entier="collerNom"></PopUpAfficherExemplaire>
    </div>
  </div>
</template>

<script>
import PopUpAfficherExemplaire from "@/components/PopUpAfficherExemplaire";

export default {
  components: {
    PopUpAfficherExemplaire
  },
  data() {
    return {
      popUpEtat: false,
    }
  },
  computed: {
    collerNom: function (){
      return (this.magazine.nom + ' ' + this.magazine.numero)
    }
  },
  props: ['magazine', 'nomRecherche', 'numeroRecherche'],
  methods: {
    ouvrePopUpAfficherExemplaire() {
      this.popUpEtat = true
    }
  }
}
</script>
