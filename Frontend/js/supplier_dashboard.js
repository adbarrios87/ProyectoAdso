document.addEventListener('DOMContentLoaded', async () => {
    const docPendientes = document.querySelector('.card.kpi a');
    if (docPendientes) {
        docPendientes.addEventListener('click', (e) => {
            e.preventDefault();
            window.location.href = 'supplier_upload_documents.html';
        });
    }

    await cargarEstadoProveedor();
});

async function cargarEstadoProveedor() {
    const statusSpan = document.getElementById('profile-status');
    if (!statusSpan) return;

    const userEmail = localStorage.getItem('userEmail');
    if (!userEmail) {
        statusSpan.textContent = 'Sin sesión';
        return;
    }

    try {
        const response = await fetch(`${CONFIG.API_BASE_URL}/proveedores/by-email?email=${userEmail}`);
        const result = await response.json();

        if (result.data && result.data.idEstadoProveedor) {
            const statusResponse = await fetch(`${CONFIG.API_BASE_URL}/estado_proveedor/${result.data.idEstadoProveedor}`);
            const statusResult = await statusResponse.json();

            if (statusResult.data && statusResult.data.estado) {
                statusSpan.textContent = statusResult.data.estado;
            } else {
                statusSpan.textContent = 'Sin estado';
            }
        } else {
            statusSpan.textContent = 'Sin estado';
        }
    } catch (error) {
        console.error("Error al cargar el estado del proveedor:", error);
        statusSpan.textContent = 'Error al cargar';
    }
}
