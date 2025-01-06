import { createRouter, createWebHistory } from 'vue-router'; // Importamos las funciones necesarias para crear el router

const router = createRouter({ // Creamos el router
  history: createWebHistory(import.meta.env.BASE_URL), // Definimos el tipo de historial que usaremos
  routes: [ // Definimos las rutas
    {
      path: '/', // Ruta principal
      name: 'Home',
      component: () => import('../views/principalPage.vue'),
    },
  
    {
      path: '/cliente', // Ruta cliente
      name: 'Cliente',
      component: () => import('../views/userSolicitation.vue'),
    },
    {
      path: '/PrincipalEmpleado', // Ruta devolucion
      name: 'EmpleadoLogin',
      component: () => import('../views/PrincipalEmpleado.vue'),
    },

    {
      path: '/empleado', // Ruta devolucion
      name: 'Empleado',
      component: () => import('../views/devolucion.vue'),
    },

    {
      path: '/gerente', // Ruta devolucion
      name: 'Gerente',
      component: () => import('../views/gerenteSolicitation.vue'),
    },
    {
      path: '/mecanico', // Ruta devolucion
      name: 'Mecanico',
      component: () => import('../views/mecanicoSolicitation.vue'),
    },

    {
      path: '/vehiculos', // Ruta vehiculos
      name: 'Vehiculos',
      component: () => import('../views/vehiculos.vue'),
    },
  
    

    {
      path: '/devolucion', // Ruta devolucion
      name: 'Devolucion',
      component: () => import('../views/devolucion.vue'),
    },

    {
      path: '/confirmacion', // Ruta devolucion
      name: 'Confirmacion',
      component: () => import('../views/confirmacionReserva.vue'),
    },


    {
      path: '/registroEmpleado', // Ruta devolucion
      name: 'RegistroEmpleado',
      component: () => import('../views/registroEmpleado.vue'),
    },
  ],
});

export default router; // Exportamos el router
