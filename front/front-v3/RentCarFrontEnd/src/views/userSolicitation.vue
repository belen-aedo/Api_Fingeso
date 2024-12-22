<template>
  <div class="page-container">
    <!-- Header -->
    <header class="header"> 
      <div class="logo-container">
        <img src="./media/icono2.png" alt="Rent-A-Wheel" class="logo">
        <span class="brand-name">Rent-A-Wheel</span>
      </div>
      <div class="header-buttons"> <!-- Botones de encabezado -->
        <router-link to="/devolucion" class="header-button">Devolución de vehiculo</router-link>  <!-- Botón de devolución de vehículo -->
      </div>
    </header>

    <!-- Contenido principal -->
    <div class="main-content">
      <div class="rental-form">
        <h2>Detalles del arriendo</h2> <!-- Formulario de arriendo -->
        <div class="form-row">
          
          <div class="form-group">
            <label>Sucursal de Retiro</label> <!-- Seleccionar sucursal de retiro -->
            <select v-model="rentalDetails.pickupBranch">
              <option value="" disabled selected>Seleccione una Sucursal</option> <!-- Opciones de sucursales -->
              <option>Sucursal 1</option>
              <option>Sucursal 2</option>
              <option>Sucursal 3</option>
              <option>Sucursal 4</option>
              <option>Sucursal 5</option>
            </select>
          </div>

          <div class="form-group">
            <label>Sucursal de devolución</label>   <!-- Seleccionar sucursal de devolución -->
            <select v-model="rentalDetails.returnBranch">
              <option value="" disabled selected>Seleccione una Sucursal</option> <!-- Opciones de sucursales -->
              <option>Sucursal 1</option>
              <option>Sucursal 2</option>
              <option>Sucursal 3</option>
              <option>Sucursal 4</option>
              <option>Sucursal 5</option>
            </select>
          </div>
        </div>

        <div class="form-row">
          <div class="form-group date-group"> <!-- Seleccionar fecha y hora de retiro -->
            <label>Fecha de retiro:</label>
            <input type="date" v-model="rentalDetails.pickupDate" />
          </div>
          
          <div class="form-group date-group"> <!-- Seleccionar fecha y hora de devolución -->
            <label>Fecha de devolución:</label>
            <input type="date" v-model="rentalDetails.returnDate" />
            </div>
        </div>

        <button @click="searchVehicles" class="search-button">Buscar Vehículos</button> <!-- Botón de búsqueda de vehículos -->
      </div>
  
      <!-- Secciones de carros -->
      <div class="car-brands">
        <h3>Marcas Disponibles</h3> <!-- Marcas disponibles -->
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
      },
      idCliente: null // Variable para almacenar el ID del cliente
    };
  },
  created() {
    // Obtén el ID del cliente de los parámetros de la URL
    this.idCliente = this.$route.query.id; 
    console.log('ID del cliente:', this.idCliente); // Solo para verificar
  },
  methods: {
    async searchVehicles() {
      try {
        // Redirige a la página de vehículos con el ID del cliente
        window.location.href = `/vehiculos?id=${this.idCliente}`; 
      } catch (error) {
        console.error(error);
      }
    },
    goToPrincipal() {
      this.$router.push('/principal'); // Redirige a la página principal
    }
  }
};
</script>






<style scoped>

/*Cuerpo de la página*/
html, body {
  margin: 0;
  padding: 0;
  height: 100%;
  width: 100%;
}

/* Descripción del contenedor de la página*/
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

/* Descripción del encabezado */
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

/* Estilo de contenido principal */
.main-content {
  flex: 1;
  padding: 2rem;
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
  
}

/* Descripción del espacio de renta */
.rental-form {
  margin: 5rem;
  background-color: rgb(255, 253, 253);
  padding: 4rem;
  border-radius: 12px;
  margin-bottom: 3rem;
  
}

/* Estilo de la fila */
.form-row {
  display: flex;
  gap: 1rem;
  margin-bottom: 1rem;
  
}

/* Estilo del grupo de vehículos */
.form-group {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  color: #000000;

  

}

/* Estilo de grupo */
.date-group {
  flex: 1;
  
}

/* Estilo de la firma */
label {
  font-weight: 500;
  color: #000000;
}

/* Estilo de input fecha */
select,
input[type="datetime-local"] {
  padding: 0.5rem;
  border: 1px solid #000000;
  border-radius: 4px;
  width: 100%;
}

/* Descripción de botón de búsqueda */
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

/* Sección de marca de carros */
.car-brands {
  background-color: rgb(255, 255, 255);/**/
  padding: 2rem;
  border-radius: 8px;
  color: #000000;

}

/* Marcas de carro */
.car-brands h3 {
  color: #000000;
  margin-bottom: 1.5rem;
  text-align: center;
}

/* Estilo de la cuadrícula de marcas */
.brands-grid {
  display: grid;
  grid-template-columns: repeat(6, 1fr);
  gap: 2rem;
  justify-items: center;
}

/* Estilo de la tarjeta de marca */
.brand-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.5rem;
}

/* Estilo del logo de la marca */
.brand-logo {
  width: 80px;
  height: 80px;
  object-fit: contain;
}


/* Diseño responsivo */
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

