document.addEventListener('DOMContentLoaded', async () => {
    const tbody = document.getElementById('tbody-compliance-list');
    const kpiApproved = document.getElementById('kpi-approved');
    const kpiRejected = document.getElementById('kpi-rejected');
    const kpiAlerts = document.getElementById('kpi-alerts');

    if (!tbody) return;

    async function loadComplianceData() {
        try {
            // Obtener proveedores, estados y validaciones finales
            const [provRes, statusRes, valRes] = await Promise.all([
                fetch(`${CONFIG.API_BASE_URL}/proveedores`),
                fetch(`${CONFIG.API_BASE_URL}/estado_proveedor`),
                fetch(`${CONFIG.API_BASE_URL}/validacion-final`)
            ]);

            if (!provRes.ok || !statusRes.ok || !valRes.ok) {
                throw new Error('Error al cargar datos de proveedores, estados o validaciones finales');
            }

            const [suppliers, statuses, finalValidations] = await Promise.all([
                provRes.json().then(r => r.data || []),
                statusRes.json().then(r => r.data || []),
                valRes.json().then(r => r.data || [])
            ]);

            // Asignar descripciones de estado por ID
            const statusMap = new Map(statuses.map(st => [st.idEstadoProveedor, st.descripcion]));

            // Mostrar proveedores para revisión de cumplimiento estado 8 (Rechazado por Novedad ) o estado 10 (Revisado con novedad)
            const complianceSuppliers = suppliers.filter(s => s.idEstadoProveedor === 8 || s.idEstadoProveedor === 10);

            // Obtener KPIs dinámicamente desde la base de datos
            if (kpiApproved) {
                // Aprobados por cumplimiento son aquellos con stateValidacion = "11"
                const approvedCount = finalValidations.filter(v => v.estadoValidacion === '11').length;
                kpiApproved.textContent = approvedCount;
            }
            if (kpiRejected) {
                // Rechazados por cumplimiento son aquellos con stateValidacion = "12"
                const rejectedCount = finalValidations.filter(v => v.estadoValidacion === '12').length;
                kpiRejected.textContent = rejectedCount;
            }
            if (kpiAlerts) {
                // proveedores con aleta de riesgo en estado 8 (Rechazado por Novedad ) o estado 10 (Revisado con novedad)
                const alertsCount = suppliers.filter(s => s.idEstadoProveedor === 8 || s.idEstadoProveedor === 10).length;
                kpiAlerts.textContent = alertsCount;
            }

            // Renderizar filas de la tabla
            tbody.innerHTML = '';
            if (complianceSuppliers.length === 0) {
                tbody.innerHTML = `<tr><td colspan="5" style="text-align:center;">No hay proveedores pendientes de decisión final.</td></tr>`;
                return;
            }

            complianceSuppliers.forEach(prov => {
                const name = prov.razonSocial || `${prov.nombres || ''} ${prov.apellidos || ''}`.trim() || 'Sin Nombre';
                const nit = prov.numeroIdentificacion || 'Sin NIT';
                const statusName = statusMap.get(prov.idEstadoProveedor) || `Estado #${prov.idEstadoProveedor}`;
                const date = prov.fechaCreado ? new Date(prov.fechaCreado).toLocaleDateString('es-CO') : '-';

                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td style="text-align: left;">${name}</td>
                    <td style="text-align: center;">${nit}</td>
                    <td style="text-align: center;">${statusName}</td>
                    <td style="text-align: center;">${date}</td>
                    <td style="text-align: center;">
                        <a href="compliance_officer_review?id=${prov.idProveedor}" class="icon-btn view" title="Revisar">
                            <i class="fa-solid fa-bullseye"></i>
                        </a>
                    </td>
                `;
                tbody.appendChild(tr);
            });

        } catch (error) {
            console.error(error);
            tbody.innerHTML = `<tr><td colspan="5" style="text-align:center;color:red;">Error al cargar proveedores desde el servidor.</td></tr>`;
        }
    }

    await loadComplianceData();
});
