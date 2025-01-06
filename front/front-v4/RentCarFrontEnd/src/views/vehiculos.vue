<template>
  <div class="vehicle-list">
    <!-- Header -->
    <header class="header">
      <div class="logo-container">
        <img src="./media/icono2.png" alt="Rent-A-Wheel" class="logo" />
        <span class="brand-name">Rent-A-Wheel</span>
        <router-link to="/Cliente" class="header-button">Página anterior</router-link>
      </div>
    </header>

    <h2>Vehículos Disponibles</h2>

    <!-- Mostrar vehículos filtrados -->
    <div v-if="vehicles.length > 0" class="vehicles-container">
      <div 
        class="vehicle-card" 
        v-for="vehicle in vehicles" 
        :key="vehicle.idVehiculoReferencia"
      >
        <img 
          class="image" 
          :src="vehicle.url" 
          :alt="vehicle.modelo" 
        />
        <h3>{{ vehicle.modelo }}</h3>
        <p><strong>Descripción:</strong> {{ vehicle.descripcionPublicacion }}</p>
        <p><strong>Transmisión:</strong> {{ vehicle.mecanico_automatico }}</p>
        <p><strong>Combustible:</strong> {{ vehicle.tipoCombustible }}</p>
        <p><strong>Puertas:</strong> {{ vehicle.cantidadPuertas }}</p>
        <p><strong>Capacidad de pasajeros:</strong> {{ vehicle.capacidadPasajeros }}</p>
        <p><strong>Costo de arriendo:</strong> {{ vehicle.costoArriendoVehiculo }} CLP</p>
        <p><strong>Costo de reserva:</strong> {{ vehicle.costoReservaVehiculo }} CLP</p>
        <button class="select-button" @click="selectVehicle(vehicle.idVehiculoReferencia)">
          Seleccionar
        </button>
      </div>
    </div>

    <!-- Mensaje si no hay vehículos -->
    <div v-else>
      <p>No hay vehículos disponibles para las fechas seleccionadas.</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'VehiclesPage',
  data() {
    return {
      vehicles: [], // Almacena los vehículos obtenidos de la query
      email: null,  // Almacena el email del cliente
      rentalData: null,  // Almacena los datos de la renta
    };
  },
  mounted() {
    // Recuperar datos de la query
    const query = this.$route.query;

    // Parsear y asignar datos
    this.email = query.email || null;
    this.rentalData = query.rentalData ? JSON.parse(query.rentalData) : null;
    this.vehicles = query.data ? JSON.parse(query.data) : [];
    
    
    console.log('Datos de la renta:', this.rentalData);
    console.log('Email del cliente:', this.email);
    console.log('Vehículos disponibles:', this.vehicles);
  },
  methods: {
    async selectVehicle(vehicleId) {
  // Encuentra el vehículo seleccionado basado en su ID
  const selectedVehicle = this.vehicles.find(vehicle => vehicle.idVehiculoReferencia === vehicleId);
  
  if (selectedVehicle) {
      console.log('Vehículo seleccionado:', selectedVehicle);
      
      // Redirige a la página de confirmación con los datos del vehículo
      this.$router.push({
        name: 'Confirmacion', // Nombre de la ruta de la página de confirmación
        query: {
          email: this.email, // Pasar el email
          rentalData: JSON.stringify(this.rentalData),
          vehicle: JSON.stringify(selectedVehicle), // Serializar los datos del vehículo
        },
      });
    } else {
      console.error('Vehículo no encontrado');
    }
  },

    getVehicleImageURL(model) {
      // Normalizar el modelo para coincidir con el nombre del archivo
      const normalizedModel = model.toLowerCase().replace(/ /g, '-'); // Ejemplo: "Toyota Corolla" -> "toyota-corolla"
      const imagePath = `./media/${normalizedModel}.jpg`;

      // Verificar si existe la imagen usando import dinámico (Webpack/Vite manejará los errores)
      try {
        return require(`@/views/media/${normalizedModel}.jpg`);
      } catch (e) {
        console.warn(`Imagen no encontrada para el modelo: ${model}. Usando imagen por defecto.`);
        return require('@/views/media/default-car.png'); // Imagen por defecto
      }
    },
  },
};
</script>

<style scoped>
html, body {
  margin: 0;
  padding: 0;
  height: 100%;
  width: 100%;
}

/* Estilos de la pagina */
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

/* Estilos del header */
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
  border-radius: 5px; /* Bordes redondeados */
}

/* Estilos del contenedor del logo */
.logo-container {
  display: flex;
  align-items: center;
  gap: 1rem;
}

/* Estilos del logo */
.logo {
  height: 40px;
  width: auto;
}

/* Estilos del nombre de la marca */
.brand-name {
  color: white;
  font-size: 1.5rem;
  font-weight: bold;
}

/* Estilos de los botones del header */
.header-button {
  background-color: rgb(238, 238, 238 );
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  font-weight: 500;
}

/* Estilos de la lista de vehiculos */
.vehicle-list {
  display: flex;
  flex-direction: column; /* Cambiado a columna para el header y título */
  align-items: center; /* Centrar horizontalmente */
  justify-content: center;
  gap: 20px;
  padding: 20px;
  background-color: #f5f5f5;
   width: 300%;  
        color: #000000;
}

/* Contenedor de vehículos */
.vehicles-container {
  display: flex;
  overflow-x: auto; /* Permitir desplazamiento horizontal */
  padding: 20px;
  gap: 20px;
  justify-content: center; /* Centrar los elementos dentro del contenedor */
}

/* Estilos de la tarjeta de vehiculo */
.vehicle-card {
  background-color: white;
  border: 1px solid #ddd;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  width: 300px; /* Ajustar el ancho de las tarjetas */
  padding: 15px;
  text-align: center;
  color: #000000;
  flex-shrink: 0; /* Evitar que las tarjetas se reduzcan */
}

/* Estilos de la imagen */
.image {
  height: 150px;
  border: none;
  border-radius: 10px; /* Cambiar el radio para un aspecto más moderno */
}

/* Estilos de los titulos */
h2 {
  width: 100%;
  text-align: center;
  font-size: 24px;
  margin: 20px 0; /* Espaciado mejorado */
}

/* Estilos de los titulos */
h3 {
  font-size: 20px;
  margin: 10px 0;
}

/* Estilos de los parrafos */
p {
  font-size: 14px;
  color: #555;
}

/* Estilos de los botones para seleccionar */
.select-button {
  background-color: #ff000d;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.select-button:hover {
  background-color: #000000;
}
</style>