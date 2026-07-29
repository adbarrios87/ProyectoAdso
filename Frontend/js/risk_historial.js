document.addEventListener('DOMContentLoaded', async () => {
    const tableBody = document.querySelector('.risk-history-table tbody');
    const filterForm = document.querySelector('.filter-form');

    if (!tableBody) return;

    let historyData = [];

    async function loadHistory() {
        try {
            // Fetch validation-final, providers, and users in parallel to cross-reference names
            const [evalRes, provRes, userRes] = await Promise.all([
                fetch(`${CONFIG.API_BASE_URL}/validacion-final`),
                fetch(`${CONFIG.API_BASE_URL}/proveedores`),
                fetch(`${CONFIG.API_BASE_URL}/usuarios`)
            ]);

            if (!evalRes.ok || !provRes.ok || !userRes.ok) throw new Error('Error al cargar datos del histórico');

            const [evals, provs, users] = await Promise.all([
                evalRes.json().then(r => r.data || []),
                provRes.json().then(r => r.data || []),
                userRes.json().then(r => r.data || [])
            ]);

            // Create lookup maps
            const provMap = new Map(provs.map(p => [p.idProveedor, p]));
            const userMap = new Map(users.map(u => [u.idUsuario, u]));

            // Filter evaluations to only show risk analyst ones (state 8 and 10)
            const riskEvaluations = evals.filter(ev => ev.estadoValidacion === '8' || ev.estadoValidacion === '10');

            historyData = riskEvaluations.map(ev => {
                const prov = provMap.get(ev.idProveedor) || {};
                const user = userMap.get(ev.creadoPor) || {}; // validacion-final uses creadoPor

                return {
                    id: `VAL-${String(ev.idValidacionFinal).padStart(5, '0')}`,
                    nit: prov.numeroIdentificacion || 'Sin NIT',
                    proveedor: prov.razonSocial || `${prov.nombres || ''} ${prov.apellidos || ''}`.trim() || 'Desconocido',
                    fecha: ev.fechaCreado ? new Date(ev.fechaCreado).toLocaleString('es-CO') : '-',
                    analista: `${user.nombres || ''} ${user.apellidos || ''}`.trim() || 'Usuario Local',
                    comentarios: ev.comentarioFinal || 'Sin comentarios',
                    aprobado: ev.estadoValidacion === '10'
                };
            });

            renderHistory(historyData);
        } catch (error) {
            console.error(error);
            tableBody.innerHTML = `<tr><td colspan="11" style="text-align:center;color:red;">Error al cargar el historial de riesgos del servidor.</td></tr>`;
        }
    }

    function renderHistory(list) {
        tableBody.innerHTML = '';
        if (list.length === 0) {
            tableBody.innerHTML = `<tr><td colspan="11" style="text-align:center;">No hay registros históricos de evaluación de riesgos.</td></tr>`;
            return;
        }

        list.forEach(item => {
            const tr = document.createElement('tr');
            const statusClass = item.aprobado ? 'approved' : 'rejected';
            const statusText = item.aprobado ? 'Aprobado' : 'Rechazado';

            tr.innerHTML = `
                <td>${item.id}</td>
                <td>${item.nit}</td>
                <td>${item.proveedor}</td>
                <td>${item.fecha}</td>
                <td>${item.analista}</td>
                <td>${item.comentarios}</td>
                <td><span class="status ${statusClass}">${statusText}</span></td>
                <td>-</td>
                <td>-</td>
                <td>-</td>
                <td>-</td>
            `;
            tableBody.appendChild(tr);
        });
    }

    if (filterForm) {
        filterForm.addEventListener('submit', (e) => {
            e.preventDefault();
            
            const analyst = document.getElementById('filterAnalyst').value.toLowerCase();
            const name = document.getElementById('filterName').value.toLowerCase();
            const nit = document.getElementById('filterNIT').value.toLowerCase();

            const filtered = historyData.filter(item => {
                const matchAnalyst = analyst === "" || item.analista.toLowerCase().includes(analyst);
                const matchName = name === "" || item.proveedor.toLowerCase().includes(name);
                const matchNit = nit === "" || item.nit.toLowerCase().includes(nit);
                return matchAnalyst && matchName && matchNit;
            });

            renderHistory(filtered);
        });

        filterForm.addEventListener('reset', () => {
            setTimeout(() => {
                renderHistory(historyData);
            }, 10);
        });
    }

    await loadHistory();
});
