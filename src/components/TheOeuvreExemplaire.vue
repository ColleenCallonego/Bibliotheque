<template>
  <div>
    <button v-on:click="ouvrePopUpAjouterOeuvre">AJOUTER OEUVRE</button>
    <PopUpAjouterOeuvre v-if="popUpEtat" v-on:fermerPopUp="popUpEtat = false"></PopUpAjouterOeuvre>
  </div>
</template>

<script>
import PopUpAjouterOeuvre from "@/components/PopUpAjouterOeuvre";

export default {
  components: {
    PopUpAjouterOeuvre
  },
  data() {
    return {
      popUpEtat: false
    }
  },
  methods: {
    ouvrePopUpAjouterOeuvre() {
      this.popUpEtat = true
    }
  }
}
</script>