document.addEventListener('DOMContentLoaded', function () {
    const userTableBody = document.getElementById('userTableBody');
    const userRole = localStorage.getItem('userRole');

    // --- 1. CONFIGURACIÓN DE EVENTOS (Primero que nada) ---
    if (userTableBody) {
        userTableBody.addEventListener('click', function (e) {
            const btn = e.target.closest('button');
            if (!btn) return;

            const row = btn.closest('tr');
            if (!row) return;

            const userName = row.querySelector('td:nth-child(2)').textContent;
            const userId = row.getAttribute('data-id');

            if (btn.classList.contains('delete')) {
                if (confirm(`¿Estás seguro de que deseas eliminar permanentemente al usuario ${userName}?`)) {
                    eliminarUsuario(userId, row);
                }
            } else if (btn.classList.contains('toggle')) {
                const badge = row.querySelector('.badge');
                if (!badge || !userId) return;

                const esActivoActualmente = badge.classList.contains('success');
                const nuevoEstado = !esActivoActualmente;
                const mensaje = nuevoEstado ? `¿Deseas activar a ${userName}?` : `¿Deseas desactivar a ${userName}?`;

                if (confirm(mensaje)) {
                    cambiarEstadoUsuario(userId, nuevoEstado, badge, btn);
                }
            } else if (btn.classList.contains('req-update')) {
                if (btn.classList.contains('disabled-btn') || !userId) return;
                const esReqUpdateActualmente = btn.classList.contains('warning-text');
                const nuevoEstado = !esReqUpdateActualmente;
                const mensaje = nuevoEstado
                    ? `¿Deseas marcar al proveedor ${userName} como que NO requiere actualización de datos?`
                    : `¿Deseas marcar al proveedor ${userName} como que requiere actualización de datos?`;

                if (confirm(mensaje)) {
                    cambiarRequiereActualizacion(userId, nuevoEstado, btn);
                }
            } else if (btn.classList.contains('edit')) {
                openEditModal(row);
            }
        });
    }

    // --- 2. LÓGICA DE DATOS ---
    async function obtenerUsuarios() {
        try {
            const response = await fetch(`${CONFIG.API_BASE_URL}/usuarios`);
            const result = await response.json();
            if (result.data && Array.isArray(result.data)) {
                renderizarTabla(result.data);
            } else {
                userTableBody.innerHTML = '<tr><td colspan="7" style="text-align:center;">No se encontraron usuarios.</td></tr>';
            }
        } catch (error) {
            console.error("Error de conexión:", error);
            userTableBody.innerHTML = '<tr><td colspan="7" style="text-align:center;">Error al conectar con el servidor.</td></tr>';
        }
    }

    function renderizarTabla(usuarios) {
        let htmlAcumulado = '';
        usuarios.forEach(user => {
            if (userRole === 'comprador' && (user.nombreRol || '').toLowerCase() !== 'proveedor') return;

            const estadoClase = user.estadoUsuario ? 'success' : 'danger';
            const estadoTexto = user.estadoUsuario ? 'Activo' : 'Inactivo';
            const toggleIcon = user.estadoUsuario ? 'fa-toggle-on' : 'fa-toggle-off';
            const toggleTitle = user.estadoUsuario ? 'Desactivar' : 'Activar';

            let reqBtnHtml = '';
            if (user.idRol === 3) {
                const isReqUpdate = user.requiereActualizacion !== false; // por defecto true
                const reqIcon = isReqUpdate ? 'fa-triangle-exclamation' : 'fa-circle-check';
                const reqClass = isReqUpdate ? 'warning-text' : 'success-text';
                const reqTitle = isReqUpdate ? 'No requiere actualización (Click para solicitar actualización)' : 'Requiere actualización (Click para NO solicitar)';
                reqBtnHtml = `<button class="action-btn-icon req-update ${reqClass}" title="${reqTitle}"><i class="fa-solid ${reqIcon}"></i></button>`;
            } else {
                reqBtnHtml = `<button class="action-btn-icon req-update disabled-btn" title="No aplica a este rol" disabled><i class="fa-solid fa-minus" style="color: #94a3b8;"></i></button>`;
            }

            htmlAcumulado += `
                <tr data-id="${user.idUsuario}" data-rol-id="${user.idRol}">
                    <td>${user.idUsuario}</td>
                    <td>${user.nombreUsuario}</td>
                    <td>${user.nombreRol || 'Sin rol'}</td>
                    <td>${user.correoUsuario}</td>
                    <td><span class="badge ${estadoClase}">${estadoTexto}</span></td>
                    <td>${user.ultimoIngreso ? new Date(user.ultimoIngreso).toLocaleString() : 'Nunca'}</td>
                    <td class="actions">
                        <button class="action-btn-icon edit" title="Editar"><i class="fa-solid fa-pen-to-square"></i></button>
                        <button class="action-btn-icon toggle" title="${toggleTitle}"><i class="fa-solid ${toggleIcon}"></i></button>
                        ${reqBtnHtml}
                    </td>
                </tr>
            `;
        });
        userTableBody.innerHTML = htmlAcumulado;
    }

    // --- 3. FUNCIONES DE ACCIÓN ---
    async function cambiarEstadoUsuario(id, nuevoEstado, badge, btn) {
        try {
            const response = await fetch(`${CONFIG.API_BASE_URL}/usuarios/${id}/estado`, {
                method: 'PATCH',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ estadoUsuario: nuevoEstado })
            });
            const result = await response.json();
            if (response.ok && result.data) {
                if (nuevoEstado) {
                    badge.className = 'badge success';
                    badge.textContent = 'Activo';
                    btn.title = "Desactivar";
                    btn.innerHTML = '<i class="fa-solid fa-toggle-on"></i>';
                } else {
                    badge.className = 'badge danger';
                    badge.textContent = 'Inactivo';
                    btn.title = "Activar";
                    btn.innerHTML = '<i class="fa-solid fa-toggle-off"></i>';
                }
            }
        } catch (error) { console.error(error); }
    }

    async function cambiarRequiereActualizacion(id, nuevoEstado, btn) {
        try {
            const response = await fetch(`${CONFIG.API_BASE_URL}/usuarios/${id}/requiere-actualizacion`, {
                method: 'PATCH',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ requiereActualizacion: nuevoEstado })
            });
            const result = await response.json();
            if (response.ok && result.data) {
                if (nuevoEstado) {
                    btn.className = 'action-btn-icon req-update warning-text';
                    btn.title = "Requiere actualización (Click para marcar como Al Día)";
                    btn.innerHTML = '<i class="fa-solid fa-triangle-exclamation"></i>';
                } else {
                    btn.className = 'action-btn-icon req-update success-text';
                    btn.title = "Información Al Día (Click para solicitar actualización)";
                    btn.innerHTML = '<i class="fa-solid fa-circle-check"></i>';
                }
            }
        } catch (error) { console.error(error); }
    }

    // --- 4. MODAL DE EDICIÓN ---
    const modal = document.getElementById("editUserModal");
    const closeBtn = document.querySelector(".close-modal");
    const cancelBtn = document.querySelector(".btn-cancel");
    const editForm = document.querySelector(".edit-user-form");
    const roleSelect = document.getElementById('edit-role');
    const statusSelect = document.getElementById('edit-status');
    const passwordInput = document.getElementById('edit-password');
    const requirementsPanel = document.getElementById('password-requirements');

    let currentRowBeingEdited = null;
    let userDataFull = null;

    // Lógica de Validación de Contraseña en el Modal
    function updateReqUI(id, isValid) {
        const el = document.getElementById(id);
        if (!el) return;

        const textMap = {
            'req-length': 'Mínimo 8 caracteres',
            'req-upper': 'Al menos una mayúscula',
            'req-number': 'Al menos un número',
            'req-symbol': 'Al menos un símbolo (@$!%*?&._-)'
        };

        if (isValid) {
            el.classList.remove('invalid');
            el.classList.add('valid');
            el.innerHTML = `<i class="fa-solid fa-circle-check"></i> ${textMap[id]}`;
        } else {
            el.classList.remove('valid');
            el.classList.add('invalid');
            el.innerHTML = `<i class="fa-solid fa-circle-xmark"></i> ${textMap[id]}`;
        }
    }

    if (passwordInput && requirementsPanel) {
        passwordInput.addEventListener('focus', () => {
            if (passwordInput.value.length > 0) requirementsPanel.classList.remove('hidden');
        });

        passwordInput.addEventListener('input', function () {
            const val = this.value;
            if (val.length > 0) {
                requirementsPanel.classList.remove('hidden');
                updateReqUI('req-length', val.length >= 8);
                updateReqUI('req-upper', /[A-Z]/.test(val));
                updateReqUI('req-number', /\d/.test(val));
                updateReqUI('req-symbol', /[@$!%*?&._-]/.test(val));
            } else {
                requirementsPanel.classList.add('hidden');
            }
        });
    }

    async function openEditModal(row) {
        if (!modal) return;
        const userId = row.getAttribute('data-id');
        try {
            const response = await fetch(`${CONFIG.API_BASE_URL}/usuarios/${userId}`);
            const result = await response.json();
            if (result.data) {
                userDataFull = result.data;
                currentRowBeingEdited = row;
                if (roleSelect) roleSelect.value = userDataFull.idRol;
                if (statusSelect) statusSelect.value = userDataFull.estadoUsuario ? "activo" : "inactivo";
                if (passwordInput) passwordInput.value = "";
                modal.style.display = "flex";
            }
        } catch (e) { console.error(e); }
    }

    function closeEditModal() {
        if (modal) modal.style.display = "none";
        currentRowBeingEdited = null;
    }

    if (closeBtn) closeBtn.addEventListener("click", closeEditModal);
    if (cancelBtn) cancelBtn.addEventListener("click", closeEditModal);
    window.addEventListener("click", (e) => { if (e.target === modal) closeEditModal(); });

    if (editForm) {
        editForm.addEventListener('submit', async function (e) {
            e.preventDefault();
            if (userDataFull && currentRowBeingEdited) {
                const updatedUser = {
                    nombreUsuario: userDataFull.nombreUsuario,
                    cargoUsuario: userDataFull.cargoUsuario,
                    correoUsuario: userDataFull.correoUsuario,
                    contrasena: passwordInput.value || userDataFull.contrasena,
                    estadoUsuario: statusSelect.value === "activo",
                    idRol: parseInt(roleSelect.value),
                    idTipoIdentificacion: userDataFull.idTipoIdentificacion,
                    numeroIdentificacion: userDataFull.numeroIdentificacion,
                    creadoPor: userDataFull.creadoPor,
                    modificadoPor: parseInt(localStorage.getItem('userId')) || null
                };

                if (passwordInput.value) {
                    const regexPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&._-])[A-Za-z\d@$!%*?&._-]{8,}$/;
                    if (!regexPassword.test(passwordInput.value)) {
                        alert("La nueva contraseña no cumple con los requisitos de seguridad.");
                        return;
                    }
                }

                try {
                    const response = await fetch(`${CONFIG.API_BASE_URL}/usuarios/${userDataFull.idUsuario}`, {
                        method: 'PUT',
                        headers: { 'Content-Type': 'application/json' },
                        body: JSON.stringify(updatedUser)
                    });
                    if (response.ok) {
                        alert("Cambios guardados exitosamente.");
                        obtenerUsuarios(); // Recargamos para ver cambios frescos
                        closeEditModal();
                    }
                } catch (e) { console.error(e); }
            }
        });
    }

    // Inicialización
    obtenerUsuarios();
    cargarRoles("edit-role");
});

// Función para eliminar
async function eliminarUsuario(id, row) {
    try {
        const response = await fetch(`${CONFIG.API_BASE_URL}/usuarios/${id}`, { method: 'DELETE' });
        if (response.ok) row.remove();
    } catch (e) { console.error(e); }
}

window.filtrarUsuarios = function () {
    const input = document.getElementById('searchInput');
    const valor = (input.value || "").toLowerCase().trim();
    const rows = document.querySelectorAll('#userTableBody tr');
    rows.forEach(row => {
        const content = row.textContent.toLowerCase();
        row.classList.toggle('hidden', !content.includes(valor));
    });
};

async function cargarRoles(selectId) {
    const select = document.getElementById(selectId);
    if (!select) return;
    try {
        const res = await fetch(`${CONFIG.API_BASE_URL}/roles`);
        const result = await res.json();
        if (result.data) {
            result.data.forEach(rol => {
                const opt = document.createElement("option");
                opt.value = rol.idRol;
                opt.textContent = rol.rol;
                select.appendChild(opt);
            });
        }
    } catch (e) { console.error(e); }
}