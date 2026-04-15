document.addEventListener('DOMContentLoaded', () => {
    // Tareas pendientes: tachar al hacer check
    const taskCheckboxes = document.querySelectorAll('.tasks input[type="checkbox"]');
    
    taskCheckboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function() {
            const listItem = this.parentElement;
            if (this.checked) {
                listItem.style.textDecoration = 'line-through';
                listItem.style.color = '#888';
            } else {
                listItem.style.textDecoration = 'none';
                listItem.style.color = 'inherit';
            }
        });
    });

    // Interacción en la barra de búsqueda del Dashboard
    const filterId = document.getElementById('filter-id');
    const filterName = document.getElementById('filter-name');
    
    if (filterId || filterName) {
        const filters = [filterId, filterName];
        filters.forEach(filter => {
            if(filter) {
                filter.addEventListener('change', (e) => {
                    console.log(`Filtro ${e.target.id} cambiado a: ${e.target.checked}`);
                    // Aquí podrías agregar lógica para filtrar una tabla real
                });
            }
        });
    }
});
