function enviarDocumento(id) {
    const input = document.getElementById(id);
    if (input && input.files && input.files.length > 0) {
        alert('Enviando documento: ' + input.files[0].name);
        
        // Simular éxito visualmente cambiando la 'x' por un check verde
        const tdEstado = input.closest('tr').querySelectorAll('td')[2];
        if (tdEstado) {
            tdEstado.innerHTML = '<i class="fa-solid fa-check" style="color: green;"></i>';
        }
    } else {
        alert('Por favor selecciona un archivo primero para poder enviarlo.');
    }
}

function enviarTodo() {
    // Validar si al menos hay un archivo seleccionado
    const inputs = document.querySelectorAll('input[type="file"]');
    let hayArchivos = false;
    
    inputs.forEach(input => {
        if (input.files && input.files.length > 0) {
            hayArchivos = true;
            // Podríamos simular el envío de cada uno acá llamando la función enviarDocumento
        }
    });

    if (hayArchivos) {
        alert('Enviando todos los documentos seleccionados en lote...');
    } else {
        alert('No has seleccionado ningún archivo nuevo para enviar.');
    }
}

// Hacerlas accesibles en el scope global para los 'onclick' del HTML
window.enviarDocumento = enviarDocumento;
window.enviarTodo = enviarTodo;
