document.addEventListener('DOMContentLoaded', () => {
    const buttons = document.querySelectorAll('.review-actions .btn');
    const textarea = document.querySelector('.official-observations textarea');
    
    let approveBtn, rejectBtn;
    buttons.forEach(btn => {
        if(btn.textContent.includes('Aprobar')) approveBtn = btn;
        if(btn.textContent.includes('Rechazar')) rejectBtn = btn;
    });

    // Datos del proveedor (pueden extraerse dinámicamente si se requiere)
    const supplierInfo = {
        nombre: 'C.R. CAFETO',
        nit: '900123456-7',
        fecha: new Date().toLocaleDateString('es-CO'),
    };

    function guardarDecision(decision, justificacion) {
        const registro = {
            proveedor: supplierInfo.nombre,
            nit: supplierInfo.nit,
            fecha: supplierInfo.fecha,
            decision: decision,
            justificacion: justificacion,
        };
        // Guardar en localStorage (array de decisiones)
        let decisiones = JSON.parse(localStorage.getItem('decisionesOficialCumplimiento') || '[]');
        decisiones.push(registro);
        localStorage.setItem('decisionesOficialCumplimiento', JSON.stringify(decisiones));
    }

    if (approveBtn) {
        approveBtn.addEventListener('click', () => {
            if(textarea && textarea.value.trim() === '') {
                alert('Debe ingresar un comentario antes de aprobar.');
                return;
            }
            guardarDecision('Aprobado', textarea.value.trim());
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
            guardarDecision('Rechazado', textarea.value.trim());
            alert('Proveedor rechazado.');
            window.location.href = 'compliance_officer_dashboard.html';
        });
    }
});
