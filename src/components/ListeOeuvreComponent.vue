<template>
  <b-card title="Liste des oeuvres">
    <div>
      <p>Recherche d'une oeuvre</p>

      <input type="radio" value="tous" v-model="typeRecherhce" v-on:click="numeroMagazineRecherche = '';sousTitreLivreRecherche = '';titreOeuvreRecherche = ''">
      <label>Tous</label>
      <input type="radio" value="livre" v-model="typeRecherhce" v-on:click="numeroMagazineRecherche = '';titreOeuvreRecherche = ''">
      <label>Livre</label>
      <input type="radio" value="magazine" v-model="typeRecherhce" v-on:click="sousTitreLivreRecherche = '';titreOeuvreRecherche = ''">
      <label>Magazine</label>

      <div class="input-class">
        <span v-if="typeRecherhce != null && typeRecherhce != 'tous'">
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
    </div>

    <div v-if="typeRecherhce != 'magazine'">
      <ListeLivreItem
          v-for="livre in livresTab"
          v-bind:key="livre.nom + ' - ' + livre.sousNom"
          v-bind:livre="livre"
          v-bind:titre-recherche="titreOeuvreRecherche"
          v-bind:sous-titre-recherche="sousTitreLivreRecherche"
          v-on:supprimerOeuvre="$emit('supprimerOeuvre')"
      >
      </ListeLivreItem>
    </div>

    <div v-if="typeRecherhce != 'livre'">
      <ListeMagazineItem
          v-for="magazine in magazinesTab"
          v-bind:key="magazine.nom + ' N°' + magazine.numero"
          v-bind:magazine="magazine"
          v-bind:nom-recherche="titreOeuvreRecherche"
          v-bind:numero-recherche="numeroMagazineRecherche"
          v-on:supprimerOeuvre="$emit('supprimerOeuvre')"
      >
      </ListeMagazineItem>
    </div>

  </b-card>
</template>

<script>
import ListeMagazineItem from "@/components/ListeMagazineItem";
import ListeLivreItem from "@/components/ListeLivreItem";

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
    ListeLivreItem, ListeMagazineItem
  },
  props: ['livresTab', 'magazinesTab']
}
</script>
