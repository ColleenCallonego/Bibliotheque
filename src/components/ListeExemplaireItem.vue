<template>
  <b-card style="min-width: 300px;height: 250px; box-shadow: 0px 5px 10px darkgray; display: inline-block; margin: 15px 15px 15px 15px; vertical-align: middle">
    <div v-if="enMofification">
      <h4>{{exemplaire.codeExemplaire}}</h4>
      <b-select v-model="newEtat">
        <option value="En rayon">En rayon</option>
        <option value="Abime">Abimé</option>
      </b-select>
      <label>Nouvel état</label>

      <div style="margin-top: 95px">
        <b-button variant="warning" v-on:click="changerEnModification">ANNULER</b-button>
        <b-button style="float: right" variant="success" v-on:click="modifierExemplaire">ENREGISTRER</b-button>
      </div>
    </div>

    <div v-else>
      <h4>{{exemplaire.codeExemplaire}}</h4>
      <p>Maison d'édition : {{exemplaire.edition}}</p>
      <p>Date de parution : {{dateExemplaire}}</p>
      <p>Disponibilité : {{exemplaire.etat}}</p>

      <b-button variant="danger" v-bind:disabled="estSupprimable" v-on:click="supprimerExemplaire">SUPPRIMER</b-button>
      <b-button style="float: right" variant="warning" v-bind:disabled="estModifiable" v-on:click="changerEnModification">MODIFIER</b-button>
    </div>
  </b-card>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      dateExemplaire: null,
      enMofification: false,
      newEtat: 'En rayon',
    }
  },
  mounted() {
    const date = new Date(this.exemplaire.dateParution)
    const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' }
    this.dateExemplaire = date.toLocaleDateString(undefined, options)
  },
  props: ['exemplaire'],
  computed: {
    estSupprimable(){
      if (this.exemplaire.etat == 'Emprunte' || this.exemplaire.etat == 'Reserve'){
        return true
      }
      else{
        return false
      }
    },
    estModifiable(){
      if (this.exemplaire.etat == 'En rayon'){
        return false
      }
      else{
        return true
      }
    },
  },
  methods: {
    changerEnModification(){
      if (!this.enMofification){
        this.newEtat = "En rayon"
      }
      this.enMofification = !this.enMofification
    },
    supprimerExemplaire(){
      let param = new URLSearchParams()
      param.append('exemplaire', this.exemplaire.id)
      axios.delete('/exemplaires/supprimer', {params: param})
          .then(response => {
            this.truc = response.data
            this.$emit('modifierSupprimerExemplaire')
          })
    },
    modifierExemplaire() {
      let param = new URLSearchParams()
      param.append('exemplaire', this.exemplaire.id)
      param.append('etat', this.newEtat)
      axios.patch('/exemplaires/modifier', param)
          .then(response => {
            this.truc = response.data
            this.$emit('modifierSupprimerExemplaire')
            this.changerEnModification()
          })
    }
  }
}
</script>
