document.addEventListener('DOMContentLoaded', () => {
    // Botones de "Ver" en la tabla
    const viewBtns = document.querySelectorAll('.icon-btn.view');
    viewBtns.forEach(btn => {
        btn.addEventListener('click', () => {
             alert('Cargando detalles ampliados de la coincidencia detectada...');
        });
    });

    // Botones de acción final
    const buttons = document.querySelectorAll('.review-actions .btn');
    const textarea = document.querySelector('.risk-observations textarea');
    
    let saveBtn, cancelBtn;
    buttons.forEach(btn => {
        if(btn.textContent.includes('Guardar')) saveBtn = btn;
        if(btn.textContent.includes('Cancelar')) cancelBtn = btn;
    });

    if (saveBtn) {
        saveBtn.addEventListener('click', () => {
            if(textarea && textarea.value.trim() === '') {
                alert('Debe ingresar alguna conclusión en las observaciones antes de guardar la revisión.');
                return;
            }
            alert('Revisión de riesgo guardada exitosamente.');
            window.location.href = 'risk_dashboard.html';
        });
    }

    if (cancelBtn) {
        cancelBtn.addEventListener('click', () => {
            if(confirm('¿Seguro que deseas cancelar? Se perderán las observaciones no guardadas.')) {
                window.location.href = 'risk_dashboard.html';
            }
        });
    }
});
