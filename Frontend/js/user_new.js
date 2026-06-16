// Esperamos a que todo el contenido HTML de la página se haya cargado
document.addEventListener("DOMContentLoaded", function () {


    cargarTiposIdentificacion("document-type");
    cargarRoles("role");
    cargarTiposPersona("person-type");

    // 1. Seleccionamos el formulario usando su clase
    const form = document.querySelector(".user-form");

    // --- Lógica para restringir caracteres en campos de texto ---
    const textFields = ["first-name", "last-name", "position", "razon-social"];
    textFields.forEach(id => {
        const inputElement = document.getElementById(id);
        if (inputElement) {
            inputElement.addEventListener("input", function () {
                this.value = this.value.replace(/[^a-zA-ZáéíóúÁÉÍÓÚñÑ\s]/g, "");
            });
        }
    });

    // --- Lógica de Interactividad (Proveedor / Tipo de Persona) ---
    const roleSelect = document.getElementById('role');
    const positionContainer = document.getElementById('position-container');
    const personTypeContainer = document.getElementById('person-type-container');
    const namesContainer = document.getElementById('names-container');
    const razonSocialContainer = document.getElementById('razon-social-container');
    const personTypeSelect = document.getElementById('person-type');

    roleSelect.addEventListener('change', function() {
        if (this.value === "3") { // Proveedor
            positionContainer.classList.add('hidden');
            document.getElementById('position').removeAttribute('required');
            personTypeContainer.classList.remove('hidden');
            personTypeSelect.setAttribute('required', 'true');
        } else {
            positionContainer.classList.remove('hidden');
            document.getElementById('position').setAttribute('required', 'true');
            personTypeContainer.classList.add('hidden');
            personTypeSelect.removeAttribute('required');
            // Reset to Natural fields
            namesContainer.classList.remove('hidden');
            razonSocialContainer.classList.add('hidden');
            document.getElementById('first-name').setAttribute('required', 'true');
            document.getElementById('last-name').setAttribute('required', 'true');
            document.getElementById('razon-social').removeAttribute('required');
        }
    });

    personTypeSelect.addEventListener('change', function() {
        if (this.value === "2") { // Jurídica
            namesContainer.classList.add('hidden');
            document.getElementById('first-name').removeAttribute('required');
            document.getElementById('last-name').removeAttribute('required');
            razonSocialContainer.classList.remove('hidden');
            document.getElementById('razon-social').setAttribute('required', 'true');
        } else { // Natural o vacío
            namesContainer.classList.remove('hidden');
            document.getElementById('first-name').setAttribute('required', 'true');
            document.getElementById('last-name').setAttribute('required', 'true');
            razonSocialContainer.classList.add('hidden');
            document.getElementById('razon-social').removeAttribute('required');
        }
    });

    // --- Lógica de Validación en Tiempo Real (Requisitos de Contraseña) ---
    const passwordInput = document.getElementById('password');
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

    if (passwordInput && requirementsPanel) {
        passwordInput.addEventListener('focus', () => requirementsPanel.classList.remove('hidden'));
        
        passwordInput.addEventListener('input', function() {
            const val = this.value;
            
            const rules = {
                length: val.length >= 8,
                upper: /[A-Z]/.test(val),
                number: /\d/.test(val),
                symbol: /[@$!%*?&._-]/.test(val)
            };

            updateReqUI('req-length', rules.length);
            updateReqUI('req-upper', rules.upper);
            updateReqUI('req-number', rules.number);
            updateReqUI('req-symbol', rules.symbol);
            
            if (val === "") requirementsPanel.classList.add('hidden');
            else requirementsPanel.classList.remove('hidden');
        });
    }

    // 2. Agregamos un 'evento' que escuche cuando el usuario intente enviar (submit) el formulario
    form.addEventListener("submit", function (event) {
        event.preventDefault();

        const idRol = document.getElementById("role").value;
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirm-password").value;
        const email = document.getElementById("email").value;
        const documentNumber = document.getElementById("document-number").value;
        const idTipoIdentificacion = document.getElementById("document-type").value;

        // Validaciones básicas
        if (password !== confirmPassword) {
            alert("¡Error! Las contraseñas no coinciden.");
            return;
        }

        const regexPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&._-])[A-Za-z\d@$!%*?&._-]{8,}$/;
        if (!regexPassword.test(password)) {
            alert("¡Error! La contraseña no cumple los requisitos.");
            return;
        }

        const sesionUsuarioId = localStorage.getItem('userId');
        if (!sesionUsuarioId) {
            alert("Error de seguridad: No hay sesión activa.");
            window.location.href = "../../login.html";
            return;
        }

        // Construir Request Body dinámicamente
        const requestBody = {
            correoUsuario: email,
            contrasena: password,
            estadoUsuario: true,
            idRol: parseInt(idRol),
            creadoPor: parseInt(sesionUsuarioId),
            idTipoIdentificacion: parseInt(idTipoIdentificacion),
            numeroIdentificacion: documentNumber
        };

        let nombreCompleto = "";

        if (idRol === "3") { // Proveedor
            const idTipoPersona = document.getElementById('person-type').value;
            requestBody.idTipoPersona = parseInt(idTipoPersona);
            
            if (idTipoPersona === "2") { // Jurídica
                const razonSocial = document.getElementById('razon-social').value;
                requestBody.razonSocial = razonSocial;
                nombreCompleto = razonSocial;
            } else { // Natural
                const firstName = document.getElementById("first-name").value;
                const lastName = document.getElementById("last-name").value;
                requestBody.nombres = firstName;
                requestBody.apellidos = lastName;
                nombreCompleto = firstName + " " + lastName;
            }
            requestBody.cargoUsuario = "Proveedor"; // Valor por defecto para cargo en tabla usuario
        } else {
            const firstName = document.getElementById("first-name").value;
            const lastName = document.getElementById("last-name").value;
            nombreCompleto = firstName + " " + lastName;
            requestBody.cargoUsuario = document.getElementById("position").value;
        }

        requestBody.nombreUsuario = nombreCompleto;

        // Enviar al Backend
        fetch(`${CONFIG.API_BASE_URL}/usuarios`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(requestBody)
        })
            .then(response => response.json())
            .then(result => {
                if (result.data) {
                    alert(`¡Usuario ${nombreCompleto} creado exitosamente!`);
                    form.reset();
                    // Restaurar estado inicial de campos
                    positionContainer.classList.remove('hidden');
                    personTypeContainer.classList.add('hidden');
                    namesContainer.classList.remove('hidden');
                    razonSocialContainer.classList.add('hidden');
                    if (requirementsPanel) requirementsPanel.classList.add('hidden');
                } else {
                    alert("Hubo un error al crear el usuario.");
                }
            })
            .catch(error => {
                console.error("Error:", error);
                alert("No se pudo conectar con el servidor.");
            });
    });

});

