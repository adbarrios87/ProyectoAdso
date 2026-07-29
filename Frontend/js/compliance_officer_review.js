document.addEventListener('DOMContentLoaded', async () => {
    const urlParams = new URLSearchParams(window.location.search);// Extrae la parte de la URL que está después del signo ? (los parámetros de consulta)
    const idProveedor = urlParams.get('id');
    const justificationArea = document.getElementById('justification');

    // Buttons
    const approveBtn = document.querySelector('.btn-approve');
    const rejectBtn = document.querySelector('.btn-reject');

    if (!idProveedor) {
        alert('ID de proveedor no especificado.');// Muestra una alerta si no se especifica un ID de proveedor.
        window.location.href = 'compliance_officer_dashboard.html';
        return;
    }

    let currentSupplier = null;
    const userId = parseInt(localStorage.getItem('userId')) || 1; // Default fallback to Admin/System user

    // Cargar detalles del proveedor dinámicamente
    async function loadSupplierDetails() {
        try {
            // Cargar info de proveedor
            const provRes = await fetch(`${CONFIG.API_BASE_URL}/proveedores/${idProveedor}`);
            if (!provRes.ok) throw new Error('Error al cargar datos del proveedor');
            const provData = await provRes.json();
            currentSupplier = provData.data;

            // Cargar estado del proveedor
            const statusRes = await fetch(`${CONFIG.API_BASE_URL}/estado_proveedor`);
            let statusName = 'En revisión';
            if (statusRes.ok) {
                const statuses = await statusRes.json();
                const statusList = statuses.data || [];
                const matchedStatus = statusList.find(st => st.idEstadoProveedor === currentSupplier.idEstadoProveedor);
                if (matchedStatus) statusName = matchedStatus.descripcion;
            }

            // Mostrar info del proveedor
            const infoGrid = document.querySelector('.info-grid');
            if (infoGrid) {
                const name = currentSupplier.razonSocial || `${currentSupplier.nombres || ''} ${currentSupplier.apellidos || ''}`.trim() || 'Sin Nombre';
                const nit = currentSupplier.numeroIdentificacion || 'Sin NIT';
                const companyType = currentSupplier.idTipoPersona === 1 ? 'Persona Natural' : 'Persona Jurídica';
                const regDate = currentSupplier.fechaCreado ? new Date(currentSupplier.fechaCreado).toLocaleDateString('es-CO') : '-';
                const description = currentSupplier.descripcion || 'Sin diligenciar';

                infoGrid.innerHTML = `
                    <div><strong>Nombre:</strong> ${name}</div>
                    <div><strong>NIT:</strong> ${nit}</div>
                    <div><strong>Tipo de empresa:</strong> ${companyType}</div>
                    <div><strong>Fecha de registro:</strong> ${regDate}</div>
                    <div><strong>Bienes/Servicios:</strong> ${description}</div>
                    <div><strong>Estado actual:</strong> ${statusName}</div>
                `;
            }

            // carga de las validaciones
            const [validationRes, valFinalRes] = await Promise.all([
                fetch(`${CONFIG.API_BASE_URL}/validacion`),
                fetch(`${CONFIG.API_BASE_URL}/validacion-final`)
            ]);

            if (validationRes.ok) {
                const validationsData = await validationRes.json();
                const validations = validationsData.data || [];
                const providerValidations = validations.filter(v => v.idProveedor === parseInt(idProveedor));

                const tbodyMatrix = document.getElementById('tbody-validation-matrix');
                if (tbodyMatrix) {
                    tbodyMatrix.innerHTML = '';

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
                                        iconColor = '#81c5aeff';
                                    } else if (validacion.resultadoValidacion === false) {
                                        iconClass = 'fa-solid fa-circle-xmark status-icon danger';
                                        title = 'Validado y no coincide';
                                        iconColor = '#caaeaeff';
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

                                iconEl.addEventListener('click', () => {
                                    if (validacion) {
                                        alert(`Validación: ${row.criterio} [${docKey.toUpperCase()}]\nResultado: ${title}\nObservaciones: ${validacion.comentarios || 'Sin observaciones.'}`);
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
            }

            // Carga la observacion del analista de riesgos
            if (valFinalRes.ok) {
                const valFinalData = await valFinalRes.json();
                const valFinalsList = valFinalData.data || [];
                // Filtra las validaciones finales del proveedor
                const providerFinals = valFinalsList.filter(vf => vf.idProveedor === parseInt(idProveedor));
                // Filtra por id para obtener la última observación
                providerFinals.sort((a, b) => b.idValidacionFinal - a.idValidacionFinal);

                const commentBox = document.getElementById('risk-analyst-comment');
                if (commentBox) {
                    if (providerFinals.length > 0) {
                        commentBox.textContent = providerFinals[0].comentarioFinal || 'Sin observaciones registradas por el analista de riesgos.';
                    } else {
                        commentBox.textContent = 'No se registran observaciones previas de analista de riesgos para este proveedor.';
                    }
                }
            }

        } catch (error) {
            console.error('Error:', error);
            alert('Error al cargar la información del proveedor.');
        }
    }

    // Guardar decision en el back
    async function submitDecision(isApproval) {
        const justificationText = justificationArea ? justificationArea.value.trim() : '';
        if (!justificationText) {
            alert('Debe ingresar una justificación antes de guardar la decisión.'); // Valida que se realice comentario de la decision del oficial
            return;
        }

        if (!currentSupplier) {
            alert('Los datos del proveedor aún no se han cargado por completo.'); // Valida que se carguen los datos del proveedor
            return;
        }

        const selectedStatusId = isApproval ? 11 : 12; // 11 es aprobado, 12 es rechazado por el oficial de cumplimiento

        // 1. Prepara el objeto que se va a guardar en validacion_final
        const validationFinalPayload = {
            idProveedor: parseInt(idProveedor),
            comentarioFinal: justificationText,
            estadoValidacion: selectedStatusId.toString(),
            creadoPor: userId,
            validationIds: [] // No se necesita enlace de validaciones atómicas para esta decisión de cumplimiento
        };

        try {
            // Send Validation Final POST
            const valResponse = await fetch(`${CONFIG.API_BASE_URL}/validacion-final`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(validationFinalPayload)
            });

            if (!valResponse.ok) throw new Error('Error al registrar la validación final');

            // Send Supplier PATCH
            const supResponse = await fetch(`${CONFIG.API_BASE_URL}/proveedores/${idProveedor}/estado`, {
                method: 'PATCH',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    idEstadoProveedor: selectedStatusId,
                    modificadoPor: userId
                })
            });

            if (!supResponse.ok) throw new Error('Error al actualizar el estado del proveedor');

            alert(isApproval ? 'Proveedor aprobado exitosamente.' : 'Proveedor rechazado exitosamente.');
            window.location.href = 'compliance_officer_dashboard.html';

        } catch (error) {
            console.error('Error:', error);
            alert('Hubo un error al guardar la decisión en el servidor.');
        }
    }

    // Botones de aprobar o rechazar
    if (approveBtn) {
        approveBtn.addEventListener('click', () => submitDecision(true));
    }
    if (rejectBtn) {
        rejectBtn.addEventListener('click', () => submitDecision(false));
    }

    await loadSupplierDetails();
});
