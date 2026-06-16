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

    // 5. Obtener proveedores
    try {
        tableBody.innerHTML = '<tr><td colspan="9" style="text-align:center;">Cargando proveedores...</td></tr>';
        const response = await fetch(`${CONFIG.API_BASE_URL}/proveedores`);
        const result = await response.json();

        if (result.data && Array.isArray(result.data)) {
            renderizarTablaProveedores(result.data, estadosMap, ubicacionesMap, contactosMap);
        } else {
            tableBody.innerHTML = '<tr><td colspan="9" style="text-align:center;">No se encontraron proveedores.</td></tr>';
        }
    } catch (error) {
        console.error("Error cargando proveedores:", error);
        tableBody.innerHTML = '<tr><td colspan="9" style="text-align:center;">Error al conectar con el servidor.</td></tr>';
    }
});

function renderizarTablaProveedores(proveedores, estadosMap, ubicacionesMap, contactosMap) {
    const tableBody = document.getElementById('resultados');
    if (!tableBody) return;

    let html = '';
    proveedores.forEach(prov => {
        const id = prov.idProveedor;
        const nit = prov.numeroIdentificacion || 'Sin NIT';
        const razonSocial = prov.razonSocial || `${prov.nombres || ''} ${prov.apellidos || ''}`.trim() || 'Sin Nombre';
        
        // Ubicación
        const ubicacion = ubicacionesMap[id] || { direccion: 'No registrada', ciudad: 'No registrada' };
        
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

        // Clasificación (Confiable, Alternativo, etc.) - podemos usar la descripción o mapearla
        let clasificacionTexto = prov.descripcion || 'Sin clasificar';
        let clasifClass = 'info';
        const clasifLwr = clasificacionTexto.toLowerCase();
        if (clasifLwr.includes('confiable') || clasifLwr.includes('alto')) {
            clasifClass = 'success';
        } else if (clasifLwr.includes('alternativo') || clasifLwr.includes('medio')) {
            clasifClass = 'warning';
        } else if (clasifLwr.includes('no confiable') || clasifLwr.includes('critico')) {
            clasifClass = 'danger';
        }

        // Fecha de registro formateada para el buscador por fecha (columna oculta o visible en la fila, para que el buscador la detecte)
        // La fecha de registro está en prov.fechaCreado (formato ISO 8601, ej: 2026-05-16T12:00:00)
        let fechaFormateada = '';
        if (prov.fechaCreado) {
            const dateObj = new Date(prov.fechaCreado);
            // Formato DD/MM/YYYY
            const day = String(dateObj.getDate()).padStart(2, '0');
            const month = String(dateObj.getMonth() + 1).padStart(2, '0');
            const year = dateObj.getFullYear();
            fechaFormateada = `${day}/${month}/${year}`;
        }

        html += `
            <tr data-id="${id}">
                <td><a href="buyer_supplier_profile.html?id=${id}">${razonSocial}</a></td>
                <td>${nit}</td>
                <td>${contacto}</td>
                <td>${ubicacion.ciudad}</td>
                <td>${prov.telefonoPrincipal || 'No registrado'}</td>
                <td>${ubicacion.direccion}</td>
                <td><span class="badge ${badgeClass}">${estadoTexto}</span></td>
                <td><span class="badge ${clasifClass}">${clasificacionTexto}</span></td>
                <td class="actions-cell">
                    <a href="buyer_supplier_profile.html?id=${id}" title="Ver perfil"><i class="fa-solid fa-eye action-icon"></i></a>
                    <a href="buyer_second_evaluation.html?id=${id}" title="Re-evaluar"><i class="fa-solid fa-arrows-rotate action-icon"></i></a>
                </td>
                <td style="display:none;">${fechaFormateada}</td> <!-- Celda oculta para el buscador de fechas -->
            </tr>
        `;
    });

    tableBody.innerHTML = html;
}

function buscarProveedores() {
    const nombre = document.getElementById('nombre').value.toLowerCase().trim();
    const nit = document.getElementById('nit').value.toLowerCase().trim();
    const estado = document.getElementById('estado').value.toLowerCase().trim();
    const fechaInicio = document.getElementById('fechaInicio').value;
    const fechaFin = document.getElementById('fechaFin').value;
    
    const rows = document.querySelectorAll('#resultados tr');

    rows.forEach(row => {
        // Si la tabla está vacía o cargando, ignorar
        if (row.cells.length < 9) return;

        const rowName = row.cells[0].textContent.toLowerCase();
        const rowNit = row.cells[1].textContent.toLowerCase();
        const rowEstado = row.cells[6].textContent.toLowerCase();

        const matchNombre = nombre === '' || rowName.includes(nombre);
        const matchNit = nit === '' || rowNit.includes(nit);
        
        let matchEstado = true;
        if (estado !== '' && estado !== 'todos') {
            matchEstado = rowEstado.includes(estado);
        }

        // Filtro por rango de fechas (usando la celda oculta de índice 9)
        let matchFecha = true;
        if (fechaInicio || fechaFin) {
            const rowFecha = row.cells[9]?.textContent || '';
            if (rowFecha) {
                // Formato DD/MM/YYYY
                const parts = rowFecha.split('/');
                const fechaRegistro = new Date(`${parts[2]}-${parts[1]}-${parts[0]}`);
                const inicio = fechaInicio ? new Date(fechaInicio) : null;
                const fin = fechaFin ? new Date(fechaFin) : null;
                
                if (inicio && fechaRegistro < inicio) matchFecha = false;
                if (fin && fechaRegistro > fin) matchFecha = false;
            } else {
                matchFecha = false;
            }
        }

        if (matchNombre && matchNit && matchEstado && matchFecha) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
}

window.buscarProveedores = buscarProveedores;
