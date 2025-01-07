<template>
  <div class="page-container">
    <!-- Header -->
    <header class="header"> 
      <div class="logo-container">
        <img :src="logo" alt="Rent-A-Wheel" class="logo">
        <span class="brand-name">Rent-A-Wheel</span>
      </div>
      
    </header>

    <!-- Main content -->
    <div class="main-content">
      <!-- Error message if exists -->
      <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>

      <div v-else class="details-container">
        <!-- Rental data -->
        <div v-if="rentalData" class="info-section">
          <h2>Información de la Reserva</h2>
          <div class="info-grid">
            <div class="info-item">
              <strong>Sucursal de Retiro:</strong>
              <span>{{ rentalData.pickupBranch }}</span>
            </div>
            <div class="info-item">
              <strong>Fecha de Retiro:</strong>
              <span>{{ rentalData.pickupDate }}</span>
            </div>
            <div class="info-item">
              <strong>Sucursal de Devolución:</strong>
              <span>{{ rentalData.returnBranch }}</span>
            </div>
            <div class="info-item">
              <strong>Fecha de Devolución:</strong>
              <span>{{ rentalData.returnDate }}</span>
            </div>
          </div>
        </div>

        <!-- Vehicle data -->
        <div v-if="vehicle" class="info-section vehicle-section">
          <h2>Detalles del Vehículo</h2>
          <div class="vehicle-content">
            <div class="vehicle-info">
              <div class="info-grid">
                <div class="info-item">
                  <strong>Modelo:</strong> <!-- Modelo del vehículo -->
                  <span>{{ vehicle.modelo }}</span>
                </div>
                <div class="info-item">
                  <strong>Transmisión:</strong> <!-- Tipo de transmisión -->
                  <span>{{ vehicle.mecanico_automatico }}</span>
                </div>
                <div class="info-item"> 
                  <strong>Combustible:</strong> <!-- Tipo de combustible -->
                  <span>{{ vehicle.tipoCombustible }}</span>
                </div>
                <div class="info-item">
                  <strong>Capacidad:</strong> <!-- Capacidad de pasajeros --> 
                  <span>{{ vehicle.capacidadPasajeros }} pasajeros</span>
                </div>
                <div class="info-item">
                  <strong>Cantidad de Puertas:</strong> <!--Cantida de puertas del vehiculo-->
                  <span>{{ vehicle.cantidadPuertas }}</span>
                </div>
                <div class="info-item">
                  <strong>Costo por Arriendo:</strong> <!--Costo del final Arriendo-->
                  <span>{{ vehicleSelected?.costoArriendoVehiculo }} CLP</span>
                </div>
                <div class="info-item">
                  <strong>Costo de Reserva:</strong> <!-- Costo de la Reserva-->
                  <span>{{ vehicleSelected?.costoReservaVehiculo }} CLP</span>
                </div>
              </div>
              <div class="vehicle-description">
                <strong>Descripción:</strong> <!-- Descripción del vehículo -->
                <p>{{ vehicle.descripcionPublicacion }}</p>
              </div>
            </div>
            <div class="vehicle-image-container">
              <img :src="vehicle.url" alt="Imagen del vehículo" class="vehicle-image" /> <!-- Imagen del vehículo -->
            </div>
          </div>

          <div>
            <button @click="confirmationReserva" class="confirmation-button"> <!-- Botón de confirmación de reserva -->
              Confirmar Reserva
            </button>
          </div>

          <!-- Client email -->
          <div v-if="email" class="info-section">
            <h2>Información del Cliente</h2>
            <div class="info-item">
              <strong>Email:</strong>
              <span>{{ email }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'; // Importar axios para realizar solicitudes HTTP

export default {
  name: 'VehiclesPage', // Nombre del componente
  data() {
    return {
      vehicles: [], // Vehículos disponibles
      email: null, // Email del cliente
      rentalData: null, // Datos de la renta
      vehicleSelected: null, // Vehículo seleccionado
      patente: null, // Patente del vehículo seleccionado
      reserva: null, // Reserva realizada
    };
  },
  mounted() {
    // Recuperar datos de la query
    const query = this.$route.query;

    // Parsear y asignar datos
    this.email = query.email || null;
    this.rentalData = query.rentalData ? JSON.parse(query.rentalData) : null;
    this.vehicle = query.vehicle ? JSON.parse(query.vehicle) : null;
    
    // Mostrar datos en consola
    console.log('Datos de la renta:', this.rentalData); 
    console.log('Email del cliente:', this.email);
    console.log('Vehículo seleccionado:', this.vehicle);
    this.obtenerDetallesReserva();
    console.log('Reserva:', this.vehicleSelected);
  },
  methods: {
    async enviarDetallesVehiculo() { // Método para enviar los detalles del vehículo al backend
  if (!this.vehicle || !this.rentalData.pickupDate || !this.rentalData.returnDate) {
    console.error('Faltan datos para enviar al backend.');
    alert('Por favor, complete todos los datos antes de continuar.');
    return;
  }

  try {
    const url = `${import.meta.env.VITE_BASE_URL}api/Agendar/VerDetallesVehiculo`; // URL del backend para enviar los detalles del vehículo

    // Verificar estructura de las fechas
    const fechaRetiro = new Date(this.rentalData.pickupDate).toISOString().split('T')[0];
    const fechaDevolucion = new Date(this.rentalData.returnDate).toISOString().split('T')[0];

    const response = await axios.post(url, this.vehicle, { // Enviar los detalles del vehículo
      params: {
        fechaRetiro,
        fechaDevolucion,
      },
      headers: {
        'Content-Type': 'application/json', // Asegurarse de que el backend lo acepte
      },
    });

    console.log('Respuesta del backend:', response.data); // Mostrar respuesta del backend
    this.vehicleSelected = response.data;
    
  } catch (error) {
    console.error('Error al enviar los detalles del vehículo:', error); // Mostrar error en consola

    // Mejor manejo de errores
    if (error.response) {
      console.error('Respuesta del servidor:', error.response.data);
      alert(`Error del servidor: ${error.response.data}`);
    } else if (error.request) {
      console.error('No hubo respuesta del servidor:', error.request);
      alert('No se recibió respuesta del servidor.');
    } else {
      console.error('Error al configurar la solicitud:', error.message);
      alert('Hubo un error al procesar los detalles del vehículo.');
    }
  }
},

async seleccionarVehiculo() {   // Método para seleccionar un vehículo
    await this.enviarDetallesVehiculo(); // Enviar los detalles del vehículo antes de seleccionar uno nuevo (si es necesario) 

  try {
    const url = `${import.meta.env.VITE_BASE_URL}api/Agendar/SelecionarVehiculoReferencia`;

    // Preparar datos para el request
    const params = {
      nombreSucursalR: this.rentalData.pickupBranch,
      fechaRetiro: new Date(this.rentalData.pickupDate).toISOString().split('T')[0],
      fechaDevolucion: new Date(this.rentalData.returnDate).toISOString().split('T')[0],
      nombreSucursalD: this.rentalData.returnBranch,
    };

    const response = await axios.post(url, this.vehicleSelected, { params }); // Seleccionar el vehículo

    console.log('Vehículo seleccionado (patente):', response.data); // Mostrar la patente del vehículo seleccionado
    this.patente = response.data;

    // Manejar la respuesta (por ejemplo, guardar la patente o redirigir)
    
  } catch (error) {
    console.error('Error al seleccionar el vehículo:', error);

    // Mejor manejo de errores
    if (error.response) {
      console.error('Respuesta del servidor:', error.response.data);
      alert(`Error del servidor: ${error.response.data}`);
    } else if (error.request) {
      console.error('No hubo respuesta del servidor:', error.request);
      alert('No se recibió respuesta del servidor.');
    } else {
      console.error('Error al configurar la solicitud:', error.message);
      alert('Hubo un error al procesar la selección del vehículo.');
    }
  }
},
async obtenerDetallesReserva() { // Método para obtener los detalles de la reserva
    await this.seleccionarVehiculo();
    try {
    const url = `${import.meta.env.VITE_BASE_URL}api/Agendar/DetallesReserva`;

    // Preparar datos para el request
    const params = {
      nombreSucursalR: this.rentalData.pickupBranch,
      fechaRetiro: new Date(this.rentalData.pickupDate).toISOString().split('T')[0],
      fechaDevolucion: new Date(this.rentalData.returnDate).toISOString().split('T')[0],
      nombreSucursalD: this.rentalData.returnBranch,
    };

    const response = await axios.post(url, this.vehicleSelected, { params });

    console.log('Detalles de reserva:', response.data);
    this.reserva = response.data;

    // Manejar la respuesta (por ejemplo, guardar la patente o redirigir)
    
  } catch (error) {
    console.error('Error en detalles de reserva:', error);
  }
},
async confirmationReserva(){ // Método para confirmar la reserva del vehículo seleccionado por el cliente 
    await this.seleccionarVehiculo();
    try {
    const url = `${import.meta.env.VITE_BASE_URL}api/Agendar/ConfirmarReserva`;

    // Preparar datos para el request
    const params = {
      patente: this.patente,
      nombreSucursalR: this.rentalData.pickupBranch,
      fechaRetiro: new Date(this.rentalData.pickupDate).toISOString().split('T')[0],
      fechaDevolucion: new Date(this.rentalData.returnDate).toISOString().split('T')[0],
      nombreSucursalD: this.rentalData.returnBranch,
      email: this.email,
    };

    const response = await axios.post(url, this.vehicleSelected, { params }); // Confirmar la reserva del vehículo

    console.log('Reserva realizada:', response.data);

    // Manejar la respuesta (por ejemplo, guardar la patente o redirigir)
    alert(response.data);

    this.$router.push({
        name: 'Cliente', // Nombre de la ruta de la página de confirmación
        query: {
          email: this.email, // Pasar el email
          reserva: this.reserva,
          vehicleSelected: this.vehicleSelected, 
        },
      });
    

  } catch (error) {
    console.error('Error en reserva:', error);
  }
},
  },
};
</script>


<style scoped>
/* Estilos para la página de confirmación de reserva */
.page-container {
  min-height: 100vh;
  width: 100vw;
  display: flex;
  flex-direction: column;
  background-color: #f4f4f4;
  margin: 0;
  padding: 0;
}

/* Estilos para el header */
.header {
  width: 100%;
  height: 80px;
  background-color: #ff0000;
  padding: 1rem 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  border-radius: 5px; /* Bordes redondeados */
}

/* Estilos para el logo */
.logo-container {
  display: flex;
  align-items: center;
  gap: 1rem;
}

/* Estilos para el logo de la empresa */
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

/* Estilos para el contenido principal */
.main-content {
  flex: 1;
  padding: 2rem;
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
  margin-top: 2rem;
}

/* Estilos para el contenedor de detalles */
.details-container {
  display: flex;
  flex-direction: column;
  gap: 2rem;
}

/* Estilos para la sección de información */
.confirmation-button {
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

/* Estilos para el botón de confirmación al pasar el mouse */
.confirmation-button:hover {
  background-color: #e63939; /* Color de fondo al pasar el mouse */
}

/* Estilos para la sección de información */
.info-section {
  background-color: #ffffff;
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

/* Estilos para el título de la sección de información */
.info-section h2 {
  color: #000000;
  margin-bottom: 1.5rem;
  font-size: 1.5rem;
}

/* Estilos para la cuadrícula de información */
.info-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 1.5rem;
}

/* Estilos para los elementos de información */
.info-item {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

/* Estilos para los elementos de información */
.info-item strong {
  color: #000000;
  font-size: 0.9rem;
}

/* Estilos para los elementos de información */
.info-item span {
  color: #000000;
  font-size: 1.1rem;
}

/* Estilos para la sección de vehículos */
.vehicle-section {
  background-color: #ffffff;
  color: #000000;
}

/* Estilos para el contenido de vehículos */
.vehicle-content {
  display: flex;
  gap: 2rem;
}

/* Estilos para la información del vehículo */
.vehicle-info {
  flex: 1;
}

/* Estilos para la descripción del vehículo */
.vehicle-description {
  margin-top: 1.5rem;
}

.vehicle-description p {
  margin-top: 0.5rem;
  color: #000000;
  line-height: 1.6;
}

.vehicle-image-container {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: flex-start;
}

.vehicle-image {
  max-width: 100%;
  height: auto;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}
/* Estilos para el mensaje de error */
.error-message {
  background-color: #fff3f3;
  border: 1px solid #ff0000;
  color: #ff0000;
  padding: 1rem;
  border-radius: 8px;
  margin-bottom: 1rem;
}

@media (max-width: 768px) { /* Estilos para pantallas pequeñas */
  .vehicle-content {
    flex-direction: column;
  }

  .info-grid {
    grid-template-columns: 1fr;
  }

  .main-content {
    padding: 1rem;
  }
}
</style>