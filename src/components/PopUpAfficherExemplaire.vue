<template>
  <div>
    <h2>Affiche les exemplaires de : {{nomEntier}}</h2>
    <button v-on:click="$emit('fermerPopUp')">RETOUR</button>

    <button v-on:click="ouvrePopUpAjouterExemplaire">AJOUTER EXEMPLAIRE</button>
    <PopUpAjouterExemplaire v-if="popUpEtat" v-on:fermerPopUp="popUpEtat = false" v-bind:id-oeuvre="idOeuvre"></PopUpAjouterExemplaire>

    <ListeExemplaireComponent v-bind:exemplaires-tab="exemplairesTab"></ListeExemplaireComponent>
  </div>
</template>

<script>
import PopUpAjouterExemplaire from "@/components/PopUpAjouterExemplaire";
import ListeExemplaireComponent from "@/components/ListeExemplaireComponent";
import axios from "axios";

export default {
  components: {
    PopUpAjouterExemplaire, ListeExemplaireComponent
  },
  props: ['idOeuvre', 'nomEntier'],
  data() {
    return {
      popUpEtat: false,
      exemplairesTab: []
    }
  },
  mounted() {
    let param = new URLSearchParams()
    param.append('oeuvre', this.idOeuvre)
    axios.get('/exemplaires/exemplairePourOeuvre', {params: param})
        .then(response => (this.exemplairesTab = response.data))
    console.log(this.exemplairesTab)
  },
  methods: {
    ouvrePopUpAjouterExemplaire() {
      this.popUpEtat = true
    }
  }
}
</script>
