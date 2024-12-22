<template>
  <div class="vehicle-list">
    <!-- Header -->
    <header class="header">
      <div class="logo-container">
        <img src="./media/icono2.png" alt="Rent-A-Wheel" class="logo" />
        <span class="brand-name">Rent-A-Wheel</span>
        <router-link to="/Cliente" class="header-button">Pagina anterior</router-link>
      </div>
    </header>

    <h2>Vehículos Disponibles</h2>
    <div v-if="filteredVehicles.length > 0">
      <div 
        class="vehicle-card"  
        v-for="vehicle in filteredVehicles" 
        :key="vehicle.id"
      >
        <!-- Tarjeta de vehículo -->
        <img 
          class="image" 
          :src="`./src/views/media/${vehicle.image}`" 
          :alt="vehicle.name" 
        />
        <h3>{{ vehicle.name }}</h3>
        <p>{{ vehicle.description }}</p>
        <p><strong>Precio:</strong> {{ vehicle.price }} CLP</p>
        <!-- Botón que llama al método con el ID del vehículo -->
        <button class="select-button" @click="goToVehiclePage(vehicle.id)">
          Seleccionar
        </button>
      </div>
    </div>
    <div v-else>
      <!-- Mensaje de no hay vehículos disponibles -->
      <p>No hay vehículos disponibles para la fecha seleccionada.</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      vehicles: [
        {
          id: 1,
          name: 'Camioneta 4x4',
          description: 'Un vehículo ideal para los viajes montañosos, 5 pasajeros, 4 puertas, bencina, mecánico',
          price: 35000,
          image: './camioneta_4x4.png',
        },
        {
          id: 2,
          name: 'SUV Familiar',
          description: 'Perfecto para viajes largos en familia, 7 pasajeros, 4 puertas, bencina, mecánico',
          price: 80000,
          image: './sub_familiar.png',
        },
        {
          id: 3,
          name: 'Camion todo terreno',
          description: 'Un vehículo ideal para los viajes montañosos, 5 pasajeros, 4 puertas, bencina, mecánico',
          price: 100000,
          image: './camion_todo_terreno.png',
        },
      ],
    };
  },
  computed: {
    filteredVehicles() {
      return this.vehicles;
    },
  },
  methods: {
    goToVehiclePage(vehicleId) {
      // Redirige a la ruta dinámica con el ID del vehículo
      this.$router.push({ name: 'VehicleDetail', params: { id: vehicleId } });
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
.header-buttons {
  display: flex;
  gap: 1rem;
}
/* Estilos de los botones del header */
.header-button {
  background-color: rgb(238, 238, 238);
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  font-weight: 500;
}

/* Estilos de la pagina */
  .page-container {
  height: 100vh; /* Toda la altura de la pantalla */
  width: 100vw;  /* Todo el ancho de la pantalla */
  display: flex;
  flex-direction: column;
  background-color: #000000;
  margin: 0;
  padding: 0;
  color: #000000;

}
/* Estilos de la lista de vehiculos */
  .vehicle-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;
    padding: 20px;
    background-color: #f5f5f5;
    width: 300%;  
        color: #000000;
  
  }
  
  /* Estilos de la tarjeta de vehiculo */
  .vehicle-card {
    background-color: white;
    border: 1px solid #ddd;
    border-radius: 10px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    width: 600px;
    padding: 15px;
    text-align: center;
    color: #000000;
    flex-direction: column; /* Contenido en columna */
  }
  
  /* Estilos de la imagen */
  .image {
    height: 150px;
    border: none;
    border-radius: 30px;
  }
  
  /* Estilos de los titulos */
  h2 {
    width: 100%;
    text-align: center;
    font-size: 24px;
    margin-bottom: 20px;
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
  
  /* Estilos de los botones para seleccionar*/
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
  