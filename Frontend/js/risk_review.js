document.addEventListener('DOMContentLoaded', async () => {
    const urlParams = new URLSearchParams(window.location.search);
    const idProveedor = urlParams.get('id');

    if (!idProveedor) {
        alert('ID de proveedor no especificado.');
        window.location.href = 'risk_list.html';
        return;
    }

    const infoGrid = document.querySelector('.info-grid');
    const tbodyMatrix = document.getElementById('tbody-validation-matrix');
    const textarea = document.querySelector('.risk-observations textarea');
    const buttons = document.querySelectorAll('.review-actions .btn');

    let saveBtn, cancelBtn;
    buttons.forEach(btn => {
        if (btn.textContent.includes('Guardar')) saveBtn = btn;
        if (btn.textContent.includes('Cancelar')) cancelBtn = btn;
    });

    const decisionSelect = document.getElementById('risk-decision-select');
    const mode = urlParams.get('mode');
    let currentSupplier = null;
    let firstValidationId = null;
    let existingEvaluation = null;

    // Adjust button text and behavior based on mode
    if (mode === 'edit') {
        if (cancelBtn) cancelBtn.innerHTML = '<i class="fa-solid fa-xmark"></i> Cancelar';
        if (saveBtn) saveBtn.innerHTML = '<i class="fa-solid fa-floppy-disk"></i> Modificar revisión';
    }

    async function loadSupplierDetails() {
        try {
            // Fetch supplier detail, validations, fields, company types, and final validations in parallel
            const [supplierRes, validationRes, fieldRes, tipoPersonaRes, valFinalRes] = await Promise.all([
                fetch(`${CONFIG.API_BASE_URL}/proveedores/${idProveedor}`),
                fetch(`${CONFIG.API_BASE_URL}/validacion`),
                fetch(`${CONFIG.API_BASE_URL}/campo_validacion`),
                fetch(`${CONFIG.API_BASE_URL}/tipo_persona`),
                fetch(`${CONFIG.API_BASE_URL}/validacion-final`)
            ]);

            if (!supplierRes.ok || !validationRes.ok || !fieldRes.ok || !tipoPersonaRes.ok || !valFinalRes.ok) {
                throw new Error('Error al cargar datos del servidor');
            }

            const [supplierData, validations, fields, tiposPersona, finalValidations] = await Promise.all([
                supplierRes.json().then(r => r.data),
                validationRes.json().then(r => r.data || []),
                fieldRes.json().then(r => r.data || []),
                tipoPersonaRes.json().then(r => r.data || []),
                valFinalRes.json().then(r => r.data || [])
            ]);

            currentSupplier = supplierData;

            // Render supplier basic info
            if (currentSupplier) {
                const name = currentSupplier.razonSocial || `${currentSupplier.nombres || ''} ${currentSupplier.apellidos || ''}`.trim() || 'Sin Nombre';
                const nit = currentSupplier.numeroIdentificacion || 'Sin NIT';
                
                const tipoPersonaObj = tiposPersona.find(tp => tp.idTipoPersona === currentSupplier.idTipoPersona);
                const tipoEmpresa = tipoPersonaObj ? tipoPersonaObj.descripcion : 'No especificado';
                
                const fecha = currentSupplier.fechaCreado ? new Date(currentSupplier.fechaCreado).toLocaleDateString('es-CO') : '-';

                if (infoGrid) {
                    infoGrid.innerHTML = `
                        <div><strong>Nombre:</strong> ${name}</div>
                        <div><strong>NIT:</strong> ${nit}</div>
                        <div><strong>Tipo de empresa:</strong> ${tipoEmpresa}</div>
                        <div><strong>Fecha de registro:</strong> ${fecha}</div>
                        <div><strong>Estado actual:</strong> <span class="badge pending">En revisión con novedad</span></div>
                    `;
                }
            }

            // Render validation matrix
            const matrixMapping = [
                {
                    criterio: "Razón Social",
                    cells: { rut: 2, camara: 6, refcom: 14, banco: 11, cedula: null }
                },
                {
                    criterio: "NIT",
                    cells: { rut: 1, camara: 5, refcom: 13, banco: 10, cedula: null }
                },
                {
                    criterio: "Composición Accionaria",
                    cells: { rut: 4, camara: 8, refcom: null, banco: null, cedula: null }
                },
                {
                    criterio: "Representante Legal",
                    cells: { rut: 3, camara: 7, refcom: null, banco: null, cedula: 16 }
                },
                {
                    criterio: "Vigencia del Documento",
                    cells: { rut: null, camara: 9, refcom: 15, banco: 12, cedula: null }
                }
            ];

            const providerValidations = validations.filter(v => 
                v.idProveedor === parseInt(idProveedor)
            );
            
            if (providerValidations.length > 0) {
                firstValidationId = providerValidations[0].idValidacion;
            }

            // If we are in edit mode, find and load the existing evaluation for this provider from validacion-final
            if (mode === 'edit') {
                const providerEvals = finalValidations.filter(ev => 
                    ev.idProveedor === parseInt(idProveedor) && 
                    (ev.estadoValidacion === '8' || ev.estadoValidacion === '10')
                );
                if (providerEvals.length > 0) {
                    // Sort descending by ID to get the latest
                    providerEvals.sort((a, b) => b.idValidacionFinal - a.idValidacionFinal);
                    existingEvaluation = providerEvals[0];

                    // Populate controls
                    if (textarea) textarea.value = existingEvaluation.comentarioFinal || '';
                    if (decisionSelect) {
                        decisionSelect.value = existingEvaluation.estadoValidacion;
                    }
                }
            }

            if (tbodyMatrix) {
                tbodyMatrix.innerHTML = '';

                matrixMapping.forEach(row => {
                    const tr = document.createElement('tr');
                    
                    // Columna de Criterio
                    const tdCriterio = document.createElement('td');
                    tdCriterio.style.textAlign = 'left';
                    tdCriterio.style.fontWeight = '500';
                    tdCriterio.textContent = row.criterio;
                    tr.appendChild(tdCriterio);

                    // Columnas de Documentos
                    const docsKeys = ['rut', 'camara', 'refcom', 'banco', 'cedula'];
                    docsKeys.forEach(docKey => {
                        const td = document.createElement('td');
                        const campoId = row.cells[docKey];

                        if (campoId === null) {
                            td.textContent = '-';
                            td.style.color = '#94a3b8';
                        } else {
                            const validacion = providerValidations.find(v => v.idCampoValidacion === campoId);
                            let iconClass = 'fa-solid fa-circle-minus status-icon pending';
                            let title = 'Pendiente / No validado';
                            let iconColor = '#94a3b8';

                            if (validacion) {
                                if (validacion.resultadoValidacion === true) {
                                    iconClass = 'fa-solid fa-circle-check status-icon success';
                                    title = 'Validado y coincide';
                                    iconColor = '#10b981'; // Green color for success
                                } else if (validacion.resultadoValidacion === false) {
                                    iconClass = 'fa-solid fa-circle-xmark status-icon danger';
                                    title = 'Validado y no coincide';
                                    iconColor = '#ef4444'; // Red color for mismatch
                                }
                            }

                            const iconEl = document.createElement('i');
                            iconEl.className = iconClass;
                            iconEl.title = `${title} (Comentario: ${validacion ? (validacion.comentarios || 'Ninguno') : 'Ninguno'})`;
                            iconEl.style.cursor = 'pointer';
                            iconEl.style.fontSize = '20px';
                            iconEl.style.color = iconColor;
                            iconEl.style.transition = 'transform 0.2s ease, filter 0.2s ease';
                            
                            iconEl.addEventListener('mouseenter', () => {
                                iconEl.style.transform = 'scale(1.25)';
                                iconEl.style.filter = 'brightness(1.1)';
                            });
                            iconEl.addEventListener('mouseleave', () => {
                                iconEl.style.transform = 'scale(1)';
                                iconEl.style.filter = 'brightness(1)';
                            });
                            
                            // Al hacer clic, mostrar alert descriptivo
                            iconEl.addEventListener('click', () => {
                                if (validacion) {
                                    alert(`Validación: ${row.criterio} [${docKey.toUpperCase()}]\nID: VAL-${String(validacion.idValidacion).padStart(5, '0')}\nResultado: ${title}\nValor Documento: ${validacion.valorDocumento || '-'}\nValor Web: ${validacion.valorWeb || '-'}\nObservaciones: ${validacion.comentarios || 'Sin observaciones.'}`);
                                } else {
                                    alert(`El campo ${row.criterio} en el documento ${docKey.toUpperCase()} no ha sido validado aún.`);
                                }
                            });

                            td.appendChild(iconEl);
                        }
                        tr.appendChild(td);
                    });

                    tbodyMatrix.appendChild(tr);
                });
            }

        } catch (error) {
            console.error(error);
            if (infoGrid) {
                infoGrid.innerHTML = `<div style="color:red;grid-column:1/-1;">Error al cargar información del proveedor de la base de datos.</div>`;
            }
            if (tbodyMatrix) {
                tbodyMatrix.innerHTML = `<tr><td colspan="6" style="text-align:center;color:red;">Error al cargar matriz de validaciones.</td></tr>`;
            }
        }
    }
    if (saveBtn) {
        saveBtn.addEventListener('click', async () => {
            if (!textarea || textarea.value.trim() === '') {
                alert('Debe ingresar alguna conclusión en las observaciones antes de guardar la revisión.');
                return;
            }

            if (!decisionSelect || decisionSelect.value === '') {
                alert('Debe seleccionar una decisión (Aprobado o Rechazado) antes de guardar.');
                return;
            }

            const selectedStatusId = parseInt(decisionSelect.value);
            const comments = textarea.value.trim();
            const userId = parseInt(localStorage.getItem('userId')) || 1;

            const payload = {
                idProveedor: parseInt(idProveedor),
                comentarioFinal: comments,
                estadoValidacion: selectedStatusId.toString(),
                creadoPor: userId,
                modificadoPor: userId,
                fechaModificado: new Date().toISOString(),
                activo: true,
                validationIds: []
            };

            try {
                // 1. Save Risk Decision to validacion-final
                const response = await fetch(`${CONFIG.API_BASE_URL}/validacion-final`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(payload)
                });

                if (!response.ok) throw new Error('Error al registrar la decisión de riesgos');

                // 2. Update Supplier Status (PATCH)
                const putResponse = await fetch(`${CONFIG.API_BASE_URL}/proveedores/${idProveedor}/estado`, {
                    method: 'PATCH',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        idEstadoProveedor: selectedStatusId,
                        modificadoPor: userId
                    })
                });

                if (!putResponse.ok) throw new Error('Error al actualizar el estado del proveedor');
                
                alert(mode === 'edit'
                    ? 'Revisión de riesgo modificada y estado del proveedor actualizado exitosamente.'
                    : 'Revisión de riesgo guardada y estado del proveedor actualizado exitosamente.'
                );
                
                if (mode === 'edit') {
                    window.location.href = 'approval_history.html';
                } else {
                    window.location.href = 'risk_list.html';
                }
            } catch (error) {
                console.error(error);
                alert('Hubo un error al guardar la revisión de riesgos o actualizar el estado del proveedor.');
            }
        });
    }

    if (cancelBtn) {
        cancelBtn.addEventListener('click', () => {
            if (confirm('¿Seguro que deseas cancelar? Se perderán las observaciones no guardadas.')) {
                if (mode === 'edit') {
                    window.location.href = 'approval_history.html';
                } else {
                    window.location.href = 'risk_list.html';
                }
            }
        });
    }

    await loadSupplierDetails();
});
