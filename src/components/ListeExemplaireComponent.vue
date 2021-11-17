<template>
  <div>
    <h2>Liste des exemplaires</h2>

    <ListeExemplaireItem
        v-for="exemplaire in exemplairesTab"
        v-bind:key="exemplaire.id"
        v-bind:exemplaire="exemplaire"
        v-on:modifierSupprimerExemplaire="$emit('modifierSupprimerExemplaire')"
    >
    </ListeExemplaireItem>
  </div>
</template>

<script>
import ListeExemplaireItem from "@/components/ListeExemplaireItem";

export default {
  components: {
    ListeExemplaireItem
  },
  props: ['exemplairesTab'],
}
</script>
