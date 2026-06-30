const schemaMap = {
    calificacion: {
        primaryKey: 'idCalificacion',
        title: 'Calificaciones',
        endpoint: '/calificacion',
        fields: [
            { name: 'codigo', label: 'Código', type: 'text', required: true },
            { name: 'descripcion', label: 'Descripción', type: 'text', required: true },
            { name: 'activo', label: 'Activo', type: 'boolean', defaultValue: true }
        ],
        columns: [
            { key: 'idCalificacion', label: 'ID' },
            { key: 'codigo', label: 'Código' },
            { key: 'descripcion', label: 'Descripción' },
            { key: 'activo', label: 'Estado', formatter: (val) => val ? 'Activo' : 'Inactivo' }
        ]
    },
    estado_proveedor: {
        primaryKey: 'idEstadoProveedor',
        title: 'Estados de Proveedor',
        endpoint: '/estado_proveedor',
        fields: [
            { name: 'estado', label: 'Estado (Nombre)', type: 'text', required: true },
            { name: 'descripcion', label: 'Descripción', type: 'text', required: true },
            { name: 'activo', label: 'Activo', type: 'boolean', defaultValue: true }
        ],
        columns: [
            { key: 'idEstadoProveedor', label: 'ID' },
            { key: 'estado', label: 'Estado' },
            { key: 'descripcion', label: 'Descripción' },
            { key: 'activo', label: 'Estado', formatter: (val) => val ? 'Activo' : 'Inactivo' }
        ]
    },
    roles: {
        primaryKey: 'idRol',
        title: 'Roles de Usuario',
        endpoint: '/roles',
        fields: [
            { name: 'rol', label: 'Nombre Rol', type: 'text', required: true },
            { name: 'descripcion', label: 'Descripción', type: 'text', required: true },
            { name: 'estadoRol', label: 'Estado Activo', type: 'boolean', defaultValue: true }
        ],
        columns: [
            { key: 'idRol', label: 'ID' },
            { key: 'rol', label: 'Rol' },
            { key: 'descripcion', label: 'Descripción' },
            { key: 'estadoRol', label: 'Estado', formatter: (val) => val ? 'Activo' : 'Inactivo' }
        ]
    },
    tipo_documento: {
        primaryKey: 'idTipoDocumento',
        title: 'Tipos de Documento',
        endpoint: '/tipo_documento',
        fields: [
            { name: 'codigo', label: 'Código', type: 'text', required: true },
            { name: 'descripcion', label: 'Descripción', type: 'text', required: true },
            { name: 'activo', label: 'Activo', type: 'boolean', defaultValue: true }
        ],
        columns: [
            { key: 'idTipoDocumento', label: 'ID' },
            { key: 'codigo', label: 'Código' },
            { key: 'descripcion', label: 'Descripción' },
            { key: 'activo', label: 'Estado', formatter: (val) => val ? 'Activo' : 'Inactivo' }
        ]
    },
    tipo_identificacion: {
        primaryKey: 'idTipoIdentificacion',
        title: 'Tipos de Identificación',
        endpoint: '/tipo_identificacion',
        fields: [
            { name: 'codigo', label: 'Código', type: 'text', required: true },
            { name: 'descripcion', label: 'Descripción', type: 'text', required: true },
            { name: 'activo', label: 'Activo', type: 'boolean', defaultValue: true }
        ],
        columns: [
            { key: 'idTipoIdentificacion', label: 'ID' },
            { key: 'codigo', label: 'Código' },
            { key: 'descripcion', label: 'Descripción' },
            { key: 'activo', label: 'Estado', formatter: (val) => val ? 'Activo' : 'Inactivo' }
        ]
    },
    tipo_notificacion: {
        primaryKey: 'idTipoNotificacion',
        title: 'Tipos de Notificación',
        endpoint: '/tipo_notificacion',
        fields: [
            { name: 'codigo', label: 'Código', type: 'text', required: true },
            { name: 'descripcion', label: 'Descripción', type: 'text', required: true },
            { name: 'activo', label: 'Activo', type: 'boolean', defaultValue: true }
        ],
        columns: [
            { key: 'idTipoNotificacion', label: 'ID' },
            { key: 'codigo', label: 'Código' },
            { key: 'descripcion', label: 'Descripción' },
            { key: 'activo', label: 'Estado', formatter: (val) => val ? 'Activo' : 'Inactivo' }
        ]
    },
    tipo_pago: {
        primaryKey: 'idTipoPago',
        title: 'Tipos de Pago',
        endpoint: '/tipo_pago',
        fields: [
            { name: 'codigo', label: 'Código', type: 'text', required: true },
            { name: 'descripcion', label: 'Descripción', type: 'text', required: true },
            { name: 'activo', label: 'Activo', type: 'boolean', defaultValue: true }
        ],
        columns: [
            { key: 'idTipoPago', label: 'ID' },
            { key: 'codigo', label: 'Código' },
            { key: 'descripcion', label: 'Descripción' },
            { key: 'activo', label: 'Estado', formatter: (val) => val ? 'Activo' : 'Inactivo' }
        ]
    },
    tipo_persona: {
        primaryKey: 'idTipoPersona',
        title: 'Tipos de Persona',
        endpoint: '/tipo_persona',
        fields: [
            { name: 'codigo', label: 'Código', type: 'text', required: true },
            { name: 'descripcion', label: 'Descripción', type: 'text', required: true },
            { name: 'activo', label: 'Activo', type: 'boolean', defaultValue: true }
        ],
        columns: [
            { key: 'idTipoPersona', label: 'ID' },
            { key: 'codigo', label: 'Código' },
            { key: 'descripcion', label: 'Descripción' },
            { key: 'activo', label: 'Estado', formatter: (val) => val ? 'Activo' : 'Inactivo' }
        ]
    },
    tipo_telefono: {
        primaryKey: 'idTipoTelefono',
        title: 'Tipos de Teléfono',
        endpoint: '/tipo_telefono',
        fields: [
            { name: 'codigo', label: 'Código', type: 'text', required: true },
            { name: 'descripcion', label: 'Descripción', type: 'text', required: true },
            { name: 'activo', label: 'Activo', type: 'boolean', defaultValue: true }
        ],
        columns: [
            { key: 'idTipoTelefono', label: 'ID' },
            { key: 'codigo', label: 'Código' },
            { key: 'descripcion', label: 'Descripción' },
            { key: 'activo', label: 'Estado', formatter: (val) => val ? 'Activo' : 'Inactivo' }
        ]
    }
};

