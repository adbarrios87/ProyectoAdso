document.addEventListener('DOMContentLoaded', () => {
    // 1. Lógica para los menús desplegables del sidebar (Dropdowns)
    const dropdowns = document.querySelectorAll('.menu-item.dropdown');
    
    dropdowns.forEach(dropdown => {
        dropdown.addEventListener('click', function(e) {
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
});
