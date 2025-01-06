<template>
  <div class="page-container">
    <!-- Header -->
    <header class="header"> 
      <div class="logo-container">
        <img src="./media/icono2.png" alt="Rent-A-Wheel" class="logo">
        <span class="brand-name">Rent-A-Wheel</span>
      </div>
     <div class="header-buttons">
        <router-link class="header-button">Reservas</router-link> <!-- Botón de reservas -->
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
              <option>Sucursal Central</option>
              <option>Sucursal Costa</option>
              <option>Sucursal 3</option>
              <option>Sucursal 4</option>
              <option>Sucursal 5</option>
            </select>
          </div>

          <div class="form-group">
            <label>Sucursal de devolución</label>   <!-- Seleccionar sucursal de devolución -->
            <select v-model="rentalDetails.returnBranch">
              <option value="" disabled selected>Seleccione una Sucursal</option> <!-- Opciones de sucursales -->
              <option>Sucursal Central</option>
              <option>Sucursal Costa</option>
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
import axios from 'axios'; // Importar axios para realizar solicitudes HTTP

export default { // Exportar el componente para ser utilizado en la aplicación
  name: 'RentAWheel',
  data() {
    return {
      rentalDetails: {
        pickupBranch: '',
        returnBranch: '',
        pickupDate: '',
        returnDate: '',
      },
      email: null, // Variable para almacenar el ID del cliente
    };
  },
  mounted() {
    const params = new URLSearchParams(window.location.search); // Obtener los parámetros de la URL
    this.email = params.get('email');
    
    console.log(this.email); // Usar el email en tu componente
  },
  methods: {
    logRentalDetails() {
    console.log(this.rentalDetails);
  },
    async searchVehicles() {
      try {
        const { pickupBranch, returnBranch, pickupDate, returnDate } = this.rentalDetails;

        // Construir la URL para el GET
        const url = `${import.meta.env.VITE_BASE_URL}api/Agendar/ObtenerVehiculoDisponibles`;

        const params = {
        nombreSucursalR: this.rentalDetails.pickupBranch,
        nombreSucursalD: this.rentalDetails.returnBranch,
        fechaRetiro: this.rentalDetails.pickupDate,
        fechaDevolucion: this.rentalDetails.returnDate,
      };


        // Realizar la solicitud GET
        const respuesta = await axios.get(url, { params });

        console.log('Respuesta del backend:', respuesta.data);


        this.$router.push({ // Redirigir a la página de vehículos
          path: '/vehiculos',
          query: {
            email: this.email,
            rentalData: JSON.stringify(this.rentalDetails),
            data: JSON.stringify(respuesta.data), // Convierte los datos a JSON si es un objeto
          },
        });
      } catch (error) {
        console.error('Error al buscar vehículos:', error);
      }
    },
  },
};
</script>

<style scoped>
/* Cuerpo de la página */
html, body {
  margin: 0;
  padding: 0;
  height: 100%;
  width: 100%;
  font-family: 'Arial', sans-serif; /* Cambiar la fuente */
  color: #333; /* Color de texto más oscuro */
}

/* Descripción del contenedor de la página */
.page-container {
  height: 100vh; 
  width: 100vw;  
  display: flex;
  flex-direction: column;
  background-color: #f4f4f4; /* Color de fondo más claro */
  margin: 0;
  padding: 0;
}

/* Descripción del encabezado */
.header {
  width: 100%;
  height: 80px;
  background-color: #ff0000; /* Color de encabezado más suave */
  padding: 1rem 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Sombra para el encabezado */
  border-radius: 5px; /* Bordes redondeados */
}

/* Estilo de logo */
.logo-container {
  display: flex;
  align-items: center;
  gap: 1rem;
}

/* Descripción del logo */
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
  background-color: #ffffff; /* Botón blanco */
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  font-weight: 500;
  transition: background-color 0.3s; /* Transición suave */
}

.header-button:hover {
  background-color: #e0e0e0; /* Color de fondo al pasar el mouse */
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
  margin: 5rem auto; /* Centrar el formulario */
  background-color: #ffffff; /* Fondo blanco */
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Sombra para el formulario */
  color: black;
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
}

/* Estilo de grupo */
.date-group {
  flex: 1;
}

/* Estilo de la firma */
label {
  font-weight: 500;
  color: #333; /* Color de texto más oscuro */
}

/* Estilo de input fecha */
select,
input[type="date"] {
  padding: 0.5rem;
  border: 1px solid #ccc; /* Borde más suave */
  border-radius: 4px;
  width: 100%;
  transition: border-color 0.3s; /* Transición suave */
}

select:focus,
input[type="date"]:focus {
  border-color: #ff4d4d; /* Color de borde al enfocar */
}

/* Descripción de botón de búsqueda */
.search-button {
  width: 100%;
  padding: 0.75rem;
  background-color: #ff0000; /* Color de botón */
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  cursor: pointer;
  margin-top: 1rem;
  transition: background-color 0.3s; /* Transición suave */
}

.search-button:hover {
  background-color: #e63939; /* Color de fondo al pasar el mouse */
}

/* Sección de marca de carros */
.car-brands {
  background-color: #ffffff; /* Fondo blanco */
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Sombra para la sección de marcas */
}

/* Marcas de carro */
.car-brands h3 {
  color: #000000; /* Color de texto más oscuro */
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
  transition: transform 0.3s; /* Transición suave */
  color: black;
}
/* Estilo de Botón de encabezado */
.header-buttons {
  display: flex;
  gap: 1rem;
}

/* Descripción Botón de encabezado */
.header-button {
  background-color: #ffffff;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  font-weight: 500;
  color: #ff0000; /* Color del texto rojo para que combine con el tema */
  transition: background-color 0.3s, transform 0.2s;
}

.header-button:hover {
  background-color: #e0e0e0;
  transform: translateY(-1px);
}

.brand-card:hover {
  transform: scale(1.05); /* Efecto de aumento al pasar el mouse */
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