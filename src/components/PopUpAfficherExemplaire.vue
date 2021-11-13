<template>
  <div>
    <h1>Afficher les exemplaires de {{titre}}</h1>
    <button v-on:click="$emit('fermerPopUp')">RETOUR</button>

    <button v-on:click="ouvrePopUpAjouterExemplaire">AJOUTER EXEMPLAIRE</button>
    <PopUpAjouterExemplaire v-if="popUpEtat" v-on:fermerPopUp="popUpEtat = false" v-bind:titre="titre"></PopUpAjouterExemplaire>

    <ListeExemplaireComponent v-bind:exemplaires-tab="exemplairesTab"></ListeExemplaireComponent>
  </div>
</template>

<script>
import PopUpAjouterExemplaire from "@/components/PopUpAjouterExemplaire";
import ListeExemplaireComponent from "@/components/ListeExemplaireComponent";

export default {
  components: {
    PopUpAjouterExemplaire, ListeExemplaireComponent
  },
  props: ['titre'],
  data() {
    return {
      popUpEtat: false,
      exemplairesTab: [
        {
          id:1,
          code: "A5NF6"
        },
        {
          id:2,
          code: "K85GT"
        },
        {
          id:3,
          code: "3MP9Q"
        }
      ]
    }
  },
  methods: {
    ouvrePopUpAjouterExemplaire() {
      this.popUpEtat = true
    }
  }
}
</script>
