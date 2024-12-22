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
      path: '/vehiculos', // Ruta vehiculos
      name: 'Vehiculos',
      component: () => import('../views/vehiculos.vue'),
    },
    {
      path: '/arriendo', // Ruta arriendo
      name: 'Arriendo',
      component: () => import('../views/arriendo.vue'),
    },
    

    {
      path: '/devolucion', // Ruta devolucion
      name: 'Devolucion',
      component: () => import('../views/devolucion.vue'),
    },
  ],
});

export default router; // Exportamos el router
