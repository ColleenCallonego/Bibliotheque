<template>
  <div class="overlay">
    <b-card style="min-width: 1000px;width: 75%;margin: 5% auto;box-shadow: 0px 5px 10px darkgray">
      <h4>Affiche les exemplaires de : {{nomEntier}}</h4>
      <b-button style="float: right" variant="warning" v-on:click="$emit('fermerPopUp')">RETOUR</b-button>

      <b-button style="background-color: #e5dcd1;color: black" v-on:click="ouvrePopUpAjouterExemplaire">AJOUTER EXEMPLAIRE</b-button>
      <PopUpAjouterExemplaire v-if="popUpEtat" v-on:fermerPopUp="popUpEtat = false" v-bind:id-oeuvre="idOeuvre" v-on:ajouterExemplaire="recupererTousLesExemplaire"></PopUpAjouterExemplaire>

      <ListeExemplaireComponent class="component-card" v-bind:exemplaires-tab="exemplairesTab" v-on:modifierSupprimerExemplaire="recupererTousLesExemplaire"></ListeExemplaireComponent>
    </b-card>
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
