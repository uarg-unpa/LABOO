window.addEventListener('load', function() {
    const formJugadores = document.getElementById('formJugadores');

    formJugadores.addEventListener('submit', function(evento) {
        // Evitamos que el formulario recargue la página automáticamente
        evento.preventDefault();
        
        let errores = "";

        // Limpiar los mensajes en rojo de la validación anterior
        document.querySelectorAll('.error-msg').forEach(function(span) {
            span.textContent = "";
        });

        // Capturamos los valores
        let id = document.getElementById('jugadorId').value.trim();
        let apellido = document.getElementById('apellido').value.trim();
        let nombre = document.getElementById('nombre').value.trim();
        let posicion = document.getElementById('posicion').value.trim();
        let edad = document.getElementById('edad').value.trim();
        let equipoId = document.getElementById('equipoId').value.trim();

        // Validaciones inyectando el texto rojo y acumulando en la variable "errores"
        if (id === "" || id.length > 5 || Number(id) <= 0) {
            errores += "- El ID es obligatorio, numérico y de hasta 5 dígitos.\n";
            document.getElementById('errorId').textContent = "Requerido (Máx 5 dígitos).";
        }
        
        if (apellido === "" || apellido.length > 20) {
            errores += "- El Apellido es obligatorio y no puede superar los 20 caracteres.\n";
            document.getElementById('errorApellido').textContent = "Requerido (Máx 20 caracteres).";
        }
        
        if (nombre === "" || nombre.length > 30) {
            errores += "- El Nombre es obligatorio y no puede superar los 30 caracteres.\n";
            document.getElementById('errorNombre').textContent = "Requerido (Máx 30 caracteres).";
        }
        
        if (posicion === "" || posicion.length > 20) {
            errores += "- La Posición es obligatoria y no puede superar los 20 caracteres.\n";
            document.getElementById('errorPosicion').textContent = "Requerido (Máx 20 caracteres).";
        }
        
        if (edad === "" || edad.length > 2 || Number(edad) <= 0) {
            errores += "- La Edad es obligatoria, numérica y de máximo 2 dígitos.\n";
            document.getElementById('errorEdad').textContent = "Requerido (Máx 2 dígitos).";
        }

        if (equipoId === "" || equipoId.length > 5 || Number(equipoId) <= 0) {
            errores += "- El ID del Equipo es obligatorio, numérico y de hasta 5 dígitos.\n";
            document.getElementById('errorEquipoId').textContent = "Requerido (Máx 5 dígitos).";
        }

        // Mostrar el resultado en ventana emergente (alert) para cumplir el TP
        if (errores === "") {
            alert("¡Validación exitosa! Los datos del jugador cumplen con el modelo de datos.");
        } else {
            alert("Existen errores en los datos ingresados:\n\n" + errores);
        }
    });
});