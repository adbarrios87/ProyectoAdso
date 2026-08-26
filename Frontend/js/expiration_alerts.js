document.addEventListener('DOMContentLoaded', () => {
    cargarAlertasVencimiento();
});

async function cargarAlertasVencimiento() {
    try {
        const response = await fetch(`${CONFIG.API_BASE_URL}/proveedores/estado/6`);
        if (!response.ok) throw new Error("Error al cargar proveedores aprobados");
        const result = await response.json();
        const proveedores = result.data;
        
        const tbody = document.getElementById('alerts-body');
        if (!tbody) return;
        
        tbody.innerHTML = '';
        
        let criticos = 0;
        let urgentes = 0;
        let pendientes = 0;

        const hoy = new Date();

        proveedores.forEach(prov => {
            if (!prov.fechaAprobacion) return;
            const fechaAprobacion = new Date(prov.fechaAprobacion);
            
            // Si tiene fechaAprobacion, vence en 365 días
            const fechaVencimiento = new Date(fechaAprobacion);
            fechaVencimiento.setDate(fechaVencimiento.getDate() + 365);
            
            const diasRestantes = Math.ceil((fechaVencimiento - hoy) / (1000 * 60 * 60 * 24));
            
            // Mostrar si está a menos de 40 días, por ejemplo
            if (diasRestantes <= 40) {
                let badgeClass = 'success';
                let rowClass = 'ok-row';
                let estadoText = 'BIEN';
                
                if (diasRestantes <= 0) {
                    badgeClass = 'danger';
                    rowClass = 'critical-row';
                    estadoText = 'VENCIDO';
                    criticos++;
                } else if (diasRestantes <= 15) {
                    badgeClass = 'danger';
                    rowClass = 'critical-row';
                    estadoText = 'CRÍTICO';
                    criticos++;
                } else if (diasRestantes <= 30) {
                    badgeClass = 'warning';
                    rowClass = 'warning-row';
                    estadoText = 'URGENTE';
                    urgentes++;
                } else {
                    badgeClass = 'info';
                    rowClass = 'pending-row';
                    estadoText = 'PENDIENTE';
                    pendientes++;
                }
                
                const tr = document.createElement('tr');
                tr.className = rowClass;
                tr.innerHTML = `
                    <td>${prov.razonSocial || (prov.nombres + ' ' + prov.apellidos)}</td>
                    <td>${prov.numeroIdentificacion}</td>
                    <td>Expediente</td>
                    <td>${fechaVencimiento.toLocaleDateString('es-ES')}</td>
                    <td><span>${diasRestantes > 0 ? diasRestantes + ' días' : Math.abs(diasRestantes) + ' días vencido'}</span></td>
                    <td><span class="badge ${badgeClass}">${estadoText}</span></td>
                    <td>
                      <button class="action-btn" title="Notificar"><i class="fa-solid fa-bell"></i></button>
                      <button class="action-btn" title="Ver perfil" onclick="window.location.href='buyer_supplier_profile.html?id=${prov.idProveedor}'"><i class="fa-solid fa-eye"></i></button>
                    </td>
                `;
                tbody.appendChild(tr);
            }
        });
        
        // Actualizar KPIs si existen
        const elsCriticos = document.querySelectorAll('.alert-kpi-card.critical .kpi-value');
        if (elsCriticos.length > 0) elsCriticos[0].textContent = criticos;
        const elsUrgentes = document.querySelectorAll('.alert-kpi-card.warning .kpi-value');
        if (elsUrgentes.length > 0) elsUrgentes[0].textContent = urgentes;
        const elsPendientes = document.querySelectorAll('.alert-kpi-card.pending .kpi-value');
        if (elsPendientes.length > 0) elsPendientes[0].textContent = pendientes;

    } catch (e) {
        console.error(e);
        const tbody = document.getElementById('alerts-body');
        if(tbody) tbody.innerHTML = '<tr><td colspan="7" style="color:red; text-align:center;">Error cargando alertas</td></tr>';
    }
}
