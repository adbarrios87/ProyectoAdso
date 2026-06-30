document.addEventListener('DOMContentLoaded', () => {
    const calificacionInputs = document.querySelectorAll('input[type="number"]');
    
    // Clamp inputs between 1 and 5
    calificacionInputs.forEach(input => {
        input.addEventListener('change', function() {
            let valor = parseInt(this.value);
            if (isNaN(valor) || valor < 1) this.value = 1;
            if (valor > 5) this.value = 5;
        });
    });

    const urlParams = new URLSearchParams(window.location.search);
    let providerId = urlParams.get('id');
    let idEvaluacion = urlParams.get('idEvaluacion');

    if (!idEvaluacion) {
        idEvaluacion = localStorage.getItem('selectedEvaluationId');
    }

    if (!providerId) {
        providerId = localStorage.getItem('selectedProviderId');
    }

    if (!providerId && !idEvaluacion) {
        alert("Error: No se ha seleccionado ningún proveedor para evaluar.");
        window.location.href = "buyer_supplier_list.html";
        return;
    }

    // Si se pasa idEvaluacion, cargar los datos de la evaluación para edición
    console.log("Detectado idEvaluacion en URL:", idEvaluacion);
    if (idEvaluacion) {
        cargarEvaluacionParaEdicion(idEvaluacion);
    }

    async function cargarEvaluacionParaEdicion(id) {
        try {
            console.log("Consultando detalle de evaluacion con ID:", id);
            const response = await fetch(`${CONFIG.API_BASE_URL}/evaluacion_proveedor/${id}`);
            const result = await response.json();
            console.log("Resultado de la consulta de evaluacion:", result);
            if (result.data) {
                const ev = result.data;
                providerId = ev.idProveedor; // Asegurarse de tener el providerId
                
                document.getElementById('score-calidad').value = ev.calidad || 5;
                document.getElementById('obs-calidad').value = ev.obsCalidad || '';
                document.getElementById('score-tiempo').value = ev.tiempo || 5;
                document.getElementById('obs-tiempo').value = ev.obsTiempo || '';
                document.getElementById('score-documenta').value = ev.documenta || 5;
                document.getElementById('obs-documental').value = ev.obsDocumental || '';
                document.getElementById('score-sarlaft').value = ev.sarlaft || 5;
                document.getElementById('obs-sarlaft').value = ev.obsSarlaft || '';
                document.getElementById('score-comercial').value = ev.comercial || 5;
                document.getElementById('obs-comercial').value = ev.obsComercial || '';
                document.getElementById('score-social').value = ev.social || 5;
                document.getElementById('obs-social').value = ev.obsSocial || '';
                document.getElementById('score-mejora').value = ev.mejora || 5;
                document.getElementById('obs-mejora').value = ev.obsMejora || '';
                document.getElementById('eval-observaciones').value = ev.observaciones || '';
            }
        } catch (err) {
            console.error("Error al cargar la evaluación:", err);
            alert("No se pudo cargar la información de la evaluación para editar.");
        }
    }

    const btnSave = document.getElementById('btn-save-evaluation');
    if (btnSave) {
        btnSave.addEventListener('click', async () => {
            const userId = localStorage.getItem('userId') ? parseInt(localStorage.getItem('userId')) : 2; // Default to 2 (Jefe de Compras)

            const payload = {
                idProveedor: parseInt(providerId),
                idUsuario: userId,
                calidad: parseInt(document.getElementById('score-calidad').value) || 5,
                obsCalidad: document.getElementById('obs-calidad').value,
                tiempo: parseInt(document.getElementById('score-tiempo').value) || 5,
                obsTiempo: document.getElementById('obs-tiempo').value,
                documenta: parseInt(document.getElementById('score-documenta').value) || 5,
                obsDocumental: document.getElementById('obs-documental').value,
                sarlaft: parseInt(document.getElementById('score-sarlaft').value) || 5,
                obsSarlaft: document.getElementById('obs-sarlaft').value,
                comercial: parseInt(document.getElementById('score-comercial').value) || 5,
                obsComercial: document.getElementById('obs-comercial').value,
                social: parseInt(document.getElementById('score-social').value) || 5,
                obsSocial: document.getElementById('obs-social').value,
                mejora: parseInt(document.getElementById('score-mejora').value) || 5,
                obsMejora: document.getElementById('obs-mejora').value,
                observaciones: document.getElementById('eval-observaciones').value,
                urlCalificacion: 's3://evals/calif_manual.pdf',
                creadoPor: userId,
                activo: true
            };

            try {
                btnSave.disabled = true;
                btnSave.textContent = 'Guardando...';

                const url = idEvaluacion 
                    ? `${CONFIG.API_BASE_URL}/evaluacion_proveedor/${idEvaluacion}`
                    : `${CONFIG.API_BASE_URL}/evaluacion_proveedor`;
                const method = idEvaluacion ? 'PUT' : 'POST';

                const response = await fetch(url, {
                    method: method,
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(payload)
                });

                const result = await response.json();
                if (response.ok) {
                    localStorage.removeItem('selectedEvaluationId');
                    alert('Evaluación guardada exitosamente en la base de datos.');
                    window.location.href = `buyer_supplier_profile.html?id=${providerId}`;
                } else {
                    throw new Error(result.message || 'Error al guardar la evaluación.');
                }
            } catch (error) {
                console.error('Error al guardar la evaluación:', error);
                alert('Ocurrió un error al guardar: ' + error.message);
                btnSave.disabled = false;
                btnSave.textContent = 'Guardar Evaluación';
            }
        });
    }
});
