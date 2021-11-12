<template>
  <div>
    <h2>Réserver une oeuvre</h2>
    <form method="post" v-on:submit.prevent>
      <input type="text" id="oeuvreAReserver" name="oeuvreAReserver">
      <label for="oeuvreAReserver">Oeuvre</label>
      <input type="hidden" id="usagerQuiReserve" name="usagerQuiReserve" v-bind:value="numUsager">

      <button type="submit" value="reserver" formaction="/reservation/creer">RESERVER</button>
    </form>
  </div>
</template>

<script>
export default {
  props: ['numUsager'],
}
</script>
