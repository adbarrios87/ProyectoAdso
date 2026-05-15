document.addEventListener('DOMContentLoaded', async () => {
    await cargarDocumentosRequeridos();
});

async function cargarDocumentosRequeridos() {
    const userEmail = localStorage.getItem('userEmail');
    if (!userEmail) {
        console.error("No se encontró el correo del usuario en la sesión.");
        return;
    }

    try {
        // 1. Obtener datos del proveedor para saber su tipo de persona
        const provResponse = await fetch(`${CONFIG.API_BASE_URL}/proveedores/by-email?email=${userEmail}`);
        const provResult = await provResponse.json();
        
        if (!provResult.data) {
            console.error("No se encontró información del proveedor.");
            return;
        }

        const idTipoPersona = provResult.data.idTipoPersona;

        // 2. Obtener los tipos de documento para ese tipo de persona
        const docsResponse = await fetch(`${CONFIG.API_BASE_URL}/tipo_documento?idTipoPersona=${idTipoPersona}`);
        const docsResult = await docsResponse.json();

        if (docsResult.data) {
            renderizarTablaDocumentos(docsResult.data);
        }
    } catch (error) {
        console.error("Error cargando documentos requeridos:", error);
    }
}

function renderizarTablaDocumentos(documentos) {
    const tableBody = document.getElementById('documentsTableBody');
    if (!tableBody) return;

    tableBody.innerHTML = ''; // Limpiar tabla

    documentos.forEach(doc => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${doc.descripcion}</td>
            <td><input type="file" id="doc-${doc.idTipoDocumento}" accept=".pdf,.jpg,.png"></td>
            <td><span class="badge danger" id="status-${doc.idTipoDocumento}"><i class="fa-solid fa-circle-xmark"></i></span></td>
            <td><button class="btn" onclick="enviarDocumento('doc-${doc.idTipoDocumento}', ${doc.idTipoDocumento})">Enviar</button></td>
        `;
        tableBody.appendChild(tr);
    });
}

async function enviarDocumento(inputId, idTipoDocumento) {
    const input = document.getElementById(inputId);
    if (input && input.files && input.files.length > 0) {
        const file = input.files[0];
        alert('Simulando envío de: ' + file.name + ' (Tipo: ' + idTipoDocumento + ')');
        
        // Simular éxito visualmente
        const statusSpan = document.getElementById(`status-${idTipoDocumento}`);
        if (statusSpan) {
            statusSpan.className = 'badge success';
            statusSpan.innerHTML = '<i class="fa-solid fa-circle-check"></i>';
        }
    } else {
        alert('Por favor selecciona un archivo primero.');
    }
}

function enviarTodo() {
    const inputs = document.querySelectorAll('input[type="file"]');
    let hayArchivos = false;
    
    inputs.forEach(input => {
        if (input.files && input.files.length > 0) {
            hayArchivos = true;
        }
    });

    if (hayArchivos) {
        alert('Expediente completo enviado a revisión.');
        // Aquí se podría actualizar el estado del proveedor en el backend
    } else {
        alert('No has seleccionado ningún archivo nuevo.');
    }
}

// Global scope mapping
window.enviarDocumento = enviarDocumento;
window.enviarTodo = enviarTodo;
