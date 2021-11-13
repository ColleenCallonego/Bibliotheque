<template>
  <div>
    <h1>Emprunts et retours d'oeuvres</h1>
    <EmprunterComponent/>
    <RetournerComponent/>
  </div>
</template>

<script>
import EmprunterComponent from "@/components/EmprunterComponent";
import RetournerComponent from "@/components/RetournerComponent";

export default {
  components: {
    EmprunterComponent,RetournerComponent
  }
}
</script>