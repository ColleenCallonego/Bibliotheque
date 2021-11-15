<template>
  <div>
    <h1>Oeuvres et exemplaires</h1>
    <button v-on:click="ouvrePopUpAjouterOeuvre">AJOUTER OEUVRE</button>
    <PopUpAjouterOeuvre v-if="popUpEtat" v-on:fermerPopUp="popUpEtat = false"></PopUpAjouterOeuvre>

    <ListeOeuvreComponent v-bind:livres-tab="livresTab" v-bind:magazines-tab="magazinesTab"></ListeOeuvreComponent>
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
    axios.get('/oeuvres')
        .then(response => {
          this.livresTab = response.data._embedded.livres
          this.magazinesTab = response.data._embedded.magazines
        })
  },
  methods: {
    ouvrePopUpAjouterOeuvre() {
      this.popUpEtat = true
    }
  }
}
</script>