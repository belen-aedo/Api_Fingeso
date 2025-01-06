<template>
  <main>
      <div class="page-container"> <!-- Contenedor de la página -->
          <header class="header"> 
              <div class="logo-container">
                  <img src="./media/icono2.png" alt="Rent-A-Wheel" class="logo">
                  <span class="brand-name">Rent-A-Wheel</span>
              </div>
              <div class="header-buttons">
        
                  <router-link to="/registroEmpleado" class="header-button">Registrar empleado</router-link>
              </div>
          </header>

          <div class="main-content"> <!-- Contenido principal -->
              <div class="details">
                  <h2>Detalles del sistema</h2>
                  <div class="employee-section">
                      <h3>Lista de empleados</h3>
                      
                      <!-- Tabla de empleados -->
                      <div class="table-container">
                          <table class="employee-table">
                              <thead>
                                  <tr>
                                      <th>ID</th>
                                      <th>Nombre</th>
                                      <th>Email</th>
                                      <th>Cargo</th>
                                      <th>Sucursal</th>
                                      <th>Acciones</th>
                                  </tr>
                              </thead>
                              <tbody> <!-- Aquí se mostrarán los empleados -->
                                  <tr v-for="employee in employees" :key="employee.id">
                                      <td>{{ employee.id }}</td>
                                      <td>{{ employee.nombre }}</td>
                                      <td>{{ employee.email }}</td>
                                      <td>{{ employee.cargo }}</td>
                                      <td>{{ employee.sucursal }}</td>
                                      <td class="actions">
                                          <button class="action-button edit">Editar</button>
                                          <button class="action-button delete">Eliminar</button>
                                      </td>
                                  </tr>
                              </tbody>
                          </table>
                      </div>
                  </div>
              </div>
          </div>
      </div>
  </main>
</template>

<script>
import axios from 'axios';

export default {
  // Propiedades del componente
  data() {
      return {
          employees: []
      }
  },
  created() {
      this.getEmployees();
  },
  methods: {
      // Método para obtener los empleados
      async getEmployees() {
          try {
              const response = await axios.get('URL_DE_TU_API/empleados');
              this.employees = response.data;
          } catch (error) {
              console.error('Error al obtener empleados:', error);
          }
      }
  }
}
</script>

<style scoped>
/* Estilos del componente */
html, body {
  margin: 0;
  padding: 0;
  height: 100%;
  width: 100%;
  font-family: 'Arial', sans-serif;
  color: #333;
}

/* Estilos del contenedor de la página */
.page-container {
  height: 100vh;
  width: 100vw;
  display: flex;
  flex-direction: column;
  background-color: #f4f4f4;
  margin: 0;
  padding: 0;
}

/* Estilos del encabezado */
.header {
  width: 100%;
  height: 80px;
  background-color: #ff0000;
  padding: 1rem 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
}

/* Estilos del logo y nombre de la marca */
.logo-container {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.logo {
  height: 40px;
  width: auto;
}

/* Estilos del nombre de la marca */
.brand-name {
  color: black;
  font-size: 1.5rem;
  font-weight: bold;
}

/* Estilos de los botones del encabezado */
.header-buttons {
  display: flex;
  gap: 1rem;
}

/* Estilos de los botones */
.header-button {
  background-color: #ffffff;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  font-weight: 500;
  transition: background-color 0.3s;
  text-decoration: none;
  color: #333;
}

/* Estilos de los botones al pasar el cursor */
.header-button:hover {
  background-color: #e0e0e0;
}

/* Estilos del contenido principal */
.main-content {
  flex: 1;
  padding: 2rem;
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
}

/* Estilos de los detalles */
.details {
  background-color: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

/* Estilos de los títulos */
.details h2 {
  margin-bottom: 1.5rem;
  color: #333;
}

/* Estilos de la sección de empleados */
.employee-section h3 {
  margin-bottom: 1rem;
  color: #666;
}

/* Estilos de la tabla de empleados */
.table-container {
  overflow-x: auto;
}

/* Estilos de la tabla */
.employee-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 1rem;
}

/* Estilos de las celdas y filas de la tabla */
.employee-table th,
.employee-table td {
  padding: 1rem;
  text-align: left;
  border-bottom: 1px solid #eee;
}

/* Estilos de las celdas de la tabla */
.employee-table th {
  background-color: #f8f8f8;
  font-weight: 600;
  color: #333;
}

/* Estilos de las filas de la tabla */
.employee-table tr:hover {
  background-color: #f9f9f9;
}

/* Estilos de las acciones */
.actions {
  display: flex;
  gap: 0.5rem;
}

/* Estilos de los botones de acción */
.action-button {
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 0.9rem;
  transition: background-color 0.3s;
}

/* Estilos de los botones de acción editar y eliminar */
.action-button.edit {
  background-color: #4CAF50;
  color: white;
}

/* Estilos de los botones de acción eliminar */
.action-button.delete {
  background-color: #f44336;
  color: white;
}

/* Estilos de los botones de acción al pasar el cursor */
.action-button:hover {
  opacity: 0.9;
}

@media (max-width: 768px) {
  .main-content {
      padding: 1rem;
  }

  .actions {
      flex-direction: column;
  }

  .employee-table th,
  .employee-table td {
      padding: 0.5rem;
  }
}
</style>