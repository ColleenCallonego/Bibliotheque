<template>
  <b-card title="Liste des exemplaires" style="box-shadow: 0px 5px 10px darkgray;width: 99%;">
    <div>
      <ListeExemplaireItem
          v-for="exemplaire in exemplairesTab"
          v-bind:key="exemplaire.id"
          v-bind:exemplaire="exemplaire"
          v-on:modifierSupprimerExemplaire="$emit('modifierSupprimerExemplaire')"
      >
      </ListeExemplaireItem>
    </div>
  </b-card>
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
