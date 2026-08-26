document.addEventListener('DOMContentLoaded', () => {
    const recoveryForm = document.querySelector('.recovery-form');
    
    if (recoveryForm) {
        recoveryForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            
            const emailInput = document.getElementById('email');
            const submitBtn = recoveryForm.querySelector('button[type="submit"]');
            const email = emailInput.value.trim();
            
            if (!email) return;

            // Mostrar estado de carga
            const originalBtnHtml = submitBtn.innerHTML;
            submitBtn.disabled = true;
            submitBtn.innerHTML = '<i class="fa-solid fa-circle-notch fa-spin"></i> Enviando...';

            try {
                const response = await fetch(`${CONFIG.API_BASE_URL}/usuarios/recuperar-contrasena?correo=${encodeURIComponent(email)}`, {
                    method: 'POST'
                });

                const result = await response.json();

                if (response.ok && result.data === true) {
                    alert(`El correo de recuperación ha sido enviado con éxito a: ${email}.\n\nPor favor revisa tu bandeja de entrada o la carpeta de spam.`);
                    emailInput.value = '';
                } else {
                    throw new Error(result.message || 'El correo electrónico ingresado no se encuentra registrado en el sistema.');
                }
            } catch (error) {
                console.error(error);
                alert(error.message || 'Hubo un error al procesar tu solicitud. Por favor intenta de nuevo.');
            } finally {
                // Restaurar botón
                submitBtn.disabled = false;
                submitBtn.innerHTML = originalBtnHtml;
            }
        });
    }
});