let currentTableKey = 'calificacion';
let allRecords = [];

document.addEventListener('DOMContentLoaded', () => {
    initTabs();
    initSearch();
    initModal();
    cargarDatos();
});

// Inicializar pestañas laterales
function initTabs() {
    const tabs = document.querySelectorAll('.tab-item');
    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            tabs.forEach(t => t.classList.remove('active'));
            tab.classList.add('active');

            currentTableKey = tab.getAttribute('data-table');
            document.getElementById('current-table-title').textContent = tab.getAttribute('data-title');

            document.getElementById('config-search').value = '';
            cargarDatos();
        });
    });
}

// Cargar registros desde Backend
async function cargarDatos() {
    const schema = schemaMap[currentTableKey];
    const tbody = document.getElementById('config-table-body');
    const thead = document.getElementById('config-table-head');

    tbody.innerHTML = '<tr><td colspan="6">Cargando registros...</td></tr>';

    // Render headers
    thead.innerHTML = '';
    const trHead = document.createElement('tr');
    schema.columns.forEach(col => {
        const th = document.createElement('th');
        th.textContent = col.label;
        trHead.appendChild(th);
    });
    const thActions = document.createElement('th');
    thActions.textContent = 'Acciones';
    thActions.style.width = '120px';
    trHead.appendChild(thActions);
    thead.appendChild(trHead);

    try {
        const res = await fetch(`${CONFIG.API_BASE_URL}${schema.endpoint}`);
        const result = await res.json();

        allRecords = result.data || [];
        renderTableRows(allRecords);
    } catch (e) {
        console.error("Error al cargar configuración:", e);
        tbody.innerHTML = '<tr><td colspan="6" style="color: var(--danger-text);">Error al conectar con la base de datos.</td></tr>';
    }
}

// Renderizar filas de la tabla
function renderTableRows(records) {
    const schema = schemaMap[currentTableKey];
    const tbody = document.getElementById('config-table-body');
    tbody.innerHTML = '';

    if (records.length === 0) {
        tbody.innerHTML = `<tr><td colspan="${schema.columns.length + 1}">No se encontraron registros.</td></tr>`;
        return;
    }

    records.forEach(row => {
        const tr = document.createElement('tr');

        schema.columns.forEach(col => {
            const td = document.createElement('td');
            const rawVal = row[col.key];
            td.textContent = col.formatter ? col.formatter(rawVal) : (rawVal !== null ? rawVal : '-');
            tr.appendChild(td);
        });

        // Columna de acciones
        const tdActions = document.createElement('td');

        const btnEdit = document.createElement('button');
        btnEdit.className = 'action-btn-icon edit';
        btnEdit.title = 'Editar';
        btnEdit.innerHTML = '<i class="fa-solid fa-pen-to-square"></i>';
        btnEdit.addEventListener('click', () => openFormModal(row));

        const btnDelete = document.createElement('button');
        btnDelete.className = 'action-btn-icon delete';
        btnDelete.title = 'Eliminar';
        btnDelete.innerHTML = '<i class="fa-solid fa-trash"></i>';
        btnDelete.addEventListener('click', () => deleteRecord(row[schema.primaryKey]));

        tdActions.appendChild(btnEdit);
        tdActions.appendChild(btnDelete);
        tr.appendChild(tdActions);

        tbody.appendChild(tr);
    });
}

