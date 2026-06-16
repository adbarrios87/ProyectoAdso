document.addEventListener('DOMContentLoaded', () => {
    const recoveryForm = document.querySelector('.recovery-form');
    
    if (recoveryForm) {
        recoveryForm.addEventListener('submit', (e) => {
            e.preventDefault();
            const email = document.getElementById('email').value;
            
            if(email) {
                alert(`Si el correo ${email} se encuentra registrado en nuestro sistema, en breve recibirás un enlace para restablecer tu contraseña.\n\nPor favor revisa tu bandeja de entrada o la carpeta de spam.`);
            }
        });
    }
});
