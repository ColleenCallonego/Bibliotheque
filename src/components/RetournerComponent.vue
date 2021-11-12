<template>
  <div>
    <h2>Retours</h2>
    <form method="post" v-on:submit.prevent>
      <input type="text" id="usagerQuiRend" name="usagerQuiRend">
      <label for="usagerQuiRend">Usager</label><br>
      <input type="text" id="oeuvreARendre" name="oeuvreARendre">
      <label for="oeuvreARendre">Oeuvre</label><br>
      <input type="text" id="exemplaireARendre" name="exemplaireARendre">
      <label for="exemplaireARendre">Exemplaire</label><br>

      <input type="checkbox" id="etatExemplaire" name="etatExemplaire">
      <label for="etatExemplaire">Abîmé ?</label><br>

      <button type="submit" value="rendre" formaction="/emprunt/modifier">RENDRE</button>
    </form>
  </div>
</template>

<script>
export default {

}
</script>
