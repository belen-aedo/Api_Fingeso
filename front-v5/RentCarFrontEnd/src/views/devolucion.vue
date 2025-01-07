<template>
  <div class="page-container">
    <!-- Header -->
    <header class="header"> 
      <div class="logo-container">
        <img src="./media/icono2.png" alt="Rent-A-Wheel" class="logo">
        <span class="brand-name">Rent-A-Wheel</span>
      </div>
      <div class="header-buttons">
        <router-link to="/cliente" class="header-button">Página anterior</router-link>
      </div>
    </header>

    <!-- Contenido principal -->
    <div class="main-content">
      <div class="rental-form">
        <h2>Devolución del Vehículo</h2>
        <form @submit.prevent="handleSubmit">
          <!-- Datos básicos del formulario -->
          <div class="form-row">
            <!-- Id de reserva -->
            <div class="form-group">
              <label>Id de reserva:</label>
              <input 
                v-model="formData.idReserva"
                type="number"
                placeholder="Ingrese el id de reserva"
                required
              />
            </div>

            <!-- Kilometraje Final -->
            <div class="form-group">
              <label>Kilometraje Final:</label>
              <input 
                v-model="formData.finalMileage"
                type="number"
                placeholder="Ingrese el kilometraje final"
                required
              />
            </div>

            <!-- Sucursal de devolución -->
            <div class="form-group">
              <label>Sucursal de devolución</label>
              <select 
                v-model="formData.SucursalD"
                required
              >
                <option value="" disabled selected>Seleccione la sucursal de devolución</option>
                <option>Sucursal Central</option>
                <option>Sucursal Costa</option>
                <option>Sucursal 3</option>
                <option>Sucursal 4</option>
                <option>Sucursal 5</option>
              </select>
            </div>
          </div>

          <!-- Elementos que se muestran tras obtener detalles de devolución -->
          <div v-if="datosArriendo" class="form-group full-width">
            <label>Información de Arriendo:</label>
            <textarea 
              readonly
              class="readonly-textarea"
            >{{ datosArriendo }}</textarea>
          </div>


          <div v-if="mostrarDetallesDevolucion">
            <div class="form-row">
              <div class="form-group">
                <label>Estado de arriendo:</label>
              <select v-model.number="pendiente" required>
                <option value="" disabled>Seleccione una opción</option>
                <option :value="1">Pendiente</option>
                <option :value="0">No Pendiente</option>
              </select>
            </div>


              <div class="form-group">
                <label>Id de Vehículo:</label>
                <input 
                  v-model="idVehiculo"
                  type="number"
                  placeholder="Ingrese el id de vehículo correspondiente"
                  required
                />
              </div>
            </div>

            <div class="form-row">
              <!-- Nivel de Combustible -->
              <div class="form-group">
                <label>Nivel de Combustible:</label>
                <select 
                  v-model="formData.fuelLevel"
                  required
                >
                  <option value="" disabled selected>Seleccione el nivel de combustible</option>
                  <option value="1/4">1/4</option>
                  <option value="1/2">1/2</option>
                  <option value="3/4">3/4</option>
                  <option value="Full">Full</option>
                </select>
              </div>

              <!-- Multa -->
              <div class="form-group">
                <label>Multa en CLP:</label>
                <input 
                  v-model="multa"
                  type="number"
                  placeholder="Ingrese multa si corresponde"
                  required
                />
              </div>
            </div>

            <!-- Condición General -->
            <div class="form-group full-width">
              <label>Condición General:</label>
              <textarea 
                v-model="formData.generalCondition"
                placeholder="Describa la condición general del vehículo"
                required
              ></textarea>
            </div>

            <!-- Daños -->
            <div class="form-row damage-section">
              <div class="form-group damage-check">
                <label>¿El vehículo tiene daños?</label>
                <div class="checkbox-group">
                  <input 
                    type="checkbox"
                    v-model="formData.hasDamage"
                  />
                  <span>Sí</span>
                  <button 
                    v-if="formData.hasDamage"
                    type="button"
                    class="workshop-button"
                    @click="handleWorkshopSubmit"
                  >
                    Enviar vehículo a taller de reparación
                  </button>
                </div>
              </div>
            </div>

            <!-- Descripción de Daños -->
            <div v-if="formData.hasDamage" class="form-group full-width">
              <label>Descripción de los daños:</label>
              <textarea 
                v-model="formData.damageDescription"
                placeholder="Describa los daños encontrados"
              ></textarea>
            </div>

            <!-- Notas Adicionales -->
            <div class="form-group full-width">
              <label>Notas Adicionales:</label>
              <textarea 
                v-model="formData.additionalNotes"
                placeholder="Ingrese cualquier nota adicional"
              ></textarea>
            </div>
          </div>

          <!-- Botones -->
          <div class="button-group">
            <button @click="registrarDevolucion" class="submit-button"
            type="button">
            Registrar Devolución
            </button>
            <button type="button" @click="resetForm" class="reset-button">
              Limpiar Formulario
            </button>
            <button 
              @click="obtenerDetallesDevolucion"
              type="button"
              class="search-button"
            >
              Detalles de Devolución
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: 'VehicleReturn',
  data() {
    return {
      formData: {
        idReserva: '',
        finalMileage: null,
        fuelLevel: '',
        generalCondition: '',
        hasDamage: false,
        damageDescription: '',
        additionalNotes: '',
        returnDate: new Date().toISOString().slice(0, 10),
        SucursalD: '',
      },
      idVehiculo: null,
      rol: null,
      email: null,
      datosArriendo: null,
      multa: null,
      pendiente: null,
      mostrarDetallesDevolucion: false, // Nueva bandera
    };
  },
  mounted() {
    // Recuperar datos de la query
    const query = this.$route.query;

    // Parsear y asignar datos
    this.email = query.email || null;
    this.rol = query.rol || null;
    
    console.log('Email del empleado:', this.email);
    console.log('Rol del empleado:', this.rol);
  },
  methods: {
    async obtenerDetallesDevolucion() {
      try {
      const url = `${import.meta.env.VITE_BASE_URL}api/Devolucion/DatosArriendo`;

      // Preparar datos para el request
      const params = {
        NombreSucursal: this.formData.SucursalD,
        idReserva: this.formData.idReserva,
        rol: this.rol,
        kilometraje: this.formData.finalMileage,
      };

      const response = await axios.get(url,{ params });

      console.log('Detalles de arriendo:', response.data);
      this.datosArriendo = response.data;
      // Mostrar los detalles al usuario
      this.mostrarDetallesDevolucion = true;
      // Manejar la respuesta (por ejemplo, guardar la patente o redirigir)
      
    } catch (error) {
      console.error('Error en detalles de arriendo:', error);
    }
  },
  async registrarDevolucion() {
  try {
    const url = `${import.meta.env.VITE_BASE_URL}api/Devolucion/confirmaDevolucion`;

    const requestData = new URLSearchParams({
      EstadoPendiente: this.pendiente,
      idReserva: this.formData.idReserva,
      rol: this.rol,
      multa: this.multa,
      idVehiculo: this.idVehiculo,
      nuevoKilometraje: this.formData.finalMileage
    });

    const response = await axios.post(url, requestData, {
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    });

    console.log('Detalles de arriendo:', response.data);
    alert(response.data);
  } catch (error) {
    console.error('Error en detalles de arriendo:', error);
  }
},

    handleSubmit() {
      // Procesar datos del formulario de devolución
      console.log('Datos de devolución:', this.formData);
      alert('Devolución completada');
    },
    handleWorkshopSubmit() {
      // Procesar envío al taller
      console.log('Enviando vehículo al taller...');
      alert('Vehículo enviado al taller de reparación');
    },
    resetForm() {
      // Reiniciar formulario
      this.formData = {
        idReserva: '',
        finalMileage: null,
        fuelLevel: '',
        generalCondition: '',
        hasDamage: false,
        damageDescription: '',
        additionalNotes: '',
        returnDate: new Date().toISOString().slice(0, 10),
        SucursalD: ''
      };
      this.mostrarDetallesDevolucion = false; // Ocultar los detalles
    },
  }
};
</script>

