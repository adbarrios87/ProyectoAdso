/**
 * configuration.js
 * Lógica para la vista de configuración general del usuario.
 */

document.addEventListener('DOMContentLoaded', () => {

    // --- 1. Cambio de Foto de Perfil ---
    const uploadInput = document.getElementById('upload-pic');
    const configUserImg = document.getElementById('config-user-img');
    const headerUserImg = document.getElementById('header-user-img'); // En el header
    const btnDeletePic = document.getElementById('btn-delete-pic');

    // Definir el prefijo basado en el usuario logueado para que cada rol tenga su propia foto y config local (opcional)
    const currentUserRole = localStorage.getItem('userRole') || 'guest';

    // Reconstruir el menú lateral dependiendo del rol
    const sidebarMenu = document.getElementById('sidebar-menu');
    if (sidebarMenu) {
        if (currentUserRole === 'proveedor') {
            sidebarMenu.innerHTML = `
              <li><i class="fa-solid fa-house"></i><a href="supplier_dashboard.html">Inicio</a></li>
              <li><i class="fa-solid fa-upload"></i><a href="supplier_upload_documents.html">Cargar documentos</a></li>
              <li><i class="fa-solid fa-spinner fa-spin"></i><a href="supplier_form.html">Actualizar información</a></li>
              <li><i class="fa-solid fa-certificate"></i><a href="supplier_certification.html">Generar certificación</a></li>
              <li><i class="fa-solid fa-history"></i><a href="supplier_qualification_history.html">Historial de calificaciones</a></li>
              <li><i class="fa-solid fa-bell"></i><a href="#">Notificaciones</a></li>
              <li class="active"><i class="fa-solid fa-gear"></i><a href="configuration.html">Configuración</a></li>
            `;
        } else if (currentUserRole === 'admin' || currentUserRole === 'administrador' || currentUserRole === 'analista' || currentUserRole === 'oficial' || currentUserRole === 'user') {
            let homeHref = 'admin_dashboard.html';
            if (currentUserRole === 'analista') homeHref = 'risk_dashboard.html';
            if (currentUserRole === 'oficial') homeHref = 'compliance_officer_dashboard.html';

            sidebarMenu.innerHTML = `
                <li><i class="fa-solid fa-house"></i><a href="${homeHref}">Inicio</a></li>
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
                <li><a class="fa-solid fa-star-half-stroke"></a>Re-evaluación</li>
                <li><a class="fa-solid fa-chart-column"></a>Reportes</li>
                <li><a class="fa-solid fa-bell"></a>Notificaciones</li>
                <li class="active"><i class="fa-solid fa-gear"></i><a href="configuration.html">Configuración</a></li>
            `;
            
            // Re-vincular eventos para los nuevos dropdowns
            const dropdowns = sidebarMenu.querySelectorAll('.menu-item.dropdown');
            dropdowns.forEach(dropdown => {
                dropdown.addEventListener('click', function (e) {
                    if (e.target.tagName !== 'A') {
                        this.classList.toggle('active');
                    }
                });
            });
        }
    }

    let defaultPicUrl = "https://randomuser.me/api/portraits/women/75.jpg"; // Foto por defecto para proveedor
    if (currentUserRole !== 'proveedor') {
        defaultPicUrl = "https://randomuser.me/api/portraits/men/75.jpg"; // Foto diferente genérica para admin y otros
    }

    // Cargar imagen de localStorage si existe
    const savedPicUrl = localStorage.getItem(`profilePic_${currentUserRole}`);
    if (savedPicUrl) {
        configUserImg.src = savedPicUrl;
        if(headerUserImg) headerUserImg.src = savedPicUrl;
    } else {
        configUserImg.src = defaultPicUrl;
        if(headerUserImg) headerUserImg.src = defaultPicUrl;
    }

    uploadInput.addEventListener('change', (event) => {
        const file = event.target.files[0];
        if (file) {
            // Validar si es imagen
            if (!file.type.match('image.*')) {
                alert("Por favor selecciona un archivo de imagen válido.");
                return;
            }

            const reader = new FileReader();
            reader.onload = (e) => {
                const newPicUrl = e.target.result;
                configUserImg.src = newPicUrl;
                if(headerUserImg) headerUserImg.src = newPicUrl;
                
                // Guardar en localStorage para mantenerla
                localStorage.setItem(`profilePic_${currentUserRole}`, newPicUrl);
                alert("Foto de perfil actualizada exitosamente.");
            };
            reader.readAsDataURL(file);
        }
    });

    btnDeletePic.addEventListener('click', () => {
        if(confirm("¿Estás seguro de que deseas eliminar tu foto de perfil?")) {
            configUserImg.src = defaultPicUrl;
            if(headerUserImg) headerUserImg.src = defaultPicUrl;
            localStorage.removeItem(`profilePic_${currentUserRole}`);
            uploadInput.value = ""; // Limpiar input
            alert("Foto de perfil eliminada.");
        }
    });

    // --- 2. Cambio de Contraseña ---
    const passwordForm = document.getElementById('password-form');
    passwordForm.addEventListener('submit', (e) => {
        e.preventDefault();
        
        const currentPassword = document.getElementById('current-password').value;
        const newPassword = document.getElementById('new-password').value;
        const confirmPassword = document.getElementById('confirm-password').value;

        if (newPassword !== confirmPassword) {
            alert("Las nuevas contraseñas no coinciden. Por favor verifica.");
            return;
        }

        if (newPassword.length < 6) {
            alert("La nueva contraseña debe tener al menos 6 caracteres.");
            return;
        }

        // Simular petición al servidor
        console.log("Actualizando contraseña...");
        alert("Contraseña actualizada exitosamente.");
        passwordForm.reset();
    });

    // --- 3. Guardar Notificaciones ---
    const btnSaveNotif = document.getElementById('save-notifications');
    btnSaveNotif.addEventListener('click', () => {
        const notifEval = document.getElementById('notif-eval').checked;
        const notifDocs = document.getElementById('notif-docs').checked;
        const notifCert = document.getElementById('notif-cert').checked;
        const notifNews = document.getElementById('notif-news').checked;

        const preferences = {
            evaluations: notifEval,
            documents: notifDocs,
            certifications: notifCert,
            newsletters: notifNews
        };

        localStorage.setItem(`notifPref_${currentUserRole}`, JSON.stringify(preferences));
        alert("Tus preferencias de notificaciones han sido guardadas.");
    });

    // Cargar preferencias
    const savedPreferences = localStorage.getItem(`notifPref_${currentUserRole}`);
    if (savedPreferences) {
        const prefs = JSON.parse(savedPreferences);
        document.getElementById('notif-eval').checked = prefs.evaluations;
        document.getElementById('notif-docs').checked = prefs.documents;
        document.getElementById('notif-cert').checked = prefs.certifications;
        document.getElementById('notif-news').checked = prefs.newsletters;
    }

    // --- 4. Información de Contacto ---
    const contactForm = document.getElementById('contact-form');
    contactForm.addEventListener('submit', (e) => {
        e.preventDefault();
        
        const email = document.getElementById('config-email').value;
        const phone = document.getElementById('config-phone').value;

        // Guardar/actualizar en DB simulado
        localStorage.setItem(`contactEmail_${currentUserRole}`, email);
        localStorage.setItem(`contactPhone_${currentUserRole}`, phone);
        
        alert("Información de contacto actualizada correctamente.");
    });

    // Cargar info contacto
    const savedEmail = localStorage.getItem(`contactEmail_${currentUserRole}`);
    const savedPhone = localStorage.getItem(`contactPhone_${currentUserRole}`);
    if (savedEmail) document.getElementById('config-email').value = savedEmail;
    if (savedPhone) document.getElementById('config-phone').value = savedPhone;

});
