let allDecisions = [];
document.addEventListener('DOMContentLoaded', () => {
    cargarDecisiones();

    const searchInput = document.getElementById('history-search-input');
    if (searchInput) { //Comprueba que el elemento de búsqueda realmente exista en el HTML
        searchInput.addEventListener('input', () => {
            filtrarDecisiones();
        });
    }
});

async function cargarDecisiones() {
    const tbody = document.getElementById('decisionsBody');
    const noDecisions = document.getElementById('noDecisions');

    if (!tbody) return;

    try {
        // Obtener las validaciones finales y los proveedores
        const [valRes, provRes] = await Promise.all([
            fetch(`${CONFIG.API_BASE_URL}/validacion-final`),
            fetch(`${CONFIG.API_BASE_URL}/proveedores`)
        ]);

        if (!valRes.ok || !provRes.ok) {
            throw new Error('Error al cargar datos del historial desde el servidor');
        }

        const [validations, suppliers] = await Promise.all([
            valRes.json().then(r => r.data || []),
            provRes.json().then(r => r.data || [])
        ]);

        const supplierMap = new Map(suppliers.map(s => [
            s.idProveedor,
            {
                nombre: s.razonSocial || `${s.nombres || ''} ${s.apellidos || ''}`.trim() || 'Sin Nombre',
                nit: s.numeroIdentificacion || 'Sin NIT'
            }
        ]));

        // Filtrado por los estados de cumplimiento 11 y 12
        allDecisions = validations
            .filter(v => v.estadoValidacion === '11' || v.estadoValidacion === '12')
            .map(v => {
                const prov = supplierMap.get(v.idProveedor) || { nombre: `Proveedor #${v.idProveedor}`, nit: '-' };
                const decisionText = (v.estadoValidacion === '11') ? 'Aprobado' : 'Rechazado';
                return {
                    id: v.idValidacionFinal,
                    proveedor: prov.nombre,
                    nit: prov.nit,
                    fecha: v.fechaCreado ? new Date(v.fechaCreado).toLocaleDateString('es-CO') : '-',
                    rawFecha: v.fechaCreado ? new Date(v.fechaCreado) : null,
                    decision: decisionText,
                    justificacion: v.comentarioFinal || 'Sin observaciones.'
                };
            });

        // Actualizar KPIs 
        const kpiApproved = document.getElementById('kpi-approved');
        const kpiRejected = document.getElementById('kpi-rejected');
        const kpiAlerts = document.getElementById('kpi-alerts');

        if (kpiApproved) {
            const approvedCount = validations.filter(v => v.estadoValidacion === '11').length;
            kpiApproved.textContent = approvedCount;
        }
        if (kpiRejected) {
            const rejectedCount = validations.filter(v => v.estadoValidacion === '12').length;
            kpiRejected.textContent = rejectedCount;
        }
        if (kpiAlerts) {
            const alertsCount = suppliers.filter(s => s.idEstadoProveedor === 8 || s.idEstadoProveedor === 10).length;
            kpiAlerts.textContent = alertsCount;
        }

        filtrarDecisiones(); // Renderizar con filtros aplicados

    } catch (error) {
        console.error(error);
        tbody.innerHTML = `<tr><td colspan="6" style="text-align:center;color:red;"> Error al cargar el historial desde el servidor.</td></tr>`;
    }
}
// Propósito es leer los valores actuales de todos los controles de filtro de la pantalla y preparar una copia de los datos de los proveedores
function filtrarDecisiones() {
    const queryInput = document.getElementById('history-search-input');
    const query = queryInput ? queryInput.value.toLowerCase().trim() : '';
    const fechaDesde = document.getElementById('filterDateFrom').value;
    const fechaHasta = document.getElementById('filterDateTo').value;

    let filtradas = [...allDecisions];

    // Filtro busqueda general
    if (query) {
        filtradas = filtradas.filter(d =>
            d.proveedor.toLowerCase().includes(query) ||
            d.nit.toLowerCase().includes(query) ||
            d.decision.toLowerCase().includes(query)
        );
    }

    // Filtro por fechas
    if (fechaDesde) {
        const fromDate = new Date(fechaDesde);
        fromDate.setHours(0, 0, 0, 0);
        filtradas = filtradas.filter(d => d.rawFecha && d.rawFecha >= fromDate);
    }

    if (fechaHasta) {
        const toDate = new Date(fechaHasta);
        toDate.setHours(23, 59, 59, 999);
        filtradas = filtradas.filter(d => d.rawFecha && d.rawFecha <= toDate);
    }

    actualizarTabla(filtradas);
}

