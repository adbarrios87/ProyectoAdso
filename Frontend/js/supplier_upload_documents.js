let currentProveedorId = null;

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
        // 1. Obtener datos del proveedor para saber su tipo de persona e idProveedor
        const provResponse = await fetch(`${CONFIG.API_BASE_URL}/proveedores/by-email?email=${userEmail}`);
        const provResult = await provResponse.json();
        
        if (!provResult.data) {
            console.error("No se encontró información del proveedor.");
            return;
        }

        currentProveedorId = provResult.data.idProveedor;
        const idTipoPersona = provResult.data.idTipoPersona;

        // Cargar estado de registro dinámico
        if (provResult.data.idEstadoProveedor) {
            try {
                const statusResponse = await fetch(`${CONFIG.API_BASE_URL}/estado_proveedor/${provResult.data.idEstadoProveedor}`);
                const statusResult = await statusResponse.json();
                const statusSpan = document.getElementById('registration-status');
                if (statusSpan && statusResult.data && statusResult.data.estado) {
                    statusSpan.textContent = statusResult.data.estado.toUpperCase();
                }
            } catch (err) {
                console.warn("No se pudo cargar el nombre del estado:", err);
            }
        }

        // 2. Obtener los tipos de documento para ese tipo de persona
        const docsResponse = await fetch(`${CONFIG.API_BASE_URL}/tipo_documento?idTipoPersona=${idTipoPersona}`);
        const docsResult = await docsResponse.json();

        // 3. Obtener los documentos que el proveedor ya ha cargado
        let uploadedDocuments = [];
        try {
            const uploadedResponse = await fetch(`${CONFIG.API_BASE_URL}/documentos/proveedor/${currentProveedorId}`);
            const uploadedResult = await uploadedResponse.json();
            if (uploadedResult.data) {
                uploadedDocuments = uploadedResult.data;
            }
        } catch (err) {
            console.warn("No se pudieron cargar los documentos existentes:", err);
        }

        if (docsResult.data) {
            renderizarTablaDocumentos(docsResult.data, uploadedDocuments);
        }
    } catch (error) {
        console.error("Error cargando documentos requeridos:", error);
    }
}

function renderizarTablaDocumentos(documentos, documentosCargados) {
    const tableBody = document.getElementById('documentsTableBody');
    if (!tableBody) return;

    tableBody.innerHTML = ''; // Limpiar tabla

    documentos.forEach(doc => {
        // Buscar si este tipo de documento ya está cargado y activo
        const cargado = documentosCargados.find(ud => ud.idTipoDocumento === doc.idTipoDocumento && ud.estadoDocumento === true);
        
        const badgeClass = cargado ? 'badge success' : 'badge danger';
        const badgeIcon = cargado ? '<i class="fa-solid fa-circle-check"></i>' : '<i class="fa-solid fa-circle-xmark"></i>';

        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${doc.descripcion}</td>
            <td><input type="file" id="doc-${doc.idTipoDocumento}" accept=".pdf,.jpg,.png"></td>
            <td><span class="${badgeClass}" id="status-${doc.idTipoDocumento}">${badgeIcon}</span></td>
            <td><button class="btn" id="btn-${doc.idTipoDocumento}" onclick="enviarDocumento('doc-${doc.idTipoDocumento}', ${doc.idTipoDocumento})">Enviar</button></td>
        `;
        tableBody.appendChild(tr);
    });
}

async function enviarDocumento(inputId, idTipoDocumento) {
    if (!currentProveedorId) {
        alert('Error: No se ha cargado el ID del proveedor.');
        return;
    }

    const input = document.getElementById(inputId);
    if (!input || !input.files || input.files.length === 0) {
        alert('Por favor selecciona un archivo primero.');
        return;
    }

    const file = input.files[0];
    const btn = document.getElementById(`btn-${idTipoDocumento}`);
    const originalText = btn ? btn.textContent : 'Enviar';

    // Deshabilitar botón para evitar doble envío
    if (btn) {
        btn.disabled = true;
        btn.textContent = 'Enviando...';
    }

    const formData = new FormData();
    formData.append('file', file);
    formData.append('idProveedor', currentProveedorId);
    formData.append('idTipoDocumento', idTipoDocumento);
    formData.append('creadoPor', localStorage.getItem('userId') || 1);

    try {
        const response = await fetch(`${CONFIG.API_BASE_URL}/documentos/upload`, {
            method: 'POST',
            body: formData
        });

        const result = await response.json();

        if (response.ok && result.data) {
            alert('Documento cargado correctamente.');
            // Actualizar éxito visualmente
            const statusSpan = document.getElementById(`status-${idTipoDocumento}`);
            if (statusSpan) {
                statusSpan.className = 'badge success';
                statusSpan.innerHTML = '<i class="fa-solid fa-circle-check"></i>';
            }
            // Limpiar el input file
            input.value = '';
        } else {
            alert('Error al subir el documento: ' + (result.message || 'Error en el servidor'));
        }
    } catch (error) {
        console.error("Error en la petición de carga:", error);
        alert('Error de red al intentar subir el documento.');
    } finally {
        if (btn) {
            btn.disabled = false;
            btn.textContent = originalText;
        }
    }
}

function enviarTodo() {
    alert('Expediente completo enviado a revisión.');
}

// Global scope mapping
window.enviarDocumento = enviarDocumento;
window.enviarTodo = enviarTodo;
