<template>
  <b-card style="min-width: 300px;height: 250px; box-shadow: 0px 5px 10px darkgray; display: inline-block; margin: 15px 15px 15px 15px; vertical-align: middle" v-if="this.usager.nom.indexOf(this.nomRecherche) != -1 && this.usager.prenom.indexOf(this.prenomRecherche) != -1">
    <div v-if="enMofification">
      <div class="input-class">
        <input type="text" v-model="nom" placeholder="Nom"><input type="text" v-model="prenom" placeholder="Prénom">
      </div>

      <div class="input-class">
       <input type="text" v-model="adresse" placeholder="Adresse">
      </div>

      <div class="input-class">
        <input type="text" v-model="mail" placeholder="Mail">
      </div>

      <div style="margin-top: 60px">
        <b-button variant="warning" v-on:click="changerEnModification">ANNULER</b-button>
        <b-button style="float: right" variant="success" v-on:click="modifierUsager">ENREGISTRER</b-button>
      </div>
    </div>

    <div v-else>
      <h4>{{this.usager.nom}} {{this.usager.prenom}}</h4><br>
      <div>
        <p>Adresse : {{this.usager.adresse}}</p>
        <p>Mail : {{this.usager.mail}}</p>
        <p>Nombre de pénalité : {{this.usager.penalite}}</p>

        <b-button variant="danger" v-on:click="supprimerUsager">SUPPRIMER</b-button>
        <b-button style="float: right" variant="warning" v-on:click="changerEnModification">MODIFIER</b-button>
      </div>
    </div>

  </b-card>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      usagerId: null,
      enMofification: false,
      nom: '',
      prenom: '',
      adresse: '',
      mail: '',
    }
  },
  props: ['usager', 'nomRecherche', 'prenomRecherche'],
  mounted() {
    let param = new URLSearchParams()
    param.append('nom', this.usager.nom)
    param.append('prenom', this.usager.prenom)
    axios.get('/usagers/identification', {params: param})
        .then(response => (this.usagerId = response.data.id))
  },
  methods: {
    changerEnModification(){
      if (!this.enMofification){
        this.nom = this.usager.nom
        this.prenom = this.usager.prenom
        this.adresse = this.usager.adresse
        this.mail = this.usager.mail
      }
      this.enMofification = !this.enMofification
    },
    supprimerUsager() {
      let param = new URLSearchParams()
      param.append('usager', this.usagerId)
      axios.delete('/usagers/supprimer', {params: param})
          .then(response => {
            this.truc = response.data
            this.$emit('supprimerModifierUsager')
          })
    },
    modifierUsager() {
      let param = new URLSearchParams()
      param.append('usager', this.usagerId)
      param.append('nom', this.nom)
      param.append('prenom', this.prenom)
      param.append('mail', this.mail)
      param.append('adresse', this.adresse)
      axios.patch('/usagers/modifier', param)
          .then(response => {
            this.truc = response.data
            this.$emit('supprimerModifierUsager')
            this.changerEnModification()
          })
    }
  }
}
</script>
