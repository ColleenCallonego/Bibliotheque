<template>
  <div>
    <button v-on:click="ouvrePopUpAjouterOeuvre">AJOUTER OEUVRE</button>
    <PopUpAjouterOeuvre v-if="popUpEtat" v-on:fermerPopUp="popUpEtat = false"></PopUpAjouterOeuvre>

    <ListeOeuvreComponent v-bind:oeuvres-tab="oeuvresTab"></ListeOeuvreComponent>
  </div>
</template>

<script>
import PopUpAjouterOeuvre from "@/components/PopUpAjouterOeuvre";
import ListeOeuvreComponent from "@/components/ListeOeuvreComponent";

export default {
  components: {
    ListeOeuvreComponent,
    PopUpAjouterOeuvre
  },
  data() {
    return {
      popUpEtat: false,
      oeuvresTab: [
        {
          id:1,
          titre: "L'épée de vérité"
        },
        {
          id:2,
          titre: "Les chevaliers d'Enkidiev"
        },
        {
          id:3,
          titre: "L'assassin royal"
        }
      ]
    }
  },
  methods: {
    ouvrePopUpAjouterOeuvre() {
      this.popUpEtat = true
    }
  }
}
</script>