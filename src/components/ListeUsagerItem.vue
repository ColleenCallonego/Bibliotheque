<template>
  <div v-if="nom.indexOf(this.nomRecherche) != -1 && prenom.indexOf(this.prenomRecherche) != -1">
    <h3>{{this.nom}} {{this.prenom}}</h3>
    <div>
      <p>{{info1}}</p>
      <p>{{info2}}</p>
      <p>{{info3}}</p>

      <button>SUPPRIMER</button>
      <button>MODIFIER</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      info1: 'info1',
      info2: 'info2',
      info3: 'info3',
    }
  },
  props: ['nom', 'prenom', 'nomRecherche', 'prenomRecherche'],
}
</script>
