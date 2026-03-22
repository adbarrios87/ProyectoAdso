document.addEventListener('DOMContentLoaded', () => {
    const buttons = document.querySelectorAll('.review-actions .btn');
    const textarea = document.querySelector('.official-observations textarea');
    
    let approveBtn, rejectBtn;
    buttons.forEach(btn => {
        if(btn.textContent.includes('Aprobar')) approveBtn = btn;
        if(btn.textContent.includes('Rechazar')) rejectBtn = btn;
    });

    if (approveBtn) {
        approveBtn.addEventListener('click', () => {
            if(textarea && textarea.value.trim() === '') {
                alert('Debe ingresar un comentario antes de aprobar.');
                return;
            }
            alert('Proveedor aprobado exitosamente.');
            window.location.href = 'compliance_officer_dashboard.html';
        });
    }

    if (rejectBtn) {
        rejectBtn.addEventListener('click', () => {
            if(textarea && textarea.value.trim() === '') {
                alert('Debe ingresar el motivo del rechazo en las observaciones.');
                return;
            }
            alert('Proveedor rechazado.');
            window.location.href = 'compliance_officer_dashboard.html';
        });
    }
});
