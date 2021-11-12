<template>
  <div>
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