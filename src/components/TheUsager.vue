<template>
  <div style="padding: 10px 0 0 20px">
    <h1>Usagers</h1>
    <b-button style="background-color: #e5dcd1;color: black" v-on:click="ouvrePopUpAjouterUsager">AJOUTER USAGER</b-button>
    <PopUpAjouterUsager v-if="popUpEtat" v-on:fermerPopUp="popUpEtat = false" v-on:ajouterUsager="recupererTousLesUsagers"></PopUpAjouterUsager>

    <ListeUsagerComponent class="component-card" v-bind:usagers-tab="usagersTab" v-on:supprimerModifierUsager="recupererTousLesUsagers"></ListeUsagerComponent>

  </div>
</template>

<script>
import PopUpAjouterUsager from "@/components/PopUpAjouterUsager";
import ListeUsagerComponent from "@/components/ListeUsagerComponent";
import axios from "axios";

export default {
  components: {
    PopUpAjouterUsager,ListeUsagerComponent
  },
  data() {
    return {
      popUpEtat: false,
      usagersTab: []
    }
  },
  mounted() {
    this.recupererTousLesUsagers()
  },
  methods: {
    ouvrePopUpAjouterUsager() {
      this.popUpEtat = true
    },
    recupererTousLesUsagers() {
      this.usagersTab = []
      axios.get('/usagers')
          .then(response => {
            this.usagersTab = response.data
          })
    }
  }
}

</script>