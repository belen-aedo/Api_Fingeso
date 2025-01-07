<template>
    <main> 
      <div class="general"> 
      <div class="content"> <!--Contenido de la pagina principal-->
          <img class="image" src="./media/icono2.png">
          <div class="header">Registro de empleado</div>
        

          <div class="headerDescription" v-if="register">Completar campos para registro</div>
          <div class="inputContainer" v-else> <!--Registro de empleado-->
            <input type="rut" v-model="rut" placeholder="Ingrese RUT (Ejemplo: 12345678-9)">
            <input type="nombre" v-model="nombre" placeholder="Ingrese nombre completo">
            <input type="direccion" v-model="direccion" placeholder="Ingrese su dirección">
            <input type="telefono" v-model="telefono" placeholder="Ingrese su número de teléfono (Ejemplo: 56912345678)">
            <input type="email" v-model="email" placeholder="Ingrese correo">
            <input type="password" v-model="passwordRegister" placeholder="Ingrese contraseña">
            <input type="password" v-model="passwordRegisterConfirmation" placeholder="Repita contraseña">
            <input type="fechaNacimiento" v-model="fechaNacimiento" placeholder="Ingrese fecha de nacimiento(Ejemplo: 2002-01-01)">
            <input type="nombreSucursal" v-model="nombreSucursal" placeholder="Ingrese nombre de la sucursal">
            <input type="rol" v-model="rol" placeholder="Ingrese rol del empleado">
            <button class="sessionButton" @click="addUser">Registrar</button>
          </div>
          <div class="also-buttons">
          <router-link to="/gerente" class="also-buttons">Página anterior</router-link> 
            </div>
  
        </div>
      </div>
    </main>
  </template>
  
  <script>
  import axios from 'axios';
  
  function redireccionASubpaginaCliente(email) {
    window.location.href = `/gerente?email=${encodeURIComponent(email)}`;
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
        idEmpleado: null,
        nombreSucursal: '',
        rol: '',
      };
    },
  
    methods: {  
        async addUser() { // Método para registrar un nuevo usuario, es async porque realiza una operación asíncrona. 
      // Las funciones async permiten el uso de await dentro de ellas, lo que facilita la escritura de código asíncrono que se lee de manera similar al código síncrono.
        if (this.passwordRegister === this.passwordRegisterConfirmation) {
            if (
            this.rut.trim() &&
            this.nombre.trim() && 
            this.direccion.trim() &&
            this.telefono.trim() &&
            this.email.trim() &&
            this.passwordRegister.trim() &&
            this.fechaNacimiento &&
            this.nombreSucursal.trim() && // Validar sucursal
            this.rol.trim() // Validar rol
            ) {
            const nuevoUsuario = {
                rut: this.rut,
                nombre: this.nombre,
                direccion: this.direccion,
                telefono: this.telefono,
                email: this.email,
                password: this.passwordRegister,
                fechaNacimiento: this.fechaNacimiento,
                sucursal: { nombreSucursal: this.nombreSucursal },
                rol: this.rol, // Enviar rol
            };

            try {
                const registro = await axios.post( // Petición POST al servidor para registrar un nuevo usuario con los datos 
                import.meta.env.VITE_BASE_URL + 'api/empleado/registrar',
                nuevoUsuario
                );
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
      window.location.href = '/gerente'; // Cambia la ruta según sea necesario
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
  
  