<template>
  <div>
    <h2>AJOUTER OEUVRE</h2>
    <button v-on:click="$emit('fermerPopUp')">ANNULER</button>

    <div>
      <input type="text" v-model="nomOeuvre">
      <label>Titre</label><br>
      <input type="text" v-model="auteurOeuvre">
      <label>Auteur</label><br>
      <input type="number" min="1" v-model="nbPageOeuvre">
      <label>Nombre de page</label><br>
      <input type="number" min="7" v-model="dureePret">
      <label>Durée du prêt</label><br>

      <input type="radio" value="livre" v-model="typeAjout" v-on:click="numeroMagazine = '';categorieMagazine = ''">
      <label>Livre</label>
      <input type="radio" value="magazine" v-model="typeAjout" v-on:click="sousNomLivre = '';resumeLivre = ''">
      <label>Magazine</label><br>

      <div v-if="typeAjout == 'livre'">
        <input type="text" v-model="sousNomLivre">
        <label>Sous-titre</label><br>
        <textarea type="text" v-model="resumeLivre"></textarea>
        <label>Résumé</label><br>

        <button v-bind:disabled="changeClicAjouterLivre" v-on:click="postFormLivre()">AJOUTER</button><span>{{reponseAPI}}</span>
      </div>
      <div v-else-if="typeAjout == 'magazine'">
        <input type="number" min="1" v-model="numeroMagazine">
        <label>Numéro</label><br>
        <input type="text" v-model="categorieMagazine">
        <label>Catégorie</label><br>

        <button v-bind:disabled="changeClicAjouterMagazine" v-on:click="postFormMagazine()">AJOUTER</button><span>{{reponseAPI}}</span>
      </div>

    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data(){
    return {
      nomOeuvre: '',
      auteurOeuvre: '',
      nbPageOeuvre: '',
      typeAjout: null,
      sousNomLivre: '',
      resumeLivre: '',
      numeroMagazine: '',
      categorieMagazine: '',
      dureePret: '',
      errors: [],
      reponseAPI: null,
    }
  },
  computed: {
    changeClicAjouterLivre: function (){
      if (this.changeClicAjouterOeuvre() && this.sousNomLivre != '' && this.resumeLivre != ''){
        return false
      }
      else {
        return true
      }
    },
    changeClicAjouterMagazine: function (){
      if (this.changeClicAjouterOeuvre() && this.numeroMagazine != '' && this.categorieMagazine != ''){
        return false
      }
      else {
        return true
      }
    }
  },
  methods: {
    changeClicAjouterOeuvre: function (){
      if (this.nomOeuvre != '' && this.auteurOeuvre != '' && this.nbPageOeuvre != '' && this.dureePret != ''){
        return false
      }
      else {
        return true
      }
    },
    postFormLivre(){
      let param = new URLSearchParams()
      param.append('nom', this.nomOeuvre)
      param.append('auteur', this.auteurOeuvre)
      param.append('nbPage', this.nbPageOeuvre)
      param.append('sousNom', this.sousNomLivre)
      param.append('resume', this.resumeLivre)
      param.append('dureePret', this.dureePret)
      axios.post('/oeuvres/creerLivre', param)
          .then(response => (this.reponseAPI = response.data))
          .catch(e => {
            this.errors.push(e)
          })
    },
    postFormMagazine(){
      let param = new URLSearchParams()
      param.append('nom', this.nomOeuvre)
      param.append('auteur', this.auteurOeuvre)
      param.append('nbPage', this.nbPageOeuvre)
      param.append('numero', this.numeroMagazine)
      param.append('categorie', this.categorieMagazine)
      param.append('dureePret', this.dureePret)
      axios.post('/oeuvres/creerMagazine', param)
          .then(response => (this.reponseAPI = response.data))
          .catch(e => {
            this.errors.push(e)
          })
    }
  }
}
</script>
