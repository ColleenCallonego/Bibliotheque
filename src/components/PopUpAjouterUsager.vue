<template>
  <div>
    <h1>AJOUTER USAGER</h1>
    <button v-on:click="$emit('fermerPopUp')">ANNULER</button>

    <div>
      <input type="text" id="nom" name="nom" v-model="nom">
      <label for="nom">Nom</label><br>
      <input type="text" id="prenom" name="prenom" v-model="prenom">
      <label for="prenom">Prenom</label><br>
      <input type="text" id="mail" name="mail" v-model="mail">
      <label for="mail">Mail</label><br>
      <input type="text" id="adresse" name="adresse" v-model="adresse">
      <label for="adresse">Adresse</label><br>

      <button v-bind:disabled="changeClicAjouterUsager" v-on:click="postForm()">AJOUTER</button><span>{{reponseAPI}}</span>
    </div>
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
          .then(response => (this.reponseAPI = response.data))
          .catch(e => {
            this.errors.push(e)
          })
    }
  }
}
</script>
