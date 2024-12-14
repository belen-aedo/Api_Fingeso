<template>
  <div id="app">
    <header>
      <div class="header-content">
        <div class="logo-section" @click="navigateTo('/principal')">
          <img src="../components/images/logoOficial.jpeg" alt="Nombre de la página" height="80">
          <h1 class="company-name">Rent-A-Wheel</h1>
        </div>
        <nav>
          <ul>
            <li v-if="!isAuthenticated"><button class="custom-button" @click="navigateTo('/registro')">Regístrate</button></li>
            <li v-if="!isAuthenticated"><button class="custom-button" @click="navigateTo('/login')">Ingresa</button></li>
            <li><button class="custom-button" @click="navigateTo('/soporte')">Ayuda</button></li>
          </ul>
        </nav>
      </div>
    </header>

    <div class="rental-filters">
      <h2>Detalles del arriendo</h2>
      <div class="form-horizontal">
        <div class="form-group">
          <label>Lugar de Retiro</label>
          <select>
            <option value="" disabled selected>Seleccione una Ciudad</option>
            <option>Región de Arica y Parinacota </option>
            <option>Región de Tarapacá</option>
            <option>Región de Antofagasta</option>
            <option>Región de Atacama</option>
            <option>Región de Coquimbo</option>
            <option>Región de Valparaíso</option>
            <option>Región Metropolitana</option>
            <option>Región de O'Higgins</option>
          </select>
        </div>
        <div class="form-group">
          <label>Lugar de devolución</label>
          <select>
            <option value="" disabled selected>Seleccione una Ciudad</option>
            <option>Región de Arica y Parinacota </option>
            <option>Región de Tarapacá</option>
            <option>Región de Antofagasta</option>
            <option>Región de Atacama</option>
            <option>Región de Coquimbo</option>
            <option>Región de Valparaíso</option>
            <option>Región Metropolitana</option>
            <option>Región de O'Higgins</option>
          </select>
        </div>

        <div class="form-group">
          <label>Sucursal de Retiro</label>
          <select>
            <option value="" disabled selected>Seleccione una Sucursal</option>
            <option>Sucursal 1</option>
            <option>Sucursal 2</option>
            <option>Sucursal 3</option>
            <option>Sucursal 4</option>
            <option>Sucursal 5</option>
            <option>Sucursal 6</option>
            <option>Sucursal 7</option>
          </select>
        </div>
        <div class="form-group">
          <label>Sucursal de devolución</label>
          <select>
            <option value="" disabled selected>Seleccione una Sucursal</option>
            <option>Sucursal 1</option>
            <option>Sucursal 2</option>
            <option>Sucursal 3</option>
            <option>Sucursal 4</option>
            <option>Sucursal 5</option>
            <option>Sucursal 6</option>
            <option>Sucursal 7</option>
          </select>
        </div>

        <div class="filter-group">
          <label for="fecha-retiro">Fecha y hora de retiro:</label>
          <input id="fecha-retiro" v-model="rentalDetails.pickupDate" type="datetime-local">
        </div>

        <div class="filter-group">
          <label for="fecha-devolucion">Fecha y hora de devolución:</label>
          <input id="fecha-devolucion" v-model="rentalDetails.returnDate" type="datetime-local">
        </div>

        <button @click="searchVehicles" class="search-button">Buscar Vehículos</button>
      </div>
    </div>

    <div class="vehicle-list">
      <h2>Vehículos disponibles</h2>
      <div class="vehicles">
        <div class="vehicle-card" v-for="vehicle in vehicles" :key="vehicle.id">
          <img :src="vehicle.image" :alt="vehicle.name" class="vehicle-image">
          <h3>{{ vehicle.name }}</h3>
          <p>{{ vehicle.description }}</p>
          <p><strong>Precio:</strong> {{ vehicle.price }} CLP</p>
          <button class="select-button" @click="selectVehicle(vehicle)">Seleccionar</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import sedan from '../components/images/sedan_compacto.png'
