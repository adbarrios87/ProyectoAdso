document.addEventListener('DOMContentLoaded', () => {
    // Solo insertar datos de prueba si no hay decisiones registradas
    const decisionesExistentes = JSON.parse(localStorage.getItem('decisionesOficialCumplimiento') || '[]');
    if (decisionesExistentes.length === 0) {
        insertarDatosPrueba();
    }
    cargarDecisiones();
});

function insertarDatosPrueba() {
    const decisionesPrueba = [
        {
            proveedor: 'C.R. CAFETO',
            nit: '900123456-7',
            fecha: '28/04/2026',
            decision: 'Aprobado',
            justificacion: 'Proveedor con excelente trayectoria, todos los documentos en regla y validaciones positivas en listas restrictivas.'
        },
        {
            proveedor: 'Distribuciones López S.A.S.',
            nit: '800234567-1',
            fecha: '25/04/2026',
            decision: 'Aprobado',
            justificacion: 'Cumple con todos los requisitos de calidad y normatividad. Sin antecedentes negativos.'
        },
        {
            proveedor: 'Insumos Técnicos Colombia',
            nit: '901234567-8',
            fecha: '24/04/2026',
            decision: 'Rechazado',
            justificacion: 'Presenta inconsistencias en estados financieros y antecedentes judiciales pendientes.'
        },
        {
            proveedor: 'Servicios Integrales Andinos',
            nit: '830456789-2',
            fecha: '23/04/2026',
            decision: 'Aprobado',
            justificacion: 'Documentación completa, referencias comerciales verificadas y evaluación de riesgo baja.'
        },
        {
            proveedor: 'Comercializadora del Valle',
            nit: '890567890-5',
            fecha: '22/04/2026',
            decision: 'Aprobado',
            justificacion: 'Proveedor estratégico de larga data con excelente cumplimiento en entregas.'
        },
        {
            proveedor: 'Logística Express Nacional',
            nit: '900678901-3',
            fecha: '21/04/2026',
            decision: 'Rechazado',
            justificacion: 'Coincidencia en lista de personas restringidas de la ONU. Requiere verificación adicional.'
        },
        {
            proveedor: 'Industrias Metálicas del Norte',
            nit: '850789012-6',
            fecha: '20/04/2026',
            decision: 'Aprobado',
            justificacion: 'Certificaciones ISO vigentes, sin hallazgos en auditoría previa.'
        },
        {
            proveedor: 'Suministros Empresariales',
            nit: '830890123-7',
            fecha: '19/04/2026',
            decision: 'Aprobado',
            justificacion: 'Cumple con políticas de sostenibilidad y responsabilidad social empresarial.'
        },
        {
            proveedor: 'Constructora Horizonte',
            nit: '900901234-9',
            fecha: '18/04/2026',
            decision: 'Rechazado',
            justificacion: 'Documentación incompleta y falta de respuesta a requerimientos de información.'
        },
        {
            proveedor: 'Tecnología Avanzada SAS',
            nit: '901012345-4',
            fecha: '17/04/2026',
            decision: 'Aprobado',
            justificacion: 'Evaluación técnica satisfactoria, referencias de otros clientes corporativos positivas.'
        }
    ];
    
    localStorage.setItem('decisionesOficialCumplimiento', JSON.stringify(decisionesPrueba));
}

function cargarDecisiones() {
    const decisiones = JSON.parse(localStorage.getItem('decisionesOficialCumplimiento') || '[]');
    const tbody = document.getElementById('decisionsBody');
    const noDecisions = document.getElementById('noDecisions');
    
    // Actualizar contadores
    const aprobados = decisiones.filter(d => d.decision === 'Aprobado').length;
    const rechazados = decisiones.filter(d => d.decision === 'Rechazado').length;
    
    document.getElementById('totalApproved').textContent = aprobados;
    document.getElementById('totalRejected').textContent = rechazados;
    document.getElementById('totalDecisions').textContent = decisiones.length;

    if (decisiones.length === 0) {
        tbody.innerHTML = '';
        noDecisions.style.display = 'block';
        return;
    }

    noDecisions.style.display = 'none';
    
    // Mostrar decisiones en orden inverso (más recientes primero)
    const decisionesOrdenadas = [...decisiones].reverse();
    
    tbody.innerHTML = decisionesOrdenadas.map((dec, index) => `
        <tr data-decision='${JSON.stringify(dec).replace(/'/g, "&#39;")}'>
            <td>${decisionesOrdenadas.length - index}</td>
            <td>${dec.fecha}</td>
            <td><strong>${dec.proveedor}</strong></td>
            <td>${dec.nit}</td>
            <td>
                <span class="decision-badge ${dec.decision.toLowerCase()}">
                    <i class="fa-solid fa-${dec.decision === 'Aprobado' ? 'check' : 'xmark'}"></i>
                    ${dec.decision}
                </span>
            </td>
            <td>
                <span class="justification-cell" onclick="verDetalle(${decisiones.length - index - 1})" title="${dec.justificacion}">
                    ${dec.justificacion.length > 50 ? dec.justificacion.substring(0, 50) + '...' : dec.justificacion}
                </span>
            </td>
            <td>
                <div class="actions-cell">
                    <button class="icon-btn view" onclick="verDetalle(${decisiones.length - index - 1})" title="Ver detalle">
                        <i class="fa-solid fa-eye"></i>
                    </button>
                </div>
            </td>
        </tr>
    `).join('');
}

