<template>
  <div id="app">
  <header>
    <div class="logo-section">
      <a @click="navigateTo('/principal')">
        <img src="../components/images/logoOficial.jpeg" alt="Nombre de la página" height="60">
      </a>
    </div>
    <nav>
      <ul>
        <a class = "principal-button"@click="navigateTo('/principal')">Inicio</a>
        <a class = "support-button"  @click="navigateTo('/soporte')">Ayuda</a>
      </ul>
    </nav>
  </header>

  <div class="registro-container">
    <h2>Registro</h2>
    <form @submit.prevent="registerForm">
      <div class="form-group" v-for="(label, model) in formFields" :key="model">
        <label :for="model">{{ label }}</label>
        <input
          :type="inputType(model)"
          :id="model"
          v-model="formData[model]"
          :placeholder="label"
          required
        />
      </div>
      <button type="submit" class="submit-button">Registrarse</button>
      <p v-if="message" class="message">{{ message }}</p>
      <div>
      
      </div>
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
    const formData = ref({
      firstName: '',
      secondName: '',
      lastName: '',
      secondLastName: '',
      rut: '',
      email: '',
      number: '',
      password: ''
    })

    const message = ref('')
    const router = useRouter()

    const formFields = {
      firstName: 'Nombre',
      lastName: 'Apellido',
      rut: 'RUT: (Ejemplo: 12345678-9)',
      email: 'Correo electrónico',
      number: 'Teléfono',
      password: 'Contraseña'
    }

    const inputType = (model) => {
      if (model === 'email') return 'email'
      if (model === 'number') return 'tel'
      if (model === 'password') return 'password'
      return 'text'
    }

    const navigateTo = (route) => {
      router.push(route)
    }

    const registerForm = async () => {
      try {
        const emailExists = await axios.get(`http://localhost:8080/users/findByEmail/${formData.value.email}`)
        if (emailExists.data) {
          message.value = 'El correo ya se encuentra registrado.'
        } else {
          const isValidRut = await axios.get(`http://localhost:8080/users/validateRut/${formData.value.rut}`)
          if (isValidRut.data) {
            await axios.post('http://localhost:8080/users/register', formData.value)
            message.value = 'Registro exitoso.'
            router.push('/principal')
          } else {
            message.value = 'El RUT ingresado no es válido.'
          }
        }
      } catch (error) {
        message.value = 'Ocurrió un error durante el registro.'
      }
    }

    const redirectToPropertyBroker = () => {
      router.push('/registroDeCorredoresPropiedades')
    }

    return {
      formData,
      formFields,
      inputType,
      message,
      registerForm,
      navigateTo,
      redirectToPropertyBroker
    }
  }
}
</script>

<style scoped>
*{
  font-family: 'Poppins';
}
header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 5px 15px;
  background-color: #ff0606;
  color: white;
  text-decoration: none
}
#app {
  background-color: #000000; 
  min-height: 100vh; 
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
  color: #f20505;
  text-decoration: none;
  font-size: 18px;
}

nav ul li a:hover {
  text-decoration: underline;
}

.principal-button, .support-button {
  background-color: #EAF9E7;
  color: #370101;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  margin-right: 10px;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
.principal-button:hover, .support-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 8px rgba(0, 0, 0, 0.1);
}
.principal-button{
  margin-left: 22px;
}

.registro-container {
  max-width: 400px;
  margin: 50px auto;
  padding: 40px;
  border: 2px solid #ffffff;
  border-radius: 15px;
  background-color: #ffffff;
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
  font-weight: bold;
  margin-bottom: 5px;
  color: #555;
}

input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 10px;
  box-sizing: border-box;
  font-size: 16px;
}

.submit-button {
  width: 100%;
  padding: 12px;
  background-color: #ff0000;
  color: white;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  font-size: 18px;
}

.submit-button:hover {
  background-color: #ff0000;
}

.message {
  margin-top: 20px;
  text-align: center;
  color: #ff0000;
  font-weight: bold;
}
footer {
  display: flex;
  justify-content: space-around;
  align-items: center;
  padding: 10px 20px;
  background-color: #ff0000;
  color: white;
}
.copyright {
  color: white;
  font-size: 14px;
  text-align: left;

}

.property-broker-button {
  background-color: transparent;
  border: none;
  color: blue;
  cursor: pointer;
  text-decoration: underline;
}

.property-broker-button:hover {
  color: darkblue;
}
</style>
