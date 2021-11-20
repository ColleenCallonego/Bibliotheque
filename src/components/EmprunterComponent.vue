<template>
  <div>
    <b-card title="Emprunts" style="max-width: 50rem; box-shadow: 0px 5px 10px darkgray">
      <div>

        <div class="input-class">
          <input type="radio" value="livre" v-model="typeOeuvre" v-on:click="titreOeuvre = '';numeroMagazine = ''">
          <label>Livre</label>
          <input type="radio" value="magazine" v-model="typeOeuvre" v-on:click="titreOeuvre = '';sousTitreLivre = ''">
          <label>Magazine</label><br>
        </div>

        <div class="input-class">
          <b-icon class="bIcone" icon="book-fill"></b-icon>
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
        </div>

        <div class="input-class">
          <b-icon class="bIcone" icon="person-fill"></b-icon>
          <input type="text" v-model="nomUsager">
          <label>Nom usager</label>
          <input type="text" v-model="prenomUsager">
          <label>Prénom usager</label><br>
        </div>

        <div class="input-class">
          <input type="text" disabled placeholder="Code d'exempaire" v-model="codeExemplaire">
          <button v-bind:disabled="changeClicDispo" v-on:click="postFormDisponibilite()">DISPONIBILITE</button><br>
        </div>

        <div class="input-class">
          <p v-if="this.usagerId == 'inexistant'">Nous n'avons pas trouvé votre compte usager, vérifiez l'orthographe ou créez-vous un compte.</p>
          <p v-else-if="this.oeuvreId == 'inexistant'">Malheureusement nous n'avons pas l'oeuvre que vous recherchez dans dans nos rayons.</p>
          <p v-else-if="this.exemplaireId == 'inexistant'">L'oeuvre que vous cherchez n'est malheuresement pas disponible pour le moment.</p>
          <p v-else-if="this.oeuvreId != null && this.exemplaireId != null && this.oeuvreId != 'inexistant' && this.exemplaireId != 'inexistant'">L'oeuvre que vous recherchez est disponible, vous pouvez voir ci-dessus le code de l'exemplaire que nous avons en rayon.</p>
          <button v-bind:disabled="changeClicEmprunt" v-on:click="postFormEmprunt()">EMPRUNTER</button><span>{{reponseAPI}}</span>
        </div>

      </div>
    </b-card>

  </div>
</template>

<script>
import axios from 'axios';

export default {
  data (){
    return {
      titreOeuvre: '',
      typeOeuvre: null,
      sousTitreLivre: '',
      numeroMagazine: '',
      nomUsager: '',
      prenomUsager: '',
      oeuvreId: null,
      exemplaireId: null,
      usagerId: null,
      codeExemplaire: '',

      reponseAPI: null,
      errors1: [],
    }
  },
  computed: {
    changeClicDispo: function() {
      if (this.titreOeuvre != '' && (this.sousTitreLivre != '' || this.numeroMagazine != '') && this.nomUsager != '' && this.prenomUsager != ''){
        return false
      }
      else {
        return true
      }
    },
    changeClicEmprunt: function() {
      if (this.codeExemplaire != ''){
        return false
      }
      else {
        return true
      }
    }
  },
  methods: {
    recupererIdUsager() {
      let param = new URLSearchParams()
      param.append('nom', this.nomUsager)
      param.append('prenom', this.prenomUsager)
      axios.get('/usagers/identification', {params: param})
          .then(response => {
            if (response.data != ''){
              this.usagerId = response.data.id
              this.recupererIdOeuvre()
            }
            else{
              this.usagerId = 'inexistant'
            }
          })
    },
    recupererIdExemplaire(){
      let param = new URLSearchParams()
      param.append('oeuvre', this.oeuvreId)
      axios.get('/exemplaires/exemplaireDisponible', {params: param})
          .then(response => {
            if (response.data != ''){
              this.exemplaireId = response.data.id
              this.codeExemplaire = response.data.codeExemplaire
            }
            else{
              this.exemplaireId = 'inexistant'
            }
          })
    },
    recupererIdOeuvre(){
      let param = new URLSearchParams()
      param.append('nom', this.titreOeuvre)
      if (this.typeOeuvre == 'livre'){
        param.append('sousNom', this.sousTitreLivre)
        axios.get('/oeuvres/identificationLivre', {params: param})
            .then(response => {
              if (response.data != ''){
                this.oeuvreId = response.data.id
                this.recupererIdExemplaire()
              }
              else{
                this.oeuvreId = 'inexistant'
              }
            })
      }
      else {
        param.append('numero', this.numeroMagazine)
        axios.get('/oeuvres/identificationMagazine', {params: param})
            .then(response => {
              if (response.data != ''){
                this.oeuvreId = response.data.id
                this.recupererIdExemplaire()
              }
              else{
                this.oeuvreId = 'inexistant'
              }
            })
      }
    },
    postFormEmprunt(){
      let param = new URLSearchParams()
      param.append('exemplaire', this.exemplaireId)
      param.append('usager', this.usagerId)
      axios.post('/emprunts/creer', param)
          .then(response => (this.reponseAPI = response.data))

    },
    postFormDisponibilite() {
      this.oeuvreId = null
      this.exemplaireId = null
      this.usagerId = null
      this.codeExemplaire = ''
      this.recupererIdUsager()
    }
  }
}
</script>

<style>
.input-class{
  margin: 10px 0 10px 0;
}
label{
  margin: 0 30px 0 10px;
}
.bIcone{
  margin-right: 10px;
}
</style>
