<template>
  <div>
    <h2>Retours</h2>
    <div>
      <input type="text" v-model="nomUsagerRetour">
      <label>Nom usager</label>
      <input type="text" v-model="prenomUsagerRetour">
      <label>Prénom usager</label><br>



      <input type="radio" value="livre" v-model="typeOeuvreRetour" v-on:click="titreOeuvreRetour = '';numeroMagazineRetour = ''">
      <label>Livre</label>
      <input type="radio" value="magazine" v-model="typeOeuvreRetour" v-on:click="titreOeuvreRetour = '';sousTitreLivreRetour = ''">
      <label>Magazine</label><br>

      <input type="text" v-model="titreOeuvreRetour">
      <label>Titre</label>
      <span v-if="typeOeuvre == 'livre'">
        <input type="text" v-model="sousTitreLivreRetour">
        <label>Sous-titre</label>
      </span>
      <span v-else-if="typeOeuvre == 'magazine'">
        <input type="number" min="1" v-model="numeroMagazineRetour">
        <label>Numéro</label>
      </span>



      <input type="text" v-model="codeExemplaireRetour">
      <label>Code exemplaire</label><br>

      <input type="checkbox" value="En rayon" v-model="etatExemplaireRetour">
      <label>Abîmé ?</label><br>

      <button>RENDRE</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      nomUsagerRetour: '',
      prenomUsagerRetour: '',
      titreOeuvreRetour: '',
      typeOeuvreRetour: null,
      sousTitreLivreRetour: '',
      numeroMagazineRetour: '',
      codeExemplaireRetour: '',
      etatExemplaireRetour: '',
    }
  }
}
</script>
