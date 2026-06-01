window.addEventListener('load', function() {
    
    // Variables globales
    const form = document.getElementById('formExpediente');
    const selectTramite = document.getElementById('tipoTramite');
    const inputResponsable = document.getElementById('responsable');
    const btnEstadistica = document.getElementById('btnEstadistica');
    const btnJSON = document.getElementById('btnJSON');
    
    // Bandera para el Punto 5
    let formularioValido = false; 

    // --- 3b) Mensaje según tipo de trámite ---
    selectTramite.addEventListener('change', function() {
        if (this.value === 'Urgente') alert('Resolución dentro de las 24 horas');
        else if (this.value === 'Normal') alert('Resolución dentro de las 48 horas');
        else if (this.value === 'Bajo') alert('Resolución dentro de las 96 horas');
    });

    // --- 3e) Mayúsculas automáticas en el responsable ---
    inputResponsable.addEventListener('input', function() {
        this.value = this.value.toUpperCase();
    });

    // --- 3f) VALIDACIÓN GENERAL DEL FORMULARIO ---
    form.addEventListener('submit', function(evento) {
        evento.preventDefault(); 
        let esValido = true; 
        
        // Limpiamos errores previos
        document.querySelectorAll('.error-msg').forEach(span => span.textContent = "");
        document.getElementById('mensajeResultado').textContent = "";
        formularioValido = false;

        // 3a) Formateo y validación de Expediente
        const numExpInput = document.getElementById('numExpediente');
        let numExp = numExpInput.value.trim();
        
        if (/^\d{4}$/.test(numExp)) {
            numExpInput.value = `EXP-${numExp}/25`;
        } else if (!/^EXP-\d{4}\/25$/.test(numExpInput.value)) {
            document.getElementById('errorExpediente').textContent = "Error: Ingrese exactamente 4 dígitos.";
            esValido = false;
        }

        // Validación Área
        if (document.getElementById('area').value === "") {
            document.getElementById('errorArea').textContent = "Seleccione un área.";
            esValido = false;
        }

        // Validación Trámite
        if (selectTramite.value === "") {
            document.getElementById('errorTramite').textContent = "Seleccione el tipo de trámite.";
            esValido = false;
        }

        // 3c) Validación Días
        const dias = document.getElementById('diasTramite').value;
        if (dias === "" || Number(dias) <= 0) {
            document.getElementById('errorDias').textContent = "Error: Debe ser un número mayor a 0.";
            esValido = false;
        }

        // 3d) Validación Estado
        const estadoOpciones = document.getElementsByName('estado');
        let estadoElegido = false;
        for (let radio of estadoOpciones) {
            if (radio.checked) { estadoElegido = true; break; }
        }
        if (!estadoElegido) {
            document.getElementById('errorEstado').textContent = "Error: Es obligatorio seleccionar un estado.";
            esValido = false;
        }

        // 3e) Validación Responsable (sin números)
        const respValor = inputResponsable.value;
        if (respValor === "") {
            document.getElementById('errorResponsable').textContent = "Responsable obligatorio.";
            esValido = false;
        } else if (/\d/.test(respValor)) {
            document.getElementById('errorResponsable').textContent = "El responsable no puede contener números.";
            esValido = false;
        }

        // Validaciones numéricas extra
        const expHoy = document.getElementById('expedientesHoy').value;
        if (expHoy === "") {
            document.getElementById('errorExpedientesHoy').textContent = "Obligatorio.";
            esValido = false;
        }

        const horas = document.getElementById('horasTrabajadas').value;
        if (horas === "" || horas < 1 || horas > 12) {
            document.getElementById('errorHoras').textContent = "Ingrese un valor entre 1 y 12.";
            esValido = false;
        }

        // Mensaje final
        const divResultado = document.getElementById('mensajeResultado');
        if (esValido) {
            divResultado.textContent = "Expediente validado correctamente";
            divResultado.style.color = "#38a169"; // Verde
            formularioValido = true; // Habilita el JSON
        } else {
            divResultado.textContent = "Existen errores en el formulario, revise los campos.";
            divResultado.style.color = "#e53e3e"; // Rojo
        }
    });

    // --- PUNTO 4) CALCULAR ESTADÍSTICA ---
    btnEstadistica.addEventListener('click', function() {
        const divEst = document.getElementById('resultadoEstadistica');
        const expGestionados = document.getElementById('expedientesHoy').value;
        const horas = document.getElementById('horasTrabajadas').value;

        if (expGestionados === "" || horas === "" || horas <= 0) {
            divEst.textContent = "Complete primero las horas y los expedientes gestionados.";
            divEst.style.color = "#e53e3e";
            return; 
        }

        const productividad = Number(expGestionados) / Number(horas);
        let nivel = "", msjAdicional = "";

        if (productividad < 2) nivel = "Productividad baja";
        else if (productividad >= 2 && productividad <= 5) nivel = "Productividad media";
        else {
            nivel = "Productividad alta";
            msjAdicional = " - Buen rendimiento";
        }

        divEst.textContent = `Productividad: ${productividad.toFixed(2)} → ${nivel}${msjAdicional}`;
        divEst.style.color = "#2b6cb0";
    });

    // --- PUNTO 5) GENERAR JSON ---
    btnJSON.addEventListener('click', function() {
        const preJSON = document.getElementById('resultadoJSON');

        if (!formularioValido) {
            preJSON.textContent = "Error: Valide correctamente el formulario antes de generar el JSON.";
            preJSON.style.color = "#e53e3e";
            preJSON.style.display = "block";
            return; 
        }

        // Tomar el estado seleccionado
        let estadoSel = "";
        const radios = document.getElementsByName('estado');
        for (let r of radios) { if (r.checked) estadoSel = r.value; }

        const expedienteObj = {
            numeroExpediente: document.getElementById('numExpediente').value,
            area: document.getElementById('area').value,
            tipoTramite: selectTramite.value,
            diasEnTramite: Number(document.getElementById('diasTramite').value),
            estado: estadoSel,
            responsable: document.getElementById('responsable').value,
            estadisticas: {
                gestionadosHoy: Number(document.getElementById('expedientesHoy').value),
                horasTrabajadas: Number(document.getElementById('horasTrabajadas').value)
            }
        };

        preJSON.textContent = JSON.stringify(expedienteObj, null, 4);
        preJSON.style.color = "#a0aec0";
        preJSON.style.display = "block";
    });
});