function actualizarTabla(decisiones) {
    const tbody = document.getElementById('decisionsBody'); // se insertan las filas de los proveedores
    const noDecisions = document.getElementById('noDecisions');

    if (!tbody) return; // si no existe en el HTML, la función se detiene inmediatamente para evitar fallos de ejecución

    if (decisiones.length === 0) {
        tbody.innerHTML = '';
        if (noDecisions) noDecisions.style.display = 'block';
        return;
    }

    if (noDecisions) noDecisions.style.display = 'none';

    // De las mas recientes a las mas antiguas
    const decisionesOrdenadas = [...decisiones].reverse(); // Ordenar de más reciente a más antiguo
    tbody.innerHTML = decisionesOrdenadas.map((dec, index) => {
        const originalIndex = decisiones.length - 1 - index;
        return `
            <tr>
                <td>${decisiones.length - index}</td>
                <td>${dec.fecha}</td>
                <td><strong>${dec.proveedor}</strong></td>
                <td>${dec.nit}</td>
                <td>
                    <span class="decision-badge ${dec.decision.toLowerCase()}"> ${dec.decision}</span>
                </td>
                <td>
                    <div class="actions-cell">
                        <button class="icon-btn" onclick="verDetalle(${originalIndex})" title="Ver detalle"><i class="fa-solid fa-eye"></i></button>
                    </div>
                </td>
            </tr>
        `;
    }).join('');
}

function verDetalle(index) {
    // Abrir un modal con la información y justificación completas de un proveedor
    const queryInput = document.getElementById('history-search-input');
    const query = queryInput ? queryInput.value.toLowerCase().trim() : '';
    const fechaDesde = document.getElementById('filterDateFrom').value;
    const fechaHasta = document.getElementById('filterDateTo').value;

    let currentFiltered = [...allDecisions];

    // Filter by query (Live Search: Proveedor, NIT, Decision/Estado)
    if (query) {
        currentFiltered = currentFiltered.filter(d =>
            d.proveedor.toLowerCase().includes(query) ||
            d.nit.toLowerCase().includes(query) ||
            d.decision.toLowerCase().includes(query)
        );
    }
    if (fechaDesde) {
        const fromDate = new Date(fechaDesde);
        fromDate.setHours(0, 0, 0, 0);
        currentFiltered = currentFiltered.filter(d => d.rawFecha && d.rawFecha >= fromDate);
    }
    if (fechaHasta) {
        const toDate = new Date(fechaHasta);
        toDate.setHours(23, 59, 59, 999);
        currentFiltered = currentFiltered.filter(d => d.rawFecha && d.rawFecha <= toDate);
    }

    const decision = currentFiltered[index];
    if (!decision) return;

    const modal = document.getElementById('modalDetail') || createModal();
    modal.classList.add('active');

    document.getElementById('detailProveedor').textContent = decision.proveedor;
    document.getElementById('detailNit').textContent = decision.nit;
    document.getElementById('detailFecha').textContent = decision.fecha;
    document.getElementById('detailDecision').textContent = decision.decision;
    document.getElementById('detailJustificacion').textContent = decision.justificacion;

    // Actualización visual del badge en el detalle
    const badge = document.getElementById('detailDecision');
    if (badge) {
        badge.className = `decision-badge ${decision.decision.toLowerCase()}`;
    }
}
// creación dinámica del contenedor del modal en el HTML
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
    document.body.appendChild(modal); // se inserta el modal creado dentro del cuerpo del HTML

    // Cierre al hacer clic fuera del modal
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
// limpiar los filtros
function limpiarFiltros() {
    document.getElementById('history-search-input').value = '';
    document.getElementById('filterDateFrom').value = '';
    document.getElementById('filterDateTo').value = '';
    filtrarDecisiones();
}