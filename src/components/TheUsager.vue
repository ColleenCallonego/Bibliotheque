<template>
  <div>
    <button v-on:click="ouvrePopUpAjouterUsager">AJOUTER USAGER</button>
    <PopUpAjouterUsager v-if="popUpEtat" v-on:fermerPopUp="popUpEtat = false"></PopUpAjouterUsager>

    <ListeUsagerComponent v-bind:usagers-tab="usagersTab" v-on:supprimerUsager="recupererTousLesUsagers"></ListeUsagerComponent>

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
      axios.get('/usagers')
          .then(response => {
            this.usagersTab = response.data._embedded.usagers
          })
    }
  }
}

</script>