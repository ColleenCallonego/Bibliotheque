<template>
  <div>
    <form method="post" v-on:submit.prevent>
      <input type="text" id="reservationUsagerAIdentifier" name="reservationUsagerAIdentifier">
      <label for="reservationUsagerAIdentifier">Usager</label>

      <button type="submit" value="reservationIdentifierUsager" formaction="/usager/recupererReservation" v-on:click="$emit('recupUsager', Math.random()*10)">OK</button><!--formaction reste à définir-->
    </form>
  </div>
</template>

<script>
export default {

}
</script>
>