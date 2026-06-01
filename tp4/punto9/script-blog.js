// Nos aseguramos que el HTML haya cargado completamente
window.addEventListener('load', function() {
    
    // ==========================================
    // FUNCIÓN 1: Activar/Desactivar Modo Oscuro
    // ==========================================
    const btnModoOscuro = document.getElementById('btnModoOscuro');
    
    btnModoOscuro.addEventListener('click', function() {
        // La propiedad 'toggle' agrega la clase si no está, o la quita si ya está
        document.body.classList.toggle('modo-oscuro');
        
        // Cambiamos el texto del botón según el estado
        if (document.body.classList.contains('modo-oscuro')) {
            btnModoOscuro.textContent = "Desactivar Modo Oscuro";
        } else {
            btnModoOscuro.textContent = "Activar Modo Oscuro";
        }
    });

    // ==========================================
    // FUNCIÓN 2: Contar la cantidad de artículos
    // ==========================================
    const btnContar = document.getElementById('btnContar');
    
    btnContar.addEventListener('click', function() {
        // Usamos document.querySelectorAll para buscar todos los <article> de la clase 'articulo-blog'
        const articulos = document.querySelectorAll('.articulo-blog');
        
        // Contamos cuántos elementos encontró
        const cantidad = articulos.length;
        
        // Mostramos el resultado en una ventana emergente
        alert("El blog actualmente cuenta con " + cantidad + " artículos publicados.");
    });

});