document.addEventListener('DOMContentLoaded', () => {
    const docPendientes = document.querySelector('.card.kpi a');
    if (docPendientes) {
        docPendientes.addEventListener('click', (e) => {
            e.preventDefault();
            window.location.href = 'supplier_upload_documents.html';
        });
    }
});
