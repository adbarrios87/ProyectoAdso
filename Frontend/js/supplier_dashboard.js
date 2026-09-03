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

    const estadoId = proveedor ? proveedor.idEstadoProveedor : 4;

    if (estadoId === 6 || estadoId === 11) {
        // --- Estado 6 (Aprobado) y 11 (Aprobado con novedad) ---
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
    } else if (estadoId === 5 || estadoId === 9 || estadoId === 10) {
        // --- Estados intermedios 5, 9, 10 (En revisión / En proceso) ---
        container.innerHTML = `
            <div class="alert-item info" style="background-color: #eff6ff; border-left: 4px solid #3b82f6; padding: 16px; border-radius: 8px; display: flex; gap: 14px; align-items: flex-start;">
                <i class="fa-solid fa-clock" style="color: #3b82f6; font-size: 24px; margin-top: 2px;"></i>
                <div class="alert-content">
                    <strong style="color: #1e40af; font-size: 15px;">Expediente en Revisión</strong>
                    <p style="margin-top: 6px; font-size: 13px; color: #1e293b; line-height: 1.5;">
                        Tu documentación se encuentra actualmente en proceso de revisión por parte del equipo de Compras y Cumplimiento. Te notificaremos una vez finalice la evaluación.
                    </p>
                </div>
            </div>
        `;
    } else if (estadoId === 7 || estadoId === 8) {
        // --- Estados 7 y 8 (Rechazado) ---
        container.innerHTML = `
            <div class="alert-item danger" style="background-color: #fef2f2; border-left: 4px solid #ef4444; padding: 16px; border-radius: 8px; display: flex; gap: 14px; align-items: flex-start;">
                <i class="fa-solid fa-circle-xmark" style="color: #ef4444; font-size: 24px; margin-top: 2px;"></i>
                <div class="alert-content">
                    <strong style="color: #991b1b; font-size: 15px;">Expediente Rechazado</strong>
                    <p style="margin-top: 6px; font-size: 13px; color: #1e293b; line-height: 1.5;">
                        Tu registro de proveedor no ha sido aprobado tras la revisión de cumplimiento. Puedes comunicarte con el área encargada para mayor información.
                    </p>
                </div>
            </div>
        `;
    } else if (estadoId === 12) {
        // --- Estado 12 (Devuelto con observaciones) ---
        container.innerHTML = `
            <div class="alert-item warning" style="background-color: #fffbeb; border-left: 4px solid #f59e0b; padding: 16px; border-radius: 8px; display: flex; gap: 14px; align-items: flex-start;">
                <i class="fa-solid fa-triangle-exclamation" style="color: #f59e0b; font-size: 24px; margin-top: 2px;"></i>
                <div class="alert-content">
                    <strong style="color: #92400e; font-size: 15px;">Expediente Devuelto con Observaciones</strong>
                    <p style="margin-top: 6px; font-size: 13px; color: #1e293b; line-height: 1.5;">
                        El equipo de revisión ha devuelto tu expediente con observaciones. Por favor dirígete a "Actualizar información" para subsanar los puntos indicados.
                    </p>
                </div>
            </div>
        `;
    } else {
        // --- Estado 4 (Sin documentos) o registro inicial ---
        container.innerHTML = `
            <div class="alert-item warning" style="background-color: #fffbeb; border-left: 4px solid #f59e0b; padding: 16px; border-radius: 8px; display: flex; gap: 14px; align-items: flex-start;">
                <i class="fa-solid fa-triangle-exclamation" style="color: #f59e0b; font-size: 24px; margin-top: 2px;"></i>
                <div class="alert-content">
                    <strong style="color: #92400e; font-size: 15px;">Inicia tu proceso...</strong>
                    <p style="margin-top: 6px; font-size: 13px; color: #1e293b; line-height: 1.5;">
                        Para iniciar el proceso de creación como proveedor, ten a la mano los siguientes documentos en formato PDF:<br><br>
                        * Certificado de existencia y representación legal (menor a 90 días)<br>
                        * Registro Único Tributario (RUT)<br>
                        * Cédula de ciudadanía del representante legal<br>
                        * Certificación bancaria (menor a 30 días)<br>
                        * Referencia comercial (menor a 90 días)<br>
                    </p>
                </div>
            </div>
        `;
    }
}

