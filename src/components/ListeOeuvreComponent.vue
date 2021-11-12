<template>
  <div>
    <h2>Liste des oeuvres </h2>

    <div id="rechercheParTitre">
      <p>Recherche d'une oeuvre</p>

      <input type="radio" id="recherhceLivre" value="livre" v-model="typeRecherhce" v-on:click="numeroMagazineRecherche = '';titreOeuvreRecherche = ''">
      <label for="recherhceLivre">Livre</label>
      <input type="radio" id="recherhceMagazine" value="magazine" v-model="typeRecherhce" v-on:click="sousTitreLivreRecherche = '';titreOeuvreRecherche = ''">
      <label for="recherhceMagazine">Magazine</label><br>

      <span v-if="typeRecherhce != null">
      <input type="text" id="rechercheOeuvreTitre" name="rechercheOeuvreTitre" v-model="titreOeuvreRecherche">
      <label for="rechercheOeuvreTitre">Titre</label>
      </span>
      <span v-if="typeRecherhce == 'livre'">
      <input type="text" id="rechercheLivreSousTitre" name="rechercheLivreSousTitre" v-model="sousTitreLivreRecherche">
      <label for="rechercheLivreSousTitre">Sous-titre</label>
      </span>
      <span v-else-if="typeRecherhce == 'magazine'">
      <input type="number" min="1" id="rechercheMagazineNumero" name="rechercheMagazineNumero" v-model="numeroMagazineRecherche">
      <label for="rechercheMagazineNumero">Numéro</label>
      </span>
    </div>

    <div>
    <ListeOeuvreItem
        v-for="oeuvre in oeuvresTab"
        v-bind:key="oeuvre.id"
        v-bind:titre="oeuvre.titre"
        v-bind:titre-recherche="titreOeuvreRecherche"
        v-bind:type-recherche="typeRecherhce"
    >
    </ListeOeuvreItem>
    </div>

  </div>
</template>

<script>
import ListeOeuvreItem from "@/components/ListeOeuvreItem";

export default {
  data() {
    return {
      typeRecherhce: null,
      titreOeuvreRecherche: '',
      sousTitreLivreRecherche: '',
      numeroMagazineRecherche: '',
    }
  },
  components: {
    ListeOeuvreItem
  },
  props: ['oeuvresTab']
}
</script>
