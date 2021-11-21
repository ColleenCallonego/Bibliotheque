<template>
  <div>
    <b-card title="Retours" style="max-width: 50rem; box-shadow: 0px 5px 10px darkgray">
      <div>

        <div class="input-class">
          <b-icon class="bIcone" icon="person-fill"></b-icon>
          <input type="text" v-model="nomUsagerRetour">
          <label>Nom usager</label>
          <input type="text" v-model="prenomUsagerRetour">
          <label>Prénom usager</label><br>
        </div>

        <div class="input-class">
          <input type="radio" value="livre" v-model="typeOeuvreRetour" v-on:click="titreOeuvreRetour = '';numeroMagazineRetour = ''">
          <label>Livre</label>
          <input type="radio" value="magazine" v-model="typeOeuvreRetour" v-on:click="titreOeuvreRetour = '';sousTitreLivreRetour = ''">
          <label>Magazine</label><br>
        </div>

        <div class="input-class">
          <b-icon class="bIcone" icon="book-fill"></b-icon>
          <input type="text" v-model="titreOeuvreRetour">
          <label>Titre</label>
          <span v-if="typeOeuvreRetour == 'livre'">
            <input type="text" v-model="sousTitreLivreRetour">
            <label>Sous-titre</label>
          </span>
          <span v-else-if="typeOeuvreRetour == 'magazine'">
            <input type="number" min="1" v-model="numeroMagazineRetour">
            <label>Numéro</label>
          </span>
        </div>

        <div class="input-class">
          <input type="text" v-model="codeExemplaireRetour">
          <label>Code exemplaire</label><br>
        </div>

        <div class="input-class">
          <input type="checkbox" v-model="checked">
          <label>Abîmé ?</label><br>
        </div>

        <div class="input-class">
          <b-button style="background-color: #e5dcd1;color: black" v-bind:disabled="changeClicRendre" v-on:click="postFormRetour()">RENDRE</b-button><span>{{reponseAPI}}</span><br>

          <p v-if="this.usagerIdRetour == 'inexistant'">Nous n'avons pas trouvé votre compte usager, vérifiez l'orthographe ou créez-vous un compte.</p>
          <p v-else-if="this.oeuvreIdRetour == 'inexistant'">Malheureusement nous ne connaisons pas l'oeuvre que vous voulez rendre, vérifiez l'orthographe.</p>
          <p v-else-if="this.exemplaireIdRetour == 'inexistant'">Malheureusement nous ne connaisons pas l'exemplaire que vous voulez rendre, vérifiez l'orthographe.</p>
          <p v-else-if="this.empruntIdRetour == 'inexistant'">Il semblerait que vous n'ayez jamais emprunté cet exemplaire, que fait-vous avec ?</p>
        </div>

      </div>
    </b-card>

  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      nomUsagerRetour: '',
      prenomUsagerRetour: '',
      titreOeuvreRetour: '',
      typeOeuvreRetour: null,
      sousTitreLivreRetour: '',
      numeroMagazineRetour: '',
      codeExemplaireRetour: '',
      checked: false,

      usagerIdRetour: null,
      oeuvreIdRetour: null,
      exemplaireIdRetour: null,
      empruntIdRetour: null,

      reponseAPI: null,
      errors1: [],
    }
  },
  computed: {
    changeClicRendre: function (){
      if (this.titreOeuvreRetour != '' && (this.sousTitreLivreRetour != '' || this.numeroMagazineRetour != '') && this.nomUsagerRetour != '' && this.prenomUsagerRetour != '' && this.codeExemplaireRetour != ''){
        return false
      }
      else {
        return true
      }
    },
    etatExemplaireRetour: function (){
      if (this.checked){
        return 'Abime'
      }
      else{
        return 'En rayon'
      }
    }
  },
  methods: {
    recupererIdUsager() {
      let param = new URLSearchParams()
      param.append('nom', this.nomUsagerRetour)
      param.append('prenom', this.prenomUsagerRetour)
      axios.get('/usagers/identification', {params: param})
          .then(response => {
            if (response.data != ''){
              this.usagerIdRetour = response.data.id
              this.recupererIdOeuvre()
            }
            else{
              this.usagerIdRetour = 'inexistant'
            }
          })
    },
    recupererIdExemplaire(){
      let param = new URLSearchParams()
      param.append('oeuvre', this.oeuvreIdRetour)
      param.append('codeExemplaire', this.codeExemplaireRetour)
      axios.get('/exemplaires/identification', {params: param})
          .then(response => {
            if (response.data != ''){
              this.exemplaireIdRetour = response.data.id
              this.recupererIdEmprunt()
            }
            else{
              this.exemplaireIdRetour = 'inexistant'
            }
          })
    },
    recupererIdOeuvre(){
      let param = new URLSearchParams()
      param.append('nom', this.titreOeuvreRetour)
      if (this.typeOeuvreRetour == 'livre'){
        param.append('sousNom', this.sousTitreLivreRetour)
        axios.get('/oeuvres/identificationLivre', {params: param})
            .then(response => {
              if (response.data != ''){
                this.oeuvreIdRetour = response.data.id
                this.recupererIdExemplaire()
              }
              else{
                this.oeuvreIdRetour = 'inexistant'
              }
            })
      }
      else {
        param.append('numero', this.numeroMagazineRetour)
        axios.get('/oeuvres/identificationMagazine', {params: param})
            .then(response => {
              if (response.data != ''){
                this.oeuvreIdRetour = response.data.id
                this.recupererIdExemplaire()
              }
              else{
                this.oeuvreIdRetour = 'inexistant'
              }
            })
      }
    },
    recupererIdEmprunt(){
      let param = new URLSearchParams()
      param.append('exemplaire', this.exemplaireIdRetour)
      param.append('usager', this.usagerIdRetour)
      axios.get('/emprunts/identification', {params: param})
          .then(response => {
            if (response.data != ''){
              this.empruntIdRetour = response.data.id
              this.modifierEmpruntRetour()
            }
            else{
              this.empruntIdRetour = 'inexistant'
            }
          })
    },
    modifierEmpruntRetour(){
      let param = new URLSearchParams()
      param.append('emprunt', this.empruntIdRetour)
      param.append('etatEx', this.etatExemplaireRetour)
      axios.patch('/emprunts/modifier', param)
          .then(response => (this.reponseAPI = response.data))
    },
    postFormRetour() {
      this.usagerIdRetour = null
      this.oeuvreIdRetour = null
      this.exemplaireIdRetour = null
      this.empruntIdRetour = null
      this.recupererIdUsager()
    }
  }
}
</script>
