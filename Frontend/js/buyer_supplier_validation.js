document.addEventListener('DOMContentLoaded', () => {
    // === MODAL AGREGAR PERSONA ===
    const modal = document.getElementById('addPersonModal');
    const btnAddPerson = document.getElementById('btnAddPerson');
    const closeModal = document.querySelector('.close-modal');
    const btnCancel = document.querySelector('.btn-cancel');
    const addPersonForm = document.getElementById('addPersonForm');

    // Abrir modal
    if (btnAddPerson) {
        btnAddPerson.addEventListener('click', () => {
            modal.classList.add('active');
        });
    }

    // Cerrar modal
    if (closeModal) {
        closeModal.addEventListener('click', () => {
            modal.classList.remove('active');
        });
    }

    if (btnCancel) {
        btnCancel.addEventListener('click', () => {
            modal.classList.remove('active');
        });
    }

    // Cerrar al hacer clic fuera del modal
    if (modal) {
        modal.addEventListener('click', (e) => {
            if (e.target === modal) {
                modal.classList.remove('active');
            }
        });
    }

    // Enviar formulario
    if (addPersonForm) {
        addPersonForm.addEventListener('submit', (e) => {
            e.preventDefault();
            const docType = document.getElementById('docType').value;
            const docNumber = document.getElementById('docNumber').value;
            const docDv = document.getElementById('docDv').value;
            const fullName = document.getElementById('fullName').value;
            
            alert(`Consultando en listas restrictivas:\nTipo: ${docType}\nNúmero: ${docNumber}\nDV: ${docDv}\nNombre: ${fullName}`);
            modal.classList.remove('active');
            addPersonForm.reset();
        });
    }

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
