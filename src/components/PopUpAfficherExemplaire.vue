<template>
  <div>
    <h1>Afficher les exemplaires de {{titre}}</h1>
    <button v-on:click="$emit('fermerPopUp')">RETOUR</button>

  </div>
</template>

<script>
export default {
  props: ['titre'],
}
</script>
