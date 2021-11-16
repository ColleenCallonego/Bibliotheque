<template>
  <div>
    <h3>{{exemplaire.codeExemplaire}}</h3>
    <p>Maison d'édition : {{exemplaire.edition}}</p>
    <p>Date de parution : {{dateExemplaire}}</p>
    <p>Disponibilité : {{exemplaire.etat}}</p>

    <button>SUPPRIMER</button>
    <button>MODIFIER</button>

    <!--
    usager ajouter update tab
    usager modifier
    usager modifier update tab

    ajouter exemplaire
    ajouter exemplaire update tab
    supprimer exemplaire
    supprimer exemplaire update tab
    modifier exemplaire
    modifier exemplaire update tab

    pas supprimer exemplaire si emprunte ou reserve
    -->

  </div>
</template>

<script>
export default {
  data() {
    return {
      dateExemplaire: null,
    }
  },
  mounted() {
    const date = new Date(this.exemplaire.dateParution)
    const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' }
    this.dateExemplaire = date.toLocaleDateString(undefined, options)
  },
  props: ['exemplaire'],
}
</script>
