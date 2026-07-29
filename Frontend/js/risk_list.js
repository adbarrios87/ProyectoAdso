document.addEventListener('DOMContentLoaded', async () => {
    const tableBody = document.querySelector('#risk-table tbody');
    const searchInput = document.getElementById('risk-search-input');

    if (!tableBody) return;

    let suppliers = [];

    async function loadSuppliers() {
        try {
            const response = await fetch(`${CONFIG.API_BASE_URL}/proveedores`);
            if (!response.ok) throw new Error('Error al obtener proveedores');
            const result = await response.json();
            
            // Filter suppliers by idEstadoProveedor = 9 (En revisión con novedad)
            suppliers = (result.data || []).filter(s => s.idEstadoProveedor === 9);
            renderSuppliers(suppliers);
        } catch (error) {
            console.error(error);
            tableBody.innerHTML = `<tr><td colspan="6" style="text-align:center;color:red;">Error al cargar proveedores pendientes de revisión de riesgos.</td></tr>`;
        }
    }

    function renderSuppliers(list) {
        tableBody.innerHTML = '';
        if (list.length === 0) {
            tableBody.innerHTML = `<tr><td colspan="6" style="text-align:center;">No hay proveedores con novedad de riesgo pendientes de revisión.</td></tr>`;
            return;
        }

        list.forEach(prov => {
            const name = prov.razonSocial || `${prov.nombres || ''} ${prov.apellidos || ''}`.trim() || 'Sin Nombre';
            const nit = prov.numeroIdentificacion || 'Sin NIT';
            const date = prov.fechaCreado ? new Date(prov.fechaCreado).toLocaleDateString('es-CO') : '-';
            
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${name}</td>
                <td>${nit}</td>
                <td>Lista Restrictiva (Automatizada)</td>
                <td><span class="badge pending">En revisión con novedad</span></td>
                <td>${date}</td>
                <td><a href="risk_review?id=${prov.idProveedor}"><i class="fa-solid fa-eye action-icon"></i></a></td>
            `;
            tableBody.appendChild(tr);
        });
    }

    if (searchInput) {
        searchInput.addEventListener('input', function() {
            const filter = this.value.toLowerCase();
            const filtered = suppliers.filter(s => {
                const name = (s.razonSocial || `${s.nombres || ''} ${s.apellidos || ''}`).toLowerCase();
                const nit = (s.numeroIdentificacion || '').toLowerCase();
                return name.includes(filter) || nit.includes(filter);
            });
            renderSuppliers(filtered);
        });
    }

    await loadSuppliers();
});
