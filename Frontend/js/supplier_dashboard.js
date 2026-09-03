document.addEventListener('DOMContentLoaded', async () => {
    await cargarDatosDashboard();
});

async function cargarDatosDashboard() {
    const statusSpan = document.getElementById('profile-status');
    const ratingSpan = document.getElementById('rating-value');
    const expirySpan = document.getElementById('expiry-value');

    const userEmail = localStorage.getItem('userEmail');
    const userId = localStorage.getItem('userId');
    if (!userId) {
        if (statusSpan) statusSpan.textContent = 'Sin sesión';
        if (ratingSpan) ratingSpan.textContent = 'No registra';
        if (expirySpan) expirySpan.textContent = 'No registra';
        return;
    }

    try {
        // 1. Obtener información básica del proveedor
        const response = await fetch(`${CONFIG.API_BASE_URL}/proveedores/by-userid?userId=${userId}`);
        const result = await response.json();

        if (result.data) {
            const proveedor = result.data;
            const idProveedor = proveedor.idProveedor;

            // --- Cargar Estado ---
            if (proveedor.idEstadoProveedor) {
                if (proveedor.idEstadoProveedor === 5 && window.inhabilitarBotonActualizarInformacion) {
                    window.inhabilitarBotonActualizarInformacion();
                }
                try {
                    const statusResponse = await fetch(`${CONFIG.API_BASE_URL}/estado_proveedor/${proveedor.idEstadoProveedor}`);
                    const statusResult = await statusResponse.json();
                    if (statusSpan) {
                        statusSpan.textContent = (statusResult.data && statusResult.data.estado) ? statusResult.data.estado : 'Sin estado';
                    }
                } catch (e) {
                    if (statusSpan) statusSpan.textContent = 'Sin estado';
                }
            } else {
                if (statusSpan) statusSpan.textContent = 'Sin estado';
            }

            // --- Cargar Próximo Vencimiento (1 año desde fecha de aprobación) ---
            if (expirySpan) {
                if (proveedor.fechaAprobacion) {
                    const fechaAprobacion = new Date(proveedor.fechaAprobacion);
                    const fechaVencimiento = new Date(fechaAprobacion);
                    fechaVencimiento.setFullYear(fechaVencimiento.getFullYear() + 1);

                    const hoy = new Date();
                    // Reiniciar horas para comparar solo fechas
                    hoy.setHours(0, 0, 0, 0);
                    fechaVencimiento.setHours(0, 0, 0, 0);

                    const diffTime = fechaVencimiento - hoy;
                    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

                    if (diffDays < 0) {
                        expirySpan.textContent = 'Vencido';
                    } else {
                        expirySpan.textContent = `${diffDays} días`;
                    }
                } else {
                    expirySpan.textContent = 'No registra';
                }
            }

            // --- Cargar Última Calificación (Evaluación) ---
            if (ratingSpan && idProveedor) {
                try {
                    const detailResponse = await fetch(`${CONFIG.API_BASE_URL}/proveedores/${idProveedor}/detalle-completo`);
                    const detailResult = await detailResponse.json();

                    if (detailResult.data && detailResult.data.evaluaciones && detailResult.data.evaluaciones.length > 0) {
                        // Ordenar de mayor a menor idEvaluacion para obtener la última
                        const evaluaciones = detailResult.data.evaluaciones;
                        evaluaciones.sort((a, b) => b.idEvaluacion - a.idEvaluacion);
                        
                        const ultimaEval = evaluaciones[0];
                        if (ultimaEval.puntaje != null) {
                            ratingSpan.textContent = `${ultimaEval.puntaje} / 5.0`;
                        } else {
                            ratingSpan.textContent = 'No registra';
                        }
                    } else {
                        ratingSpan.textContent = 'No registra';
                    }
                } catch (e) {
                    console.error("Error al cargar la calificación:", e);
                    ratingSpan.textContent = 'No registra';
                }
            }

            // --- Actualizar Avisos del Sistema según estado del proveedor ---
            actualizarAvisosSistema(proveedor);

        } else {
            if (statusSpan) statusSpan.textContent = 'Sin estado';
            if (ratingSpan) ratingSpan.textContent = 'No registra';
            if (expirySpan) expirySpan.textContent = 'No registra';
            actualizarAvisosSistema(null);
        }
    } catch (error) {
        console.error("Error al cargar los datos del dashboard:", error);
        if (statusSpan) statusSpan.textContent = 'Error al cargar';
        if (ratingSpan) ratingSpan.textContent = 'No registra';
        if (expirySpan) expirySpan.textContent = 'No registra';
    }
}

function actualizarAvisosSistema(proveedor) {
    const container = document.getElementById('dashboard-alert-container');
    if (!container) return;

    if (proveedor && proveedor.idEstadoProveedor === 6) {
        container.innerHTML = `
            <div class="alert-item success" style="background-color: #ecfdf5; border-left: 4px solid #059669; padding: 16px; border-radius: 8px; display: flex; gap: 14px; align-items: flex-start;">
                <i class="fa-solid fa-circle-check" style="color: #059669; font-size: 24px; margin-top: 2px;"></i>
                <div class="alert-content">
                    <strong style="color: #065f46; font-size: 15px;">¡Expediente Aprobado!</strong>
                    <p style="margin-top: 6px; font-size: 13px; color: #1e293b; line-height: 1.5;">
                        Tu registro como proveedor ha sido verificado y aprobado satisfactoriamente por el Oficial de Cumplimiento. Tu vinculación se encuentra activa y vigente.
                    </p>
                </div>
            </div>
        `;
    } else if (proveedor && proveedor.idEstadoProveedor === 5) {
        container.innerHTML = `
            <div class="alert-item info" style="background-color: #eff6ff; border-left: 4px solid #3b82f6; padding: 16px; border-radius: 8px; display: flex; gap: 14px; align-items: flex-start;">
                <i class="fa-solid fa-clock" style="color: #3b82f6; font-size: 24px; margin-top: 2px;"></i>
                <div class="alert-content">
                    <strong style="color: #1e40af; font-size: 15px;">Expediente en Revisión</strong>
                    <p style="margin-top: 6px; font-size: 13px; color: #1e293b; line-height: 1.5;">
                        Tu información y documentos se encuentran actualmente en proceso de revisión por parte del equipo de Compras y Cumplimiento. Te notificaremos una vez finalice la evaluación.
                    </p>
                </div>
            </div>
        `;
    }
}
