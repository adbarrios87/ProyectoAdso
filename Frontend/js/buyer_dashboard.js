document.addEventListener('DOMContentLoaded', () => {
    cargarTareasPendientes();

    // Interacción en la barra de búsqueda del Dashboard
    const filterId = document.getElementById('filter-id');
    const filterName = document.getElementById('filter-name');
    
    if (filterId || filterName) {
        const filters = [filterId, filterName];
        filters.forEach(filter => {
            if(filter) {
                filter.addEventListener('change', (e) => {
                    console.log(`Filtro ${e.target.id} cambiado a: ${e.target.checked}`);
                });
            }
        });
    }
});

async function cargarTareasPendientes() {
    try {
        // Estado 5: En Revisión (Expediente completo / REG)
        const response = await fetch(`${CONFIG.API_BASE_URL}/proveedores/estado/5`);
        if (!response.ok) throw new Error('Error al cargar proveedores en revisión');
        
        const result = await response.json();
        const proveedores = result.data;
        const ul = document.getElementById('tasks-list');
        
        if (!ul) return;

        ul.innerHTML = '';
        if (!proveedores || proveedores.length === 0) {
            ul.innerHTML = '<li>No hay tareas pendientes.</li>';
            return;
        }

        proveedores.forEach(prov => {
            const li = document.createElement('li');
            li.innerHTML = `<input type="checkbox"> Revisar documentación de <strong>${prov.razonSocial || prov.nombres + ' ' + prov.apellidos}</strong>`;
            
            const checkbox = li.querySelector('input');
            checkbox.addEventListener('change', function() {
                if (this.checked) {
                    li.style.textDecoration = 'line-through';
                    li.style.color = '#888';
                } else {
                    li.style.textDecoration = 'none';
                    li.style.color = 'inherit';
                }
            });
            
            ul.appendChild(li);
        });

    } catch (e) {
        console.error(e);
        const ul = document.getElementById('tasks-list');
        if (ul) ul.innerHTML = '<li style="color:red">Error cargando tareas.</li>';
    }
}
