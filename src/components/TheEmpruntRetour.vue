<template>
  <div style="padding: 10px 0 0 20px">
    <h1>Emprunts et retours d'oeuvres</h1>
    <EmprunterComponent class="component-card"></EmprunterComponent>
    <RetournerComponent class="component-card"></RetournerComponent>
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

<style>
.component-card{
  margin: 20px 0 20px 0;
}

</style>