<template>
  <div v-if="enMofification">
    <h3>{{exemplaire.codeExemplaire}}</h3>
    <select v-model="newEtat">
      <option value="En rayon">En rayon</option>
      <option value="Abime">Abimé</option>
    </select>
    <label>Nouvel état</label>
    <br>

    <button v-on:click="changerEnModification">ANNULER</button>
    <button v-on:click="modifierExemplaire">ENREGISTRER</button>
  </div>

  <div v-else>
    <h3>{{exemplaire.codeExemplaire}}</h3>
    <p>Maison d'édition : {{exemplaire.edition}}</p>
    <p>Date de parution : {{dateExemplaire}}</p>
    <p>Disponibilité : {{exemplaire.etat}}</p>

    <button v-bind:disabled="estSupprimable" v-on:click="supprimerExemplaire">SUPPRIMER</button>
    <button v-bind:disabled="estModifiable" v-on:click="changerEnModification">MODIFIER</button>
  </div>
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
