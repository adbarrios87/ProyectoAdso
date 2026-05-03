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



    // Cargar imagen de localStorage si existe (la que guardó el login o main.js)
    const savedPicUrl = localStorage.getItem('userPhoto');
    const storedName = localStorage.getItem('userName');
    
    if (savedPicUrl && savedPicUrl.trim() !== '') {
        configUserImg.src = savedPicUrl;
        if(headerUserImg) headerUserImg.src = savedPicUrl;
    } else {
        const defaultPic = 'https://ui-avatars.com/api/?name=' + encodeURIComponent(storedName || 'U') + '&background=random&color=fff';
        configUserImg.src = defaultPic;
        if(headerUserImg) headerUserImg.src = defaultPic;
    }

    // Variables para la nueva lógica de foto
    const inputUrl = document.getElementById('upload-pic-url');
    const btnSavePic = document.getElementById('btn-save-pic');

    btnSavePic.addEventListener('click', async () => {
        const newUrl = inputUrl.value.trim();
        if (!newUrl) {
            alert("Por favor, ingresa el enlace de tu foto.");
            return;
        }

        const userId = localStorage.getItem('userId');
        if (!userId) {
            alert("No se pudo identificar tu sesión. Por favor, vuelve a iniciar sesión.");
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/usuarios/${userId}/foto`, {
                method: 'PATCH',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ fotoUrl: newUrl })
            });

            const result = await response.json();
            if (result.data) {
                configUserImg.src = newUrl;
                if (headerUserImg) headerUserImg.src = newUrl;
                localStorage.setItem('userPhoto', newUrl);
                inputUrl.value = "";
                alert("¡Foto de perfil guardada exitosamente en la base de datos!");
            } else {
                alert("Hubo un error al guardar la foto.");
            }
        } catch (error) {
            console.error(error);
            alert("Error al conectar con el servidor.");
        }
    });

    btnDeletePic.addEventListener('click', () => {
        if(confirm("¿Estás seguro de que deseas eliminar tu foto de perfil?")) {
            const defaultPic = 'https://ui-avatars.com/api/?name=' + encodeURIComponent(localStorage.getItem('userName') || 'U') + '&background=random&color=fff';
            
            const userId = localStorage.getItem('userId');
            if(userId) {
                fetch(`http://localhost:8080/usuarios/${userId}/foto`, {
                    method: 'PATCH',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ fotoUrl: "" })
                });
            }

            configUserImg.src = defaultPic;
            if(headerUserImg) headerUserImg.src = defaultPic;
            localStorage.setItem('userPhoto', ''); 
            if(inputUrl) inputUrl.value = "";
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
