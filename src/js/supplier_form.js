document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('.user-form');
    if (form) {
        form.addEventListener('submit', (e) => {
            e.preventDefault();
            alert('Información del proveedor registrada/actualizada exitosamente.');
            // Puedes agregar redirección o reseteo acá si lo necesitas
        });
    }

    const resetBtn = document.querySelector('.btn-reset');
    if (resetBtn) {
        resetBtn.addEventListener('click', () => {
            if(!confirm('¿Estás seguro de que deseas limpiar todo el formulario?')) {
                // Prevenir el comportamiento por defecto si cancelan
                event.preventDefault(); 
            }
        });
    }
});
