<template>
  <div v-if="titre.indexOf(this.titreRecherche) != -1">
    <h3>{{titre}}</h3>
    <div>
      <p>{{info1}}</p>
      <p>{{info2}}</p>
      <p>{{info3}}</p>


      <button>SUPPRIMER</button>
      <button v-on:click="ouvrePopUpAfficherExemplaire">AFFICHER EXEMPLAIRES</button>
      <PopUpAfficherExemplaire v-if="popUpEtat" v-on:fermerPopUp="popUpEtat = false" v-bind:titre="this.titre"></PopUpAfficherExemplaire>
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
      info1: 'info1',
      info2: 'info2',
      info3: 'info3',
    }
  },
  props: ['titre', 'titreRecherche', 'typeRecherche'],
  methods: {
    ouvrePopUpAfficherExemplaire() {
      this.popUpEtat = true
    }
  }
}
</script>
