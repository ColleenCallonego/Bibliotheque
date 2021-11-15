<template>
  <div>
    <h3>{{}}</h3>
    <div>
      <p>{{}}</p>
      <p>{{}}</p>
      <p>{{}}</p>

      <p v-if="oeuvreDispo">Dispo<button>RECUP</button></p>
      <p v-else>Pas dispo</p>
      <button>ANNULER</button>
    </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      oeuvreDispo: true,
    }
  },
  props: ['reservation'],
  computed: {

  }
}
</script>
