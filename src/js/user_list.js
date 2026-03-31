document.addEventListener('DOMContentLoaded', function() {
    const tableBody = document.querySelector('tbody');
    const userRole = localStorage.getItem('userRole');

    // Filtrar usuarios si el rol es comprador (solo ver proveedores)
    if (userRole === 'comprador' && tableBody) {
        const rows = tableBody.querySelectorAll('tr');
        rows.forEach(row => {
            const roleCell = row.querySelector('td:nth-child(3)');
            if (roleCell && roleCell.textContent.trim().toLowerCase() !== 'proveedor') {
                row.style.display = 'none';
                row.classList.add('hidden-by-role');
            }
        });
    }

    // 1. Funcionalidad de Búsqueda
    const searchInput = document.querySelector('.search-input');

    if (searchInput && tableBody) {
        searchInput.addEventListener('input', function(e) {
            const searchTerm = e.target.value.toLowerCase();
            const rows = tableBody.querySelectorAll('tr');

            rows.forEach(row => {
                const text = row.textContent.toLowerCase();
                if (text.includes(searchTerm) && !row.classList.contains('hidden-by-role')) {
                    row.style.display = '';
                } else {
                    row.style.display = 'none';
                }
            });
        });
    }

    // 2. Funciones para los botones de acción en cada fila
    if (tableBody) {
        tableBody.addEventListener('click', function(e) {
            // Encontrar el botón más cercano que se haya clickeado
            const btn = e.target.closest('button');
            if (!btn) return;

            const row = btn.closest('tr');
            if (!row) return;

            const userName = row.querySelector('td:nth-child(2)').textContent;

            // Funcionalidad: Eliminar
            if (btn.classList.contains('delete')) {
                if (confirm(`¿Estás seguro de que deseas eliminar al usuario ${userName}?`)) {
                    row.remove();
                    // Aquí podrías agregar una llamada a tu backend para confirmar el borrado
                }
            }

            // Funcionalidad: Activar / Desactivar (Toggle)
            else if (btn.classList.contains('toggle')) {
                const badge = row.querySelector('.badge');
                if (!badge) return;

                if (badge.classList.contains('success')) {
                    // Pasar a inactivo
                    if(confirm(`¿Deseas desactivar a ${userName}?`)) {
                        badge.classList.remove('success');
                        badge.classList.add('danger');
                        badge.textContent = 'Inactivo';
                        
                        btn.title = "Activar";
                        btn.innerHTML = '<i class="fas fa-toggle-off"></i>';
                    }
                } else {
                    // Pasar a activo
                    if(confirm(`¿Deseas activar a ${userName}?`)) {
                        badge.classList.remove('danger');
                        badge.classList.add('success');
                        badge.textContent = 'Activo';

                        btn.title = "Desactivar";
                        btn.innerHTML = '<i class="fas fa-toggle-on"></i>';
                    }
                }
            }

            // Funcionalidad: Editar
            else if (btn.classList.contains('edit')) {
                openEditModal(row);
            }
        });
    }

    // 3. Lógica del Modal de Edición
    const modal = document.getElementById("editUserModal");
    const closeBtn = document.querySelector(".close-modal");
    const cancelBtn = document.querySelector(".btn-cancel");
    const editForm = document.querySelector(".edit-user-form");
    
    // Elementos del formulario
    const roleSelect = document.getElementById('edit-role');
    const statusSelect = document.getElementById('edit-status');
    const passwordInput = document.getElementById('edit-password');

    let currentRowBeingEdited = null;

    function openEditModal(row) {
        if (!modal) return;
        
        currentRowBeingEdited = row;
        
        // Obtener datos actuales de la fila
        const currentRole = row.querySelector('td:nth-child(3)').textContent.toLowerCase().trim();
        const currentStatusText = row.querySelector('.badge').textContent.toLowerCase().trim();
        
        // Mapear el rol al valor exacto del select (ajustar según el HTML)
        let roleValue = "";
        if (currentRole.includes("admin")) roleValue = "admin";
        else if (currentRole.includes("comprador")) roleValue = "comprador";
        else if (currentRole.includes("analista")) roleValue = "analista";
        else if (currentRole.includes("proveedor")) roleValue = "proveedor";
        else roleValue = "";

        // Setear los valores en el formulario
        if (roleSelect) roleSelect.value = roleValue;
        if (statusSelect) statusSelect.value = currentStatusText;
        if (passwordInput) passwordInput.value = ""; // Limpiar contraseña por seguridad

        modal.style.display = "flex";
    }

    function closeEditModal() {
        if (modal) modal.style.display = "none";
        currentRowBeingEdited = null;
    }

    if (closeBtn) closeBtn.addEventListener("click", closeEditModal);
    if (cancelBtn) cancelBtn.addEventListener("click", closeEditModal);
    window.addEventListener("click", (e) => {
        if (e.target === modal) closeEditModal();
    });

    // Guardar los cambios del formulario (simulado frontend)
    if (editForm) {
        editForm.addEventListener('submit', function(e) {
            e.preventDefault(); // Evita recargar la página
            
            if (currentRowBeingEdited) {
                // Actualizar Rol
                const selectedRoleOption = roleSelect.options[roleSelect.selectedIndex].text;
                currentRowBeingEdited.querySelector('td:nth-child(3)').textContent = selectedRoleOption;

                // Actualizar Estado
                const badge = currentRowBeingEdited.querySelector('.badge');
                const toggleBtn = currentRowBeingEdited.querySelector('.icon-btn.toggle');
                const newStatus = statusSelect.value;
                
                if (newStatus === "activo") {
                    badge.classList.remove('danger');
                    badge.classList.add('success');
                    badge.textContent = 'Activo';
                    if (toggleBtn) {
                        toggleBtn.title = "Desactivar";
                        toggleBtn.innerHTML = '<i class="fas fa-toggle-on"></i>';
                    }
                } else {
                    badge.classList.remove('success');
                    badge.classList.add('danger');
                    badge.textContent = 'Inactivo';
                    if (toggleBtn) {
                        toggleBtn.title = "Activar";
                        toggleBtn.innerHTML = '<i class="fas fa-toggle-off"></i>';
                    }
                }

                alert("Cambios guardados con éxito.");
                closeEditModal();
            }
        });
    }
});
