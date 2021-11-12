<template>
  <div>
    <button v-on:click="ouvrePopUpAjouterUsager">AJOUTER USAGER</button>
    <PopUpAjouterUsager v-if="popUpEtat" v-on:fermerPopUp="popUpEtat = false"></PopUpAjouterUsager>

    <ListeUsagerComponent v-bind:usagers-tab="usagersTab"></ListeUsagerComponent>

  </div>
</template>

<script>
import PopUpAjouterUsager from "@/components/PopUpAjouterUsager";
import ListeUsagerComponent from "@/components/ListeUsagerComponent";

export default {
  components: {
    PopUpAjouterUsager,ListeUsagerComponent
  },
  data() {
    return {
      popUpEtat: false,
      usagersTab: [
        {
          id:1,
          nom: "Couroux",
          prenom: "Gabriel"
        },
        {
          id:2,
          nom: "Callonego",
          prenom: "Colleen"
        },
        {
          id:3,
          nom: "Couroux",
          prenom: "Wilfrid"
        }
      ]
    }
  },
  methods: {
    ouvrePopUpAjouterUsager() {
      this.popUpEtat = true
    }
  }
}

</script>