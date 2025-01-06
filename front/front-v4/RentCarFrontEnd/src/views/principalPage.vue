<template>
  <main> 
    <div class="general"> 
    <div class="content"> <!--Contenido de la pagina principal-->
        <img class="image" src="./media/icono2.png">
        <div class="header">INICIO DE SESIÓN</div>
        <div class="headerDescription" v-if="!register">Completar campos para iniciar sesión</div>
        <div class="headerDescription" v-else>Completar campos para registro</div>
        <div class="inputContainer" v-if="!register">
          <input type="email" v-model="email" placeholder="Ingrese correo">
          <input type="password" v-model="password" placeholder="Ingrese contraseña">
          <button class="sessionButton" @click="login">Iniciar sesión</button>
        </div>
        <div class="inputContainer" v-else> <!--Registro de usuario-->
          <input type="rut" v-model="rut" placeholder="Ingrese su RUT (Ejemplo: 12345678-9)">
          <input type="nombre" v-model="nombre" placeholder="Ingrese nombre completo">
          <input type="direccion" v-model="direccion" placeholder="Ingrese su dirección">
          <input type="telefono" v-model="telefono" placeholder="Ingrese su número de teléfono (Ejemplo: 56912345678)">
          <input type="email" v-model="email" placeholder="Ingrese correo">
          <input type="password" v-model="passwordRegister" placeholder="Ingrese contraseña">
          <input type="password" v-model="passwordRegisterConfirmation" placeholder="Repita contraseña">
          <input type="fechaNacimiento" v-model="fechaNacimiento" placeholder="Ingrese fecha de nacimiento(Ejemplo: 2002-01-01)">
          <button class="sessionButton" @click="addUser">Registrar</button>
        </div>
        <div class="alsoButtons"> <!--Botones de cambio de vista-->
          <div class="alsoButton" @click="handleChange" v-if="!register">Registrarse</div>
          <div class="alsoButton" @click="handleChange" v-else>Iniciar Sesión</div>
          <div class="alsoButton" @click="redirectToPrincipalEmpleado">¿Eres empleado?</div> <!-- Nuevo botón -->
        </div>

      </div>
    </div>
  </main>
</template>

<script>
import axios from 'axios';

function redireccionASubpaginaCliente(email) {
  window.location.href = `/cliente?email=${encodeURIComponent(email)}`;
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
      fechaNacimiento: '',
      idCliente: null,
    };
  },

  methods: {
    async login() {
      const usuario = {
        email: this.email,
        password: this.password,
      };

      try {
        const respuesta = await axios.post(import.meta.env.VITE_BASE_URL + 'api/cliente/login', usuario);
        if (respuesta.data == 1) {
          console.log('email:', this.email);
          redireccionASubpaginaCliente(this.email);
        } else {
          alert('Usuario o contraseña incorrectos');
        }
      } catch (error) {
        alert('Error al iniciar sesión:', error);
      }
    },

    handleChange() {
      this.register = !this.register;
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
          this.fechaNacimiento 
        ) {
          const nuevoUsuario = {
            rut: this.rut,
            nombre: this.nombre,
            direccion: this.direccion,
            telefono: this.telefono,
            email: this.email,
            password: this.passwordRegister,
            fechaNacimiento: this.fechaNacimiento,
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
    },
    redirectToPrincipalEmpleado() {
    window.location.href = '/PrincipalEmpleado'; // Cambia la ruta según sea necesario
  },
  },
};
</script>


<style scoped>


/* Descripción del encabezado*/
.header,
.headerDescription {
  color: black;
}

/*Estilos de la pagina principal*/
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

/* Descripción de la imagen*/
.image{
  height: 150px;
  border: none;
  border-radius: 30px;
}


/* Descripción del botón de sesión*/
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

/* Descripción del bloque de input*/
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

/* Descripción del contenido*/
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

/* Descripción de botones extras*/
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
/* Descripción de botones antes de presionarlos*/
 .sessionButton:hover {
  background-color: #ff0000a5;
 }

.alsoButton:hover {
  background-color: #393232a5;
}

</style>

