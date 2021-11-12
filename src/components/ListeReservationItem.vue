<template>
  <div>
    <h3>{{title}}</h3>
    <div>
      <p>{{info1}}</p>
      <p>{{info2}}</p>
      <p>{{info3}}</p>

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
      info1: 'info1',
      info2: 'info2',
      info3: 'info3',
      oeuvreDispo: true
    }
  },
  props: ['title'],
}
</script>
