<template>
  <div v-if="this.livre.nom.indexOf(this.titreRecherche) != -1 && this.livre.sousNom.indexOf(this.sousTitreRecherche) != -1">
    <h3>{{this.livre.nom}} - {{this.livre.sousNom}}</h3>
    <div>
      <p>Auteur : {{this.livre.auteur}}</p>
      <p>Résumé : {{this.livre.resume}}</p>
      <p>{{this.livre.nbPage}} pages</p>

      <button>SUPPRIMER</button>
      <button v-on:click="ouvrePopUpAfficherExemplaire">AFFICHER EXEMPLAIRES</button>
      <PopUpAfficherExemplaire v-if="popUpEtat" v-on:fermerPopUp="popUpEtat = false" v-bind:id-oeuvre="this.livre.id"  v-bind:nom-entier="collerNom"></PopUpAfficherExemplaire>
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
      return (this.livre.nom + ' ' + this.livre.sousNom)
    }
  },
  props: ['livre', 'titreRecherche', 'sousTitreRecherche'],
  methods: {
    ouvrePopUpAfficherExemplaire() {
      this.popUpEtat = true
    }
  }
}
</script>
