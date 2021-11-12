<template>
  <div>
    <h2>Emprunts</h2>
    <form method="post" v-on:submit.prevent>
      <input type="text" id="oeuvreAEmprunter" name="oeuvreAEmprunter">
      <label for="oeuvreAEmprunter">Oeuvre</label>
      <input type="text" id="usagerQuiEmprunte" name="usagerQuiEmprunte">
      <label for="usagerQuiEmprunte">Usager</label><br>

      <input type="text" disabled placeholder="Code d'exempaire">
      <button type="submit" value="disponibilite" formaction="/verifierDisponibilite">DISPONIBILITE</button>

      <p id="repDisponibilite" v-bind:content="repDispo">{{repDispo}}</p>
      <button type="submit" value="emprunter" formaction="/emprunt/creer" v-bind:disabled="empruntImpossible">EMPRUNTER</button>
    </form>
  </div>
</template>

<script>
export default {
  data (){
    return {
      codeExemplaire: "",
      repDispo: "L'oeuvre que vous cherchez n'est malheuresement pas disponible pour le moment.",
      empruntImpossible: true
    }
  }
}
</script>
