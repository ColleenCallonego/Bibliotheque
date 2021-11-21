<template>
  <div class="overlay">
    <b-card title="AJOUTER USAGER" style="min-width: 474px;width: 25%;margin: 10% auto;box-shadow: 0px 5px 10px darkgray">
      <b-button style="float: right" variant="warning" v-on:click="$emit('fermerPopUp')">ANNULER</b-button>

      <div>
        <div class="input-class">
          <input type="text" id="nom" name="nom" v-model="nom">
          <label for="nom">Nom</label>
        </div>
        <div class="input-class">
          <input type="text" id="prenom" name="prenom" v-model="prenom">
          <label for="prenom">Prenom</label>
        </div>
        <div class="input-class">
          <input type="text" id="mail" name="mail" v-model="mail">
          <label for="mail">Mail</label>
        </div>
        <div class="input-class">
          <input type="text" id="adresse" name="adresse" v-model="adresse">
          <label for="adresse">Adresse</label>
        </div>
        <b-button variant="success" v-bind:disabled="changeClicAjouterUsager" v-on:click="postForm()">AJOUTER</b-button><span>{{reponseAPI}}</span>
      </div>
    </b-card>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      nom: '',
      prenom: '',
      mail: '',
      adresse: '',
      errors: [],
      reponseAPI: null,
    }
  },
  computed: {
    changeClicAjouterUsager: function (){
      if (this.nom != '' && this.prenom != '' && this.mail != '' && this.adresse != ''){
        return false
      }
      else {
        return true
      }
    }
  },
  methods: {
    postForm() {
      let param = new URLSearchParams()
      param.append('nom', this.nom)
      param.append('prenom', this.prenom)
      param.append('mail', this.mail)
      param.append('adresse', this.adresse)
      axios.post('/usagers/creer', param)
          .then(response => {
            this.reponseAPI = response.data
            this.$emit('ajouterUsager')
          })
    }
  }
}
</script>

<style>
.overlay {
  position: fixed;
  left: 0px;
  top:0px;
  background-color: rgba(0, 0, 0, 0.5);
  width: 100%;
  height: 100%;
  z-index:2;
}
</style>
