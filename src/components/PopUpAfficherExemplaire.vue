<template>
  <div>
    <h2>Affiche les exemplaires de : {{nomEntier}}</h2>
    <button v-on:click="$emit('fermerPopUp')">RETOUR</button>

    <button v-on:click="ouvrePopUpAjouterExemplaire">AJOUTER EXEMPLAIRE</button>
    <PopUpAjouterExemplaire v-if="popUpEtat" v-on:fermerPopUp="popUpEtat = false" v-bind:id-oeuvre="idOeuvre" v-on:ajouterExemplaire="recupererTousLesExemplaire"></PopUpAjouterExemplaire>

    <ListeExemplaireComponent v-bind:exemplaires-tab="exemplairesTab" v-on:modifierSupprimerExemplaire="recupererTousLesExemplaire"></ListeExemplaireComponent>
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
    this.recupererTousLesExemplaire()
  },
  methods: {
    ouvrePopUpAjouterExemplaire() {
      this.popUpEtat = true
    },
    recupererTousLesExemplaire(){
      let param = new URLSearchParams()
      param.append('oeuvre', this.idOeuvre)
      axios.get('/exemplaires/exemplairePourOeuvre', {params: param})
          .then(response => (this.exemplairesTab = response.data))
    }
  }
}
</script>
