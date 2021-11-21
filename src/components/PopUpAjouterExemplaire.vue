<template>
  <div class="overlay">
    <b-card title="AJOUTER EXEMPLAIRE" style="min-width: 500px;width: 25%;margin: 10% auto;box-shadow: 0px 5px 10px darkgray">
      <b-button style="float: right" variant="warning" v-on:click="$emit('fermerPopUp')">ANNULER</b-button>

      <div>
        <div class="component-card">
          <input type="text" maxlength="5" v-model="codeExemplaire">
          <label>Code Exemplaire</label>
        </div>
        <div class="component-card">
          <input type="text" v-model="editionExemplaire">
          <label>Edition Exemplaire</label>
        </div>
        <div class="component-card">
          <input type="date" v-model="dateParutionExemplaire">
          <label>Date de parution Exemplaire</label><br>
        </div>

        <b-button variant="success" v-bind:disabled="changeClicAjouterExemplaire" v-on:click="postFormAjouterExemplaire()">AJOUTER</b-button><span>{{reponseAPI}}</span>
      </div>
    </b-card>
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
