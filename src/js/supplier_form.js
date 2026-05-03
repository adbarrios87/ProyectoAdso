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

            // Objeto principal para enviar al backend
            const formData = {
                empresa: {
                    nombre: document.getElementById('company name').value,
                    tipoPersona: document.getElementById('type of person').value,
                    categoria: document.getElementById('company category').value,
                    tipoDocumento: document.getElementById('document-type company').value,
                    numeroDocumento: document.getElementById('document-number company').value,
                    telefono: document.getElementById('phone company').value,
                    correo: document.getElementById('email company').value
                },
                ubicacion: {
                    departamento: document.getElementById('departament').value,
                    ciudad: document.getElementById('city').value,
                    direccion: document.getElementById('addrees').value
                },
                bancaria: {
                    tipoCuenta: document.getElementById('account type').value,
                    numeroCuenta: document.getElementById('account-number').value,
                    metodoPago: document.getElementById('payment_method').value
                },
                contacto1: {
                    nombres: document.getElementById('first-name-c1').value,
                    apellidos: document.getElementById('last-name-c1').value,
                    cargo: document.getElementById('position-c1').value,
                    tipoDocumento: document.getElementById('document-type-c1').value,
                    numeroDocumento: document.getElementById('document-number-c1').value,
                    telefono: document.getElementById('phone-c1').value,
                    correo: document.getElementById('email-c1').value
                },
                representante1: {
                    nombres: document.getElementById('first-name-r1').value,
                    apellidos: document.getElementById('last-name-r1').value,
                    tipoDocumento: document.getElementById('document-type-r1').value,
                    numeroDocumento: document.getElementById('document-number-r1').value,
                    telefono: document.getElementById('phone-r1').value,
                    correo: document.getElementById('email-r1').value
                },
                // Opcionales
                contacto2: {
                    nombres: document.getElementById('first-name-c2')?.value || '',
                    apellidos: document.getElementById('last-name-c2')?.value || '',
                    cargo: document.getElementById('position-c2')?.value || '',
                    tipoDocumento: document.getElementById('document-type-c2')?.value || '',
                    numeroDocumento: document.getElementById('document-number-c2')?.value || '',
                    telefono: document.getElementById('phone-c2')?.value || '',
                    correo: document.getElementById('email-c2')?.value || ''
                },
                representante2: {
                    nombres: document.getElementById('first-name-r2')?.value || '',
                    apellidos: document.getElementById('last-name-r2')?.value || '',
                    tipoDocumento: document.getElementById('document-type-r2')?.value || '',
                    numeroDocumento: document.getElementById('document-number-r2')?.value || '',
                    telefono: document.getElementById('phone-r2')?.value || '',
                    correo: document.getElementById('email-r2')?.value || ''
                },
                socio1: {
                    nombres: document.getElementById('first-name-s1')?.value || '',
                    apellidos: document.getElementById('last-name-s1')?.value || '',
                    tipoDocumento: document.getElementById('document-type-s1')?.value || '',
                    numeroDocumento: document.getElementById('document-number-s1')?.value || '',
                    telefono: document.getElementById('phone-s1')?.value || '',
                    correo: document.getElementById('email-s1')?.value || ''
                },
                socio2: {
                    nombres: document.getElementById('first-name-s2')?.value || '',
                    apellidos: document.getElementById('last-name-s2')?.value || '',
                    tipoDocumento: document.getElementById('document-type-s2')?.value || '',
                    numeroDocumento: document.getElementById('document-number-s2')?.value || '',
                    telefono: document.getElementById('phone-s2')?.value || '',
                    correo: document.getElementById('email-s2')?.value || ''
                }
            };

            const userId = localStorage.getItem('userId');
            
            fetch('http://localhost:8080/proveedores/registro-completo', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ ...formData, idUsuarioAsignado: userId })
            })
            .then(response => {
                if(response.ok) return response.json();
                throw new Error("Error en la petición");
            })
            .then(result => {
                alert('¡Toda la información del proveedor ha sido enviada y registrada en la base de datos!');
                form.reset(); // Limpiar el formulario luego del éxito
            })
            .catch(error => {
                console.error(error);
                alert('Hubo un error al guardar la información. Verifica que el servidor Backend esté ejecutándose.');
            });

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
