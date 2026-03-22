document.addEventListener('DOMContentLoaded', () => {
    // Interacción para botones de descarga
    const downloadBtns = document.querySelectorAll('.fa-download');
    downloadBtns.forEach(btn => {
        btn.addEventListener('click', (e) => {
            e.preventDefault();
            alert('Iniciando descarga del documento...');
        });
    });

    const filterSelect = document.querySelector('.filter-select');
    if (filterSelect) {
        filterSelect.addEventListener('change', (e) => {
            console.log('Filtrando documentos por año:', e.target.value);
            // Aquí iría la lógica para filtrar la tabla de documentos
            alert(`Filtrando documentos para el año ${e.target.value}`);
        });
    }

    // Guardar observaciones
    const saveNoteBtn = document.querySelector('.notes + .btn');
    if (saveNoteBtn) {
        saveNoteBtn.addEventListener('click', () => {
            const textarea = document.querySelector('.notes');
            if(textarea.value.trim() !== "") {
                alert('Observación guardada.');
                textarea.value = '';
            } else {
                alert('Escribe una observación antes de guardar.');
            }
        });
    }
});
