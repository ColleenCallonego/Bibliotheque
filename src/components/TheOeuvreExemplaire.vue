<template>
  <div style="padding: 10px 0 0 20px">
    <h1>Oeuvres et exemplaires</h1>
    <b-button style="background-color: #e5dcd1;color: black" v-on:click="ouvrePopUpAjouterOeuvre">AJOUTER OEUVRE</b-button>
    <PopUpAjouterOeuvre v-if="popUpEtat" v-on:fermerPopUp="popUpEtat = false" v-on:ajouterOeuvre="recupererToutesLesOeuvres"></PopUpAjouterOeuvre>

    <ListeOeuvreComponent class="component-card" v-bind:livres-tab="livresTab" v-bind:magazines-tab="magazinesTab" v-on:supprimerOeuvre="recupererToutesLesOeuvres"></ListeOeuvreComponent>
  </div>
</template>

<script>
import PopUpAjouterOeuvre from "@/components/PopUpAjouterOeuvre";
import ListeOeuvreComponent from "@/components/ListeOeuvreComponent";
import axios from "axios";

export default {
  components: {
    ListeOeuvreComponent,
    PopUpAjouterOeuvre
  },
  data() {
    return {
      popUpEtat: false,
      livresTab: [],
      magazinesTab: [],
    }
  },
  mounted() {
    this.recupererToutesLesOeuvres()

  },
  methods: {
    ouvrePopUpAjouterOeuvre() {
      this.popUpEtat = true
    },
    recupererToutesLesOeuvres() {
      axios.get('/oeuvres')
          .then(response => {
            this.livresTab = response.data._embedded.livres
            this.magazinesTab = response.data._embedded.magazines
          })
    }
  }
}
</script>