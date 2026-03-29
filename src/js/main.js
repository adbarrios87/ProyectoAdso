// ==========================================
// INICIO DEL BLOQUE DE CONTROL DE ACCESOS POR ROL
// ==========================================
// Este código verifica qué usuario entró y si tiene permiso de ver el archivo actual.
const userRole = localStorage.getItem('userRole');
const currentFile = location.pathname.substring(location.pathname.lastIndexOf("/") + 1).toLowerCase();

// Si no estamos en el login y hay un archivo cargando
if (currentFile !== 'login.html' && currentFile !== '') {
    // Si no ha iniciado sesión (no hay rol guardado), lo enviamos al login
    if (!userRole) {
        window.location.href = '../../login.html';
    } else {
        // Verificar permisos basados en el rol y el nombre del archivo (prefijo)
        let hasAccess = false;

        if (currentFile === 'configuration.html') {
            hasAccess = true; // La configuración es global
        } else if (userRole === 'analista' && currentFile.startsWith('risk')) {
            hasAccess = true;
        } else if (userRole === 'oficial' && currentFile.startsWith('compliance_officer')) {
            hasAccess = true;
        } else if (userRole === 'admin' && (currentFile.startsWith('admin') || currentFile.startsWith('buyer') || currentFile.startsWith('user'))) {
            // Nota: Agregué 'user' para que el Administrador también pueda acceder a user_new.html y user_list.html
            hasAccess = true;
        } else if (userRole === 'proveedor' && currentFile.startsWith('supplier')) {
            hasAccess = true;
        }

        // Si el usuario intentó entrar a una página que no le corresponde
        if (!hasAccess) {
            alert("Acceso denegado: No tienes permiso para ver esta página con tu rol actual (" + userRole + ").");
            window.location.href = '../../login.html'; // Devolverlo al login
        }
    }
}
// ==========================================
// FIN DEL BLOQUE DE CONTROL DE ACCESOS
// ==========================================

document.addEventListener('DOMContentLoaded', () => {
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
