<template>
  <div id="app">
    <b-navbar style="background-color: antiquewhite; box-shadow: 0px 5px 3px darkgray; z-index: 1">
      <b-nav>
        <b-nav-item><img width="68" height="68" src="@/assets/livres.png" v-on:click="currentTab = 'Home'"></b-nav-item>
        <b-nav-item>
          <b-button style="height: 68px;vertical-align: middle; background-color: rgba(119,141,175,0.16); color: black; margin: 0px 50px 0px 50px"
          v-for="tab in tabs"
          v-bind:key="tab"
          v-on:click="currentTab = tab"
          >
          {{ tab }}
          </b-button>
        </b-nav-item>
      </b-nav>
    </b-navbar>
    <component v-bind:is="currentTabComponent" style="background-color: #fffaf8;position:absolute;height:100%;width:100%;"></component>
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