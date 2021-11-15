<template>
  <div v-if="this.usager.nom.indexOf(this.nomRecherche) != -1 && this.usager.prenom.indexOf(this.prenomRecherche) != -1">
    <h3>{{this.usager.nom}} {{this.usager.prenom}}</h3>
    <div>
      <p>Adresse : {{this.usager.adresse}}</p>
      <p>Mail : {{this.usager.mail}}</p>
      <p>Nombre de pénalité : {{this.usager.penalite}}</p>

      <button>SUPPRIMER</button>
      <button>MODIFIER</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {

    }
  },
  props: ['usager', 'nomRecherche', 'prenomRecherche'],
}
</script>
