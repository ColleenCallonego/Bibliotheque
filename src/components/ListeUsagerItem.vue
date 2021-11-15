<template>
  <div v-if="this.usager.nom.indexOf(this.nomRecherche) != -1 && this.usager.prenom.indexOf(this.prenomRecherche) != -1">
    <h3>{{this.usager.nom}} {{this.usager.prenom}}</h3>
    <div>
      <p>Adresse : {{this.usager.adresse}}</p>
      <p>Mail : {{this.usager.mail}}</p>
      <p>Nombre de pénalité : {{this.usager.penalite}}</p>

      <button v-on:click="supprimerUsager">SUPPRIMER</button>
      <button>MODIFIER</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      usagerId: null,
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
    supprimerUsager() {
      let param = new URLSearchParams()
      param.append('usager', this.usagerId)
      axios.delete('/usagers/supprimer', {params: param})
          .then(response => {
            this.truc = response.data
            this.$emit('supprimerUsager')
          })
    }
  }
}
</script>
