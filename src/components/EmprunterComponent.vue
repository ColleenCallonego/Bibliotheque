<template>
  <div>
    <h2>Emprunts</h2>
    <div>
      <input type="radio" value="livre" v-model="typeOeuvre" v-on:click="titreOeuvre = '';numeroMagazine = ''">
      <label>Livre</label>
      <input type="radio" value="magazine" v-model="typeOeuvre" v-on:click="titreOeuvre = '';sousTitreLivre = ''">
      <label>Magazine</label><br>

      <input type="text" v-model="titreOeuvre">
      <label>Titre</label>
      <span v-if="typeOeuvre == 'livre'">
        <input type="text" v-model="sousTitreLivre">
        <label>Sous-titre</label>
      </span>
      <span v-else-if="typeOeuvre == 'magazine'">
        <input type="number" min="1" v-model="numeroMagazine">
        <label>Numéro</label>
      </span>

      <br>
      <input type="text" v-model="nomUsager">
      <label>Nom usager</label>
      <input type="text" v-model="prenomUsager">
      <label>Prénom usager</label><br>

      <input type="text" disabled placeholder="Code d'exempaire">
      <button v-on:click="postFormDisponibilite()">DISPONIBILITE</button><span>{{reponse1API}}</span>

      <p id="repDisponibilite" v-bind:content="repDispo">{{repDispo}}</p>
      <button type="submit" value="emprunter" formaction="/emprunt/creer" v-bind:disabled="empruntImpossible">EMPRUNTER</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data (){
    return {
      titreOeuvre: "",
      typeOeuvre: null,
      sousTitreLivre: "",
      numeroMagazine: "",
      nomUsager: "",
      prenomUsager: "",

      codeExemplaire: "",
      repDispo: "L'oeuvre que vous cherchez n'est malheuresement pas disponible pour le moment.",
      empruntImpossible: true,

      reponse1API: null,
      errors1: [],
    }
  },
  methods: {
    postFormDisponibilite() {

      let param1 = new URLSearchParams()
      param1.append('nom', this.titreOeuvre)
      if (this.typeOeuvre == 'livre'){
        param1.append('sousNom', this.sousTitreLivre)
        axios.post('/oeuvre/identificationLivre', param1)
            .then(response => (this.reponseAPI = response.data))
            .catch(e => {
              this.errors.push(e)
            })
      }
      else {
        param1.append('numero', this.numeroMagazine)
        axios.post('/oeuvre/identificationMagazine', param1)
            .then(response => (this.reponseAPI = response.data))
            .catch(e => {
              this.errors.push(e)
            })
      }



    }
  }
}
</script>
