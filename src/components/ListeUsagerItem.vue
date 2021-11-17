<template>
  <div v-if="this.usager.nom.indexOf(this.nomRecherche) != -1 && this.usager.prenom.indexOf(this.prenomRecherche) != -1">
    <div v-if="enMofification">
      <input type="text" v-model="nom" placeholder="Nom"><input type="text" v-model="prenom" placeholder="Prénom">
      <div>
        <input type="text" v-model="adresse" placeholder="Adresse">
        <input type="text" v-model="mail" placeholder="Mail">
        <br>

        <button v-on:click="changerEnModification">ANNULER</button>
        <button v-on:click="modifierUsager">ENREGISTRER</button>
      </div>
    </div>

    <div v-else>
      <h3>{{this.usager.nom}} {{this.usager.prenom}}</h3>
      <div>
        <p>Adresse : {{this.usager.adresse}}</p>
        <p>Mail : {{this.usager.mail}}</p>
        <p>Nombre de pénalité : {{this.usager.penalite}}</p>

        <button v-on:click="supprimerUsager">SUPPRIMER</button>
        <button v-on:click="changerEnModification">MODIFIER</button>
      </div>
    </div>

  </div>
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