<style scoped>
/* Los estilos se mantienen igual */
.page-container {
  min-height: 100vh;
  width: 100vw;
  display: flex;
  flex-direction: column;
  background-color: #f4f4f4;
  margin: 0;
  padding: 0;
}

/* Header */
.header {
  width: 100%;
  height: 80px;
  background-color: #ff0000;
  padding: 1rem 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

/* Logo */
.logo-container {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.logo {
  height: 40px;
  width: auto;
}

/* Nombre de la marca */
.brand-name {
  color: white;
  font-size: 1.5rem;
  font-weight: bold;
}

/* Botones del header */
.header-button {
  background-color: #ffffff;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  font-weight: 500;
  text-decoration: none;
  color: #333;
  transition: background-color 0.3s;
}

/* Efecto hover */
.header-button:hover {
  background-color: #e0e0e0;
}

/* Contenido principal */
.main-content {
  flex: 1;
  padding: 2rem;
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
}

/* Formulario de devolución */
.rental-form {
  margin: 5rem auto;
  background-color: #ffffff;
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

/* Título del formulario */
.rental-form h2 {
  color: #ff0000;
  text-align: center;
  margin-bottom: 2rem;
  font-size: 1.5rem;
  font-weight: bold;
}

/* Estilos de formulario */
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
}


.full-width {
  width: 100%;
}


label {
  font-weight: 500;
  color: #333;
}
/* Estilos de los campos de formulario */
input, select, textarea {
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  transition: border-color 0.3s;
}

input:focus, select:focus, textarea:focus {
  border-color: #ff4d4d;
  outline: none;
}

textarea {
  min-height: 100px;
  resize: vertical;
}

.damage-section {
  align-items: center;
}

.damage-check {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.checkbox-group {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.workshop-button {
  background-color: #e0e0e0;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.workshop-button:hover {
  background-color: #d0d0d0;
}

.button-group {
  display: flex;
  justify-content: space-between;
  margin-top: 2rem;
}

.submit-button {
  background-color: #ff0000;
  color: white;
  border: none;
  padding: 0.75rem 1.5rem;
  border-radius: 4px;
  font-weight: 500;
  cursor: pointer;
  transition: background-color 0.3s;
}

.submit-button:hover {
  background-color: #e60000;
}

.reset-button {
  background-color: #e0e0e0;
  border: none;
  padding: 0.75rem 1.5rem;
  border-radius: 4px;
  font-weight: 500;
  cursor: pointer;
  transition: background-color 0.3s;
}

.reset-button:hover {
  background-color: #d0d0d0;
}

@media (max-width: 768px) {
  .form-row {
    flex-direction: column;
  }

  .rental-form {
    margin: 1rem;
    padding: 1rem;
  }

  .button-group {
    flex-direction: column;
    gap: 1rem;
  }

  .submit-button, .reset-button {
    width: 100%;
  }
}
</style>