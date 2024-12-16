<template>
  <div class="page-container">
    <!-- Header -->
    <header class="header">
      <div class="logo-container">
        <img src="./media/icono2.png" alt="Rent-A-Wheel" class="logo">
        <span class="brand-name">Rent-A-Wheel</span>
      </div>
      <div class="header-buttons">
        <router-link to="/devolucion" class="header-button">Devolución de vehiculo</router-link>
        
      </div>
    </header>

    <!-- Main Content -->
    <div class="main-content">
      <div class="rental-form">
        <h2>Detalles del arriendo</h2>
        <div class="form-row">
          <div class="form-group">
            <label>Lugar de Retiro</label>
            <select>
              <option value="" disabled selected>Seleccione una Ciudad</option>
              <option>Región de Arica y Parinacota</option>
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
              <option>Región de Arica y Parinacota</option>
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
            </select>
          </div>
        </div>

        <div class="form-row">
          <div class="form-group date-group">
            <label>Fecha y hora de retiro:</label>
            <input type="date" />
          </div>
          
          <div class="form-group date-group">
            <label>Fecha y hora de devolución:</label>
            <input type="date" />
            </div>
        </div>

        <button @click="searchVehicles" class="search-button">Buscar Vehículos</button>


      </div>
  
      <!-- Car Brands Section -->
      <div class="car-brands">
        <h3>Marcas Disponibles</h3>
        <div class="brands-grid">
          <div class="brand-card">
           <img src="@/views/media/toyoya-logo.jpeg" alt="Toyota" class="brand-logo" />
            <span>Toyota</span>
          </div>
          <div class="brand-card">
            <img src="./media/honda-logo.jpeg" alt="Honda" class="brand-logo">
            <span>Honda</span>
          </div>
          <div class="brand-card">
            <img src="./media/nisan-logo.jpeg" alt="Nissan" class="brand-logo">
            <span>Nissan</span>
          </div>
          <div class="brand-card">
            <img src="./media/hyundai-logo.jpeg" alt="Hyundai" class="brand-logo">
            <span>Hyundai</span>
          </div>
          <div class="brand-card">
            <img src="./media/kia-logo.jpeg" alt="Kia" class="brand-logo">
            <span>Kia</span>
          </div>
          <div class="brand-card">
            <img src="./media/mazda-logo.jpeg" alt="Mazda" class="brand-logo">
            <span>Mazda</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

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
  background-color: #000000;
  margin: 0;
  padding: 0;
  color: #000000;

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

.header-button {
  background-color: rgb(238, 238, 238);
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  font-weight: 500;
}

.main-content {
  flex: 1;
  padding: 2rem;
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
  
}

.rental-form {
  margin: 5rem;
  background-color: rgb(255, 253, 253);
  padding: 4rem;
  border-radius: 12px;
  margin-bottom: 3rem;
  
}

.form-row {
  display: flex;
  gap: 1rem;
  margin-bottom: 1rem;
  
}

.form-group {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  color: #000000;

  

}

.date-group {
  flex: 1;
  
}

label {
  font-weight: 500;
  color: #000000;
}

select,
input[type="datetime-local"] {
  padding: 0.5rem;
  border: 1px solid #000000;
  border-radius: 4px;
  width: 100%;
}

.search-button {
  width: 100%;
  padding: 0.75rem;
  background-color: #ff0000;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  cursor: pointer;
  margin-top: 1rem;
}

/* Car Brands Section */
.car-brands {
  background-color: rgb(255, 255, 255);/**/
  padding: 2rem;
  border-radius: 8px;
  color: #000000;

}

.car-brands h3 {
  color: #000000;
  margin-bottom: 1.5rem;
  text-align: center;
}

.brands-grid {
  display: grid;
  grid-template-columns: repeat(6, 1fr);
  gap: 2rem;
  justify-items: center;
}

.brand-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.5rem;
}

.brand-logo {
  width: 80px;
  height: 80px;
  object-fit: contain;
}


/* Responsive Design */
@media (max-width: 1024px) {
  .brands-grid {
    grid-template-columns: repeat(3, 1fr);
  }
}

@media (max-width: 768px) {
  .form-row {
    flex-direction: column;
  }
  
  .brands-grid {
    grid-template-columns: repeat(2, 1fr);
  }
  
  .main-content {
    padding: 1rem;
  }
}
</style>

<script>
export default {
  name: 'RentAWheel',
  data() {
    return {
      rentalDetails: {
        pickupBranch: '',
        returnBranch: '',
        pickupDate: '',
        returnDate: ''
      }
    };
  },
  methods: {
    async searchVehicles() {
      try {
        window.location.href = "/vehiculos";
      } catch (error) {
        console.error(error);
      }
    }
    ,
  
    goToPrincipal() {
  this.$router.push('/principal');
}



  }
  
};

</script>