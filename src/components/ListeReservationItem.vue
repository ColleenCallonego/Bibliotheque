<template>
  <!--Livre

  annuler reservation ->
  reservations/modifier  idRES(on l'a)
  puis chaine event pour recharger les données

  recuperer reservation ->
  reservations/modifier  idRES(on l'a)
  exemplaire/reserver idOEUVRE(on l'a)
  emprunts/creer idUsager idOeuvre idExemplaire(recupérer juste avant)

  -->
  <div v-if="typeOeuvre == 'L'">
    <h3>{{reservation.oeuvre.nom}} - {{reservation.oeuvre.sousNom}}</h3>
    <div>
      <p v-if="reservation.etat == 'Prete'">Disponible<button>RECUPERER</button></p>
      <p v-else>Pas disponible</p>
      <button>ANNULER</button>
    </div>

  </div>
  <!--Magazine-->
  <div v-else>
    <h3>{{reservation.id}}</h3>
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

    }
  },
  props: ['reservation'],
  computed: {
    typeOeuvre: function (){
      if (this.reservation.oeuvre.sousNom == null){
        return 'M'
      }
      else{
        return 'L'
      }
    }
  },
}
</script>
