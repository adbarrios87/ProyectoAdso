// Esperamos a que todo el contenido HTML de la página se haya cargado
document.addEventListener("DOMContentLoaded", function () {

    // 1. Seleccionamos el formulario usando su clase
    const form = document.querySelector(".user-form");

    // --- Lógica para restringir caracteres en campos de texto (Solo letras y espacios) ---
    const textFields = ["first-name", "last-name", "position"];
    textFields.forEach(id => {
        const inputElement = document.getElementById(id);
        if (inputElement) {
            inputElement.addEventListener("input", function () {
                // Remover cualquier carácter que no sea letra o espacio en tiempo real
                this.value = this.value.replace(/[^a-zA-ZáéíóúÁÉÍÓÚñÑ\s]/g, "");
            });
        }
    });

    // --- Lógica para validación en tiempo real de la contraseña ---
    const passwordInput = document.getElementById("password");
    const passwordMessage = document.createElement("small");
    passwordMessage.style.display = "block";
    passwordMessage.style.marginTop = "5px";
    passwordMessage.style.fontWeight = "bold";
    // Colocar el mensaje justo debajo del campo de contraseña
    passwordInput.parentNode.appendChild(passwordMessage);

    passwordInput.addEventListener("input", function () {
        const val = passwordInput.value;
        let errors = [];

        if (val.length > 0 && val.length < 6) {
            errors.push("6 caracteres");
        }
        if (!/[A-Z]/.test(val)) {
            errors.push("una mayúscula");
        }
        if (!/[!@#$%^&*(),.?":{}|<>]/.test(val)) {
            errors.push("un carácter especial");
        }

        if (val.length === 0) {
            passwordMessage.textContent = "";
        } else if (errors.length > 0) {
            passwordMessage.textContent = "Tu contraseña debe incluir: " + errors.join(", ") + ".";
            passwordMessage.style.color = "#d9534f"; // Rojo
        } else {
            passwordMessage.textContent = "El formato de tu contraseña es seguro.";
            passwordMessage.style.color = "#5cb85c"; // Verde oscuro
        }
    });

    // 2. Agregamos un 'evento' que escuche cuando el usuario intente enviar (submit) el formulario
    form.addEventListener("submit", function (event) {

        // Prevenimos el comportamiento por defecto (que recarga la página al enviar)
        event.preventDefault();

        // 3. Obtenemos los valores de los diferentes campos usando su ID
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirm-password").value;
        const firstName = document.getElementById("first-name").value;
        const lastName = document.getElementById("last-name").value;
        const position = document.getElementById("position").value;
        const email = document.getElementById("email").value;
        const documentNumber = document.getElementById("document-number").value;

        // 4. Validación 1: Verificar que las contraseñas coincidan
        if (password !== confirmPassword) {
            // Si no coinciden, mostramos una alerta al usuario
            alert("¡Error! Las contraseñas no coinciden. Por favor, escríbelas igual.");

            // Retornamos para detener la ejecución y no simular el registro
            return;
        }

        // 5. Validación 2: Validar la longitud y formato de la contraseña
        if (password.length < 6) {
            alert("La contraseña debe tener al menos 6 caracteres.");
            return;
        }
        if (!/[A-Z]/.test(password) || !/[!@#$%^&*(),.?":{}|<>]/.test(password)) {
            alert("¡Error! La contraseña debe contener al menos una letra mayúscula y un carácter especial.");
            return;
        }

        // 6. Validación 3: Validar que el número de documento solo contenga números
        const documentRegex = /^[0-9]+$/;
        if (!documentRegex.test(documentNumber)) {
            alert("¡Error! El número de documento (" + documentNumber + ") no es válido. Solo se permiten números.");
            return;
        }

        // 7. Validación 4: Nombres, apellidos y cargo solo letras
        const letterRegex = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/;
        if (!letterRegex.test(firstName)) {
            alert("¡Error! El campo 'Nombres' solo debe contener letras.");
            return;
        }
        if (!letterRegex.test(lastName)) {
            alert("¡Error! El campo 'Apellidos' solo debe contener letras.");
            return;
        }
        if (!letterRegex.test(position)) {
            alert("¡Error! El campo 'Cargo' solo debe contener letras.");
            return;
        }

        // 8. Preparar los datos para el Backend
        const roleString = document.getElementById("role").value;
        let idRolValue = 1;
        switch(roleString) {
            case 'admin': idRolValue = 1; break;
            case 'proveedor': idRolValue = 2; break;
            case 'comprador': idRolValue = 3; break;
            case 'analista': idRolValue = 4; break;
            case 'oficial': idRolValue = 5; break;
        }

        const requestBody = {
            nombreUsuario: firstName + " " + lastName,
            cargoUsuario: position,
            correoUsuario: email,
            contrasena: password,
            estadoUsuario: true,
            idRol: idRolValue
        };

        // 9. Enviar al Backend
        fetch('http://localhost:8080/usuarios', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(requestBody)
        })
        .then(response => response.json())
        .then(result => {
            if (result.data) {
                alert(`¡Usuario ${firstName} ${lastName} creado exitosamente en la Base de Datos!`);
                form.reset();
                passwordMessage.textContent = "";
            } else {
                alert("Hubo un error al crear el usuario en el servidor.");
            }
        })
        .catch(error => {
            console.error("Error al conectar con el servidor:", error);
            alert("No se pudo conectar con el servidor. Verifica que el Backend esté encendido en IntelliJ.");
        });
    });

});
