document.addEventListener('DOMContentLoaded', async () => {
    const tableBody = document.getElementById('resultados');
    if (!tableBody) return;

    // 1. Obtener estados de proveedores
    let estadosMap = {};
    try {
        const response = await fetch(`${CONFIG.API_BASE_URL}/estado_proveedor`);
        const result = await response.json();
        if (result.data) {
            result.data.forEach(est => {
                estadosMap[est.idEstadoProveedor] = est.descripcion;
            });
        }
    } catch (error) {
        console.error("Error cargando estados:", error);
    }

    // 2. Obtener municipios para la columna Ciudad
    let municipiosMap = {};
    try {
        const response = await fetch(`${CONFIG.API_BASE_URL}/municipio`);
        const result = await response.json();
        if (result.data) {
            result.data.forEach(m => {
                municipiosMap[m.idMunicipio] = m.nombre;
            });
        }
    } catch (error) {
        console.error("Error cargando municipios:", error);
    }

    // 3. Obtener ubicaciones para mapear proveedor -> dirección/municipio
    let ubicacionesMap = {};
    try {
        const response = await fetch(`${CONFIG.API_BASE_URL}/ubicacion`);
        const result = await response.json();
        if (result.data) {
            result.data.forEach(ub => {
                ubicacionesMap[ub.idProveedor] = {
                    direccion: ub.direccion || 'No registrada',
                    ciudad: municipiosMap[ub.idMunicipio] || 'No registrada'
                };
            });
        }
    } catch (error) {
        console.error("Error cargando ubicaciones:", error);
    }

    // 4. Obtener contactos principales de proveedores
    let contactosMap = {};
    try {
        // Obtenemos relaciones proveedor_contacto
        const pcResponse = await fetch(`${CONFIG.API_BASE_URL}/proveedor_contacto`);
        const pcResult = await pcResponse.json();
        // Obtenemos la lista de contactos
        const cResponse = await fetch(`${CONFIG.API_BASE_URL}/contacto`);
        const cResult = await cResponse.json();

        if (pcResult.data && cResult.data) {
            const contactosListMap = {};
            cResult.data.forEach(c => {
                contactosListMap[c.idContacto] = c.nombreContacto;
            });
            pcResult.data.forEach(pc => {
                if (pc.activo) {
                    contactosMap[pc.idProveedor] = contactosListMap[pc.idContacto] || 'No registrado';
                }
            });
        }
    } catch (error) {
        console.error("Error cargando contactos:", error);
    }

    // 5. Obtener calificaciones (Confiable, Alternativo, No confiable)
    let calificacionMap = {};
    try {
        const response = await fetch(`${CONFIG.API_BASE_URL}/calificacion`);
        const result = await response.json();
        if (result.data) {
            result.data.forEach(c => {
                calificacionMap[c.idCalificacion] = c.descripcion;
            });
        }
    } catch (error) {
        console.error("Error cargando calificaciones:", error);
    }

    // 6. Obtener evaluaciones para buscar la más reciente por proveedor
    let ultimasEvaluacionesMap = {};
    try {
        const response = await fetch(`${CONFIG.API_BASE_URL}/evaluacion_proveedor`);
        const result = await response.json();
        if (result.data) {
            result.data.forEach(eval => {
                const provId = eval.idProveedor;
                const existing = ultimasEvaluacionesMap[provId];
                if (!existing || new Date(eval.fechaCreado) > new Date(existing.fechaCreado)) {
                    ultimasEvaluacionesMap[provId] = eval;
                }
            });
        }
    } catch (error) {
        console.error("Error cargando evaluaciones:", error);
    }

    // 7. Obtener proveedores
    try {
        tableBody.innerHTML = '<tr><td colspan="8" style="text-align:center;">Cargando proveedores...</td></tr>';
        const response = await fetch(`${CONFIG.API_BASE_URL}/proveedores`);
        const result = await response.json();

        if (result.data && Array.isArray(result.data)) {
            renderizarTablaProveedores(result.data, estadosMap, ubicacionesMap, contactosMap, calificacionMap, ultimasEvaluacionesMap);
        } else {
            tableBody.innerHTML = '<tr><td colspan="8" style="text-align:center;">No se encontraron proveedores.</td></tr>';
        }
    } catch (error) {
        console.error("Error cargando proveedores:", error);
        tableBody.innerHTML = '<tr><td colspan="8" style="text-align:center;">Error al conectar con el servidor.</td></tr>';
    }
});

