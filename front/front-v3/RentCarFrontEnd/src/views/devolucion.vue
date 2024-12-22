<template>
  <div class="vehicle-return container mx-auto p-4">
    <!-- Contenedor principal -->
    <header class="header"> 
      <div class="logo-container">
        <img src="./media/icono2.png" alt="Rent-A-Wheel" class="logo">
        <span class="brand-name">Rent-A-Wheel</span>
      </div>
      <div class="header-buttons"> <!-- Botones de encabezado -->
        <router-link to="/cliente" class="header-button">Página anterior</router-link>  <!-- Botón de devolución de vehículo -->
      </div>
    </header>

    <div class="main-content"> 
      <!-- Contenido principal -->
      <div class="return-form mt-16">
        <h2 class="text-2xl font-bold text-center mb-4">Devolución del Vehículo</h2>
        <form @submit.prevent="handleSubmit" class="space-y-4">
          
          <!-- Número de Placa -->
          <div class="form-group">
            <label for="plateNumber" class="block text-lg font-semibold">Id de reserva: </label>
            <input 
              id="plateNumber" 
              v-model="formData.plateNumber" 
              type="text" 
              class="w-full border border-gray-300 rounded px-2 py-1"
              placeholder="Ingrese el id de reserva" 
              required 
            />
          </div>

          <!-- Kilometraje Final -->
          <div class="form-group">
            <label for="finalMileage" class="block text-lg font-semibold">Kilometraje Final: </label>
            <input 
              id="finalMileage" 
              v-model="formData.finalMileage" 
              type="number" 
              class="w-full border border-gray-300 rounded px-2 py-1"
              placeholder="Ingrese el kilometraje final" 
              required 
            />
          </div>

          <!-- Nivel de Combustible -->
          <div class="form-group">
            <label for="fuelLevel" class="block text-lg font-semibold">Nivel de Combustible:</label>
            <select 
              id="fuelLevel" 
              v-model="formData.fuelLevel" 
              class="w-full border border-gray-300 rounded px-2 py-1" 
              required
            >
              <option value="" disabled selected>Seleccione el nivel de combustible</option>
              <option value="1/4">1/4</option>
              <option value="1/2">1/2</option>
              <option value="3/4">3/4</option>
              <option value="Full">Full</option>
            </select>
          </div>

          <!-- Condición General -->
          <div class="form-group">
            <label for="generalCondition" class="block text-lg font-semibold">Condición General:</label>
            <textarea 
              id="generalCondition" 
              v-model="formData.generalCondition" 
              class="w-full border border-gray-300 rounded px-2 py-1" 
              placeholder="Describa la condición general del vehículo" 
              required
            ></textarea>
          </div>

          <!-- Daños -->
          <div class="form-group flex items-center gap-4">
            <label class="text-lg font-semibold">¿El vehículo tiene daños?</label>
            
            <!-- Checkbox y texto "Sí" alineados -->
            <div class="flex items-center gap-2">
              <input 
                id="hasDamage" 
                v-model="formData.hasDamage" 
                type="checkbox" 
                class="mr-1"
              />
              <label for="hasDamage" class="text-base">Sí</label>
            </div>

            <!-- Botón para "Enviar vehículo a taller" -->
            <button 
              type="button" 
              class="bg-gray-300 px-4 py-2 rounded font-bold hover:bg-gray-400"
              v-if="formData.hasDamage"
            >
              Enviar vehículo a taller de reparación
            </button>
          </div>

          <!-- Descripción de Daños -->
          <div v-if="formData.hasDamage" class="mt-2">
            <label for="damageDescription" class="block text-lg font-semibold">Descripción de los daños:</label>
            <textarea 
              id="damageDescription" 
              v-model="formData.damageDescription" 
              class="w-full border border-gray-300 rounded px-2 py-1" 
              placeholder="Describa los daños encontrados" 
            ></textarea>
          </div>

          <!-- Notas Adicionales -->
          <div>
            <label for="additionalNotes" class="block text-lg font-semibold">Notas Adicionales:</label>
            <textarea 
              id="additionalNotes" 
              v-model="formData.additionalNotes" 
              class="w-full border border-gray-300 rounded px-2 py-1" 
              placeholder="Ingrese cualquier nota adicional"
            ></textarea>
          </div>

          <!-- Fecha de Devolución -->
          <div>
            <label for="returnDate" class="block text-lg font-semibold">Fecha de Devolución:</label>
            <input 
              id="returnDate" 
              v-model="formData.returnDate" 
              type="date" 
              class="w-full border border-gray-300 rounded px-2 py-1"
              required
            />
          </div>

          <!-- Botones -->
          <div class="flex justify-between">
            <button 
              type="submit" 
              class="bg-red-500 text-white px-4 py-2 rounded font-bold hover:bg-red-600"
            >
              Registrar Devolución
            </button>
            <button 
              type="button" 
              @click="resetForm" 
              class="bg-gray-300 px-4 py-2 rounded font-bold hover:bg-gray-400"
            >
              Limpiar Formulario
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'VehicleReturn',
  data() {
    return {
      formData: {
        idReserva: '',
        finalMileage: null,
        fuelLevel: null,
        generalCondition: '',
        hasDamage: false,
        damageDescription: '',
        additionalNotes: '',
        returnDate: new Date().toISOString().slice(0, 10) // Fecha actual formateada solo con
      }
    };
  },
  methods: {
    handleSubmit() {
      // Procesar datos del formulario de devolución
      console.log('Datos de devolución:', this.formData);
      this.$emit('vehicle-returned', this.formData);
      alert('Devolución registrada con éxito.');
    },
    resetForm() {
      // Reiniciar formulario
      this.formData = {
        plateNumber: '',
        finalMileage: null,
        fuelLevel: null,
        generalCondition: '',
        hasDamage: false,
        damageDescription: '',
        additionalNotes: '',
        returnDate: new Date().toISOString().slice(0, 10)
      };
    }
  }
};
</script>

<style scoped>
.header {
  width: 100%;
  height: 80px;
  background-color: #ff0000;
  padding: 1rem 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-sizing: border-box;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1000;
}

/* Estilo de logo */
.logo-container {
  display: flex;
  align-items: center;
  gap: 1rem;
  
}

/* Descripcón del logo */
.logo {
  height: 40px;
  width: auto;
}

/* Descripción del nombre de la empresa */
.brand-name {
  color: white;
  font-size: 1.5rem;
  font-weight: bold;
}

/* Estilo de Botón de encabezado */
.header-buttons {
  display: flex;
  gap: 1rem;
}

/* Descripción Botón de encabezado */
.header-button {
  background-color: rgb(238, 238, 238);
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  font-weight: 500;
}


.container {
  height: 100vh;
  width: 100vw;
  display: flex;
  flex-direction: column;
  background-color: #000000;
  margin: 0;
  padding: 0;
  color: #000000;
}

.main-content {
  flex: 1;
  padding: 2rem;
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
}

.return-form {
  margin: 5rem;
  background-color: rgb(255, 253, 253);
  padding: 4rem;
  border-radius: 12px;
  margin-bottom: 3rem;
}

.form-group {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  color: #000000; 
}

h2 {
  color: #bb3636;
}

button {
  background-color: rgb(196, 47, 47);
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  font-weight: 500;
  color: #ffffff;
  
}

button:hover {
  cursor: pointer;
}
</style>