import sub_familiar from '../components/images/sub_familiar.png'
import pickupRugged from '../components/images/pickupRugged.jpeg'
import jeep_gladiator from '../components/images/jeep_gladiator.png'
import pickup_baja from '../components/images/pickupBaja.png'
import jeep_recon from '../components/images/jeep_recon.png'
import todo_terreno from '../components/images/todoTerreno.png'
import croosover from '../components/images/crossover.png'
import camioneta_off_road from '../components/images/camioneta_offroad.jpeg'


export default {
  name: 'RentAWheel',
  setup() {
    const router = useRouter();

    const rentalDetails = ref({
      city: '',
      pickupDate: '',
      returnDate: ''
    });

    const vehicles = ref([
      {
        id: 1,
        name: 'Sedán Compacto',
        description: 'Un vehículo compacto ideal para la ciudad.',
        price: 50000,
        image: sedan
      },
      {
        id: 2,
        name: 'SUV Familiar',
        description: 'Perfecto para viajes largos en familia.',
        price: 80000,
        image: sub_familiar
      },
      {
        id: 3,
        name: 'Camioneta',
        description: 'Ideal para carga y terrenos difíciles.',
        price: 100000,
        image: pickupRugged
            },
      {
        id: 4,
        name: 'Jeep Gladiator',
        description: 'Ideal para carga y terrenos difíciles.', 
        price: 50000,
        image: jeep_gladiator
   },
      {
                id: 5,
                name: 'Pickup Baja',
                description: 'Perfecto para viajes largos en familia.',
                price: 80000,
                image: pickup_baja
            },
            {
                id: 6,
                name: 'Jeep Recon',
                description: 'Ideal para carga y terrenos difíciles.',
                price: 100000,
                image: jeep_recon
            },
            {
                id: 7,
                name: 'Todo Terreno',
                description: 'Ideal para carga y terrenos difíciles.',
                price: 100000,
                image: todo_terreno
            },
            {
                id: 8,
                name: 'Croosover',
                description: 'Ideal para carga y terrenos difíciles.',
                price: 100000,
                image: croosover
            },
            {
                id: 9,
                name: 'Camioneta off-road',
                description: 'Ideal para carga y terrenos difíciles.',
                price: 100000,
                image: camioneta_off_road
            }
            
    ]);

    const navigateTo = (path) => {
      router.push(path);
    };

    const searchVehicles = () => {
      console.log('Buscando vehículos con detalles:', rentalDetails.value);
    };

    const selectVehicle = (vehicle) => {
      console.log('Vehículo seleccionado:', vehicle);
    };

    return {
      rentalDetails,
      vehicles,
      searchVehicles,
      selectVehicle,
      navigateTo
    };
  }
};
</script>

<style scoped>
#app {
  background-color: #000000;
  min-height: 100vh;
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 5px 15px;
  background-color: #ff0000;
  color: white;
}

.logo-section {
  display: flex;
  align-items: center;
  gap: 15px;
}

.company-name {
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  color: white;
}

nav ul {
  display: flex;
  list-style: none;
  gap: 10px;
  padding: 0;
  margin: 0;
}

.custom-button {
  background-color: #ffffff;
  color: rgb(0, 0, 0);
  padding: 10px 15px;
  border: none;
  border-radius: 5px;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.custom-button:hover {
  background-color: #ffffff;
  transform: scale(1.05);
}

.rental-filters {
  max-width: 1200px;
  margin: 20px auto;
  background-color: #fff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(101, 240, 203, 0.1);
}

.form-horizontal {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  gap: 20px;
}

.filter-group,
.form-group {
  flex: 1 1 calc(25% - 20px);
  display: flex;
  flex-direction: column;
  margin-bottom: 15px;
}

.filter-group label,
.form-group label {
  font-weight: bold;
  margin-bottom: 5px;
}

.filter-group input,
.form-group select {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.search-button {
  flex: 1 1 100%;
  background-color: #ff0000;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.search-button:hover {
  background-color: #cc0000;
}

.vehicle-list {
  max-width: 1200px;
  margin: 20px auto;
  padding: 20px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.vehicles {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  gap: 20px;
}

.vehicle-card {
  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 10px;
  padding: 15px;
  width: 300px;
  text-align: center;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.vehicle-image {
  max-width: 100%;
  height: auto;
  border-radius: 10px;
}

.select-button {
  background-color: #ff0000;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.select-button:hover {
  background-color: #cc0000;
}
</style>
