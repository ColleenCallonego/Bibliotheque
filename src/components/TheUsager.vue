<template>
  <!--<div>
    <h2>Person Details</h2>
    <div>
      <label for="nom">Nom:</label><br>
      <input type="text" id="nom" name="nom" v-model="nom"><br>
      <label for="prenom">Prenom:</label><br>
      <input type="text" id="prenom" name="prenom" v-model="prenom"><br><br>
      <label for="mail">Mail:</label><br>
      <input type="text" id="mail" name="mail" v-model="mail"><br><br>
      <label for="adresse">Adresse:</label><br>
      <input type="text" id="adresse" name="adresse" v-model="adresse"><br><br>
      <button v-on:click="postForm()">AJOUTER</button>
    </div>
    <h1>{{reponseAPI}}</h1>
    <ul v-if="errors && errors.length">
      <li v-for="error of errors" v-bind:key="error">
        {{error.message}}
      </li>
    </ul>
  </div>-->


  <div>
    <button v-on:click="ouvrePopUpAjouterUsager">AJOUTER USAGER</button>
    <PopUpAjouterUsager v-if="popUpEtat" v-on:fermerPopUp="popUpEtat = false"></PopUpAjouterUsager>





  </div>








</template>

<script>

import PopUpAjouterUsager from "@/components/PopUpAjouterUsager";

export default {
  components: {
    PopUpAjouterUsager
  },
  data() {
    return {
      popUpEtat: false
    }
  },
  methods: {
    ouvrePopUpAjouterUsager() {
      this.popUpEtat = true
    }
  }
}























/* import axios from 'axios';

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
} */
</script>