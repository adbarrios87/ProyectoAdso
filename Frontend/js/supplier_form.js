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

    if (userRole && userRole.toLowerCase() === 'proveedor' && userEmail) {
        precompletarDatosProveedor(userEmail);
    } else {
        renderizarCargadoresDocumentos(1); // Natural por defecto
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
        "company name",
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

    function actualizarDeclaracionOrigenFondos() {
        const tipoPersona = typeOfPersonSelect?.value;
        const lblNombre = document.getElementById('lbl-declarante-nombre');
        const lblDoc = document.getElementById('lbl-declarante-doc');
        if (!lblNombre || !lblDoc) return;

        if (tipoPersona !== "1") { // Persona Jurídica u otro
            const nombresRep = document.getElementById('first-name-r1')?.value || '';
            const apellidosRep = document.getElementById('last-name-r1')?.value || '';
            const docRep = document.getElementById('document-number-r1')?.value || '';
            const nombreCompleto = `${nombresRep} ${apellidosRep}`.trim();

            lblNombre.textContent = nombreCompleto || '[Nombre del Representante Legal]';
            lblDoc.textContent = docRep || '[Documento del Representante Legal]';
        } else { // Persona Natural
            const nombreNatural = document.getElementById('company name')?.value || '';
            const docNatural = document.getElementById('document-number company')?.value || '';

            lblNombre.textContent = nombreNatural || '[Nombre de la Persona Natural]';
            lblDoc.textContent = docNatural || '[Documento de la Persona Natural]';
        }
    }

    // Registrar escuchadores de eventos para actualizar en tiempo real
    const inputsParaDeclaracion = [
        'company name',
        'document-number company',
        'first-name-r1',
        'last-name-r1',
        'document-number-r1'
    ];
    inputsParaDeclaracion.forEach(id => {
        const el = document.getElementById(id);
        if (el) {
            el.addEventListener('input', actualizarDeclaracionOrigenFondos);
            el.addEventListener('change', actualizarDeclaracionOrigenFondos);
        }
    });

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
        actualizarDeclaracionOrigenFondos();
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
        actualizarDeclaracionOrigenFondos();
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
        actualizarDeclaracionOrigenFondos();
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

    // Sobrecarga de cargarTiposTelefono para aceptar elemento DOM directo
    const originalCargarTiposTelefono = window.cargarTiposTelefono;
    window.cargarTiposTelefono = function (target) {
        if (typeof target === 'string') {
            return originalCargarTiposTelefono(target);
        } else if (target && target.tagName === 'SELECT') {
            const url = `${CONFIG.API_BASE_URL}/tipo_telefono`;
            return fetch(url).then(res => res.json()).then(result => {
                if (result.data) {
                    target.innerHTML = '<option value="">Tipo de teléfono (*)</option>';
                    result.data.forEach(item => {
                        const opt = document.createElement('option');
                        opt.value = item.idTipoTelefono;
                        opt.textContent = item.descripcion;
                        target.appendChild(opt);
                    });
                }
            });
        }
    };

    // --- Gestión Dinámica de Múltiples Contactos ---
    const btnAddContacto = document.getElementById('btn-add-contacto');
    const contactosContainer = document.getElementById('contactos-container');
    let contactoCounter = 1;

    if (btnAddContacto && contactosContainer) {
        btnAddContacto.addEventListener('click', async () => {
            contactoCounter++;
            const div = document.createElement('div');
            div.className = 'contacto-item card';
            div.style = 'border: 1px solid var(--border-color); padding: 15px; border-radius: 8px; margin-bottom: 15px; background: var(--bg-card);';

            const uniqueId = contactoCounter;
            div.innerHTML = `
              <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 10px;">
                <strong style="color: var(--primary-color);">Contacto #${uniqueId}</strong>
                <button type="button" class="btn btn-remove-contacto" style="background-color: var(--danger-bg); color: var(--danger-text); padding: 5px 10px; font-size: 12px; border: none; border-radius: 4px; cursor: pointer;"><i class="fa-solid fa-trash"></i> Eliminar</button>
              </div>
              <div class="names">
                <div class="form-group">
                  <input type="text" class="c-nombres" placeholder="Nombres (*)" required />
                </div>
                <div class="form-group">
                  <input type="text" class="c-apellidos" placeholder="Apellidos (*)" required />
                </div>
                <div class="form-group">
                  <input type="text" class="c-cargo" placeholder="Cargo (*)" required />
                </div>
              </div>

              <div class="document">
                <div class="form-group">
                  <select class="c-tipo-doc" required>
                    <option value="">Tipo Id (*)</option>
                  </select>
                </div>
                <div class="form-group">
                  <input type="text" class="c-doc-num" placeholder="Número documento (*)" required />
                </div>
              </div>

              <div class="dates3">
                <div class="form-group">
                  <select class="c-tipo-tel" required>
                    <option value="">Tipo de teléfono (*)</option>
                  </select>
                </div>
                <div class="form-group">
                  <input type="tel" class="c-telefono" placeholder="Número de teléfono (*)" required />
                </div>
                <div class="form-group">
                  <input type="email" class="c-correo" placeholder="Correo Electrónico (*)" required />
                </div>
              </div>
            `;

            contactosContainer.appendChild(div);

            // Cargar select opciones
            await Promise.all([
                cargarTiposIdentificacion(div.querySelector('.c-tipo-doc')),
                cargarTiposTelefono(div.querySelector('.c-tipo-tel'))
            ]);

            // Aplicar filtros
            aplicarFiltroTexto(div.querySelector('.c-nombres'));
            aplicarFiltroTexto(div.querySelector('.c-apellidos'));
            aplicarFiltroTexto(div.querySelector('.c-cargo'));
            aplicarFiltroNumero(div.querySelector('.c-doc-num'));
            aplicarFiltroNumero(div.querySelector('.c-telefono'));

            div.querySelector('.btn-remove-contacto').addEventListener('click', () => {
                div.remove();
            });
        });
    }

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
                contactos: [],
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
                validaciones: [],
                laft: {
                    p1: document.querySelector('input[name="laft_p1"]:checked')?.value === 'true',
                    p2: document.querySelector('input[name="laft_p2"]:checked')?.value === 'true',
                    p3: document.querySelector('input[name="laft_p3"]:checked')?.value === 'true',
                    p4: document.querySelector('input[name="laft_p4"]:checked')?.value === 'true',
                    p5: document.querySelector('input[name="laft_p5"]:checked')?.value === 'true'
                }
            };

            // Recoger todos los contactos
            const contactItems = document.querySelectorAll('.contacto-item');
            contactItems.forEach(item => {
                const nombres = item.querySelector('.c-nombres').value;
                const apellidos = item.querySelector('.c-apellidos').value;
                const cargo = item.querySelector('.c-cargo').value;
                const idTipoIdentificacion = parseInt(item.querySelector('.c-tipo-doc').value);
                const numeroIdentificacion = item.querySelector('.c-doc-num').value;
                const idTipoTelefono = parseInt(item.querySelector('.c-tipo-tel').value);
                const telefono = item.querySelector('.c-telefono').value;
                const correo = item.querySelector('.c-correo').value;

                if (nombres) {
                    formData.contactos.push({
                        nombres,
                        apellidos,
                        cargo,
                        idTipoIdentificacion,
                        numeroIdentificacion,
                        idTipoTelefono,
                        telefono,
                        correo
                    });
                }
            });

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

            // --- Agregar alerta financiera si los gastos superan los ingresos ---
            if (formData.financiera.totalGastos > formData.financiera.totalIngresos) {
                formData.validaciones.push({
                    idCampoValidacion: 1, // Usando ID 1 temporalmente, el backend lo procesará
                    valorWeb: formData.financiera.totalIngresos.toString(),
                    valorDocumento: formData.financiera.totalGastos.toString(),
                    resultadoValidacion: false, // Debe ser booleano
                    comentarios: "Alerta Financiera: Los gastos reportados superan a los ingresos reportados."
                });
            }

            fetch(`${CONFIG.API_BASE_URL}/proveedores/registro-completo`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ ...formData, idUsuarioAsignado: userId })
            })
                .then(response => {
                    if (response.ok) return response.json();
                    throw new Error("Error en la petición");
                })
                .then(async result => {
                    // Cargar documentos
                    try {
                        const provRes = await fetch(`${CONFIG.API_BASE_URL}/proveedores/by-userid?userId=${userId}`);
                        const provData = await provRes.json();
                        if (provData.data && provData.data.idProveedor) {
                            const idProveedor = provData.data.idProveedor;
                            const fileInputs = document.querySelectorAll('input[type="file"][id^="file-"]');
                            for (const input of fileInputs) {
                                if (input.files.length > 0) {
                                    const fd = new FormData();
                                    fd.append("file", input.files[0]);
                                    fd.append("idProveedor", idProveedor);
                                    fd.append("idTipoDocumento", input.getAttribute("data-doc-id"));
                                    fd.append("creadoPor", userId);
                                    await fetch(`${CONFIG.API_BASE_URL}/documentos/upload`, { method: 'POST', body: fd });
                                }
                            }
                        }
                    } catch (e) {
                        console.error("Error al cargar documentos:", e);
                    }

                    alert('¡Toda la información y documentos han sido guardados exitosamente!');
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

                const accountTypeSelect = document.getElementById('account type');
                if (accountTypeSelect && prov.tipoCuenta) {
                    accountTypeSelect.value = prov.tipoCuenta;
                }

                const accountNumInput = document.getElementById('account-number');
                if (accountNumInput && prov.numCuenta) {
                    accountNumInput.value = prov.numCuenta;
                }

                const bankNameInput = document.getElementById('bank_name');
                if (bankNameInput && prov.bancoReferencia) {
                    bankNameInput.value = prov.bancoReferencia;
                }

                // Actualizar etiquetas de la declaración de fondos
                actualizarDeclaracionOrigenFondos();

                // Renderizar los cargadores de documentos según tipo de persona
                renderizarCargadoresDocumentos(prov.idTipoPersona || 1);
            } else {
                renderizarCargadoresDocumentos(1);
            }
        } catch (e) {
            console.error("Error pre-completando datos:", e);
            renderizarCargadoresDocumentos(1);
        }
    }
    actualizarDeclaracionOrigenFondos();

    // --- 10. Lógica de Wizard / Pasos ---
    const step1El = document.getElementById('step-1');
    const step2El = document.getElementById('step-2');
    const step3El = document.getElementById('step-3');

    const indStep1 = document.getElementById('ind-step-1');
    const indStep2 = document.getElementById('ind-step-2');
    const indStep3 = document.getElementById('ind-step-3');

    function irAPaso(paso) {
        if (step1El) step1El.style.display = 'none';
        if (step2El) step2El.style.display = 'none';
        if (step3El) step3El.style.display = 'none';

        [indStep1, indStep2, indStep3].forEach(el => {
            if (el) {
                el.style.color = 'var(--text-muted)';
                el.style.borderBottom = 'none';
            }
        });

        if (paso === 1 && step1El) {
            step1El.style.display = 'block';
            if (indStep1) {
                indStep1.style.color = 'var(--accent-color)';
                indStep1.style.borderBottom = '2px solid var(--accent-color)';
            }
        } else if (paso === 2 && step2El) {
            step2El.style.display = 'block';
            if (indStep2) {
                indStep2.style.color = 'var(--accent-color)';
                indStep2.style.borderBottom = '2px solid var(--accent-color)';
            }
        } else if (paso === 3 && step3El) {
            step3El.style.display = 'block';
            if (indStep3) {
                indStep3.style.color = 'var(--accent-color)';
                indStep3.style.borderBottom = '2px solid var(--accent-color)';
            }
        }
        window.scrollTo({ top: 0, behavior: 'smooth' });
    }

    document.getElementById('btn-goto-step2')?.addEventListener('click', () => irAPaso(2));
    document.getElementById('btn-goto-step3')?.addEventListener('click', () => {
        // --- Validación Financiera Estricta ---
        const activos = parseFloat(document.getElementById('activos')?.value) || 0;
        const pasivos = parseFloat(document.getElementById('pasivos')?.value) || 0;
        const patrimonio = parseFloat(document.getElementById('patrimonio')?.value) || 0;
        const ingresos = parseFloat(document.getElementById('total_ingresos')?.value) || 0;
        const gastos = parseFloat(document.getElementById('total_gastos')?.value) || 0;

        // Ecuación Patrimonial: Activos - Pasivos = Patrimonio (con tolerancia por decimales)
        const diff = Math.abs((activos - pasivos) - patrimonio);
        if (diff > 100) {
            alert(`Error de Validación Financiera:\nLa ecuación contable no coincide.\n\nActivos (${activos}) - Pasivos (${pasivos}) = ${activos - pasivos}\nPero el Patrimonio ingresado es: ${patrimonio}`);
            return;
        }

        // (Nota: La validación de Ingresos vs Gastos no bloquea el paso, se envía como alerta al backend al finalizar)
        
        irAPaso(3);
    });
    document.getElementById('btn-back-to-step1')?.addEventListener('click', () => irAPaso(1));
    document.getElementById('btn-back-to-step2')?.addEventListener('click', () => irAPaso(2));

    // --- 11. Cargadores Dinámicos según tipo de Persona en BD ---
    let tipoPersonaProveedorGlobal = "natural";

    async function renderizarCargadoresDocumentos(idTipoPersona) {
        const tbody = document.getElementById('document-loaders-tbody');
        if (!tbody) return;

        tipoPersonaProveedorGlobal = (idTipoPersona === 2) ? "juridica" : "natural";

        try {
            const res = await fetch(`${CONFIG.API_BASE_URL}/tipo_documento?idTipoPersona=${idTipoPersona}`);
            const result = await res.json();

            if (result.data && Array.isArray(result.data)) {
                let html = '';
                result.data.forEach(doc => {
                    let fileKey = "documento_" + doc.idTipoDocumento;
                    const desc = doc.descripcion.toLowerCase();
                    if (desc.includes("rut") || desc.includes("tributario")) {
                        fileKey = "rut";
                    } else if (desc.includes("cámara") || desc.includes("camara") || desc.includes("existencia")) {
                        fileKey = "camara";
                    } else if (desc.includes("cédula") || desc.includes("cedula")) {
                        fileKey = "cedula";
                    } else if (desc.includes("bancaria") || desc.includes("banco")) {
                        fileKey = "banco";
                    } else if (desc.includes("referencia") || desc.includes("comercial")) {
                        fileKey = "refCom";
                    }

                    html += `
                        <tr>
                            <td style="font-weight: 500; font-size: 13px; color: var(--text-primary); text-align: left; vertical-align: middle;">${doc.descripcion}</td>
                            <td style="vertical-align: middle; text-align: center;">
                                <input type="file" id="file-${fileKey}" data-doc-id="${doc.idTipoDocumento}" accept=".pdf" style="font-size: 12px;" required />
                            </td>
                            <td style="vertical-align: middle; text-align: center;">
                                <span id="status-${fileKey}" style="font-size: 12px; color: var(--text-muted); font-weight: 600;">
                                    <i class="fa-solid fa-circle-minus"></i> Pendiente
                                </span>
                            </td>
                        </tr>
                    `;
                });
                tbody.innerHTML = html;
            } else {
                tbody.innerHTML = '<tr><td colspan="3" style="text-align:center;">No se encontraron documentos requeridos.</td></tr>';
            }
        } catch (e) {
            console.error("Error cargando cargadores dinámicos:", e);
            tbody.innerHTML = '<tr><td colspan="3" style="text-align:center; color: var(--danger-text);">Error al conectar con la base de datos de documentos.</td></tr>';
        }
    }

    // --- 12. Petición AJAX OCR y Validación Cruzada ---
    document.getElementById('btn-validar-expediente')?.addEventListener('click', async () => {
        const btn = document.getElementById('btn-validar-expediente');
        const summaryEl = document.getElementById('ocr-validation-summary');
        const continueBtn = document.getElementById('btn-goto-step2');

        if (!btn || !summaryEl || !continueBtn) return;

        // Validar que se seleccionaron los archivos requeridos
        const files = {};
        let missing = false;
        const keys = tipoPersonaProveedorGlobal === "juridica"
            ? ["camara", "rut", "cedula", "banco", "refCom"]
            : ["rut", "cedula", "banco", "refCom"];

        keys.forEach(k => {
            const el = document.getElementById(`file-${k}`);
            if (el && el.files.length > 0) {
                files[k] = el.files[0];
                document.getElementById(`status-${k}`).innerHTML = '<i class="fa-solid fa-circle-check" style="color: var(--success-text);"></i> Cargado';
            } else if (el) {
                missing = true;
                document.getElementById(`status-${k}`).innerHTML = '<i class="fa-solid fa-circle-xmark" style="color: var(--danger-text);"></i> Requerido';
            }
        });

        if (missing) {
            alert("Por favor, selecciona todos los archivos requeridos.");
            return;
        }

        // Mostrar loading spinner
        btn.disabled = true;
        btn.innerHTML = '<i class="fa-solid fa-spinner fa-spin"></i> Procesando y Validando...';
        summaryEl.style.display = 'none';

        const formData = new FormData();
        keys.forEach(k => {
            formData.append(k, files[k]);
        });
        formData.append("tipoPersona", tipoPersonaProveedorGlobal);

        try {
            const res = await fetch(`${CONFIG.API_BASE_URL}/proveedores/pre-procesar`, {
                method: 'POST',
                body: formData
            });
            const result = await res.json();

            btn.disabled = false;
            btn.innerHTML = '<i class="fa-solid fa-square-check"></i> Validar Documentos';

            if (res.ok && result.data) {
                const data = result.data;
                summaryEl.style.display = 'block';

                // Llenar campos del formulario con los datos que se hayan podido extraer
                prellenarFormularioOCR(data);

                if (data.extraccionExitosa) {
                    summaryEl.style.backgroundColor = 'var(--success-bg)';
                    summaryEl.style.color = 'var(--success-text)';
                    summaryEl.style.border = '1px solid var(--success-text)';
                    summaryEl.innerHTML = `<strong><i class="fa-solid fa-circle-check"></i> Éxito:</strong> ${data.mensaje}`;

                    // Habilitar botón continuar
                    continueBtn.style.display = 'inline-flex';
                } else {
                    summaryEl.style.backgroundColor = 'var(--warning-bg)';
                    summaryEl.style.color = 'var(--warning-text)';
                    summaryEl.style.border = '1px solid var(--warning-text)';
                    summaryEl.innerHTML = `<strong><i class="fa-solid fa-triangle-exclamation"></i> Advertencia:</strong> ${data.mensaje}. <br/>Puedes completar los campos restantes del formulario manualmente.`;

                    // Permitir continuar con advertencia
                    continueBtn.style.display = 'inline-flex';
                }
            } else {
                throw new Error("Error en el servidor");
            }
        } catch (e) {
            btn.disabled = false;
            btn.innerHTML = '<i class="fa-solid fa-square-check"></i> Validar Documentos';
            alert("Hubo un error al procesar el expediente. Asegúrate de que los archivos sean PDFs válidos.");
        }
    });

    function prellenarFormularioOCR(data) {
        const setVal = (id, val, readOnly = true) => {
            const el = document.getElementById(id);
            if (el && val) {
                el.value = val;
                if (readOnly) {
                    el.readOnly = true;
                    el.style.backgroundColor = '#e2e8f0';
                }
            }
        };

        setVal('company name', data.razonSocial);
        setVal('document-number company', data.nit);
        setVal('addrees', data.direccion);
        setVal('email company', data.correo);
        setVal('phone company', data.telefono);
        setVal('ciiu', data.ciiu);
        setVal('bank_name', data.banco);
        setVal('account-number', data.numeroCuenta);

        if (data.tipoCuenta) {
            const accountTypeSelect = document.getElementById('account type');
            if (accountTypeSelect) {
                const cleanType = data.tipoCuenta.toLowerCase();
                accountTypeSelect.value = cleanType.includes("ahorro") ? "ahorros" : "corriente";
                accountTypeSelect.style.pointerEvents = 'none';
                accountTypeSelect.style.backgroundColor = '#e2e8f0';
            }
        }

        // Llenar representante
        if (data.representantes && data.representantes.length > 0) {
            const rep = data.representantes[0];
            setVal('first-name-r1', rep.nombres);
            setVal('last-name-r1', rep.apellidos);
            setVal('position-r1', rep.cargo);
            setVal('document-number-r1', rep.numeroDocumento);

            // Suplentes dinámicos
            const repsSuplentesContainer = document.getElementById('reps-suplentes-container');
            if (repsSuplentesContainer) repsSuplentesContainer.innerHTML = '';
            for (let i = 1; i < data.representantes.length; i++) {
                const s = data.representantes[i];
                document.getElementById('btn-add-rep-suplente')?.click();
                setTimeout(() => {
                    const rows = repsSuplentesContainer.querySelectorAll('.rep-suplente-item');
                    if (rows.length >= i) {
                        const row = rows[i - 1];
                        row.querySelector('.rep-nombre').value = s.nombres || '';
                        row.querySelector('.rep-apellido').value = s.apellidos || '';
                        row.querySelector('.rep-doc-num').value = s.numeroDocumento || '';
                    }
                }, 100);
            }
        }

        // Llenar socios
        if (data.socios && data.socios.length > 0) {
            const tbodySocios = document.getElementById('tbody-socios');
            if (tbodySocios) tbodySocios.innerHTML = '';
            data.socios.forEach((socio, idx) => {
                document.getElementById('btn-add-socio')?.click();
                setTimeout(() => {
                    const rows = tbodySocios.querySelectorAll('.socio-row');
                    if (rows.length > idx) {
                        const row = rows[idx];
                        row.querySelector('.socio-nombre').value = socio.nombreCompleto || '';
                        row.querySelector('.socio-doc-num').value = socio.numeroDocumento || '';
                        row.querySelector('.socio-part').value = socio.participacion || '';
                        row.querySelector('.socio-nac').value = socio.nacionalidad || '';

                        // Seleccionar Tipo Persona
                        const selectTipoPers = row.querySelector('.socio-tipo-pers');
                        if (selectTipoPers && socio.tipoPersona) {
                            const isJuridica = socio.tipoPersona.toLowerCase() === 'jurídica' || socio.tipoPersona.toLowerCase() === 'juridica';
                            for (let option of selectTipoPers.options) {
                                if (isJuridica && (option.text.toLowerCase().includes('jurídica') || option.text.toLowerCase().includes('juridica') || option.value === '2')) {
                                    selectTipoPers.value = option.value;
                                    break;
                                } else if (!isJuridica && (option.text.toLowerCase().includes('natural') || option.value === '1')) {
                                    selectTipoPers.value = option.value;
                                    break;
                                }
                            }
                        }

                        // Seleccionar Tipo Documento
                        const selectTipoDoc = row.querySelector('.socio-tipo-doc');
                        if (selectTipoDoc && socio.tipoDocumento) {
                            const targetDoc = socio.tipoDocumento.toUpperCase().trim();
                            for (let option of selectTipoDoc.options) {
                                const optText = option.text.toUpperCase();
                                if (optText.includes(targetDoc) || (targetDoc === 'CC' && (optText.includes('CÉDULA') || optText.includes('CC')))) {
                                    selectTipoDoc.value = option.value;
                                    break;
                                }
                            }
                        }
                    }
                }, 200);
            });
        }

        actualizarDeclaracionOrigenFondos();
    }
});
