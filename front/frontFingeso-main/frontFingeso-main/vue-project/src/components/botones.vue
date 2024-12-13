<script setup>
import { ref } from "vue";
import { defineProps } from "vue";
import Boton from "./Boton.vue";

const contador = ref(0);

//metodo para agregar emojis por cada numero que se incremente
const emoji = () => {
  if (contador.value > 0) {
    return "👍";
  } else if (contador.value < 0) {
    return "👎";
  } else {
    return "";
  }
};
</script>