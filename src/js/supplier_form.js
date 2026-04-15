document.addEventListener('DOMContentLoaded', () => {
    // --- 1. Lógica para restringir caracteres en nombres y cargos (Solo letras y espacios) ---
    const textFields = [
        "company category", "departament", "city",
        "first-name-c1", "last-name-c1", "position-c1",
        "first-name-r1", "last-name-r1", 
        "first-name-c2", "last-name-c2", "position-c2",
        "first-name-r2", "last-name-r2",
        "first-name-s1", "last-name-s1",
        "first-name-s2", "last-name-s2"
    ];

    textFields.forEach(id => {
        const inputElement = document.getElementById(id);
        if (inputElement) {
            inputElement.addEventListener("input", function() {
                // Remover cualquier carácter que no sea letra o espacio en tiempo real
                this.value = this.value.replace(/[^a-zA-ZáéíóúÁÉÍÓÚñÑ\s]/g, "");
            });
        }
    });

    // --- 2. Lógica para restringir a solo números (Teléfonos, Cuentas, Documentos) ---
    const numberFields = [
        "document-number company", "phone company", "account-number",
        "document-number-c1", "phone-c1",
        "document-number-r1", "phone-r1",
        "document-number-c2", "phone-c2",
        "document-number-r2", "phone-r2",
        "document-number-s1", "phone-s1",
        "document-number-s2", "phone-s2"
    ];

    numberFields.forEach(id => {
        const inputElement = document.getElementById(id);
        if (inputElement) {
            inputElement.addEventListener("input", function() {
                // Remover cualquier carácter que no sea número en tiempo real
                this.value = this.value.replace(/[^0-9]/g, "");
            });
        }
    });

    // --- 3. Lógica principal del formulario ---
    const form = document.querySelector('.user-form');
    if (form) {
        form.addEventListener('submit', (e) => {
            e.preventDefault();

            // Validación de correos electrónicos antes de enviar
            const emailFields = [
                "email company", "email-c1", "email-r1", 
                "email-c2", "email-r2", "email-s1", "email-s2"
            ];
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            let hasEmailError = false;

            for (const id of emailFields) {
                const emailInput = document.getElementById(id);
                // Validar solo si el campo existe y tiene un valor (para campos auxiliares no obligatorios)
                if (emailInput && emailInput.value.trim() !== "") {
                    if (!emailRegex.test(emailInput.value.trim())) {
                        alert(`¡Error! El correo ingresado (${emailInput.value}) no es válido. Debe contener un '@' y un dominio (ejemplo: usuario@correo.com).`);
                        emailInput.focus();
                        hasEmailError = true;
                        break; // Detener la validación si hay un error y mostrar mensaje
                    }
                }
            }

            if (hasEmailError) {
                return; // Cortar ejecución si el correo está mal
            }

            alert('Información del proveedor registrada/actualizada exitosamente.');
            form.reset(); // Limpiar el formulario luego del éxito
        });
    }

    const resetBtn = document.querySelector('.btn-reset');
    if (resetBtn) {
        resetBtn.addEventListener('click', (event) => {
            if(!confirm('¿Estás seguro de que deseas limpiar todo el formulario?')) {
                // Prevenir el comportamiento por defecto si cancelan
                event.preventDefault(); 
            }
        });
    }
});
