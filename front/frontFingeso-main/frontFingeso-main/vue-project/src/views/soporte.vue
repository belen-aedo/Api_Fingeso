<template>
  <div id="app">
    <header>
      <div class="logo-section">
        <img src="../components/images/logoOficial.jpeg" alt="Nombre de la página" height="60">
      </div>
      <nav>
        <ul>
          <a class = "principal-button" @click="navigateTo('/principal')">Inicio</a>
          <button v-if="isAuthenticated" class="logout-button" @click="logout">Cerrar sesión</button>
        </ul>
      </nav>
    </header>
  
    <div class="soporte-container">
      <h2>Soporte</h2>
      <form @submit.prevent="soporteForm">
        
        <div class="form-group">
          <label for="descripcion">CONSULTA</label>
          <textarea id="descripcion" v-model="descripcion" placeholder="Describa la solicitud" required></textarea>
        </div>
        <button @click="navigateTo('/principal')" type="submit" class="submit-button">Enviar Solicitud</button>
        <p v-if="message" class="message">{{ message }}</p>
      </form>
    </div>
  
    <footer>
      <p class = "copyright">© 2024 HomeSphere Todos los derechos reservados. Prohibida su reproducción total o parcial por cualquier medio</p>
    </footer>
  </div>
  </template>
  
  <script>
  import axios from 'axios'
  import { ref } from 'vue'
  import { useRouter } from 'vue-router'
  
  export default {
    setup() {
      const tipo_peticion = ref('')
      const descripcion = ref('')
      const message = ref('')
      const router = useRouter()
      const isAuthenticated = ref(false);
      const checkAuth = () => {
        const userId = localStorage.getItem('usuario');
        isAuthenticated.value = !!userId;
     };
      const logout = () => {
        localStorage.removeItem('usuario');
        isAuthenticated.value = false;
        router.push('/login');
      };
      
      const soporteForm = async () => {
        try {
          const response = await axios.post('http://localhost:8080/api/soporte', {
            tipo_peticion: tipo_peticion.value,
            descripcion: descripcion.value
          })
          if (response.data) {
            message.value = 'Solicitud enviada con éxito.'
            router.push('/principal')
          }
        } catch (error) {
          message.value = 'Error al enviar la solicitud.'
        }
      }
  
      const navigateTo = (route) => {
        router.push(route)
      }
  
      return {
        tipo_peticion,
        descripcion,
        message,
        soporteForm,
        navigateTo,
        isAuthenticated,
        logout,
        checkAuth
      }
    }
  }
  </script>
  
<style scoped>
  *{
  font-family: 'Poppins';
}
#app {
  background-color: #000000; 
  min-height: 100vh; 
}

header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 5px 15px;
  background-color: #ff0000;
  color: white;
  text-decoration: none
}
header .logo-section img {
  width: 100px;
  height: 80px;
  border-radius: 10px;
}


nav ul {
  display: flex;
  align-items: center;
  list-style: none;
  padding: 0;
  margin: 0;
  text-decoration: none
}

nav ul li {
  margin-right: 20px;
  text-decoration: none
}

nav ul li a {
  color: #636869;
  text-decoration: none;
  font-size: 18px;
}

nav ul li a:hover {
  text-decoration: underline;
}
.principal-button,.logout-button {
  background-color: #ffffff;
  color: #000000;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  margin-right: 10px;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
.principal-button:hover,.logout-button:hover{
  transform: translateY(-2px);
  box-shadow: 0 6px 8px rgba(0, 0, 0, 0.1);
}
.logout-button {
  background-color: #c62828;
  color: rgb(255, 255, 255);
}

.logout-button:hover {
  background-color: #c62828;
}

  .soporte-container {
    max-width: 400px;
    margin: 50px auto;
    padding: 40px;
    border: 2px solid #ffffff;
    border-radius: 15px;
    background-color: #fffcfc;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }
  
  h2 {
    text-align: center;
    margin-bottom: 20px;
    color: #333;
  }
  
  .form-group {
    margin-bottom: 20px;
    
  }
  .form-group label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
    color: #555;
  }
  
  .form-group select,
  .form-group textarea {
    width: 100%;
    padding: 10px;
    border: 1px solid #000000;
    border-radius: 10px;
    box-sizing: border-box;
    font-size: 16px;
  }
  
  textarea {
    resize: vertical;
    height: 100px;
  }
  
  .submit-button {
    width: 100%;
    padding: 10px;
    background-color: #ff0000;
    color: white;
    border: none;
    border-radius: 10px;
    cursor: pointer;
    font-size: 18px;
  }
  
  .submit-button:hover {
    background-color: #f00000;
  }
  
  .message {
    margin-top: 20px;
    text-align: center;
    color: #f93c3c;
    font-weight: bold;
  }
  
  footer {
  display: flex;
  justify-content: space-around;
  align-items: center;
  padding: 10px 20px;
  background-color: #f80000;
  color: white;
}
.copyright {
  color: white;
  font-size: 14px;
  text-align: left;

}
</style>
  