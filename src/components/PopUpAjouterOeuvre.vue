<template>
  <div>
    <h1>AJOUTER OEUVRE</h1>
    <button v-on:click="$emit('fermerPopUp')">ANNULER</button>

    <div>
      <input type="text" v-model="nomOeuvre">
      <label>Titre</label><br>
      <input type="text" v-model="auteurOeuvre">
      <label>Auteur</label><br>
      <input type="number" min="1" v-model="nbPageOeuvre">
      <label>Nombre de page</label><br>

      <input type="radio" value="livre" v-model="typeAjout" v-on:click="numeroMagazine = '';categorieMagazine = ''">
      <label>Livre</label>
      <input type="radio" value="magazine" v-model="typeAjout" v-on:click="sousNomLivre = '';resumeLivre = ''">
      <label>Magazine</label><br>

      <div v-if="typeAjout == 'livre'">
        <input type="text" v-model="sousNomLivre">
        <label>Sous-titre</label><br>
        <textarea type="text" v-model="resumeLivre"></textarea>
        <label>Résumé</label><br>

        <button v-on:click="postFormLivre()">AJOUTER</button><span>{{reponseAPI}}</span>
      </div>
      <div v-else-if="typeAjout == 'magazine'">
        <input type="number" min="1" v-model="numeroMagazine">
        <label>Numéro</label><br>
        <input type="text" v-model="categorieMagazine">
        <label>Catégorie</label><br>

        <button v-on:click="postFormMagazine()">AJOUTER</button><span>{{reponseAPI}}</span>
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
      errors: [],
      reponseAPI: null,
    }
  },
  methods: {
    postFormLivre(){
      let param = new URLSearchParams()
      param.append('nom', this.nom)
      param.append('auteur', this.prenom)
      param.append('nbPage', this.mail)
      param.append('sousNom', this.adresse)
      param.append('resume', this.adresse)
      axios.post('/oeuvres/creerLivre', param)
          .then(response => (this.reponseAPI = response.data))
          .catch(e => {
            this.errors.push(e)
          })
    },
    postFormMagazine(){
      let param = new URLSearchParams()
      param.append('nom', this.nom)
      param.append('auteur', this.prenom)
      param.append('nbPage', this.mail)
      param.append('numero', this.adresse)
      param.append('categorie', this.adresse)
      axios.post('/oeuvres/creerMagazine', param)
          .then(response => (this.reponseAPI = response.data))
          .catch(e => {
            this.errors.push(e)
          })
    }
  }
}
</script>
