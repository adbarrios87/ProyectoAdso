document.addEventListener('DOMContentLoaded', async () => {
    const urlParams = new URLSearchParams(window.location.search);
    const token = urlParams.get('token');
    const resetForm = document.querySelector('.reset-form');

    if (!token) {
        alert('Enlace de recuperación inválido o token ausente. Serás redirigido al inicio de sesión.');
        window.location.href = '../../login.html';
        return;
    }

    // --- 1. Validar el token con el servidor al cargar ---
    try {
        const validateRes = await fetch(`${CONFIG.API_BASE_URL}/usuarios/validar-token-recuperacion?token=${token}`);
        if (!validateRes.ok) {
            const errData = await validateRes.json();
            throw new Error(errData.message || 'El enlace de recuperación es inválido o ha expirado.');
        }
    } catch (e) {
        alert(e.message);
        window.location.href = '../../login.html';
        return;
    }

    // --- 2. Procesar el formulario de restablecimiento ---
    if (resetForm) {
        resetForm.addEventListener('submit', async (e) => {
            e.preventDefault();

            const passwordInput = document.getElementById('password');
            const confirmInput = document.getElementById('confirm-password');
            const submitBtn = resetForm.querySelector('button[type="submit"]');

            const password = passwordInput.value;
            const confirmPassword = confirmInput.value;

            // Validaciones básicas
            if (password.length < 6) {
                alert('La contraseña debe tener al menos 6 caracteres.');
                return;
            }

            if (password !== confirmPassword) {
                alert('Las contraseñas ingresadas no coinciden.');
                return;
            }

            // Iniciar animación de carga
            const originalBtnHtml = submitBtn.innerHTML;
            submitBtn.disabled = true;
            submitBtn.innerHTML = '<i class="fa-solid fa-circle-notch fa-spin"></i> Guardando...';

            try {
                const res = await fetch(`${CONFIG.API_BASE_URL}/usuarios/restablecer-contrasena`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        token: token,
                        contrasena: password
                    })
                });

                const result = await res.json();

                if (res.ok && result.data === true) {
                    alert('Tu contraseña ha sido restablecida exitosamente. Ahora puedes iniciar sesión con tus nuevas credenciales.');
                    window.location.href = '../../login.html?success=true';
                } else {
                    throw new Error(result.message || 'Error al restablecer la contraseña.');
                }
            } catch (err) {
                console.error(err);
                alert(err.message || 'Hubo un error inesperado al restablecer tu contraseña.');
            } finally {
                submitBtn.disabled = false;
                submitBtn.innerHTML = originalBtnHtml;
            }
        });
    }
});
