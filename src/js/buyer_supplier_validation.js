document.addEventListener('DOMContentLoaded', () => {
    // Búsqueda en la tabla de personas
    const searchInput = document.querySelector('.search-input');
    const tableRows = document.querySelectorAll('.general-table tbody tr');
    
    if (searchInput) {
        searchInput.addEventListener('input', (e) => {
            const term = e.target.value.toLowerCase();
            tableRows.forEach(row => {
                const text = row.textContent.toLowerCase();
                row.style.display = text.includes(term) ? '' : 'none';
            });
        });
    }

    // Botones de acción principales
    const buttons = document.querySelectorAll('.btn');
    buttons.forEach(btn => {
        btn.addEventListener('click', (e) => {
            if (btn.classList.contains('btn-olive')) {
                alert('Apertura de formulario para agregar persona a consultar.');
            } else if (btn.textContent.includes('Consulta Masiva')) {
                alert('Iniciando consulta masiva en listas restrictivas...');
            } else if (btn.textContent.includes('Exportar Resultados')) {
                alert('Generando archivo Excel...');
            } else if (btn.textContent.includes('Historial')) {
                alert('Cargando historial de validaciones previas...');
            }
        });
    });

    // Botones de la tabla (Editar / Eliminar)
    const tableBtns = document.querySelectorAll('.btn-icon');
    tableBtns.forEach(btn => {
        btn.addEventListener('click', (e) => {
            const row = e.target.closest('tr');
            if (!row) return;

            const name = row.cells[1].textContent;

            if (btn.classList.contains('edit') || btn.closest('.edit')) {
                alert(`Editando información de: ${name}`);
            } else if (btn.classList.contains('delete') || btn.closest('.delete')) {
                if(confirm(`¿Seguro que deseas eliminar a ${name}?`)) {
                    row.remove();
                }
            }
        });
    });
});