function verDetalle(index) {
    const decisiones = JSON.parse(localStorage.getItem('decisionesOficialCumplimiento') || '[]');
    const decision = decisiones[index];
    
    if (!decision) return;

    const modal = document.getElementById('modalDetail') || createModal();
    modal.classList.add('active');
    
    document.getElementById('detailProveedor').textContent = decision.proveedor;
    document.getElementById('detailNit').textContent = decision.nit;
    document.getElementById('detailFecha').textContent = decision.fecha;
    document.getElementById('detailDecision').textContent = decision.decision;
    document.getElementById('detailJustificacion').textContent = decision.justificacion;
    
    // Actualizar clase del badge
    const badge = document.getElementById('detailDecision').parentElement;
    badge.className = `decision-badge ${decision.decision.toLowerCase()}`;
}

function createModal() {
    const modal = document.createElement('div');
    modal.id = 'modalDetail';
    modal.className = 'modal-detail';
    modal.innerHTML = `
        <div class="modal-content">
            <button class="modal-close" onclick="cerrarModal()">&times;</button>
            <h2><i class="fa-solid fa-file-signature"></i> Detalle de Decisión</h2>
            <div class="detail-row">
                <span class="detail-label">Proveedor:</span>
                <span class="detail-value" id="detailProveedor"></span>
            </div>
            <div class="detail-row">
                <span class="detail-label">NIT:</span>
                <span class="detail-value" id="detailNit"></span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Fecha:</span>
                <span class="detail-value" id="detailFecha"></span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Decisión:</span>
                <span class="detail-value"><span id="detailDecision" class="decision-badge"></span></span>
            </div>
            <div class="detail-row" style="flex-direction: column; align-items: flex-start;">
                <span class="detail-label" style="margin-bottom: 8px;">Justificación:</span>
                <span class="detail-value" id="detailJustificacion" style="text-align: left; max-width: 100%; white-space: normal;"></span>
            </div>
        </div>
    `;
    document.body.appendChild(modal);
    
    // Cerrar al hacer clic fuera
    modal.addEventListener('click', (e) => {
        if (e.target === modal) {
            cerrarModal();
        }
    });
    
    return modal;
}

function cerrarModal() {
    const modal = document.getElementById('modalDetail');
    if (modal) {
        modal.classList.remove('active');
    }
}

function filtrarDecisiones() {
    const estado = document.getElementById('filterStatus').value;
    const fechaDesde = document.getElementById('filterDateFrom').value;
    const fechaHasta = document.getElementById('filterDateTo').value;
    
    const decisiones = JSON.parse(localStorage.getItem('decisionesOficialCumplimiento') || '[]');
    let filtradas = [...decisiones];

    // Filtrar por estado
    if (estado !== 'todos') {
        filtradas = filtradas.filter(d => d.decision === estado);
    }

    // Filtrar por rango de fechas
    if (fechaDesde) {
        filtradas = filtradas.filter(d => {
            const fechaDec = new Date(d.fecha.split('/').reverse().join('-'));
            return fechaDec >= new Date(fechaDesde);
        });
    }

    if (fechaHasta) {
        filtradas = filtradas.filter(d => {
            const fechaDec = new Date(d.fecha.split('/').reverse().join('-'));
            return fechaDec <= new Date(fechaHasta);
        });
    }

    actualizarTabla(filtradas);
}

function actualizarTabla(decisiones) {
    const tbody = document.getElementById('decisionsBody');
    const noDecisions = document.getElementById('noDecisions');

    if (decisiones.length === 0) {
        tbody.innerHTML = '';
        noDecisions.style.display = 'block';
        return;
    }

    noDecisions.style.display = 'none';
    
    const decisionesOrdenadas = [...decisiones].reverse();
    
    tbody.innerHTML = decisionesOrdenadas.map((dec, index) => {
        const originalIndex = decisiones.length - 1 - index;
        return `
            <tr>
                <td>${decisiones.length - index}</td>
                <td>${dec.fecha}</td>
                <td><strong>${dec.proveedor}</strong></td>
                <td>${dec.nit}</td>
                <td>
                    <span class="decision-badge ${dec.decision.toLowerCase()}">
                        <i class="fa-solid fa-${dec.decision === 'Aprobado' ? 'check' : 'xmark'}"></i>
                        ${dec.decision}
                    </span>
                </td>
                <td>
                    <span class="justification-cell" onclick="verDetalle(${originalIndex})" title="${dec.justificacion}">
                        ${dec.justificacion.length > 50 ? dec.justificacion.substring(0, 50) + '...' : dec.justificacion}
                    </span>
                </td>
                <td>
                    <div class="actions-cell">
                        <button class="icon-btn view" onclick="verDetalle(${originalIndex})" title="Ver detalle">
                            <i class="fa-solid fa-eye"></i>
                        </button>
                    </div>
                </td>
            </tr>
        `;
    }).join('');
}

function limpiarFiltros() {
    document.getElementById('filterStatus').value = 'todos';
    document.getElementById('filterDateFrom').value = '';
    document.getElementById('filterDateTo').value = '';
    cargarDecisiones();
}