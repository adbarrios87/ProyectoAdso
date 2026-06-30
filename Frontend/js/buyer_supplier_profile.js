let currentSupplierData = null;

document.addEventListener('DOMContentLoaded', async () => {
    // Descargar ficha PDF
    const downloadBtn = document.getElementById('action-download');
    if (downloadBtn) {
        downloadBtn.addEventListener('click', (e) => {
            e.preventDefault();
            generarFichaPDF();
        });
    }



    const filterSelect = document.querySelector('.filter-select');
    if (filterSelect) {
        filterSelect.addEventListener('change', (e) => {
            console.log('Filtrando documentos por año:', e.target.value);
            alert(`Filtrando documentos para el año ${e.target.value}`);
        });
    }

    // Guardar observaciones
    const saveNoteBtn = document.querySelector('.notes + .btn');
    if (saveNoteBtn) {
        saveNoteBtn.addEventListener('click', () => {
            const textarea = document.querySelector('.notes');
            if(textarea.value.trim() !== "") {
                alert('Observación guardada.');
                textarea.value = '';
            } else {
                alert('Escribe una observación antes de guardar.');
            }
        });
    }

    // --- CARGA DINÁMICA DEL PROVEEDOR ---
    const urlParams = new URLSearchParams(window.location.search);
    let providerId = urlParams.get('id');

    if (!providerId) {
        providerId = localStorage.getItem('selectedProviderId');
    } else {
        localStorage.setItem('selectedProviderId', providerId);
    }

    if (!providerId) {
        console.warn("No se especificó un ID de proveedor en la URL ni en localStorage.");
        document.getElementById('supplier-name').textContent = "Proveedor no seleccionado";
        return;
    }

    try {
        // Cargar catálogos auxiliares para mapear IDs a nombres descriptivos
        let personaMap = {};
        let idMap = {};
        let idCodigoMap = {};
        let estadoMap = {};
        let municipioMap = {};

        try {
            const [resPers, resIds, resEst, resMun] = await Promise.all([
                fetch(`${CONFIG.API_BASE_URL}/tipo_persona`),
                fetch(`${CONFIG.API_BASE_URL}/tipo_identificacion`),
                fetch(`${CONFIG.API_BASE_URL}/estado_proveedor`),
                fetch(`${CONFIG.API_BASE_URL}/municipio`)
            ]);
            
            const [pJson, iJson, eJson, mJson] = await Promise.all([
                resPers.json(), resIds.json(), resEst.json(), resMun.json()
            ]);

            if (pJson.data) pJson.data.forEach(t => personaMap[t.idTipoPersona] = t.descripcion);
            if (iJson.data) iJson.data.forEach(t => {
                idMap[t.idTipoIdentificacion] = t.descripcion;
                idCodigoMap[t.idTipoIdentificacion] = t.codigo;
            });
            if (eJson.data) eJson.data.forEach(t => estadoMap[t.idEstadoProveedor] = t.descripcion);
            if (mJson.data) mJson.data.forEach(t => municipioMap[t.idMunicipio] = t.nombre);
        } catch (e) {
            console.error("Error cargando catálogos de mapeo:", e);
        }

        // Obtener detalle completo unificado
        const response = await fetch(`${CONFIG.API_BASE_URL}/proveedores/${providerId}/detalle-completo`);
        const result = await response.json();
        const data = result.data;
        currentSupplierData = data;

        if (!data || !data.proveedor) {
            document.getElementById('supplier-name').textContent = "Proveedor no encontrado";
            return;
        }

        const prov = data.proveedor;

        // Razón Social / Nombre
        const nameText = prov.razonSocial || `${prov.nombres || ''} ${prov.apellidos || ''}`.trim() || 'Sin nombre';
        document.getElementById('supplier-name').textContent = nameText;
        document.getElementById('supplier-nit').textContent = prov.numeroIdentificacion || 'Sin NIT';

        // Fechas
        document.getElementById('supplier-reg-date').textContent = prov.fechaCreado ? new Date(prov.fechaCreado).toLocaleDateString() : 'No registrada';
        document.getElementById('supplier-update-date').textContent = prov.fechaModificado ? new Date(prov.fechaModificado).toLocaleDateString() : 'No registrada';

        // Botón Certificación y Editar
        const certBtn = document.getElementById('action-cert');
        if (certBtn) certBtn.href = `supplier_certification.html?id=${providerId}`;

        const evalBtn = document.getElementById('action-eval');
        if (evalBtn) evalBtn.href = `buyer_first_evaluation.html?id=${providerId}`;

        const reevalBtn = document.getElementById('action-reeval');
        if (reevalBtn) reevalBtn.href = `buyer_second_evaluation.html?id=${providerId}`;

        const historyBtn = document.getElementById('action-history');
        if (historyBtn) historyBtn.href = `supplier_qualification_history.html?id=${providerId}`;



        // Información General
        document.getElementById('info-person-type').textContent = personaMap[prov.idTipoPersona] || 'No registrado';
        document.getElementById('info-business-type').textContent = prov.descripcion || 'Sin clasificar';
        document.getElementById('info-email').textContent = prov.correoPrincipal || 'No registrado';
        document.getElementById('info-phone').textContent = prov.telefonoPrincipal || 'No registrado';

        // Ubicación y Dirección (desde endpoint ubicacion de respaldo si es necesario, o la unificamos)
        let direccionTexto = 'No registrada';
        let ciudadTexto = 'No registrada';
        try {
            const locResponse = await fetch(`${CONFIG.API_BASE_URL}/ubicacion`);
            const locResult = await locResponse.json();
            const ubicacion = locResult.data ? locResult.data.find(u => u.idProveedor === prov.idProveedor) : null;
            if (ubicacion) {
                direccionTexto = ubicacion.direccion || 'No registrada';
                ciudadTexto = municipioMap[ubicacion.idMunicipio] ? `${municipioMap[ubicacion.idMunicipio]}, Colombia` : 'No registrada';
            }
        } catch(e) {}
        document.getElementById('info-address').textContent = direccionTexto;
        document.getElementById('info-location').textContent = ciudadTexto;

        // Estado del Proveedor Badge
        const statusSpan = document.getElementById('supplier-status');
        const estadoTexto = estadoMap[prov.idEstadoProveedor] || 'Sin estado';
        statusSpan.textContent = estadoTexto;
        statusSpan.className = 'status';
        const desc = estadoTexto.toLowerCase();
        if (desc.includes('activo') || desc.includes('aprobado') || desc.includes('confiable')) {
            statusSpan.classList.add('active'); // Verde
        } else if (desc.includes('pendiente') || desc.includes('proximo') || desc.includes('vencer') || desc.includes('alternativo')) {
            statusSpan.classList.add('pending'); // Amarillo
        } else {
            statusSpan.classList.add('inactive'); // Rojo
        }

        // --- CONTACTOS ---
        const contactosTbody = document.getElementById('contactos-tbody');
        if (data.contactos && data.contactos.length > 0) {
            let contactosHtml = '';
            data.contactos.forEach(c => {
                contactosHtml += `
                    <tr>
                        <td>${c.nombreContacto || 'Sin Nombre'}</td>
                        <td>${c.cargoContacto || 'Sin Cargo'}</td>
                        <td>${c.telefonoContacto || 'Sin Teléfono'}</td>
                        <td>${c.correoContacto || 'Sin Correo'}</td>
                        <td style="text-align: center;">
                            <button type="button" class="btn btn-outline btn-edit-contacto" data-id="${c.idContacto}" style="padding: 4px 8px; font-size: 12px; margin-right: 5px;"><i class="fa-solid fa-pen"></i> Editar</button>
                        </td>
                    </tr>
                `;
            });
            contactosTbody.innerHTML = contactosHtml;
        } else {
            contactosTbody.innerHTML = `<tr><td colspan="5" style="text-align:center;">No hay contactos registrados.</td></tr>`;
        }

        // --- REPRESENTANTE LEGAL ---
        const representantesTbody = document.getElementById('representantes-tbody');
        if (data.representantes && data.representantes.length > 0) {
            let repsHtml = '';
            data.representantes.forEach(r => {
                const tipoDoc = idCodigoMap[r.idTipoIdentificacion] || 'N/A';
                repsHtml += `
                    <tr>
                        <td style="text-align: left; padding-left: 15px;">${((r.nombres || '') + ' ' + (r.apellidos || '')).trim() || 'Sin Nombre'}</td>
                        <td style="text-align: center;">${tipoDoc}</td>
                        <td style="text-align: center;">${r.numeroIdentificacion || 'Sin ID'}</td>
                        <td style="text-align: center;">${r.telefono || 'Sin Teléfono'}</td>
                        <td style="text-align: center;">${r.correo || 'Sin Correo'}</td>
                    </tr>
                `;
            });
            representantesTbody.innerHTML = repsHtml;
        } else {
            representantesTbody.innerHTML = `<tr><td colspan="5" style="text-align:center;">No hay representantes registrados.</td></tr>`;
        }

        // --- INFORMACIÓN FINANCIERA Y COMERCIAL ---
        let tipoPagoMap = {};
        let tipoPagoList = [];
        try {
            const resTipoPago = await fetch(`${CONFIG.API_BASE_URL}/tipo_pago`);
            const jsonTipoPago = await resTipoPago.json();
            if (jsonTipoPago.data) {
                tipoPagoList = jsonTipoPago.data;
                jsonTipoPago.data.forEach(tp => {
                    tipoPagoMap[tp.idTipoPago] = tp.descripcion;
                });
            }
        } catch(e) {
            console.error("Error cargando tipo_pago:", e);
        }

        const pagosTbody = document.getElementById('pagos-tbody');
        let pagosList = [];
        try {
            const resPagos = await fetch(`${CONFIG.API_BASE_URL}/forma_de_pago`);
            const jsonPagos = await resPagos.json();
            if (jsonPagos.data) {
                pagosList = jsonPagos.data.filter(p => p.idProveedor === prov.idProveedor && p.activo !== false);
            }
        } catch(e) {
            console.error("Error cargando forma_de_pago:", e);
        }

        if (pagosList.length > 0) {
            let pagosHtml = '';
            pagosList.forEach(p => {
                const tipoDesc = tipoPagoMap[p.idTipoPago] || 'No registrado';
                const formattedMonto = p.monto ? new Intl.NumberFormat('es-CO', { style: 'currency', currency: 'COP', minimumFractionDigits: 0 }).format(p.monto) : '$ 0';
                pagosHtml += `
                    <tr>
                        <td style="text-align: left; padding-left: 15px;">${tipoDesc}</td>
                        <td style="text-align: center;">${prov.bancoReferencia || 'No registrado'}</td>
                        <td style="text-align: center;">${prov.tipoCuenta || 'No registrado'}</td>
                        <td style="text-align: center;">${prov.numCuenta || 'No registrado'}</td>
                        <td style="text-align: center;">${formattedMonto}</td>
                        <td style="text-align: center;">${p.plazo || 0} días</td>
                        <td style="text-align: center;">
                            <button type="button" class="btn btn-outline btn-edit-pago" data-id="${p.idFormaPago}" style="padding: 4px 8px; font-size: 12px; margin-right: 5px;"><i class="fa-solid fa-pen"></i> Editar</button>
                        </td>
                    </tr>
                `;
            });
            pagosTbody.innerHTML = pagosHtml;
        } else {
            pagosTbody.innerHTML = `<tr><td colspan="7" style="text-align:center;">No hay métodos de pago registrados.</td></tr>`;
        }

        // --- SOCIOS/ACCIONISTAS ---
        const sociosTbody = document.getElementById('socios-tbody');
        if (data.socios && data.socios.length > 0) {
            let sociosHtml = '';
            data.socios.forEach(s => {
                const tipoDoc = idCodigoMap[s.idTipoIdentificacion] || 'N/A';
                sociosHtml += `
                    <tr>
                        <td style="text-align: left; padding-left: 15px;">${((s.nombres || '') + ' ' + (s.apellidos || '')).trim() || 'Sin Nombre'}</td>
                        <td style="text-align: center;">${tipoDoc}</td>
                        <td style="text-align: center;">${s.numeroIdentificacion || 'Sin ID'}</td>
                        <td style="text-align: center;">${s.participacion ? s.participacion + '%' : 'N/A'}</td>
                        <td style="text-align: center;">${s.nacionalidad || 'Colombia'}</td>
                    </tr>
                `;
            });
            sociosTbody.innerHTML = sociosHtml;
        } else {
            sociosTbody.innerHTML = `<tr><td colspan="5" style="text-align:center;">No hay socios registrados.</td></tr>`;
        }

        // --- DOCUMENTOS ---
        const documentosTbody = document.getElementById('documentos-tbody');
        if (data.documentos && data.documentos.length > 0) {
            let tipoDocMap = {};
            try {
                const docTypesResponse = await fetch(`${CONFIG.API_BASE_URL}/tipo_documento`);
                const docTypesResult = await docTypesResponse.json();
                if (docTypesResult.data) {
                    docTypesResult.data.forEach(td => tipoDocMap[td.idTipoDocumento] = td.descripcion);
                }
            } catch(e) {}

            let docsHtml = '';
            data.documentos.forEach(d => {
                const tipoDesc = tipoDocMap[d.idTipoDocumento] || 'Documento';
                const nombreArchivo = d.urlDocumento ? d.urlDocumento.substring(d.urlDocumento.lastIndexOf('/') + 1) : 'archivo.pdf';
                const fechaCarga = d.fechaCarga ? new Date(d.fechaCarga).toLocaleDateString() : 'N/A';
                const badgeClass = d.estadoDocumento ? 'success' : 'warning';
                const estadoTexto = d.estadoDocumento ? 'Vigente' : 'Pendiente revisión';

                docsHtml += `
                    <tr>
                        <td>${tipoDesc}</td>
                        <td><a href="${d.urlDocumento || '#'}" target="_blank">${nombreArchivo}</a></td>
                        <td>${fechaCarga}</td>
                        <td><span class="badge ${badgeClass}">${estadoTexto}</span></td>
                        <td>
                            <a href="${d.urlDocumento || '#'}" target="_blank" title="Ver"><i class="fa-solid fa-eye action-icon"></i></a>
                            <a href="#" class="fa-download-file" data-url="${d.urlDocumento || '#'}" title="Descargar"><i class="fa-solid fa-download action-icon"></i></a>
                        </td>
                    </tr>
                `;
            });
            documentosTbody.innerHTML = docsHtml;

            // Manejador de descargas físicas
            documentosTbody.querySelectorAll('.fa-download-file').forEach(btn => {
                btn.addEventListener('click', (e) => {
                    e.preventDefault();
                    const url = btn.getAttribute('data-url');
                    if(url && url !== '#') {
                        window.open(url, '_blank');
                    } else {
                        alert('No hay URL de documento disponible.');
                    }
                });
            });
        } else {
            documentosTbody.innerHTML = `<tr><td colspan="5" style="text-align:center;">No se encontraron documentos cargados.</td></tr>`;
        }

        // --- CALIFICACIONES / EVALUACIONES ---
        const calificacionesTbody = document.getElementById('calificaciones-tbody');
        if (data.evaluaciones && data.evaluaciones.length > 0) {
            let califMap = {};
            try {
                const califCatResponse = await fetch(`${CONFIG.API_BASE_URL}/calificacion`);
                const califCatResult = await califCatResponse.json();
                if (califCatResult.data) {
                    califCatResult.data.forEach(c => califMap[c.idCalificacion] = c.descripcion);
                }
            } catch(e) {}

            // Ordenar de más reciente a más antigua
            const sortedEvaluaciones = [...data.evaluaciones].sort((a, b) => {
                const dateA = a.fechaCreado ? new Date(a.fechaCreado) : new Date(0);
                const dateB = b.fechaCreado ? new Date(b.fechaCreado) : new Date(0);
                return dateB - dateA;
            });

            // Mostrar solo los 3 últimos registros (más recientes)
            const topEvaluaciones = sortedEvaluaciones.slice(0, 3);

            let evalHtml = '';
            topEvaluaciones.forEach(ev => {
                const periodo = ev.fechaCreado ? `I - ${new Date(ev.fechaCreado).getFullYear()}` : 'N/A';
                
                let badgeClass = 'warning';
                if (ev.puntaje >= 80) badgeClass = 'success';
                else if (ev.puntaje < 60) badgeClass = 'danger';
                
                const estrellas = (ev.puntaje / 20).toFixed(1);
                const puntaje = ev.puntaje ? `<span class="badge ${badgeClass}" style="display: inline-block; min-width: 140px; text-align: center; font-size: 11px;">${estrellas} / 5 ⭐ (${ev.puntaje} pts)</span>` : 'N/A';
                const evaluador = 'Área de Compras'; // Valor descriptivo por defecto
                const fecha = ev.fechaCreado ? new Date(ev.fechaCreado).toLocaleDateString() : 'N/A';

                evalHtml += `
                    <tr>
                        <td style="text-align: center;">${periodo}</td>
                        <td style="text-align: center;">${puntaje}</td>
                        <td style="text-align: center;">${evaluador}</td>
                        <td style="text-align: center;">${fecha}</td>
                        <td style="text-align: center;">
                            <i class="fa-solid fa-eye action-icon" title="Ver" style="cursor: pointer; font-size: 14px;" onclick="alert('Detalle de calificación: ${ev.observaciones || 'Sin observaciones'}')"></i>
                        </td>
                    </tr>
                `;
            });
            calificacionesTbody.innerHTML = evalHtml;
        } else {
            calificacionesTbody.innerHTML = `<tr><td colspan="5" style="text-align:center;">No hay calificaciones registradas.</td></tr>`;
        }

        // --- VALIDACIONES DE RIESGO ---
        let validacionFecha = 'No registrada';
        let validacionResult = 'Sin coincidencia';
        let validacionNotes = 'Cumple todos los criterios de revisión de listas restrictivas.';
        try {
            const riskResponse = await fetch(`${CONFIG.API_BASE_URL}/evaluacion_riesgos`);
            const riskResult = await riskResponse.json();
            const lastRisk = riskResult.data ? riskResult.data.find(r => r.idProveedor === prov.idProveedor) : null;
            if (lastRisk) {
                validacionFecha = lastRisk.fechaCreado ? new Date(lastRisk.fechaCreado).toLocaleDateString() : 'No registrada';
                validacionResult = lastRisk.aprobado ? 'Aprobado sin coincidencias' : 'Coincidencias encontradas';
                validacionNotes = lastRisk.observaciones || 'Sin observaciones.';
            }
        } catch(e) {}

        document.getElementById('risk-last-date').textContent = validacionFecha;
        document.getElementById('risk-result').textContent = validacionResult;
        document.getElementById('risk-notes').textContent = validacionNotes;

        // --- LOGICA MODAL CONTACTO ---
        const modal = document.getElementById('contact-modal');
        const modalTitle = document.getElementById('modal-title');
        const modalForm = document.getElementById('contact-modal-form');
        const btnAddContact = document.getElementById('btn-add-profile-contact');
        const btnCancelModal = document.getElementById('btn-cancel-modal');
        const closeModalSpan = document.getElementById('close-modal');

        // Cargar catálogos del modal
        if (window.cargarTiposTelefono) {
            window.cargarTiposTelefono('modal-contact-tipo-tel');
        }

        function openModal(mode, contact = null) {
            modalForm.reset();
            document.getElementById('modal-contact-id').value = '';
            if (mode === 'add') {
                modalTitle.textContent = 'Agregar Contacto';
            } else {
                modalTitle.textContent = 'Editar Contacto';
                document.getElementById('modal-contact-id').value = contact.idContacto;
                
                // Dividir el nombre completo en Nombres y Apellidos
                const parts = contact.nombreContacto ? contact.nombreContacto.split(' ') : [];
                const nombres = parts.length > 0 ? parts.slice(0, -1).join(' ') || parts[0] : '';
                const apellidos = parts.length > 1 ? parts[parts.length - 1] : '';

                document.getElementById('modal-contact-nombres').value = nombres;
                document.getElementById('modal-contact-apellidos').value = apellidos;
                document.getElementById('modal-contact-cargo').value = contact.cargoContacto || '';
                document.getElementById('modal-contact-tipo-tel').value = contact.idTipoTelefono || '';
                document.getElementById('modal-contact-tel').value = contact.telefonoContacto || '';
                document.getElementById('modal-contact-email').value = contact.correoContacto || '';
            }
            modal.style.display = 'flex';
        }

        function closeModal() {
            modal.style.display = 'none';
        }

        if (btnAddContact) {
            btnAddContact.addEventListener('click', () => openModal('add'));
        }

        if (btnCancelModal) btnCancelModal.addEventListener('click', closeModal);
        if (closeModalSpan) closeModalSpan.addEventListener('click', closeModal);

        // Event listener para botones Editar (delegación de eventos en contactos-tbody)
        if (contactosTbody) {
            contactosTbody.addEventListener('click', (e) => {
                const btnEdit = e.target.closest('.btn-edit-contacto');
                if (btnEdit) {
                    const cId = parseInt(btnEdit.getAttribute('data-id'));
                    const contactObj = data.contactos.find(c => c.idContacto === cId);
                    if (contactObj) {
                        openModal('edit', contactObj);
                    }
                }
            });
        }

        // Envío del formulario
        if (modalForm) {
            modalForm.addEventListener('submit', async (evt) => {
                evt.preventDefault();
                const contactIdVal = document.getElementById('modal-contact-id').value;
                const nombres = document.getElementById('modal-contact-nombres').value;
                const apellidos = document.getElementById('modal-contact-apellidos').value;
                const cargo = document.getElementById('modal-contact-cargo').value;
                const idTipoTelefono = parseInt(document.getElementById('modal-contact-tipo-tel').value);
                const telefono = document.getElementById('modal-contact-tel').value;
                const correo = document.getElementById('modal-contact-email').value;

                const userId = localStorage.getItem('userId') ? parseInt(localStorage.getItem('userId')) : 1;
                const contactPayload = {
                    nombreContacto: `${nombres} ${apellidos}`.trim(),
                    cargoContacto: cargo,
                    idTipoTelefono,
                    telefonoContacto: telefono,
                    correoContacto: correo,
                    activo: true
                };

                if (contactIdVal === '') {
                    contactPayload.creadoPor = userId;
                    contactPayload.modificadoPor = null;
                } else {
                    contactPayload.modificadoPor = userId;
                }

                try {
                    if (contactIdVal === '') {
                        // Crear nuevo contacto
                        const response = await fetch(`${CONFIG.API_BASE_URL}/contacto`, {
                            method: 'POST',
                            headers: { 'Content-Type': 'application/json' },
                            body: JSON.stringify(contactPayload)
                        });
                        const resJson = await response.json();
                        if (resJson.data && resJson.data.idContacto) {
                            const newContactId = resJson.data.idContacto;
                            // Guardar relacion proveedor_contacto
                            await fetch(`${CONFIG.API_BASE_URL}/proveedor_contacto`, {
                                method: 'POST',
                                headers: { 'Content-Type': 'application/json' },
                                body: JSON.stringify({
                                    idProveedor: prov.idProveedor,
                                    idContacto: newContactId,
                                    estadoContacto: true,
                                    activo: true,
                                    creadoPor: userId
                                })
                            });
                            alert('Contacto creado exitosamente.');
                        } else {
                            throw new Error('No se pudo guardar el contacto.');
                        }
                    } else {
                        // Editar contacto existente
                        const response = await fetch(`${CONFIG.API_BASE_URL}/contacto/${contactIdVal}`, {
                            method: 'PUT',
                            headers: { 'Content-Type': 'application/json' },
                            body: JSON.stringify(contactPayload)
                        });
                        if (response.ok) {
                            alert('Contacto actualizado exitosamente.');
                        } else {
                            throw new Error('Error al actualizar contacto.');
                        }
                    }
                    closeModal();
                    window.location.reload(); // Recargar para mostrar los cambios
                } catch(err) {
                    console.error(err);
                    alert('Error: ' + err.message);
                }
            });
        }

        // --- LOGICA MODAL FORMA DE PAGO ---
        const paymentModal = document.getElementById('payment-modal');
        const paymentModalTitle = document.getElementById('payment-modal-title');
        const paymentModalForm = document.getElementById('payment-modal-form');
        const btnAddPayment = document.getElementById('btn-add-payment-method');
        const btnCancelPaymentModal = document.getElementById('btn-cancel-payment-modal');
        const closePaymentModalSpan = document.getElementById('close-payment-modal');
        const selectPaymentTipo = document.getElementById('modal-payment-tipo');

        // Poblar opciones del tipo de pago
        if (selectPaymentTipo) {
            let options = '<option value="">Seleccione un método...</option>';
            tipoPagoList.forEach(tp => {
                options += `<option value="${tp.idTipoPago}">${tp.descripcion}</option>`;
            });
            selectPaymentTipo.innerHTML = options;
        }

        function openPaymentModal(mode, pago = null) {
            paymentModalForm.reset();
            document.getElementById('modal-payment-id').value = '';
            
            // Cargar info bancaria por defecto desde el proveedor
            document.getElementById('modal-payment-banco').value = prov.bancoReferencia || '';
            document.getElementById('modal-payment-tipo-cuenta').value = prov.tipoCuenta || '';
            document.getElementById('modal-payment-num-cuenta').value = prov.numCuenta || '';

            if (mode === 'add') {
                paymentModalTitle.textContent = 'Agregar Método de Pago';
            } else {
                paymentModalTitle.textContent = 'Editar Método de Pago';
                document.getElementById('modal-payment-id').value = pago.idFormaPago;
                document.getElementById('modal-payment-tipo').value = pago.idTipoPago;
                document.getElementById('modal-payment-monto').value = pago.monto || '';
                document.getElementById('modal-payment-plazo').value = pago.plazo || '';
            }
            paymentModal.style.display = 'flex';
        }

        function closePaymentModal() {
            paymentModal.style.display = 'none';
        }

        if (btnAddPayment) {
            btnAddPayment.addEventListener('click', () => openPaymentModal('add'));
        }
        if (btnCancelPaymentModal) btnCancelPaymentModal.addEventListener('click', closePaymentModal);
        if (closePaymentModalSpan) closePaymentModalSpan.addEventListener('click', closePaymentModal);

        if (pagosTbody) {
            pagosTbody.addEventListener('click', (e) => {
                const btnEdit = e.target.closest('.btn-edit-pago');
                if (btnEdit) {
                    const pId = parseInt(btnEdit.getAttribute('data-id'));
                    const pagoObj = pagosList.find(p => p.idFormaPago === pId);
                    if (pagoObj) {
                        openPaymentModal('edit', pagoObj);
                    }
                }
            });
        }

        if (paymentModalForm) {
            paymentModalForm.addEventListener('submit', async (evt) => {
                evt.preventDefault();
                const paymentIdVal = document.getElementById('modal-payment-id').value;
                const idTipoPago = parseInt(document.getElementById('modal-payment-tipo').value);
                const banco = document.getElementById('modal-payment-banco').value;
                const tipoCuenta = document.getElementById('modal-payment-tipo-cuenta').value;
                const numCuenta = document.getElementById('modal-payment-num-cuenta').value;
                const monto = parseFloat(document.getElementById('modal-payment-monto').value);
                const plazo = parseInt(document.getElementById('modal-payment-plazo').value);

                const userId = localStorage.getItem('userId') ? parseInt(localStorage.getItem('userId')) : 1;
                
                try {
                    // 1. Actualizar info bancaria del proveedor
                    const supplierPayload = {
                        idTipoIdentificacion: prov.idTipoIdentificacion,
                        numeroIdentificacion: prov.numeroIdentificacion,
                        digitoVerificacion: prov.digitoVerificacion,
                        razonSocial: prov.razonSocial,
                        nombres: prov.nombres,
                        apellidos: prov.apellidos,
                        idTipoPersona: prov.idTipoPersona,
                        telefonoPrincipal: prov.telefonoPrincipal,
                        idTipoTelefono: prov.idTipoTelefono,
                        correoPrincipal: prov.correoPrincipal,
                        idActualizacionProveedor: prov.idActualizacionProveedor,
                        requiereActualizacion: prov.requiereActualizacion,
                        descripcion: prov.descripcion,
                        fechaCreado: prov.fechaCreado,
                        creadoPor: prov.creadoPor,
                        activo: prov.activo,
                        idEstadoProveedor: prov.idEstadoProveedor,
                        fechaAprobacion: prov.fechaAprobacion,
                        bancoReferencia: banco,
                        tipoCuenta: tipoCuenta,
                        numCuenta: numCuenta
                    };

                    const resSupplier = await fetch(`${CONFIG.API_BASE_URL}/proveedores/${prov.idProveedor}`, {
                        method: 'PUT',
                        headers: { 'Content-Type': 'application/json' },
                        body: JSON.stringify(supplierPayload)
                    });

                    if (!resSupplier.ok) {
                        throw new Error('No se pudo actualizar la información bancaria del proveedor.');
                    }

                    // 2. Crear o actualizar forma de pago
                    const paymentPayload = {
                        idProveedor: prov.idProveedor,
                        idTipoPago,
                        monto,
                        plazo,
                        activo: true
                    };

                    if (paymentIdVal === '') {
                        paymentPayload.creadoPor = userId;
                        paymentPayload.modificadoPor = null;
                    } else {
                        paymentPayload.modificadoPor = userId;
                    }

                    if (paymentIdVal === '') {
                        // Crear nuevo método
                        const response = await fetch(`${CONFIG.API_BASE_URL}/forma_de_pago`, {
                            method: 'POST',
                            headers: { 'Content-Type': 'application/json' },
                            body: JSON.stringify(paymentPayload)
                        });
                        if (response.ok) {
                            alert('Método de pago agregado exitosamente.');
                        } else {
                            throw new Error('Error al agregar método de pago.');
                        }
                    } else {
                        // Editar existente
                        const response = await fetch(`${CONFIG.API_BASE_URL}/forma_de_pago/${paymentIdVal}`, {
                            method: 'PUT',
                            headers: { 'Content-Type': 'application/json' },
                            body: JSON.stringify(paymentPayload)
                        });
                        if (response.ok) {
                            alert('Método de pago actualizado exitosamente.');
                        } else {
                            throw new Error('Error al actualizar método de pago.');
                        }
                    }
                    closePaymentModal();
                    window.location.reload();
                } catch(err) {
                    console.error(err);
                    alert('Error: ' + err.message);
                }
            });
        }

    } catch (error) {
        console.error("Error al cargar la información del proveedor:", error);
    }
});

