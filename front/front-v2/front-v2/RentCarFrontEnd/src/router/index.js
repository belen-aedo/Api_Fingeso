import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
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

export default router;
