<template>
    <div class="devolucion_vehiculo"> <!-- Clase para el contenedor de la página -->
        <header class="header"> 
      <div class="logo-container">
        <img src="./media/icono2.png" alt="Rent-A-Wheel" class="logo">
        <span class="brand-name">Rent-A-Wheel</span> <!-- Nombre de la empresa -->
        <router-link to="/Cliente" class="header-button">Pagina anterior</router-link> <!-- Botón para volver a la página anterior -->
      </div>
    
    </header>
  </div>
  </template>
  
  <script>
  export default { // Exportación de la vista
    name: 'VehicleReturn', // Nombre de la vista
    data() { // Datos de la vista
      return { // Retorno de los datos
        formData: {
          plateNumber: '',
          finalMileage: null, // Kilometraje final
          fuelLevel: null,
          generalCondition: '',
          hasDamage: false, // Daño en bool
          damageDescription: '',
          additionalNotes: '',
          returnDate: new Date().toISOString() 
        }
      }
    },
    methods: {
      handleSubmit() { // Método para enviar los datos
        // Aquí iría la lógica para procesar la devolución
        console.log('Datos de devolución:', this.formData);
        // Ejemplo de emisión de evento
        this.$emit('vehicle-returned', this.formData);
      },
      resetForm() { // Método para resetear el formulario
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
  /* Estilos para la vista de devolución de vehículo */
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

/* Estilos para el formulario de devolución de vehículo */
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

/* Estilos para el contenedor del logo y nombre de la empresa */
.logo-container {
  display: flex;
  align-items: center;
  gap: 1rem;
  
}

/* Estilos para el logo */
.logo {
  height: 40px;
  width: auto;
}

/* Estilos para el nombre de la empresa */
.brand-name {
  color: white;
  font-size: 1.5rem;
  font-weight: bold;
}

/* Estilos para los botones del header */
.header-buttons {
  display: flex;
  gap: 1rem;
}

    
    </style>