<template>
    <div class="vehicle-return container mx-auto p-4">
        <header class="header">
      <div class="logo-container">
        <img src="./media/icono2.png" alt="Rent-A-Wheel" class="logo">
        <span class="brand-name">Rent-A-Wheel</span>
        <router-link to="/Cliente" class="header-button">Pagina anterior</router-link>
      </div>
    
    </header>
  </div>
  </template>
  
  <script>
  export default {
    name: 'VehicleReturn',
    data() {
      return {
        formData: {
          plateNumber: '',
          finalMileage: null,
          fuelLevel: null,
          generalCondition: '',
          hasDamage: false,
          damageDescription: '',
          additionalNotes: '',
          returnDate: new Date().toISOString()
        }
      }
    },
    methods: {
      handleSubmit() {
        // Aquí iría la lógica para procesar la devolución
        console.log('Datos de devolución:', this.formData);
        // Ejemplo de emisión de evento
        this.$emit('vehicle-returned', this.formData);
      },
      resetForm() {
        this.formData = {
          plateNumber: '',
          finalMileage: null,
          fuelLevel: null,
          generalCondition: '',
          hasDamage: false,
          damageDescription: '',
          additionalNotes: '',
          returnDate: new Date().toISOString()
        }
      }
    }
}
 
  </script>
  <style scoped>
  .page-container {
  height: 100vh; /* Toda la altura de la pantalla */
  width: 100vw;  /* Todo el ancho de la pantalla */
  display: flex;
  flex-direction: column;
  background-color: #eae2e2;
  margin: 0;
  padding: 0;
  color: #bb3636;

}


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
.logo-container {
  display: flex;
  align-items: center;
  gap: 1rem;
  
}

.logo {
  height: 40px;
  width: auto;
}
.brand-name {
  color: white;
  font-size: 1.5rem;
  font-weight: bold;
}

.header-buttons {
  display: flex;
  gap: 1rem;
}

    
    </style>