function renderizarTablaProveedores(proveedores, estadosMap, ubicacionesMap, contactosMap, calificacionMap, ultimasEvaluacionesMap) {
    const tableBody = document.getElementById('resultados');
    if (!tableBody) return;

    let html = '';
    proveedores.forEach(prov => {
        const id = prov.idProveedor;
        const nit = prov.numeroIdentificacion || 'Sin NIT';
        const razonSocial = prov.razonSocial || `${prov.nombres || ''} ${prov.apellidos || ''}`.trim() || 'Sin Nombre';

        // Contacto
        const contacto = contactosMap[id] || prov.correoPrincipal || 'No registrado';

        // Estado y Badge Clase
        const estadoId = prov.idEstadoProveedor;
        const estadoTexto = estadosMap[estadoId] || 'Sin estado';
        let badgeClass = 'info';

        const txtLwr = estadoTexto.toLowerCase();
        if (txtLwr.includes('activo') || txtLwr.includes('aprobado') || txtLwr.includes('confiable')) {
            badgeClass = 'success';
        } else if (txtLwr.includes('proximo') || txtLwr.includes('vencer') || txtLwr.includes('alternativo') || txtLwr.includes('pendiente')) {
            badgeClass = 'warning';
        } else if (txtLwr.includes('vencido') || txtLwr.includes('rechazado') || txtLwr.includes('riesgo') || txtLwr.includes('documentacion')) {
            badgeClass = 'danger';
        }

        // Calificación del proveedor: Tomar la evaluación más reciente
        const ultimaEval = ultimasEvaluacionesMap[id];
        let califTexto = 'SIN CALIFICAR';
        let califBadgeClass = 'info';
        if (ultimaEval && ultimaEval.idCalificacion) {
            const desc = calificacionMap[ultimaEval.idCalificacion] || '';
            califTexto = desc.toUpperCase();
            const descLwr = desc.toLowerCase();
            if (descLwr.includes('confiable') && !descLwr.includes('no')) {
                califBadgeClass = 'success'; // verde
            } else if (descLwr.includes('alternativo')) {
                califBadgeClass = 'warning'; // amarillo
            } else if (descLwr.includes('no confiable')) {
                califBadgeClass = 'danger'; // rojo
            }
        }

        // Guardar las fechas como atributos data- en la fila para filtrado dinámico
        const fReg = prov.fechaCreado || '';
        const fAct = prov.fechaModificado || '';
        const fCal = (ultimaEval && ultimaEval.fechaCreado) ? ultimaEval.fechaCreado : '';

        html += `
            <tr data-id="${id}"
                data-fecha-registro="${fReg}"
                data-fecha-actualizacion="${fAct}"
                data-fecha-calificacion="${fCal}">
                <td>${nit}</td>
                <td><a href="buyer_supplier_profile.html?id=${id}" onclick="localStorage.setItem('selectedProviderId', '${id}')">${razonSocial}</a></td>
                <td>${contacto}</td>
                <td>${prov.telefonoPrincipal || 'No registrado'}</td>
                <td><span class="badge ${badgeClass}">${estadoTexto}</span></td>
                <td><span class="badge ${califBadgeClass}">${califTexto}</span></td>
                <td>${prov.descripcion || 'Sin clasificar'}</td>
                <td class="actions-cell">
                    <a href="buyer_supplier_profile.html?id=${id}" onclick="localStorage.setItem('selectedProviderId', '${id}')" title="Ver perfil"><i class="fa-solid fa-eye action-icon"></i></a>
                    <a href="buyer_second_evaluation.html?id=${id}" onclick="localStorage.setItem('selectedProviderId', '${id}')" title="Re-evaluar"><i class="fa-solid fa-arrows-rotate action-icon"></i></a>
                </td>
            </tr>
        `;
    });

    tableBody.innerHTML = html;
}

function buscarProveedores() {
    const query = document.getElementById('searchInput').value.toLowerCase().trim();
    const tipoFecha = document.getElementById('tipoFecha').value;
    const fechaInicio = document.getElementById('fechaInicio').value;
    const fechaFin = document.getElementById('fechaFin').value;

    const rows = document.querySelectorAll('#resultados tr');

    rows.forEach(row => {
        if (row.cells.length < 7) return;

        // Buscar en todas las columnas de texto relevantes
        const nitText = row.cells[0].textContent.toLowerCase();
        const nameText = row.cells[1].textContent.toLowerCase();
        const contactText = row.cells[2].textContent.toLowerCase();
        const phoneText = row.cells[3].textContent.toLowerCase();
        const statusText = row.cells[4].textContent.toLowerCase();
        const califText = row.cells[5].textContent.toLowerCase();
        const descText = row.cells[6].textContent.toLowerCase();

        const matchQuery = query === '' ||
                           nitText.includes(query) ||
                           nameText.includes(query) ||
                           contactText.includes(query) ||
                           phoneText.includes(query) ||
                           statusText.includes(query) ||
                           califText.includes(query) ||
                           descText.includes(query);

        // Filtro por tipo de fecha
        let matchFecha = true;
        let dateValueStr = '';
        
        if (tipoFecha === 'registro') {
            dateValueStr = row.getAttribute('data-fecha-registro');
        } else if (tipoFecha === 'actualizacion') {
            dateValueStr = row.getAttribute('data-fecha-actualizacion');
        } else if (tipoFecha === 'calificacion') {
            dateValueStr = row.getAttribute('data-fecha-calificacion');
        }

        if (fechaInicio || fechaFin) {
            if (dateValueStr) {
                const dateObj = new Date(dateValueStr);
                dateObj.setHours(0,0,0,0);

                if (fechaInicio) {
                    const inicio = new Date(fechaInicio);
                    inicio.setHours(0,0,0,0);
                    if (dateObj < inicio) matchFecha = false;
                }
                if (fechaFin) {
                    const fin = new Date(fechaFin);
                    fin.setHours(0,0,0,0);
                    if (dateObj > fin) matchFecha = false;
                }
            } else {
                matchFecha = false; // Excluir si la fecha requerida no existe para este registro
            }
        }

        if (matchQuery && matchFecha) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
}

window.buscarProveedores = buscarProveedores;
