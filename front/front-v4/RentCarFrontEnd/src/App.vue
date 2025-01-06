<script setup>
import { RouterView } from 'vue-router' 
</script>

<template>
  <RouterView /> <!-- Aquí es donde el router renderizará los componentes -->
</template>

<style>
html,
body { /* Para que el componente ocupe toda la pantalla */
  height: 100vh; 
  width: 50vw;
  margin: 0;
}

#app { 
  height: inherit;
  width: inherit;
}
</style>
