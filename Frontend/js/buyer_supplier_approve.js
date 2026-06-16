document.addEventListener('DOMContentLoaded', () => {
    const detailBtn = document.querySelector('.btn-secondary');
    if (detailBtn) {
        detailBtn.addEventListener('click', () => {
            alert('Mostrando detalles de validaciones automáticas...');
        });
    }
});
