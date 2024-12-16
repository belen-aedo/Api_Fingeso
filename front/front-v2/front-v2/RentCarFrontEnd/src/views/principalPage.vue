<script setup>


</script>

<template>
  <main>
    <div class="general">
      <div class="content">
        <img class="image" src="./media/icono2.png">
        <div class="header">INICIO DE SESIÓN</div>
        <div class="headerDescription" v-if="!register">Completar campos para iniciar sesión</div>
        <div class="headerDescription" v-else>Completar campos para registro</div>
        <div class="inputContainer" v-if="!register">
          <input type="email" v-model="email" placeholder="Ingrese correo">
          <input type="password" v-model="password" placeholder="Ingrese contraseña">
          <button class="sessionButton" @click="login">Iniciar sesión</button>
        </div>
        <div class="inputContainer" v-else>
          <input type="rut" v-model="rut" placeholder="Ingrese su RUT (Ejemplo: 12345678-9)">
          <input type="nombre" v-model="nombre" placeholder="Ingrese nombre completo">
          <input type="direccion" v-model="direccion" placeholder="Ingrese su dirección">
          <input type="telefono" v-model="telefono" placeholder="Ingrese su número de teléfono (Ejemplo: 56912345678)">
          <input type="email" v-model="email" placeholder="Ingrese correo">
          <input type="password" v-model="passwordRegister" placeholder="Ingrese contraseña">
          <input type="password" v-model="passwordRegisterConfirmation" placeholder="Repita contraseña">
          <input type="fechaDeNacimiento" v-model="fechaDeNacimiento" placeholder="Ingrese fecha de nacimiento(Ejemplo: 2002-01-01)">
          <button class="sessionButton" @click="addUser">Registrar</button>
        </div>
        <div class="alsoButtons">
          <div class="alsoButton" @click="handleChange" v-if="!register">Registrarse</div>
          <div class="alsoButton" @click="handleChange" v-else>Iniciar Sesión</div>
          
        </div>
      </div>
    </div>
  </main>
</template>

<script>

import axios from 'axios';
//redireccionamos a cliente
function redireccionASubpaginaCliente() {
  window.location.href = "/cliente";
  
}
export default {
  data() {
    return {
      register: false,
      rut: '',
      nombre: '',
      direccion: '',
      telefono: '',
      email: '',
      password: '',
      passwordRegister: '',
      passwordRegisterConfirmation: '',
      fechaDeNacimiento: ''
    };
  },
  methods: {
    async login() {
      const usuario = {
        "email": this.email,
        "password": this.password
      };

      try {
        const respuesta = await axios.post(import.meta.env.VITE_BASE_URL + 'api/cliente/login', usuario);
        if (respuesta.data === 1) {
          redireccionASubpaginaCliente();
        }

        if (respuesta.data === 0) {
          alert('Usuario o contraseña incorrectos');
        }
        respuesta.data === 0;
        console.log(respuesta.data);

      } catch (error) {
        alert('Error al iniciar sesión:', error);
      }
    },
    handleChange() {
      this.register = !this.register;
      console.log(this.register);
    },
    async addUser() {
      if (this.passwordRegister === this.passwordRegisterConfirmation) {
        if (
          this.rut.trim() &&
          this.nombre.trim() &&
          this.direccion.trim() &&
          this.telefono.trim() &&
          this.email.trim() &&
          this.passwordRegister.trim() &&
          this.fechaDeNacimiento
        ) {
          const fechaFormateada = new Date(this.fechaDeNacimiento).toISOString().split('T')[0];

          const nuevoUsuario = {
            "rut": this.rut,
            "nombre": this.nombre,
            "direccion": this.direccion,
            "telefono": this.telefono,
            "email": this.email,
            "password": this.passwordRegister,
            "fechaDeNacimiento": this.fechaDeNacimiento
          };

          try {
            const registro = await axios.post(import.meta.env.VITE_BASE_URL + 'api/cliente/registrar', nuevoUsuario);
            console.log('Respuesta del servidor:', registro.data);
            alert('Usuario registrado exitosamente');
          } catch (error) {
            console.error('Error al registrar usuario:', error.response ? error.response.data : error.message);
            alert('Error al registrar usuario: ' + (error.response ? error.response.data : error.message));
          }
        } else {
          alert('Debe completar todos los campos');
        }
      } else {
        alert('Las contraseñas no coinciden');
        }
    }  
  }
}

</script>
<style scoped>

.header,
.headerDescription {
  color: black;
}

.general {
  background-image: url("./media/fondo1.jpg");
  height: 120vh;
  width: 100vw; 
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  display: flex;
  justify-content: center;
  align-items: center;
}

.image{
  height: 150px;
  border: none;
  border-radius: 30px;
}

.sessionButton {
  width: 100%;
  padding: 8px;
  background-color: #ff0000;
  color: white;
  border: none;
  border-radius: 30px;
  cursor: pointer;
  margin-top: 10px;
}

input {
  width: calc(100% - 20px);
  padding: 8px 10px;
  border: 2px solid #ff0000a5;
  align-items: center;
  align-content: center;
  justify-content: center;
  border-radius: 50px;
  box-sizing: border-box;
}

.content {
  box-sizing: border-box;
  padding: 10px;
  background-color: #dad7d7f3;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  align-items: center;

  min-width: 300px;
  max-width: 500px;
  width: 100%;
  margin: 0px 10px;
  min-height: 500px;
  max-height: 700px;
  height: auto;

  border-radius: 10px;
}

.alsoButton {
  width: 100%;
  padding: 8px;
  background-color: #393232;
  color: white;
  border: none;
  border-radius: 30px;
  cursor: pointer;
  margin-top: 10px;
}
 .sessionButton:hover {
  background-color: #ff0000a5;
 }

.alsoButton:hover {
  background-color: #393232a5;
}

</style>

