document.addEventListener('DOMContentLoaded', async () => {
    // --- 0. Pre-completar datos del proveedor ---
    const userEmail = localStorage.getItem('userEmail');
    const userRole = localStorage.getItem('userRole');

    // Cargar catálogos primero para que las selecciones funcionen
    await Promise.all([
        cargarTiposPersona("type of person"),
        cargarTiposIdentificacion("document-type company"),
        cargarPaises("country"),
        cargarTiposIdentificacion("document-type-c1"),
        cargarTiposIdentificacion("document-type-r1"),
        cargarTiposPago("payment_method"),
        cargarTiposTelefono("phone-type company"),
        cargarTiposTelefono("phone-type-c1"),
        cargarTiposTelefono("phone-type-r1")
    ]);

    if (userRole === 'proveedor' && userEmail) {
        precompletarDatosProveedor(userEmail);
    }

    // --- 1. Lógica para restringir caracteres en nombres y cargos (Solo letras y espacios) ---
    function aplicarFiltroTexto(inputElement) {
        if (inputElement) {
            inputElement.addEventListener("input", function () {
                this.value = this.value.replace(/[^a-zA-ZáéíóúÁÉÍÓÚñÑ\s]/g, "");
            });
        }
    }

    const textFields = [
        "company name", "company category",
        "first-name-c1", "last-name-c1", "position-c1",
        "first-name-r1", "last-name-r1", "position-r1", "nationality-r1",
        "bank_name"
    ];
    textFields.forEach(id => aplicarFiltroTexto(document.getElementById(id)));

    // --- 2. Lógica para restringir a solo números (Teléfonos, Cuentas, Documentos) ---
    function aplicarFiltroNumero(inputElement) {
        if (inputElement) {
            inputElement.addEventListener("input", function () {
                this.value = this.value.replace(/[^0-9]/g, "");
            });
        }
    }

    const numberFields = [
        "document-number company", "phone company", "account-number", "ciiu",
        "document-number-c1", "phone-c1",
        "document-number-r1", "phone-r1"
    ];
    numberFields.forEach(id => aplicarFiltroNumero(document.getElementById(id)));

    // --- 3. Lógica de cascada para ubicación (País -> Departamento -> Municipio) ---
    const countrySelect = document.getElementById('country');
    const deptoSelect = document.getElementById('departament');
    const citySelect = document.getElementById('city');

    if (countrySelect) {
        countrySelect.addEventListener('change', function () {
            const paisId = this.value;
            if (paisId) {
                cargarDepartamentos("departament", paisId);
            } else {
                if (deptoSelect) deptoSelect.innerHTML = '<option value="">Departamento (*)</option>';
                if (citySelect) citySelect.innerHTML = '<option value="">Ciudad / Municipio (*)</option>';
            }
        });
    }

    if (deptoSelect) {
        deptoSelect.addEventListener('change', function () {
            const deptoId = this.value;
            if (deptoId) {
                cargarMunicipios("city", deptoId);
            } else {
                if (citySelect) citySelect.innerHTML = '<option value="">Ciudad / Municipio (*)</option>';
            }
        });
    }

    // --- 4. Renderizado Condicional (+) Persona Jurídica vs Persona Natural ---
    const typeOfPersonSelect = document.getElementById('type of person');
    const seccionRepresentante = document.getElementById('seccion-representante');
    const seccionSocios = document.getElementById('seccion-socios');
    const containerChkNatural = document.getElementById('container-chk-natural');

    const repRequiredInputs = [
        "first-name-r1", "last-name-r1", "position-r1", "document-type-r1",
        "document-number-r1", "nationality-r1", "phone-type-r1", "phone-r1", "email-r1"
    ];

    function actualizarRenderizadoCondicional() {
        const tipoPersona = typeOfPersonSelect.value;

        if (tipoPersona === "1") { // 1 = Persona Natural
            seccionRepresentante.classList.add('hidden-section');
            seccionSocios.classList.add('hidden-section');
            containerChkNatural.classList.remove('hidden-section');

            // Quitar required de representante
            repRequiredInputs.forEach(id => {
                const el = document.getElementById(id);
                if (el) el.required = false;
            });
        } else { // Persona Jurídica u otro
            seccionRepresentante.classList.remove('hidden-section');
            seccionSocios.classList.remove('hidden-section');
            containerChkNatural.classList.add('hidden-section');

            // Habilitar required de representante
            repRequiredInputs.forEach(id => {
                const el = document.getElementById(id);
                if (el) el.required = true;
            });

            // Desmarcar check de persona natural si estaba marcado
            const chkNatural = document.getElementById('same-as-natural');
            if (chkNatural && chkNatural.checked) {
                chkNatural.checked = false;
                chkNatural.dispatchEvent(new Event('change'));
            }
        }
    }

    if (typeOfPersonSelect) {
        typeOfPersonSelect.addEventListener('change', actualizarRenderizadoCondicional);
    }

    // --- 5. Lógica de "Mismo que Persona Natural" para Contacto ---
    const sameAsNaturalCheck = document.getElementById('same-as-natural');
    const naturalMap = [
        ['document-type company', 'document-type-c1'],
        ['document-number company', 'document-number-c1'],
        ['phone-type company', 'phone-type-c1'],
        ['phone company', 'phone-c1'],
        ['email company', 'email-c1']
    ];

    function syncNaturalFields() {
        const isChecked = sameAsNaturalCheck.checked;
        const nombreEmpresa = document.getElementById('company name')?.value || '';
        const firstNameC1 = document.getElementById('first-name-c1');
        const lastNameC1 = document.getElementById('last-name-c1');
        const positionC1 = document.getElementById('position-c1');

        if (isChecked) {
            // Dividir nombre en nombres y apellidos
            const partes = nombreEmpresa.trim().split(' ');
            if (firstNameC1) {
                firstNameC1.value = partes[0] || '';
                firstNameC1.readOnly = true;
                firstNameC1.style.backgroundColor = '#f4f4f4';
            }
            if (lastNameC1) {
                lastNameC1.value = partes.slice(1).join(' ') || '';
                lastNameC1.readOnly = true;
                lastNameC1.style.backgroundColor = '#f4f4f4';
            }
            if (positionC1) {
                positionC1.value = "Propietario";
                positionC1.readOnly = true;
                positionC1.style.backgroundColor = '#f4f4f4';
            }

            naturalMap.forEach(([sourceId, targetId]) => {
                const sourceEl = document.getElementById(sourceId);
                const targetEl = document.getElementById(targetId);
                if (sourceEl && targetEl) {
                    targetEl.value = sourceEl.value;
                    targetEl.readOnly = true;
                    if (targetEl.tagName === 'SELECT') {
                        targetEl.style.pointerEvents = 'none';
                    }
                    targetEl.style.backgroundColor = '#f4f4f4';
                }
            });
        } else {
            if (firstNameC1) { firstNameC1.readOnly = false; firstNameC1.style.backgroundColor = ''; }
            if (lastNameC1) { lastNameC1.readOnly = false; lastNameC1.style.backgroundColor = ''; }
            if (positionC1) { positionC1.readOnly = false; positionC1.style.backgroundColor = ''; }

            naturalMap.forEach(([_, targetId]) => {
                const targetEl = document.getElementById(targetId);
                if (targetEl) {
                    targetEl.readOnly = false;
                    if (targetEl.tagName === 'SELECT') {
                        targetEl.style.pointerEvents = 'auto';
                    }
                    targetEl.style.backgroundColor = '';
                }
            });
        }
    }

    if (sameAsNaturalCheck) {
        sameAsNaturalCheck.addEventListener('change', syncNaturalFields);
    }

    // --- 6. Lógica de "Mismo que contacto" para Representante Legal Principal ---
    const sameAsContactCheck = document.getElementById('same-as-contact');
    const contactToRepMap = [
        ['first-name-c1', 'first-name-r1'],
        ['last-name-c1', 'last-name-r1'],
        ['position-c1', 'position-r1'],
        ['document-type-c1', 'document-type-r1'],
        ['document-number-c1', 'document-number-r1'],
        ['phone-type-c1', 'phone-type-r1'],
        ['phone-c1', 'phone-r1'],
        ['email-c1', 'email-r1']
    ];

    function syncRepFields() {
        const isChecked = sameAsContactCheck.checked;

        contactToRepMap.forEach(([contactId, repId]) => {
            const contactEl = document.getElementById(contactId);
            const repEl = document.getElementById(repId);

            if (contactEl && repEl) {
                if (isChecked) {
                    repEl.value = contactEl.value;
                    repEl.readOnly = true;
                    if (repEl.tagName === 'SELECT') {
                        repEl.style.pointerEvents = 'none';
                    }
                    repEl.style.backgroundColor = '#f4f4f4';
                } else {
                    repEl.readOnly = false;
                    if (repEl.tagName === 'SELECT') {
                        repEl.style.pointerEvents = 'auto';
                    }
                    repEl.style.backgroundColor = '';
                }
            }
        });
    }

    if (sameAsContactCheck) {
        sameAsContactCheck.addEventListener('change', syncRepFields);
        contactToRepMap.forEach(([contactId, _]) => {
            const contactEl = document.getElementById(contactId);
            if (contactEl) {
                contactEl.addEventListener('input', () => { if (sameAsContactCheck.checked) syncRepFields(); });
                contactEl.addEventListener('change', () => { if (sameAsContactCheck.checked) syncRepFields(); });
            }
        });
    }

    // --- 7. Gestión Dinámica de Representantes Legales Suplentes ---
    const btnAddRepSuplente = document.getElementById('btn-add-rep-suplente');
    const repsSuplentesContainer = document.getElementById('reps-suplentes-container');
    let repSuplenteCount = 0;

    if (btnAddRepSuplente && repsSuplentesContainer) {
        btnAddRepSuplente.addEventListener('click', async () => {
            repSuplenteCount++;
            const div = document.createElement('div');
            div.className = 'rep-suplente-item';
            div.style.borderTop = '1px dashed var(--border-color)';
            div.style.paddingTop = '15px';
            div.style.marginTop = '15px';
            div.innerHTML = `
                <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 10px;">
                    <h5 style="color: var(--accent-color); margin: 0;">Representante Legal Suplente #${repSuplenteCount}</h5>
                    <button type="button" class="btn action-btn btn-remove-rep" style="background-color: var(--danger-bg); color: var(--danger-text); padding: 5px 10px; font-size: 12px;"><i class="fa-solid fa-trash"></i> Eliminar</button>
                </div>
                <div class="names">
                    <div class="form-group"><input type="text" class="rep-nombre" placeholder="Nombres (*)" required /></div>
                    <div class="form-group"><input type="text" class="rep-apellido" placeholder="Apellidos (*)" required /></div>
                </div>
                <div class="document">
                    <div class="form-group"><select class="rep-doc-type" required><option value="">Tipo Id (*)</option></select></div>
                    <div class="form-group"><input type="text" class="rep-doc-num" placeholder="Número de documento (*)" required /></div>
                </div>
                <div class="dates3">
                    <div class="form-group"><input type="tel" class="rep-tel" placeholder="Número de teléfono (*)" required /></div>
                    <div class="form-group"><input type="email" class="rep-email" placeholder="Correo Electrónico (*)" required /></div>
                </div>
            `;

            repsSuplentesContainer.appendChild(div);

            // Cargar catálogo de tipos de identificación para este nuevo select
            const selectDoc = div.querySelector('.rep-doc-type');
            await cargarTiposIdentificacion(selectDoc);

            // Filtros
            aplicarFiltroTexto(div.querySelector('.rep-nombre'));
            aplicarFiltroTexto(div.querySelector('.rep-apellido'));
            aplicarFiltroNumero(div.querySelector('.rep-doc-num'));
            aplicarFiltroNumero(div.querySelector('.rep-tel'));

            // Botón eliminar
            div.querySelector('.btn-remove-rep').addEventListener('click', () => {
                div.remove();
            });
        });
    }

    // Sobrecarga de cargarTiposIdentificacion para aceptar elemento DOM directo
    const originalCargarTiposIdentificacion = window.cargarTiposIdentificacion;
    window.cargarTiposIdentificacion = function (target) {
        if (typeof target === 'string') {
            return originalCargarTiposIdentificacion(target);
        } else if (target && target.tagName === 'SELECT') {
            // Usar una función auxiliar para llenar un select DOM directo
            const url = `${CONFIG.API_BASE_URL}/tipo_identificacion`;
            return fetch(url).then(res => res.json()).then(result => {
                if (result.data) {
                    target.innerHTML = '<option value="">Tipo Id (*)</option>';
                    result.data.forEach(item => {
                        const opt = document.createElement('option');
                        opt.value = item.idTipoIdentificacion;
                        opt.textContent = item.descripcion;
                        target.appendChild(opt);
                    });
                }
            });
        }
    };

    // Sobrecarga de cargarTiposPersona para aceptar elemento DOM directo
    const originalCargarTiposPersona = window.cargarTiposPersona;
    window.cargarTiposPersona = function (target) {
        if (typeof target === 'string') {
            return originalCargarTiposPersona(target);
        } else if (target && target.tagName === 'SELECT') {
            const url = `${CONFIG.API_BASE_URL}/tipo_persona`;
            return fetch(url).then(res => res.json()).then(result => {
                if (result.data) {
                    target.innerHTML = '<option value="">Tipo Persona (*)</option>';
                    result.data.forEach(item => {
                        const opt = document.createElement('option');
                        opt.value = item.idTipoPersona;
                        opt.textContent = item.descripcion;
                        target.appendChild(opt);
                    });
                }
            });
        }
    };

    // --- 8. Gestión Dinámica de Socios / Accionistas (>5%) ---
    const btnAddSocio = document.getElementById('btn-add-socio');
    const tbodySocios = document.getElementById('tbody-socios');

    if (btnAddSocio && tbodySocios) {
        btnAddSocio.addEventListener('click', async () => {
            const tr = document.createElement('tr');
            tr.className = 'socio-row';
            tr.innerHTML = `
                <td class="table-inputs"><select class="socio-tipo-pers" required><option value="">Tipo (*)</option></select></td>
                <td class="table-inputs"><input type="text" class="socio-nombre" placeholder="Nombre Completo (*)" required /></td>
                <td class="table-inputs"><select class="socio-tipo-doc" required><option value="">Tipo ID (*)</option></select></td>
                <td class="table-inputs"><input type="text" class="socio-doc-num" placeholder="Número ID (*)" required /></td>
                <td class="table-inputs"><input type="number" step="0.01" class="socio-part" placeholder="0.00" required /></td>
                <td class="table-inputs"><input type="text" class="socio-nac" placeholder="Nacionalidad (*)" required /></td>
                <td style="text-align: center;"><button type="button" class="btn action-btn btn-remove-socio" style="background-color: var(--danger-bg); color: var(--danger-text); padding: 5px 10px; font-size: 12px;"><i class="fa-solid fa-trash"></i></button></td>
            `;

            tbodySocios.appendChild(tr);

            // Cargar catálogos para los selects de esta fila
            await Promise.all([
                cargarTiposPersona(tr.querySelector('.socio-tipo-pers')),
                cargarTiposIdentificacion(tr.querySelector('.socio-tipo-doc'))
            ]);

            aplicarFiltroTexto(tr.querySelector('.socio-nombre'));
            aplicarFiltroNumero(tr.querySelector('.socio-doc-num'));
            aplicarFiltroTexto(tr.querySelector('.socio-nac'));

            tr.querySelector('.btn-remove-socio').addEventListener('click', () => {
                tr.remove();
            });
        });
    }

    // --- 9. Lógica principal del formulario (Submit) ---
    const form = document.querySelector('.user-form');
    if (form) {
        form.addEventListener('submit', (e) => {
            e.preventDefault();

            // Objeto principal para enviar al backend
            const formData = {
                empresa: {
                    nombre: document.getElementById('company name').value,
                    idTipoPersona: parseInt(document.getElementById('type of person').value),
                    categoria: document.getElementById('company category').value,
                    idTipoIdentificacion: parseInt(document.getElementById('document-type company').value),
                    numeroIdentificacion: document.getElementById('document-number company').value,
                    ciiu: document.getElementById('ciiu').value,
                    paginaWeb: document.getElementById('pagina_web').value,
                    idTipoTelefono: parseInt(document.getElementById('phone-type company').value),
                    telefono: document.getElementById('phone company').value,
                    correo: document.getElementById('email company').value,
                    idPais: parseInt(document.getElementById('country').value),
                    idDepartamento: parseInt(document.getElementById('departament').value),
                    idMunicipio: parseInt(document.getElementById('city').value),
                    direccion: document.getElementById('addrees').value
                },
                contacto: {
                    nombres: document.getElementById('first-name-c1').value,
                    apellidos: document.getElementById('last-name-c1').value,
                    cargo: document.getElementById('position-c1').value,
                    idTipoIdentificacion: parseInt(document.getElementById('document-type-c1').value),
                    numeroIdentificacion: document.getElementById('document-number-c1').value,
                    idTipoTelefono: parseInt(document.getElementById('phone-type-c1').value),
                    telefono: document.getElementById('phone-c1').value,
                    correo: document.getElementById('email-c1').value
                },
                representantes: [],
                socios: [],
                bancaria: {
                    tipoCuenta: document.getElementById('account type').value,
                    numeroCuenta: document.getElementById('account-number').value,
                    banco: document.getElementById('bank_name').value,
                    idMetodoPago: parseInt(document.getElementById('payment_method').value)
                },
                financiera: {
                    activos: parseFloat(document.getElementById('activos').value),
                    pasivos: parseFloat(document.getElementById('pasivos').value),
                    patrimonio: parseFloat(document.getElementById('patrimonio').value),
                    totalIngresos: parseFloat(document.getElementById('total_ingresos').value),
                    totalGastos: parseFloat(document.getElementById('total_gastos').value)
                },
                laft: {
                    p1: document.querySelector('input[name="laft_p1"]:checked')?.value === 'true',
                    p2: document.querySelector('input[name="laft_p2"]:checked')?.value === 'true',
                    p3: document.querySelector('input[name="laft_p3"]:checked')?.value === 'true',
                    p4: document.querySelector('input[name="laft_p4"]:checked')?.value === 'true',
                    p5: document.querySelector('input[name="laft_p5"]:checked')?.value === 'true'
                }
            };

            // Agregar Representante Principal si es Persona Jurídica
            if (formData.empresa.idTipoPersona !== 1) { // 1 = Persona Natural
                formData.representantes.push({
                    esPrincipal: true,
                    nombres: document.getElementById('first-name-r1').value,
                    apellidos: document.getElementById('last-name-r1').value,
                    cargo: document.getElementById('position-r1').value,
                    idTipoIdentificacion: parseInt(document.getElementById('document-type-r1').value),
                    numeroIdentificacion: document.getElementById('document-number-r1').value,
                    nacionalidad: document.getElementById('nationality-r1').value,
                    idTipoTelefono: parseInt(document.getElementById('phone-type-r1').value),
                    telefono: document.getElementById('phone-r1').value,
                    correo: document.getElementById('email-r1').value
                });

                // Agregar Representantes Suplentes dinámicos
                const suplentes = document.querySelectorAll('.rep-suplente-item');
                suplentes.forEach(item => {
                    formData.representantes.push({
                        esPrincipal: false,
                        nombres: item.querySelector('.rep-nombre').value,
                        apellidos: item.querySelector('.rep-apellido').value,
                        cargo: "Suplente",
                        idTipoIdentificacion: parseInt(item.querySelector('.rep-doc-type').value),
                        numeroIdentificacion: item.querySelector('.rep-doc-num').value,
                        nacionalidad: "No especificada",
                        idTipoTelefono: 1, // Por defecto
                        telefono: item.querySelector('.rep-tel').value,
                        correo: item.querySelector('.rep-email').value
                    });
                });

                // Agregar Socios dinámicos
                const sociosRows = document.querySelectorAll('.socio-row');
                sociosRows.forEach(row => {
                    formData.socios.push({
                        idTipoPersona: parseInt(row.querySelector('.socio-tipo-pers').value),
                        nombreCompleto: row.querySelector('.socio-nombre').value,
                        idTipoIdentificacion: parseInt(row.querySelector('.socio-tipo-doc').value),
                        numeroIdentificacion: row.querySelector('.socio-doc-num').value,
                        participacion: parseFloat(row.querySelector('.socio-part').value),
                        nacionalidad: row.querySelector('.socio-nac').value
                    });
                });
            }

            const userId = localStorage.getItem('userId');

            fetch(`${CONFIG.API_BASE_URL}/proveedores/registro-completo`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ ...formData, idUsuarioAsignado: userId })
            })
                .then(response => {
                    if (response.ok) return response.json();
                    throw new Error("Error en la petición");
                })
                .then(result => {
                    alert('¡Toda la información ha sido guardada exitosamente!');
                    form.reset();
                    if (repsSuplentesContainer) repsSuplentesContainer.innerHTML = '';
                    if (tbodySocios) tbodySocios.innerHTML = '';
                    actualizarRenderizadoCondicional();
                })
                .catch(error => {
                    console.error(error);
                    alert('Hubo un error al guardar la información. Verifica que el servidor Backend esté ejecutándose.');
                });
        });
    }

    const resetBtn = document.querySelector('.btn-reset');
    if (resetBtn) {
        resetBtn.addEventListener('click', (event) => {
            if (!confirm('¿Estás seguro de que deseas limpiar todo el formulario?')) {
                event.preventDefault();
            } else {
                setTimeout(() => {
                    if (repsSuplentesContainer) repsSuplentesContainer.innerHTML = '';
                    if (tbodySocios) tbodySocios.innerHTML = '';
                    actualizarRenderizadoCondicional();
                }, 100);
            }
        });
    }

    // --- FUNCIONES DE SOPORTE ---
    async function precompletarDatosProveedor(email) {
        try {
            const response = await fetch(`${CONFIG.API_BASE_URL}/proveedores/by-email?email=${email}`);
            const result = await response.json();

            if (result.data) {
                const prov = result.data;

                const nameInput = document.getElementById('company name');
                let nombreMostrar = '';
                if (nameInput) {
                    if (prov.idTipoPersona === 2) {
                        nombreMostrar = prov.razonSocial || '';
                    } else {
                        nombreMostrar = `${prov.nombres || ''} ${prov.apellidos || ''}`.trim();
                    }
                    nameInput.value = nombreMostrar;
                }

                const personTypeSelect = document.getElementById('type of person');
                if (personTypeSelect) {
                    personTypeSelect.value = prov.idTipoPersona;
                    actualizarRenderizadoCondicional();
                }

                const docTypeSelect = document.getElementById('document-type company');
                if (docTypeSelect) docTypeSelect.value = prov.idTipoIdentificacion;

                const docNumInput = document.getElementById('document-number company');
                if (docNumInput) docNumInput.value = prov.numeroIdentificacion || '';

                const emailInput = document.getElementById('email company');
                if (emailInput) {
                    emailInput.value = prov.correoPrincipal || email;
                    emailInput.readOnly = true;
                    emailInput.style.backgroundColor = '#f4f4f4';
                }

                // Actualizar etiquetas de la declaración de fondos
                const lblNombre = document.getElementById('lbl-declarante-nombre');
                const lblDoc = document.getElementById('lbl-declarante-doc');
                if (lblNombre) lblNombre.textContent = nombreMostrar || '[Nombre del Proveedor]';
                if (lblDoc) lblDoc.textContent = prov.numeroIdentificacion || '[Número de Documento]';
            }
        } catch (e) {
            console.error("Error pre-completando datos:", e);
        }
    }
});
