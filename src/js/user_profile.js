document.addEventListener('DOMContentLoaded', function () {
    const userId = localStorage.getItem('userId');
    const headerUserName = document.querySelector('.user-name');
    const headerUserRole = document.querySelector('.user-role');
    const headerUserImg = document.getElementById('header-user-img');

    const configUserImg = document.getElementById('config-user-img');
    const uploadPicInput = document.getElementById('upload-pic');
    const btnDeletePic = document.getElementById('btn-delete-pic');

    const emailInput = document.getElementById('config-email');
    const cargoInput = document.getElementById('user-cargo');

    const passwordForm = document.getElementById('password-form');
    const contactForm = document.getElementById('contact-form');

    // --- Lógica de Iniciales y Avatares (Específica para perfil grande) ---
    function actualizarAvatarPerfil(fotoUrl, nombre) {
        const img = document.getElementById('config-user-img');
        const initials = document.getElementById('config-initials');

        if (!img || !initials) return;

        if (fotoUrl && fotoUrl.length > 50) {
            img.src = fotoUrl;
            img.classList.remove('hidden');
            initials.classList.add('hidden');
        } else {
            const nombreUser = nombre || "Usuario";
            const iniciales = nombreUser.split(" ").filter(n => n).map(n => n[0]).join("").toUpperCase().substring(0, 2);
            initials.textContent = iniciales || "?";
            initials.classList.remove('hidden');
            img.classList.add('hidden');
        }
    }

    // 1. Cargar Datos del Usuario (Solo para los campos de la página)
    async function cargarDatosPagina() {
        if (!userId) return;

        try {
            const response = await fetch(`${CONFIG.API_BASE_URL}/usuarios/${userId}`);
            const result = await response.json();

            if (result.data) {
                const user = result.data;
                // Actualizar Avatar Central (Grande)
                actualizarAvatarPerfil(user.fotoUrl, user.nombreUsuario);

                // Llenar Formulario de Contacto
                emailInput.value = user.correoUsuario || '';
                cargoInput.value = user.cargoUsuario || '';
            }
        } catch (error) { console.error("Error cargando página perfil:", error); }
    }

    // 2. Manejo de Cambio de Foto (Base64)
    uploadPicInput.addEventListener('change', function (e) {
        const file = e.target.files[0];
        if (!file) return;

        // Escudo de seguridad: Límite de 1MB
        const maxSize = 1 * 1024 * 1024; 
        if (file.size > maxSize) {
            alert("La foto es demasiado pesada. El límite es de 1MB para asegurar el rendimiento del sistema.");
            e.target.value = ""; // Limpiamos el input
            return;
        }

        const reader = new FileReader();
        reader.onload = async function (event) {
            const base64String = event.target.result;

            // Actualizar vista previa (perfil grande)
            actualizarAvatarPerfil(base64String, "");

            // Forzamos actualización del Header (que está en el main.js)
            const headerImg = document.getElementById('header-user-img');
            const headerInitials = document.getElementById('header-initials');
            if (headerImg) {
                headerImg.src = base64String;
                headerImg.classList.remove('hidden');
            }
            if (headerInitials) headerInitials.classList.add('hidden');

            try {
                const response = await fetch(`${CONFIG.API_BASE_URL}/usuarios/${userId}/foto`, {
                    method: 'PATCH',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ fotoUrl: base64String })
                });

                if (response.ok) {
                    alert("¡Foto actualizada correctamente!");
                } else {
                    const errorData = await response.json();
                    alert("Error al actualizar la foto: " + (errorData.message || "Error en el servidor"));
                }
            } catch (error) { 
                console.error("Error de conexión:", error);
                alert("No se pudo conectar con el servidor para actualizar la foto.");
            }
        };
        reader.readAsDataURL(file);
    });

    // 3. Eliminar Foto
    btnDeletePic.addEventListener('click', async function () {
        try {
            const response = await fetch(`${CONFIG.API_BASE_URL}/usuarios/${userId}/foto`, {
                method: 'PATCH',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ fotoUrl: null })
            });

            if (response.ok) {
                // Al borrar, volvemos a cargar los datos de la página
                cargarDatosPagina();
                alert("Foto eliminada correctamente.");
            } else {
                alert("Error al eliminar la foto del servidor.");
            }
        } catch (error) { 
            console.error("Error al eliminar foto:", error);
            alert("No se pudo conectar con el servidor para eliminar la foto.");
        }
    });

    // --- Lógica de Validación en Tiempo Real (Requisitos de Contraseña) ---
    const newPasswordInput = document.getElementById('new-password');
    const requirementsPanel = document.getElementById('password-requirements');
    
    function updateReqUI(id, isValid) {
        const el = document.getElementById(id);
        if (!el) return;
        
        const text = id === 'req-length' ? 'Mínimo 8 caracteres' :
                     id === 'req-upper' ? 'Al menos una mayúscula' :
                     id === 'req-number' ? 'Al menos un número' :
                     'Al menos un símbolo (@$!%*?&._-)';
        
        if (isValid) {
            el.classList.remove('invalid');
            el.classList.add('valid');
            el.innerHTML = `<i class="fas fa-check-circle"></i> ${text}`;
        } else {
            el.classList.remove('valid');
            el.classList.add('invalid');
            el.innerHTML = `<i class="fas fa-times-circle"></i> ${text}`;
        }
    }

    if (newPasswordInput && requirementsPanel) {
        newPasswordInput.addEventListener('focus', () => requirementsPanel.classList.remove('hidden'));
        
        newPasswordInput.addEventListener('input', function() {
            const val = this.value;
            
            // Requisitos
            const rules = {
                length: val.length >= 8,
                upper: /[A-Z]/.test(val),
                number: /\d/.test(val),
                symbol: /[@$!%*?&._-]/.test(val)
            };

            // Actualizar UI
            updateReqUI('req-length', rules.length);
            updateReqUI('req-upper', rules.upper);
            updateReqUI('req-number', rules.number);
            updateReqUI('req-symbol', rules.symbol);
            
            if (val === "") requirementsPanel.classList.add('hidden');
            else requirementsPanel.classList.remove('hidden');
        });
    }

    // 4. Actualizar Contraseña con Validación de Seguridad
    passwordForm.addEventListener('submit', async function (e) {
        e.preventDefault();
        const currentPass = document.getElementById('current-password').value;
        const newPass = document.getElementById('new-password').value;
        const confirmPass = document.getElementById('confirm-password').value;

        if (newPass !== confirmPass) {
            alert("Las nuevas contraseñas no coinciden.");
            return;
        }

        // --- NUEVA VALIDACIÓN DE COMPLEJIDAD ---
        const regexPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&._-])[A-Za-z\d@$!%*?&._-]{8,}$/;
        if (!regexPassword.test(newPass)) {
            alert("La nueva contraseña debe cumplir con:\n- Mínimo 8 caracteres\n- Al menos una mayúscula\n- Al menos un número\n- Al menos un símbolo (@$!%*?&._-)");
            return;
        }

        try {
            // 1. Obtenemos los datos actuales del usuario (incluyendo la contraseña real)
            const userRes = await fetch(`${CONFIG.API_BASE_URL}/usuarios/${userId}`);
            const result = await userRes.json();

            if (!result.data) {
                alert("Error al verificar identidad.");
                return;
            }

            const userData = result.data;

            // 2. VALIDACIÓN CRÍTICA: ¿La contraseña actual es correcta?
            if (currentPass !== userData.contrasena) {
                alert("La 'Contraseña actual' es incorrecta. Por seguridad, no podemos realizar el cambio.");
                return;
            }
            const updatedUser = {
                ...userData,
                contrasena: newPass,
                modificadoPor: parseInt(userId)
            };

            const response = await fetch(`${CONFIG.API_BASE_URL}/usuarios/${userId}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(updatedUser)
            });

            if (response.ok) {
                alert("¡Contraseña actualizada con éxito!");
                passwordForm.reset();
            }
        } catch (error) {
            console.error("Error seguridad contraseña:", error);
            alert("Hubo un error al procesar el cambio de contraseña.");
        }
    });

    // 5. Actualizar Información de Contacto (Correo)
    if (contactForm) {
        contactForm.addEventListener('submit', async function (e) {
            e.preventDefault();
            if (!userId) return;

            try {
                // Primero obtenemos los datos frescos (para no sobreescribir otros campos)
                const userRes = await fetch(`${CONFIG.API_BASE_URL}/usuarios/${userId}`);
                const result = await userRes.json();
                
                if (result.data) {
                    const updatedUser = {
                        ...result.data,
                        correoUsuario: emailInput.value,
                        modificadoPor: parseInt(userId)
                    };

                    const response = await fetch(`${CONFIG.API_BASE_URL}/usuarios/${userId}`, {
                        method: 'PUT',
                        headers: { 'Content-Type': 'application/json' },
                        body: JSON.stringify(updatedUser)
                    });

                    if (response.ok) {
                        alert("¡Información de contacto actualizada correctamente!");
                        // Actualizar el nombre en el header por si acaso
                        if (headerUserName) headerUserName.textContent = result.data.nombreUsuario;
                    }
                }
            } catch (error) {
                console.error("Error al actualizar contacto:", error);
                alert("Error al guardar los cambios.");
            }
        });
    }

    // 6. Alternar Visibilidad de Contraseñas
    document.querySelectorAll('.toggle-password').forEach(btn => {
        btn.addEventListener('click', function() {
            const targetId = this.getAttribute('data-target');
            const input = document.getElementById(targetId);
            
            if (input.type === 'password') {
                input.type = 'text';
                this.classList.remove('fa-eye');
                this.classList.add('fa-eye-slash');
            } else {
                input.type = 'password';
                this.classList.remove('fa-eye-slash');
                this.classList.add('fa-eye');
            }
        });
    });

    // --- 6. Manejo de Preferencias de Notificaciones ---
    let currentUserData = null; // Guardaremos los datos completos del usuario

    async function cargarPreferenciasNotificaciones() {
        if (!userId) return;
        try {
            const response = await fetch(`${CONFIG.API_BASE_URL}/usuarios/${userId}`);
            const result = await response.json();
            if (result.data) {
                currentUserData = result.data;
                // Sincronizar los switches con los nuevos campos del usuario
                document.getElementById('notif-status').checked = currentUserData.notifStatus || false;
                document.getElementById('notif-docs').checked = currentUserData.notifDocs || false;
                document.getElementById('notif-expiry').checked = currentUserData.notifExpiry || false;
                document.getElementById('notif-news').checked = currentUserData.notifNews || false;
            }
        } catch (error) { console.error("Error cargando preferencias:", error); }
    }

    const btnSaveNotifs = document.getElementById('btn-save-notifs');
    if (btnSaveNotifs) {
        btnSaveNotifs.addEventListener('click', async function () {
            if (!currentUserData) return;

            try {
                // Preparamos el objeto actualizado manteniendo todo lo demás igual
                const updatedUser = {
                    ...currentUserData,
                    notifStatus: document.getElementById('notif-status').checked,
                    notifDocs: document.getElementById('notif-docs').checked,
                    notifExpiry: document.getElementById('notif-expiry').checked,
                    notifNews: document.getElementById('notif-news').checked,
                    modificadoPor: parseInt(userId)
                };

                const response = await fetch(`${CONFIG.API_BASE_URL}/usuarios/${userId}`, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(updatedUser)
                });

                if (response.ok) {
                    alert("¡Preferencias de notificación guardadas correctamente!");
                    currentUserData = updatedUser; // Actualizamos localmente
                }
            } catch (error) {
                console.error("Error guardando preferencias:", error);
                alert("Error al guardar preferencias.");
            }
        });
    }

    // Inicializar todo
    cargarDatosPagina();
    cargarPreferenciasNotificaciones();
});
