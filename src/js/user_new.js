// Esperamos a que todo el contenido HTML de la página se haya cargado
document.addEventListener("DOMContentLoaded", function() {
    
    // 1. Seleccionamos el formulario usando su clase
    const form = document.querySelector(".user-form");

    // --- Lógica para validación en tiempo real de la contraseña ---
    const passwordInput = document.getElementById("password");
    const passwordMessage = document.createElement("small");
    passwordMessage.style.display = "block";
    passwordMessage.style.marginTop = "5px";
    passwordMessage.style.fontWeight = "bold";
    // Colocar el mensaje justo debajo del campo de contraseña
    passwordInput.parentNode.appendChild(passwordMessage);

    passwordInput.addEventListener("input", function() {
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
    form.addEventListener("submit", function(event) {
        
        // Prevenimos el comportamiento por defecto (que recarga la página al enviar)
        event.preventDefault();

        // 3. Obtenemos los valores de los diferentes campos usando su ID
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirm-password").value;
        const firstName = document.getElementById("first-name").value;
        const lastName = document.getElementById("last-name").value;
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

        // Si todas las validaciones pasan, podemos mostrar los datos en consola (F12 en el navegador)
        console.log("Datos capturados listos para enviar:", {
            nombres: firstName,
            apellidos: lastName,
            correo: email,
            documento: documentNumber
        });

        // 6. Mostramos un mensaje de éxito al usuario
        alert(`¡Registro simulado con éxito para ${firstName} ${lastName}!`);

        // 7. Limpiamos los campos del formulario tras el éxito
        form.reset();
    });

});
