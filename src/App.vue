<template>
  <div id="app">
    <div id="nav">
      <img src="@/assets/livres.png" v-on:click="currentTab = 'Home'">
      <button
          v-for="tab in tabs"
          v-bind:key="tab"
          v-on:click="currentTab = tab"
      >
        {{ tab }}
      </button>
    </div>
    <component v-bind:is="currentTabComponent"></component>
  </div>
</template>

<script>
import TheHome from '@/components/TheHome.vue'
import TheEmpruntRetour from '@/components/TheEmpruntRetour.vue'
import TheReservation from '@/components/TheReservation.vue'
import TheOeuvreExemplaire from '@/components/TheOeuvreExemplaire.vue'
import TheUsager from '@/components/TheUsager.vue'

export default {
  components: {
    TheHome, TheEmpruntRetour, TheReservation, TheOeuvreExemplaire, TheUsager
  },
  data() {
    return {
      currentTab: "Home",
      tabs: ["EmpruntRetour", "Reservation", "OeuvreExemplaire", "Usager"]
    }
  },
  computed: {
    currentTabComponent() {
      return "The" + this.currentTab;
    }
  }
}





</script>