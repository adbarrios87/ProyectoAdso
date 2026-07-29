document.addEventListener('DOMContentLoaded', async () => {
    const tableBody = document.getElementById('approval-table-body');
    const searchInput = document.getElementById('approval-search-input');

    if (!tableBody) return;

    let historyList = [];

    async function loadApprovalHistory() {
        try {
            // Fetch evaluations and suppliers in parallel
            const [evalRes, provRes] = await Promise.all([
                fetch(`${CONFIG.API_BASE_URL}/evaluacion_riesgos`),
                fetch(`${CONFIG.API_BASE_URL}/proveedores`)
            ]);

            if (!evalRes.ok || !provRes.ok) {
                throw new Error('Error al cargar datos de auditoría o proveedores');
            }

            const [evaluations, providers] = await Promise.all([
                evalRes.json().then(r => r.data || []),
                provRes.json().then(r => r.data || [])
            ]);

            // Map providers by ID for easy lookup
            const providersMap = new Map(providers.map(p => [p.idProveedor, p]));

            // Process evaluations list
            historyList = evaluations.map(ev => {
                const prov = providersMap.get(ev.idProveedor) || {};
                const name = prov.razonSocial || `${prov.nombres || ''} ${prov.apellidos || ''}`.trim() || 'Proveedor Desconocido';
                const nit = prov.numeroIdentificacion || 'Sin NIT';
                const fechaDet = prov.fechaCreado ? new Date(prov.fechaCreado).toLocaleDateString('es-CO') : '-';
                const fechaApro = ev.fecha ? new Date(ev.fecha).toLocaleDateString('es-CO') : '-';

                return {
                    idProveedor: ev.idProveedor,
                    nombre: name,
                    nit: nit,
                    fechaDeteccion: fechaDet,
                    fechaAprobacion: fechaApro,
                    aprobado: ev.validacionAuditoria === true
                };
            });

            renderTable(historyList);
        } catch (error) {
            console.error(error);
            tableBody.innerHTML = `<tr><td colspan="7" style="text-align:center;color:red;">Error al cargar el histórico desde el servidor.</td></tr>`;
        }
    }

    function renderTable(list) {
        tableBody.innerHTML = '';

        if (list.length === 0) {
            tableBody.innerHTML = `<tr><td colspan="7" style="text-align:center;">No hay registros de aprobaciones o rechazos de riesgos en el histórico.</td></tr>`;
            return;
        }

        list.forEach(item => {
            const tr = document.createElement('tr');

            const badgeClass = item.aprobado ? 'badge clear' : 'badge danger';
            const badgeText = item.aprobado ? 'Aprobado' : 'Rechazado';

            tr.innerHTML = `
                <td style="text-align: left;">${item.nombre}</td>
                <td style="text-align: center;">${item.nit}</td>
                <td style="text-align: center;">${item.fechaDeteccion}</td>
                <td style="text-align: center;">${item.fechaAprobacion}</td>
                <td style="text-align: center;"><span class="${badgeClass}">${badgeText}</span></td>
                <td style="text-align: center;"><a href="risk_review?id=${item.idProveedor}&mode=edit"><i class="fa-solid fa-eye action-icon"></i></a></td>
            `;

            tableBody.appendChild(tr);
        });
    }

    // Search bar functionality
    if (searchInput) {
        searchInput.addEventListener('input', function () {
            const filter = this.value.toLowerCase();
            const filtered = historyList.filter(item =>
                item.nombre.toLowerCase().includes(filter) ||
                item.nit.toLowerCase().includes(filter) ||
                item.fechaAprobacion.toLowerCase().includes(filter)
            );
            renderTable(filtered);
        });
    }

    await loadApprovalHistory();
});
