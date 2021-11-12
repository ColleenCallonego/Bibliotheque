<template>
  <div>
    <h2>Liste des usagers </h2>

    <div id="rechercheParNom">
      <p>Recherche d'un usager</p>

      <input type="text" id="rechercheUsagerNom" name="rechercheUsagerNom" v-model="nomUsagerRecherche">
      <label for="rechercheUsagerNom">Nom</label>

      <input type="text" id="rechercheUsagersPrenom" name="rechercheUsagersPrenom" v-model="prenomUsagerRecherche">
      <label for="rechercheUsagersPrenom">Prenom</label>
    </div>

    <div>
      <ListeUsagerItem
          v-for="usager in usagersTab"
          v-bind:key="usager.id"
          v-bind:nom="usager.nom"
          v-bind:prenom="usager.prenom"
          v-bind:nom-recherche="nomUsagerRecherche"
          v-bind:prenom-recherche="prenomUsagerRecherche"
      >
      </ListeUsagerItem>
    </div>
  </div>
</template>

<script>
import ListeUsagerItem from "@/components/ListeUsagerItem";

export default {
  data() {
    return {
      nomUsagerRecherche: '',
      prenomUsagerRecherche: ''
    }
  },
  components: {
    ListeUsagerItem
  },
  props: ['usagersTab'],
}
</script>
