<template>
  <b-card style="box-shadow: 0px 5px 10px darkgray;width: 99%" title="Liste des usagers">
    <div>
      <p>Recherche d'un usager</p>

      <input type="text" id="rechercheUsagerNom" name="rechercheUsagerNom" v-model="nomUsagerRecherche">
      <label for="rechercheUsagerNom">Nom</label>

      <input type="text" id="rechercheUsagersPrenom" name="rechercheUsagersPrenom" v-model="prenomUsagerRecherche">
      <label for="rechercheUsagersPrenom">Prenom</label>
    </div>

    <div>
      <ListeUsagerItem
          v-for="usager in usagersTab"
          v-bind:key="usager.nom + ' ' + usager.prenom"
          v-bind:usager="usager"
          v-bind:nom-recherche="nomUsagerRecherche"
          v-bind:prenom-recherche="prenomUsagerRecherche"
          v-on:supprimerModifierUsager="$emit('supprimerModifierUsager')"
      >
      </ListeUsagerItem>
    </div>
  </b-card>
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
