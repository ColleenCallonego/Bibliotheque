<template>
  <div>
    <h3>AJOUTER EXEMPALIRE</h3>
    <button v-on:click="$emit('fermerPopUp')">ANNULER</button>

    <div>
      <input type="text" maxlength="5" v-model="codeExemplaire">
      <label>Code Exemplaire</label><br>
      <input type="text" v-model="editionExemplaire">
      <label>Edition Exemplaire</label><br>
      <input type="date" v-model="dateParutionExemplaire">
      <label>Date de parution Exemplaire</label><br>

      <button v-bind:disabled="changeClicAjouterExemplaire" v-on:click="postFormAjouterExemplaire()">AJOUTER</button><span>{{reponseAPI}}</span>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data(){
    return {
      codeExemplaire: '',
      editionExemplaire: '',
      dateParutionExemplaire: '',
      errors: [],
      reponseAPI: null,
    }
  },
  props: ['idOeuvre'],
  computed: {
    changeClicAjouterExemplaire: function (){
      if (this.codeExemplaire != '' && this.editionExemplaire != '' && this.dateParutionExemplaire != ''){
        return false
      }
      else {
        return true
      }
    },
  },
  methods: {
    postFormAjouterExemplaire() {
      let param = new URLSearchParams()
      param.append('oeuvre', this.idOeuvre)
      param.append('edition', this.editionExemplaire)
      param.append('dateParution', this.dateParutionExemplaire)
      param.append('codeExemplaire', this.codeExemplaire)
      axios.post('/exemplaires/creer', param)
          .then(response => {
            this.reponseAPI = response.data
            this.$emit('ajouterExemplaire')
          })
    }
  }
}
</script>
