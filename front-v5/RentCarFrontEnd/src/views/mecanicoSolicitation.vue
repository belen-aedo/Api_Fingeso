<template>
    <div class="vehicle-list">
      <!-- Header -->
      <header class="header">
        <div class="logo-container">
          <img src="./media/icono2.png" alt="Rent-A-Wheel" class="logo">
          <span class="brand-name">Rent-A-Wheel</span>
        </div>
      </header>
      
        <h2>Lista de Vehículos</h2>
          <div 
            class="vehicle-card" 
            v-for="vehicle in vehicles" 
            :key="vehicle.id"
          >
            <img class="image" :src="`./src/views/media/${vehicle.image}`" :alt="vehicles.name">
            <h3>{{ vehicle.name }}</h3>
            <p>{{ vehicle.description }}</p>
            <p><strong>estado:</strong> {{ vehicle.estado }}</p>
            <button class="select-button">Seleccionar</button>
          </div>
      </div>
    </template>
    
    <script>
    export default {
      data() {
        return {
          vehicles: [
            {
              id: 2,
              name: 'Sedan Compacto',
              description: 'Perfecto para viajes largos en familia, 7 pasajeros, 4 puertas, bencina, mecánico, ',
              estado: "Reparación",
              image: "./sedan_compacto.png",
            },
            {
              id: 3,
              name: 'Jeep Recon',
              description : 'Un vehículo ideal para los viajes montañosos, 5 pasajeros, 4 puertas, bencina, mecánico',
              estado: "Mantenimiento",
              image: "./jeep_recon.png",
            },
            {
              id: 4,
              name: 'SUV de Lujo',
              description: 'Un vehículo ideal para viajes largos, 5 pasajeros, 4 puertas, bencina, mecánico',
              estado: "Mantenimiento",
              image: "./suvLujo.png",
            },
            {
              id: 5,
              name: 'Jeep Gladiator',
              description: 'Un vehículo ideal para los amantes de la velocidad, 5 pasajeros, 4 puertas, bencina, mecánico',
              estado: "Mantenimiento",
              image: "./jeep_gladiator.png",
            },
            {
              id: 6,
              name: 'Cross Over',
              description: 'Un vehículo ideal para los viajes montañosos, 5 pasajeros, 4 puertas, bencina, mecánico',
              estado: "Reparación",
              image: "./crossover.png",
            },
          ],
          selectedDate: null
        };
      },
      computed: {
        
      }
    };
    </script>
    
    <style scoped>
    html, body {
    margin: 0;
    padding: 0;
    height: 100%;
    width: 100%;
  }
  
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
    
    .image {
      height: 150px;
      border: none;
      border-radius: 30px;
    }
    
    h2 {
      width: 100%;
      text-align: center;
      font-size: 24px;
      margin-bottom: 20px;
    }
    
    h3 {
      font-size: 20px;
      margin: 10px 0;
    }
    
    p {
      font-size: 14px;
      color: #555;
    }
    
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
    