function generarFichaPDF() {
    if (!currentSupplierData || !currentSupplierData.proveedor) {
        alert("No hay datos cargados del proveedor para descargar.");
        return;
    }

    const prov = currentSupplierData.proveedor;
    const contact = currentSupplierData.contactos && currentSupplierData.contactos.length > 0 ? currentSupplierData.contactos[0] : null;
    const representantes = currentSupplierData.representantes || [];
    const socios = currentSupplierData.socios || [];

    const { jsPDF } = window.jspdf;
    const doc = new jsPDF({
        orientation: 'portrait',
        unit: 'mm',
        format: 'a4'
    });

    // Títulos y tipografía
    doc.setFont("helvetica", "normal");

    // Cabecera Principal con un Banner o Título Grande
    doc.setFillColor(30, 30, 47); // Color azul oscuro (#1E1E2F)
    doc.rect(0, 0, 210, 35, 'F');

    doc.setFontSize(22);
    doc.setTextColor(212, 163, 115); // Color dorado accent (#D4A373)
    doc.setFont("helvetica", "bold");
    doc.text("FICHA DE PROVEEDOR", 20, 23);

    doc.setFontSize(10);
    doc.setTextColor(255, 255, 255);
    doc.setFont("helvetica", "normal");
    const todayStr = new Date().toLocaleDateString('es-ES', { year: 'numeric', month: 'long', day: 'numeric' });
    doc.text(`Generada el ${todayStr}`, 190, 23, { align: "right" });

    let y = 48;
    doc.setTextColor(51, 51, 51);

    // Nombre del proveedor
    const nameText = prov.razonSocial || `${prov.nombres || ''} ${prov.apellidos || ''}`.trim() || 'Sin nombre';
    doc.setFontSize(16);
    doc.setFont("helvetica", "bold");
    doc.text(nameText.toUpperCase(), 20, y);
    y += 6;

    doc.setFontSize(11);
    doc.setFont("helvetica", "normal");
    doc.text(`NIT / Identificación: ${prov.numeroIdentificacion || 'Sin NIT'}`, 20, y);
    y += 10;

    // Función auxiliar para dibujar secciones
    function drawSectionHeader(title) {
        doc.setFillColor(242, 242, 242);
        doc.rect(20, y, 170, 7, 'F');
        doc.setFont("helvetica", "bold");
        doc.setFontSize(10);
        doc.setTextColor(30, 30, 47);
        doc.text(title.toUpperCase(), 23, y + 5);
        y += 12;
        doc.setFont("helvetica", "normal");
        doc.setTextColor(51, 51, 51);
    }

    // Información General
    drawSectionHeader("Información General");
    doc.setFontSize(9);
    doc.text(`Correo Electrónico: ${prov.correoPrincipal || 'No registrado'}`, 20, y);
    doc.text(`Teléfono Principal: ${prov.telefonoPrincipal || 'No registrado'}`, 110, y);
    y += 6;
    
    let ciudadTexto = 'No registrada';
    let direccionTexto = 'No registrada';
    const addressEl = document.getElementById('info-address');
    const locationEl = document.getElementById('info-location');
    if (addressEl) direccionTexto = addressEl.textContent;
    if (locationEl) ciudadTexto = locationEl.textContent;

    doc.text(`Ubicación: ${ciudadTexto}`, 20, y);
    doc.text(`Dirección: ${direccionTexto}`, 110, y);
    y += 12;

    // Contacto Comercial
    drawSectionHeader("Contacto Comercial");
    if (contact) {
        doc.text(`Nombre: ${contact.nombreContacto || 'No registrado'}`, 20, y);
        doc.text(`Cargo: ${contact.cargoContacto || 'No registrado'}`, 110, y);
        y += 6;
        doc.text(`Correo: ${contact.correoContacto || 'No registrado'}`, 20, y);
        doc.text(`Teléfono: ${contact.telefonoContacto || 'No registrado'}`, 110, y);
    } else {
        doc.text("No se encontró contacto comercial registrado.", 20, y);
    }
    y += 12;

    // Representantes Legales
    drawSectionHeader("Representantes Legales");
    if (representantes.length > 0) {
        representantes.forEach(r => {
            const repName = `${r.nombres || ''} ${r.apellidos || ''}`.trim() || 'No registrado';
            doc.text(`Nombre: ${repName}`, 20, y);
            doc.text(`Documento: ${r.numeroIdentificacion || 'No registrado'}`, 110, y);
            y += 6;
            doc.text(`Correo: ${r.correo || 'No registrado'}`, 20, y);
            doc.text(`Teléfono: ${r.telefono || 'No registrado'}`, 110, y);
            y += 8;

            if (y > 270) {
                doc.addPage();
                y = 25;
            }
        });
    } else {
        doc.text("No se encontraron representantes legales registrados.", 20, y);
        y += 6;
    }
    y += 4;

    // Información Financiera y Comercial
    drawSectionHeader("Información Financiera y Comercial");
    doc.text(`Banco: ${prov.bancoReferencia || 'No registrado'}`, 20, y);
    doc.text(`Tipo de Cuenta: ${prov.tipoCuenta || 'No registrado'}`, 110, y);
    y += 6;
    doc.text(`Número de Cuenta: ${prov.numCuenta || 'No registrado'}`, 20, y);
    
    let condicionesPago = 'No registradas';
    const termsEl = document.getElementById('fin-terms');
    if (termsEl) condicionesPago = termsEl.textContent;
    doc.text(`Condiciones de Pago: ${condicionesPago}`, 110, y);
    y += 12;

    // Socios / Accionistas
    if (socios.length > 0) {
        if (y > 220) {
            doc.addPage();
            y = 25;
        }
        drawSectionHeader("Socios / Accionistas");
        
        doc.setFont("helvetica", "bold");
        doc.text("Nombre", 20, y);
        doc.text("Documento", 85, y);
        doc.text("Participación", 135, y);
        doc.text("Origen", 165, y);
        y += 4;
        doc.line(20, y, 190, y);
        y += 6;
        doc.setFont("helvetica", "normal");

        socios.forEach(s => {
            const sName = s.nombreCompleto || `${s.nombres || ''} ${s.apellidos || ''}`.trim() || 'Sin nombre';
            doc.text(sName, 20, y);
            doc.text(s.numeroIdentificacion || 'Sin ID', 85, y);
            doc.text(s.participacion ? `${s.participacion}%` : 'N/A', 135, y);
            doc.text(s.nacionalidad || 'Colombia', 165, y);
            y += 6;

            if (y > 270) {
                doc.addPage();
                y = 25;
            }
        });
    }

    // Descargar PDF
    const filename = `ficha_${nameText.toLowerCase().replace(/\s+/g, '_')}.pdf`;
    doc.save(filename);
}
