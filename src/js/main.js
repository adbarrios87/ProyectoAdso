// ==========================================
// INICIO DEL BLOQUE DE CONTROL DE ACCESOS
// ==========================================
// Este código verifica qué usuario entró y si tiene una sesión válida.
const userRole = localStorage.getItem('userRole');
const currentFile = location.pathname.substring(location.pathname.lastIndexOf("/") + 1).toLowerCase();

// Si no estamos en el login y hay un archivo cargando
if (currentFile !== 'login.html' && currentFile !== '') {
    // Si no ha iniciado sesión (no hay rol guardado), lo enviamos al login
    if (!userRole) {
        window.location.href = '../../login.html';
    }
    // Nota: Las restricciones específicas por rol (Ej. acceso denegado a ciertas páginas dependiente del rol)
    // han sido removidas temporalmente para facilitar pruebas.
}
// ==========================================
// FIN DEL BLOQUE DE CONTROL DE ACCESOS
// ==========================================

document.addEventListener('DOMContentLoaded', () => {
    // 0. Generar menú lateral dinámico según el rol
    const storedRoleMenu = localStorage.getItem('userRole');
    const sidebarMenu = document.querySelector('.sidebar .menu');

    if (sidebarMenu) {
        if (storedRoleMenu === 'proveedor') {
            sidebarMenu.innerHTML = `
                <li><i class="fa-solid fa-house"></i><a href="supplier_dashboard.html">Inicio</a></li>
                <li><i class="fa-solid fa-upload"></i><a href="supplier_upload_documents.html">Cargar documentos</a></li>
                <li><i class="fa-solid fa-spinner fa-spin"></i><a href="supplier_form.html">Actualizar información</a></li>
                <li><i class="fa-solid fa-certificate"></i><a href="supplier_certification.html">Generar certificación</a></li>
                <li><i class="fa-solid fa-history"></i><a href="supplier_qualification_history.html">Historial de calificaciones</a></li>
                <li><i class="fa-solid fa-bell"></i><a href="expiration_alerts.html">Notificaciones</a></li>
                <li><i class="fa-solid fa-gear"></i><a href="configuration.html">Configuración</a></li>
            `;
        } else if (storedRoleMenu === 'admin' || storedRoleMenu === 'administrador') {
            sidebarMenu.innerHTML = `
                <li><i class="fa-solid fa-house"></i><a href="admin_dashboard.html">Inicio</a></li>
                <li class="menu-item dropdown"><i class="fa-solid fa-users"></i>Usuarios
                    <ul class="dropdown-menu">
                        <li><a href="user_new.html">Nuevo usuario</a></li>
                        <li><a href="user_list.html">Lista de usuarios</a></li>
                    </ul>
                </li>
                <li class="menu-item dropdown"><i class="fa-solid fa-building-user"></i>Proveedores
                    <ul class="dropdown-menu">
                        <li><a href="buyer_supplier_list.html">Comprador</a></li>
                        <li><a href="supplier_dashboard.html">Proveedor</a></li>
                    </ul>
                </li>
                <li class="menu-item dropdown"><i class="fa-solid fa-triangle-exclamation"></i>Riesgos
                    <ul class="dropdown-menu">
                        <li><a href="risk_dashboard.html">Analista de riesgos</a></li>
                        <li><a href="compliance_officer_dashboard.html">Oficial de cumplimiento</a></li>
                    </ul>
                </li>
                <li><i class="fa-solid fa-star-half-stroke"></i><a href="buyer_second_evaluation.html">Re-evaluación</a></li>
                <li><a class="fa-solid fa-chart-column"></a>Reportes</li>
                <li><i class="fa-solid fa-bell"></i><a href="expiration_alerts.html">Notificaciones</a></li>
                <li><i class="fa-solid fa-gear"></i><a href="configuration.html">Configuración</a></li>
            `;
        } else if (storedRoleMenu === 'comprador') {
            sidebarMenu.innerHTML = `
                <li><i class="fa-solid fa-house"></i><a href="#">Inicio</a></li>
                <li class="menu-item dropdown"><i class="fa-solid fa-users"></i>Usuarios
                    <ul class="dropdown-menu">
                        <li><a href="user_new.html">Nuevo usuario</a></li>
                        <li><a href="user_list.html">Lista de usuarios</a></li>
                    </ul>
                </li>
                <li><i class="fa-solid fa-building-user"></i><a href="buyer_supplier_list.html">Compradores</a></li>
                <li><i class="fa-solid fa-star-half-stroke"></i><a href="buyer_second_evaluation.html">Re-evaluación</a></li>
                <li><i class="fa-solid fa-chart-column"></i><a href="#">Reportes</a></li>
                <li><i class="fa-solid fa-bell"></i><a href="expiration_alerts.html">Notificaciones</a></li>
                <li><i class="fa-solid fa-gear"></i><a href="configuration.html">Configuración</a></li>
            `;
        }
    }

    // 1. Lógica para los menús desplegables del sidebar (Dropdowns)
    const dropdowns = document.querySelectorAll('.menu-item.dropdown');

    dropdowns.forEach(dropdown => {
        dropdown.addEventListener('click', function (e) {
            // Evitamos cerrar/abrir si se hizo clic en un enlace interno
            if (e.target.tagName !== 'A') {
                this.classList.toggle('active');
            }
        });
    });

    // 2. Resaltar el enlace activo en el menú lateral de acuerdo a la URL actual
    const currentLocation = location.pathname.substring(location.pathname.lastIndexOf("/") + 1);
    if (currentLocation) {
        const menuLinks = document.querySelectorAll('.sidebar a');
        menuLinks.forEach(link => {
            if (link.getAttribute('href') === currentLocation) {
                link.parentElement.classList.add('active');
                // Si el enlace está dentro de un dropdown, mantenemos abierto el dropdown
                const parentDropdown = link.closest('.menu-item.dropdown');
                if (parentDropdown) {
                    parentDropdown.classList.add('active');
                }
            }
        });
    }

    // 3. (Opcional) Funcionalidad básica para el perfil de usuario o barra de búsqueda global
    const userProfile = document.querySelector('.user-profile');
    if (userProfile) {
        userProfile.addEventListener('click', () => {
            // Aquí puedes agregar lógica para mostrar un menú de opciones del usuario
            console.log("Perfil de usuario clickeado");
        });
    }

    // 4. Actualizar dinámicamente el nombre y el rol en el header de todas las páginas
    const storedRole = localStorage.getItem('userRole');
    const storedName = localStorage.getItem('userName');

    if (storedRole) {
        const roleSpan = document.querySelector('.user-role');
        const nameSpan = document.querySelector('.user-name');

        if (roleSpan) {
            let roleIcon = '<i class="fa-solid fa-user"></i>';
            let formattedRole = storedRole.charAt(0).toUpperCase() + storedRole.slice(1);
            if (storedRole === 'proveedor') {
                roleIcon = '<i class="fa-solid fa-user-shield"></i>';
            } else if (storedRole === 'admin' || storedRole === 'administrador') {
                roleIcon = '<i class="fa-solid fa-user-gear"></i>';
                formattedRole = 'Administrador';
            } else if (storedRole === 'comprador') {
                roleIcon = '<i class="fa-solid fa-user-tie"></i>';
                formattedRole = 'Comprador';
            } else if (storedRole === 'analista') {
                roleIcon = '<i class="fa-solid fa-magnifying-glass-chart"></i>';
            } else if (storedRole === 'oficial') {
                roleIcon = '<i class="fa-solid fa-user-check"></i>';
            }

            roleSpan.innerHTML = `${roleIcon} ${formattedRole}`;
        }

        if (nameSpan && storedName) {
            nameSpan.textContent = storedName.toUpperCase();
        }
    }
});
