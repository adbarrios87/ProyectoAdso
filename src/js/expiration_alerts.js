document.addEventListener('DOMContentLoaded', () => {
    const alertIcons = document.querySelectorAll('.alert-icon');
    
    alertIcons.forEach(icon => {
        // Envolver en estilo de cursor apuntador
        icon.style.cursor = 'pointer';
        
        icon.addEventListener('click', (e) => {
             const row = e.target.closest('tr');
             if(!row) return;
             
             const companyName = row.cells[0].textContent;
             const status = row.cells[3].textContent;
             
             if (status.toLowerCase().includes('vencido')) {
                 alert(`Atención: La documentación de ${companyName} ya está vencida. Se enviará una notificación de bloqueo o advertencia severa.`);
             } else {
                 alert(`Enviando recordatorio preventivo automático a: ${companyName}`);
             }
        });
    });
});
