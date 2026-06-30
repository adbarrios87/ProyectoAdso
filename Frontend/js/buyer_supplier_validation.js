document.addEventListener('DOMContentLoaded', async () => {
    // --- 1. MATRIZ DE VALIDACIÓN ---
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

    function renderMatrix(validaciones) {
        const tbody = document.getElementById('tbody-validation-matrix');
        if (!tbody) return;

        tbody.innerHTML = '';

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
                    const validacion = validaciones.find(v => v.idCampoValidacion === campoId);
                    let iconClass = 'fa-solid fa-circle-minus status-icon pending';
                    let title = 'Pendiente / No validado';

                    if (validacion) {
                        if (validacion.resultadoValidacion === true) {
                            iconClass = 'fa-solid fa-circle-check status-icon success';
                            title = 'Validado y coincide';
                        } else if (validacion.resultadoValidacion === false) {
                            iconClass = 'fa-solid fa-circle-xmark status-icon danger';
                            title = 'Validado y no coincide';
                        }
                    }

                    const iconEl = document.createElement('i');
                    iconEl.className = iconClass;
                    iconEl.title = `${title} (Comentario: ${validacion ? validacion.comentarios : 'Ninguno'})`;
                    iconEl.style.cursor = 'pointer';
                    iconEl.style.fontSize = '20px';
                    iconEl.style.transition = 'transform 0.2s ease, filter 0.2s ease';
                    
                    iconEl.addEventListener('mouseenter', () => {
                        iconEl.style.transform = 'scale(1.25)';
                        iconEl.style.filter = 'brightness(1.1)';
                    });
                    iconEl.addEventListener('mouseleave', () => {
                        iconEl.style.transform = 'scale(1)';
                        iconEl.style.filter = 'brightness(1)';
                    });
                    
                    // Al hacer clic, abrir modal de edición
                    iconEl.addEventListener('click', () => {
                        openEditModal(campoId, validacion);
                    });

                    td.appendChild(iconEl);
                }
                tr.appendChild(td);
            });

            tbody.appendChild(tr);
        });
    }

    // --- 0. OBTENER PROVEEDOR DESDE URL / STORAGE ---
    const urlParams = new URLSearchParams(window.location.search);
    let providerId = urlParams.get('id');

    if (!providerId) {
        providerId = localStorage.getItem('selectedProviderId');
    } else {
        localStorage.setItem('selectedProviderId', providerId);
    }

    if (!providerId) {
        console.warn("No se especificó un ID de proveedor en la URL ni en localStorage.");
        return;
    }

    // Cargar datos de cabecera del proveedor
    let validacionesCargadas = [];
    const userId = parseInt(localStorage.getItem('userId') || '2'); // Fallback a Jefe de Compras (ID 2)

    try {
        const res = await fetch(`${CONFIG.API_BASE_URL}/proveedores/${providerId}/detalle-completo`);
        const result = await res.json();
        if (result.data) {
            const prov = result.data.proveedor;
            validacionesCargadas = result.data.validaciones || [];

            // Actualizar info en cabecera
            const nameEl = document.getElementById('info-nombre');
            const nitEl = document.getElementById('info-nit');
            const tipoEl = document.getElementById('info-tipo');
            const fechaRegEl = document.getElementById('info-fecha');
            const responsableEl = document.getElementById('info-responsable');
            
            if (nameEl) nameEl.innerHTML = `<strong>Nombre:</strong> ${prov.razonSocial || (prov.nombres + ' ' + prov.apellidos)}`;
            if (nitEl) nitEl.innerHTML = `<strong>NIT:</strong> ${prov.numeroIdentificacion}`;
            if (tipoEl) tipoEl.innerHTML = `<strong>Tipo:</strong> ${prov.idTipoPersona === 2 ? 'Persona Jurídica' : 'Persona Natural'}`;
            
            if (fechaRegEl && prov.fechaCreado) {
                const dateObj = new Date(prov.fechaCreado);
                if (!isNaN(dateObj)) {
                    const day = String(dateObj.getDate()).padStart(2, '0');
                    const month = String(dateObj.getMonth() + 1).padStart(2, '0');
                    const year = dateObj.getFullYear();
                    fechaRegEl.innerHTML = `<strong>Fecha Registro:</strong> ${day}/${month}/${year}`;
                } else {
                    fechaRegEl.innerHTML = `<strong>Fecha Registro:</strong> ${prov.fechaCreado}`;
                }
            }
            if (responsableEl) {
                const userName = localStorage.getItem('userName') || 'Usuario';
                responsableEl.innerHTML = `<strong>Responsable Validación:</strong> ${userName}`;
            }
            
            renderMatrix(validacionesCargadas);
        }
    } catch (e) {
        console.error("Error al cargar la información del proveedor:", e);
    }

    // --- 2. MODAL DE EDICIÓN ---
    const editModal = document.getElementById('editValidationModal');
    const editForm = document.getElementById('editValidationForm');
    const modalValIdInput = document.getElementById('modal-validation-id');
    const modalResultSelect = document.getElementById('modal-validation-result');

    let currentSelectedCampoId = null;
    let currentSelectedValidacion = null;

    function openEditModal(campoId, validacion) {
        currentSelectedCampoId = campoId;
        currentSelectedValidacion = validacion;

        if (validacion) {
            modalValIdInput.value = validacion.idValidacion || '';
            if (validacion.resultadoValidacion === true) {
                modalResultSelect.value = 'true';
            } else if (validacion.resultadoValidacion === false) {
                modalResultSelect.value = 'false';
            } else {
                modalResultSelect.value = 'null';
            }
        } else {
            modalValIdInput.value = '';
            modalResultSelect.value = 'null';
        }

        editModal.style.display = 'flex';
    }

    function closeEditModal() {
        editModal.style.display = 'none';
        editForm.reset();
    }

    // Registro de eventos para cerrar modal
    document.querySelectorAll('.close-validation-modal, .close-validation-modal-btn').forEach(btn => {
        btn.addEventListener('click', closeEditModal);
    });

    // Guardar cambio de estado de validación
    if (editForm) {
        editForm.addEventListener('submit', async (e) => {
            e.preventDefault();

            const valId = modalValIdInput.value;
            const selectVal = modalResultSelect.value;
            const resultado = selectVal === 'true' ? true : (selectVal === 'false' ? false : null);
            const ahoraStr = new Date().toISOString().substring(0, 19); // yyyy-MM-ddTHH:mm:ss

            const payload = {
                idUsuario: userId,
                idProveedor: parseInt(providerId),
                idCampoValidacion: currentSelectedCampoId,
                valorWeb: currentSelectedValidacion ? currentSelectedValidacion.valorWeb : "",
                valorDocumento: currentSelectedValidacion ? currentSelectedValidacion.valorDocumento : "",
                idDocumento: currentSelectedValidacion ? currentSelectedValidacion.idDocumento : null,
                resultadoValidacion: resultado,
                fechaValidacion: new Date().toISOString().substring(0, 10),
                comentarios: "Validado manualmente",
                activo: true
            };

            try {
                let url = `${CONFIG.API_BASE_URL}/validacion`;
                let method = 'POST';

                if (valId) {
                    url = `${CONFIG.API_BASE_URL}/validacion/${valId}`;
                    method = 'PUT';
                    payload.idValidacion = parseInt(valId);
                    payload.modificadoPor = userId;
                    payload.fechaModificado = ahoraStr;
                } else {
                    payload.creadoPor = userId;
                    payload.fechaCreado = ahoraStr;
                }

                const res = await fetch(url, {
                    method: method,
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(payload)
                });

                if (res.ok) {
                    alert("Estado de validación actualizado exitosamente.");
                    closeEditModal();
                    window.location.reload(); // Recargar para mostrar los nuevos estados
                } else {
                    alert("Error al actualizar la validación.");
                }
            } catch (err) {
                console.error(err);
                alert("Error de conexión al servidor.");
            }
        });
    }

    // --- 3. GUARDAR VALIDACIÓN FINAL ---
    const btnSaveFinalValidation = document.getElementById('btn-save-final-validation');
    const finalStatusSelect = document.getElementById('final-status-select');
    const finalCommentTextarea = document.getElementById('final-comment');

    if (btnSaveFinalValidation) {
        btnSaveFinalValidation.addEventListener('click', async () => {
            const estadoVal = finalStatusSelect.value;
            const comentarioVal = finalCommentTextarea.value.trim();

            if (!estadoVal) {
                alert("Por favor seleccione una decisión de validación.");
                return;
            }
            if (!comentarioVal) {
                alert("Por favor escriba un comentario final.");
                return;
            }

            const validationIds = validacionesCargadas.map(v => v.idValidacion).filter(id => id !== undefined && id !== null);

            const payload = {
                idProveedor: parseInt(providerId),
                comentarioFinal: comentarioVal,
                estadoValidacion: estadoVal, // "10" o "11"
                creadoPor: userId,
                validationIds: validationIds
            };

            try {
                btnSaveFinalValidation.disabled = true;
                btnSaveFinalValidation.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Guardando...';

                const res = await fetch(`${CONFIG.API_BASE_URL}/validacion-final`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(payload)
                });

                if (res.ok) {
                    alert("Validación final guardada exitosamente y estado del proveedor actualizado.");
                    window.location.href = `buyer_supplier_profile.html?id=${providerId}`;
                } else {
                    const errResult = await res.json().catch(() => ({}));
                    alert(`Error al guardar la validación final: ${errResult.message || 'Error del servidor'}`);
                }
            } catch (err) {
                console.error(err);
                alert("Error de conexión al guardar la validación final.");
            } finally {
                btnSaveFinalValidation.disabled = false;
                btnSaveFinalValidation.innerHTML = '<i class="fas fa-save"></i> Guardar Validación';
            }
        });
    }

    // === MODAL AGREGAR PERSONA (Mantener lógica existente) ===
    const modal = document.getElementById('addPersonModal');
    const btnAddPerson = document.getElementById('btnAddPerson');
    const closeModal = document.querySelector('.close-modal');
    const btnCancel = document.querySelector('.btn-cancel');
    const addPersonForm = document.getElementById('addPersonForm');

    if (btnAddPerson) {
        btnAddPerson.addEventListener('click', () => {
            modal.classList.add('active');
        });
    }

    if (closeModal) {
        closeModal.addEventListener('click', () => {
            modal.classList.remove('active');
        });
    }

    if (btnCancel) {
        btnCancel.addEventListener('click', () => {
            modal.classList.remove('active');
        });
    }

    if (modal) {
        modal.addEventListener('click', (e) => {
            if (e.target === modal) {
                modal.classList.remove('active');
            }
        });
    }

    if (addPersonForm) {
        addPersonForm.addEventListener('submit', (e) => {
            e.preventDefault();
            const docType = document.getElementById('docType').value;
            const docNumber = document.getElementById('docNumber').value;
            const docDv = document.getElementById('docDv').value;
            const fullName = document.getElementById('fullName').value;
            
            alert(`Consultando en listas restrictivas:\nTipo: ${docType}\nNúmero: ${docNumber}\nDV: ${docDv}\nNombre: ${fullName}`);
            modal.classList.remove('active');
            addPersonForm.reset();
        });
    }

    const searchInput = document.querySelector('.search-input');
    const tableRows = document.querySelectorAll('.general-table tbody tr');
    
    if (searchInput) {
        searchInput.addEventListener('input', (e) => {
            const term = e.target.value.toLowerCase();
            tableRows.forEach(row => {
                const text = row.textContent.toLowerCase();
                if (row.closest('.matrix-table')) return; // No filtrar matriz
                row.style.display = text.includes(term) ? '' : 'none';
            });
        });
    }
});