// Búsqueda en frontend
function initSearch() {
    const searchInput = document.getElementById('config-search');
    searchInput.addEventListener('input', (e) => {
        const query = e.target.value.toLowerCase().trim();
        if (!query) {
            renderTableRows(allRecords);
            return;
        }

        const schema = schemaMap[currentTableKey];
        const filtered = allRecords.filter(row => {
            return schema.columns.some(col => {
                const val = row[col.key];
                if (val === null || val === undefined) return false;
                return String(val).toLowerCase().includes(query);
            });
        });
        renderTableRows(filtered);
    });
}

// Lógica de Modales y Formulario CRUD
function initModal() {
    const modal = document.getElementById('recordModal');
    const form = document.getElementById('recordForm');
    const btnAdd = document.getElementById('btn-add-record');

    btnAdd.addEventListener('click', () => openFormModal(null));

    document.querySelectorAll('.close-record-modal').forEach(btn => {
        btn.addEventListener('click', () => modal.style.display = 'none');
    });

    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        await saveRecord();
    });
}

// Abrir modal y construir campos dinámicos
function openFormModal(record = null) {
    const schema = schemaMap[currentTableKey];
    const modal = document.getElementById('recordModal');
    const modalTitle = document.getElementById('modal-title');
    const recordIdInput = document.getElementById('record-id');
    const fieldsContainer = document.getElementById('modal-dynamic-fields');

    fieldsContainer.innerHTML = '';
    recordIdInput.value = record ? record[schema.primaryKey] : '';
    modalTitle.textContent = record ? `Editar ${schema.title}` : `Agregar ${schema.title}`;

    // Construir campos
    schema.fields.forEach(field => {
        const group = document.createElement('div');
        group.className = 'form-group';

        const label = document.createElement('label');
        label.textContent = field.label;
        group.appendChild(label);

        const currentValue = record ? record[field.name] : (field.defaultValue !== undefined ? field.defaultValue : '');

        if (field.type === 'boolean') {
            const select = document.createElement('select');
            select.name = field.name;
            select.id = `field-${field.name}`;

            const optTrue = document.createElement('option');
            optTrue.value = 'true';
            optTrue.textContent = 'Sí / Activo';
            if (currentValue === true) optTrue.selected = true;

            const optFalse = document.createElement('option');
            optFalse.value = 'false';
            optFalse.textContent = 'No / Inactivo';
            if (currentValue === false) optFalse.selected = true;

            select.appendChild(optTrue);
            select.appendChild(optFalse);
            group.appendChild(select);
        } else {
            const input = document.createElement('input');
            input.type = 'text';
            input.name = field.name;
            input.id = `field-${field.name}`;
            input.value = currentValue;
            input.required = field.required || false;
            group.appendChild(input);
        }

        fieldsContainer.appendChild(group);
    });

    modal.style.display = 'flex';
}

// Crear o Actualizar Registro (POST/PUT)
async function saveRecord() {
    const schema = schemaMap[currentTableKey];
    const recordId = document.getElementById('record-id').value;
    const isEdit = !!recordId;

    const payload = {};
    if (isEdit) {
        payload[schema.primaryKey] = parseInt(recordId);
    }

    schema.fields.forEach(field => {
        const input = document.getElementById(`field-${field.name}`);
        if (field.type === 'boolean') {
            payload[field.name] = input.value === 'true';
        } else {
            payload[field.name] = input.value;
        }
    });

    // Auditoría si aplica (por ejemplo para roles)
    if (currentTableKey === 'roles') {
        const userId = parseInt(localStorage.getItem('userId') || '2');
        if (isEdit) {
            payload.modificadoPor = userId;
        } else {
            payload.creadoPor = userId;
        }
    }

    const url = isEdit ? `${CONFIG.API_BASE_URL}${schema.endpoint}/${recordId}` : `${CONFIG.API_BASE_URL}${schema.endpoint}`;
    const method = isEdit ? 'PUT' : 'POST';

    try {
        const res = await fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        if (res.ok) {
            document.getElementById('recordModal').style.display = 'none';
            alert('Registro guardado correctamente.');
            cargarDatos();
        } else {
            const err = await res.json();
            alert(`Error al guardar: ${err.message || 'Error del servidor'}`);
        }
    } catch (e) {
        console.error("Error al guardar registro:", e);
        alert('Error de red al intentar conectar con el servidor.');
    }
}

// Eliminar Registro (DELETE)
async function deleteRecord(id) {
    if (!confirm('¿Estás seguro de que deseas eliminar este registro?')) return;

    const schema = schemaMap[currentTableKey];
    const url = `${CONFIG.API_BASE_URL}${schema.endpoint}/${id}`;

    try {
        const res = await fetch(url, { method: 'DELETE' });
        if (res.ok) {
            alert('Registro eliminado correctamente.');
            cargarDatos();
        } else {
            alert('No se pudo eliminar el registro. Puede estar en uso por otra entidad.');
        }
    } catch (e) {
        console.error("Error al eliminar:", e);
        alert('Error al intentar conectar con el servidor.');
    }
}
