<template>
  <div class="overlay">
    <b-card title="AJOUTER OEUVRE" style="min-width: 474px;width: 25%;margin: 10% auto;box-shadow: 0px 5px 10px darkgray">
      <b-button style="float: right" variant="warning" v-on:click="$emit('fermerPopUp')">ANNULER</b-button>

      <div>
        <div class="input-class">
          <input type="text" v-model="nomOeuvre">
          <label>Titre</label>
        </div>
        <div class="input-class">
          <input type="text" v-model="auteurOeuvre">
          <label>Auteur</label>
        </div>
        <div class="input-class">
          <input type="number" min="1" v-model="nbPageOeuvre">
          <label>Nombre de page</label>
        </div>
        <div class="input-class">
          <input type="number" min="7" v-model="dureePret">
          <label>Durée du prêt</label>
        </div>
        <div class="input-class">
          <input type="radio" value="livre" v-model="typeAjout" v-on:click="numeroMagazine = '';categorieMagazine = ''">
          <label>Livre</label>
          <input type="radio" value="magazine" v-model="typeAjout" v-on:click="sousNomLivre = '';resumeLivre = ''">
          <label>Magazine</label>
        </div>

        <div v-if="typeAjout == 'livre'">
          <div class="input-class">
            <input type="text" v-model="sousNomLivre">
            <label>Sous-titre</label>
          </div>
          <div class="input-class">
            <textarea type="text" v-model="resumeLivre"></textarea>
            <label>Résumé</label>
          </div>
          <b-button variant="success" v-bind:disabled="changeClicAjouterLivre" v-on:click="postFormLivre()">AJOUTER</b-button><span>{{reponseAPI}}</span>
        </div>
        <div v-else-if="typeAjout == 'magazine'">
          <div class="input-class">
            <input type="number" min="1" v-model="numeroMagazine">
            <label>Numéro</label>
          </div>
          <div class="input-class">
            <input type="text" v-model="categorieMagazine">
            <label>Catégorie</label>
          </div>
          <b-button variant="success" v-bind:disabled="changeClicAjouterMagazine" v-on:click="postFormMagazine()">AJOUTER</b-button><span>{{reponseAPI}}</span>
        </div>

      </div>
    </b-card>
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
      reponseAPI: null,
    }
  },
  computed: {
    changeClicAjouterLivre: function (){
      if (!this.changeClicAjouterOeuvre() && this.sousNomLivre != '' && this.resumeLivre != ''){
        return false
      }
      else {
        return true
      }
    },
    changeClicAjouterMagazine: function (){
      if (!this.changeClicAjouterOeuvre() && this.numeroMagazine != '' && this.categorieMagazine != ''){
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
          .then(response => {
            this.reponseAPI = response.data
            this.$emit('ajouterOeuvre')
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
          .then(response => {
            this.reponseAPI = response.data
            this.$emit('ajouterOeuvre')
          })
    }
  }
}
</script>
